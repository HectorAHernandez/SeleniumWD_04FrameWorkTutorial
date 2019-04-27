package UsingParametersInExcelSheet;

import java.io.IOException;

public class processingExcelSheetWithMethodInAClass extends ReadWriteExcelSpreadSheet{
// Class ReadWriteExcelSpreadSheet contains the setCellValue() and getCellValue() methods.    
	public static void main(String[] args) throws IOException  {
	 
		// the getCellvalue(row, cell, filePath, fileName, sheetName) method works based on the x,y in the spreadsheet.
		String filePath = "C:\\Eclipse\\work_space\\04FrameWorkTutorial\\src\\UsingParametersInExcelSheet";
	    String fileName = "newusersList.xlsx";
	    String sheetName = "FirstSheetTab";
		String valueInCell = getCellValue(4,2, filePath, fileName, sheetName);
		System.out.println("*** value in cell: "+valueInCell);
		
		valueInCell = getCellValue(3,1, filePath, fileName, sheetName);
		System.out.println("*** value in cell: "+valueInCell);
				
		// To access another cell in another spreadsheet.
		filePath = "C:\\Eclipse\\work_space\\04FrameWorkTutorial\\src\\UsingParametersInExcelSheet";
		fileName = "ExportExcel.xlsx";
		sheetName = "ExcellGuru99Demo";
		valueInCell = getCellValue(17,2, filePath, fileName, sheetName);
		System.out.println("*** value in cell: "+valueInCell);
		
		valueInCell = getCellValue(8,1, filePath, fileName, sheetName);
		System.out.println("*** value in cell: "+valueInCell);
		
		// using the onther method to SET a cell value:
		// the setCellvalue(newvalue, row, cell, filePath, fileName, sheetName) method works based on the x,y in the spreadsheet.
		filePath = "C:\\Eclipse\\work_space\\04FrameWorkTutorial\\src\\UsingParametersInExcelSheet";
		fileName = "newusersList.xlsx";
		sheetName = "FirstSheetTab";
		valueInCell = setCellValue("Hector New Value",4,2, filePath, fileName, sheetName);
		System.out.println("*** NEW value in cell: "+valueInCell);
		
		filePath = "C:\\Eclipse\\work_space\\04FrameWorkTutorial\\src\\UsingParametersInExcelSheet";
		fileName = "ExportExcel.xlsx";
		sheetName = "ExcellGuru99Demo";
		valueInCell = setCellValue("MODIFIED VALUE",7,2, filePath, fileName, sheetName);
		System.out.println("*** NEW value in cell: "+valueInCell);
		// To displays the data we got from the spreadsheet in a Selenium WebObject we can use the sendKeys method, example:
		// driver.findElement(By.id("userId").sendKeys(valueInCell)); // valueInCell from line 34.
	}	
}
