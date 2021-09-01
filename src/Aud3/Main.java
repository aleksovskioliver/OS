package Aud3;

import java.sql.DataTruncation;
import java.time.LocalDateTime;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws InterruptedException {

      //  System.out.println("Execution into the main class.");

        Incrementor incrementor = new Incrementor();

        ThreadClass t1 = new ThreadClass("T1",incrementor);
        ThreadClass t2 = new ThreadClass("T2",incrementor);


        t1.start();
        t2.start();

        t1.join();
        t2.join();
        if (t1.isAlive() && t2.isAlive()){
            System.out.println("Thread is still alive");
            t1.interrupt();
            t2.interrupt();
        }

        System.out.println(Incrementor.getCount());
    }
}

class ThreadClass extends Thread {
    String name;
    Incrementor incrementor;

    public ThreadClass(String name, Incrementor incrementor) {
        this.name = name;
        this.incrementor = incrementor;
    }

    @Override
    public void run() {
        //   System.out.println("Execution in the Threat class.");
        for (int i = 0; i < 20; i++) {
            try {
                Incrementor.safeSemaphoreIncrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Incrementor{
    private static int count = 0;
    private final Lock lock = new ReentrantLock();
    public static Semaphore semaphore = new Semaphore(5);

    public static void safeSemaphoreIncrement() throws InterruptedException {
        semaphore.acquire();
        count++;
        semaphore.release();
    }

    public void safeMutexIncrement(){
        lock.lock();
        count++;
        lock.unlock();
    }
//
//
//    public void increment() throws InterruptedException {
//        count++;
//        Thread.sleep(1);
//    }
    public static int getCount(){
        return count;
    }
    public synchronized void increment(){
        count++;
//        synchronized (this){
//            count++;
//        }
    }
    public synchronized void safeClassIncrement(){
        count++;
//        synchronized (Incrementor.class) {
//            count++;
//        }                       ->Ova ni e potrebno ako vo metodot go nemame zborceto synchronized
    }

}
