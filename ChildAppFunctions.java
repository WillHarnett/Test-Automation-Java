package com.familyzone.qa.automation;

import com.experitest.client.*;
import java.net.MalformedURLException; //Redundant
import java.util.ArrayList;
import org.testng.asserts.SoftAssert; //TODO
import org.junit.Before;
import org.testng.asserts.Assertion;  //TODO


public class ChildAppFunctions {	
	private static String host = "localhost";
	private static int port = 8889;
	protected static Client client = null;
	
	@Before
	public static void openChildApp( ) {
		String deviceName = FunctionLibrary.deviceName;
		client = new Client(host, port, true);
		client.setDevice("adb:Pixel");
        client.launch("au.com.familyzone/.activities.SplashActivity", false, true);
	}
	
	public static Boolean requestBorrow(Boolean BorrowRequestSent, String userA, String userB, int borrowRequestAttempts) {
		client.click("NATIVE", userA, 0, 1);
        client.click("NATIVE", userB, 0, 1);
        client.click("NATIVE", "xpath=//*[@text='BORROW']", 0, 1);
        if(client.waitForElement("NATIVE", "xpath=//*[@text='Successfully raised borrow request.']", 0, 30000)){
        	BorrowRequestSent = true;
        }
        else {
        	BorrowRequestSent = false;
        }
		return BorrowRequestSent;
	}
}
