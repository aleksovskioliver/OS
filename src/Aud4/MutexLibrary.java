package Aud4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexLibrary {
    List<String > books = new ArrayList<>();
    int capacity;
    Lock lock = new ReentrantLock();

    public MutexLibrary(int capacity){
        this.capacity = capacity;
    }

    //vrati kniga vo bibliotekata
    public void returnBook(String book) throws InterruptedException {
       while (true) {
           lock.lock();
           if (books.size() < capacity) {
               books.add(book);
               lock.unlock();
               break;
           }
           lock.unlock();
       }
    }

    //pozajmi
    public String borrowBook() throws InterruptedException {
        String book = "";
        while (true) {
            lock.lock();
            if (books.size() > 0) {
                book = books.remove(0);
                lock.unlock();
                break;
            }
            lock.unlock();
        }
        return book;
    }
}
