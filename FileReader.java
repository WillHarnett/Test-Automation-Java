package test;
// Import basic read write stuff
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

// Import stuff to read and write to an excel sheet
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

public class FileReader {

	// Declare a whole bunch of lists and list of lists
	static ArrayList<ArrayList<String>> stepsList = new ArrayList<ArrayList<String>>();
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
		
		FileInputStream fileIn=new FileInputStream(excelFilePath);
		// Find the sheet containing the test steps data
		Workbook workbook = WorkbookFactory.create(fileIn);  
		String testSheet = "Function Test Suite";
		org.apache.poi.ss.usermodel.Sheet functionSheet = workbook.getSheet(testSheet);

		//For Loop which finds the date in columns and rows
        for(int i = 3; i < 50; i++) {
        	String flagColumnRow = "B" + new Integer(i).toString();
        	CellReference flagReference = new CellReference(flagColumnRow); 
            Row flagRow = functionSheet.getRow(flagReference.getRow());
            Cell flagCell = flagRow.getCell(flagReference.getCol());
            
            //If the column flag is set to AUTO then find Steps, Expected results, and Test Results       
            if (Objects.equals(flagCell.getStringCellValue(), "AUTO")) {
 
            	String stepsColumnRow = "H" + new Integer(i).toString();
                CellReference stepsReference = new CellReference(stepsColumnRow); 
                Row stepsRow = functionSheet.getRow(stepsReference.getRow());
                Cell stepsCell = stepsRow.getCell(stepsReference.getCol()); 

            	String eResultsColumnRow = "I" + new Integer(i).toString();
                CellReference eResultsReference = new CellReference(eResultsColumnRow); 
                Row eResultsRow = functionSheet.getRow(eResultsReference.getRow());
                Cell eResultsCell = eResultsRow.getCell(eResultsReference.getCol()); 
                
            	String fireFoxTestResultsColumnRow = "K" + new Integer(i).toString();
            	String testerColumnRow = "P" + new Integer(i).toString();
            	String dateColumnRow = "Q" + new Integer(i).toString();

            	//If the Steps section and Expected Results section is empty then create a temp list
                if (stepsCell!=null && eResultsCell!=null) {
                	boolean addTestCases = true;
                	ArrayList<String> tempSteps = new ArrayList<String>();
                	ArrayList<String> tempeResults = new ArrayList<String>();
                	
                	String steps = stepsCell.getStringCellValue();
                	String eResults = eResultsCell.getStringCellValue();
                	
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
		ArrayList<String> temp = new ArrayList<String>();
		String tempString = "";
		String emptyString = "";
		strings = strings.replaceAll("[^A-Za-z1-9]+", "").toUpperCase();
		
		//Grabs test steps and puts them in number order, adds them to a list
		int baseCount = 0;
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
		FileInputStream fileIn = new FileInputStream(excelFilePath);
		
		//Targets the right Sheet in the Test Suite
		Workbook workbook = WorkbookFactory.create(fileIn);  
		String testSheet = "Function Test Suite";
		org.apache.poi.ss.usermodel.Sheet functionSheet = workbook.getSheet(testSheet);
		
		//Finds the results section in Test Suite
        CellReference resultsReference = new CellReference(resultsColumnRow); 
        Row resultsRow = functionSheet.getRow(resultsReference.getRow());
		XSSFCell resultsCell = (XSSFCell) resultsRow.createCell((short) resultsReference.getCol());
		//Finds the place to put the Tester name
        CellReference testerReference = new CellReference(testerColumnRow); 
        Row testerRow = functionSheet.getRow(testerReference.getRow());
		XSSFCell testerCell = (XSSFCell) testerRow.createCell((short) testerReference.getCol());
		
		//Finds the Data section
        CellReference dateReference = new CellReference(dateColumnRow); 
        Row dateRow = functionSheet.getRow(dateReference.getRow());
		XSSFCell dateCell = (XSSFCell) dateRow.createCell((short) dateReference.getCol());
        
		FileOutputStream fileOut = new FileOutputStream(new File(excelFilePath));
		
		//Sets Pass or Fail results based on format boolean
        if (results == true) {
        	resultsCell.setCellValue("P-A");
        }
        else {
        	resultsCell.setCellValue("F-A");
        }

        testerCell.setCellValue("AUTO");
        
        //Adds the Date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();

        dateCell.setCellValue(dtf.format(localDate));
        
        addBorders(workbook, resultsCell);
        addBorders(workbook, testerCell);
        addBorders(workbook, dateCell);
        
		workbook.write(fileOut);
	}

	//Make Boarders look nice?
	public static void addBorders(Workbook workbook, XSSFCell cell) {
		XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
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