package com.heowc;

import io.reactivex.Observable;

public class HelloWorld {

    public static void main(String[] args) {
        String[] array = { "Hello", "World" };

        Observable.just(array)
                .subscribe(
                        System.out::println,
                        Throwable::printStackTrace,
                        () -> System.out.println("Done")
                );
    }
}
