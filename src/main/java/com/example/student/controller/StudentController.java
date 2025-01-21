package com.example.student.controller;

import com.example.student.exception.CustomException;
import com.example.student.exception.InputRestriction;
import com.example.student.model.ApiResponse;
import com.example.student.model.ErrorCode;
import com.example.student.service.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
	private final StudentService studentService;

	// 헬퍼 메서드. 복수건과 단건을 모두 처리
	public <T> ApiResponse<T> makeResponse(List<T> result) {
		return new ApiResponse<T>(result);
	}

	public <T> ApiResponse<T> makeResponse(T result) {
		return makeResponse(Collections.singletonList(result));
	}

	// 이름과 성적을 입력받아서 저장
	@PostMapping("/student")
	public ApiResponse add(
		@RequestParam("name") String name,
		@RequestParam("grade") int grade
	) {
		// 예외처리 추가
		if( grade >=6 ) {
			throw new CustomException(ErrorCode.BAD_REQUEST, "grade는 6 이상을 입력할 수 없습니다",
				new InputRestriction(6));
		}
		return makeResponse(studentService.addStudent(name, grade));
	}

	// 전체 조회
	@GetMapping("/students")
	public ApiResponse getAll() {
		return makeResponse(studentService.getAll());
	}

	// 특정 성적의 학생들 조회
	@GetMapping("/students/{grade}")
	public ApiResponse getStudentByGrade(
		@PathVariable("grade") int grade
	) {
		return makeResponse(studentService.getStudentByGrade(grade));
	}

	// 예외 처리
	@ExceptionHandler(CustomException.class)
	public ApiResponse customExceptionHandler(CustomException customException, HttpServletResponse response) {
		response.setStatus(customException.getErrorCode().getHttpStatus().value());
		return new ApiResponse(customException.getErrorCode().getCode(), customException.getMessage(), customException.getData());
	}
}
