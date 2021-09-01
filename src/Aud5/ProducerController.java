package Aud5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ProducerController {

    public static int NUM_RUN = 50;
    static Semaphore sharedMemory;
    static Semaphore controllerChecks;
    static Semaphore lock;
    static int numChecks = 0;

    public static void init() {
        sharedMemory = new Semaphore(1);
        controllerChecks = new Semaphore(10);
        lock = new Semaphore(1);
    }

    public static class Buffer {

        public void produce() {
            System.out.println("Producer is producing...");
        }

        public void check() {
            System.out.println("Controller is checking...");
        }
    }

    public static class Producer extends Thread {
        private final Buffer buffer;

        public Producer(Buffer b) {
            this.buffer = b;
        }

        public void execute() throws InterruptedException {
            sharedMemory.acquire();  //barame pristap do memorija
            lock.acquire();
            if (numChecks == 0) {  //odnosno ako nemame momentalno proverka vo bufffer
                this.buffer.produce();  //mozeme da dodademe proizvod vo nego
            }
            sharedMemory.release();
            lock.release();
            //controllerChecks.release();  // ->budime controller dokolku ceka da proveri
        }

        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Controller extends Thread {

        private final Buffer buffer;

        public Controller(Buffer buffer) {
            this.buffer = buffer;
        }

        public void execute() throws InterruptedException {
            lock.acquire();
            if (numChecks == 0){    // nema povekje proverki,odnosno da se izvrsheni site za da moze da iame produce
                sharedMemory.acquire();
            }
            numChecks++;
            lock.release();

            controllerChecks.acquire();
            this.buffer.check();
            lock.acquire();
            numChecks--;
            controllerChecks.release();

            if (numChecks==0)
                sharedMemory.release();   //za da moze produce da dobie dozvola
            lock.release();















        }

        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Producer p = new Producer(buffer);
        List<Controller> controllers = new ArrayList<>();
        init();
        for (int i = 0; i < 10; i++) {
            controllers.add(new Controller(buffer));
        }
        p.start();
        for (int i = 0; i < 10; i++) {
            controllers.get(i).start();
        }

    }

}
