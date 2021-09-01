package Aud4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreLibrary {
    List<String > books = new ArrayList<>();
    int capacity;
    static Semaphore returnSemaphore = new Semaphore(10);
    static Semaphore borrowSemaphore = new Semaphore(10);
    static Semaphore coordinator = new Semaphore(1);

    public SemaphoreLibrary(int capacity){
        this.capacity = capacity;
    }

    //vrati kniga vo bibliotekata
    public void returnBook(String book) throws InterruptedException {
        returnSemaphore.acquire();  //se zaklucuva m1, m2...m10
        coordinator.acquire();      //zkalucuvame kriticen domen

        while (books.size()==capacity){
            coordinator.release();
            Thread.sleep(1000);
            coordinator.acquire();
        }
        books.add(book);
        coordinator.release();
        borrowSemaphore.release();
    }

    //pozajmi
    public String borrowBook() throws InterruptedException {
        borrowSemaphore.acquire();
        coordinator.acquire();
        String book = "";
        while (books.size() <= 0) {
            coordinator.release();      //se otklucuva kriticniot domen za da moze da dojde nekoj i da stave kniga vo megjuvreme
            Thread.sleep(1000);
            coordinator.acquire();
        }
        book = books.remove(0);
        coordinator.release();
        returnSemaphore.release(); //mu davame dozvola(go otklucuvame) bidejki imame izavedeno kniga i ima mesto nekoj drug da vrati kniga.
        return book;
    }
}
