package com.heowc.projectrector;

import com.heowc.projectrector.subscriber.SimpleSubscriber;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Sample {

    public static void main(String[] args) throws InterruptedException {

        List<String> list = Arrays.asList("project", "reactor", "spring", "webflux", "boot", "cloud");

        // 1) simple
        Flux.fromIterable(list)
            .map(String::toUpperCase)
            .subscribe(
                System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("Done")
            );

        // 2) generate
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

        // 3) generate
        Flux<String> flux = Flux.generate(
            AtomicLong::new,
            (state, sink) -> {
                long i = state.getAndIncrement();
                sink.next("3 x " + i + " = " + 3*i);
                if (i == 10) sink.complete();
                return state;
            },
            (state) -> System.out.println("state: " + state));
        flux.subscribe(new SimpleSubscriber());

        // 4) handle
        Flux<String> alphabet = Flux.just(-1, 30, 13, 9, 20)
                .handle((i, sink) -> {
                    String letter = alphabet(i);
                    if (letter != null)
                        sink.next(letter);
                });
        alphabet.subscribe(System.out::println);
    }

    private static String alphabet(int letterNumber) {
        if (letterNumber < 1 || letterNumber > 26) {
            return null;
        }
        int letterIndexAscii = 'A' + letterNumber - 1;
        return "" + (char) letterIndexAscii;
    }
}