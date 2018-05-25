package com.familyzone.qa.automation;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import com.experitest.client.Client;

import org.junit.*;

public class RunAutomation {

	static int deviceType = 1;// See below for device Type options --- If this is not filled in correctly the
								// results will be recorded in the wrong place
	// 1 = iOS
	// 2 = iOS Filter Only
	// 3 = iOS FCVL
	// 4 = Android
	// 5 = Android FCVL

	static String headerRow = Integer.toString(2);
	static String testSuite = "C:\\Users\\fzadmin\\Desktop\\New folder\\Use in Testing\\VerdictTestingMobile.xlsx";
	static String verdictTestData = "C:\\Users\\fzadmin\\Desktop\\New folder\\TestData\\verdictTestData.xlsx";
	static String controlsTestData = "C:\\Users\\fzadmin\\Desktop\\New folder\\TestData\\Categories and CE Settings.xlsx";

	static String comment;
	static String currentStep;

	static ArrayList<String> stepsList = new ArrayList<String>();
	static ArrayList<String> expectedResultsList = new ArrayList<String>();
	// need more Variables defined: allowed, loggedIn, deviceName

	private static String host = "localhost";
	private static int port = 8889;
	protected static Client client = null;
	String deviceName = "adb:Pixel";

	public void main(String args[]) throws EncryptedDocumentException, InvalidFormatException, IOException {
		Run();
	}

	@Test
	public void Run() throws EncryptedDocumentException, InvalidFormatException, IOException {

		SetupDevice();

		FunctionLibrary FunctionLibrary = new FunctionLibrary();

		testSuite = SetUpTestData.copyRenameFile(testSuite);
		// SetUpTestData.setUpUrlVerdictTesting(testSuite, verdictTestData,
		// controlsTestData, headerRow);

		ReadAndWriteToTestSuite.setUpVariables();
		ReadAndWriteToTestSuite.setUpStepsAndExpectedResults();

		Boolean result = true;

		System.out.println(stepsList.size());

		for (int i = 0; i < stepsList.size(); i++) {
			System.out.println(stepsList.get(i));
			currentStep = stepsList.get(i);
			FunctionLibrary.findAndRunAppVerdictFunctions();
			ReadAndWriteToTestSuite.writeResults(result);
		}
	}

	public void SetupDevice() {
		client = new Client(host, port, true);
		client.setDevice(deviceName);
	}
}