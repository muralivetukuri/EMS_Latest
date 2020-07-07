package com.prokarma.qa.web.pages;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.prokarma.qa.base.CommonFunctions;
import com.prokarma.qa.web.helpers.ExcelFileUtil;
import com.prokarma.qa.web.helpers.Log;

public class HomePage  extends CommonFunctions{
	
	private WebDriver driver;

	public HomePage(WebDriver driver ) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(linkText="WORKSPACE")
	public WebElement workspace_lnk;
	
	@FindBy(xpath="//a[text()='Tools ']")
	public WebElement Toos_btn;
	
	
	//******************* SIDE BAR BUTTONS **********************//
	
	@FindBy(xpath="//h3[text()='Analytics']//ancestor::li")
	public WebElement analytics;
	
	@FindBy(xpath="(//h3[text()='Analytics']//parent::div/div/ul/li)[1]")
	public WebElement analytics_Reporting;
	
	@FindBy(xpath="//h3[text()='Analytics']//parent::div/div[2]/ul/li[1]")
	public WebElement analytics_Reporting_Spendandconsumption;
	
	@FindBy(xpath="//h3[text()='Analytics']//parent::div/div[2]/ul/li[8]")
	public WebElement analytics_Reporting_SpendandconsumptionYOY;
	
	
	//******************* Spend & Consumption - YOY BUTTONS **********************//
	
	@FindBy(id="ddlClient")
	public WebElement clientYOY;
	
//	@FindBy(xpath="(//div[contains(@id,'jqxScrollThumbverticalScrollBarinnerListBoxjqxWidget')])[13]")
//	public WebElement clientWindowYOY;
	
	@FindBy(xpath="//div[contains(@id,'jqxScrollThumbverticalScrollBarinnerListBoxjqxWidget')]")
	public List<WebElement> clientWindowYOY;
	
	@FindBy(xpath="//span[text()='All']//parent::div/child::div/child::div/child::div")
	public List<WebElement>  clientWindowYOY_all; 
	
	@FindBy(id="ddlCommodity")
	public WebElement utilityYOY;
	
//	@FindBy(xpath="(//div[contains(@id,'jqxScrollThumbverticalScrollBarinnerListBoxjqxWidget')])[14]")
//	public WebElement utilityWindowYOY;
	
	@FindBy(xpath="//div[contains(@id,'jqxScrollThumbverticalScrollBarinnerListBoxjqxWidget')]")
	public List<WebElement> utilityWindowYOY;
	
	@FindBy(xpath="//span[text()='All']//parent::div/child::div/child::div/child::div")
	public List<WebElement>  utilityWindowYOY_all; 
	
	@FindBy(xpath="//label[contains(text(),'From')]//following::angulardropdownlist[1]")
	public WebElement commodityFromMonthYOY;
	
	@FindBy(xpath="//label[contains(text(),'From')]//following::angulardropdownlist[2]")
	public WebElement commodityFromYearYOY;
	
	@FindBy(xpath="//label[contains(text(),'To')]//following::angulardropdownlist[1]")
	public WebElement commodityToMonthYOY;
	
	@FindBy(xpath="//label[contains(text(),'To')]//following::angulardropdownlist[2]")
	public WebElement commodityToYearYOY;
	
	@FindBy(id="btnMeterDetails")
	public WebElement spendConsumptionsearchYOY;  
	
	@FindBy(xpath="//*[@id='dvYoyByPartnerDetails']/div/div[1]/div/i")
	public WebElement recordsloadingYOY;
	
	@FindBy(xpath="//button[contains(text(),'SEARCH')]")
	public WebElement spendConsumptionsearch;  
	
	@FindBy(xpath="//div[contains(@id,'row0')]/div/div")
	public List<WebElement> spendConsumptionYOYReportRow0;
	
	@FindBy(xpath="//div[@role='columnheader']/div/div[1]/span")
	public List<WebElement> spendConsumptionYOYheaders;
	
	@FindBy(xpath="(//*[contains(@id,'jqxScrollThumbverticalScrollBarjqxWidget')])[2]")
	public WebElement spendConsumptionYOYReportWindow;
	
	@FindBy(xpath="//li[@class='dropdown nav-client']")
	public WebElement allClientWindow;
	
