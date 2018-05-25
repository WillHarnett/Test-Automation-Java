package com.familyzone.qa.automation;

import com.experitest.client.*;
import java.net.MalformedURLException; //Redundant
import java.util.ArrayList;
import org.testng.asserts.SoftAssert; //TODO
import org.junit.Before;
import org.testng.asserts.Assertion; //TODO

public class ChildAppFunctions {
	static Client client = RunAutomation.client;

	public static void openChildApp() {
		client.launch("au.com.familyzone/.activities.SplashActivity", false, true);
	}

	public static Boolean requestBorrow(String userA, String userB) {
		client.click("NATIVE", userA, 0, 1);
		client.click("NATIVE", userB, 0, 1);
		client.click("NATIVE", "xpath=//*[@text='BORROW']", 0, 1);
		if (client.waitForElement("NATIVE", "xpath=//*[@text='Successfully raised borrow request.']", 0, 30000)) {
			return true;
		} else if (client.waitForElement("NATIVE", "xpath=//*[@text='Borrow request failed. Please try again later.']",
				0, 30000)) {
			System.out.println("Borrow Request Failed, Trying again...");
			return false;
		} else {
			System.out.println("The Borrow Request Completely failed");
			return false;
		}
	}
}
