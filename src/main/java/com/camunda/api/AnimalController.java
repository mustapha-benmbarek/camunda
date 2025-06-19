package com.camunda.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequestMapping("/api/animals")
public class AnimalController {

	@Autowired
	private AnimalService service;

	/* Saves animal images based on request input */
	@PostMapping
	public ResponseEntity<List<Animal>> save(@Valid @RequestBody AnimalRequest req) {
		log.debug("Request received ...");
		List<Animal> lstAnimals = service.save(req.getType(), req.getCount());
		return Optional.ofNullable(lstAnimals).filter(list -> !list.isEmpty()).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.badRequest().build());
	}

	/* Retrieves the most recently stored animal image */
	@GetMapping
	public ResponseEntity<Animal> fetch() {
		return service.fetch().map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
