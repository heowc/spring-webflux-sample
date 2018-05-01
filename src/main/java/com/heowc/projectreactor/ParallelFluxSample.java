package com.heowc.projectreactor;

import org.springframework.util.StopWatch;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ParallelFluxSample {

    private static final StopWatch STOPWATCH = new StopWatch();

    public static void main(String[] args) {
        STOPWATCH.start();

        Flux.range(1, 10)
                .parallel()
                .runOn(Schedulers.newParallel("pub"))
                .log()
                .doOnNext(i -> {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnComplete() {
                        STOPWATCH.stop();
                        System.out.println(STOPWATCH.prettyPrint());
                    }
                });
    }
}
