package Aud4;

import java.util.ArrayList;
import java.util.List;

public class LibraryDemo {
    public static void main(String[] args) throws InterruptedException {
        List<Member> members = new ArrayList<>();
        SemaphoreLibrary sharedLibrary = new SemaphoreLibrary(10);
        for (int i=0;i<10;i++){
            Member member = new Member("M"+1,sharedLibrary);
            members.add(member);
        }
        for (Member member:members){
            member.start();
        }
        for (Member member : members){
            member.join(3000);
        }
        System.out.println("END OF PROGRAM!");
    }
}

class Member extends Thread{
    String name;
    SemaphoreLibrary library;

    public Member(String name, SemaphoreLibrary library){
        this.name = name;
        this.library = library;
    }

    @Override
    public void run() {
        for (int i=0;i<3;i++){
            System.out.println("Member "+ i + "return book");
            try {
                library.returnBook("Book" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i=0;i<2;i++){
            System.out.println("Member " + i + "borrow book");
            try {
                library.borrowBook();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
