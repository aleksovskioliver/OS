package Aud3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingMutexQueue<T> {

    List<T> products = new ArrayList<>();
    int capacity;
    Lock lock = new ReentrantLock();

    public BlockingMutexQueue(int capacity){
        this.capacity = capacity;
    }

    public void enqueue(T item) throws InterruptedException {
        while (true) {
            lock.lock();
            if (products.size() < capacity) {
                products.add(item);
                lock.unlock();
                break;
            }
            lock.unlock();
        }
    }
    public T dequeue() throws InterruptedException {
        T item;
        while (true) {
            lock.lock();
            if (products.size() > 0) {
                item = products.remove(products.size() - 1);
                lock.unlock();
                break;
            }
            lock.unlock();
        }
        return item;
    }

}