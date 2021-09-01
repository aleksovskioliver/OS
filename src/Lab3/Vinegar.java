package Lab3;
import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class Vinegar {
    public static Semaphore C = new Semaphore(2);
    public static Semaphore H = new Semaphore(4);
    public static Semaphore O = new Semaphore(2);
    public static Semaphore CHere = new Semaphore(0);
    public static Semaphore HHere = new Semaphore(0);
    public static Semaphore OHere = new Semaphore(0);
    public static Semaphore ready = new Semaphore(0);
    public static Semaphore done = new Semaphore(0);
    public static Semaphore leave = new Semaphore(0);
    public static Semaphore lock = new Semaphore(1);
    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        HashSet<Thread> threads = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            threads.add(new C());
            threads.add(new H());
            threads.add(new H());
            threads.add(new O());
        }
        for (Thread t: threads){
            t.start();
        }
        for (Thread t: threads){
            t.join(2000);
        }
        for (Thread t: threads){
            if (t.isAlive()){
                t.interrupt();
                System.out.println("Possible deadlock!");
            }
        }
        System.out.println("Process finished.");

    }

    static class C extends Thread {

        public void execute() throws InterruptedException {
            C.acquire();
            System.out.println("C here.");
            lock.acquire();
            count++;
            if (count == 2){   //kodot na coordinatorot C
                count=0;
                lock.release();
                CHere.acquire();
                HHere.acquire(4);
                OHere.acquire(2);
                ready.release(7);
                System.out.println("Molecule bonding.");
                Thread.sleep(100);// this represent the bonding process
                System.out.println("C done.");
                done.acquire(7);
                leave.release(7);
                System.out.println("Molecule created.");
                C.release();
            }else {   //kodot na C1
                lock.release();
                CHere.release();
                ready.acquire();
                System.out.println("Molecule bonding.");
                Thread.sleep(100);// this represent the bonding process
                System.out.println("C done.");
                done.release();
                leave.acquire();
                C.release();
            }
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

    static class H extends Thread {

        public void execute() throws InterruptedException {
            H.acquire();
            System.out.println("H here.");
            HHere.release();
            ready.acquire();
            System.out.println("Molecule bonding.");
            Thread.sleep(100);// this represent the bonding process
            System.out.println("H done.");
            done.release();
            leave.acquire();
            H.release();
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

    static class O extends Thread {

        public void execute() throws InterruptedException {
            O.acquire();
            System.out.println("O here.");
            OHere.release();
            ready.acquire();
            System.out.println("Molecule bonding.");
            Thread.sleep(100);// this represent the bonding process
            System.out.println("O done.");
            done.release();
            leave.acquire();
            O.release();
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


}