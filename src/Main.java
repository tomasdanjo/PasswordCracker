import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Timer;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        int len = pass.length();
        String atk = "a".repeat(len);

        char letter = 'a';

        Thread[] threads = new Thread[26];

        //create threads
        for(int i=0;i<26;i++,letter++){
            threads[i] = new Thread(new CrackerRunnable(letter,pass));
//            System.out.println(letter);
        }


        //run threads;
        for(int i=0;i<26;i++){
            threads[i].start();
        }

        Long start = System.currentTimeMillis();
        for(int i=0;i<26;i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Long end = System.currentTimeMillis();
        System.out.println("Found after "+(end-start)+" Ms");

    }
}