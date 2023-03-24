package call_report_driver;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileSystem {
    //класс для работы с вводом/выводом через файлы
    public static void readInputFile(String fileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        BufferedInputStream inputStream = new BufferedInputStream(fileInputStream, 52);

        int i;
        String newCdrRecord = "";
        while ((i=inputStream.read() )!=-1) {
            if((char)i != '\n'){
                newCdrRecord+=(char)i;
            } else {
                System.out.println(newCdrRecord);
                //обработка полученной строки
                newCdrRecord = "";
            }
        }
        inputStream.close();
        fileInputStream.close();
    }

    public static void newReport(String directoryName) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(directoryName);
        //создание нового отчета
        fileOutputStream.write("new report".getBytes());
        fileOutputStream.close();
    }
}
