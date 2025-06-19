package com.camunda.api;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalRequestError {

	/* HTTP status code (e.g., 400, 404, 500) */
	private int status;

	/* Short error description (e.g., "Bad Request") */
	private String error;

	/* Detailed error message or reason (e.g, Validation failed) */
	private String message;

	/* Request path (e.g., /api/animals) where the error occurred */
	private String path;

	/* Timestamp of when the error occurred */
	private Instant timestamp;

	/* Optional list of field-level validation errors */
	private List<AnimalValidationError> subErrors;
}
