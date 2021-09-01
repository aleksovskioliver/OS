package Aud3;

import java.util.ArrayList;
import java.util.List;

public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingSemaphoreQueue<String > blockingQueue = new BlockingSemaphoreQueue<>(10);
        List<Producers> producers = new ArrayList<>();
        List<Consumer> consumers = new ArrayList<>();

        for(int i=0;i<10;i++){
            Consumer consumer = new Consumer("Consumer "+i, blockingQueue);
            Producers producers1 = new Producers("Producer "+i,blockingQueue);
            producers.add(producers1);
            consumers.add(consumer);
        }

        for (int i=0;i<10;i++){
            consumers.get(i).start();
        }
        for (int i=0;i<10;i++){
            producers.get(i).start();
        }
        for (int i=0;i<10;i++){
            consumers.get(i).join(3000);
        }
        for (int i=0;i<10;i++){
            producers.get(i).join(3000);
        }

        System.out.println("Successfully execute. ");
    }

}

class Producers extends Thread{
    String name;
    BlockingSemaphoreQueue<String> sharedMemory;

    public Producers(String name, BlockingSemaphoreQueue sharedMemory){
        this.name = name;
        this.sharedMemory = sharedMemory;
    }

    @Override
    public void run() {
        for (int i=1;i<=5;i++){
            System.out.println("Producer is making product no."+i);
            try {
                sharedMemory.enqueue("Producer: " + name + " is making a product no." + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Consumer extends Thread{
    String name;
    BlockingSemaphoreQueue<String> sharedMemory;

    public Consumer(String name,BlockingSemaphoreQueue sharedMemory){
        this.name = name;
        this.sharedMemory = sharedMemory;
    }

    @Override
    public void run() {

        for (int i=1;i<=5;i++){
            String item = "";
            System.out.println("Conusumer: " + i + " is buying.");
            try {
                item = sharedMemory.dequeue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Consumer " + name + "is buying the item " + item);
        }
    }
}
