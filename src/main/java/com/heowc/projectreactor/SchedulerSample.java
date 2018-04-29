package com.heowc.projectreactor;

import com.google.common.base.Stopwatch;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulerSample {

    private static final Stopwatch STOPWATCH = Stopwatch.createUnstarted();

    public static void main(String[] args) {

        STOPWATCH.start();

        Flux.range(1, 10)
            .publishOn(Schedulers.newParallel("pub"))
            .map(i -> {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return i * 2;
            })
            .log()
            .doOnTerminate(() -> System.out.println(STOPWATCH.stop()))
            .subscribeOn(Schedulers.newParallel("sub"))
            .subscribe(System.out::println);
    }
}