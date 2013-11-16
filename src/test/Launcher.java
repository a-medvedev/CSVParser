package test;

import util.ThreadPool;
import util.ThreadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author artem.s.medvedev@gmail.com
 */
public class Launcher {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        CopyOnWriteArrayList<String> result = new CopyOnWriteArrayList<String>();
        ThreadPool tp = new ThreadPool(4);

        File f = new File("D:\\out.csv");
        Scanner s = new Scanner(f);

        ArrayList<String> block = new ArrayList<String>();
        int counter = 0;
        while (s.hasNextLine()){
            block.add(s.nextLine());
            counter ++;
            if ((counter % 1000) == 0){
                tp.submit(new ThreadTask(block, result));
                //block.clear();      //ошибка
                block = new ArrayList<String>();
                continue;
            }
            if (!s.hasNextLine()){
                tp.submit(new ThreadTask(block, result));
                System.out.println("Обработано " + counter + " строк");
            }
        }
        tp.shutdown();
        while (!tp.isTerminated()){
            System.out.println("Ожидаем завершение всех потоков");
            Thread.sleep(100);
        }

        //Вывод результирующего массива
        System.out.println("Результат");
        int c = 0;
        for (String str : result){
            System.out.println(str);
            c++;
        }
        System.out.println("Выведено " + c + " строк");
    }
}
