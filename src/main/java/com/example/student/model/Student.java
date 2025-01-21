package com.example.student.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Student implements Comparable<Student>{
	private String name;
	private int grade;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Student student = (Student) o;
		return grade == student.grade && Objects.equals(name, student.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, grade);
	}

	@Override
	public int compareTo(Student o) {
		return this.grade - o.getGrade();
	}
}
