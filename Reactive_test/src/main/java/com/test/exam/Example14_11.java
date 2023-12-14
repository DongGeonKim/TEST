package com.test.exam;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

/**
 * generate 예제
 */
@Slf4j
public class Example14_11 {
    public static void main(String[] args) {
        Map<Integer, Tuple2<Integer, Long>> map =
                                            SampleData.getBtcTopPricesPerYearMap();
        Flux
                .generate(() -> 2019, (state, sink) -> {
                    if (state > 2021) {
                        sink.complete();
                    } else {
                        sink.next(map.get(state));
                    }

                    return ++state;
                })
                .subscribe(data -> log.info("# onNext: {}", data));
    }
}
