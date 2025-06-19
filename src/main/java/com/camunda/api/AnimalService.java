package com.camunda.api;

import java.util.List;
import java.util.Optional;

public interface AnimalService {
	/* Saves a specified number of animal images of a given type */
	List<Animal> save(String type, int count);

	/* Retrieves the most recently stored animal image */
	Optional<Animal> fetch();
}