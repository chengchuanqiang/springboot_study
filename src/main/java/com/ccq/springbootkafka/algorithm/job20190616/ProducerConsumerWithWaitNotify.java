package com.ccq.springbootkafka.algorithm.job20190616;

/**
 * @Description:
 * @Author: ChengChuanQiang
 * @Date: 2019/6/26 0:06
 */
public class ProducerConsumerWithWaitNotify {


    public static void main(String[] args) {
        Resource resource = new Resource();
        //生产者线程
        ProducerThread p1 = new ProducerThread(resource);
        ProducerThread p2 = new ProducerThread(resource);
        ProducerThread p3 = new ProducerThread(resource);
        //消费者线程
        ConsumerThread c1 = new ConsumerThread(resource);
        //ConsumerThread c2 = new ConsumerThread(resource);
        //ConsumerThread c3 = new ConsumerThread(resource);

        p1.start();
        p2.start();
        p3.start();
        c1.start();
    }
}

class Resource {
    private int num = 0;
    private int size = 10;

    public synchronized void remove() {
        if (num > 0) {
            num--;
            System.out.println("消费者 " + Thread.currentThread().getName() + " 消费一件商品，剩余产品数量：" + num);
            notifyAll();
        } else {
            try {
                wait();
                System.out.println("消费者 " + Thread.currentThread().getName() + " 线程进入等待状态");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void add() {
        if (num < size) {
            num++;
            System.out.println("生产者 " + Thread.currentThread().getName() + " 生产一件商品，剩余产品数量：" + num);
            notifyAll();
        } else {
            try {
                wait();
                System.out.println("生产者 " + Thread.currentThread().getName() + " 线程进入等待状态");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class ConsumerThread extends Thread {

    private Resource resource;

    public ConsumerThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            resource.remove();
        }
    }
}

class ProducerThread extends Thread {

    private Resource resource;

    public ProducerThread(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            resource.add();
        }
    }

}
