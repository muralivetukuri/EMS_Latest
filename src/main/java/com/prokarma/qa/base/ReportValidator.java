package com.prokarma.qa.base;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.prokarma.qa.web.helpers.ExcelFileUtil;
import com.prokarma.qa.web.helpers.Log;

public class ReportValidator extends CommonFunctions{
	
//	public File getLatestFilefromDir(String dirPath) throws Exception{
	public String getLatestFilefromDir(String dirPath) throws Exception{
		
		Thread.sleep(60000);
		
	    File dir = new File(dirPath);
	    
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        return null;
	    }
	
	    File lastModifiedFile = files[0];
	    for (int i = 1; i < files.length; i++) {
	       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	           lastModifiedFile = files[i];
	       }
	    }
	    
//	    return lastModifiedFile;
	    String modFileName=lastModifiedFile.getName();  
	    return modFileName;
	}
	
	public void validateSpendConsumptionReport(String sheetToValidate) throws Throwable {
		
		Log.info("Report Validation Started");
		
		Thread.sleep(10000);
		String testoutput=System.getProperty("user.dir")+File.separator+"TestOutput";
//		File modFile=getLatestFilefromDir(testoutput);
//		String modFileName=modFile.getName(); 
		
		
//		String modFileName=getLatestFilefromDir(testoutput);
//		String reqFileName="";
//		boolean reportValidator=true;
//		
//		if(modFileName.contains("Spend & Consumption")){
//      	  reqFileName=modFileName;
//        }
		
		String reqFileName="";
		boolean reportValidator=true;
		
		while(reqFileName=="") {
		
			String modFileName=getLatestFilefromDir(testoutput);
			
			if(modFileName.contains("Spend & Consumption")){
	      	  reqFileName=modFileName;
	        }
			
		}	
		
//		System.out.println("reqFileName : "+reqFileName);
		
//		  File dir = new File("TestOutput");
//		  String reqFileName="";
//	      String[] children = dir.list();
//	      
//	      if (children == null) {
//	         System.out.println("does not exist or  is not a directory");
//	      } else {
//	         for (int i = 0; i < children.length; i++) {
//	            String filename = children[i];
//		          if(filename.contains("Spend & Consumption")){
//		        	  reqFileName=filename;
//		          }
//	         }
//	      }
	      
	      Thread.sleep(10000);
	      
		  String outPutFilePath=System.getProperty("user.dir")+File.separator+"TestOutput"+File.separator+"OutPutSheet.xls";	
		  ExcelFileUtil outputExl=new ExcelFileUtil(outPutFilePath);
		 
			  ArrayList<String> column_list=new ArrayList<String>();
				  column_list.add("Site #>Site #");
				  column_list.add("Utility Type>Commodity");
				  column_list.add("Billing Month>Month");
				  column_list.add("Billing Year>Year");
				  column_list.add("Spend>Spend");
				  column_list.add("Consumption>Consumption");
//				  column_list.add("Demand>Demand");
				 
			  HashMap<String,String> month_map  = new HashMap<>(); 
				  month_map.put("Jan","1"); 
				  month_map.put("Feb","2"); 
				  month_map.put("Mar","3"); 
				  month_map.put("Apr","4");
				  month_map.put("May","5");		
				  month_map.put("Jun","6");
				  month_map.put("Jul","7");
				  month_map.put("Aug","8"); 
				  month_map.put("Sep","9"); 
				  month_map.put("Oct","10"); 
				  month_map.put("Nov","11");
				  month_map.put("Dec","12");		
	 
				  String downloadedReportFilePath=System.getProperty("user.dir")+File.separator+"TestOutput"+File.separator+reqFileName;	
				  ExcelFileUtil reportExl=new ExcelFileUtil(downloadedReportFilePath);
				   
				  String site_header_str=column_list.get(0);
				  
				  String[] headerlist=site_header_str.split(">");
				  
				  String output_header=headerlist[0];
				  int req_output_colindex=outputExl.getColIndex(sheetToValidate, output_header);
				  
				  String report_header=headerlist[1];
				  int req_report_colindex=reportExl.getColIndex("Data", report_header);	 
				 
				  int output_rowCount= outputExl.rowCount(sheetToValidate);
				  int report_rowCount= reportExl.rowCount("Data");
				  
				  for(int i=1;i<=output_rowCount;i++) {
					  
					  ArrayList<Integer> matching_row_list=new ArrayList<Integer>();
			
					  String output_primary_key= outputExl.getData(sheetToValidate, i, req_output_colindex);
					  
					  for(int j=1;j<=report_rowCount;j++) {
						  String report_primary_key= reportExl.getData("Data", j, req_report_colindex); 
						  if(output_primary_key.equalsIgnoreCase(report_primary_key)) {
							  matching_row_list.add(j);
						  }  
					  }
					  
					 for(int matching_row:matching_row_list) {
//						 System.out.println(output_primary_key+" "+matching_row);
						 
						 boolean row_status=true;
						 
						 for(String header:column_list) {
							 
							 boolean cellrow_status=true;
							 
							 String[] headers=header.split(">");
							 
							 String output_header_str=headers[0];
							 
//							 System.out.println(output_header_str);
							 int output_header_str_colindex=outputExl.getColIndex(sheetToValidate, output_header_str);
							 String output_header_data=outputExl.getData(sheetToValidate, i, output_header_str_colindex);
							 output_header_data= output_header_data.replace(",", "");
							 
							 String report_header_data="";
							 if(output_header_str.equalsIgnoreCase("Billing Month")) {
								 String report_header_str=headers[1];
								 int report_header_str_colindex=reportExl.getColIndex("Data", report_header_str);
								 report_header_data=reportExl.getData("Data", matching_row, report_header_str_colindex);
								 report_header_data=month_map.get(report_header_data);		 
							 }else  if(output_header_str.equalsIgnoreCase("Spend")) {
								 String report_header_str=headers[1];
								 int report_header_str_colindex=reportExl.getColIndex("Data", report_header_str);
								 report_header_data=reportExl.getData("Data", matching_row, report_header_str_colindex);
								 report_header_data=report_header_data.replace(",", "");
								 
//								 float report_header_data_float= Float.parseFloat(report_header_data);
//								 report_header_data_float=report_header_data_float+0.01f;						 
//								 report_header_data=String.valueOf(report_header_data_float);  
								 
								 report_header_data= "$"+report_header_data;
								 int dot_index=report_header_data.indexOf('.');
								 report_header_data=report_header_data.substring(0, dot_index+3);
								 
							 }else {
								 String report_header_str=headers[1];
								 int report_header_str_colindex=reportExl.getColIndex("Data", report_header_str);
								 report_header_data=reportExl.getData("Data", matching_row, report_header_str_colindex);
							 }
							
							 if(output_header_str.equalsIgnoreCase("Spend")) {
								 
//								 System.out.println("Spend "+output_header_data+" <> "+report_header_data);
								 
								 output_header_data=output_header_data.replace("$", "");
								 report_header_data=report_header_data.replace("$", "");
								 
								 float output_header_data_float = Float.parseFloat(output_header_data);
								 float report_header_data_float = Float.parseFloat(report_header_data);
								 
								 float diff1=output_header_data_float-report_header_data_float;
								 float diff2=report_header_data_float-output_header_data_float;
								 
//								 System.out.println("diff1 : "+diff1);
//								 System.out.println("diff2 : "+diff2);
								 
								 String diff1_str1=String.valueOf(diff1); 
								 String diff2_str2=String.valueOf(diff2); 
								 
//								 if(diff1==0.0 || diff2==0.0 || diff1_str1.contains("0.01") || diff2_str2.contains("0.01") || diff1_str1.contains("0.00") || diff2_str2.contains("0.00")) {
								 if(diff1==0.0 || diff2==0.0 || diff1<1 || diff2<1) {
								 	cellrow_status=true;
//									 System.out.println("Spend matching "+output_header_data+" "+report_header_data);
								 }else {
//									 System.out.println(output_header_data+" "+report_header_data);
//									 System.out.println("Spend not matching "+output_header_data+" "+report_header_data);
									 cellrow_status=false;
									 row_status=false;
									 break;
								 } 
							 }
							 else if(output_header_str.equalsIgnoreCase("Consumption")) {
								  
//								 System.out.println("Consumption : "+output_header_data+" <> "+report_header_data);								 
//								 output_header_data=output_header_data.replace("$", "");
//								 report_header_data=report_header_data.replace("$", "");
								 
								 float output_header_data_float = Float.parseFloat(output_header_data);
								 float report_header_data_float = Float.parseFloat(report_header_data);
								 
								 float diff1=output_header_data_float-report_header_data_float;
								 float diff2=report_header_data_float-output_header_data_float;
								 
//								 System.out.println("diff1 : "+diff1);
//								 System.out.println("diff2 : "+diff2);
								 
								 String diff1_str1=String.valueOf(diff1); 
								 String diff2_str2=String.valueOf(diff2); 
								 
//								 System.out.println(output_header_data+" <> "+report_header_data);
//								 System.out.println(diff1+" <> "+diff1);
//								 System.out.println(diff2_str2+" <> "+diff2_str2);
//								 System.out.println(diff1_str1);
//								 System.out.println(diff2_str2);
								 
//								 if(diff1==0.0 || diff2==0.0 || diff1_str1.contains("0.01") || diff2_str2.contains("0.01") || diff1_str1.contains("0.00") || diff2_str2.contains("0.00") || diff1_str1.contains("-0.00") || diff2_str2.contains("-0.00") || diff1_str1.contains("-0.01") || diff2_str2.contains("-0.01") || diff1_str1.contains("-0.001") || diff2_str2.contains("-0.001")) {
								 if(diff1==0.0 || diff2==0.0 || diff1<1 || diff2<1) {
								 	 cellrow_status=true;
//									 System.out.println("Consumption matching "+output_header_data+" "+report_header_data);
								 }else {
//									 System.out.println("Consumption not matching "+output_header_data+" "+report_header_data);
									 cellrow_status=false;
									 row_status=false;
									 break;
								 } 
							 }
							 
							 if(!output_header_str.equalsIgnoreCase("Spend") && !output_header_str.equalsIgnoreCase("Consumption")) {						 
								 if(output_header_data.equalsIgnoreCase(report_header_data)) {
//									 System.out.println(" matching "+output_header_data+" "+report_header_data);
									 cellrow_status=true;
								 }else {
//									 System.out.println(" not matching "+output_header_data+" "+report_header_data);
									 cellrow_status=false;
									 row_status=false;
									 break;
								 } 						 
							 } 
						 } 
						  
						 if(row_status==true) {
							 int row_statusColIndex=outputExl.getColIndex(sheetToValidate, "Status");	 
							 outputExl.setData(sheetToValidate, i, row_statusColIndex, "Found-Pass");	
							 break;
						 }else {
							 int row_statusColIndex=outputExl.getColIndex(sheetToValidate, "Status");
							 outputExl.setData(sheetToValidate, i, row_statusColIndex, "Not Found-Fail");
						 } 
					 }    	  
				  }
				  
				    int row_status_colindex=outputExl.getColIndex(sheetToValidate, "Status");
				      
				    for(int startrow=1;startrow<=output_rowCount;startrow++)
				    {
				    	String foundrow_status=outputExl.getData(sheetToValidate, startrow, row_status_colindex);
				    	
				    	if(foundrow_status.equalsIgnoreCase("Not Found-Fail")) {
				    		reportValidator=false;
				    		break;
				    	}
				    	
				    }
				    
				    if(reportValidator==true) {
				  		Log.info("Report Validation Pass");
						write("PASS", "Report Validation Pass");
				  	}else {
				  		Log.info("Report Validation Fail");
						write("FAIL", "Report Validation Fail");
				  	}
				  	
   }
	
	public void validateSpendConsumptionYOYReport(String sheetToValidate) throws Throwable {
			
			Log.info("Report Validation Started");
			
			Thread.sleep(10000);
			String testoutput=System.getProperty("user.dir")+File.separator+"TestOutput";
			
			String reqFileName="";
			
			while(reqFileName=="") {
			
				String modFileName=getLatestFilefromDir(testoutput);
				
				if(modFileName.contains("SpendAndConsumption_YOY_Report")){
		      	  reqFileName=modFileName;
		        }
				
			}	
		      
		      Thread.sleep(10000);
		      
			  String outPutFilePath=System.getProperty("user.dir")+File.separator+"TestOutput"+File.separator+"OutPutSheet.xls";	
			  ExcelFileUtil outputExl=new ExcelFileUtil(outPutFilePath);
			 
				  ArrayList<String> column_list=new ArrayList<String>();
					  column_list.add("Site #>Site #");
					  column_list.add("Client Name>Client Name");
					  column_list.add("Vendor Name>Vendor Name");
					  column_list.add("Utility Type>Utility Type");
					  column_list.add("Billing Month>Billing Month");
					  column_list.add("Billing Year>Billing Year");
					  column_list.add("Spend>Spend");
					  column_list.add("Consumption>Consumption");
					  column_list.add("Demand>Demand");	
		 
					  String downloadedReportFilePath=System.getProperty("user.dir")+File.separator+"TestOutput"+File.separator+reqFileName;	
					  ExcelFileUtil reportExl=new ExcelFileUtil(downloadedReportFilePath);
					   
					  String site_header_str=column_list.get(0);
					  
					  String[] headerlist=site_header_str.split(">");
					  
					  String output_header=headerlist[0];
					  int req_output_colindex=outputExl.getColIndex(sheetToValidate, output_header);
					  
					  String report_header=headerlist[1];
					  int req_report_colindex=reportExl.getColIndex("Data", report_header);	 
					 
					  int output_rowCount= outputExl.rowCount(sheetToValidate);
					  int report_rowCount= reportExl.rowCount("Data");
					  
					  for(int i=1;i<=output_rowCount;i++) {
						  
						  ArrayList<Integer> matching_row_list=new ArrayList<Integer>();
				
						  String output_primary_key= outputExl.getData(sheetToValidate, i, req_output_colindex);
						  
						  for(int j=1;j<=report_rowCount;j++) {
							  String report_primary_key= reportExl.getData("Data", j, req_report_colindex); 
							  if(output_primary_key.equalsIgnoreCase(report_primary_key)) {
								  matching_row_list.add(j);
							  }  
						  }
						  
						  
						  if(matching_row_list.isEmpty()) {
							  int row_statusColIndex=outputExl.getColIndex(sheetToValidate, "row_status");
							  outputExl.setData(sheetToValidate, i, row_statusColIndex, "Not Found-Fail");
						  }
						   
						 String matching_row_status=""; 
						 
						 for(int matching_row:matching_row_list) {
							 
							 matching_row_status=""; 
							 
							 String cell_status=""; 
							 
							 for(String header:column_list) {
								 
								 cell_status="";
								 
								 String[] headers=header.split(">");
								 
								 String output_header_str=headers[0];
								 int output_header_str_colindex=outputExl.getColIndex(sheetToValidate, output_header_str);
								 String output_header_data=outputExl.getData(sheetToValidate, i, output_header_str_colindex);
								 output_header_data= output_header_data.replace(",", "");
								 output_header_data= output_header_data.replace("$", "");
								 
								 String report_header_data="";
								 
								 String report_header_str=headers[1];
								 int report_header_str_colindex=reportExl.getColIndex("Data", report_header_str);
								 report_header_data=reportExl.getData("Data", matching_row, report_header_str_colindex);
								 report_header_data=report_header_data.replace(",", "");
								 report_header_data= report_header_data.replace("$", "");
								 
								if(output_header_str.equalsIgnoreCase("Spend") || output_header_str.equalsIgnoreCase("Consumption") || output_header_str.equalsIgnoreCase("Demand")) {
											
									 float output_header_data_float = Float.parseFloat(output_header_data);
									 float report_header_data_float = Float.parseFloat(report_header_data);
									 
									 float diff1=output_header_data_float-report_header_data_float;
									 float diff2=report_header_data_float-output_header_data_float;
									 
									 if(diff1<1 && diff2<1) {
//											 System.out.println(" yes "+diff1+" <> "+diff2+" "+output_header_str+" matching "+output_header_data+" "+report_header_data);
									  }else {
											 cell_status="false";
//											 System.out.println(" no "+diff1+" <> "+diff2+" "+output_header_str+" not matching "+output_header_data+" "+report_header_data);
											 int row_statusColIndex=outputExl.getColIndex(sheetToValidate, "Status");
											 outputExl.setData(sheetToValidate, i, row_statusColIndex, "Not Found-Fail");
											 
											 break;
									  } 
									 
								 }
							     else { 
							    	 
							    	 if(output_header_data.equals(report_header_data)) {
//							    		 System.out.println(output_header_str+" matching "+output_header_data+" "+report_header_data);
							    	 }else {
//							    		 System.out.println(output_header_str+" not matching "+output_header_data+" "+report_header_data);
							    		 cell_status="false"; 
										 int row_statusColIndex=outputExl.getColIndex(sheetToValidate, "Status");
										 outputExl.setData(sheetToValidate, i, row_statusColIndex, "Not Found-Fail");
										 break;
							    	 } 	 
							    }			
							 } 
							 
							 if(cell_status=="") {
								 matching_row_status="true";
								 break;
						 	 }
							 
						 }  
						 
						 		if(matching_row_status=="true") {
						 			 int row_statusColIndex=outputExl.getColIndex(sheetToValidate, "Status");
									 outputExl.setData(sheetToValidate, i, row_statusColIndex, "Found-Pass");
						 		}
						 		
					  }
					  
					  boolean reportValidator=true;
					  
					  output_rowCount= outputExl.rowCount(sheetToValidate);
							  
					  for(int i=1;i<=output_rowCount;i++) {
						  int row_statusColIndex=outputExl.getColIndex(sheetToValidate, "Status");
						  String status=outputExl.getData(sheetToValidate, i, row_statusColIndex);
						  
						  if(status.equalsIgnoreCase("Not Found-Fail")) {
							  reportValidator=false;
							  break;
						  }  
					  }			
					  
					  if(reportValidator==false) {
						  Log.info("Report Validation Fail");
						  write("FAIL", "Report validation Fail");
					  }else if(reportValidator==true){
						  Log.info("Report Validation Pass");
						  write("PASS", "Report Validation Pass");	
					  }  
	   }	
	
	
