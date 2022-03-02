package org.atumar4031.model;

import java.util.LinkedList;

public class MyPriorityQueue<T> {
    private T list;

    public MyPriorityQueue(){
        list = (T) new LinkedList<T>();
    }

    public void addToQueue(T c){
        list = c;
    }
}
