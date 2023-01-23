package com.learnreactiveprogramming.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.learnreactiveprogramming.util.CommonUtil.delay;

public class FluxAndMonoSchedulersService {
    private static final Logger log = LoggerFactory.getLogger("FluxAndMonoSchedulersService");
    static List<String> namesList = List.of("alex", "ben", "chloe");
    static List<String> namesList1 = List.of("adam", "jill", "jack");
    static List<String> myList = List.of("alex,ben,choi");


    public static void main(String[] args) {
        FluxAndMonoSchedulersService fmsService = new FluxAndMonoSchedulersService();
        fmsService.namesFlux()
                .subscribe(name -> log.info(name));

        log.info("\n\n namesMono ----");
        fmsService.namesMono()
                .subscribe(name -> log.info(name));

        log.info("\n\n Mono test----");
//        fmsService.listMonoDong()
//                .subscribe(myList.stream().forEach(s -> log.info(s)));
//        이렇게는 안되는구나..


    }

    private Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"));
        //이런식으로 무한대의 요소를 처리 할 수 있음
    }

    public Mono<String> namesMono() {
        return Mono.just("alex");
        //하나만 가능
    }

    public Mono<List<String>> listMonoDong() {
        return Mono.just(myList);
    }


    private String upperCase(String name) {
        delay(1000);
        return name.toUpperCase();
    }

}
