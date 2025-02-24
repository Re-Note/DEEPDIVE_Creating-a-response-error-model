package com.example.student.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class ApiResponse<T> {
	private final Status status;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<T> results;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Metadata metadata;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Object data;

	// 생성자
	// 정상 응답
	public ApiResponse(List<T> results) {
		this.status = new Status(ErrorCode.OK.getCode(), ErrorCode.OK.getMessage());
		this.results = results;
		this.metadata = new Metadata(results.size());
	}

	// 예외 응답
	public ApiResponse(int code, String message, Object data) {
		this.status = new Status(code, message);
		this.data = data;
	}

	@Getter
	@AllArgsConstructor
	public static class Status {
		private int code;
		private String message;
	}

	@Getter
	@AllArgsConstructor
	private static class Metadata {
		private int resultCount = 0;
	}
}
