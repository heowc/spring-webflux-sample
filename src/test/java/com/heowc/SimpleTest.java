package com.heowc;

import org.junit.Test;
import reactor.core.publisher.Flux;

import static com.heowc.Constant.LIST;

public class SimpleTest extends AbstractStopWatchTest {

    @Test
    public void simple() {
        Flux.fromIterable(LIST)
                .map(String::toUpperCase)
                .subscribe(
                        System.out::println,
                        Throwable::printStackTrace,
                        () -> System.out.println("Done")
                );
    }
}
