package Aud4;

import java.util.ArrayList;
import java.util.List;

public class SyncLibrary {

    List<String > books = new ArrayList<>();
    int capacity;

    public SyncLibrary(int capacity){
        this.capacity = capacity;
    }

    //vrati kniga vo bibliotekata
    public synchronized void returnBook(String book) throws InterruptedException {
        if (books.size() == capacity)
            wait();
        books.add(book);
        notifyAll();
    }

    //pozajmi
    public synchronized String borrowBook() throws InterruptedException {
        String book="";
        if (books.size()<=0){
            wait();
        }
        book = books.remove(books.size()-1);
        notifyAll();
        return book;
    }
}
