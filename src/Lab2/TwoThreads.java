package Lab2;

import java.util.ArrayList;
import java.util.List;

public class TwoThreads {

//    public static void main(String[] args) throws InterruptedException {
//        ThreadClassLetters letters = new ThreadClassLetters();
//        ThreadClassNumbers numbers = new ThreadClassNumbers();
//        numbers.start();
//        numbers.join();
//        letters.start();
//        letters.join();
//
//    }
    public static void main(String[] args) throws InterruptedException {

        Thread threadNumbers = new Thread(new ThreadClassNumbersLetters(1,2,3,4,5));
        Thread threadLetters = new Thread(new ThreadClassNumbersLetters("A","B","C","D","E"));

        threadNumbers.start();
        threadNumbers.join();
        threadLetters.start();
        threadLetters.join();
    }
}
class ThreadClassNumbersLetters<T> implements Runnable{
    T[] list;
    public ThreadClassNumbersLetters(T... list){
        this.list = list;
    }


    @Override
    public void run() {
        for (T element : list){
            System.out.println(element);
        }
    }
}

//class ThreadClassNumbers extends Thread {
//
//        @Override
//        public void run() {
//            for (int i = 0; i < 5; i++) System.out.println(i);
//        }
//}
//
//
//    class ThreadClassLetters extends Thread {
//
//        @Override
//        public void run() {
//            for (int i = 0; i < 5; i++) System.out.println((char) (i + 65));
//        }
//    }
//    class ThreadClassNumbers extends Thread {
//
//        @Override
//        public void run() {
//            for (int i = 0; i < 10; i++) System.out.println(i);
//        }
//    }
//
//
//    class ThreadClassLetters extends Thread {
//
//        @Override
//        public void run() {
//            for (int i = 0; i < 10; i++) System.out.println((char) (i + 65));
//        }
//    }
