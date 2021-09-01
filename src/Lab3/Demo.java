package Lab3;

import java.util.Random;
import java.util.concurrent.Semaphore;
public class Demo {

    public static void main(String args[]) throws InterruptedException {
        DiningPhilosophers.runTest();
    }
}

class DiningPhilosophers {

    private static Random random = new Random(System.currentTimeMillis());
    private Semaphore[] forks = new Semaphore[6];
    private Semaphore mutex = new Semaphore(1);

    public DiningPhilosophers() {
        forks[0] = new Semaphore(1);
        forks[1] = new Semaphore(1);
        forks[2] = new Semaphore(1);
        forks[3] = new Semaphore(1);
        forks[4] = new Semaphore(1);
        forks[5] = new Semaphore(1);

    }

    public void lifecycleOfPhilosopher(int id) throws InterruptedException {

        while (true) {
            think();
            eat(id);
        }
    }

    void think() throws InterruptedException {
        Thread.sleep(random.nextInt(50));
    }

    void eat(int id) throws InterruptedException {
        mutex.acquire();
        forks[id].acquire();
        if (forks[(id+1)%5].tryAcquire()){

            System.out.println("Ruchka: " + id);

            forks[(id+1)%5].release();      //ja ostava desnata
            forks[id].release();        //ja ostava i levata
        }else {
            Thread.sleep(100); //ne moze da gi zeme dvete viluski zatoa prodolzuva da misli
            forks[id].release();     //da ne se sluchi deadlock,dokolku ne ja zeme desnata
        }
        mutex.release();

//        synchronized (DiningPhilosophers.class){
//            forks[id].acquire();
//            if (forks[(id+1)%5].tryAcquire()) {
//                System.out.println("Rucka " + id);
//                forks[id].release();
//                forks[(id+1)%5].release();
//            }
//            forks[id].release();
//        }


    }

    static void runPhilosopher(DiningPhilosophers dp, int id) {
        try {
            dp.lifecycleOfPhilosopher(id);
        } catch (InterruptedException ie) {

        }
    }

    public static void runTest() throws InterruptedException {
        final DiningPhilosophers dp = new DiningPhilosophers();

        Thread p1 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 0);
            }
        });

        Thread p2 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 1);
            }
        });

        Thread p3 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 2);
            }
        });

        Thread p4 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 3);
            }
        });

        Thread p5 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 4);
            }
        });

        Thread p6 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 5);
            }
        });

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();

        p1.join();
        p2.join();
        p3.join();
        p4.join();
        p5.join();
        p6.join();

    }
}
