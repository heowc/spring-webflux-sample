package com.heowc.projectreactor;

import org.springframework.util.StopWatch;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Schedulers;

public class SchedulerSample {

    private static final StopWatch STOPWATCH = new StopWatch();

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
            .subscribeOn(Schedulers.newParallel("sub"))
            .subscribe(new BaseSubscriber<Integer>() {
                @Override
                protected void hookFinally(SignalType type) {
                    STOPWATCH.stop();
                    System.out.println(STOPWATCH.prettyPrint());
                }
            });
    }
}