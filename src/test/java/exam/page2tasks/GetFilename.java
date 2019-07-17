package exam.page2tasks;

import org.testng.Assert;

//Дана строка, содержащая полное имя файла (например, 'c:\WebServers\home\testsite\www\myfile.txt').
// Выделите из этой строки имя файла без расширения.
public class GetFilename {
    public static void main(String[] args) {

        String filePath = "c:\\WebServers\\home\\testsite\\www\\myfile.txt";
        String fileNameWithoutExtension = getFilenameWithoutExt(filePath);

        System.out.printf("Имя файла без расширения: %s", fileNameWithoutExtension);

        Assert.assertEquals(fileNameWithoutExtension, "myfile");
    }

    public static String getFilenameWithoutExt(String filePath) {
        return filePath.substring(filePath.lastIndexOf('\\') + 1, filePath.lastIndexOf('.'));
    }
}
