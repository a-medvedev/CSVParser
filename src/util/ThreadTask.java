package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

public class ThreadTask implements Runnable {
    private ArrayList<String> block;        //block - блок строк из csv-файла для разбора,
    private CopyOnWriteArrayList result;    //result - массив накапливающий результаты работы каждого потока

    public ThreadTask(ArrayList<String> b, CopyOnWriteArrayList<String> r){
        block = b;
        result = r;
    }

    @Override
    public void run() {
        for(final String s : block){
            List<String> words = Arrays.asList(s.split(","));
            for (final String w : words){
                //критерий сортировки при выполнении задачи
                if (w.length() > 15 && Character.isAlphabetic(w.charAt(0))){
                    result.add(w);
                }
            }
        }
    }
}
