package call_report_driver;

import java.io.*;
import java.util.Map;

public class FileSystem {
    //класс для работы с вводом/выводом через файлы
    CDR_Reader cdrReader;
    FileReader fileInputStream;
    BufferedReader inputStream;
    FileOutputStream fileOutputStream;

    FileSystem() {
        cdrReader = new CDR_Reader();
    }
    public void readInputFile(String fileName) throws IOException {
        fileInputStream = new FileReader(fileName);
        inputStream = new BufferedReader(fileInputStream);

        String newCdrRecord = inputStream.readLine();
        while (newCdrRecord!=null) {
            cdrReader.processCdrRecord(newCdrRecord);
            newCdrRecord = inputStream.readLine();
        }
        inputStream.close();
        fileInputStream.close();
    }

    public void createReports(String directoryName) throws IOException{
        String reportText;

        for (Map.Entry<String, Subscriber> entry: Main.subscribersBase.entrySet()) {
            fileOutputStream = new FileOutputStream(directoryName + "\\" + entry.getKey() + "report.txt");
            reportText = entry.getValue().toString();
            fileOutputStream.write(reportText.getBytes());

            fileOutputStream.close();
        }
    }
}
