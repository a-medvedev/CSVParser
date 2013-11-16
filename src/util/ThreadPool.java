package util;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadPool {
    private ExecutorService es;
    
    public ThreadPool(int poolCount){
        es = Executors.newFixedThreadPool(poolCount);
    }

    public void submit(ThreadTask t){
        es.submit(t);
        //TODO добавить возврат значения(Future<V>)
    }

    public void shutdown(){
        es.shutdown();
        //TODO добавить возврат значения(Future<V>)
    }

    public boolean isShutdown(){
        return es.isShutdown();
    }

    public boolean isTerminated(){
        return es.isTerminated();
    }

    //public ThreadPool(int poolCount, Queue<ThreadTask>)
}
