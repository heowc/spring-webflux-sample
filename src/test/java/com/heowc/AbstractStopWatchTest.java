package com.heowc;

import com.google.common.base.Stopwatch;
import org.junit.After;
import org.junit.Before;

public abstract class AbstractStopWatchTest {

    private Stopwatch stopwatch = Stopwatch.createUnstarted();

    @Before
    public void start() {
        stopwatch.reset();
        stopwatch.start();
    }

    @After
    public void end() {
        stopwatch.stop();
        System.out.println(stopwatch);
    }
}
