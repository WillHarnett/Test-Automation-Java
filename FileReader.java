package com.familyzone.qa.automation;
// Import basic read write stuff

import com.google.common.collect.Table.Cell;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Import stuff to read and write to an excel sheet

public class FileReader {

	// Declare a whole bunch of lists and list of lists
	static List<List<String>> stepsList = new ArrayList<>();
	static ArrayList<String> eResutlsList = new ArrayList<String>();
	
	static ArrayList<String> stepsKeyWordList;
	static ArrayList<String> eResultsKeyWordList;
	
	static ArrayList<String> fireFoxTestResults = new ArrayList<String>();
	static ArrayList<String> tester = new ArrayList<String>();
	static ArrayList<String> date = new ArrayList<String>();

	// Include file path to test suite here:
	static String excelFilePath = "C:\\Users\\Aaron\\Documents\\Test Suites\\signInSignOut.xlsm";

	
	public static void readTestSuite() throws IOException, EncryptedDocumentException, InvalidFormatException {
		// Declare a list of key words and a list of expected results
		stepsKeyWordList = KeyWords.CreateStepsKeyWordList();
		eResultsKeyWordList = KeyWords. CreateeResultsKeyWordList();
		
		final FileInputStream fileIn=new FileInputStream(excelFilePath);
		// Find the sheet containing the test steps data
		final Workbook workbook = WorkbookFactory.create(fileIn);  
		final String testSheet = "Function Test Suite";
		final org.apache.poi.ss.usermodel.Sheet functionSheet = workbook.getSheet(testSheet);

		//For Loop which finds the date in columns and rows
        for(int i = 3; i < 50; i++) {
        	final String flagColumnRow = "B" + new Integer(i).toString();
        	final CellReference flagReference = new CellReference(flagColumnRow); 
            final Row flagRow = functionSheet.getRow(flagReference.getRow());
            final Cell flagCell = (Cell) flagRow.getCell(flagReference.getCol());
            
            //If the column flag is set to AUTO then find Steps, Expected results, and Test Results       
            if (Objects.equals(((org.apache.poi.ss.usermodel.Cell) flagCell).getStringCellValue(), "AUTO")) {
 
            	final String stepsColumnRow = "H" + new Integer(i).toString();
                final CellReference stepsReference = new CellReference(stepsColumnRow); 
                final Row stepsRow = functionSheet.getRow(stepsReference.getRow());
                final Cell stepsCell = (Cell) stepsRow.getCell(stepsReference.getCol()); 

            	final String eResultsColumnRow = "I" + new Integer(i).toString();
                final CellReference eResultsReference = new CellReference(eResultsColumnRow); 
                final Row eResultsRow = functionSheet.getRow(eResultsReference.getRow());
                final Cell eResultsCell = (Cell) eResultsRow.getCell(eResultsReference.getCol()); 
                
            	final String fireFoxTestResultsColumnRow = "K" + new Integer(i).toString();
            	final String testerColumnRow = "P" + new Integer(i).toString();
            	final String dateColumnRow = "Q" + new Integer(i).toString();

            	//If the Steps section and Expected Results section is empty then create a temp list
                if (stepsCell!=null && eResultsCell!=null) {
                	final boolean addTestCases = true;
                	ArrayList<String> tempSteps = new ArrayList<String>();
                	ArrayList<String> tempeResults = new ArrayList<String>();
                	
                	final String steps = ((org.apache.poi.ss.usermodel.Cell) stepsCell).getStringCellValue();
                	String eResults = ((org.apache.poi.ss.usermodel.Cell) eResultsCell).getStringCellValue();
                	
                	tempSteps = FindKeyWords(steps);
                	tempeResults = FindKeyWords(eResults);
                	
                	eResults = tempeResults.get(0);
                	
                	fireFoxTestResults.add(fireFoxTestResultsColumnRow);
                	tester.add(testerColumnRow);
                	date.add(dateColumnRow);
                	
                	if(addTestCases == true) {
                    	stepsList.add(tempSteps);
                    	eResutlsList.add(eResults);
                	}
                }         
            }	
        }
        
        //This runs the RunAutomation Class
        RunAutomation.stepsList = stepsList;
        RunAutomation.eResutlsList = eResutlsList;
	}
	
