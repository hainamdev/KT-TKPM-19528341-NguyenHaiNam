package com.example.demo.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Subject;




@RestController
public class Controller {
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@GetMapping("/{id}")
	public Subject findSubject(@PathVariable int id) {
		Subject subject = restTemplate().getForObject("http://localhost:8081/"+id,Subject.class);
		return subject;
	}

	@PostMapping("/save")
	public Subject saveSubject(@RequestBody Subject sub) {
		System.out.println(sub);
		restTemplate().postForEntity("http://localhost:8081/save", sub, String.class);
		return sub;
	}
}
