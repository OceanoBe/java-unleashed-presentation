package org.oceanobe.multithreading;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class CustomThreadPool {

    public static ThreadPoolExecutor createCustomThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, String threadNamePrefix) {
        return (ThreadPoolExecutor) Executors.newFixedThreadPool(corePoolSize, new CustomThreadFactory(threadNamePrefix));
    }
}
