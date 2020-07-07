package com.prokarma.qa.web.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	
		Workbook wb;
	
	//it will load all the Excel Sheet
		public ExcelFileUtil(String FilePath) throws Throwable
		{
//			FileInputStream fis=new FileInputStream("C:\\Users\\Venkatesh\\eclipse-workspace\\EMS1\\TestData\\InputSheet.xlsx");
//			FileInputStream fis=new FileInputStream("C:\\Users\\Venkatesh\\eclipse-workspace\\EMS1\\TestOutput\\OutPutSheet.xlsx");
			FileInputStream fis=new FileInputStream(FilePath);
			
			if(FilePath.contains("Spend & Consumption") || FilePath.contains("SpendAndConsumption_YOY_Report")) {
				wb=new XSSFWorkbook(fis);
			}else {
				wb=new HSSFWorkbook(fis);
			}
			
		}
		
		public int getSheetIndex(String FilePath,String sheetname) throws Exception {
			FileInputStream inputStream = new FileInputStream(new File(FilePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            int index=workbook.getSheetIndex(sheetname);
            workbook.close();
            return index;
		}
		
		public void deleteSheet(String FilePath,String sheetname) throws IOException {
//			System.out.println(wb);
//			int index=wb.getSheetIndex(sheetname);
//			System.out.println(index);
////			wb.removeName(index);
////			wb.removeSheetAt(index);
//			wb.removeName(sheetname);
////			FileOutputStream outputStream = new FileOutputStream("JavaBooks.xls");
////            wb.write(outputStream);
////            wb.close();
////            outputStream.close();
			
//			String FilePath="C:\\Users\\Venkatesh\\Desktop\\EMS\\TestOutput\\OutPutSheet.xls";
		    try {
	            FileInputStream inputStream = new FileInputStream(new File(FilePath));
	            Workbook workbook = WorkbookFactory.create(inputStream);
	            int index=workbook.getSheetIndex(sheetname);
	            workbook.removeSheetAt(index);         
	 
	            FileOutputStream outputStream = new FileOutputStream(FilePath);
	            workbook.write(outputStream);
	            workbook.close();
	            outputStream.close();
	             
	        } catch (IOException | EncryptedDocumentException
	                | InvalidFormatException ex) {
	            ex.printStackTrace();
	        }
			
			
		}
		
		public void createSheet(String FilePath,String sheetname) {
			
			 try {
		            FileInputStream inputStream = new FileInputStream(new File(FilePath));
		            Workbook workbook = WorkbookFactory.create(inputStream);
		            
		            int sheetIndex= workbook.getSheetIndex(sheetname);
		            workbook.createSheet(sheetname);
		            		 
		            FileOutputStream outputStream = new FileOutputStream(FilePath);
		            workbook.write(outputStream);
		            workbook.close();
		            outputStream.close();
		             
		        } catch (IOException | EncryptedDocumentException
		                | InvalidFormatException ex) {
		            ex.printStackTrace();
		        }
			 
		}
		
		public int rowCount(String sheetname)
		{
			return wb.getSheet(sheetname).getLastRowNum();
		}
		
		public int colCount(String sheetname,int row)
		{
			return wb.getSheet(sheetname).getRow(row).getLastCellNum();
		}

		public String getData(String sheetname,int row ,int column)
		{
			String data="";
			
			try {
			
				if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
				{
	//				int celldata=(int)(wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue());
					data=NumberToTextConverter.toText(wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue());
	//				int celldata=(int)(wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue());
	//				data=String.valueOf(celldata);
					
				}else if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_STRING)
				{
					data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
				}
			}catch(Exception e) {
				
			}
			
			return data;
		}

		//Store data into excel sheet Pass Or Fail and Not Executed
		public void setData(String sheetname,int row, int column,String Status) throws Throwable
		{	
		//	System.out.println(sheetname+" "+row+" "+column+" "+Status);
			
			
			FileInputStream fis =new FileInputStream(System.getProperty("user.dir") + File.separator + "TestOutput"+ File.separator +"OutPutSheet.xls");
			
			Workbook workbook = new HSSFWorkbook(fis);
			
			Sheet sheet =workbook.getSheet(sheetname);
						
//			try {
//				sheet = workbook.getSheet(sheetname);
//				System.out.println("sheet is getSheet "+sheet);
//			}catch(NullPointerException e){
//				sheet =workbook.createSheet(sheetname);
//				System.out.println("sheet is createSheet "+sheet);
//			}
			
		    Row rownum;
		    
		    try {
			        rownum = sheet.getRow(row);
			        Cell cell = rownum.createCell(column);
					cell.setCellValue(Status);
		    }catch(Exception e) {
		    		rownum = sheet.createRow(row);
		    		Cell cell = rownum.createCell(column);
			        cell.setCellValue(Status);
		    }
			
		    FileOutputStream fos =new FileOutputStream(System.getProperty("user.dir") + File.separator + "TestOutput"+ File.separator +"OutPutSheet.xls");
			workbook.write(fos);
			fos.close();
			
		}
		
		public int getColIndex(String sheetname,String colName)
		{	
			int colCount=wb.getSheet(sheetname).getRow(0).getLastCellNum();
			int req_col = -1;
			for(int i=0;i<colCount;i++) {
				
				String colHeader=wb.getSheet(sheetname).getRow(0).getCell(i).getStringCellValue();
				if(colHeader.trim().equalsIgnoreCase(colName.trim())) {
					req_col=i;
					break;
				}
			}
			return req_col;
		}
		
		public String getTestInput(String sheetname,String testCaseName,String colName) throws Throwable {
			
//			ExcelFileUtil exlTestInput=new ExcelFileUtil(sheetname);
			int rowCount=rowCount(sheetname);
			
			int reqColIndex=getColIndex(sheetname,colName);
			String testData=null;
			
			for(int i=0;i<=rowCount;i++) {
				if(getData(sheetname, i, 0).equalsIgnoreCase(testCaseName)){
					testData=getData(sheetname, i, reqColIndex);
					break;
				}
			}
			
			return testData;	
		}
		
		//Store data into excel sheet Pass Or Fail and Not Executed
		public void setDataInOther(String dirPath,String sheetname,int row, int column,String Status) throws Throwable
		{	
		//	System.out.println(sheetname+" "+row+" "+column+" "+Status);
			
			
			FileInputStream fis =new FileInputStream(dirPath);
			
			Workbook workbook = new XSSFWorkbook(fis);
			
			Sheet sheet =workbook.getSheet(sheetname);
						
//			try {
//				sheet = workbook.getSheet(sheetname);
//				System.out.println("sheet is getSheet "+sheet);
//			}catch(NullPointerException e){
//				sheet =workbook.createSheet(sheetname);
//				System.out.println("sheet is createSheet "+sheet);
//			}
			
		    Row rownum;
		    
		    try {
			        rownum = sheet.getRow(row);
			        Cell cell = rownum.createCell(column);
					cell.setCellValue(Status);
		    }catch(Exception e) {
		    		rownum = sheet.createRow(row);
		    		Cell cell = rownum.createCell(column);
			        cell.setCellValue(Status);
		    }
			
		    FileOutputStream fos =new FileOutputStream(dirPath);
			workbook.write(fos);
			fos.close();
			
		}


}
