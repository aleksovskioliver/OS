package Aud4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class LibraryMembership {

    static Semaphore shalter = new Semaphore(10);
    static Semaphore davaDokumentiByChlen = new Semaphore(1);
    static Semaphore prezemiDokumentiByBibliotekar = new Semaphore(1);
    static Semaphore zapishan = new Semaphore(1);


    public static void main(String[] args) throws InterruptedException {
        List<Clen> clenovi = new ArrayList<>();
        List<Bibliotekar> bibliotekari = new ArrayList<>();

        for (int i=0;i<5;i++){
            clenovi.add(new Clen("C"+i));
            bibliotekari.add(new Bibliotekar("B"+i));
        }
        for (int i=0;i<5;i++){
            clenovi.get(i).start();
//            bibliotekari.get(i).start();
        }
        for (int i=0;i<5;i++){
            bibliotekari.get(i).start();
        }
        for (Clen clen : clenovi){
            clen.join();
        }
        for (Bibliotekar bibliotekar : bibliotekari){
            bibliotekar.join();
        }

        System.out.println("Site studenti se zapishani");
    }

    static class Bibliotekar extends Thread{
        String name;

        public Bibliotekar(String name){
            this.name = name;
        }

        public void prezemiDokumenti() throws InterruptedException {
            shalter.acquire();   //kje go zakluci shalterot za 1 2 3 4...10 koi cekaat da gi predadat dokumentite

            davaDokumentiByChlen.release();
            prezemiDokumentiByBibliotekar.acquire();
            System.out.println("Bibliotekarot : " + name + "gi prevzema dokumentite");
            zapishan.release();

            shalter.release();
        }

        @Override
        public void run() {
            try {
                prezemiDokumenti();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Clen extends Thread {

        String name;

        public Clen(String name){
            this.name = name;
        }
        public void predadiDokumenti() throws InterruptedException {

            davaDokumentiByChlen.acquire();
            System.out.println("Chlenot: " + name + " gi predade dokumentite");
            prezemiDokumentiByBibliotekar.release();
            zapishan.acquire();

        }

        @Override
        public void run() {
            try {
                predadiDokumenti();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
