package com.camunda.api;

import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class AnimalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<AnimalRequestError> handleValidationExceptions(MethodArgumentNotValidException ex,
			HttpServletRequest req) {

		List<AnimalValidationError> validationErrors = ex.getBindingResult().getFieldErrors().stream()
				.map(fe -> AnimalValidationError.builder().object(fe.getObjectName()).field(fe.getField())
						.rejectedValue(fe.getRejectedValue()).message(fe.getDefaultMessage()).build())
				.toList();

		AnimalRequestError apiError = AnimalRequestError.builder().timestamp(Instant.now()).status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.getReasonPhrase()).message("Validation failed").path(req.getRequestURI())
				.subErrors(validationErrors).build();

		return ResponseEntity.badRequest().body(apiError);
	}
}
