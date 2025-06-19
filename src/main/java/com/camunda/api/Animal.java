package com.camunda.api;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "animals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

	/* Primary key of the animal record (auto-generated) */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/* Type of the animal (e.g. cat, dog, bear) */
	@Column(nullable = false)
	private String type;

	/* Source URL from which the image was fetched */
	@Column(nullable = false)
	private String imageUrl;

	/* Binary content of the animal image (stored as BLOB) */
	@Lob
	@Column(columnDefinition = "BYTEA", nullable = false)
	private byte[] imageBinary;

	/* Binary content type of the animal image (e.g. png, jpg) */
	@Column(nullable = false)
	private String imageBinaryFormat;

}
