package call_report_driver;

import java.io.*;
import java.util.Map;

public class FileSystem {
    //класс для работы с вводом/выводом через файлы
    CDR_Reader cdrReader;

    FileSystem() {
        cdrReader = new CDR_Reader();
    }
    public void readInputFile(String fileName) throws IOException {
        FileReader fileInputStream = new FileReader(fileName);
        BufferedReader inputStream = new BufferedReader(fileInputStream);

        int i;
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
            FileOutputStream fileOutputStream = new FileOutputStream(directoryName + "\\" + entry.getKey() + "report.txt");
            reportText = entry.getValue().toString();
            fileOutputStream.write(reportText.getBytes());

            fileOutputStream.close();
        }
    }
}
