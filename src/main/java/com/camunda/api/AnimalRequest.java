package com.camunda.api;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalRequest {

	/*
	 * Type of animal to fetch (must be 'cat', 'dog', or 'bear'). 
	 * Cannot be blank and must match allowed values (case-insensitive).
	 */
	@NotBlank(message = "{api.err.type.msg.blank}")
	@Pattern(regexp = "cat|dog|bear", flags = Pattern.Flag.CASE_INSENSITIVE, message = "{api.err.type.msg.case}")
	private String type;

	/*
	 * Number of images to fetch. 
	 * Must be between 1 and 10 (inclusive) and not null.
	 */
	@NotNull(message = "{api.err.count.msg.notnull}")
	@Min(value = 1, message = "{api.err.count.msg.min}")
	@Max(value = 10, message = "{api.err.count.msg.max}")
	private Integer count;
}
