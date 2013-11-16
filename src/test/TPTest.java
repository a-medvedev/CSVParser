package test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author artem.s.medvedev@gmail.com
 */
public class TPTest {
    private ExecutorService es;
   // private Queue q = new LinkedList();
    
    public TPTest(int count){ //count - кол-во потоков в пуле
        es = Executors.newFixedThreadPool(count);
        //q = tasks;
    }
    
    public void submit(TaskThread task){
        es.submit(task);
    }
    
    public void off(){
        es.shutdown();
    }
}

class TaskThread implements Runnable{
    private String name;
    
    public TaskThread(String n){
        name = n;
    }
        
    @Override
    public void run() {
        for (int i = 0; i < 5; i++){
            System.out.println(name + ": " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
               System.out.println("Interrupted exception in " + name);
            }
        }
    }
    
}