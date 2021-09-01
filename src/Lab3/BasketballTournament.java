package Lab3;
import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class BasketballTournament {

    public static void main(String[] args) throws InterruptedException {
        HashSet<Player> threads = new HashSet<>();
        for (int i = 0; i < 60; i++) {
            Player p = new Player();
            threads.add(p);
        }
        for (Player player : threads){
            player.start();
        }
        for (Player player : threads){
            player.join(5000);
        }
        for (Player player : threads) {
            if (player.isAlive()) {
                player.interrupt();
                System.out.println("Possible deadlock!");
            }
        }
        System.out.println("Tournament finished.");

    }
}
class Increment{
  public static int counter = 0;
  public static Semaphore lock = new Semaphore(1);

  public static void increment() throws InterruptedException {
      lock.acquire();
      counter++;
      lock.release();
  }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Increment.counter = counter;
    }
}

class Player extends Thread{
    public static Semaphore sala = new Semaphore(20);
    public static Semaphore kabina = new Semaphore(10);
    public static Semaphore natprevar = new Semaphore(1);

    public static Increment readyPlayers = new Increment();
    public static Increment donePlayerts = new Increment();

    public void execute() throws InterruptedException {
        sala.acquire();  //bara pristap za vlez vo sala(moze max 20)
        System.out.println("Player inside.");

        kabina.acquire();  //bara pristap za vlez vo kabina(moze max 10)
        System.out.println("In dressing room.");
        Thread.sleep(10);// this represent the dressing time
        kabina.release();  //ja osloboduva kabinata
        readyPlayers.increment();

        if (readyPlayers.getCounter() == 20){
            readyPlayers.setCounter(0);
            natprevar.acquire();  //natprevarot zapocnal
            System.out.println("Game started.");
        }
        Thread.sleep(100);// this represent the game duration
        System.out.println("Player done.");
        donePlayerts.increment();  //broj na zavrsheni

        if (donePlayerts.getCounter() == 20){
            donePlayerts.setCounter(0);

            System.out.println("Game finished.");
            natprevar.release();
            sala.release(20);
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