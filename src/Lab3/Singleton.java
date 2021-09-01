package Lab3;

import Aud5.SiO2;

import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class Singleton {

    private static volatile Singleton singleton;
    static Semaphore instance = new Semaphore(1);
    static int counter = 0;
    private Singleton() {

    }

    public static Singleton getInstance() {
        // TODO: 3/29/20 Synchronize this
        if (instance.tryAcquire()) {
            singleton = new Singleton();
            synchronized (Singleton.class){
                counter++;
            }
        }
        return singleton;
    }

    public static void main(String[] args) throws InterruptedException {
        // TODO: 3/29/20 Simulate the scenario when multiple threads call the method getInstance
        HashSet<Thread> threads = new HashSet<>();

        for (int i=0;i<50;i++){
            Thread t = new Thread(Singleton::getInstance);
            threads.add(t);
        }
        for (Thread thread : threads){
            thread.start();
        }
        for (Thread thread : threads){
            thread.join(2000);
        }
        if (counter == 1){
            System.out.println("Samo edna e kreirana");
        }else {
            System.out.println("Neuspeshna sinhronizacija");
        }
    }

}