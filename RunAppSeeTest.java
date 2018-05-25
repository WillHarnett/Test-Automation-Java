package com.familyzone.qa.automation;

import com.experitest.client.*;
import java.net.MalformedURLException; //Redundant
import java.util.ArrayList;
import org.junit.*;
import org.testng.asserts.SoftAssert; //TODO
import org.testng.asserts.Assertion; //TODO

public class RunAppSeeTest {
	private static String host = "localhost";
	private static int port = 8889;
	protected static Client client = null;
	static Boolean hasSIMCard = true;
	static String comment = "";

	@Test
	public void openApp() {// Finds what App is being tested, if logged in and if

		Boolean allowed = FunctionLibrary.allowed;
		Boolean loggedIn = FunctionLibrary.loggedIn;
		String appName = FunctionLibrary.appName;
		String deviceName = FunctionLibrary.deviceName;
		String stepPassed = FunctionLibrary.stepPassed;
		
		client = new Client(host, port, true);
		client.setDevice(deviceName);
		// stepPassed = RunAppSeeTest.openApp(appName, deviceName, loggedIn, allowed);
		// System.out.println("This test: " + stepPassed); //TODO step passed

		final ArrayList<String> orderOfValidation = new ArrayList<String>();

		String Validation1 = "null";
		String Validation2 = "null";
		String Validation3 = "null";
		String Validation4 = "null";
		String Validation5 = "null";
		String Validation6 = "null";
		int Validation7 = 1000;
		String Validation8a = "null";
		int Validation8b = 10000;
		String didClickWork1 = "null";
		String didClickWork2 = "null";
		String didClickWork3 = "null";
		String logInStep1 = "null";
		String logInStep2 = "null";
		String logInStep3 = "null";
		String password = "null";
		String username = "null";
		String openAppValidation = "null";

		String comment = "";

		switch (appName) {// Switch for setting each test step variable for the App being tested

		case "snapchat":
			if (loggedIn == true) {
				openAppValidation = "xpath=xpath=//*[@id='nueva_nav_default_camera_button']";
				if (allowed == true) {
					Validation1 = "xpath=xpath=//*[@id='hova_nav_stories_dark']";
					Validation8a = "xpath=((//*[@id='recycler_view']/*/*[@id='frame'])[1]/*[@class='android.widget.RelativeLayout' and @width>0 and @height>0])[1]";
					Validation8b = 60000;
					Validation2 = "xpath=((//*[@id='recycler_view']/*/*[@id='frame'])[1]/*[@class='android.widget.RelativeLayout' and @width>0 and @height>0])[1]";
					didClickWork1 = "xpath=//*[@text='Discover']";
					didClickWork2 = "(xpath=//*[@class='android.widget.FrameLayout' and ./parent::*[@id='opera_viewer']]/*[@class='android.widget.FrameLayout' and @width>0 and @height>0])[4]";
					orderOfValidation.add("doAllowedValidation1");
					orderOfValidation.add("doAllowedValidation8");
					orderOfValidation.add("doAllowedValidation2");
				} else if (allowed == false) {
					Validation8a = "xpath=//*[@id='notification_content']";
					Validation8b = 60000;
					orderOfValidation.add("doBlockedValidation8");
				} else {
					System.out.println(" Snapchat is logged in but not allowed or denied");

				}
				break;
			} else if (loggedIn == false) {
				password = "FZtest12345";
				username = "FZtest";
				logInStep1 = "xpath=//*[@id='username_or_email_field']";
				logInStep2 = "xpath=//*[@id='password_field']";
				logInStep3 = "xpath=//*[@id='registration_nav_container']";
				openAppValidation = "xpath=//*[@text='LOG IN']";
				if (allowed == true) {
					Validation4 = "xpath=//*[@id='nueva_nav_default_camera_button']";
					orderOfValidation.add("doAllowedValidation4");
				} else if (allowed == false) {
					Validation4 = "xpath=//*[@class='android.widget.FrameLayout' and @width>0 and ./*[@class='android.widget.FrameLayout' and ./*[@id='content']]]";
					orderOfValidation.add("doBlockedValidation4");
				} else {
					System.out.println("\n Snapchat is not logged in but not allowed or denied");

				}
			} else {
				System.out.println("\n Snapchat isn't defined at all");

			}
			client.launch("com.snapchat.android/.LandingPageActivity", false, true);
			break;
		case "spotify":
			if (loggedIn == true) {
				openAppValidation = "xpath=//*[@text='Home']";
				if (allowed == true) {
					Validation1 = "xpath=//*[@id='icon' and (./preceding-sibling::* | ./following-sibling::*)[@text='@  Need for Speed']]";
					Validation2 = "xpath=//*[@text='SHUFFLE PLAY']";
					didClickWork1 = "xpath=//*[@text='SHUFFLE PLAY']";
					didClickWork2 = "xpath=//*[@id='mini_player_progress']";
					orderOfValidation.add("doAllowedValidation1");
					orderOfValidation.add("doAllowedValidation2");
				} else if (allowed == false) {
					Validation4 = "xpath=//*[@text='No Internet connection available']";
					orderOfValidation.add("doBlockedValidation4");
				} else {
					System.out.println("\n Spotify is logged in but not allowed or denied");
				}
				break;
			} else if (loggedIn == false) {
				password = "QATestapps";
				username = "fz.test.apps@gmail.com";
				logInStep1 = "xpath=//*[@id='username_text']";
				logInStep2 = "xpath=//*[@id='password_text']";
				logInStep3 = "xpath=//*[@id='icon' and (./preceding-sibling::* | ./following-sibling::*)[@class='android.widget.ImageView']]";
				openAppValidation = "xpath=//*[@text='LOG IN']";
				if (allowed == true) {
					orderOfValidation.add("doAllowedValidation4");
					Validation4 = "xpath=//*[@text='Recently played']";
				} else if (allowed == false) {
					orderOfValidation.add("doBlockedValidation4");
					Validation4 = "xpath=//*[@text=concat('You', \"'\", 're offline')]";
				} else {
					System.out.println("\n Spotify is not logged in but not allowed or denied");

				}
			} else {
				System.out.println("\n Spotify isn't defined at all");

			}
			client.launch("com.spotify.music/.MainActivity", false, true);
			break;
		case "whatsapp":
			if (RunAppSeeTest.hasSIMCard == false) {
				comment = "SIM Not installed skipped test step";
			} else {
				if (loggedIn == true) {
					openAppValidation = "xpath=//*[@id='contact_row_container' and ./*[./*[@contentDescription='Ross']]]";
					if (allowed == true) {
						Validation1 = "xpath=//*[@id='fab']";
						didClickWork1 = "xpath=//*[@text='Select contact']";
						Validation3 = "xpath=//*[@class='android.widget.LinearLayout' and @width>0 and ./*[@class='android.widget.LinearLayout' and ./*[@class='android.widget.FrameLayout' and ./*[@text='Ross']]]]";
						Validation6 = "automated message no need to reply";
						Validation2 = "xpath=//*[@id='send']";
						didClickWork2 = "xpath=//*[@id='voice_note_btn']";
						Validation4 = "xpath=//*[@id='status']";
						orderOfValidation.add("doAllowedValidation1");
						orderOfValidation.add("doAllowedValidation2");
						orderOfValidation.add("doAllowedValidation6");
						orderOfValidation.add("doAllowedValidation2");
						orderOfValidation.add("doAllowedValidation4");
					} else if (allowed == false) {
						Validation1 = "xpath=//*[@id='fab']";
						Validation3 = "xpath=//*[@class='android.widget.LinearLayout' and @width>0 and ./*[@class='android.widget.LinearLayout' and ./*[@class='android.widget.FrameLayout' and ./*[@text='Ross']]]]";
						Validation6 = "automated message no need to reply";
						Validation2 = "xpath=//*[@id='send']";
						Validation4 = "xpath=//*[@contentDescription='Pending']";
						orderOfValidation.add("doBlockedValidation1");
						orderOfValidation.add("doBlockedValidation3");
						orderOfValidation.add("doBlockedValidation6");
						orderOfValidation.add("doBlockedValidation2");
						orderOfValidation.add("doBlockedValidation4");
					} else {
						System.out.println("\n Whatsapp is logged in but not allowed or denied");

					}
				} else if (loggedIn == false) {
					System.out.println("need more time to code this test"); // TODO

					if (allowed == true) {
						System.out.println("need more time to code this test"); // TODO

					} else if (allowed == false) {
						System.out.println("need more time to code this test"); // TODO

					} else {
						System.out.println(" Whatsapp is not logged in but not allowed or denied");

					}
				} else {
					System.out.println(" Whatsapp isn't defined at all");

				}
				client.launch("com.whatsapp/.Main", false, true);
			}
			break;
		case "instagram":
			if (loggedIn == true) {
				openAppValidation = "xpath=//*[@id='tab_icon' and ./parent::*[@contentDescription='Home']]";
				if (allowed == true) {
					Validation1 = "xpath=//*[@contentDescription='Search and explore']";
					Validation2 = "xpath=//*[@id='pulse_emitter']";
					Validation4 = "xpath=//*[@id='action_bar_shadow']";
					didClickWork1 = "xpath=//*[@id='action_bar_search_edit_text']";
					didClickWork2 = "xpath=//*[@text='Top live videos']";
					orderOfValidation.add("doAllowedValidation1");
					orderOfValidation.add("doAllowedValidation2");
					orderOfValidation.add("doAllowedValidation4");
				} else if (allowed == false) {
					Validation1 = "xpath=//*[@id='tab_icon' and ./parent::*[@contentDescription='Home']]";
					Validation5 = "Down";
					Validation4 = "xpath=//*[@class='android.widget.ImageView' and ./parent::*[@id='row_load_more_button']]";
					didClickWork1 = "xpath=//*[@id='action_bar_left_button']";
					orderOfValidation.add("doBlockedValidation1");
					orderOfValidation.add("doBlockedValidation5");
					orderOfValidation.add("doBlockedValidation4");
				} else {
					System.out.println("\n Instagram is logged in but not allowed or denied");

				}
			} else if (loggedIn == false) {
				password = "QATestapps";
				username = "fz.test.apps@gmail.com";
				logInStep1 = "xpath=//*[@id='login_username']";
				logInStep2 = "xpath=//*[@id='password']";
				logInStep3 = "xpath=//*[@text='Log In']";
				openAppValidation = "xpath=//*[@text='Already have an account? Log in.']";
				if (allowed == true) {
					Validation4 = "xpath=//*[@id='tab_icon' and ./parent::*[@contentDescription='Home']]";
					orderOfValidation.add("doAllowedValidation4");
				} else if (allowed == false) {
					Validation4 = "xpath=//*[@text='An unknown network error has occurred.']";
					orderOfValidation.add("doBlockedValidation4");
				} else {
					System.out.println("Instagram is not logged in but not allowed or denied");

				}
			} else {
				System.out.println("Instagram isn't defined at all");

			}
			client.launch("com.instagram.android/.activity.MainTabActivity", false, true);
		case "facebook":
			if (loggedIn == true) {
				openAppValidation = "xpath=//*[@contentDescription='Selected, News Feed, Tab 1 of 5']";
				if (allowed == true) {
					Validation1 = "xpath=//*[@contentDescription='Selected, News Feed, Tab 1 of 5']";
					Validation4 = "xpath=//*[@text='What are you listing?']]";
					didClickWork1 = "xpath=//*[@contentDescription='Selected, Marketplace, Tab 3 of 5']";
					orderOfValidation.add("doAllowedValidation1");
					orderOfValidation.add("doAllowedValidation4");
				} else if (allowed == false) {
					Validation1 = "xpath=//*[@contentDescription='Selected, News Feed, Tab 1 of 5']";
					didClickWork1 = "xpath=//*[@contentDescription='Selected, Marketplace, Tab 3 of 5']";
					Validation4 = "xpath=//*[@text='Something went wrong']";
					orderOfValidation.add("doBlockedValidation1");
					orderOfValidation.add("doBlockedValidation4");
				} else {
					System.out.println("Facebook is logged in but not allowed or denied");

				}
			} else if (loggedIn == false) {
				password = "FZtestapp!";
				username = "FZ Tester";
				logInStep1 = "((xpath=//*[@id='(name removed)' and @class='android.widget.LinearLayout']/*[@id='(name removed)' and @class='android.widget.LinearLayout'])[1]/*/*[@class='android.widget.EditText'])[1]";
				logInStep2 = "((xpath=//*[@id='(name removed)' and @class='android.widget.LinearLayout']/*[@id='(name removed)' and @class='android.widget.LinearLayout'])[1]/*/*[@class='android.widget.EditText'])[2]";
				logInStep3 = "xpath=//*[@text='LOG IN']";
				openAppValidation = "xpath=//*[@text='Log into another account']";
				if (allowed == true) {
					Validation4 = "xpath=//*[@class='android.view.ViewGroup' and @width>0 and @height>0 and ./*[@contentDescription='Go to profile']]";
					orderOfValidation.add("doAllowedValidation4");
				} else if (allowed == false) {
					Validation4 = "xpath=//*[@class='android.widget.FrameLayout' and @width>0 and ./*[@class='android.widget.FrameLayout' and ./*[@id='content']]]";
					orderOfValidation.add("doBlockedValidation4");
				} else {
					System.out.println("Facebook is not logged in but not allowed or denied");

				}
			} else {
				System.out.println("Facebook isn't defined at all");
			}
			client.launch("com.facebook.katana/.LoginActivity", false, true);
		case "zone manager":
			if (loggedIn == true) {
				openAppValidation = "xpath=//*[@id='family']";
				if (allowed == true) {
					Validation1 = "";
					Validation4 = "";
					didClickWork1 = "";
					orderOfValidation.add("");
					orderOfValidation.add("");
				} else if (allowed == false) {
					Validation1 = "";
					didClickWork1 = "";
					Validation4 = "";
					orderOfValidation.add("");
					orderOfValidation.add("");
				} else {
					System.out.println("Zone Manager is logged in but something else is wrong...");

				}
			} else if (loggedIn == false) {
				password = "uE%1uNp2vFa%BkJSKe4^";
				username = "automation@familyzone.com";
				logInStep1 = "xpath=//*[@id='username']";
				logInStep2 = "xpath=//*[@id='password']";
				logInStep3 = "xpath=//*[@text='SIGN IN']";
				openAppValidation = "xpath=//*[@text='SIGN IN']";
				if (allowed == true) {
					Validation4 = "";
					orderOfValidation.add("");
				} else if (allowed == false) {
					Validation4 = "";
					orderOfValidation.add("");
				} else {
					System.out.println("Zone Manager is not logged in but something else is wrong...");

				}
			} else {
				System.out.println("Zone Manager isn't defined at all");
			}
			client.launch("com.familyzone.zonemanager/.activity.PreliminaryActivity", false, true);
		default:
			System.out.println("App Not Defined! Something very wrong");

		}

		/**
		 * After all the variables have been set we can now move on to executing the
		 * tests on the device based on if it's allowed and logged in
		 **/

		String testCasePassed1 = "null";
		String testCasePassed2 = "null";
		String testCasePassed3 = "null";
		String testCasePassed4 = "null";
		String testCasePassed5 = "null";
		String testCasePassed6 = "null";
		String testCasePassed7 = "null";
		String testCasePassed8 = "null";

		if (loggedIn == true) {
			client.waitForElement("NATIVE", openAppValidation, 0, 10000);
			if (allowed == true) {
				for (int j = 0; j < orderOfValidation.size(); j++) {
					switch (orderOfValidation.get(j)) {
					case "doAllowedValidation1":
						// if (client.verifyElementFound("NATIVE", allowedValidation1, 0)) {
						client.click("NATIVE", Validation1, 0, 1);
						testCasePassed1 = "pass";
						// }

						break;
					case "doAllowedValidation2":
						// if (client.findElement(By.xpath(allowedValidation2)) != null) {
						client.click("NATIVE", Validation2, 0, 1);
						// if (client.findElement(By.xpath(didClickWork2)) != null) {
						testCasePassed2 = "pass";
						// }
						// } else {
						// testCasePassed2 = "fail";
						// }

						break;
					case "doAllowedValidation3":
						// if (client.findElement(By.xpath(allowedValidation3)) != null) {
						client.click("NATIVE", Validation3, 0, 1);
						// if (client.findElement(By.xpath(didClickWork3)) != null) {
						testCasePassed3 = "pass";
						// }
						// } else {
						// testCasePassed3 = "fail";
						// }

					case "doAllowedValidation4":
						// if (client.waitForElement("NATIVE", allowedValidation4, 0, 10000)) != null) {
						client.waitForElement("NATIVE", Validation4, 0, 10000);
						testCasePassed4 = "pass";
						// } else {
						// testCasePassed4 = "fail";
						// }

					case "doAllowedValidation5":
						// if (client.executeScript(allowedValidation5) != null) {
						client.swipe(Validation5, 500, 500);
						testCasePassed5 = "pass";
						// } else {
						// testCasePassed5 = "fail";
						// }

					case "doAllowedValidation6":
						// if (client.executeScript(allowedValidation6) != null) {
						client.sendText(Validation6);
						testCasePassed6 = "pass";
						// }
					case "doAllowedValidation7":
						// if (client.executeScript(allowedValidation6) != null) {
						client.sleep(Validation7);
						testCasePassed7 = "pass";
						// }
					case "doAllowedValidation8":
						// if (client.executeScript(allowedValidation6) != null) {
						client.waitForElement("NATIVE", Validation8a, 0, Validation8b);
						testCasePassed8 = "pass";
						// }
					}
				}
			} else if (allowed == false) {

				{
					for (int j = 0; j < orderOfValidation.size(); j++) {
						switch (orderOfValidation.get(j)) {
						case "doBlockedValidation1":
							// if (client.click("NATIVE", blockedValidation1, 0, 1) != null) {
							client.click("NATIVE", Validation1, 0, 1);
							// if (client.click("NATIVE", blockedValidation1, 0, 1) != null) {
							testCasePassed1 = "pass";
							// }

							// } else {
							// testCasePassed1 = "fail";
							// }
						case "doBlockedValidation2":
							// if (client.findElement(By.xpath(blockedValidation2)) != null) {
							client.click("NATIVE", Validation2, 0, 1);
							// if (client.findElement(By.xpath(didClickWork2)) != null) {
							testCasePassed2 = "pass";
							// }
							// } else {
							// testCasePassed2 = "fail";
							// }
						case "doBlockedValidation3":
							// if (client.findElement(By.xpath(blockedValidation3)) != null) {
							client.click("NATIVE", Validation3, 0, 1);
							// if (client.findElement(By.xpath(didClickWork3)) != null) {
							testCasePassed3 = "pass";
							// }
							// } else {
							// testCasePassed3 = "fail";
							// }
						case "doBlockedValidation4":
							// if (client.findElement(By.xpath(blockedValidation3)) != null) {
							client.waitForElement("NATIVE", Validation4, 0, 10000);
							testCasePassed4 = "pass";
							// } else {
							// testCasePassed4 = "fail";
							// }
							// }

						case "doBlockedValidation5":
							// if (client.executeScript(blockedValidation5) != null) {
							client.swipe(Validation5, 500, 500);
							testCasePassed5 = "pass";
							// } else {
							// testCasePassed5 = "fail";
							// }
						case "doBlockedValidation6":
							// if (client.findElement(By.xpath(blockedValidation6a)) != null) {
							client.sendText(Validation6);
							// if (client.findElement(By.xpath(didClickWork1)) != null) {
							testCasePassed6 = "pass";
							// } else {
							// throw new IllegalStateException(appName + " isn't allowed or
							// blocked");
							// }
							// }
						case "doBlockedValidation7":
							// if (client.sleep(allowedValidation7) != null) {
							client.sleep(Validation7);
							testCasePassed7 = "pass";
							// }
						case "doBlockedValidation8":
							// if (client.waitForElement(allowedValidation8, 0, blockedValidation8b) !=
							// null) {
							client.waitForElement("NATIVE", Validation8a, 0, Validation8b);
							testCasePassed8 = "pass";
							// }
						default:
							System.out.println("Something is broken");
							break;
						}
					}
				}
			} else if (loggedIn == false) {
				login(openAppValidation, username, password, logInStep1, logInStep2, logInStep3);
				if (allowed == true) {
					for (int j = 0; j < orderOfValidation.size(); j++) {
						switch (orderOfValidation.get(j)) {
						case "doAllowedValidation1":
							// if (client.verifyElementFound("NATIVE", allowedValidation1, 0)) {
							client.click("NATIVE", Validation1, 0, 1);
							testCasePassed1 = "pass";
							// }

							break;
						case "doAllowedValidation2":
							// if (client.findElement(By.xpath(allowedValidation2)) != null) {
							client.click("NATIVE", Validation2, 0, 1);
							// if (client.findElement(By.xpath(didClickWork2)) != null) {
							testCasePassed2 = "pass";
							// }
							// } else {
							// testCasePassed2 = "fail";
							// }

							break;
						case "doAllowedValidation3":
							// if (client.findElement(By.xpath(allowedValidation3)) != null) {
							client.click("NATIVE", Validation3, 0, 1);
							// if (client.findElement(By.xpath(didClickWork3)) != null) {
							testCasePassed3 = "pass";
							// }
							// } else {
							// testCasePassed3 = "fail";
							// }

						case "doAllowedValidation4":
							// if (client.waitForElement("NATIVE", allowedValidation4, 0, 10000)) != null) {
							client.waitForElement("NATIVE", Validation4, 0, 10000);
							testCasePassed4 = "pass";
							// } else {
							// testCasePassed4 = "fail";
							// }

						case "doAllowedValidation5":
							// if (client.executeScript(allowedValidation5) != null) {
							client.swipe(Validation5, 500, 500);
							testCasePassed5 = "pass";
							// } else {
							// testCasePassed5 = "fail";
							// }

						case "doAllowedValidation6":
							// if (client.executeScript(allowedValidation6) != null) {
							client.sendText(Validation6);
							testCasePassed6 = "pass";
							// }
						case "doAllowedValidation7":
							// if (client.sleep(allowedValidation7)) != null) {
							client.sleep(Validation7);
							testCasePassed7 = "pass";
							// }
						case "doAllowedValidation8":
							// if client.waitForElement("NATIVE", allowedValidation8a, 0,
							// allowedValidation8b) != null) {
							client.waitForElement("NATIVE", Validation8a, 0, Validation8b);
							testCasePassed8 = "pass";
							// }
						default:
							System.out.println("Something is broken");
							break;
						}
					}
				} else if (allowed == false) {
					for (int j = 0; j < orderOfValidation.size(); j++) {
						switch (orderOfValidation.get(j)) {
						case "doBlockedValidation1":
							// if (client.click("NATIVE", blockedValidation1, 0, 1) != null) {
							client.click("NATIVE", Validation1, 0, 1);
							// if (client.click("NATIVE", blockedValidation1, 0, 1) != null) {
							testCasePassed1 = "pass";
							// }

							// } else {
							// testCasePassed1 = "fail";
							// }
						case "doBlockedValidation2":
							// if (client.findElement(By.xpath(blockedValidation2)) != null) {
							client.click("NATIVE", Validation2, 0, 1);
							// if (client.findElement(By.xpath(didClickWork2)) != null) {
							testCasePassed2 = "pass";
							// }
							// } else {
							// testCasePassed2 = "fail";
							// }
						case "doBlockedValidation3":
							// if (client.findElement(By.xpath(blockedValidation3)) != null) {
							client.click("NATIVE", Validation3, 0, 1);
							// if (client.findElement(By.xpath(didClickWork3)) != null) {
							testCasePassed3 = "pass";
							// }
							// } else {
							// testCasePassed3 = "fail";
							// }
						case "doBlockedValidation4":
							// if (client.findElement(By.xpath(blockedValidation3)) != null) {
							client.waitForElement("NATIVE", Validation4, 0, 10000);
							testCasePassed4 = "pass";
							// } else {
							// testCasePassed4 = "fail";
							// }
							// }

						case "doBlockedValidation5":
							// if (client.executeScript(blockedValidation5) != null) {
							client.swipe(Validation5, 500, 500);
							testCasePassed5 = "pass";
							// } else {
							// testCasePassed5 = "fail";
							// }
						case "doBlockedValidation6":
							// if (client.findElement(By.xpath(blockedValidation6a)) != null) {
							client.sendText(Validation6);
							// if (client.findElement(By.xpath(didClickWork1)) != null) {
							testCasePassed6 = "pass";
							// } else {
							// throw new IllegalStateException(appName + " isn't allowed or
							// blocked");
							// }
							// }
						case "doBlockedValidation7":
							// if (client.sleep(allowedValidation7) != null) {
							client.sleep(Validation7);
							testCasePassed7 = "pass";
							// }
						case "doBlockedValidation8":
							// if (client.waitForElement(allowedValidation8, 0, blockedValidation8b) !=
							// null) {
							client.waitForElement("NATIVE", Validation8a, 0, Validation8b);
							testCasePassed8 = "pass";
							// }
						default:
							System.out.println("Something is broken");
							break;
						}
					}
				}

				if (testCasePassed1.equals("fail") || testCasePassed2.equals("fail") || testCasePassed3.equals("fail")
						|| testCasePassed4.equals("fail") || testCasePassed5.equals("fail")
						|| testCasePassed6.equals("fail") || testCasePassed7.equals("fail")
						|| testCasePassed8.equals("fail")) {
					stepPassed = "false";
					System.out.println(testCasePassed1);
					System.out.println(testCasePassed2);
					System.out.println(testCasePassed3);
					System.out.println(testCasePassed4);
					System.out.println(testCasePassed5);
					System.out.println(testCasePassed6);
					System.out.println(testCasePassed7);
					System.out.println(testCasePassed7);
				} else {
					System.out.println("All test cases passed!");
				}

				RunAppSeeTest.comment = comment;
			}
		}
	}

	public static void login(final String openAppValidation, final String username, final String password,
			final String logInStep1, final String logInStep2, final String logInStep3) {
		client.click("NATIVE", openAppValidation, 0, 1);
		client.elementSendText("NATIVE", username, 0, logInStep1);
		client.elementSendText("NATIVE", password, 0, logInStep2);
		client.click("NATIVE", logInStep3, 0, 1);
	}

	@After
	public void tearDown() {
		client.releaseClient();
	}
}
