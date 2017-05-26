/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mymongodb.project1;
//import com.sun.rowset.internal.Row;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Hemonth.Mandava
 */
public class WordToExcel {

	// Input
	public static final String inputDirectoryPath = "/Users/vvelegap/Desktop/1batch/";
	
	
	// Output
	public static final String outputDirecotryPath = "/Users/vvelegap/Desktop/7600Audit/";
	public static final String outputFileName = "Version.xls";	

	public static void main(String[] args) throws IOException {
		
		BufferedReader buffReader = null;
		FileOutputStream fileOutputStream = null;
		
		HSSFWorkbook workbook = null;
		String sheetName = "Sheet6";

		try {
			File file = new File(inputDirectoryPath);
			if (file.isDirectory()) {
				workbook = new HSSFWorkbook();
				workbook.createSheet(sheetName);
				int rowNo = 0;		

				// Create Header Row
				Row row = workbook.getSheet(sheetName).createRow(rowNo);
				row.createCell(0).setCellValue("Devices");
				row.createCell(1).setCellValue("Version");
				
				
				rowNo++;
				
				for (String subDirectory : file.list()) {
					
					File subFile=new File(inputDirectoryPath+subDirectory);
					if (subFile.isDirectory()) {
						for (String fileName:subFile.list()){
							if (fileName.startsWith("sh_version")){
								System.out.println("FileName:" + subFile);
								
								String line = null;
								String version = null;
																
								buffReader = new BufferedReader(new FileReader(inputDirectoryPath + subDirectory + "//" + fileName));	
								while ((line = buffReader.readLine()) != null) {
									if (line.startsWith("Cisco IOS Software")) {
										String[] splitStrArr = line.trim().split(",");
//										version = splitStrArr[2].trim();
										String[] secondsplitStrArr = splitStrArr[2].split("\\)");
										version = secondsplitStrArr[1].trim();
										System.out.println(version);
										
										
									}
								}

								// Create Data Row
								Row dataRow = workbook.getSheet(sheetName).createRow(rowNo);
								dataRow.createCell(0).setCellValue(subDirectory);
								dataRow.createCell(1).setCellValue(version);

								rowNo++;
								
								break;
							}
						}
					}
					
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		
		
		// Automatically adjust the columns as per the cell content for the last file
		workbook.getSheet(sheetName).autoSizeColumn(0);
		workbook.getSheet(sheetName).autoSizeColumn(1);
		
		// Write Last Excel File 
		fileOutputStream = new FileOutputStream(new File(outputDirecotryPath + outputFileName));
		workbook.write(fileOutputStream);
		fileOutputStream.close();
	}

}
