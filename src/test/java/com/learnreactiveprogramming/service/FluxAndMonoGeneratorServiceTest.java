package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

public class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService fms = new FluxAndMonoGeneratorService();

    @DisplayName("")
    @Test
    public void flux() {
        //given

        //when
        var fluxName = fms.namesFlux();
        //then
        StepVerifier.create(fluxName)
                .expectNext("alex","ben", "chloe")
                .verifyComplete();
    }

}
