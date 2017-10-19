package com.cmr.cloud.contentrating.service;

import java.lang.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED, reason = "Rating already exists")
public class DuplicateRatingException extends Exception {

	public DuplicateRatingException(String message) {
		super(message);
	}
}
