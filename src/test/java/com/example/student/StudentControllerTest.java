package com.example.student;

import com.example.student.controller.StudentController;
import com.example.student.exception.CustomException;
import com.example.student.model.ApiResponse;
import com.example.student.model.ErrorCode;
import com.example.student.model.Student;
import com.example.student.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentControllerTest {

	@Mock
	private StudentService studentService;

	@InjectMocks
	private StudentController studentController;

	@BeforeEach
	void setUp() {
		// 각 테스트 실행 전에 Mock 객체 초기화
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddStudent_Success() {
		// given: 테스트를 위한 초기 데이터 설정
		String name = "John";
		int grade = 5;
		Student student = new Student(name, grade);
		when(studentService.addStudent(name, grade)).thenReturn(student);

		// when: 테스트 대상 메서드 실행
		ApiResponse response = studentController.add(name, grade);

		// then: 결과 검증
		assertNotNull(response);
		assertEquals(1, response.getResults().size());
		assertEquals(student, response.getResults().get(0));
		verify(studentService, times(1)).addStudent(name, grade);
	}

	@Test
	void testAddStudent_GradeExceedsLimit() {
		// given: 잘못된 데이터 설정
		String name = "John";
		int grade = 7;

		// when & then: 예외 발생 여부 검증
		CustomException exception = assertThrows(CustomException.class, () -> studentController.add(name, grade));
		assertEquals(ErrorCode.BAD_REQUEST, exception.getErrorCode());
		assertEquals("grade는 6 이상을 입력할 수 없습니다", exception.getMessage());
	}

	@Test
	void testGetAllStudents_Success() {
		// given: 학생 목록 설정
		List<Student> students = List.of(new Student("Alice", 3), new Student("Bob", 4));
		when(studentService.getAll()).thenReturn(students);

		// when: 학생 전체 조회 메서드 실행
		ApiResponse response = studentController.getAll();

		// then: 결과 검증
		assertNotNull(response);
		assertEquals(2, response.getResults().size());
		assertEquals(students, response.getResults());
		verify(studentService, times(1)).getAll();
	}

	@Test
	void testGetStudentByGrade_Success() {
		// given: 특정 성적의 학생 설정
		int grade = 3;
		List<Student> students = List.of(new Student("Alice", grade));
		when(studentService.getStudentByGrade(grade)).thenReturn(students);

		// when: 특정 성적의 학생 조회 메서드 실행
		ApiResponse response = studentController.getStudentByGrade(grade);

		// then: 결과 검증
		assertNotNull(response);
		assertEquals(1, response.getResults().size());
		assertEquals(students, response.getResults());
		verify(studentService, times(1)).getStudentByGrade(grade);
	}

	@Test
	void testCustomExceptionHandler() {
		// given: 예외 객체 설정
		CustomException exception = new CustomException(ErrorCode.BAD_REQUEST, "Test Error", null);

		// when: 예외 핸들러 실행
		ApiResponse response = studentController.customExceptionHandler(exception, new MockHttpServletResponse());

		// then: 결과 검증
		assertNotNull(response);
		assertEquals(ErrorCode.BAD_REQUEST.getCode(), response.getStatus().getCode());
		assertEquals("Test Error", response.getStatus().getMessage());
	}
}
