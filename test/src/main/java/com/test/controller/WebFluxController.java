package com.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class WebFluxController {
	
	@GetMapping("/webflux_test")
	private Flux<String> getEmployeeById() {
	    return Flux.just("111");
	}
	
}
