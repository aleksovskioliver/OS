package Aud5;
import java.util.Objects;
import java.util.concurrent.Semaphore;

public class FinkiToilet {

    public static Semaphore sharedMemory;
    public static Semaphore Lmazhi;
    public static Semaphore Lzheni;

    public static int brMazhi=0;
    public static int brZheni=0;


    public static class Toilet {

        public void vlezi() {
            System.out.println("Vleguva...");
        }

        public void izlezi() {
            System.out.println("Izleguva...");
        }
    }


    public static void init() {
        sharedMemory = new Semaphore(1);
        Lmazhi = new Semaphore(0);
        Lzheni = new Semaphore(0);
    }

    public static class Man extends Thread{

        private Toilet toilet;

        public Man(Toilet toilet) {
            this.toilet = toilet;
        }

        public void enter() throws InterruptedException {
            Lmazhi.acquire();   //bara pristap
            if (brMazhi==0) {   //ako brojot  e 0
                sharedMemory.acquire();  //se bara pristap do toiletot
            }
            brMazhi++;  //se zgolemuva brojot na mazi vnatre
            toilet.vlezi();  //vleguva vo toilet
            Lmazhi.release();    //se osloboduva pristapot
        }

        public void exit() throws InterruptedException {
            Lmazhi.acquire();   //lock za da ne dojde do zabuna vo brMazi
            toilet.izlezi();   //izleguvame
            brMazhi--;  //fo namaluvame brojot
            if (brMazhi == 0){  //ako brojot na mazi vnatre e pomal od 0
                sharedMemory.release();   //dozvoli vlez na zheni
            }
            Lmazhi.release();
        }

        @Override
        public void run() {
            super.run();
        }
    }

    public static class Woman extends Thread{

        private Toilet toilet;

        public Woman(Toilet toilet) {
            this.toilet = toilet;
        }

        public void enter() throws InterruptedException {
            Lzheni.acquire(); //bara pristap
            if (brZheni == 0)
                sharedMemory.acquire();   //cekame koga kje mozeme da dobieme pristap za vlez vo toilet
            brZheni++;
            toilet.vlezi();
            Lzheni.release();
        }

        public void exit() throws InterruptedException {
            Lzheni.acquire();
            toilet.izlezi();
            brZheni--;
            if (brZheni == 0){
                sharedMemory.release();   //oslobodi go toletot
            }
            Lzheni.release();
        }


        @Override
        public void run() {
            super.run();
        }
    }
}