package com.camunda.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

	private final AnimalRepository repository;
	private final RestTemplate restTemplate = new RestTemplate();

	@Autowired
	private Environment env;

	@Override
	@Transactional
	public List<Animal> save(String type, int count) {
		List<Animal> animals = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			String imageUrl = fetchImageUrlByType(type);
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.IMAGE_JPEG, MediaType.IMAGE_PNG, MediaType.ALL));
            HttpEntity<Void> entity = new HttpEntity<>(headers);
            ResponseEntity<byte[]> response = restTemplate.exchange(imageUrl, HttpMethod.GET,entity,byte[].class);
			byte[] imageBinary = response.getBody();
			String imageBinaryFormat = response.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE);
			Animal animal = new Animal(null, type, imageUrl, imageBinary, imageBinaryFormat);
			animals.add(repository.save(animal));
		}
		return animals;
	}

	@Override
	public Optional<Animal> fetch() {
		return repository.findFirstByOrderByIdDesc();
	}

	
	private String fetchImageUrlByType(String type) {
		switch (type.toLowerCase()) {
		case "cat":
			return env.getProperty("api.service.cat");
		case "dog":			
			return (String) restTemplate.getForObject(env.getProperty("api.service.dog"), Map.class).get("message");
		case "bear":			
			return String.format(env.getProperty("api.service.bear"), ThreadLocalRandom.current().nextInt(200, 400));
		default:
			return env.getProperty("api.service.cat");
		}
	}

}
