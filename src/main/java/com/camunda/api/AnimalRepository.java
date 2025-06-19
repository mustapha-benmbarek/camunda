package com.camunda.api;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

	/* Retrieves the most recently added Animal record based on descending ID */
	Optional<Animal> findFirstByOrderByIdDesc();
}
