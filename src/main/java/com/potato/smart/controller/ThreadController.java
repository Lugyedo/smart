package com.potato.smart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.potato.smart.util.concurrent.ThreadPoolHolder.DEMO_EXECUTOR;
import static com.potato.smart.util.concurrent.ThreadPoolHolder.SMART_EXECUTOR;

/**
 * @author dehuab
 */
@RestController
public class ThreadController {
    private static final Logger logger = LoggerFactory.getLogger(ThreadController.class);

    @RequestMapping(value = "/demoThreadPool")
    public String demoThreadPool() {
        for (int i = 0; i < 20; i++) {
            final int j = i;
            SMART_EXECUTOR.execute(new Runnable() {
                                       @Override
                                       public void run() {
                                           try {
                                               Thread.sleep(3000);
                                           } catch (Exception e) {
                                               logger.error("Exception", e);
                                           }
                                           logger.info("Task-" + j + " completed. QueueSize:" + DEMO_EXECUTOR.getQueue().size());
                                       }
                                   }
            );
        }
        return new Date() + " Thread Pool Size=" + SMART_EXECUTOR.getPoolSize();
    }
}
