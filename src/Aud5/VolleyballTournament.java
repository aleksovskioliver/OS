package Aud5;

import java.util.HashSet;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolleyballTournament {
    public static void main(String[] args) throws InterruptedException {
        HashSet<Player> threads = new HashSet<>();
        for (int i=0;i<60;i++){
            Player p = new Player();
            threads.add(p);
        }
        for (Player player : threads){
            player.start();
        }
        for (Player player : threads){
            player.join(3000);
        }
        for (Player player : threads){
            if (player.isAlive()){
                player.interrupt();
                System.out.println("Possible deadlock.");
            }
        }
        System.out.println("Tournament finished.");
    }
}
class Increment{
    public static int count = 0;
    public static Semaphore lock = new Semaphore(1);

    public static void increment() throws InterruptedException {
        lock.acquire();
        count++;
        lock.release();
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Increment.count = count;
    }
}
class Player extends Thread{

    public static Semaphore sala = new Semaphore(12);
    public static Semaphore kabina = new Semaphore(4);
    public static Semaphore natprevar = new Semaphore(1);

    public static Increment readyPlayers = new Increment();
    public static Increment donePlayers = new Increment();

    public void execute() throws InterruptedException {
        sala.acquire();  //bara dozvola za vlez vo sala(max 12)
        System.out.println("Player inside");

        kabina.acquire(); //barame dozvola za vlez vo kabina(max 4)
        System.out.println("In dressing room");
        Thread.sleep(10);
        kabina.release();
        readyPlayers.increment();   //spremni +1;

        if (readyPlayers.getCount() == 12){
            readyPlayers.setCount(0);   //vekje nemame spremni igraci
            System.out.println("Game started.");
            natprevar.acquire();  //zapocnal natprevarot
        }
        Thread.sleep(100);
        System.out.println("Player done.");
        donePlayers.increment();

        if (donePlayers.getCount() == 12) {
            donePlayers.setCount(0); //vekje nemame zavrsheni igraci odnosno site se izlezeni
            System.out.println("Game finished.");
            natprevar.release();  //zavrshil
            sala.release(12);
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