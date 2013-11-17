package parser;

import util.ThreadPool;

import java.io.File;
import java.util.ArrayList;


public class CSVParserMultiThread {
    private ArrayList<File> workFilesList;   //список файлов, с которым будет работать парсер
    private ThreadPool tp;
    
    public CSVParserMultiThread(ArrayList<File> files, int threadCount) {
        workFilesList = files;
        tp = new ThreadPool(threadCount);
    }

    public void doParse(){
        for (File currentFile : workFilesList){
            if (!isCSV(currentFile)){
                continue;
            } else {

            }
        }
    }

    //проверяет является ли файл csv-файлом
    private boolean isCSV(File f){
        if (f.getName().endsWith(".csv")){
            return true;
        } else {
            return false;
        }
    }
    
}
