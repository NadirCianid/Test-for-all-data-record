package call_report_driver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static HashMap<String, Subscriber> subscribersBase = new HashMap<>();
    public static void main(String[] args) throws IOException {

        //TODO: закончить метод
        FileSystem fileSystem = new FileSystem();
        fileSystem.readInputFile("cdrTest.txt");

        fileSystem.createReports("reports");
    }

}
