package com.familyzone.qa.automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Objects;
import static java.util.stream.Stream.of;
import static java.util.stream.Collectors.toCollection;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class SetUpTestData {

	static String excelIncrementFilePath = "C:\\Users\\fzadmin\\Desktop\\New folder\\FileIncrementDB.xlsx";
	static String excelTestSuiteFilePath = "C:\\Users\\fzadmin\\Desktop\\New folder\\SocialAppVerdictTesting.xlsx";
	static String excelUseInTestingFilePath = "C:\\Users\\fzadmin\\Desktop\\New folder\\Use in Testing\\";

	static String testSuiteName = "SocialAppVerdictTesting";

	public static ArrayList<ArrayList<ArrayList<String>>> fetchControlsTestData(String controlsTestData)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream fileIn = new FileInputStream(controlsTestData);
		Workbook workbook = WorkbookFactory.create(fileIn);

		ArrayList<String> columns = new ArrayList<String>();
		columns.add("A");
		columns.add("B");
		columns.add("C");
		columns.add("D");
		columns.add("E");

		ArrayList<ArrayList<ArrayList<String>>> listListList = new ArrayList<ArrayList<ArrayList<String>>>();

		for (int i = 1; i < 6; i++) {
			Sheet sheet = workbook.getSheetAt(i);
			ArrayList<ArrayList<String>> listList = new ArrayList<ArrayList<String>>();
			for (int j = 1; j < columns.size() + 1; j++) {

				Cell cell = null;
				int termination = 2;
				ArrayList<String> tempList = new ArrayList<String>();

				for (Row row : sheet) {
					String counter = Integer.toString(termination);
					String column = columns.get(j - 1) + counter;
					CellReference cellReference = new CellReference(column);
					Row nRow = sheet.getRow(cellReference.getRow());
					cell = nRow.getCell(cellReference.getCol());
					if ((Objects.equals(cell.getStringCellValue(), "END") == false
							&& cell.getStringCellValue() != null)) {
						tempList.add(cell.getStringCellValue());
					} else if (Objects.equals(cell.getStringCellValue(), "END") == true) {
						break;
					} else {
						termination = termination + 500;
					}
					termination++;
				}
				listList.add(tempList);
			}
			listListList.add(listList);
		}
		fileIn.close();
		return listListList;
	}

	public static ArrayList<ArrayList<String>> fetchTestData(String verdictTestData,
			ArrayList<String> controlCategories)
			// Takes a file that has all the
			// URLS in it and loads the URLS
			// into lists for the different
			// access periods and allow/block
			// settings

			// I need to make it so this also loads in the categories for each URL and loads
			// each URL for each category into its own list
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		FileInputStream fileIn = new FileInputStream(verdictTestData);
		Workbook workbook = WorkbookFactory.create(fileIn);
		Sheet sheet = workbook.getSheetAt(0);

		ArrayList<String> columns = new ArrayList<String>();
		columns.add("A");
		columns.add("B");
		columns.add("C");
		columns.add("D");
		columns.add("E");
		columns.add("F");
		columns.add("G");
		columns.add("H");

		ArrayList<ArrayList<String>> allUrls = new ArrayList<ArrayList<String>>();

		int rowCount = 1;
		for (Row row : sheet) {
			rowCount++;
			String counter = Integer.toString(rowCount);
			ArrayList<String> tempList = new ArrayList<String>();

			String categoryColumn = columns.get(0) + counter;
			CellReference categoryCellReference = new CellReference(categoryColumn);
			Row categoryRow = sheet.getRow(categoryCellReference.getRow());
			// System.out.println(nRow);
			Cell categoryCell = categoryRow.getCell(categoryCellReference.getCol());

			if (controlCategories.contains(categoryCell.getStringCellValue()) == true) {
				tempList.add(categoryCell.getStringCellValue());
				for (int i = 1; i < columns.size(); i++) {
					String urlColumn = columns.get(i) + counter;
					CellReference urlCellReference = new CellReference(urlColumn);
					Row urlRow = sheet.getRow(urlCellReference.getRow());
					// System.out.println(nRow);
					Cell urlCell = urlRow.getCell(urlCellReference.getCol());

					if (urlCell.getStringCellValue() != "") {
						tempList.add(urlCell.getStringCellValue());
					}
				}
			} else if (controlCategories.contains(categoryCell.getStringCellValue()) == false) {
				if (Objects.equals(categoryCell.getStringCellValue(), "END") == false) {
					System.out.println("This Category was not included: " + categoryCell.getStringCellValue());
				} else if (Objects.equals(categoryCell.getStringCellValue(), "END") == true) {
					break;
				}
			} else {
				System.out.println("Something is broken");
			}
			if (tempList != null) {
				allUrls.add(tempList);
			} else {
				System.out.println("The tempList is null");
			}
		}
		fileIn.close();
		return allUrls;
	}

	public static ArrayList<ArrayList<String>> setUpUrlVerdictTesting(String testSuite, String verdictTestData,
			String controlsTestData, String headerRow)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		ArrayList<ArrayList<ArrayList<String>>> listListList = new ArrayList<ArrayList<ArrayList<String>>>();
		listListList = fetchControlsTestData(controlsTestData);
		ArrayList<String> controlCategories = listListList.get(0).get(0);
		ArrayList<ArrayList<String>> allUrls = fetchTestData(verdictTestData, controlCategories);

		ArrayList<String> headers = of("FLAG", "TYPE", "TC #", "PRIORITY", "LARGE", "MEDIUM", "SMALL", "STEPS",
				"E RESULTS", "TESTDATA", "ANDROID", "VERSION", "TESTERA", "DATE", "IOS", "VERSION", "TESTEI", "DATE",
				"JIRA LINK", "PRE JIRA LINK", "COMMENTS").collect(toCollection(ArrayList::new));

		ArrayList<String> columns = of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
				"Q", "R", "S", "T", "U").collect(toCollection(ArrayList::new));

		String newline = System.getProperty("line.separator");
		String steps = "1. Launch Chrome \n2. Open Incagnito Tab \n3. Go to: ";

		ArrayList<String> allTestInfo = of("A", "AUTO", "C", "3", "E", "F", "G", steps, "I", "J", "K", "L", "M", "N",
				"O", "P", "Q", "R", "S", "T", "U").collect(toCollection(ArrayList::new));

		FileInputStream fileIn = new FileInputStream(testSuite);
		Workbook workbook = WorkbookFactory.create(fileIn);
		Sheet sheet = workbook.getSheetAt(0);

		System.out.println("failed here1");

		// This Sets up headers for each column
		for (int i = 0; i < columns.size(); i++) {
			System.out.println("failed here2");
			String column = columns.get(i) + headerRow;
			System.out.println(column);
			CellReference cellReference = new CellReference(column);
			Row nRow;
			if (sheet.getRow(cellReference.getRow()) == null) {
				nRow = sheet.createRow(cellReference.getRow());
			} else {
				nRow = sheet.getRow(cellReference.getRow());
			}
			System.out.println("failed here3");
			Cell cell = nRow.createCell(cellReference.getCol());
			System.out.println("failed here4");
			cell.setCellType(CellType.STRING);
			System.out.println("failed here5");
			cell.setCellValue(headers.get(i));
		}

		// This sets up the Test Suite body (Type, Priority, Steps etc.)
		int currentRow = Integer.parseInt(headerRow);
		for (int i = 0; i < allUrls.size(); i++) {
			ArrayList<String> tempList = new ArrayList<String>();
			tempList = allUrls.get(i);

			for (int j = 1; j < tempList.size(); j++) {
				currentRow++;

				for (int k = 0; k < columns.size(); k++) {

					String column;
					CellReference cellReference;
					Row row;
					Cell cell;

					switch (columns.get(k)) {
					case "A":
						break;
					case "B":
						column = columns.get(k) + Integer.toString(currentRow);
						cellReference = new CellReference(column);
						if (sheet.getRow(cellReference.getRow()) == null) {
							row = sheet.createRow(cellReference.getRow());
						} else {
							row = sheet.getRow(cellReference.getRow());
						}
						cell = row.createCell(cellReference.getCol());
						cell.setCellType(CellType.STRING);
						cell.setCellValue(allTestInfo.get(k));
						break;
					case "C":
						break;
					case "D":
						column = columns.get(k) + Integer.toString(currentRow);
						cellReference = new CellReference(column);
						if (sheet.getRow(cellReference.getRow()) == null) {
							row = sheet.createRow(cellReference.getRow());
						} else {
							row = sheet.getRow(cellReference.getRow());
						}
						cell = row.createCell(cellReference.getCol());
						cell.setCellType(CellType.STRING);
						cell.setCellValue(allTestInfo.get(k));
						break;
					case "E":
						column = columns.get(k) + Integer.toString(currentRow);
						cellReference = new CellReference(column);
						if (sheet.getRow(cellReference.getRow()) == null) {
							row = sheet.createRow(cellReference.getRow());
						} else {
							row = sheet.getRow(cellReference.getRow());
						}
						cell = row.createCell(cellReference.getCol());
						cell.setCellType(CellType.STRING);
						cell.setCellValue("Controls");
						break;
					case "F":
						column = columns.get(k) + Integer.toString(currentRow);
						cellReference = new CellReference(column);
						if (sheet.getRow(cellReference.getRow()) == null) {
							row = sheet.createRow(cellReference.getRow());
						} else {
							row = sheet.getRow(cellReference.getRow());
						}
						cell = row.createCell(cellReference.getCol());
						cell.setCellType(CellType.STRING);
						cell.setCellValue(tempList.get(0));
						break;
					case "G":
						column = columns.get(k) + Integer.toString(currentRow);
						cellReference = new CellReference(column);
						if (sheet.getRow(cellReference.getRow()) == null) {
							row = sheet.createRow(cellReference.getRow());
						} else {
							row = sheet.getRow(cellReference.getRow());
						}
						cell = row.createCell(cellReference.getCol());
						cell.setCellType(CellType.STRING);
						cell.setCellValue("Block - Allow");
						break;
					case "H":
						column = columns.get(k) + Integer.toString(currentRow);
						cellReference = new CellReference(column);
						if (sheet.getRow(cellReference.getRow()) == null) {
							row = sheet.createRow(cellReference.getRow());
						} else {
							row = sheet.getRow(cellReference.getRow());
						}
						cell = row.createCell(cellReference.getCol());
						cell.setCellType(CellType.STRING);
						CellStyle cs = workbook.createCellStyle();
						cs.setWrapText(true);
						cell.setCellStyle(cs);
						cell.setCellValue(allTestInfo.get(k) + tempList.get(j));
						break;
					case "I":
						break;
					case "J":
						break;
					case "K":
						break;
					case "L":
						break;
					case "M":
						break;
					case "N":
						break;
					case "O":
						break;
					case "P":
						break;
					case "Q":
						break;
					case "R":
						break;
					case "S":
						break;
					case "T":
						break;
					case "U":
						break;
					default:
						break;
					}
				}
			}
		}

		FileOutputStream fileOut = new FileOutputStream(testSuite); // Writes whatever changes were made to the actual
																	// file

		workbook.write(fileOut);

		for (int i = 0; i < columns.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		fileOut.close();
		workbook.close();
		fileIn.close();

		return allUrls;
	}

	public static String copyRenameFile(String testSuit)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		double increment;

		FileInputStream fileIn = new FileInputStream(excelIncrementFilePath);
		Workbook workbook = WorkbookFactory.create(fileIn);

		Sheet sheet = workbook.getSheetAt(0);

		Row fileNameRow;
		Cell fileNameCell;

		Row incrementRow;
		Cell incrementCell;

		for (int i = 1; i < 300; i++) {// Increments throughout the Excel file looking for a cell that contains the
										// name of the test suite

			fileNameRow = sheet.getRow(i);
			fileNameCell = fileNameRow.getCell(0);
			if (Objects.equals(fileNameCell.getStringCellValue(), "END")) {
				break;
			} else {
				fileNameCell = fileNameRow.getCell(0);
			}

			if (Objects.equals(fileNameCell.getStringCellValue(), testSuiteName)) {// if the name is found then finds
																					// what the current increment is for
																					// that file
				incrementRow = sheet.getRow(i); // Reference to row 5
				incrementCell = incrementRow.getCell(1); // Reference to cell B5
				increment = incrementCell.getNumericCellValue();
				fileIn.close();

				FileOutputStream fileOut = new FileOutputStream(new File(excelIncrementFilePath));// File path of the
																									// file that is
																									// going to be
																									// written to
				incrementCell.setCellValue(increment + 1.0);
				System.out.println("increment " + increment);
				workbook.write(fileOut);// Writes whatever changes were made to the file to the actual file
				fileOut.flush();
				fileOut.close();

				String incrementString = Integer.toString((int) increment);
				String source = excelTestSuiteFilePath;// Is the destination of the File that is going to be copied
				String dest = excelUseInTestingFilePath + testSuiteName + incrementString + ".xlsx";// Adds the
																									// increment to the
																									// file name and
																									// points to the
																									// destination
																									// folder

				testSuit = excelUseInTestingFilePath + testSuiteName + incrementString + ".xlsx";
				System.out.println(dest);
				FileChannel sourceChannel = null;
				FileChannel destChannel = null;
				try {
					sourceChannel = new FileInputStream(source).getChannel();
					destChannel = new FileOutputStream(dest).getChannel();
					destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
				} finally {
					sourceChannel.close();
					destChannel.close();
				}
				break;
			}
		}
		return testSuit;
	}
}