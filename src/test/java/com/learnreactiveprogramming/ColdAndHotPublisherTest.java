package com.learnreactiveprogramming;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static com.learnreactiveprogramming.util.CommonUtil.delay;

public class ColdAndHotPublisherTest {

    @Test
    public void coldPublisherTest() throws InterruptedException {

        Flux<Integer> stringFlux = Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1));

        stringFlux.subscribe(s -> System.out.println("Subscriber 1 : " + s)); //emits the value from beginning
        Thread.sleep(2000);
        stringFlux.subscribe(s -> System.out.println("Subscriber 2 : " + s));//emits the value from beginning
        Thread.sleep(10000);
    }

    @Test
    public void hotPublisherTest() throws InterruptedException {

        Flux<Integer> stringFlux = Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1));

        ConnectableFlux<Integer> connectableFlux = stringFlux.publish();
        connectableFlux.connect();

        connectableFlux.subscribe(s -> System.out.println("Subscriber 1 : " + s));
        Thread.sleep(3000);
        connectableFlux.subscribe(s -> System.out.println("Subscriber 2 : " + s)); // does not get the values from beginning
        Thread.sleep(10000);

    }

    @Test
    public void hotPublisherTest_autoConnect() throws InterruptedException {

        Flux<Integer> stringFlux = Flux.range(1, 10)
                .doOnSubscribe(s -> {
                    System.out.println("Subscription started");
                })
                .delayElements(Duration.ofSeconds(1));

        // this "autoConnect" call needs to be connected to the publish method itself
        var hotSource = stringFlux.publish().autoConnect(2);

        hotSource.subscribe(s -> System.out.println("Subscriber 1 : " + s));
        delay(1000);
        hotSource.subscribe(s -> System.out.println("Subscriber 2 : " + s)); // does not get the values from beginning
        System.out.println("Two subscribers connected");
        delay(2000);
        hotSource.subscribe(s -> System.out.println("Subscriber 3 : " + s)); // does not get the values from beginning
        Thread.sleep(10000);

    }

    @Test
    public void hotPublisherTest_refConnect() throws InterruptedException {

        Flux<Integer> stringFlux = Flux.range(1, 10)
                .doOnSubscribe(s -> {
                    System.out.println("Subscription started");
                })
                .doOnCancel(() -> {
                    System.out.println("Received Cancel Signal");
                })
                .delayElements(Duration.ofSeconds(1));

        // this "refCount" call needs to be connected to the publish method itself
        var hotSource = stringFlux.publish().refCount(2);

        var disposable = hotSource.subscribe(s -> System.out.println("Subscriber 1 : " + s));
        delay(1000);
        var disposable1 = hotSource.subscribe(s -> System.out.println("Subscriber 2 : " + s)); // does not get the values from beginning
        System.out.println("Two subscribers connected");
        delay(2000);
        disposable.dispose();
        disposable1.dispose();
        hotSource.subscribe(s -> System.out.println("Subscriber 3 : " + s)); // does not get the values from beginning

        // Run by showing the above code and then enable the below code and run it.
        /*delay(2000);
        hotSource.subscribe(s -> System.out.println("Subscriber 4: " + s)); // does not get the values from beginning
        Thread.sleep(10000);*/
    }
}