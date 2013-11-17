package util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author artem.s.medvedev@gmail.com
 */

//Обертка для ExecutorService
public class ThreadPool {
    private ExecutorService es;
    
    public ThreadPool(int threadCount){
        es = Executors.newFixedThreadPool(threadCount);
    }

    public void submit(ThreadTask t){
        es.submit(t);
        //TODO добавить возврат значения(Future<V>)
    }

    public void shutdown(){
        es.shutdown();
    }

    public boolean isShutdown(){
        return es.isShutdown();
    }

    public boolean isTerminated(){
        return es.isTerminated();
    }
}
