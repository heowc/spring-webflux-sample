package com.heowc;

import org.junit.After;
import org.junit.Before;
import org.springframework.util.StopWatch;

public abstract class AbstractStopWatchTest {

    private StopWatch stopwatch = new StopWatch();

    @Before
    public void start() {
        stopwatch.start();
    }

    @After
    public void end() {
        stopwatch.stop();
        System.out.println(stopwatch.prettyPrint());
    }
}
