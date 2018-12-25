package com.ccq.springbootkafka.algorithm.play.maze;

import java.util.LinkedList;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2018/12/23 22:31
 */
public class RandomQueue2<E> {
    private LinkedList<E> queue;

    public RandomQueue2(){
        queue = new LinkedList<E>();
    }

    public void add(E e){
        if(Math.random() < 0.5)
            queue.addFirst(e);
        else
            queue.addLast(e);
    }

    public E remove(){
        if(queue.size() == 0)
            throw new IllegalArgumentException("There's no element to remove in Random Qeuue");


        if(Math.random() < 0.5)
            return queue.removeFirst();
        else
            return queue.removeLast();
    }

    public int size(){
        return queue.size();
    }

    public boolean isEmpty(){
        return size() == 0;
    }
}
