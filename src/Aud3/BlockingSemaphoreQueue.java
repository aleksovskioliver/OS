package Aud3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingSemaphoreQueue<T> {

    List<T> products = new ArrayList<>();
    int capacity;
    Semaphore producerSemaphore = new Semaphore(100);
    Semaphore consumerSemaphore = new Semaphore(100);
    Semaphore coordinator = new Semaphore(1);


    public BlockingSemaphoreQueue(int capacity) {
        this.capacity = capacity;
    }

    public void enqueue(T item) throws InterruptedException {

        producerSemaphore.acquire();
        coordinator.acquire();
        if (products.size() == capacity) {
            coordinator.release();          //ne moze da stavash,cekaj prvo nekoj da izvadi
            Thread.sleep(1000);        //prostorotot(coordinator/shared memory) neka mu se dozvoli na consumerot da izavde item
            coordinator.acquire();
        }
        products.add(item);
        coordinator.release();
        consumerSemaphore.release();    //osloboduvame nekoj consumer
    }

    public T dequeue() throws InterruptedException {
        T item;
        consumerSemaphore.acquire();
        coordinator.acquire();
        if (products.size() == 0) {
            coordinator.release();        //ako nema produkti vo listata,go osloboduvame prostorot za da moze producerot da stavi proizvod
            Thread.sleep(1000);
            coordinator.acquire();
        }
        item = products.remove(products.size() - 1);
        coordinator.release();
        producerSemaphore.release();    //sme izvadile proizvod, sega moze producerot da stavi proizvod ako cheka
        return item;
    }
}
