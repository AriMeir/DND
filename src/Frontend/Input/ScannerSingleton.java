package Frontend.Input;
import java.util.Scanner;

public class ScannerSingleton {
    private static ScannerSingleton instance;
    private Scanner scanner;

    private ScannerSingleton() {
        scanner = new Scanner(System.in);
    }

    public static ScannerSingleton getInstance() {
        if(instance == null) {
            instance = new ScannerSingleton();
        }
        return instance;
    }

    public int nextInput() {
        scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}

