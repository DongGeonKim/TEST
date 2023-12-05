package com.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
public class TestController {
	
	@GetMapping("/test")
	public String getText() throws Exception {
		Thread.sleep(5000);
		return "test";
	}
	
	@GetMapping("/test2")
    public Mono<String> getText2() throws Exception{
        
		 return WebClient.create()
	                .get()
	                .uri("http://httpbin.org/delay/3")
	                .retrieve()
	                .bodyToMono(String.class);
		
    }
	
}
