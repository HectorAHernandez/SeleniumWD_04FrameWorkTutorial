package UsingParametersInExcelSheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// first we have to set up the project to be able to connect to Excel software, so we have to
// download from http://poi.apache.org/download.html section "binary distribution" click on this
// poi-bin-3.17-20170915.zip (or current release) for windows, this contains all the .jar file 
// for Excel connection. 
// then import all the poi.xxxx .jar into the selenium project. and then import the 
// xmlbeans-2.6.0.jar (or current release) in directory ooxml-lib.
// also import ALL the .jar file in the "lib" folder.


public class ReadWriteExcelSpreadSheet {

		public static Workbook myWorkbook;  // making these variable public static so they can be used outside the method.
		public static Sheet mySheet;
		public static Row row;
		public static Cell specificCell;
		public static FileInputStream fileInputStrm;
	 
	public static String getCellValue(int rowNumParm, int cellNumParm, String parmFilePath, String parmFileName, String parmSheetName) throws IOException {

	  //* Note: Working with Excel in order to use the values in a cell we first have to navigate
	  // * through the hierarchy of: getting the Workbook, then getting the Sheet, getting the row
	  // * then getting the column, and finally getting the content/value in the cell, so: 
	  			    
		//Create an object of File class to open the file
	    File file =    new File(parmFilePath+"\\"+parmFileName);
	    //Create an object of FileInputStream class to save the PATH of the excel file
	    // FileInputStream fileInputStrm = new FileInputStream(file);  // moved to the top as public static, to create a method
	    fileInputStrm = new FileInputStream(file);
	    
	    //Find the file extension by splitting file name in substring  and getting only extension name
	    String fileExtensionName = parmFileName.substring(parmFileName.indexOf("."));
	    //System.out.println("fileExtensionName: "+fileExtensionName);
	    
	    //Workbook myWorkbook = null;  //define an object of WorkBook type. --- moved to the top as public static for creating
	                             // this functionality as a method.
	    //Check condition if the file is xlsx file
	    if(fileExtensionName.equals(".xlsx")){
	    //If it is xlsx file then create/instantiate an object of XSSFWorkbook class, for .xlsx version
	     myWorkbook = new XSSFWorkbook(fileInputStrm);
	    }
	    //Check condition if the file is xls file
	    else if(fileExtensionName.equals(".xls")){
	        //If it is xls file then create/instantiate an object of XSSFWorkbook class, for .xls version
	    	 myWorkbook = new HSSFWorkbook(fileInputStrm);
	    }

	    //Locate the sheet inside the workbook by its name
	    //Sheet mySheet = myWorkbook.getSheet(parmSheetName); // moved to the top as public static, to create a method
	    mySheet = myWorkbook.getSheet(parmSheetName);  
	    //System.out.println("parmSheetName:" + parmSheetName);
	    
	    //calculate the number of rows in th sheet 
	    int rowCount = mySheet.getLastRowNum() - mySheet.getFirstRowNum();
	   // System.out.println("rowCount:" + rowCount+ "  Last row #: " + mySheet.getLastRowNum()
	   //                   +" first row: "+mySheet.getFirstRowNum());
	    
	    //Locate the row in the sheet
	    //Row row = mySheet.getRow(rowNumParm - 1); // moved to the top as public static, to create a method
	    row = mySheet.getRow(rowNumParm - 1);
	    
	    String valueInCell = "ERROR *** Invalid row number: "+rowNumParm+ " OR  column number: "+cellNumParm+ " ***";
	    if ( (rowNumParm - 1) <= rowCount ) {
	    	//calculate the number of columns in the row
	    	int cellCount = row.getLastCellNum() - row.getFirstCellNum(); 
		 //   System.out.println("cellCount:" + cellCount +" last cell: " +row.getLastCellNum()
		 //                      +" first cell " +row.getFirstCellNum());
	    	if ( cellNumParm <= cellCount ) {
	    		//Locate the cell in the row:
	    		//Cell specificCell = row.getCell(cellNumParm - 1);  // moved to the top as public static, to create a method
	    	    specificCell = row.getCell(cellNumParm - 1); //specificCell points to the location of the indicated
	    		//XSSFCell specificCell = row.getCell(2); //specificCell points to the location of the second
	    		              //cell in row (int row), in mySheet Sheet, in myWorkBook WorkBook.
	    		// to get the value in the specificCell
	            valueInCell = specificCell.getStringCellValue(); //also getDateCellValue(); getNumericCellValue();	
	    	}
	    }
	    	    		
        return valueInCell;
		}
	public static String setCellValue(String parmNewCellValue, int rowNumParm, int cellNumParm, String parmFilePath, String parmFileName, String parmSheetName) throws IOException {

		  //* Note: Working with Excel in order to use the values in a cell we first have to navigate
		  // * through the hierarchy of: getting the Workbook, then getting the Sheet, getting the row
		  // * then getting the column, and finally getting the content/value in the cell, so: 
		  			    
			//Create an object of File class to open the file
		    File file =    new File(parmFilePath+"\\"+parmFileName);
		    //Create an object of FileInputStream class to save the PATH of the excel file
		    // FileInputStream fileInputStrm = new FileInputStream(file);  // moved to the top as public static, to create a method
		    fileInputStrm = new FileInputStream(file);
		    
		    //Find the file extension by splitting file name in substring  and getting only extension name
		    String fileExtensionName = parmFileName.substring(parmFileName.indexOf("."));
	//	    System.out.println("fileExtensionName: "+fileExtensionName);
		    
		    //Workbook myWorkbook = null;  //define an object of WorkBook type. --- moved to the top as public static for creating
		                             // this functionality as a method.
		    //Check condition if the file is xlsx file
		    if(fileExtensionName.equals(".xlsx")){
		    //If it is xlsx file then create/instantiate an object of XSSFWorkbook class, for .xlsx version
		     myWorkbook = new XSSFWorkbook(fileInputStrm);
		    }
		    //Check condition if the file is xls file
		    else if(fileExtensionName.equals(".xls")){
		        //If it is xls file then create/instantiate an object of XSSFWorkbook class, for .xls version
		    	 myWorkbook = new HSSFWorkbook(fileInputStrm);
		    }

		    //Locate the sheet inside the workbook by its name
		    //Sheet mySheet = myWorkbook.getSheet(parmSheetName); // moved to the top as public static, to create a method
		    mySheet = myWorkbook.getSheet(parmSheetName);  
		    //System.out.println("parmSheetName:" + parmSheetName);
		    
		    //calculate the number of rows in th sheet 
		    int rowCount = mySheet.getLastRowNum() - mySheet.getFirstRowNum();
	//	    System.out.println("rowCount:" + rowCount+ "  Last row #: " + mySheet.getLastRowNum()
	//	                      +" first row: "+mySheet.getFirstRowNum());
		    
		    //Locate the row in the sheet
		    //Row row = mySheet.getRow(rowNumParm - 1); // moved to the top as public static, to create a method
		    row = mySheet.getRow(rowNumParm - 1);
		    
		    String valueInCell = "ERROR *** Invalid row number: "+rowNumParm+ " OR  column number: "+cellNumParm+ " ***";
		    if ( (rowNumParm - 1) <= rowCount ) {
		    	//calculate the number of columns in the row
		    	int cellCount = row.getLastCellNum() - row.getFirstCellNum(); 
	//		    System.out.println("cellCount:" + cellCount +" last cell: " +row.getLastCellNum()
	//		                       +" first cell " +row.getFirstCellNum());
		    	if ( cellNumParm <= cellCount ) {
		    		//Locate the cell in the row:
		    		//Cell specificCell = row.getCell(cellNumParm - 1);  // moved to the top as public static, to create a method
		    	    specificCell = row.getCell(cellNumParm - 1); //specificCell points to the location of the indicated
		    		//XSSFCell specificCell = row.getCell(2); //specificCell points to the location of the second
		    		              //cell in row (int row), in mySheet Sheet, in myWorkBook WorkBook.
		    		// to get the value in the specificCell
		            specificCell.setCellValue(parmNewCellValue); //set the new value for the specific Cell.	
		            valueInCell = specificCell.getStringCellValue(); // make the new value available to return and used if needed.
		    	}
		    }
		    	    		
	        return valueInCell;
			}	
}
