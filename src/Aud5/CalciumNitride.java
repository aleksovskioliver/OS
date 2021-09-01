//package Aud5;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Scanner;
//import java.util.concurrent.Semaphore;
//
//public class CalciumNitride {
//    public static int numRuns = 10;
//    public static Semaphore Ca;
//    public static Semaphore N;
//    public static Semaphore CaHere;
//    public static Semaphore NHere;
//    public static Semaphore ready;
//    public static Semaphore done;
//    public static Semaphore leave;
//    public static int countCa = 0;
//    public static Semaphore lock;
//
//
//    public static void init() {
//        Ca = new Semaphore(3);
//        N = new Semaphore(2);
//        CaHere = new Semaphore(0);
//        NHere = new Semaphore(0);
//        ready = new Semaphore(0);
//        done = new Semaphore(0);
//        leave = new Semaphore(0);
//        lock = new Semaphore(1);
//
//    }
//
//    public static class Calcium extends Thread {
//
////        public Calcium(int numRuns) {
////            super(numRuns);
////        }
//
//        public void execute() throws InterruptedException {
//            Ca.acquire();
//            lock.acquire();
//            countCa++;
//            if (countCa == 3){  //ako se imaat zbereno 3,znaci sme kaj koordinatorot i mozeme da ja grademe molekulata
//                countCa=0;
//                lock.release();
//                CaHere.acquire(2);
//                NHere.acquire(2);
//                ready.release(4);
//                state.bond();
//                done.acquire(4);
//                leave.release(4);
//                state.validate();
//                Ca.release();
//            }
//            else {   //se naogjame kaj Ca2
//               lock.release();
//               CaHere.release();
//               ready.acquire();
//               state.bond();
//               done.release();
//               leave.acquire();
//               Ca.release();
//            }
//        }
//
//        @Override
//        public void run() {
//            try {
//                execute();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static class Nitrogen extends Thread {
//
//        public Nitrogen(int numRuns) {
//            super(numRuns);
//        }
//
//        @Override
//        public void run() {
//            try {
//                execute();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void execute() throws InterruptedException {
//            N.acquire();
//            NHere.release();
//            ready.acquire();
//            state.bond();
//            done.release();
//            leave.acquire();
//            N.release();
//        }
//
//    }
//
//  //  static CalciumNitrideState state = new CalciumNitrideState();
//
//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            run();
//        }
//    }
//
//
//    public static void run() {
//        try {
//            Scanner s = new Scanner(System.in);
//            int numRuns = 1;
//            int numIterations = 100;
//            s.close();
//
//            HashSet<Thread> threads = new HashSet<Thread>();
//
//            for (int i = 0; i < numIterations; i++) {
//                Nitrogen n = new Nitrogen(numRuns);
//                threads.add(n);
//                Calcium ca = new Calcium(numRuns);
//                threads.add(ca);
//                ca = new Calcium(numRuns);
//                threads.add(ca);
//                n = new Nitrogen(numRuns);
//                threads.add(n);
//                ca = new Calcium(numRuns);
//                threads.add(ca);
//            }
//
//            init();
//
//            ProblemExecution.start(threads, state);
//            System.out.println(new Date().getTime());
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//}