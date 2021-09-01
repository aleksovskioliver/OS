package Lab2;


public class Example{


    public static void main(String[] args) throws InterruptedException {
        ThreadClass thread = new ThreadClass();
        thread.start();
        thread.join(10);        //dokolku nema join kje isprinta 0 bidejki brzo se izvrshuva i ne ceka da zavrshat site threads.run
        System.out.println(thread.getCount());
        System.out.println("END OF PROGRAM");
    }
}

class ThreadClass extends Thread{
    static int count = 0;
    public void increment() {
        count++;
    }
    public int getCount(){
        return count;
    }

    @Override
    public  void run() {
        for(int i = 0; i < 50; i++)
            increment();
    }
}