	@FindBy(xpath="//a[text()='ALL']")
	public WebElement allClientWindowAllOption;
	
	@FindBy(xpath="//label[contains(text(),'Commodity')]//following::div[1]")
	public WebElement  spendConsumptionCommodity;
	
	@FindBy(xpath="//label[contains(text(),'From')]//following::angulardropdownlist[1]")
	public WebElement  spendConsumptionCommodityFromMonth;
	
	@FindBy(xpath="//label[contains(text(),'To')]//following::angulardropdownlist[1]")
	public WebElement  spendConsumptionCommodityToMonth;
	
	@FindBy(xpath="//label[contains(text(),'From')]//following::angulardropdownlist[2]")
	public WebElement  spendConsumptionCommodityFromYear;
	
	@FindBy(xpath="//label[contains(text(),'To')]//following::angulardropdownlist[2]")
	public WebElement  spendConsumptionCommodityToYear;
	
	@FindBy(xpath="//i[@ngbtooltip='Download']")
	public WebElement  spendConsumptionDownloadArrow;
	
	@FindBy(xpath="(//angularradiobutton)[18]")
	public WebElement  spendConsumptionExcelDownloadRadio; 
	
	@FindBy(xpath="//button[text()='DOWNLOAD']")
	public WebElement  spendConsumptionDownloadBtn;
	
	@FindBy(xpath="//i[@ngbtooltip='Download']")
	public WebElement  spendConsumptionYOYDownloadArrow;
	
	@FindBy(xpath="(//angularradiobutton)[8]")
	public WebElement  spendConsumptionYOYExcelDownloadRadio; 
	
	@FindBy(xpath="//button[text()='DOWNLOAD']")
	public WebElement  spendConsumptionYOYDownloadBtn;
	
	@FindBy(xpath="//*[text()='No data to display']")
	public WebElement  textNotDisplay;
	
	@FindBy(xpath="//*[contains(@id,'pagerjqxWidget')]/div/div[6]")
	public WebElement  spendConsumptionYOYTotalRecords;
	
	@FindBy(xpath="//div[@title='next']")
	public WebElement  spendConsumptionYOYTableRecordsNextButton;
	
	public void fn_hp_click_workspace_lnk() {
		fnWaitForElement(workspace_lnk,60);
		fnClickElementByJS("workspace_lnk",workspace_lnk);
	}
	
	public void checkForLogin() {
		try {
			fnWaitForElement(workspace_lnk,60);
			Assert.assertTrue(workspace_lnk.isDisplayed());		
			Log.info("Login Successfull");
			write("PASS", "Login Successfull");
		}catch(Exception e) {
			Log.info("Login Failed");
			write("FAIL", "Login Successfull");
		}
	}
	
	public void fnSelectWorkspace() throws Exception {
		try {
			fnWaitForElement(workspace_lnk,60);
			fnClickElementByJS("workspace_lnk",workspace_lnk);
			fnVisibleOnPage(Toos_btn,"Toos_btn");
			fnClickElementByJS("workspace_lnk",workspace_lnk);
			Log.info("workspace selected");
		}catch(Exception e) {
			Log.info("click on workspace failed");
		}
	}
	
	public void fn_hp_select_bubble(WebElement mainIcon,WebElement parentBubble,WebElement childBubble,String selectionMessage) throws Exception {
		try {
			mainIcon.click();
			Thread.sleep(7000);
			parentBubble.click();
			Thread.sleep(7000);
			childBubble.click();
			Log.info("Bubble : "+selectionMessage+" Selected");
			write("PASS", "Bubble : "+selectionMessage+" Selected");
		}catch(Exception e) {
			takeScreenShot(driver,"Select Bubble",geTtestCaseName());
			Log.info("Bubble : "+selectionMessage+" Selection error");
			write("FAIL", "Bubble : "+selectionMessage+" Selection error");
		}
			
	}
	
