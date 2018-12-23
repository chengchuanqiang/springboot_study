package com.ccq.springbootkafka.algorithm.play.maze;

import java.util.ArrayList;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2018/12/23 21:50
 */
public class RandomQueue<E> {
    private ArrayList<E> queue;

    public RandomQueue() {
        queue = new ArrayList<E>();
    }

    public void add(E e) {
        queue.add(e);
    }

    public E remove() {
        if (queue.size() == 0)
            throw new IllegalArgumentException("There's no element to remove in Random Queue");

        int randIndex = (int) (Math.random() * queue.size());

        E randElement = queue.get(randIndex);
        queue.set(randIndex, queue.get(queue.size() - 1));
        queue.remove(queue.size() - 1);

        return randElement;
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
