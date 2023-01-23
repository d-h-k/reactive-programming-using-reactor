package com.learnreactiveprogramming.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
@Slf4j
public class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService fms = new FluxAndMonoGeneratorService();

    @DisplayName("플렉스검증성공")
    @Test
    public void fluxSuccess() {
        //given

        //when
        var fluxName = fms.namesFlux();
        //then
        StepVerifier.create(fluxName)
                .expectNext("alex","ben", "chloe")
                .verifyComplete();
    }    @DisplayName("플렉스검증실패")
    @Test
    public void fluxFail() {
        //given

        //when
        var fluxName = fms.namesFlux();
        //then
        StepVerifier.create(fluxName)
                .expectNext("ben","alex", "chloe")
                .verifyComplete();
        //
        log.warn("순서가 다르면 실패한다");
    }

    @DisplayName("Flux 요소의 갯수로 테스트")
    @Test
    public void run() {
        //given

        //when
        var fluxname1 = fms.namesFlux();
        //then
        StepVerifier.create(fluxname1)
                .expectNextCount(3)
                .verifyComplete();

        log.info("하나는 벨류로 체크하고, 나머지는 카운트로 체ㅔ");
        var fluxname2 = fms.namesFlux();
        //then
        StepVerifier.create(fluxname2)
                .expectNext("alex")
                .expectNextCount(2)
                .verifyComplete();
    }

    @DisplayName("모노도 테스트해봐야지")
    @Test
    public void monoTest() {
        //given

        //when
        var stringMono = fms.namesMono();

        //then
        StepVerifier.create(stringMono)
                .expectNext("alex")
                .verifyComplete();
    }

    @DisplayName("플랙스 맵")
    @Test
    void namesFluxMap() {
        var stringFlux = fms.namesFluxMap();
        StepVerifier.create(stringFlux)
                .expectNext("KIM", "DONG", "HUN")
                .verifyComplete();
    }

    @Test
    @DisplayName("플랙스이뮤터블리티")
    void namesFluxImmutability() {
        //놀랍게도 틀리다. 플렉스는 방어적 복사를 떄린다. 이뮤터블이다
        var stringFlux = fms.namesFluxImmutability();
        StepVerifier.create(stringFlux)
                .expectNext("KIM", "DONG", "HUN")
                .verifyComplete();
        log.info("리액티브 스트림은 불변이다");
    }

    @DisplayName("필터기능")
    @Test
    public void namesFluxFilter() {
        //given
        final int stringLenLimit = 3;
        //when
        var nameFlux = fms.namesFluxFilter(stringLenLimit);
        //then
        StepVerifier.create(nameFlux)
                .expectNextCount(1)
                .verifyComplete();
    }
}
