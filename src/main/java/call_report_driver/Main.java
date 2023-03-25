package call_report_driver;

import java.io.*;
import java.util.HashMap;

public class Main {
    public static HashMap<String, Subscriber> subscribersBase = new HashMap<>();
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        try {
            fileSystem.readInputFile("cdr.txt");

        fileSystem.createReports("reports");
        } catch (IOException e) {
            System.out.println("File reading error. Check files path.");
        }
    }
}
