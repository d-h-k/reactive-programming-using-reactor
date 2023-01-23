package com.learnreactiveprogramming.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.learnreactiveprogramming.util.CommonUtil.delay;

public class FluxAndMonoGeneratorService {
    private static final Logger log = LoggerFactory.getLogger("FmsService");
    static List<String> namesList = List.of("alex", "ben", "chloe");
    static List<String> namesList1 = List.of("adam", "jill", "jack");
    static List<String> myList = List.of("alex,ben,choi");


    public static void main(String[] args) {
        FluxAndMonoGeneratorService fmsService = new FluxAndMonoGeneratorService();
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

    public Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
        //이런식으로 무한대의 요소를 처리 할 수 있음
                .log(); //로그를 찍어보면 아래에서 일어나는 일을 알수있음
        /*
        프로젝트 리엑터가 이런일을 해준다고 함
        12:40:57.429 [main] INFO reactor.Flux.Iterable.1 - | onSubscribe([Synchronous Fuseable] FluxIterable.IterableSubscription)
        12:40:57.430 [main] INFO reactor.Flux.Iterable.1 - | request(unbounded)
        12:40:57.431 [main] INFO reactor.Flux.Iterable.1 - | onNext(alex)
        12:40:57.431 [main] INFO FluxAndMonoSchedulersService - alex
        12:40:57.431 [main] INFO reactor.Flux.Iterable.1 - | onNext(ben)
        12:40:57.431 [main] INFO FluxAndMonoSchedulersService - ben
        12:40:57.431 [main] INFO reactor.Flux.Iterable.1 - | onNext(chloe)
        12:40:57.431 [main] INFO FluxAndMonoSchedulersService - chloe
        12:40:57.431 [main] INFO reactor.Flux.Iterable.1 - | onComplete()
        12:40:57.431 [main] INFO FluxAndMonoSchedulersService -
         */
    }

    public Mono<String> namesMono() {
        log.info("namesMono()");
        return Mono.just("alex")
                //하나만 가능
                .log();
        /*
        12:44:12.966 [main] INFO FmsService -  namesMono
        12:44:12.985 [main] INFO reactor.Mono.Just.2 - | onSubscribe([Synchronous Fuseable] Operators.ScalarSubscription)
        12:44:12.985 [main] INFO reactor.Mono.Just.2 - | request(unbounded)
        12:44:12.985 [main] INFO reactor.Mono.Just.2 - | onNext(alex)
        12:44:12.985 [main] INFO FmsService - alex
        12:44:12.985 [main] INFO reactor.Mono.Just.2 - | onComplete()
        12:44:12.985 [main] INFO FmsService -\
         */
    }

    @Deprecated(since = "야 이거 안돌아가네..")
    public Mono<List<String>> listMonoDong() {
        return Mono.just(myList);
    }


    public Flux<String> namesFluxMap() {
        log.info("namesFluxMap()");
        return Flux.fromIterable(List.of("kim", "dong", "hun"))
                .map(String::toUpperCase)
                .log();
    }
    public Mono<String> namesMonoMap() {
        log.info("namesMonoMap()");
        return Mono.just("alex")
                //하나만 가능
                .log();
    }


    public Flux<String> namesFluxImmutability() {
        log.info("namesFluxImmutability()");
        var fluxImmu = Flux.fromIterable(List.of("kim", "dong", "hun"));
        fluxImmu.map(String::toUpperCase);
        return fluxImmu;
    }


    public Flux<String> namesFluxFilter(int stringLen) {
        log.info("namesFluxFilter()");
        return Flux.fromIterable(List.of("kim", "dong", "hun"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLen)
                .map(name ->  name.length()+ "-"+ name)
                .log();
    }

    public Mono<String> namesMono_map_filter(int stringLength) {
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .log();
    }


}
