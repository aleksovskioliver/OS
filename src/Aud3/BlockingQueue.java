package Aud3;

import java.util.ArrayList;
import java.util.List;

public class BlockingQueue<T> {

    List<T> products = new ArrayList<>();
    int capacity;

    public BlockingQueue(int capacity){
        this.capacity = capacity;
    }

    public void enqueue(T item) throws InterruptedException {
        while (products.size() == capacity){
            wait();
        }
        products.add(item);
        notifyAll();
    }

    public T dequeue() throws InterruptedException {
        T item;
        while (products.size() == 0){
            wait();
        }
        item = products.remove(products.size()-1);
        notifyAll();
        return item;
    }
}