	//Run the FindKeyWords module and format the data from the workbook by removing unused characters
	//Also converts strings to all upper case 
	public static ArrayList<String> FindKeyWords(String strings) {
		final ArrayList<String> temp = new ArrayList<String>();
		String tempString = "";
		final String emptyString = "";
		strings = strings.replaceAll("[^A-Za-z1-9]+", "").toUpperCase();
		
		//Grabs test steps and puts them in number order, adds them to a list
		final int baseCount = 0;
		int currentCount = 0;
		for(int i = 0; i < strings.length(); i++) {
			if(strings.charAt(i) >= '0' && strings.charAt(i) <= '9') {
				currentCount++;
				
				//This if statement checks the second char to see if the step number is larger than '9'
				//and adds them to the steps list too
				if(strings.charAt(i+1) >= '0' && strings.charAt(i+1) <= '9') {
					i++;
					currentCount++;
					if(currentCount >= baseCount + 2) {
						currentCount = 1;
						System.out.println(tempString);
						temp.add(tempString);
						tempString = emptyString;
					}
				}
				else {
					tempString = tempString + strings.charAt(i);
				}
			}
			else {
				
				if(currentCount >= baseCount + 2) {
					currentCount = 1;
					System.out.println(tempString);
					temp.add(tempString);
					tempString = emptyString;
				}
				else{
					tempString = tempString + strings.charAt(i);
				}
			}	
		}
		temp.add(tempString);
		return temp;
	}
	
	//This will write the results to the WorkBook 
	public static void PrintResults(boolean results, String resultsColumnRow, String testerColumnRow, String dateColumnRow) throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		//Tells where the Test Suite is
		final FileInputStream fileIn = new FileInputStream(excelFilePath);
		
		//Targets the right Sheet in the Test Suite
		final Workbook workbook = WorkbookFactory.create(fileIn);  
		final String testSheet = "Function Test Suite";
		final org.apache.poi.ss.usermodel.Sheet functionSheet = workbook.getSheet(testSheet);
		
		//Finds the results section in Test Suite
        final CellReference resultsReference = new CellReference(resultsColumnRow); 
        final Row resultsRow = functionSheet.getRow(resultsReference.getRow());
		final XSSFCell resultsCell = (XSSFCell) resultsRow.createCell(resultsReference.getCol());
		//Finds the place to put the Tester name
        final CellReference testerReference = new CellReference(testerColumnRow); 
        final Row testerRow = functionSheet.getRow(testerReference.getRow());
		final XSSFCell testerCell = (XSSFCell) testerRow.createCell(testerReference.getCol());
		
		//Finds the Data section
        final CellReference dateReference = new CellReference(dateColumnRow); 
        final Row dateRow = functionSheet.getRow(dateReference.getRow());
		final XSSFCell dateCell = (XSSFCell) dateRow.createCell(dateReference.getCol());
        
		final FileOutputStream fileOut = new FileOutputStream(new File(excelFilePath));
		
		//Sets Pass or Fail results based on format boolean
        if (results == true) {
        	resultsCell.setCellValue("P-A");
        }
        else {
        	resultsCell.setCellValue("F-A");
        }

        testerCell.setCellValue("AUTO");
        
        //Adds the Date
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        final LocalDate localDate = LocalDate.now();

        dateCell.setCellValue(dtf.format(localDate));
        
        addBorders(workbook, resultsCell);
        addBorders(workbook, testerCell);
        addBorders(workbook, dateCell);
        
		workbook.write(fileOut);
	}

	//Make Boarders look nice?
	public static void addBorders(Workbook workbook, XSSFCell cell) {
		final XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
		//style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);	
		
		cell.setCellStyle(style);
	} 
	//Says that everything is done (subject to change)
	public static void main(String [] args) {
	System.out.println("Done! Pretty sure there wasn't any errors...");
	}
}