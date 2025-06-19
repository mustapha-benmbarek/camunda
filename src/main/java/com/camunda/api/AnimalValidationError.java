package com.camunda.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalValidationError {

	/* Name of the object where the validation error occurred */
	private String object;

	/* Name of the field that failed validation */
	private String field;

	/* The invalid value that was rejected */
	private Object rejectedValue;

	/* Descriptive validation error message */
	private String message;
}