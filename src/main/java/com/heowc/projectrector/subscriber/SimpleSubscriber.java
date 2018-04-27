package com.heowc.projectrector.subscriber;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

public class SimpleSubscriber extends BaseSubscriber<String> {

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        subscription.request(1L);
    }

    @Override
    protected void hookOnNext(String value) {
        System.out.println("value: " + value);
        request(1L);
    }
}
