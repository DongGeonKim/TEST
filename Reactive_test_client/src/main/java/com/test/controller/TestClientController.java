package com.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class TestClientController {
	
	@GetMapping("/test1")
	public String getText1() throws Exception {
		Thread.sleep(2000);
		return "test1";
	}
	
	@GetMapping("/test2")
    public Mono<String> getText2() throws Exception{
		Thread.sleep(2000);
		return Mono.just("test2");
    }
}
