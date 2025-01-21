package com.example.student.service;

import com.example.student.model.Student;
import com.example.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
	private final StudentRepository studentRepository;

	// 이름과 성적을 입력받아 저장
	public Student addStudent(String name, int grade) {
		Student student = new Student(name, grade);
		studentRepository.add(student);
		return student;
	}

	// 전체 성적 조회
	public List<Student> getAll() {
		return studentRepository.getAll();
	}

	// 특정 성적의 학생 조회
	public List<Student> getStudentByGrade(int grade) {
		return studentRepository.getStudentByGrade(grade);
	}
}
