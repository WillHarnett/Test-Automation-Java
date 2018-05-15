package test;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class RunApp {

	String deviceUUID = "94a4b8bb";
	private String reportDirectory = "reports";
	private String reportFormat = "xml";
	private String testName = "Untitled";
	protected AndroidDriver<AndroidElement> driver = null;

	DesiredCapabilities dc = new DesiredCapabilities();

	public static void main(String[] args) throws MalformedURLException {

		Boolean loggedIn = false;
		Boolean allowed = false;
		Boolean stepPassed = false;
		RunApp app = new RunApp();
		String appName = "facebook";
		stepPassed = app.openApp(appName, loggedIn, allowed);
		System.out.println("This test: " + stepPassed);

	}

	public Boolean openApp(final String appName, final Boolean loggedIn, final Boolean allowed)
			throws MalformedURLException {// Finds what App is being tested, if logged in and if allowed

		dc.setCapability("reportDirectory", reportDirectory);
		dc.setCapability("reportFormat", reportFormat);
		dc.setCapability("testName", testName);
		dc.setCapability(MobileCapabilityType.UDID, "94a4b8bb");
		driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
		driver.setLogLevel(Level.INFO);

		Activity activity = null;

		Boolean doAllowedValidation1 = false;
		Boolean doAllowedValidation2 = false;
		Boolean doAllowedValidation3 = false;
		Boolean doAllowedValidation4 = false;
		Boolean doAllowedValidation5 = false;

		Boolean doBlockedValidation1 = false;
		Boolean doBlockedValidation2 = false;
		Boolean doBlockedValidation3 = false;
		Boolean doBlockedValidation4 = false;
		Boolean doBlockedValidation5 = false;

		Boolean stepPassed = null;

		String allowedValidation1 = null;
		String allowedValidation2 = null;
		String allowedValidation3 = null;
		String allowedValidation4 = null;
		String allowedValidation5 = null;
		String blockedValidation1 = null;
		String blockedValidation2 = null;
		String blockedValidation3 = null;
		String blockedValidation4 = null;
		String blockedValidation5 = null;
		String didClickWork1 = null;
		String didClickWork2 = null;
		String didClickWork3 = null;
		String logInStep1 = null;
		String logInStep2 = null;
		String logInStep3 = null;
		String password = null;
		String username = null;
		String openAppValidation = null;
		switch (appName) {// Switch for setting each test step variable for the App being tested

		case "snapchat":
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
					doAllowedValidation1 = true;
					doAllowedValidation2 = true;
				} else if (allowed == false) {
					// L Need 1 FaC, 1 Swipe, 1 Wait B
					blockedValidation4 = "//*[@id='notification_content']";
					didClickWork1 = "//*[@text='Discover']";
					doBlockedValidation4 = true;
				} else {
					throw new IllegalStateException("\n Snapchat is logged in but not allowed or denied");
				}
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
					doAllowedValidation4 = true;
				} else if (allowed == false) {
					// NL Need 1 Wait and Find B
					doBlockedValidation4 = true;
					blockedValidation4 = "//*[@class='android.widget.FrameLayout' and @width>0 and ./*[@class='android.widget.FrameLayout' and ./*[@id='content']]]";
				} else {
					throw new IllegalStateException("\n Snapchat is not logged in but not allowed or denied");
				}
			} else {
				throw new IllegalStateException("\n Snapchat isn't defined at all");
			}

			activity = new Activity("com.snapchat.android", ".LandingPageActivity");
			break;

		case "spotify":
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
					doAllowedValidation1 = true;
					doAllowedValidation2 = true;
				} else if (allowed == false) {
					// L Need 1 Wait and Find B
					blockedValidation1 = "//*[@text='No Internet connection available']";
					doBlockedValidation4 = true;
				} else {
					throw new IllegalStateException("\n Spotify is logged in but not allowed or denied");
				}
			} else if (loggedIn == false) {
				password = "QATestapps";
				username = "fz.test.apps@gmail.com";
				logInStep1 = "//*[@id='username_text']";
				logInStep2 = "//*[@id='password_text']";
				logInStep3 = "//*[@id='icon' and (./preceding-sibling::* | ./following-sibling::*)[@class='android.widget.ImageView']]";
				openAppValidation = "//*[@text='LOG IN']";
				if (allowed == true) {
					// NL Need 1 Wait and Find A
					doAllowedValidation4 = true;
					allowedValidation4 = "//*[@text='Recently played']";
				} else if (allowed == false) {
					// NL Need 1 Wait and Find B
					doBlockedValidation4 = true;
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
			dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.whatsapp");
			dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".Main");
			if (loggedIn == true) {
				openAppValidation = "//*[@id='contact_row_container' and ./*[./*[@contentDescription='Ross']]]";
				if (allowed == true) {
					// TODO
					doAllowedValidation1 = true;
					doAllowedValidation2 = true;
					doAllowedValidation3 = true;
					allowedValidation1 = "//*[@id='entry']";
					allowedValidation2 = "send doots";
					allowedValidation3 = "//*[@id='send']";
					// TODO
				} else if (allowed == false) {
					// TODO
					doBlockedValidation1 = true;
					doBlockedValidation2 = true;
					doBlockedValidation3 = true;
					blockedValidation1 = "//*[@id='entry']";
					blockedValidation2 = "send doots";
					blockedValidation3 = "//*[@id='send']";
					// TODO
				} else {
					throw new IllegalStateException("\n Whatsapp is logged in but not allowed or denied");
				}
			} else if (loggedIn == false) {
				password = "FZtest12345";
				username = "FZtest";
				logInStep1 = "//*[@text='AGREE AND CONTINUE']";
				// TODO
				if (allowed == true) {
					// TODO
					doAllowedValidation1 = true;
					allowedValidation1 = "TBD"; // TODO
				} else if (allowed == false) {
					// TODO
					doBlockedValidation1 = true;
					blockedValidation1 = "//*[@text='A cellular data network is required to activate WhatsApp Messenger.']";
				} else {
					throw new IllegalStateException("\n Whatsapp is not logged in but not allowed or denied");
				}
			} else {
				throw new IllegalStateException("\n Whatsapp isn't defined at all");
			}
			activity = new Activity("com.whatsapp", ".Main");
			break;
		case "instagram":
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
					doAllowedValidation1 = true;
					doAllowedValidation2 = true;
					doAllowedValidation4 = true;
				} else if (allowed == false) {
					// L Need 1 FaC, 1 Swipe, 1 Wait B
					blockedValidation1 = "//*[@id='tab_icon' and ./parent::*[@contentDescription='Home']]";
					blockedValidation5 = "seetest:client.swipe(\"Up\", 500, 500)";
					blockedValidation4 = "//*[@class='android.widget.ImageView' and ./parent::*[@id='row_load_more_button']]";
					didClickWork1 = "//*[@id='action_bar_left_button']";
					doBlockedValidation1 = true;
					doBlockedValidation5 = true;
					doBlockedValidation4 = true;
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
					doAllowedValidation4 = true;
				} else if (allowed == false) {
					// NL Need 1 Wait and Find B
					blockedValidation4 = "//*[@text='An unknown network error has occurred.']";
					doBlockedValidation4 = true;
				} else {
					throw new IllegalStateException("\n Instagram is not logged in but not allowed or denied");
				}
			} else {
				throw new IllegalStateException("\n Instagram isn't defined at all");
			}

			activity = new Activity("com.instagram.android", ".activity.MainTabActivity");
		case "facebook":
	        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.facebook.katana");
	        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
			if (loggedIn == true) {
				openAppValidation = "//*[@contentDescription='Selected, News Feed, Tab 1 of 5']";
				if (allowed == true) {
					// L Need 2 find and clicks A
					allowedValidation1 = "//*[@contentDescription='Selected, News Feed, Tab 1 of 5']";
					allowedValidation4 = "//*[@text='What are you listing?']]";
					didClickWork1 = "//*[@contentDescription='Selected, Marketplace, Tab 3 of 5']";
					doAllowedValidation1 = true;
					doAllowedValidation4 = true;
				} else if (allowed == false) {
					// L 1 Wait B
					blockedValidation1 = "//*[@contentDescription='Selected, News Feed, Tab 1 of 5']";
					didClickWork1 = "//*[@contentDescription='Selected, Marketplace, Tab 3 of 5']";
					blockedValidation4 = "//*[@text='Something went wrong']";
					doBlockedValidation1 = true;
					doBlockedValidation4 = true;
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
					doAllowedValidation4 = true;
				} else if (allowed == false) {
					// NL Need 1 Wait and Find B
					blockedValidation4 = "//*[@class='android.widget.FrameLayout' and @width>0 and ./*[@class='android.widget.FrameLayout' and ./*[@id='content']]]";
					doBlockedValidation4 = true;
				} else {
					throw new IllegalStateException("\n Snapchat is not logged in but not allowed or denied");
				}
			} else {
				throw new IllegalStateException("\n Snapchat isn't defined at all");
			}

			activity = new Activity("kik.android", ".chat.activity.IntroActivity");
			break;	
		default:
			throw new IllegalStateException("\n App Not Defined!");
		}

		/*
		 * After all the variables have been set
		 * we can now move on to executing the tests on the device
		 * based on if it's allowed and logged in
		 */

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
				if (doAllowedValidation1 == true) {
					if (driver.findElement(By.xpath(allowedValidation1)) != null) {
						driver.findElement(By.xpath(allowedValidation1)).click();
						if (driver.findElement(By.xpath(didClickWork1)) != null) {
							testCasePassed1 = "pass";
						}
					} else {
						testCasePassed1 = "fail";
					}
				}

				if (doAllowedValidation2 == true) {
					if (driver.findElement(By.xpath(allowedValidation2)) != null) {
						driver.findElement(By.xpath(allowedValidation2)).click();
						if (driver.findElement(By.xpath(didClickWork2)) != null) {
							testCasePassed2 = "pass";
						}
					} else {
						testCasePassed2 = "fail";
					}
				}
				
				if (doAllowedValidation3 == true) {
					if (driver.findElement(By.xpath(allowedValidation3)) != null) {
						driver.findElement(By.xpath(allowedValidation3)).click();
						if (driver.findElement(By.xpath(didClickWork3)) != null) {
							testCasePassed3 = "pass";
						}
					} else {
						testCasePassed3 = "fail";
					}
				}

				if (doAllowedValidation4 == true) {
					if (new WebDriverWait(driver, 11)
							.until(ExpectedConditions.presenceOfElementLocated(By.xpath(allowedValidation4))) != null) {
						testCasePassed4 = "pass";
					} else {
						testCasePassed4 = "fail";
					}
				}

				if (doAllowedValidation5 == true) {
					if (driver.executeScript(allowedValidation5) != null) {
						driver.executeScript(allowedValidation5);
						testCasePassed5 = "pass";
					} else {
						testCasePassed5 = "fail";
					}
				}
			} else if (allowed == false) {
				if (doBlockedValidation1 == true) {
					if (driver.findElement(By.xpath(blockedValidation1)) != null) {
						driver.findElement(By.xpath(blockedValidation1)).click();
						if (driver.findElement(By.xpath(didClickWork1)) != null) {
							testCasePassed1 = "pass";
						}
					} else {
						testCasePassed1 = "fail";
					}
				}
				if (doBlockedValidation2 == true) {
					if (driver.findElement(By.xpath(blockedValidation2)) != null) {
						driver.findElement(By.xpath(blockedValidation2)).click();
						if (driver.findElement(By.xpath(didClickWork2)) != null) {
							testCasePassed2 = "pass";
						}
					} else {
						testCasePassed2 = "fail";
					}
				}
				if (doBlockedValidation3 == true) {
					if (driver.findElement(By.xpath(blockedValidation3)) != null) {
						driver.findElement(By.xpath(blockedValidation3)).click();
						if (driver.findElement(By.xpath(didClickWork3)) != null) {
							testCasePassed3 = "pass";
						}
					} else {
						testCasePassed3 = "fail";
					}
				}
				if (doBlockedValidation4 == true) {
					if (new WebDriverWait(driver, 11)
							.until(ExpectedConditions.presenceOfElementLocated(By.xpath(blockedValidation4))) != null) {
						testCasePassed4 = "pass";
					} else {
						testCasePassed4 = "fail";
					}
				}
				if (doBlockedValidation5 == true) {
					if (driver.executeScript(blockedValidation5) != null) {
						driver.executeScript(blockedValidation5);
						testCasePassed5 = "pass";
					} else {
						testCasePassed5 = "fail";
					}
				}
			} else {
				throw new IllegalStateException("\n" + appName + " isn't allowed or blocked");
			}
		} else if (loggedIn == false) {
			login(openAppValidation, username, password, logInStep1, logInStep2, logInStep3);
			if (allowed == true) {
				if (doAllowedValidation1 == true) {
					if (driver.findElement(By.xpath(allowedValidation1)) != null) {
						driver.findElement(By.xpath(allowedValidation1)).click();
						if (driver.findElement(By.xpath(didClickWork1)) != null) {
							testCasePassed1 = "pass";
						}
					} else {
						testCasePassed1 = "fail";
					}
				}
				if (doAllowedValidation2 == true) {
					if (driver.findElement(By.xpath(allowedValidation2)) != null) {
						driver.findElement(By.xpath(allowedValidation2)).click();
						if (driver.findElement(By.xpath(didClickWork2)) != null) {
							testCasePassed2 = "pass";
						}
					} else {
						testCasePassed2 = "fail";
					}
				}
				if (doAllowedValidation3 == true) {
					if (driver.findElement(By.xpath(allowedValidation3)) != null) {
						driver.findElement(By.xpath(allowedValidation3)).click();
						if (driver.findElement(By.xpath(didClickWork3)) != null) {
							testCasePassed3 = "pass";
						}
					} else {
						testCasePassed3 = "fail";
					}
				}
				if (doAllowedValidation4 == true) {
					if (new WebDriverWait(driver, 11)
							.until(ExpectedConditions.presenceOfElementLocated(By.xpath(allowedValidation4))) != null) {
						testCasePassed4 = "pass";
					} else {
						testCasePassed4 = "fail";
					}
				}
				if (doAllowedValidation5 == true) {
					if (driver.executeScript(allowedValidation5) != null) {
						driver.executeScript(allowedValidation5);
						testCasePassed5 = "pass";
					} else {
						testCasePassed5 = "fail";
					}
				}
				// done
			} else if (allowed == false) {
				if (doBlockedValidation1 == true) {
					if (driver.findElement(By.xpath(blockedValidation1)) != null) {
						driver.findElement(By.xpath(blockedValidation1)).click();
						if (driver.findElement(By.xpath(didClickWork1)) != null) {
							testCasePassed1 = "pass";
						}
					} else {
						testCasePassed1 = "fail";
					}
				}
				if (doBlockedValidation2 == true) {
					if (driver.findElement(By.xpath(blockedValidation2)) != null) {
						driver.findElement(By.xpath(blockedValidation2)).click();
						if (driver.findElement(By.xpath(didClickWork2)) != null) {
							testCasePassed2 = "pass";
						}
					} else {
						testCasePassed2 = "fail";
					}
				}
				if (doBlockedValidation3 == true) {
					if (driver.findElement(By.xpath(blockedValidation3)) != null) {
						driver.findElement(By.xpath(blockedValidation3)).click();
						if (driver.findElement(By.xpath(didClickWork3)) != null) {
							testCasePassed3 = "pass";
						}
					} else {
						testCasePassed3 = "fail";
					}
				}
				if (doBlockedValidation4 == true) {
					if (new WebDriverWait(driver, 11)
							.until(ExpectedConditions.presenceOfElementLocated(By.xpath(blockedValidation4))) != null) {
						testCasePassed4 = "pass";
					} else {
						testCasePassed4 = "fail";
					}
				}
				if (doBlockedValidation5 == true) {
					if (driver.executeScript(blockedValidation5) != null) {
						driver.executeScript(blockedValidation5);
						testCasePassed5 = "pass";
					} else {
						testCasePassed5 = "fail";
					}
				}
			} else {
				throw new IllegalStateException("\n" + appName + " is neither blocked nor allowed");
			}
		} else {
			throw new IllegalStateException("\n" + appName + " login state isn't defined");
		}

		if (testCasePassed1 == "fail" || testCasePassed2 == "fail" || testCasePassed3 == "fail" || testCasePassed4 == "fail"
				|| testCasePassed5 == "fail" || testCasePassed6 == "fail") {
			stepPassed = false;
			System.out.println(testCasePassed1);
			System.out.println(testCasePassed2);
			System.out.println(testCasePassed3);
			System.out.println(testCasePassed4);
			System.out.println(testCasePassed5);
			System.out.println(testCasePassed6);
		}
		else {
			stepPassed = true;
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
