package org.example;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class ClientLog {
    private List<Integer> list = new ArrayList<>();

    public void log(int productNum, int amount) {
        list.add(productNum + 1);
        list.add(amount);
    }

    public void exportAsCSV(File txtFile) throws IOException {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(txtFile, true))) {
            int size = list.size();
            int first = list.get(size - 2);
            int second = list.get(size - 1);
            String log = String.valueOf(first + "," + second);
            try {
                FileReader myFile = new FileReader(txtFile);
                BufferedReader InputFile = new BufferedReader(myFile);
                if (txtFile.exists() && InputFile.readLine() == null) {
                    String title = "productNum" + "," + "amount";
                    csvWriter.writeNext(title.split(","));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            csvWriter.writeNext(log.split(","));
        }
    }
}