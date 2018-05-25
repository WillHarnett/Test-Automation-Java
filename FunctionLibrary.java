package com.familyzone.qa.automation;

import java.net.MalformedURLException;

public class FunctionLibrary {

	static String appName = "null";
	static String deviceName = "adb:Pixel";
	static Boolean loggedIn = true;
	static Boolean allowed = true;
	static String stepPassed = "null";
	
	String userA = "xpath=//*[@text='AUTO_PLAY_1_B']";
	String userB = "xpath=//*[@text='AUTO_PLAY_1_A']";
	Boolean borrowRequestSent = false;
	Boolean borrowAccepted = false;

	public void findAndRunAppVerdictFunctions() throws MalformedURLException {

		RunAppSeeTest RunAppSeeTest = new RunAppSeeTest();

		String step = "borrow";// RunAutomation.currentStep;

		switch (step) {
		case "open facebook":
			appName = "facebook";
			RunAppSeeTest.openApp();
			break;
		case "open whatsapp":
			appName = "whatsapp";
			RunAppSeeTest.openApp();
			break;
		case "open 9gag":
			appName = "9gag";
			RunAppSeeTest.openApp();
			break;
		case "open ask.fm":
			appName = "ask.fm";
			RunAppSeeTest.openApp();
			break;
		case "open discord":
			appName = "discord";
			RunAppSeeTest.openApp();
			break;
		case "open imgur":
			appName = "imgur";
			RunAppSeeTest.openApp();
			break;
		case "open instagram":
			appName = "instagram";
			RunAppSeeTest.openApp();
			break;
		case "open kik messenger":
			appName = "kik messenger";
			RunAppSeeTest.openApp();
			break;
		case "open musical.ly | live.ly":
			appName = "musical.ly | live.ly";
			RunAppSeeTest.openApp();
			break;
		case "open netflix":
			appName = "netflix";
			RunAppSeeTest.openApp();
			break;
		case "open pokemon go":
			appName = "pokemon go";
			RunAppSeeTest.openApp();
			break;
		case "open reddit":
			appName = "reddit";
			RunAppSeeTest.openApp();
			break;
		case "open sarahah":
			appName = "sarahah";
			RunAppSeeTest.openApp();
			break;
		case "open skype":
			appName = "skype";
			RunAppSeeTest.openApp();
			break;
		case "open snapchat":
			appName = "snapchat";
			RunAppSeeTest.openApp();
			break;
		case "open spotify":
			appName = "spotify";
			RunAppSeeTest.openApp();
			break;
		case "open tinder":
			appName = "tinder";
			RunAppSeeTest.openApp();
			break;
		case "open tumblr":
			appName = "tumblr";
			RunAppSeeTest.openApp();
			break;
		case "open twitch tv":
			appName = "twitch tv";
			RunAppSeeTest.openApp();
			break;
		case "open twitter":
			appName = "twitter";
			RunAppSeeTest.openApp();
			break;
		case "open viber":
			appName = "viber";
			RunAppSeeTest.openApp();
			break;
		case "open youtube":
			appName = "youtube";
			RunAppSeeTest.openApp();
			break;

		// Zone Manager && Child App time boi
		case "open zone manager":
			appName = "zone manager";
			RunAppSeeTest.openApp();
			break;
		case "borrow":
			int Attempts = 0;
			while (Attempts < 10 && borrowRequestSent != true) {
				System.out.println("Made it this far 1a");
				ChildAppFunctions.openChildApp();
				System.out.println("Made it this far 2a");
				borrowRequestSent = ChildAppFunctions.requestBorrow(userA, userB);
				System.out.println("Made it this far 3a");
				Attempts++;
				System.out.println("Current Attempt " + Attempts);
			}

			Attempts = 0;
			while (Attempts < 10 && borrowAccepted != true) {
				System.out.println("Made it this far 1");
				ZoneManagerFunctions.OpenZoneManager();
				System.out.println("Made it this far 2");
				borrowAccepted = ZoneManagerFunctions.AcceptBorrow(borrowAccepted);
				System.out.println("Made it this far 3");
				Attempts++;
			}
			break;

		default:
			break;
		}
		/*
		 * if (stepPassed == null) { RunAutomation.comment = RunAutomation.comment +
		 * " Test Results are Null "; System.out.println("Test Results are Null ");
		 * stepPassed = false; }
		 * 
		 * return stepPassed;
		 */
	}
}