	 public void selectClientFromClientsWindow() throws Throwable {
		 String clientName = getTestInput("TestData",geTtestCaseName(), "ClientNavigation");
		 if(!clientName.isEmpty()) {
			 try {
				  fnWaitForElement(allClientWindow,60);	
				  allClientWindow.click();
				  fnWaitForElement(allClientWindowAllOption,60);	
				  allClientWindowAllOption.click();	
				  driver.findElement(By.xpath("//img[@alt='"+clientName+"']//parent::a")).click();
				  Log.info(clientName+" Selected");
				  write("PASS", clientName+" Selected");
			}catch(Exception e) {
				 Log.info(clientName+" Selection error");
				 write("FAIL", clientName+" Selection error");
			}
		 }  
	 }
	
	 public void selectClient(String ClientName) throws Throwable {
		 String exp_option =getTestInput("TestData", geTtestCaseName(), ClientName);
		 
		 try {
			 
			 if(!exp_option.equalsIgnoreCase("All")){
					fnWaitForElement(clientYOY,60);
				    clientYOY.click();
				    Thread.sleep(5000);
				    boolean allClickStatus=false;
			    
				    for(WebElement all:clientWindowYOY_all) {
				    	if(allClickStatus==false) {
					    	try {
					    		if(!all.isSelected()) {
					    			all.click();
					    		    allClickStatus=true;
					    		    Thread.sleep(5000);
					    		}
					    	}catch(Exception e) {
					    		
					    	}
				    	}	
				    }
				    
				    WebElement clientWindowwYOY= getRequiredElement(clientWindowYOY);
	
				     boolean scrollStatus=true;  
					  try {
						  scrollStatus=clientWindowwYOY.isDisplayed();
					  }catch(Exception e) {
						  scrollStatus=false;
					  }
					  
					  Point location = clientWindowwYOY.getLocation();
					  int x = location.getX();
					  int y = location.getY();
	
				    if(scrollStatus==true) {
						int xOffset = clientWindowwYOY.getLocation().getX();		
						String[] req_Options=exp_option.split("/");
						
									for(String option:req_Options) {
//										Actions actions=new Actions(driver);
										
										boolean clickStatus=false;
										
										while(clickStatus==false) {
											try {
												
												List<WebElement> options=driver.findElements(By.xpath("//span[text()='"+option+"']//parent::div/child::div/child::div/child::div"));
												getRequiredElement(options).click();
//												driver.findElement(By.xpath("//span[text()='"+option+"']//parent::div/child::div/child::div/child::div")).click();
												break;
											}catch(Exception e) {
												
												Actions dragger = new Actions(driver);
												dragger.moveToElement(clientWindowwYOY).clickAndHold().moveByOffset(0,20).build().perform();
												
											}
										}	
									}
				     }else {
				    			String[] req_Options=exp_option.split("/");
				    			System.out.println("options are "+req_Options);
						    	for(String option:req_Options) {
						    		System.out.println("req_Options are "+option);
//									driver.findElement(By.xpath("//span[text()='"+option+"']//parent::div/child::div/child::div/child::div")).click();
									List<WebElement> options=driver.findElements(By.xpath("//span[text()='"+option+"']//parent::div/child::div/child::div/child::div"));
									getRequiredElement(options).click();
									
						    	}		
				     }
			}	    
				    	Log.info(exp_option+" selected from client dropdown");
				    	write("PASS", exp_option+" selected from client dropdown");    	
				    	
		}catch(Exception e) {
						e.printStackTrace();
						takeScreenShot(driver,"Select Client",geTtestCaseName());
						Log.info(exp_option+" selection error in client dropdown");
				    	write("FAIL", exp_option+" selection error in client dropdown");
		}
	}
	 