public void validateSpendConsumptionReportRandomRecords() throws Throwable {
	
	Log.info("Report Validation Started");
	
	Thread.sleep(10000);
	String testoutput=System.getProperty("user.dir")+File.separator+"TestOutput";
	
	String downloaded_SpendConsumptionYOY_ReportFilePath=getFilePath(testoutput,"SpendAndConsumption_YOY_Report");	
	ExcelFileUtil exlObj_spendConsumptionYOY=new ExcelFileUtil(downloaded_SpendConsumptionYOY_ReportFilePath);
	
	String downloaded_SpendConsumption_ReportFilePath=getFilePath(testoutput,"Spend & Consumption");	
	ExcelFileUtil exlObj_spendConsumption=new ExcelFileUtil(downloaded_SpendConsumption_ReportFilePath);
	
	int rowCount_YOY=exlObj_spendConsumptionYOY.rowCount("Data");
	
	int recordsvalidaterange=rowCount_YOY/4;
		
	Random rand = new Random(); 
	List<Integer> list=new ArrayList<Integer>();   

	int irow=1;
	while(irow<=recordsvalidaterange) {
	int rand_int1 = rand.nextInt(rowCount_YOY); 

	if(!list.contains(rand_int1) && rand_int1!=0) 
		list.add(rand_int1);
		irow++;
	}
	
	Collections.sort(list);  
	boolean reportValidator=true;
	 
	ArrayList<String> column_list=new ArrayList<String>();
	  column_list.add("Site #>Site #");
	  column_list.add("Utility Type>Commodity");
	  column_list.add("Billing Month>Month");
	  column_list.add("Billing Year>Year");
	  column_list.add("Spend>Spend");
	  column_list.add("Consumption>Consumption");
	  
	  HashMap<String,String> month_map  = new HashMap<>(); 
	  month_map.put("Jan","1"); 
	  month_map.put("Feb","2"); 
	  month_map.put("Mar","3"); 
	  month_map.put("Apr","4");
	  month_map.put("May","5");		
	  month_map.put("Jun","6");
	  month_map.put("Jul","7");
	  month_map.put("Aug","8"); 
	  month_map.put("Sep","9"); 
	  month_map.put("Oct","10"); 
	  month_map.put("Nov","11");
	  month_map.put("Dec","12");	
			   
			  String site_header_str=column_list.get(0);
			  
			  String[] headerlist=site_header_str.split(">");
			  
			  String output_header=headerlist[0];
			  int req_output_colindex=exlObj_spendConsumptionYOY.getColIndex("Data", output_header);
			  
			  String report_header=headerlist[1];
			  int req_report_colindex=exlObj_spendConsumption.getColIndex("Data", report_header);	 
			 
			  int output_rowCount= exlObj_spendConsumptionYOY.rowCount("Data");
			  int report_rowCount= exlObj_spendConsumption.rowCount("Data");
			  
			  for(int i=0;i<list.size();i++) {
				  
				  int row_YOY=list.get(i);
				  
				  ArrayList<Integer> matching_row_list=new ArrayList<Integer>();
		
				  String output_primary_key= exlObj_spendConsumptionYOY.getData("Data", row_YOY, req_output_colindex);
				  
				  for(int j=1;j<=report_rowCount;j++) {
					  String report_primary_key= exlObj_spendConsumption.getData("Data", j, req_report_colindex); 
					  if(output_primary_key.equalsIgnoreCase(report_primary_key)) {
						  matching_row_list.add(j);
					  }  
				  }
				  
				 for(int matching_row:matching_row_list) {
					 
					 boolean row_status=true;
					 
					 for(String header:column_list) {
						 
						 boolean cellrow_status=true;
						 
						 String[] headers=header.split(">");
						 
						 String output_header_str=headers[0];
						 String report_header_str=headers[1];
						 
						 int output_header_str_colindex=exlObj_spendConsumptionYOY.getColIndex("Data", output_header_str);
						 String output_header_data=exlObj_spendConsumptionYOY.getData("Data", row_YOY, output_header_str_colindex);
						 
						 String report_header_data="";
						 int report_header_str_colindex=exlObj_spendConsumption.getColIndex("Data", report_header_str);
						 report_header_data=exlObj_spendConsumption.getData("Data", matching_row, report_header_str_colindex);
						  
						 if(output_header_str.equalsIgnoreCase("Billing Month")) {
							 report_header_data=exlObj_spendConsumption.getData("Data", matching_row, report_header_str_colindex);
							 report_header_data=month_map.get(report_header_data);		 
						 }
					
						 if(output_header_str.equalsIgnoreCase("Spend") || output_header_str.equalsIgnoreCase("Consumption")) {
							 
//							 System.out.println("Spend "+output_header_data+" <> "+report_header_data);
							 
							 output_header_data=output_header_data.replace("$", "");
							 report_header_data=report_header_data.replace("$", "");
							 
							 float output_header_data_float = Float.parseFloat(output_header_data);
							 float report_header_data_float = Float.parseFloat(report_header_data);
							 
							 float diff1=output_header_data_float-report_header_data_float;
							 float diff2=report_header_data_float-output_header_data_float;
							 
							 if(diff1==0.0 || diff2==0.0 || diff1<1 || diff2<1) {
							 	 cellrow_status=true;
								 System.out.println(output_header_str+" matching "+output_header_data+" "+report_header_data);
							 }else {
								 cellrow_status=false;
								 row_status=false;
								 break;
							 } 
						 }
						 if(!output_header_str.equalsIgnoreCase("Spend") && !output_header_str.equalsIgnoreCase("Consumption")) {						 
							 if(output_header_data.equalsIgnoreCase(report_header_data)) {
								 cellrow_status=true;
							 }else {
								 cellrow_status=false;
								 row_status=false;
								 break;
							 } 						 
						 } 
					 }   
//				 }  
					 
					 if(row_status==true) {	
						 int colCountYOY=exlObj_spendConsumptionYOY.colCount("Data", 0);
						 int row_statusColIndex=colCountYOY+1;							 
						 exlObj_spendConsumptionYOY.setDataInOther(downloaded_SpendConsumptionYOY_ReportFilePath,"Data", row_YOY, row_statusColIndex, "Found-Pass");	
						 break;
					 }else {
						 int colCountYOY=exlObj_spendConsumptionYOY.colCount("Data", 0);
						 int row_statusColIndex=colCountYOY+1;
						 exlObj_spendConsumptionYOY.setDataInOther(downloaded_SpendConsumptionYOY_ReportFilePath,"Data", row_YOY, row_statusColIndex, "Not Found-Fail");
					 }
			  }
			    
			    int colCountYOY=exlObj_spendConsumptionYOY.colCount("Data", 0);
				int row_status_colindex=colCountYOY+1;
			      
			    for(int startrow=1;startrow<=output_rowCount;startrow++)
			    {
			    	String foundrow_status=exlObj_spendConsumptionYOY.getData("Data", startrow, row_status_colindex);				    	
			    	if(foundrow_status.equalsIgnoreCase("Not Found-Fail")) {
			    		reportValidator=false;
			    		break;
			    	}
			    	
			    }
			  }		    
			    if(reportValidator==true) {
			  		Log.info("Report Validation Pass");
					write("PASS", "Report Validation Pass");
			  	}else {
			  		Log.info("Report Validation Fail");
					write("FAIL", "Report Validation Fail");
			  	}
			  	
}

public String getFilePath(String dirPath,String fileName) {
	
	 File dir = new File(dirPath);
	 File[] files = dir.listFiles();
	 String filePathtoReturn="";
	 
	 for (int i = 1; i < files.length; i++) {
	       if (files[i].getName().contains(fileName)) {  	
	    	   filePathtoReturn=files[i].getAbsolutePath();
	    	   break;
	       }
	 }
	 
	 return filePathtoReturn;
	
}

}
