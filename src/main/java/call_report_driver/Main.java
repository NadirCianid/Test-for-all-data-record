package call_report_driver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) throws IOException {
        FileSystem.readInputFile("cdr.txt");

        FileSystem.newReport("reports\\output.txt");
    }
}
