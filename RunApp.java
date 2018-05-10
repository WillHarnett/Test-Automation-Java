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

		Boolean loggedIn = true;
		Boolean allowed = false;
		RunApp app = new RunApp();
		String appName = "snapchat";
		app.openApp(appName, loggedIn, allowed);

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

		Boolean doAllowedValidation1 = null;
		Boolean doAllowedValidation2 = null;
		Boolean doAllowedValidation3 = null;
		Boolean doAllowedValidation4 = null;
		Boolean doAllowedValidation5 = null;

		Boolean doBlockedValidation1 = null;
		Boolean doBlockedValidation2 = null;
		Boolean doBlockedValidation3 = null;
		Boolean doBlockedValidation4 = null;
		Boolean doBlockedValidation5 = null;

		Boolean stepPassed = null;

		String allowedValidation1 = null;
		String allowedValidation2 = null;
		String allowedValidation3 = null;
		String blockedValidation1 = null;
		String blockedValidation2 = null;
		String blockedValidation3 = null;
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
					blockedValidation1 = "//*[@id='hova_nav_stories_dark']";
					blockedValidation2 = "seetest:client.swipe(\"Up\", 500, 500)";
					blockedValidation3 = "//*[@id='notification_content']";
					didClickWork1 = "//*[@text='Discover']";
					doBlockedValidation1 = true;
					doBlockedValidation5 = true;
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
					allowedValidation1 = "//*[@id='nueva_nav_default_camera_button']";
					doAllowedValidation4 = true;
				} else if (allowed == false) {
					// NL Need 1 Wait and Find B
					doBlockedValidation4 = true;
					blockedValidation1 = "//*[@class='android.widget.FrameLayout' and @width>0 and ./*[@class='android.widget.FrameLayout' and ./*[@id='content']]]";
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
					allowedValidation1 = "//*[@text='Recently played']";
				} else if (allowed == false) {
					// NL Need 1 Wait and Find B
					doBlockedValidation4 = true;
					blockedValidation1 = "//*[@text=concat('You', \"'\", 're offline')]";
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
					allowedValidation3 = "//*[@id='action_bar_shadow']";
					didClickWork1 = "//*[@id='action_bar_search_edit_text']";
					didClickWork2 = "//*[@text='Top live videos']";
					doAllowedValidation1 = true;
					doAllowedValidation2 = true;
					doAllowedValidation4 = true;
				} else if (allowed == false) {
					// L Need 1 FaC, 1 Swipe, 1 Wait B
					blockedValidation1 = "//*[@id='tab_icon' and ./parent::*[@contentDescription='Home']]";
					blockedValidation2 = "seetest:client.swipe(\"Up\", 500, 500)";
					blockedValidation3 = "//*[@class='android.widget.ImageView' and ./parent::*[@id='row_load_more_button']]";
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
					allowedValidation1 = "//*[@id='tab_icon' and ./parent::*[@contentDescription='Home']]";
					doAllowedValidation4 = true;
				} else if (allowed == false) {
					// NL Need 1 Wait and Find B
					blockedValidation1 = "//*[@text='An unknown network error has occurred.']";
					doBlockedValidation4 = true;
				} else {
					throw new IllegalStateException("\n Instagram is not logged in but not allowed or denied");
				}
			} else {
				throw new IllegalStateException("\n Instagram isn't defined at all");
			}

			activity = new Activity("com.instagram.android", ".activity.MainTabActivity");
		default:
			throw new IllegalStateException("\n No test data!");
		}

		// After all the variables have been set
		// we can now move on to executing the tests on the device
		// based on if it's allowed and logged in

		Boolean testCasePassed1 = null;
		Boolean testCasePassed2 = null;
		Boolean testCasePassed3 = null;
		Boolean testCasePassed4 = null;
		Boolean testCasePassed5 = null;
		Boolean testCasePassed6 = null;

		driver.startActivity(activity);
		if (loggedIn == true) {
			new WebDriverWait(driver, 10)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(openAppValidation)));
			if (allowed == true) {
				if (doAllowedValidation1 == true) {
					if (driver.findElement(By.xpath(allowedValidation1)) != null) {
						driver.findElement(By.xpath(allowedValidation1)).click();
						if (driver.findElement(By.xpath(didClickWork1)) != null) {
							testCasePassed1 = true;
						}
					} else {
						testCasePassed1 = false;
					}
				}

				if (doAllowedValidation2 == true) {
					if (driver.findElement(By.xpath(allowedValidation2)) != null) {
						driver.findElement(By.xpath(allowedValidation2)).click();
						if (driver.findElement(By.xpath(didClickWork2)) != null) {
							testCasePassed2 = true;
						}
					} else {
						testCasePassed2 = false;
					}
				}

				if (doAllowedValidation3 == true) {
					if (driver.findElement(By.xpath(allowedValidation3)) != null) {
						driver.findElement(By.xpath(allowedValidation3)).click();
						if (driver.findElement(By.xpath(didClickWork3)) != null) {
							testCasePassed3 = true;
						}
					} else {
						testCasePassed3 = false;
					}
				}

				if (doAllowedValidation4 == true) {
					if (new WebDriverWait(driver, 11)
							.until(ExpectedConditions.presenceOfElementLocated(By.xpath(allowedValidation1))) != null) {
						testCasePassed4 = true;
					} else {
						testCasePassed4 = false;
					}
				}

				if (doAllowedValidation5 == true) {
					if (driver.executeScript(allowedValidation1) != null) {
						driver.executeScript(allowedValidation1);
						testCasePassed5 = true;
					} else {
						testCasePassed5 = false;
					}
				}
			} else if (allowed == false) {
				if (doBlockedValidation1 == true) {
					if (driver.findElement(By.xpath(blockedValidation1)) != null) {
						driver.findElement(By.xpath(blockedValidation1)).click();
						if (driver.findElement(By.xpath(didClickWork1)) != null) {
							testCasePassed1 = true;
						}
					} else {
						testCasePassed1 = false;
					}
				}
				if (doBlockedValidation2 == true) {
					if (driver.findElement(By.xpath(blockedValidation2)) != null) {
						driver.findElement(By.xpath(blockedValidation2)).click();
						if (driver.findElement(By.xpath(didClickWork2)) != null) {
							testCasePassed2 = true;
						}
					} else {
						testCasePassed2 = false;
					}
				}
				if (doBlockedValidation3 == true) {
					if (driver.findElement(By.xpath(blockedValidation3)) != null) {
						driver.findElement(By.xpath(blockedValidation3)).click();
						if (driver.findElement(By.xpath(didClickWork3)) != null) {
							testCasePassed3 = true;
						}
					} else {
						testCasePassed3 = false;
					}
				}
				if (doBlockedValidation4 == true) {
					if (new WebDriverWait(driver, 11)
							.until(ExpectedConditions.presenceOfElementLocated(By.xpath(blockedValidation1))) != null) {
						testCasePassed4 = true;
					} else {
						testCasePassed4 = false;
					}
				}
				if (doBlockedValidation5 == true) {
					if (driver.executeScript(blockedValidation1) != null) {
						driver.executeScript(blockedValidation1);
						testCasePassed5 = true;
					} else {
						testCasePassed5 = false;
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
							testCasePassed1 = true;
						}
					} else {
						testCasePassed1 = false;
					}
				}
				if (doAllowedValidation2 == true) {
					if (driver.findElement(By.xpath(allowedValidation2)) != null) {
						driver.findElement(By.xpath(allowedValidation2)).click();
						if (driver.findElement(By.xpath(didClickWork2)) != null) {
							testCasePassed2 = true;
						}
					} else {
						testCasePassed2 = false;
					}
				}
				if (doAllowedValidation3 == true) {
					if (driver.findElement(By.xpath(allowedValidation3)) != null) {
						driver.findElement(By.xpath(allowedValidation3)).click();
						if (driver.findElement(By.xpath(didClickWork3)) != null) {
							testCasePassed3 = true;
						}
					} else {
						testCasePassed3 = false;
					}
				}
				if (doAllowedValidation4 == true) {
					if (new WebDriverWait(driver, 11)
							.until(ExpectedConditions.presenceOfElementLocated(By.xpath(allowedValidation1))) != null) {
						testCasePassed4 = true;
					} else {
						testCasePassed4 = false;
					}
				}
				if (doAllowedValidation5 == true) {
					if (driver.executeScript(allowedValidation1) != null) {
						driver.executeScript(allowedValidation1);
						testCasePassed5 = true;
					} else {
						testCasePassed5 = false;
					}
				}
				// done
			} else if (allowed == false) {
				if (doBlockedValidation1 == true) {
					if (driver.findElement(By.xpath(blockedValidation1)) != null) {
						driver.findElement(By.xpath(blockedValidation1)).click();
						if (driver.findElement(By.xpath(didClickWork1)) != null) {
							testCasePassed1 = true;
						}
					} else {
						testCasePassed1 = false;
					}
				}
				if (doBlockedValidation2 == true) {
					if (driver.findElement(By.xpath(blockedValidation2)) != null) {
						driver.findElement(By.xpath(blockedValidation2)).click();
						if (driver.findElement(By.xpath(didClickWork2)) != null) {
							testCasePassed2 = true;
						}
					} else {
						testCasePassed2 = false;
					}
				}
				if (doBlockedValidation3 == true) {
					if (driver.findElement(By.xpath(blockedValidation3)) != null) {
						driver.findElement(By.xpath(blockedValidation3)).click();
						if (driver.findElement(By.xpath(didClickWork3)) != null) {
							testCasePassed3 = true;
						}
					} else {
						testCasePassed3 = false;
					}
				}
				if (doBlockedValidation4 == true) {
					if (new WebDriverWait(driver, 11)
							.until(ExpectedConditions.presenceOfElementLocated(By.xpath(blockedValidation1))) != null) {
						testCasePassed4 = true;
					} else {
						testCasePassed4 = false;
					}
				}
				if (doBlockedValidation5 == true) {
					if (driver.executeScript(blockedValidation1) != null) {
						driver.executeScript(blockedValidation1);
						testCasePassed5 = true;
					} else {
						testCasePassed5 = false;
					}
				}
			} else {
				throw new IllegalStateException("\n" + appName + " is neither blocked nor allowed");
			}
		} else {
			throw new IllegalStateException("\n" + appName + " login state isn't defined");
		}

		if (testCasePassed1 == false || testCasePassed2 == false || testCasePassed3 == false || testCasePassed4 == false
				|| testCasePassed5 == false || testCasePassed6 == false) {
			stepPassed = false;
			System.out.println(testCasePassed1);
			System.out.println(testCasePassed2);
			System.out.println(testCasePassed3);
			System.out.println(testCasePassed4);
			System.out.println(testCasePassed5);
			System.out.println(testCasePassed6);
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