	public void selectUtility(String Commodity) throws Throwable {
		
		String exp_option =getTestInput("TestData", geTtestCaseName(), Commodity);
		
		try {	
			
			if(!exp_option.equalsIgnoreCase("All")){
				
				fnWaitForElement(utilityYOY,60);	
				utilityYOY.click();
				Thread.sleep(5000);
				
				boolean allClickStatus=false;
		    
				for(WebElement all:clientWindowYOY_all) {
			    	if(allClickStatus==false) {
				    	try {
				    		if(!all.isSelected()) {
				    			all.click();
				    		    allClickStatus=true;
				    		    Thread.sleep(5000);
				    		    break;
				    		}
				    	}catch(Exception e) {
				    		
				    	}
			    	}	
			    }
				
				WebElement utilityWindowwYOY= getRequiredElement(utilityWindowYOY);
  
			  	boolean scrollStatus=true;  
				try {
					  scrollStatus=utilityWindowwYOY.isDisplayed();
				}catch(Exception e) {
					  scrollStatus=false;
				}
	  
			    if(scrollStatus==true) {
					int xOffset = utilityWindowwYOY.getLocation().getX();
					
					String[] req_Options=exp_option.split("/");
					
								for(String option:req_Options) {
//									Actions actions=new Actions(driver);
									
									boolean clickStatus=false;
									
									while(clickStatus==false) {
										try {
											List<WebElement> options=driver.findElements(By.xpath("//span[text()='"+option+"']//parent::div/child::div/child::div/child::div"));
											getRequiredElement(options).click();
//											driver.findElement(By.xpath("//span[text()='"+option+"']//parent::div/child::div/child::div/child::div")).click();
											break;
										}catch(Exception e) {
	//										actions.dragAndDropBy(utilityWindowYOY, 9, 10).perform();
											Actions dragger = new Actions(driver);
											dragger.moveToElement(utilityWindowwYOY).clickAndHold().moveByOffset(0,20).build().perform();
										}
									}	
								}
			    }else {
			    			String[] req_Options=exp_option.split("/");
					    	for(String option:req_Options) {
								driver.findElement(By.xpath("//span[text()='"+option+"']//parent::div/child::div/child::div/child::div")).click();
					    	}		
			    }
			}
		    		Log.info(exp_option+" selected from utility dropdown");
		    		write("PASS", exp_option+" selected from utility dropdown");  
		    		
			}catch(Exception e) {
				        takeScreenShot(driver,"Select Utility",geTtestCaseName());
						Log.info(exp_option+" selected error in utility dropdown");
				    	write("PASS", exp_option+" selected error in utility dropdown");
			}
	}
	
	public void selectYOYFromMonth() throws Throwable {
		String commodity_from_month =getTestInput("TestData", geTtestCaseName(), "Commodity_From_Month");
		fnWaitForElement(commodityFromMonthYOY, 60);
		commodityFromMonthYOY.click();
		
		List<WebElement> elements_to_click=driver.findElements(By.xpath("//span[contains(text(),'"+commodity_from_month+"')]"));
		getRequiredElement(elements_to_click).click();
//		driver.findElement(By.xpath("(//span[contains(text(),'"+commodity_from_month+"')])[1]")).click();
	}
	
	public void selectYOYFromYear() throws Exception, Throwable {
		String commodity_from_year=getTestInput("TestData", geTtestCaseName(), "Commodity_From_Year");
		commodityFromYearYOY.click();
		List<WebElement> elements_to_click=driver.findElements(By.xpath("//span[contains(text(),'"+commodity_from_year+"')]"));
		getRequiredElement(elements_to_click).click();
//		driver.findElement(By.xpath("(//span[contains(text(),'"+commodity_from_year+"')])[1]")).click();
	}
	
	public void selectYOYToMonth() throws Exception, Throwable {
		String commodity_to_month =getTestInput("TestData", geTtestCaseName(), "Commodity_To_Month");
		commodityToMonthYOY.click(); 
		List<WebElement> elements_to_click=driver.findElements(By.xpath("//span[contains(text(),'"+commodity_to_month+"')]"));
		getRequiredElement(elements_to_click).click();
//		driver.findElement(By.xpath("(//span[contains(text(),'"+commodity_to_month+"')])[2]")).click();
	}
	
	public void selectYOYToYear() throws Exception, Throwable {
		String commodity_to_year=getTestInput("TestData", geTtestCaseName(), "Commodity_To_Year");
		commodityToYearYOY.click();
		List<WebElement> elements_to_click=driver.findElements(By.xpath("//span[contains(text(),'"+commodity_to_year+"')]"));
		getRequiredElement(elements_to_click).click();
//		driver.findElement(By.xpath("(//span[contains(text(),'"+commodity_to_year+"')])[2]")).click();
		
	}
	
