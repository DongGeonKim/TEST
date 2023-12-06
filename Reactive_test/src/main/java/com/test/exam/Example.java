package com.test.exam;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Hello Reactor 예제
 */
@Slf4j
public class Example {
	public static void main(String[] args) {
    	Flux.concat(
                Flux.just("Mercury", "Venus", "Earth"),
                Flux.just("Mars", "Jupiter", "Saturn"),
                Flux.just("Uranus", "Neptune", "Pluto"))
        .collectList()
        //.subscribe(planets -> System.out.println(planets));
        .subscribe(System.out::println);
    }
}
	