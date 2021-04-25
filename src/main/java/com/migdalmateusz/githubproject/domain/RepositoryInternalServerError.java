package com.migdalmateusz.githubproject.domain;

public class RepositoryInternalServerError extends RuntimeException {

	public RepositoryInternalServerError(String message, Throwable cause) {
		super(message, cause);
	}
}
