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
    	Flux<String> flux =
                Mono.justOrEmpty("Steve")
                        .concatWith(Mono.justOrEmpty("Jobs").concatWith(Mono.justOrEmpty("abs")));
        flux.subscribe(System.out::println);
    }
}
	