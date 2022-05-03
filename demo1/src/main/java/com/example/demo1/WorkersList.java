package com.example.demo1;

import java.util.ArrayList;

public class WorkersList {
    private static ArrayList<Workers> workers;
    public static ArrayList<Workers> getWorkers(){
        if (workers==null){
            workers=new ArrayList<Workers>();
        }
        return workers;
    };
}