	public boolean get_spendConsumptionYOY_records(String sheetToStoreRecords) throws Throwable {
		
		boolean table_status = false;

		fn_hp_select_bubble(analytics,analytics_Reporting,analytics_Reporting_SpendandconsumptionYOY,"Analytics>Reporting>Spend and ConsumptionYOY");	
		selectClient("YOYClientName");
		selectUtility("YOYCommodity");
		selectYOYFromMonth();
		selectYOYFromYear();
		selectYOYToMonth();
		selectYOYToYear();
		
		Thread.sleep(5000);
		spendConsumptionsearchYOY.click();
		
		try {	
			int scrollstatus=1;
			while(recordsloadingYOY.isDisplayed()) {
				Thread.sleep(5000);
				scrollstatus++;
			}
		}catch(Exception e) {
			
			
		}
		
		Thread.sleep(5000);

		ExcelFileUtil exlTestoutput=new ExcelFileUtil(getTestOutputFile());
		int sheetIndex=exlTestoutput.getSheetIndex(getTestOutputFile(), sheetToStoreRecords);
		
		if(sheetIndex>=0) {
			exlTestoutput.deleteSheet(getTestOutputFile(),sheetToStoreRecords);
			exlTestoutput.createSheet(getTestOutputFile(),sheetToStoreRecords);
		}else {
			exlTestoutput.createSheet(getTestOutputFile(),sheetToStoreRecords);
		}
		
		if(spendConsumptionYOYReportRow0.size()!=0) {
			  ArrayList<ArrayList<String>> mainList = new ArrayList<ArrayList<String>>();
			   
			  mainList=getheaders(driver,mainList);
			  mainList=getfirsthalfrecords(driver,mainList);
			  mainList=getsecondhalfrecords(driver, mainList);
			 
			  for(int i=1;i<=4;i++) {
				  spendConsumptionYOYTableRecordsNextButton.click();
				  mainList=getfirsthalfrecords(driver,mainList);
				  mainList=getsecondhalfrecords(driver, mainList);
			  }
			  
			  int i=0;
			  for(ArrayList<String> list:mainList) {
				 int j=0;
				 for(String listvalue:list) {
						exlTestoutput.setData(sheetToStoreRecords,i, j, listvalue);
						j++;
				 }
				i++;	
			   }
			  	
//				  String clientName = getTestInput("TestData",geTtestCaseName(), "ClientNavigation");
//				  if(!clientName.isEmpty()) {
//				  selectClientFromClientsWindow(clientName);
//				  }
			  selectClientFromClientsWindow();
			  
				exlTestoutput=new ExcelFileUtil(getTestOutputFile());						
				int totalrows=exlTestoutput.rowCount(sheetToStoreRecords);
				int statuscolindex=exlTestoutput.getColIndex(sheetToStoreRecords, "Status");
				
				for(int j=1;j<=totalrows;j++) {
					exlTestoutput.setData(sheetToStoreRecords,j, statuscolindex, "");
				}
					
		}else { 
			  try {
			  	 table_status=textNotDisplay.isDisplayed();
				  if(table_status==true) {
					 String table_empty_status=  textNotDisplay.getText();
					 System.out.println(table_empty_status);
				  }
			  }catch(Exception e) {
				  
			  }  			  	
		}	
		
		if(table_status==false) {
			write("INFO", "SpendConsumptionYOY_table records saved to OUTPUT file "+sheetToStoreRecords+" sheet");
			Log.info("SpendConsumptionYOY_table records saved to OUTPUT file "+sheetToStoreRecords+" sheet");
		}else {
			write("INFO", "SpendConsumptionYOY_table have no records to display");
			Log.info("SpendConsumptionYOY_table have no records to display");
		}
		
		return table_status;
	}

	 public ArrayList<ArrayList<String>> getfirsthalfrecords(WebDriver driver,ArrayList<ArrayList<String>> mainList ) {
			
			int i =0;		
			while(i<=4) {	
				ArrayList<String> templist=new ArrayList<String>();
				String xpath="//div[contains(@id,'row"+i+"')]/div/div";
				List<WebElement> cells=driver.findElements(By.xpath(xpath));
				for(int k=0;k<cells.size();k++) {
					String cellValue=cells.get(k).getText();
					templist.add(cellValue);
//					System.out.println(cellValue);
				}
				mainList.add(templist);
				i++;		
			}	
			return mainList;
	  }
		
