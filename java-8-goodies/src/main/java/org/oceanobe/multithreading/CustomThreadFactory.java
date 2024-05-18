package org.oceanobe.multithreading;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadFactory implements ThreadFactory {
    private final String prefix;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public CustomThreadFactory(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(prefix + "-thread-" + threadNumber.getAndIncrement());
        return thread;
    }
}