package call_report_driver;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class FileSystem {
    //класс для работы с вводом/выводом через файлы
    CDR_Reader cdrReader;
    ReportDriver reportDriver;
    FileSystem() {
        cdrReader = new CDR_Reader();
        reportDriver = new ReportDriver();
    }
    public void readInputFile(String fileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        BufferedInputStream inputStream = new BufferedInputStream(fileInputStream, 52);

        int i;
        String newCdrRecord = "";
        while ((i=inputStream.read() )!=-1) {
            if((char)i != '\n'){
                newCdrRecord+=(char)i;
            } else {
                cdrReader.processCdrRecord(newCdrRecord);
                newCdrRecord = "";
            }
        }
        inputStream.close();
        fileInputStream.close();
    }

    public void createReports(String directoryName) throws IOException{
        String reportText;

        for (Map.Entry<String, Subscriber> entry: Main.subscribersBase.entrySet()) {
            FileOutputStream fileOutputStream = new FileOutputStream(directoryName + "\\" + entry.getKey() + "report.txt");
            reportText =  reportDriver.doMagic(entry.getValue()); //TODO: написать логику создания нового отчета
            fileOutputStream.write(reportText.getBytes());

            fileOutputStream.close();
        }
    }
}
