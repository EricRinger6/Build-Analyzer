import java.util.Scanner;

public class Console {

    public static void println(String prompt){
        System.out.println(prompt);
    }

    public static void print(String prompt){
        System.out.print(prompt);
    }

    public static String getString(String prompt){
        Scanner scanner = new Scanner(System.in);
        println(prompt);
        return scanner.next();
    }
}
