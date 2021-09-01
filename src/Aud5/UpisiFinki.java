package Aud5;
import java.util.concurrent.Semaphore;

public class UpisiFinki {

    public static Semaphore komisija;
    public static Semaphore canEntry;
    public static Semaphore studentHere;
    public static Semaphore done;


    public static void init() {
        komisija = new Semaphore(4);
        canEntry = new Semaphore(0);
        studentHere = new Semaphore(0);
        done = new Semaphore(0);
    }

    public static class Clen extends Thread{
        public void init() {
        }

        public void execute() throws InterruptedException {
            komisija.acquire();  //bara dozvola za vlez vo prostorijata
            int i=10;  //moze da zapishe 10studenti
            while (i>0){
                canEntry.release();  //davam dozvola za vlez
                studentHere.acquire();  //studentot e ovde
                zapisi();       //zapishuvam
                done.release(); //zavrshil so zapishuvame,moze da zamine
                i--;
            }
            komisija.release();
        }

        public void zapisi() {
            System.out.println("Zapisuvam student...");
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

    public static class Student extends Thread{

        public void execute() throws InterruptedException {
            canEntry.acquire();  //bara dozvola za vlez
            studentHere.release(); //vleguva
            ostaviDokumenti();
            done.acquire();  //so ova sum zavrshil
        }

        public void ostaviDokumenti() {
            System.out.println("Ostavam dokumenti...");
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