		public ArrayList<ArrayList<String>> getsecondhalfrecords(WebDriver driver,ArrayList<ArrayList<String>> mainList) {
					
//			int xOffset = spendConsumptionYOYReportWindow.getLocation().getX();
			
			Actions actions=new Actions(driver);
			actions.dragAndDropBy(spendConsumptionYOYReportWindow, 9, 153).perform();
			
			int i =0;	
			while(i<5) {		
				ArrayList<String> templist=new ArrayList<String>();
				String xpath="//div[contains(@id,'row"+i+"')]/div/div";
				List<WebElement> cells=driver.findElements(By.xpath(xpath));

				for(int k=0;k<cells.size();k++) {
					String cellValue=cells.get(k).getText();
					templist.add(cellValue);
				}
				mainList.add(templist);
			    i++;
			}
			return mainList;
		 }
		
		public ArrayList<ArrayList<String>> getheaders(WebDriver driver,ArrayList<ArrayList<String>> mainList) {
			
			ArrayList<String> templist=new ArrayList<String>();
			
			for(WebElement header:spendConsumptionYOYheaders) {
				String headertext=header.getText();			
				templist.add(headertext);
			}
			templist.add("Status");
			mainList.add(templist);
			return mainList;
		}
		
		 public boolean downloadspendConsumptionYOYReport() throws Throwable {
			 	
			  boolean table_status=false;
	
	   		  deleteFile("SpendAndConsumption_YOY_Report");
			  fnWaitForElement(analytics,60);
			  Thread.sleep(8000);
//			  PageFactory.initElements(this.driver, this);
			  fn_hp_select_bubble(analytics,analytics_Reporting,analytics_Reporting_SpendandconsumptionYOY,"Analytics>Reporting>Spend and ConsumptionYOY");	
			  Thread.sleep(8000);
			  
			  	selectClient("YOYClientName");
				selectUtility("YOYCommodity");
				selectYOYFromMonth();
				selectYOYFromYear();
				selectYOYToMonth();
				selectYOYToYear();
				
				Thread.sleep(5000);
				spendConsumptionsearchYOY.click();
				
				try {
					
					int scrollstatus=1;
					while(recordsloadingYOY.isDisplayed()) {
						Thread.sleep(5000);
						scrollstatus++;
					}
				
				}catch(Exception e) {
					
					
				}
				
				Thread.sleep(5000);
			  
			  try {
//				  fnWaitForElement(textNotDisplay,60);
			  	  table_status=textNotDisplay.isDisplayed();
				  if(table_status==true) {
					 String table_empty_status=  textNotDisplay.getText();
//					 System.out.println(table_empty_status);
				  }
			  }catch(Exception e) {
				  table_status=false;
				  fnWaitForElement(spendConsumptionYOYDownloadArrow,60);
				  fnClickElementByJS("spendConsumptionYOYDownloadArrow", spendConsumptionYOYDownloadArrow);
				  fnWaitForElement(spendConsumptionYOYExcelDownloadRadio,60);
				  spendConsumptionYOYExcelDownloadRadio.click();
				  spendConsumptionYOYDownloadBtn.click();
				  
				  write("INFO", "SpendConsumptionYOY Report Downloaded to TestOutput");
				  Log.info("SpendConsumptionYOY Report Downloaded to TestOutput");
					
			  }
			  
			  return table_status;
		 }	  
	
