package com.heowc;

import com.heowc.projectreactor.subscriber.SimpleSubscriber;
import org.junit.Test;
import reactor.core.publisher.Flux;

public class GenerateWithCustomizedSubscriberTest extends AbstractStopWatchTest {

    @Test
    public void test() {
        Flux<String> number = Flux.generate(
                () -> 1,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state);
                    if (state == 10) sink.complete();
                    return state + 1;
                });

        SimpleSubscriber ss = new SimpleSubscriber();
        number.subscribe(
                System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("Done"),
                s -> ss.request(1L)
        );
        number.subscribe(ss);
    }
}
