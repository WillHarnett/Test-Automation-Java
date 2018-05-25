package com.familyzone.qa.automation;

import com.experitest.client.*;
import java.net.MalformedURLException; //Redundant
import java.util.ArrayList;
import org.junit.*;
import org.testng.asserts.SoftAssert; //TODO
import org.testng.asserts.Assertion; //TODO

public class ZoneManagerFunctions {

	static Client client = RunAutomation.client;
	static String deviceName = FunctionLibrary.deviceName;
	String stepPassed = FunctionLibrary.stepPassed;

	public static void OpenZoneManager() {
		client.launch("com.familyzone.zonemanager/.activity.PreliminaryActivity", false, true);
	}

	@SuppressWarnings("deprecation")
	public static Boolean AcceptBorrow(Boolean borrowAccepted) {
		client.click("NATIVE", "xpath=//*[@text='To do']", 0, 1, 0, 0);
		client.swipe("Up", 500);
		if (client.waitForElement("NATIVE", "xpath=//*[@text='ALLOW']", 0, 30000)) {
			client.click("NATIVE", "xpath=//*[@text='ALLOW']", 0, 1);
			client.click("NATIVE", "xpath=//*[@text='REST OF TODAY']", 0, 1, 0, 0);
			client.sleep(1500);
			return true;
		} else {
			return false;
		}

	}
}