package com.trello.ui.core.credentialsstorage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteTextFileLines {
    private String filePath;
    private boolean append = false;

    public ReadWriteTextFileLines(String filePath) {
        this.filePath = filePath;
    }

    public ReadWriteTextFileLines(String filePath, boolean append) {
        this.filePath = filePath;
        this.append = append;
    }

    public void writeToFile(String textLine) throws IOException {
        FileWriter writer = new FileWriter(filePath, append);
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(textLine);
        printWriter.close();
    }

    public List<String> readTextFileLines() throws IOException {
        List<String> lines = new ArrayList<>();
        File file = new File(filePath);

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            //System.out.println(st);
            lines.add(line);
        }
        return lines;
    }
}
