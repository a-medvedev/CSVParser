package test;

/**
 *
 * @author artem.s.medvedev@gmail.com
 */
public class Launcher {
    public static void main(String[] args){
        TPTest t = new TPTest(2);
        t.submit(new TaskThread("A"));
        t.submit(new TaskThread("B"));
        t.submit(new TaskThread("C"));
        t.submit(new TaskThread("D"));
        t.off();
    }
}
