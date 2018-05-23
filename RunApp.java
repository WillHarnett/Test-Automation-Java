package com.familyzone.qa.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.experitest.client.*;
import org.junit.*;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class RunApp {

	static Boolean hasSIMCard = true;
	static String comment = "";
	String deviceUUID = "FA6B30300278";
	private final String reportDirectory = "reports";
	private final String reportFormat = "xml";
	private final String testName = "Untitled";
	protected AndroidDriver<AndroidElement> driver = null;

	DesiredCapabilities dc = new DesiredCapabilities();

	public static void main(String[] args) throws MalformedURLException {

		final Boolean allowed = true;
		final Boolean loggedIn = true;
		Boolean stepPassed = null;
		final RunApp app = new RunApp();
		final String appName = "snapchat";
		final String deviceName = "Android";
		stepPassed = app.openApp(appName, deviceName, loggedIn, allowed);
		System.out.println("This test: " + stepPassed);

	}

	public Boolean openApp(final String appName, final String deviceName, final Boolean loggedIn, final Boolean allowed)
			throws MalformedURLException {// Finds what App is being tested, if logged in and if allowed

	//	String appPackage = null;
	//	String appActivity = null;

		final ArrayList<String> orderOfValidation = new ArrayList<String>();

		dc.setCapability("reportDirectory", reportDirectory);
		dc.setCapability("reportFormat", reportFormat);
		dc.setCapability("testName", testName);
		dc.setCapability(MobileCapabilityType.UDID, deviceUUID);
	//	dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
	//	dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
		dc.setCapability("deviceName", deviceName);
		driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
		driver.setLogLevel(Level.INFO);

		Activity activity = null;
		Boolean stepPassed = null;

		String allowedValidation1 = null;
		String allowedValidation2 = null;
		String allowedValidation3 = null;
		String allowedValidation4 = null;
		String allowedValidation5 = null;
		String allowedValidation6a = null;
		String allowedValidation6b = null;
		String blockedValidation1 = null;
		String blockedValidation2 = null;
		String blockedValidation3 = null;
		String blockedValidation4 = null;
		String blockedValidation5 = null;
		String blockedValidation6a = null;
		String blockedValidation6b = null;
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
		//	appPackage = "com.snapchat.android";
		//	appActivity = ".LandingPageActivity";
			dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.snapchat.android");
			dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LandingPageActivity");

			if (loggedIn == true) {
				openAppValidation = "//*[@id='nueva_nav_default_camera_button']";
				if (allowed == true) {
					// L Need 2 find and clicks A
					allowedValidation1 = "//*[@id='hova_nav_stories_dark']";
					allowedValidation2 = "((//*[@id='recycler_view']/*/*[@id='frame'])[1]/*[@class='android.widget.RelativeLayout' and @width>0 and @height>0])[1]";
					didClickWork1 = "//*[@text='Discover']";
					didClickWork2 = "(//*[@class='android.widget.FrameLayout' and ./parent::*[@id='opera_viewer']]/*[@class='android.widget.FrameLayout' and @width>0 and @height>0])[4]";
					orderOfValidation.add("doAllowedValidation1");
					orderOfValidation.add("doAllowedValidation2");
				} else if (allowed == false) {
					// L Need 1 FaC, 1 Swipe, 1 Wait B
					blockedValidation4 = "//*[@id='notification_content']";
					didClickWork1 = "//*[@text='Discover']";
					orderOfValidation.add("doBlockedValidation4");
				} else {
					throw new IllegalStateException("\n Snapchat is logged in but not allowed or denied");
				}
				break;
			} else if (loggedIn == false) {
				password = "FZtest12345";
				username = "FZtest";
				logInStep1 = "//*[@id='username_or_email_field']";
				logInStep2 = "//*[@id='password_field']";
				logInStep3 = "//*[@id='registration_nav_container']";
				openAppValidation = "//*[@text='LOG IN']";
				if (allowed == true) {
					// NL Need 1 Wait and Find A
					allowedValidation4 = "//*[@id='nueva_nav_default_camera_button']";
					orderOfValidation.add("doAllowedValidation4");
				} else if (allowed == false) {
					// NL Need 1 Wait and Find B
					blockedValidation4 = "//*[@class='android.widget.FrameLayout' and @width>0 and ./*[@class='android.widget.FrameLayout' and ./*[@id='content']]]";
					orderOfValidation.add("doBlockedValidation4");
				} else {
					throw new IllegalStateException("\n Snapchat is not logged in but not allowed or denied");
				}
			} else {
				throw new IllegalStateException("\n Snapchat isn't defined at all");
			}

			activity = new Activity("com.snapchat.android", ".LandingPageActivity");
			break;
		case "spotify":
		//	appPackage = "com.spotify.music";
		//	appActivity = ".MainActivity";
			dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.spotify.music");
			dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
			if (loggedIn == true) {
				openAppValidation = "//*[@text='Home']";
				if (allowed == true) {
					// L Need 2 FaC A
					allowedValidation1 = "//*[@id='icon' and (./preceding-sibling::* | ./following-sibling::*)[@text='@  Need for Speed']]";
					allowedValidation2 = "//*[@text='SHUFFLE PLAY']";
					didClickWork1 = "//*[@text='SHUFFLE PLAY']";
					didClickWork2 = "//*[@id='mini_player_progress']";
					orderOfValidation.add("doAllowedValidation1");
					orderOfValidation.add("doAllowedValidation2");
				} else if (allowed == false) {
					// L Need 1 Wait and Find B
					blockedValidation4 = "//*[@text='No Internet connection available']";
					orderOfValidation.add("doBlockedValidation4");
				} else {
					throw new IllegalStateException("\n Spotify is logged in but not allowed or denied");
				}
				break;
			} else if (loggedIn == false) {
				password = "QATestapps";
				username = "fz.test.apps@gmail.com";
				logInStep1 = "//*[@id='username_text']";
				logInStep2 = "//*[@id='password_text']";
				logInStep3 = "//*[@id='icon' and (./preceding-sibling::* | ./following-sibling::*)[@class='android.widget.ImageView']]";
				openAppValidation = "//*[@text='LOG IN']";
				if (allowed == true) {
					// NL Need 1 Wait and Find A
					orderOfValidation.add("doAllowedValidation4");
					allowedValidation4 = "//*[@text='Recently played']";
				} else if (allowed == false) {
					// NL Need 1 Wait and Find B
					orderOfValidation.add("doBlockedValidation4");
					blockedValidation4 = "//*[@text=concat('You', \"'\", 're offline')]";
				} else {
					throw new IllegalStateException("\n Spotify is not logged in but not allowed or denied");
				}
			} else {
				throw new IllegalStateException("\n Spotify isn't defined at all");
			}
			activity = new Activity("com.spotify.music", ".MainActivity");
			break;
		case "whatsapp":
			if (RunApp.hasSIMCard == false) {
				comment = "SIM Not installed skipped test step";
			} else {
			//	appPackage = "com.whatsapp";
			//	appActivity = ".Main";
				dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.whatsapp");
				dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".Main");
				if (loggedIn == true) {
					openAppValidation = "//*[@id='contact_row_container' and ./*[./*[@contentDescription='Ross']]]";
					if (allowed == true) {
						allowedValidation1 = "//*[@id='fab']";
						didClickWork1 = "//*[@text='Select contact']";
						allowedValidation6a = "//*[@class='android.widget.LinearLayout' and @width>0 and ./*[@class='android.widget.LinearLayout' and ./*[@class='android.widget.FrameLayout' and ./*[@text='Ross']]]]";
						allowedValidation6b = "automated message no need to reply";
						allowedValidation2 = "//*[@id='send']";
						didClickWork2 = "//*[@id='voice_note_btn']";
						allowedValidation4 = "//*[@id='status']";
						orderOfValidation.add("doAllowedValidation1");
						orderOfValidation.add("doAllowedValidation6");
						orderOfValidation.add("doAllowedValidation2");
						orderOfValidation.add("doAllowedValidation4");

					} else if (allowed == false) {
						blockedValidation1 = "//*[@id='fab']";
						blockedValidation6a = "//*[@class='android.widget.LinearLayout' and @width>0 and ./*[@class='android.widget.LinearLayout' and ./*[@class='android.widget.FrameLayout' and ./*[@text='Ross']]]]";
						blockedValidation6b = "automated message no need to reply";
						blockedValidation2 = "//*[@id='send']";
						blockedValidation4 = "//*[@contentDescription='Pending']";
						orderOfValidation.add("doBlockedValidation1");
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
				activity = new Activity("com.whatsapp", ".Main");
			}
			break;
		case "instagram":
		//	appPackage = "com.instagram.android";
		//	appActivity = ".activity.MainTabActivity";
			dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.instagram.android");
			dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".activity.MainTabActivity");
			if (loggedIn == true) {
				openAppValidation = "//*[@id='tab_icon' and ./parent::*[@contentDescription='Home']]";
				if (allowed == true) {
					// L Need 2 FaC, 1 Wait and Find A
					allowedValidation1 = "//*[@contentDescription='Search and explore']";
					allowedValidation2 = "//*[@id='pulse_emitter']";
					allowedValidation4 = "//*[@id='action_bar_shadow']";
					didClickWork1 = "//*[@id='action_bar_search_edit_text']";
					didClickWork2 = "//*[@text='Top live videos']";
					orderOfValidation.add("doAllowedValidation1");
					orderOfValidation.add("doAllowedValidation2");
					orderOfValidation.add("doAllowedValidation4");
				} else if (allowed == false) {
					// L Need 1 FaC, 1 Swipe, 1 Wait B
					blockedValidation1 = "//*[@id='tab_icon' and ./parent::*[@contentDescription='Home']]";
					blockedValidation5 = "seetest:client.swipe(\"Up\", 500, 500)";
					blockedValidation4 = "//*[@class='android.widget.ImageView' and ./parent::*[@id='row_load_more_button']]";
					didClickWork1 = "//*[@id='action_bar_left_button']";
					orderOfValidation.add("doBlockedValidation1");
					orderOfValidation.add("doBlockedValidation5");
					orderOfValidation.add("doBlockedValidation4");
				} else {
					throw new IllegalStateException("\n Instagram is logged in but not allowed or denied");
				}
			} else if (loggedIn == false) {
				password = "QATestapps";
				username = "fz.test.apps@gmail.com";
				logInStep1 = "//*[@id='login_username']";
				logInStep2 = "//*[@id='password']";
				logInStep3 = "//*[@text='Log In']";
				openAppValidation = "//*[@text='Already have an account? Log in.']";
				if (allowed == true) {
					// NL Need 1 Wait and Find A
					allowedValidation4 = "//*[@id='tab_icon' and ./parent::*[@contentDescription='Home']]";
					orderOfValidation.add("doAllowedValidation4");
				} else if (allowed == false) {
					// NL Need 1 Wait and Find B
					blockedValidation4 = "//*[@text='An unknown network error has occurred.']";
					orderOfValidation.add("doBlockedValidation4");
				} else {
					throw new IllegalStateException("\n Instagram is not logged in but not allowed or denied");
				}
			} else {
				throw new IllegalStateException("\n Instagram isn't defined at all");
			}
			activity = new Activity("com.instagram.android", ".activity.MainTabActivity");
		case "facebook":
		//	appPackage = "com.facebook.katana";
		//	appActivity = ".LoginActivity";
			dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.facebook.katana");
			dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
			if (loggedIn == true) {
				openAppValidation = "//*[@contentDescription='Selected, News Feed, Tab 1 of 5']";
				if (allowed == true) {
					// L Need 2 find and clicks A
					allowedValidation1 = "//*[@contentDescription='Selected, News Feed, Tab 1 of 5']";
					allowedValidation4 = "//*[@text='What are you listing?']]";
					didClickWork1 = "//*[@contentDescription='Selected, Marketplace, Tab 3 of 5']";
					orderOfValidation.add("doAllowedValidation1");
					orderOfValidation.add("doAllowedValidation4");
				} else if (allowed == false) {
					// L 1 Wait B
					blockedValidation1 = "//*[@contentDescription='Selected, News Feed, Tab 1 of 5']";
					didClickWork1 = "//*[@contentDescription='Selected, Marketplace, Tab 3 of 5']";
					blockedValidation4 = "//*[@text='Something went wrong']";
					orderOfValidation.add("doBlockedValidation1");
					orderOfValidation.add("doBlockedValidation4");
				} else {
					throw new IllegalStateException("\n Facebook is logged in but not allowed or denied");
				}
			} else if (loggedIn == false) {
				password = "FZtestapp!";
				username = "FZ Tester";
				logInStep1 = "((//*[@id='(name removed)' and @class='android.widget.LinearLayout']/*[@id='(name removed)' and @class='android.widget.LinearLayout'])[1]/*/*[@class='android.widget.EditText'])[1]";
				logInStep2 = "((//*[@id='(name removed)' and @class='android.widget.LinearLayout']/*[@id='(name removed)' and @class='android.widget.LinearLayout'])[1]/*/*[@class='android.widget.EditText'])[2]";
				logInStep3 = "//*[@text='LOG IN']";
				openAppValidation = "//*[@text='Log into another account']";
				if (allowed == true) {
					// NL Need 1 Wait and Find A
					allowedValidation4 = "//*[@class='android.view.ViewGroup' and @width>0 and @height>0 and ./*[@contentDescription='Go to profile']]";
					orderOfValidation.add("doAllowedValidation4");
				} else if (allowed == false) {
					// NL Need 1 Wait and Find B
					blockedValidation4 = "//*[@class='android.widget.FrameLayout' and @width>0 and ./*[@class='android.widget.FrameLayout' and ./*[@id='content']]]";
					orderOfValidation.add("doBlockedValidation4");
				} else {
					throw new IllegalStateException("\n Facebook is not logged in but not allowed or denied");
				}
			} else {
				throw new IllegalStateException("\n Facebook isn't defined at all");
			}
			activity = new Activity("com.facebook.katana", ".LoginActivity");
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

		driver.startActivity(activity);

		if (loggedIn == true) {
			new WebDriverWait(driver, 10)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(openAppValidation)));
			if (allowed == true) {
				for (int i = 0; i < orderOfValidation.size(); i++) {
					switch (orderOfValidation.get(i)) {
					case "doAllowedValidation1":
						if (driver.findElement(By.xpath(allowedValidation1)) != null) {
							driver.findElement(By.xpath(allowedValidation1)).click();
							if (driver.findElement(By.xpath(didClickWork1)) != null) {
								testCasePassed1 = "pass";
							}
						} else {
							testCasePassed1 = "fail";
						}

						break;
					case "doAllowedValidation2":
						if (driver.findElement(By.xpath(allowedValidation2)) != null) {
							driver.findElement(By.xpath(allowedValidation2)).click();
							if (driver.findElement(By.xpath(didClickWork2)) != null) {
								testCasePassed2 = "pass";
							}
						} else {
							testCasePassed2 = "fail";
						}

						break;
					case "doAllowedValidation3":
						if (driver.findElement(By.xpath(allowedValidation3)) != null) {
							driver.findElement(By.xpath(allowedValidation3)).click();
							if (driver.findElement(By.xpath(didClickWork3)) != null) {
								testCasePassed3 = "pass";
							}
						} else {
							testCasePassed3 = "fail";
						}

					case "doAllowedValidation4":
						if (driver.findElement(By.xpath(allowedValidation4)) != null) {
							if (new WebDriverWait(driver, 11).until(ExpectedConditions
									.presenceOfElementLocated(By.xpath(allowedValidation4))) != null) {
								testCasePassed4 = "pass";
							} else {
								testCasePassed4 = "fail";
							}
						}

					case "doAllowedValidation5":
						if (driver.executeScript(allowedValidation5) != null) {
							driver.executeScript(allowedValidation5);
							testCasePassed5 = "pass";
						} else {
							testCasePassed5 = "fail";
						}

					case "doAllowedValidation6":
						if (driver.findElement(By.xpath(allowedValidation6a)) != null) {
							if (driver.executeScript(allowedValidation6a) != null) {
								driver.findElement(By.xpath(blockedValidation6a)).sendKeys(blockedValidation6b);
							}
						}
					}
				}
			}
		} else if (allowed == false) {
			for (int j = 0; j < orderOfValidation.size(); j++) {
				switch (orderOfValidation.get(j)) {
				case "doBlockedValidation1":
					if (driver.findElement(By.xpath(blockedValidation1)) != null) {
						driver.findElement(By.xpath(blockedValidation1)).click();
						if (driver.findElement(By.xpath(didClickWork1)) != null) {
							testCasePassed1 = "pass";
						}
					} else {
						testCasePassed1 = "fail";
					}
				case "doBlockedValidation2":
					if (driver.findElement(By.xpath(blockedValidation2)) != null) {
						driver.findElement(By.xpath(blockedValidation2)).click();
						if (driver.findElement(By.xpath(didClickWork2)) != null) {
							testCasePassed2 = "pass";
						}
					} else {
						testCasePassed2 = "fail";
					}
				case "doBlockedValidation3":
						if (driver.findElement(By.xpath(blockedValidation3)) != null) {
							driver.findElement(By.xpath(blockedValidation3)).click();
							if (driver.findElement(By.xpath(didClickWork3)) != null) {
								testCasePassed3 = "pass";
							}
						} else {
							testCasePassed3 = "fail";
						}
				case "doBlockedValidation4":
					if (driver.findElement(By.xpath(blockedValidation3)) != null) {
						if (new WebDriverWait(driver, 11).until(
								ExpectedConditions.presenceOfElementLocated(By.xpath(blockedValidation4))) != null) {
							testCasePassed4 = "pass";
						} else {
							testCasePassed4 = "fail";
						}
					}

				case "doBlockedValidation5":
					if (driver.executeScript(blockedValidation5) != null) {
						driver.executeScript(blockedValidation5);
						testCasePassed5 = "pass";
					} else {
						testCasePassed5 = "fail";
					}
				case "doBlockedValidation6":
					if (driver.findElement(By.xpath(blockedValidation6a)) != null) {
						driver.findElement(By.xpath(blockedValidation6a)).sendKeys(blockedValidation6b);
						if (driver.findElement(By.xpath(didClickWork1)) != null) {
							testCasePassed6 = "pass";
						} else {
							throw new IllegalStateException("\n" + appName + " isn't allowed or blocked");
						}
					}
				default:
					System.out.println("Something is broken");
					break;
				}
			}
		} else if (loggedIn == false) {
			login(openAppValidation, username, password, logInStep1, logInStep2, logInStep3);
			if (allowed == true) {
				for (int j = 0; j < orderOfValidation.size(); j++) {
					switch (orderOfValidation.get(j)) {
					case "doAllowedValidation1":
						if (driver.findElement(By.xpath(allowedValidation1)) != null) {
							driver.findElement(By.xpath(allowedValidation1)).click();
							if (driver.findElement(By.xpath(didClickWork1)) != null) {
								testCasePassed1 = "pass";
							}
						} else {
							testCasePassed1 = "fail";
						}

						break;
					case "doAllowedValidation2":
						if (driver.findElement(By.xpath(allowedValidation2)) != null) {
							driver.findElement(By.xpath(allowedValidation2)).click();
							if (driver.findElement(By.xpath(didClickWork2)) != null) {
								testCasePassed2 = "pass";
							}
						} else {
							testCasePassed2 = "fail";
						}

						break;
					case "doAllowedValidation3":
						if (driver.findElement(By.xpath(allowedValidation3)) != null) {
							driver.findElement(By.xpath(allowedValidation3)).click();
							if (driver.findElement(By.xpath(didClickWork3)) != null) {
								testCasePassed3 = "pass";
							}
						} else {
							testCasePassed3 = "fail";
						}

					case "doAllowedValidation4":
						if (new WebDriverWait(driver, 11).until(
								ExpectedConditions.presenceOfElementLocated(By.xpath(allowedValidation4))) != null) {
							testCasePassed4 = "pass";
						} else {
							testCasePassed4 = "fail";
						}

					case "doAllowedValidation5":
						if (driver.executeScript(allowedValidation5) != null) {
							driver.executeScript(allowedValidation5);
							testCasePassed5 = "pass";
						} else {
							testCasePassed5 = "fail";
						}

					case "doAllowedValidation6":
						if (driver.executeScript(allowedValidation6a) != null) {
							driver.findElement(By.xpath(allowedValidation6a)).sendKeys(allowedValidation6b);
						}
					}
				}
			} else if (allowed == false) {
				for (int j = 0; j < orderOfValidation.size(); j++) {
					switch (orderOfValidation.get(j)) {
					case "doBlockedValidation1":
						if (driver.findElement(By.xpath(blockedValidation1)) != null) {
							driver.findElement(By.xpath(blockedValidation1)).click();
							if (driver.findElement(By.xpath(didClickWork1)) != null) {
								testCasePassed1 = "pass";
							}
						} else {
							testCasePassed1 = "fail";
						}
					case "doBlockedValidation2":
						if (driver.findElement(By.xpath(blockedValidation2)) != null) {
							driver.findElement(By.xpath(blockedValidation2)).click();
							if (driver.findElement(By.xpath(didClickWork2)) != null) {
								testCasePassed2 = "pass";
							}
						} else {
							testCasePassed2 = "fail";
						}
					case "doBlockedValidation3":
							if (driver.findElement(By.xpath(blockedValidation3)) != null) {
								driver.findElement(By.xpath(blockedValidation3)).click();
								if (driver.findElement(By.xpath(didClickWork3)) != null) {
									testCasePassed3 = "pass";
								}
							} else {
								testCasePassed3 = "fail";
							}
					case "doBlockedValidation4":
						if (new WebDriverWait(driver, 11).until(
								ExpectedConditions.presenceOfElementLocated(By.xpath(blockedValidation4))) != null) {
							testCasePassed4 = "pass";
						} else {
							testCasePassed4 = "fail";
						}

					case "doBlockedValidation5":
						if (driver.executeScript(blockedValidation5) != null) {
							driver.executeScript(blockedValidation5);
							testCasePassed5 = "pass";
						} else {
							testCasePassed5 = "fail";
						}
					case "doBlockedValidation6":
						driver.findElement(By.xpath(blockedValidation6a)).sendKeys(blockedValidation6b);
						if (driver.findElement(By.xpath(didClickWork1)) != null) {
							testCasePassed6 = "pass";
						} else {
							throw new IllegalStateException("\n" + appName + " isn't allowed or blocked");
						}
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

			RunApp.comment = comment;
			return stepPassed;
		}
		return stepPassed;

	}

	public void login(final String openAppValidation, final String username, final String password,
			final String logInStep1, final String logInStep2, final String logInStep3) {
		driver.findElement(By.xpath(openAppValidation)).click();
		driver.findElement(By.xpath(logInStep1)).sendKeys(username);
		driver.findElement(By.xpath(logInStep2)).sendKeys(password);
		driver.findElement(By.xpath(logInStep3)).click();
	}

	public void tearDown() {
		driver.quit();
	}
}
