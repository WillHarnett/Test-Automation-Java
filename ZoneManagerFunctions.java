package com.familyzone.qa.automation;

import com.experitest.client.*;
import java.net.MalformedURLException; //Redundant
import java.util.ArrayList;
import org.junit.*;
import org.testng.asserts.SoftAssert; //TODO
import org.testng.asserts.Assertion; //TODO

public class ZoneManagerFunctions {

	private static String host = "localhost";
	private static int port = 8889;
	protected static Client client = null;
	static String deviceName = FunctionLibrary.deviceName;
	String stepPassed = FunctionLibrary.stepPassed;

	public static void OpenZoneManager() {
		client = new Client(host, port, true);
		client.setDevice("adb:Pixel");
		client.launch("au.com.familyzone/.activities.SplashActivity", false, true);
	}

	@SuppressWarnings("deprecation")
	public static Boolean AcceptBorrow(Boolean borrowAccepted, int borrowAcceptAttemps) {
		client = new Client(host, port, true);
		client.setDevice(deviceName);
		client.click("NATIVE", "xpath=//*[@text='To do']", 0, 1, 0, 0);
		client.swipe("Up", 500);
		if (client.waitForElement("NATIVE", "xpath=//*[@text='ALLOW']", 0, 30000)) {
			borrowAccepted = true;
			client.click("NATIVE", "xpath=//*[@text='ALLOW']", 0, 1);
			client.click("NATIVE", "xpath=//*[@text='REST OF TODAY']", 0, 1, 0, 0);
			client.sleep(1500);
		}
		else {
			borrowAccepted = false;
		}
		return borrowAccepted;
		
	}
}