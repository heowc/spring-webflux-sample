package com.heowc.projectrector;

import com.heowc.projectrector.subscriber.SimpleSubscriber;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class Sample {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("project", "reactor", "spring", "webflux", "boot", "cloud");

        Flux.fromIterable(list)
            .map(String::toUpperCase)
            .subscribe(
                System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("Done")
            );

        Flux.fromIterable(list)
            .subscribe(new SimpleSubscriber());

        Flux<String> number = Flux.generate(
                () -> 1,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state);
                    if (state == 10) sink.complete();
                    return state + 1;
                });

        number.subscribe(
            System.out::println,
            Throwable::printStackTrace,
            () -> System.out.println("Done")
        );
    }
}
