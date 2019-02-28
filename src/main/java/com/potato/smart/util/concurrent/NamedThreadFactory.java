package com.potato.smart.util.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现描述：命名线程工厂
 *
 * @author dehuab
 */
public class NamedThreadFactory implements ThreadFactory {
    private static final Logger logger = LoggerFactory.getLogger(NamedThreadFactory.class);

    final private static String DEFAULT_NAME = "thread-factory";
    final private boolean daemon;
    final private ThreadGroup group;
    final private String name;
    final private AtomicInteger threadNumber = new AtomicInteger(0);

    NamedThreadFactory() {
        this(NamedThreadFactory.DEFAULT_NAME, true);
    }

    NamedThreadFactory(String name) {
        this(name, true);
    }

    NamedThreadFactory(String name, boolean daemon) {
        this.name = name;
        this.daemon = daemon;
        SecurityManager s = System.getSecurityManager();
        group = s != null ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, name + "-" + threadNumber.getAndIncrement(), 0);
        t.setDaemon(daemon);
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }

        t.setUncaughtExceptionHandler((thread, e) -> logger.error("from " + thread.getName(), e));
        return t;
    }
}
