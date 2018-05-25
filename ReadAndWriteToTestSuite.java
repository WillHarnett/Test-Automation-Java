package com.familyzone.qa.automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadAndWriteToTestSuite {

	static String testSuite = RunAutomation.testSuite;

	static int currentRowCount = 0;

	// Below is all of the relevant columns
	static int stepsColumn = 0;
	static int expectedResultsColumn = 0;

	static int iOSColumn = 0;
	static int iOSFilterOnlyColumn = 0;
	static int iOSFCVLColumn = 0;
	static int androidColumn = 0;
	static int androidFCVLColumn = 0;

	static int commentColumn = 0;

	public static void setUpVariables() throws EncryptedDocumentException, InvalidFormatException, IOException {

		FileInputStream fileIn = new FileInputStream(testSuite);
		Workbook workbook = WorkbookFactory.create(fileIn);

		Sheet sheet = workbook.getSheetAt(0);

		Row currentRow;
		Cell testResultsCell;
		for (int i = 0; i < 10; i++) {// Find the "STEPS" column and save column as variable
			for (int j = 0; j < 50; j++) {
				currentRow = sheet.getRow(i);
				testResultsCell = currentRow.getCell(j);
				if (testResultsCell == null) {

				} else if (Objects.equals(testResultsCell.getStringCellValue(), "STEPS")) {
					currentRowCount = i + 1;
					stepsColumn = j;
					i = 10;
					break;
				}
			}
		}
		for (int i = 0; i < 10; i++) {// Find the "E RESULTS" or "ERESULTS" column and save column as variable
			for (int j = 0; j < 50; j++) {
				currentRow = sheet.getRow(i);
				testResultsCell = currentRow.getCell(j);
				if (testResultsCell == null) {

				} else if ((Objects.equals(testResultsCell.getStringCellValue(), "E RESULTS"))
						|| (Objects.equals(testResultsCell.getStringCellValue(), "ERESULTS"))) {
					currentRowCount = i + 1;
					expectedResultsColumn = j;
					i = 10;
					break;
				}
			}
		}
		for (int i = 0; i < 10; i++) {// Find the "IOS" column and save column as variable
			for (int j = 0; j < 50; j++) {
				currentRow = sheet.getRow(i);
				testResultsCell = currentRow.getCell(j);
				if (testResultsCell == null) {

				} else if (Objects.equals(testResultsCell.getStringCellValue(), "IOS")) {
					currentRowCount = i + 1;
					iOSColumn = j;
					i = 10;
					break;
				}
			}
		}
		for (int i = 0; i < 10; i++) {// Find the "IOS FILTER ONLY" column and save column as variable
			for (int j = 0; j < 50; j++) {
				currentRow = sheet.getRow(i);
				testResultsCell = currentRow.getCell(j);
				if (testResultsCell == null) {

				} else if (Objects.equals(testResultsCell.getStringCellValue(), "IOS FILTER ONLY")) {
					currentRowCount = i + 1;
					iOSFilterOnlyColumn = j;
					i = 10;
					break;
				}
			}
		}
		for (int i = 0; i < 10; i++) {// Find the "IOS FCVL" column and save column as variable
			for (int j = 0; j < 50; j++) {
				currentRow = sheet.getRow(i);
				testResultsCell = currentRow.getCell(j);
				if (testResultsCell == null) {

				} else if (Objects.equals(testResultsCell.getStringCellValue(), "IOS FCVL")) {
					currentRowCount = i + 1;
					iOSFCVLColumn = j;
					i = 10;
					break;
				}
			}
		}
		for (int i = 0; i < 10; i++) {// Find the "ANDROID" column and save column as variable
			for (int j = 0; j < 50; j++) {
				currentRow = sheet.getRow(i);
				testResultsCell = currentRow.getCell(j);
				if (testResultsCell == null) {

				} else if (Objects.equals(testResultsCell.getStringCellValue(), "ANDROID")) {
					currentRowCount = i + 1;
					androidColumn = j;
					i = 10;
					break;
				}
			}
		}
		for (int i = 0; i < 10; i++) {// Find the "ANDROID FCVL" column and save column as variable
			for (int j = 0; j < 50; j++) {
				currentRow = sheet.getRow(i);
				testResultsCell = currentRow.getCell(j);
				if (testResultsCell == null) {

				} else if (Objects.equals(testResultsCell.getStringCellValue(), "ANDROID FCVL")) {
					currentRowCount = i + 1;
					androidFCVLColumn = j;
					i = 10;
					break;
				}
			}
		}
		for (int i = 0; i < 10; i++) {// Find the "COMMENT" column and save column as variable
			for (int j = 0; j < 50; j++) {
				currentRow = sheet.getRow(i);
				testResultsCell = currentRow.getCell(j);
				if (testResultsCell == null) {

				} else if ((Objects.equals(testResultsCell.getStringCellValue(), "COMMENT"))
						|| (Objects.equals(testResultsCell.getStringCellValue(), "COMMENTS"))) {
					currentRowCount = i + 1;
					commentColumn = j;
					i = 10;
					break;
				}
			}
		}
		fileIn.close();
	}

	public static void setUpStepsAndExpectedResults()
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream fileIn = new FileInputStream(testSuite);
		Workbook workbook = WorkbookFactory.create(fileIn);

		Sheet sheet = workbook.getSheetAt(0);

		Row currentRow = null;
		Cell stepsCell = null;
		Cell expectedResultsCell = null;

		for (int i = currentRowCount; i < 10000; i++) {
			currentRow = sheet.getRow(i);
			if (currentRow == null) {
				break;
			} else {
				stepsCell = currentRow.getCell(stepsColumn);
				expectedResultsCell = currentRow.getCell(expectedResultsColumn);

				if (stepsCell != null) {// Add steps from the test suite into the Steps List
					String tempString = stepsCell.getStringCellValue();
					int count = 0;
					for (int j = 0; j < tempString.length(); j++) {
						if (count < 5) {

							if (Character.toString(tempString.charAt(j)) == ".") {// ||\s||[.])//a number or space or
																					// period )
								tempString.charAt(j); // replace with nothing
							} else {
								count = 10;
							}
						} else {
							tempString.indexOf(j);
						}
						count++;
					}
					RunAutomation.stepsList.add(tempString);
				}

				if (expectedResultsCell != null) {// Add expected results from the test suite into the Steps List
					RunAutomation.expectedResultsList.add(expectedResultsCell.getStringCellValue());
				}
			}
		}
	}

	public static void writeResults(Boolean testResult)// Writes the actual results out
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		FileInputStream fileIn = new FileInputStream(testSuite);
		Workbook workbook = WorkbookFactory.create(fileIn);
		Sheet sheet = workbook.getSheetAt(0);
		Row currentRow = sheet.getRow(currentRowCount);

		int platformColumn = 0;

		switch (RunAutomation.deviceType) {
		case 1:
			platformColumn = iOSColumn;
			break;
		case 2:
			platformColumn = iOSFilterOnlyColumn;
			break;
		case 3:
			platformColumn = iOSFCVLColumn;
			break;
		case 4:
			platformColumn = androidColumn;
			break;
		case 5:
			platformColumn = androidFCVLColumn;
			break;
		default:
			break;
		}

		Cell testResultsCell = currentRow.createCell(platformColumn);
		Cell commentsCell = currentRow.createCell(commentColumn);

		FileOutputStream fileOut = new FileOutputStream(new File(testSuite));// File path of the test Suite to be
																				// written to

		fileIn.close();

		if (testResult == true) {// This Sets the value to be written to the results Cell
			testResultsCell.setCellValue("P-A");
		} else if (testResult == false) {
			testResultsCell.setCellValue("F-A");
		} else {
			System.out.println("Something went wrong");
		}

		if (RunAutomation.comment == null) {// Determines if a comment needs to be added

		} else {
			commentsCell.setCellValue(RunAutomation.comment);
			RunAutomation.comment = null;
		}

		workbook.write(fileOut);// Writes whatever changes were made to the actual file
		fileOut.flush();
		fileOut.close();
		currentRowCount = currentRowCount + 1;
	}
}