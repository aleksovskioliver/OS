package Lab2;

class Exercise {

    public static void main(String args[]) throws InterruptedException {




    }
}

class ThreadingExample {

    static public void example() throws InterruptedException {

        final Thread t1 = new Thread(new Runnable() {

            public void run() {
                try {
                    System.out.println("Block thread for an hour");
                    Thread.sleep(1000 * 60 * 60);
                } catch (InterruptedException ie) {
                    System.out.println("Thread is interrupted : " + Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    System.out.println("The thread is up");

                }
            }
        });

        t1.start();
        t1.interrupt();
        t1.join();

    }
}
