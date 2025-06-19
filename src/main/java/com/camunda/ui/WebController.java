package com.camunda.ui;

import java.util.Base64;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.camunda.api.Animal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WebController {

	@GetMapping("/")
	public String redirectToWeb() {
		return "redirect:/fetch";
	}

	@GetMapping("/web")
	public String showForm(Model model) {
		model.addAttribute("types", new String[] { "cat", "dog", "bear" });
		return "form";
	}

	/*
	@PostMapping("/web/form")
	public String processForm(@RequestParam String type, @RequestParam int count, Model model) {
		model.addAttribute("type", type);
		model.addAttribute("count", count);
		return "result";
	}
	*/
	
	@GetMapping("/fetch")
	public String showImage(Model model) throws RestClientException {
		RestTemplate restTemplate = new RestTemplate();
		Animal animal = restTemplate.getForObject("http://localhost:8080/api/animals", Animal.class);
		String animalPicture = Base64.getEncoder().encodeToString(animal.getImageBinary());
	    model.addAttribute("type", animal.getType());
	    model.addAttribute("imageBase64", animalPicture);
	    model.addAttribute("mime", animal.getImageBinaryFormat()); 	    
	    return "fetch";
	}
	
}
