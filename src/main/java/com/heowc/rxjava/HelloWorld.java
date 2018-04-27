package com.heowc.rxjava;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class HelloWorld {

    private static final Logger logger = Logger.getLogger(HelloWorld.class.getSimpleName());

    public static void main(String[] args) throws InterruptedException {

        String[] array = { "Hello", "World" };

        Observable.fromArray(array)
                .delay(1, TimeUnit.SECONDS)
                .map(s -> String.format("%s 1", s))
                .subscribe(
                    logger::info,
                    Throwable::printStackTrace,
                    () -> logger.info("Done 1")
                );

        logger.info("End 1");

        Observable.fromArray(array)
                .map(s -> String.format("%s 2", s))
                .subscribe(
                    logger::info,
                    Throwable::printStackTrace,
                    () -> logger.fine("Done 2")
                );

        logger.info("End 2");

        Thread.sleep(1000L);
    }

    /*
    결과 >>
        End 1
        Hello 2
        World 2
        End 2
        Hello 1
        World 1
        Done 1
    */
}
