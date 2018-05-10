package test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class KeyWords {

	static Boolean loggedIn = true;
	static Boolean allowed = false;
	String appName = "snapchat";

	static String FamilyZoneHomePage = "https://www.familyzone.com/au/";

	static ArrayList<String> stepsKeyWordList = new ArrayList<String>();
	static ArrayList<String> eResultsKeyWordList = new ArrayList<String>();
	static ArrayList<String> testDataKeyWordList = new ArrayList<String>();

	static String userName = "put correct username here";
	static String userPassword = "put correct password here";

	static String wrongUserName = "asdf";
	static String wrongUserPassword = "asdf";

	public static void KeyWord()
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		int numberOfTestCases = FileReader.fireFoxTestResults.size();

		System.out.println("total length of TC: " + RunAutomation.stepsList.size());
		System.out.println("total length of TC: " + numberOfTestCases);

		for (int i = 0; i < numberOfTestCases; i++) {
			ArrayList<String> temp = RunAutomation.stepsList.get(i);

			StepsKeyWords(temp, allowed);
			ResultsKeyWords(RunAutomation.eResutlsList.get(i), FileReader.fireFoxTestResults.get(i),
					FileReader.tester.get(i), FileReader.date.get(i));
		}
	}

	public static void StepsKeyWords(ArrayList<String> keyWordList, final Boolean loggenIn) throws InterruptedException, MalformedURLException {
		Boolean allowed = null;
		String appName = null;
		String appPassword = null;
		String appUsername = null;
		for (int i = 0; i < testDataKeyWordList.size(); i++) {
			String testData = testDataKeyWordList.get(i);
			switch (testData) {
			case "1":
				break;
			case "2":
				break;
			}
		}

		for (int i = 0; i < eResultsKeyWordList.size(); i++) {
			String eResults = eResultsKeyWordList.get(i);
			switch (eResults) {
			case "blocked":
				allowed = false;
				break;
			case "allowed":
				allowed = true;
				break;
			}
		}

		for (int i = 0; i < stepsKeyWordList.size(); i++) {
			String stepsKeyWord = stepsKeyWordList.get(i);
			switch (stepsKeyWord) {
			case "launch":
				RunApp app = new RunApp();
				appName = keyWordList.get(i + 1);
				app.openApp(appName, loggedIn, allowed);
				break;
			default:
				break;
			
			}
			System.out.println("StepKeyWord: " + stepsKeyWord);
			System.out.println("StepKeyWord: " + stepsKeyWordList.size() + " " + i);
		}
	}

	public static void ResultsKeyWords(String keyWord, String resultsColumnRow, String testerColumnRow,
			String dateColumnRow)
			throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {

		String FamilyZoneHomePage = "https://www.familyzone.com/au/";
		String myFamilyURL = "https://portal.familyzone.com/#myFamily";
		String signInURL = "https://portal.familyzone.com/#login";

		boolean results = false;

		switch (keyWord) {
		case "HOMEPAGEISDISPLAYED":
			results = RunFunction.CompareURL(FamilyZoneHomePage);
			break;
		case "MYFAMILYPAGEISDISPLAYED":
			results = RunFunction.CompareURL(myFamilyURL);
			break;
		case "SIGNINPAGEISDISPLAYED":
			results = RunFunction.CompareURL(signInURL);
			break;
		}

		FileReader.PrintResults(results, resultsColumnRow, testerColumnRow, dateColumnRow);
		System.out.println("ResultKeyWord: " + keyWord);
	}

	public static ArrayList<String> CreateStepsKeyWordList() {
		stepsKeyWordList.add("GOTOFAMILYZONEHOMEPAGE");
		stepsKeyWordList.add("CLICKBACKBUTTON");
		stepsKeyWordList.add("CLICKFORWARDBUTTON");
		stepsKeyWordList.add("CLICKONSIGNINHOMEICON");
		stepsKeyWordList.add("CLICKONSIGNINNAME");
		stepsKeyWordList.add("CLICKONLINKMYACCOUNT");
		stepsKeyWordList.add("CLICKONSIGNIN");
		stepsKeyWordList.add("INPUTSIGNINEMAIL");
		stepsKeyWordList.add("INPUTSIGNINPASSWORD");
		stepsKeyWordList.add("INPUTWRONGSIGNINEMAIL");
		stepsKeyWordList.add("INPUTWRONGSIGNINPASSWORD");
		// stepsKeyWordList.add();
		return stepsKeyWordList;
	}

	public static ArrayList<String> CreateeResultsKeyWordList() {
		eResultsKeyWordList.add("HOMEPAGEISDISPLAYED");
		eResultsKeyWordList.add("MYFAMILYPAGEISDISPLAYED");
		eResultsKeyWordList.add("SIGNINPAGEISDISPLAYED");
		// eResultsKeyWordList.add(e);
		return eResultsKeyWordList;
	}

}
