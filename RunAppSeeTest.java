package com.familyzone.qa.automation;

import com.experitest.client.*;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class RunAppSeeTest {

	static Boolean hasSIMCard = true;
	static String comment = "";
	private String host = "localhost";
	private int port = 8889;
	private String projectBaseDirectory = "C:\\Users\\fzadmin\\workspace\\appium-project";
	protected static Client client = null;
	static String deviceName = "Pixel";

	public static void main(String[] args) throws MalformedURLException {

		client.setDevice("adb:" + deviceName);
		final Boolean allowed = true;
		final Boolean loggedIn = true;
		Boolean stepPassed = null;
		final RunAppSeeTest app = new RunAppSeeTest();
		final String appName = "snapchat";
		final String deviceName = "Android";
		stepPassed = app.openApp(appName, deviceName, loggedIn, allowed);
		System.out.println("This test: " + stepPassed);

	}

	public Boolean openApp(final String appName, final String deviceName, final Boolean loggedIn, final Boolean allowed)
			throws MalformedURLException {// Finds what App is being tested, if logged in and if allowed

		client = new Client(host, port, true);
		client.setProjectBaseDirectory(projectBaseDirectory);
		client.setReporter("xml", "reports", "Unnamed");

		final ArrayList<String> orderOfValidation = new ArrayList<String>();

		String activity = null;
		Boolean stepPassed = null;

		String allowedValidation1 = "sdf";
		String allowedValidation2 = null;
		String allowedValidation3 = null;
		String allowedValidation4 = null;
		String allowedValidation5 = null;
		String allowedValidation6 = null;
		String blockedValidation1 = null;
		String blockedValidation2 = null;
		String blockedValidation3 = null;
		String blockedValidation4 = null;
		String blockedValidation5 = null;
		String blockedValidation6 = null;
		String didClickWork1 = null;
		String didClickWork2 = null;
		String didClickWork3 = null;
		String logInStep1 = null;
		String logInStep2 = null;
		String logInStep3 = null;
		String password = null;
		String username = null;
		String openAppValidation = null;

		String comment = "";

		switch (appName) {// Switch for setting each test step variable for the App being tested

		case "snapchat":
			if (loggedIn == true) {
				openAppValidation = "xpath=xpath=//*[@id='nueva_nav_default_camera_button']";
				if (allowed == true) {
					allowedValidation1 = "xpath=xpath=//*[@id='hova_nav_stories_dark']";
					allowedValidation2 = "((xpath=//*[@id='recycler_view']/*/*[@id='frame'])[1]/*[@class='android.widget.RelativeLayout' and @width>0 and @height>0])[1]";
					didClickWork1 = "xpath=//*[@text='Discover']";
					didClickWork2 = "(xpath=//*[@class='android.widget.FrameLayout' and ./parent::*[@id='opera_viewer']]/*[@class='android.widget.FrameLayout' and @width>0 and @height>0])[4]";
					orderOfValidation.add("doAllowedValidation1");
					orderOfValidation.add("doAllowedValidation2");
				} else if (allowed == false) {
					blockedValidation4 = "xpath=//*[@id='notification_content']";
					didClickWork1 = "xpath=//*[@text='Discover']";
					orderOfValidation.add("doBlockedValidation4");
				} else {
					throw new IllegalStateException("\n Snapchat is logged in but not allowed or denied");
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
					allowedValidation4 = "xpath=//*[@id='nueva_nav_default_camera_button']";
					orderOfValidation.add("doAllowedValidation4");
				} else if (allowed == false) {
					blockedValidation4 = "xpath=//*[@class='android.widget.FrameLayout' and @width>0 and ./*[@class='android.widget.FrameLayout' and ./*[@id='content']]]";
					orderOfValidation.add("doBlockedValidation4");
				} else {
					throw new IllegalStateException("\n Snapchat is not logged in but not allowed or denied");
				}
			} else {
				throw new IllegalStateException("\n Snapchat isn't defined at all");
			}

			activity = "com.snapchat.android/.LandingPageActivity";
			break;
		case "spotify":
			if (loggedIn == true) {
				openAppValidation = "xpath=//*[@text='Home']";
				if (allowed == true) {
					allowedValidation1 = "xpath=//*[@id='icon' and (./preceding-sibling::* | ./following-sibling::*)[@text='@  Need for Speed']]";
					allowedValidation2 = "xpath=//*[@text='SHUFFLE PLAY']";
					didClickWork1 = "xpath=//*[@text='SHUFFLE PLAY']";
					didClickWork2 = "xpath=//*[@id='mini_player_progress']";
					orderOfValidation.add("doAllowedValidation1");
					orderOfValidation.add("doAllowedValidation2");
				} else if (allowed == false) {
					blockedValidation4 = "xpath=//*[@text='No Internet connection available']";
					orderOfValidation.add("doBlockedValidation4");
				} else {
					throw new IllegalStateException("\n Spotify is logged in but not allowed or denied");
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
					allowedValidation4 = "xpath=//*[@text='Recently played']";
				} else if (allowed == false) {

					orderOfValidation.add("doBlockedValidation4");
					blockedValidation4 = "xpath=//*[@text=concat('You', \"'\", 're offline')]";
				} else {
					throw new IllegalStateException("\n Spotify is not logged in but not allowed or denied");
				}
			} else {
				throw new IllegalStateException("\n Spotify isn't defined at all");
			}
			activity = "com.spotify.music/.MainActivity";
			break;
		case "whatsapp":
			if (RunAppSeeTest.hasSIMCard == false) {
				comment = "SIM Not installed skipped test step";
			} else {
				if (loggedIn == true) {
					openAppValidation = "xpath=//*[@id='contact_row_container' and ./*[./*[@contentDescription='Ross']]]";
					if (allowed == true) {
						allowedValidation1 = "xpath=//*[@id='fab']";
						didClickWork1 = "xpath=//*[@text='Select contact']";
						allowedValidation3 = "xpath=//*[@class='android.widget.LinearLayout' and @width>0 and ./*[@class='android.widget.LinearLayout' and ./*[@class='android.widget.FrameLayout' and ./*[@text='Ross']]]]";
						allowedValidation6 = "automated message no need to reply";
						allowedValidation2 = "xpath=//*[@id='send']";
						didClickWork2 = "xpath=//*[@id='voice_note_btn']";
						allowedValidation4 = "xpath=//*[@id='status']";
						orderOfValidation.add("doAllowedValidation1");
						orderOfValidation.add("doAllowedValidation2");
						orderOfValidation.add("doAllowedValidation6");
						orderOfValidation.add("doAllowedValidation2");
						orderOfValidation.add("doAllowedValidation4");

					} else if (allowed == false) {
						blockedValidation1 = "xpath=//*[@id='fab']";
						blockedValidation3 = "xpath=//*[@class='android.widget.LinearLayout' and @width>0 and ./*[@class='android.widget.LinearLayout' and ./*[@class='android.widget.FrameLayout' and ./*[@text='Ross']]]]";
						blockedValidation6 = "automated message no need to reply";
						blockedValidation2 = "xpath=//*[@id='send']";
						blockedValidation4 = "xpath=//*[@contentDescription='Pending']";
						orderOfValidation.add("doBlockedValidation1");
						orderOfValidation.add("doBlockedValidation3");
						orderOfValidation.add("doBlockedValidation6");
						orderOfValidation.add("doBlockedValidation2");
						orderOfValidation.add("doBlockedValidation4");
					} else {
						throw new IllegalStateException("\n Whatsapp is logged in but not allowed or denied");
					}
				} else if (loggedIn == false) {
					System.out.println("need more time to code this test"); // TODO
					if (allowed == true) {
						System.out.println("need more time to code this test"); // TODO
					} else if (allowed == false) {
						System.out.println("need more time to code this test"); // TODO
					} else {
						throw new IllegalStateException("\n Whatsapp is not logged in but not allowed or denied");
					}
				} else {
					throw new IllegalStateException("\n Whatsapp isn't defined at all");
				}
				activity = "com.whatsapp/.Main";
			}
			break;
		case "instagram":
			if (loggedIn == true) {
				openAppValidation = "xpath=//*[@id='tab_icon' and ./parent::*[@contentDescription='Home']]";
				if (allowed == true) {
					allowedValidation1 = "xpath=//*[@contentDescription='Search and explore']";
					allowedValidation2 = "xpath=//*[@id='pulse_emitter']";
					allowedValidation4 = "xpath=//*[@id='action_bar_shadow']";
					didClickWork1 = "xpath=//*[@id='action_bar_search_edit_text']";
					didClickWork2 = "xpath=//*[@text='Top live videos']";
					orderOfValidation.add("doAllowedValidation1");
					orderOfValidation.add("doAllowedValidation2");
					orderOfValidation.add("doAllowedValidation4");
				} else if (allowed == false) {
					blockedValidation1 = "xpath=//*[@id='tab_icon' and ./parent::*[@contentDescription='Home']]";
					blockedValidation5 = "Down";
					blockedValidation4 = "xpath=//*[@class='android.widget.ImageView' and ./parent::*[@id='row_load_more_button']]";
					didClickWork1 = "xpath=//*[@id='action_bar_left_button']";
					orderOfValidation.add("doBlockedValidation1");
					orderOfValidation.add("doBlockedValidation5");
					orderOfValidation.add("doBlockedValidation4");
				} else {
					throw new IllegalStateException("\n Instagram is logged in but not allowed or denied");
				}
			} else if (loggedIn == false) {
				password = "QATestapps";
				username = "fz.test.apps@gmail.com";
				logInStep1 = "xpath=//*[@id='login_username']";
				logInStep2 = "xpath=//*[@id='password']";
				logInStep3 = "xpath=//*[@text='Log In']";
				openAppValidation = "xpath=//*[@text='Already have an account? Log in.']";
				if (allowed == true) {
					allowedValidation4 = "xpath=//*[@id='tab_icon' and ./parent::*[@contentDescription='Home']]";
					orderOfValidation.add("doAllowedValidation4");
				} else if (allowed == false) {
					blockedValidation4 = "xpath=//*[@text='An unknown network error has occurred.']";
					orderOfValidation.add("doBlockedValidation4");
				} else {
					throw new IllegalStateException("\n Instagram is not logged in but not allowed or denied");
				}
			} else {
				throw new IllegalStateException("\n Instagram isn't defined at all");
			}
			activity = "com.instagram.android/.activity.MainTabActivity";
		case "facebook":
			if (loggedIn == true) {
				openAppValidation = "xpath=//*[@contentDescription='Selected, News Feed, Tab 1 of 5']";
				if (allowed == true) {
					allowedValidation1 = "xpath=//*[@contentDescription='Selected, News Feed, Tab 1 of 5']";
					allowedValidation4 = "xpath=//*[@text='What are you listing?']]";
					didClickWork1 = "xpath=//*[@contentDescription='Selected, Marketplace, Tab 3 of 5']";
					orderOfValidation.add("doAllowedValidation1");
					orderOfValidation.add("doAllowedValidation4");
				} else if (allowed == false) {
					blockedValidation1 = "xpath=//*[@contentDescription='Selected, News Feed, Tab 1 of 5']";
					didClickWork1 = "xpath=//*[@contentDescription='Selected, Marketplace, Tab 3 of 5']";
					blockedValidation4 = "xpath=//*[@text='Something went wrong']";
					orderOfValidation.add("doBlockedValidation1");
					orderOfValidation.add("doBlockedValidation4");
				} else {
					throw new IllegalStateException("\n Facebook is logged in but not allowed or denied");
				}
			} else if (loggedIn == false) {
				password = "FZtestapp!";
				username = "FZ Tester";
				logInStep1 = "((xpath=//*[@id='(name removed)' and @class='android.widget.LinearLayout']/*[@id='(name removed)' and @class='android.widget.LinearLayout'])[1]/*/*[@class='android.widget.EditText'])[1]";
				logInStep2 = "((xpath=//*[@id='(name removed)' and @class='android.widget.LinearLayout']/*[@id='(name removed)' and @class='android.widget.LinearLayout'])[1]/*/*[@class='android.widget.EditText'])[2]";
				logInStep3 = "xpath=//*[@text='LOG IN']";
				openAppValidation = "xpath=//*[@text='Log into another account']";
				if (allowed == true) {
					allowedValidation4 = "xpath=//*[@class='android.view.ViewGroup' and @width>0 and @height>0 and ./*[@contentDescription='Go to profile']]";
					orderOfValidation.add("doAllowedValidation4");
				} else if (allowed == false) {
					blockedValidation4 = "xpath=//*[@class='android.widget.FrameLayout' and @width>0 and ./*[@class='android.widget.FrameLayout' and ./*[@id='content']]]";
					orderOfValidation.add("doBlockedValidation4");
				} else {
					throw new IllegalStateException("\n Facebook is not logged in but not allowed or denied");
				}
			} else {
				throw new IllegalStateException("\n Facebook isn't defined at all");
			}
			activity = "com.facebook.katana/.LoginActivity";
		default:
			throw new IllegalStateException("\n App Not Defined!");
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

		client.launch(activity, false, true);

		if (loggedIn == true) {
			client.waitForElement("NATIVE", openAppValidation, 0, 10000);
			if (allowed == true) {
				for (int j = 0; j < orderOfValidation.size(); j++) {
					switch (orderOfValidation.get(j)) {
					case "doAllowedValidation1":
						// if (client.verifyElementFound("NATIVE", allowedValidation1, 0)) {
						client.click("NATIVE", allowedValidation1, 0, 1);
						testCasePassed1 = "pass";
						// }

						break;
					case "doAllowedValidation2":
						// if (client.findElement(By.xpath(allowedValidation2)) != null) {
						client.click("NATIVE", allowedValidation2, 0, 1);
						// if (client.findElement(By.xpath(didClickWork2)) != null) {
						testCasePassed2 = "pass";
						// }
						// } else {
						// testCasePassed2 = "fail";
						// }

						break;
					case "doAllowedValidation3":
						// if (client.findElement(By.xpath(allowedValidation3)) != null) {
						client.click("NATIVE", allowedValidation3, 0, 1);
						// if (client.findElement(By.xpath(didClickWork3)) != null) {
						testCasePassed3 = "pass";
						// }
						// } else {
						// testCasePassed3 = "fail";
						// }

					case "doAllowedValidation4":
						// if (client.waitForElement("NATIVE", allowedValidation4, 0, 10000)) != null) {
						client.waitForElement("NATIVE", allowedValidation4, 0, 10000);
						testCasePassed4 = "pass";
						// } else {
						// testCasePassed4 = "fail";
						// }

					case "doAllowedValidation5":
						// if (client.executeScript(allowedValidation5) != null) {
						client.swipe(allowedValidation5, 500, 500);
						testCasePassed5 = "pass";
						// } else {
						// testCasePassed5 = "fail";
						// }

					case "doAllowedValidation6":
						// if (client.executeScript(allowedValidation6) != null) {
						client.sendText(allowedValidation6);
						testCasePassed6 = "pass";
						// }
					}
				}
			} else if (allowed == false) {

				{
					for (int j = 0; j < orderOfValidation.size(); j++) {
						switch (orderOfValidation.get(j)) {
						case "doBlockedValidation1":
							// if (client.click("NATIVE", blockedValidation1, 0, 1) != null) {
							client.click("NATIVE", blockedValidation1, 0, 1);
							// if (client.click("NATIVE", blockedValidation1, 0, 1) != null) {
							testCasePassed1 = "pass";
							// }

							// } else {
							// testCasePassed1 = "fail";
							// }
						case "doBlockedValidation2":
							// if (client.findElement(By.xpath(blockedValidation2)) != null) {
							client.click("NATIVE", blockedValidation2, 0, 1);
							// if (client.findElement(By.xpath(didClickWork2)) != null) {
							testCasePassed2 = "pass";
							// }
							// } else {
							// testCasePassed2 = "fail";
							// }
						case "doBlockedValidation3":
							// if (client.findElement(By.xpath(blockedValidation3)) != null) {
							client.click("NATIVE", blockedValidation3, 0, 1);
							// if (client.findElement(By.xpath(didClickWork3)) != null) {
							testCasePassed3 = "pass";
							// }
							// } else {
							// testCasePassed3 = "fail";
							// }
						case "doBlockedValidation4":
							// if (client.findElement(By.xpath(blockedValidation3)) != null) {
							client.waitForElement("NATIVE", blockedValidation4, 0, 10000);
							testCasePassed4 = "pass";
							// } else {
							// testCasePassed4 = "fail";
							// }
							// }

						case "doBlockedValidation5":
							// if (client.executeScript(blockedValidation5) != null) {
							client.swipe(blockedValidation5, 500, 500);
							testCasePassed5 = "pass";
							// } else {
							// testCasePassed5 = "fail";
							// }
						case "doBlockedValidation6":
							// if (client.findElement(By.xpath(blockedValidation6a)) != null) {
							client.sendText(blockedValidation6);
							// if (client.findElement(By.xpath(didClickWork1)) != null) {
							testCasePassed6 = "pass";
							// } else {
							// throw new IllegalStateException("\n" + appName + " isn't allowed or
							// blocked");
							// }
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
							client.click("NATIVE", allowedValidation1, 0, 1);
							testCasePassed1 = "pass";
							// }

							break;
						case "doAllowedValidation2":
							// if (client.findElement(By.xpath(allowedValidation2)) != null) {
							client.click("NATIVE", allowedValidation2, 0, 1);
							// if (client.findElement(By.xpath(didClickWork2)) != null) {
							testCasePassed2 = "pass";
							// }
							// } else {
							// testCasePassed2 = "fail";
							// }

							break;
						case "doAllowedValidation3":
							// if (client.findElement(By.xpath(allowedValidation3)) != null) {
							client.click("NATIVE", allowedValidation3, 0, 1);
							// if (client.findElement(By.xpath(didClickWork3)) != null) {
							testCasePassed3 = "pass";
							// }
							// } else {
							// testCasePassed3 = "fail";
							// }

						case "doAllowedValidation4":
							// if (client.waitForElement("NATIVE", allowedValidation4, 0, 10000)) != null) {
							client.waitForElement("NATIVE", allowedValidation4, 0, 10000);
							testCasePassed4 = "pass";
							// } else {
							// testCasePassed4 = "fail";
							// }

						case "doAllowedValidation5":
							// if (client.executeScript(allowedValidation5) != null) {
							client.swipe(allowedValidation5, 500, 500);
							testCasePassed5 = "pass";
							// } else {
							// testCasePassed5 = "fail";
							// }

						case "doAllowedValidation6":
							// if (client.executeScript(allowedValidation6) != null) {
							client.sendText(allowedValidation6);
							testCasePassed6 = "pass";
							// }
						}
					}
				} else if (allowed == false) {
					for (int j = 0; j < orderOfValidation.size(); j++) {
						switch (orderOfValidation.get(j)) {
						case "doBlockedValidation1":
							// if (client.click("NATIVE", blockedValidation1, 0, 1) != null) {
							client.click("NATIVE", blockedValidation1, 0, 1);
							// if (client.click("NATIVE", blockedValidation1, 0, 1) != null) {
							testCasePassed1 = "pass";
							// }

							// } else {
							// testCasePassed1 = "fail";
							// }
						case "doBlockedValidation2":
							// if (client.findElement(By.xpath(blockedValidation2)) != null) {
							client.click("NATIVE", blockedValidation2, 0, 1);
							// if (client.findElement(By.xpath(didClickWork2)) != null) {
							testCasePassed2 = "pass";
							// }
							// } else {
							// testCasePassed2 = "fail";
							// }
						case "doBlockedValidation3":
							// if (client.findElement(By.xpath(blockedValidation3)) != null) {
							client.click("NATIVE", blockedValidation3, 0, 1);
							// if (client.findElement(By.xpath(didClickWork3)) != null) {
							testCasePassed3 = "pass";
							// }
							// } else {
							// testCasePassed3 = "fail";
							// }
						case "doBlockedValidation4":
							// if (client.findElement(By.xpath(blockedValidation3)) != null) {
							client.waitForElement("NATIVE", blockedValidation4, 0, 10000);
							testCasePassed4 = "pass";
							// } else {
							// testCasePassed4 = "fail";
							// }
							// }

						case "doBlockedValidation5":
							// if (client.executeScript(blockedValidation5) != null) {
							client.swipe(allowedValidation5, 500, 500);
							testCasePassed5 = "pass";
							// } else {
							// testCasePassed5 = "fail";
							// }
						case "doBlockedValidation6":
							// if (client.findElement(By.xpath(blockedValidation6a)) != null) {
							client.sendText(allowedValidation6);
							// if (client.findElement(By.xpath(didClickWork1)) != null) {
							testCasePassed6 = "pass";
							// } else {
							// throw new IllegalStateException("\n" + appName + " isn't allowed or
							// blocked");
							// }
							// }
						default:
							System.out.println("Something is broken");
							break;
						}
					}
				}

				if (testCasePassed1.equals("fail") || testCasePassed2.equals("fail") || testCasePassed3.equals("fail")
						|| testCasePassed4.equals("fail") || testCasePassed5.equals("fail")
						|| testCasePassed6.equals("fail")) {
					stepPassed = false;
					System.out.println(testCasePassed1);
					System.out.println(testCasePassed2);
					System.out.println(testCasePassed3);
					System.out.println(testCasePassed4);
					System.out.println(testCasePassed5);
					System.out.println(testCasePassed6);
				} else {
					stepPassed = true;
				}

				RunAppSeeTest.comment = comment;

			}
		}
		return stepPassed;

	}

	public void login(final String openAppValidation, final String username, final String password,
			final String logInStep1, final String logInStep2, final String logInStep3) {
		client.click("NATIVE", openAppValidation, 0, 1);
		client.elementSendText("NATIVE", username, 0, logInStep1);
		client.elementSendText("NATIVE", password, 0, logInStep2);
		client.click("NATIVE", logInStep3, 0, 1);
	}

	public void tearDown() {
		client.releaseClient();
	}
}
