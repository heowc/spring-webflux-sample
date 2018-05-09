package com.heowc;

import io.reactivex.exceptions.CompositeException;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

public class SimpleTest extends AbstractStopWatchTest {

    @Test
    public void testAppendBoomError() {
        Flux<String> source = Flux.just("foo", "bar");

        StepVerifier.create(appendBoomError(source))
                .expectNext("foo") // onNext
//                .consumeNextWith(System.out::println)
                .expectNextCount(1L)
//                .expectNext("bar")
//                .expectComplete() // onComplete
                .expectErrorMessage("boom") // onError
                .verify();
    }

    @Test
    public void testVerifyThenAssertThat() {
        Flux<String> source = Flux.just("foo", "bar");

        StepVerifier.create(appendBoomError(source))
                .expectNext("foo") // onNext
                .expectNext("bar")
                .expectErrorMessage("boom") // onError
                .verifyThenAssertThat()
                .hasOperatorErrors(1)
                .hasOperatorErrorWithMessage("boom");
    }

    private <T> Flux<T> appendBoomError(Flux<T> source) {
        return source.concatWith(Mono.error(new IllegalArgumentException("boom")));
    }

    @Test
    public void testNoEventComplete() {
        StepVerifier.withVirtualTime(() -> Mono.delay(Duration.ofDays(1)))
                .expectSubscription()
                .expectNoEvent(Duration.ofDays(1))
                .expectNext(0L)
                .verifyComplete();
    }

    @Test
    public void testContext() {
        final String key = "message";
        Mono<String> r = Mono.just("Hello")
                .subscriberContext(ctx -> ctx.put(key, "World"))
                .flatMap(s -> Mono.subscriberContext()
                        .map(ctx -> s + " " + ctx.get(key)));

        StepVerifier.create(r)
                .expectNext("Hello World")
                .verifyComplete();
    }


}
