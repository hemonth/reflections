/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymongodb.project1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 *
 * @author Hemonth.Mandava
 */
public class ReadWordDocumentsUsingJavaNIO {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        //Root directory
        Path path = Paths.get("C:\\Users\\hemonth.mandava.SSTECH\\Desktop\\status reports");
        //Files.list returns list of sub paths
        Files.list(path).forEach(i -> {
            try {
                List<String> searchWords = Arrays.asList("HEMONTH","Working","Worked","MANDAVA");
                Map<String,Integer> wordsMap = new HashMap<>();
                FileInputStream fis = new FileInputStream(i.normalize().toString());
                XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
                //count no of occurrences
                xdoc.getPart().getParagraphs().stream().flatMap(p -> Stream.of(p.getText().split(" "))).filter(w -> searchWords.contains(w)).forEach(fw -> {
                    if(!wordsMap.containsKey(fw)){
                        wordsMap.put(fw, 1);
                    }
                    else wordsMap.computeIfPresent(fw, (k,v) -> v++);
                });
                System.out.println("Total occurrences of words: "+wordsMap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvalidFormatException ex) {
                Logger.getLogger(ReadWordDocumentsUsingJavaNIO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ReadWordDocumentsUsingJavaNIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

}

