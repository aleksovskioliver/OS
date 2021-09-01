package Aud5;
import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class SiO2 {

    public static int NUM_RUN = 50;
    public static Semaphore Si;
    public static Semaphore O;
    public static Semaphore SiHere;
    public static Semaphore OHere;
    public static Semaphore ready;

    public static void init() {
        Si = new Semaphore(1);
        O = new Semaphore(2);
        SiHere = new Semaphore(0);
        OHere = new Semaphore(0);
        ready = new Semaphore(0);
    }

    public static class Si extends Thread {

        public void bond() {
            System.out.println("Si is bonding now.");
        }

        @Override
        public void run() {
            for (int i=0;i<NUM_RUN;i++) {
                try {
                    execute();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void execute() throws InterruptedException {
            Si.acquire();
            SiHere.release(2);
            OHere.acquire(2);
            ready.release(2);
            bond();
            Si.release();
        }

    }

    public static class O extends Thread {

        public void execute() throws InterruptedException {
            O.acquire(1);
            SiHere.acquire();
            OHere.release();
            ready.acquire();
            bond();
            O.release();

        }

        public void bond() {
            System.out.println("O is bonding now.");
        }


        @Override
        public void run() {
            for (int i=0;i<NUM_RUN;i++) {
                try {
                    execute();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//
//    public static void main(String[] args) {
//        HashSet<Thread> threads = new HashSet<>();
//
//    }
}
