/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymongodb.project1;

import static com.mymongodb.project1.WordToExcel.outputDirecotryPath;
import static com.mymongodb.project1.WordToExcel.outputFileName;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

//import org.apache.poi.hwpf.HWPFTestDataSamples;
public class WordToExcelTest {

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\hemonth.mandava.SSTECH\\Desktop\\test.docx");
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
            xdoc.removeProtectionEnforcement();
            //iterate each table cell in word document
//            xdoc.getTablesIterator()
//                    .forEachRemaining(i -> i.getRows()
//                    .forEach(s -> s.getTableCells()
//                    .forEach(l -> {
//                        System.out.print(l.getText());
//                    })));
//................................................................................
// get all paragraphs from word document
//           List<XWPFParagraph> paragraphList = xdoc.getParagraphs();
//       for (XWPFParagraph paragraph: paragraphList){
//	   System.out.println(paragraph.getText());
//       }
//................................................................................
            //iterate each table row in word document
            FileOutputStream fileOutputStream = null;
            String outputDirecotryPath = "C:\\Users\\hemonth.mandava.SSTECH\\Desktop\\";
            String outputFileName = "reportfile.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            String sheetName = "Sheet1";
            workbook.createSheet(sheetName);
            List<XWPFTableRow> rows = new ArrayList<>();
            List<StringBuilder> rowText = new ArrayList<>();
// iterating over each row and saving all the row cells into one string
            xdoc.getTablesIterator()
                    .forEachRemaining(i -> i.getRows().forEach(r -> rows.add(r)));
            for (XWPFTableRow row : rows) {
                StringBuilder text = new StringBuilder();
                System.out.println();
                row.getTableCells().forEach(c -> {
                    text.append(c.getText());                    
                });
                rowText.add(text);
            }
            for(StringBuilder sb: rowText){
                System.out.println(sb);
                if (sb.toString().contains("Account Name")) {
                        // Create Data Row
                        Row dataRow = workbook.getSheet(sheetName).createRow(0);
                        dataRow.createCell(0).setCellValue("Account Name");
                        dataRow.createCell(1).setCellValue(sb.toString().replace("Account Name", ""));
                    }
                    if (sb.toString().contains("CSPC ID")) {
                        // Create Data Row
                        Row dataRow = workbook.getSheet(sheetName).createRow(1);
                        dataRow.createCell(0).setCellValue("CSPC ID");
                        dataRow.createCell(1).setCellValue(sb.toString().replace("CSPC ID", ""));
                    }
                    if (sb.toString().contains("Part Number")) {
                        // Create Data Row
                        Row dataRow = workbook.getSheet(sheetName).createRow(2);
                        dataRow.createCell(0).setCellValue("Part Number");
                        dataRow.createCell(1).setCellValue(sb.toString().replace("Part Number", ""));
                    }
                    if (sb.toString().contains("Hardware Serial Number")) {
                        // Create Data Row
                        Row dataRow = workbook.getSheet(sheetName).createRow(3);
                        dataRow.createCell(0).setCellValue("Hardware Serial Number");
                        dataRow.createCell(1).setCellValue(sb.toString().replace("Hardware Serial Number", ""));
                    }
            }
            // Automatically adjust the columns as per the cell content for the last file
		workbook.getSheet(sheetName).autoSizeColumn(0);
		workbook.getSheet(sheetName).autoSizeColumn(1);
		
		// Write Last Excel File 
		fileOutputStream = new FileOutputStream(new File(outputDirecotryPath + outputFileName));
		workbook.write(fileOutputStream);
		fileOutputStream.close();
            //............................................................................
//            xdoc.getTables().forEach(t -> System.out.println(t.getNumberOfRows()));
//            xdoc.getTables().forEach( i -> {
//                if(i.getText().contains(outputFileName)){
//                    
//                }
//                    });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
