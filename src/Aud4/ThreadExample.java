package Aud4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        SharedSource source1 = new SharedSource();
        SharedSource source2 = new SharedSource();


        Thread t1 = new CustomThread(source1);
        Thread t2 = new Thread(new CustomRunnable(source2));

        t2.start();
        t2.join();
        source2.increment();
        int final_count = source2.getCounter(); //vo finalcount iame 21
        System.out.println(final_count);    //21

        t1.start();
        source1.increment();     //source.getCounter ==22;
        System.out.println(source1.getCounter()); //22;
       t1.join();
        System.out.println(source1.getCounter());    //42
    }
}

class SharedSource{
    private static int counter=0;
   // private static Lock lock = new ReentrantLock();

    public static void increment(){
     //   lock.lock();
        counter++;
       // lock.unlock();
    }
    public int getCounter(){
        return counter;
    }
}

class CustomThread extends Thread{
    private SharedSource source;

    public CustomThread(SharedSource source){
        this.source = source;
    }

    @Override
    public void run() {
        for (int i=0;i<20;i++){
            source.increment();
            System.out.println("Thread class");
        }
    }
}

class CustomRunnable implements Runnable{

    private SharedSource source;

    public CustomRunnable(SharedSource source){
        this.source = source;
    }

    @Override
    public void run() {
        for (int i=0;i<20;i++){
            source.increment();
            System.out.println("Runnable class");
        }
    }
}