		 public boolean downloadspendConsumptionReport() throws Throwable {
			 	
			  boolean table_status=false;
			 
	   		  deleteFile("Spend & Consumption");
			  fnWaitForElement(analytics,60);
			  Thread.sleep(8000);
			  PageFactory.initElements(this.driver, this);
			  fn_hp_select_bubble(analytics,analytics_Reporting,analytics_Reporting_Spendandconsumption,"Analytics>Reporting>Spend and Consumption");	
			  Thread.sleep(8000);
			  spendConsumptionCommodity.click();
			  String commodity =getTestInput("TestData", geTtestCaseName(), "Commodity"); 
			  Thread.sleep(8000);
			  
			  Actions actions=new Actions(driver);
			  List<WebElement> r=driver.findElements(By.xpath("//label[contains(text(),'Commodity')]//parent::div//following::div[contains(@id,'jqxScrollThumbverticalScrollBarinnerListBoxjqxWidget')]"));
			 
			  WebElement requiredelement=getRequiredElement(r);
			  boolean clickStatus=false;
				while(clickStatus==false) {
					try {
						  	 List<WebElement> options=driver.findElements(By.xpath("//label[contains(text(),'Commodity')]//following::span[text()='"+commodity+"']"));
							 WebElement requiredwindowelement=getRequiredElement(options);
							 actions.moveToElement(requiredwindowelement).click().build().perform();
							 break;
					}catch(Exception e) {
						actions.dragAndDropBy(requiredelement, 9, 20).perform();	
						Thread.sleep(5000);
					}
				}
				
			 Thread.sleep(5000);
			  
			  spendConsumptionCommodityFromMonth.click();
			  String commodity_from_month =getTestInput("TestData", geTtestCaseName(), "Commodity_From_Month");
//			  driver.findElement(By.xpath("//span[contains(text(),'"+commodity_from_month+"')]")).click();
			  r=driver.findElements(By.xpath("//span[contains(text(),'"+commodity_from_month+"')]"));
			  requiredelement=getRequiredElement(r);
			  requiredelement.click();			  
			  
			  spendConsumptionCommodityFromYear.click();
			  String commodity_from_year=getTestInput("TestData", geTtestCaseName(), "Commodity_From_Year");
//			  driver.findElement(By.xpath("//span[contains(text(),'"+commodity_from_year+"')]//parent::div")).click();
			  r=driver.findElements(By.xpath("//span[contains(text(),'"+commodity_from_year+"')]//parent::div"));
			  requiredelement=getRequiredElement(r);
			  requiredelement.click();
			  
			  spendConsumptionCommodityToMonth.click();
			  String commodity_to_month =getTestInput("TestData", geTtestCaseName(), "Commodity_To_Month");
//			  driver.findElement(By.xpath("(//span[contains(text(),'"+commodity_to_month+"')])[2]")).click();
			  r=driver.findElements(By.xpath("//span[contains(text(),'"+commodity_to_month+"')]"));
			  requiredelement=getRequiredElement(r);
			  requiredelement.click();
			  
			  spendConsumptionCommodityToYear.click();
			  String commodity_to_year=getTestInput("TestData", geTtestCaseName(), "Commodity_To_Year");
//			  driver.findElement(By.xpath("(//span[contains(text(),'"+commodity_to_year+"')])[2]//parent::div")).click();
			  r=driver.findElements(By.xpath("//span[contains(text(),'"+commodity_to_year+"')]//parent::div"));
			  requiredelement=getRequiredElement(r);
			  requiredelement.click();
			  
			  spendConsumptionsearch.click();
			  
			  Thread.sleep(20000);
			  
			  try {
//				  fnWaitForElement(textNotDisplay,60);
			  	  table_status=textNotDisplay.isDisplayed();
				  if(table_status==true) {
					 String table_empty_status=  textNotDisplay.getText();
//					 System.out.println(table_empty_status);
				  }
			  }catch(Exception e) {
				  table_status=false;
				  fnWaitForElement(spendConsumptionDownloadArrow,60);
				  fnClickElementByJS("spendConsumptionDownloadArrow", spendConsumptionDownloadArrow);
				  fnWaitForElement(spendConsumptionExcelDownloadRadio,60);
				  spendConsumptionExcelDownloadRadio.click();
				  spendConsumptionDownloadBtn.click();
				  write("INFO", "SpendConsumption Report Downloaded to TestOutput");
				  Log.info("SpendConsumption Report Downloaded to TestOutput");
			  } 
			
			  return table_status;
		 }
		 
		 public void deleteFile(String fileToDelete) throws InterruptedException {
				
				  File dir = new File("TestOutput");
				  String reqFileName="";
			      String[] children = dir.list();
			      
			      if (children == null) {
			         System.out.println("does not exist or  is not a directory");
			      } else {
			         for (int i = 0; i < children.length; i++) {
			              String filename = children[i];
				          if(filename.contains(fileToDelete)){
				        	  reqFileName=filename;
				        	  File f=new File(System.getProperty("user.dir")+File.separator+"TestOutput"+File.separator+reqFileName);
				        	  f.delete();
				        	  break;
				          }
			         }
			      }
			    
		}
}
