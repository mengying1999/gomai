package com.gomai.order.delay;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池工具类
 */
public class ThreadPoolUtils {

    private final ExecutorService executor;

    private static ThreadPoolUtils instance = new ThreadPoolUtils();

    private ThreadPoolUtils() {
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    }

    public static ThreadPoolUtils getInstance() {
        return instance;
    }

    public static <T> Future<T> execute(final Callable<T> runnable) {
        return getInstance().executor.submit(runnable);
    }

    public static Future<?> execute(final Runnable runnable) {
        return getInstance().executor.submit(runnable);
    }
}
