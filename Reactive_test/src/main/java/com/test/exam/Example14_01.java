package com.test.exam;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class Example14_01 {
    public static void main(String[] args) {
        Mono
            .justOrEmpty(null)
            .subscribe(data -> {},
                    error -> {},
                    () -> log.info("# onComplete"));
    }
}
