/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymongodb.project1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

/**
 *
 * @author Hemonth.Mandava
 */
public class ProcessLargeCSVFile {

    public static void main(String[] args) throws FileNotFoundException, InvalidFormatException, IOException {
        Map<String, String> keyValues = new HashMap<>();
        List<String> urlWithPaths = new ArrayList<>();
        Path pathToFile = Paths.get("C:\\Users\\hemonth.mandava.SSTECH\\Desktop\\url.csv");
        // create an instance of BufferedReader 
        // using try with resource, Java 7 feature to close resources 
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            // read the first line from the text file 
            String line = br.readLine();
            // loop until all lines are read 
            while (line != null) {
                String[] attributes = line.split("/");
                line = br.readLine();
                urlWithPaths.add(attributes[2] + "/" + attributes[3]);
                if (!keyValues.containsKey(attributes[2])) {
                    keyValues.put(attributes[2], attributes[3]);
                } else {
                    keyValues.computeIfPresent(attributes[2], (k, v) -> v.concat(";").concat(attributes[3]));
                }
            }
        }
        //saving processed data into csv file. 
        File file = new File("C:\\Users\\hemonth.mandava.SSTECH\\Desktop\\outputurls.csv");
        FileWriter fileWriter = new FileWriter(file);
        try (ICsvListWriter listWriter = new CsvListWriter(fileWriter, CsvPreference.STANDARD_PREFERENCE)) {
//            for (Map.Entry<String, String> entry : keyValues.entrySet()) {
//                listWriter.write(entry.getKey(), entry.getValue());
//            }
            for (String s : urlWithPaths) {
                String[] v = s.split("/");
                listWriter.write(v[0], v[1]);
            }
        }

    }
}
