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
        CopyOnWriteArrayList<String> result = new CopyOnWriteArrayList<String>();   //потокобезопасный массив для хранения результатов
        ThreadPool tp = new ThreadPool(4);              //фиксированный пул на 4 потока

        //указываем фал для разбора
        File f = new File("D:\\out.csv");
        Scanner s = new Scanner(f);

        ArrayList<String> block = new ArrayList<String>();  //блок для хранения строк, передаваемых в поток для разбора
        int counter = 0;    //кол-во обработаных линий
        while (s.hasNextLine()){
            block.add(s.nextLine());
            counter ++;
            if ((counter % 1000) == 0){ //каждую 1000 строк отправляем отдельному потоку для разбора
                tp.submit(new ThreadTask(block, result));
                block = new ArrayList<String>();
                continue;
            }
            if (!s.hasNextLine()){ //Если достигнут конец файла, а строк меньше 1000
                tp.submit(new ThreadTask(block, result));
                System.out.println("Обработано " + counter + " строк");
            }
        }
        tp.shutdown();  //необходимо для корректного завершения работающих потоков и пула

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
