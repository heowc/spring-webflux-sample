package com.heowc;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class SimpleTest extends AbstractStopWatchTest {

    @Test
    public void testAppendBoomError() {
        Flux<String> source = Flux.just("foo", "bar");

        StepVerifier.create(appendBoomError(source))
                .expectNext("foo") // onNext
                .expectNext("bar")
//                .expectComplete() // onComplete
                .expectErrorMessage("boom") // onError

                .verify();
    }

    private <T> Flux<T> appendBoomError(Flux<T> source) {
        return source.concatWith(Mono.error(new IllegalArgumentException("boom")));
    }
}
