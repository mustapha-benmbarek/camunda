package com.camunda;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.camunda.api.AnimalController;
import com.camunda.api.AnimalService;

@WebMvcTest(AnimalController.class)
public class AnimalControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AnimalService service;

	@Test
	public void fetch_ShouldReturnNotFound_WhenNoAnimalFound() throws Exception {
		when(service.fetch()).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/animals")).andExpect(status().isNotFound());
	}
}
