package com.migdalmateusz.githubproject.domain;

public class BadRequestException extends RuntimeException {

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
