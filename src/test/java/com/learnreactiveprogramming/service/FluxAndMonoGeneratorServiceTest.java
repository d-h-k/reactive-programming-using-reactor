package com.learnreactiveprogramming.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
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

}
