package Frontend.Input;

import java.util.Scanner;

public class MainScannerSingleton {
    private static MainScannerSingleton instance;
    private Scanner scanner;
    private MainScannerSingleton() {
        scanner = new Scanner(System.in);
    }

    public static MainScannerSingleton getInstance() {
        if(instance == null) {
            instance = new MainScannerSingleton();
        }
        return instance;
    }

    public String nextInput() {
        return scanner.next();
    }
}
