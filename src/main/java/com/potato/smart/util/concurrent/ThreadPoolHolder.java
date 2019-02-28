package com.potato.smart.util.concurrent;

import lombok.experimental.UtilityClass;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * in order to best usage an manage thread in pool, every business should define their own fit pool and avoid to run need too many time task in it.
 *
 * @author dehuab
 **/
@UtilityClass
public class ThreadPoolHolder {

    private static final int CORE = Runtime.getRuntime().availableProcessors();

    public static final ThreadPoolExecutor SMART_EXECUTOR = new ThreadPoolExecutor(
            CORE,
            CORE << 3,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(CORE << 4),
            new NamedThreadFactory("smart", false),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    public static final ThreadPoolExecutor DEMO_EXECUTOR = new ThreadPoolExecutor(
            0,
            6,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(4)
    );
}
