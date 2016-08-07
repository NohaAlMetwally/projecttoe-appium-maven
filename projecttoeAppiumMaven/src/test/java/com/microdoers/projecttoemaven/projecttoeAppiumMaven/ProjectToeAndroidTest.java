package com.microdoers.projecttoemaven.projecttoeAppiumMaven;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.jetty.html.Break;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.server.handler.interactions.touch.Scroll;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDeviceActionShortcuts;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import java.text.SimpleDateFormat;


public class ProjectToeAndroidTest {
	// APK Path
		// private static final String TARGET_APP_PATH =
		// "/User/user/Desktop/com.microdoers.projecttoe-versionDevelopment-debug.apk";
		private AppiumDriver driver;
		private String username = "test666";
		private String password = "666666";

		String currentUser;
		String adminUsername = "test666";
		String nonAdminUsername = "name";
		String newUserName = "AppiumTestAndroid";
		String newUserPass = "111111";

		WebElement nav_bar;
		String postDate;
		private String dateNow = null;
		private String logOutMsg = "Are you sure you want to log-out from ProjectToe?";
		private String loginFailedMsg = "Login Failed";
		private String resetPassSuccess = "Reset Password successful";
		private String resetPassFailed = "Reset Password Failed";
		private String projectToeTutPage = "Project Toe";
		private String createProfileTutPage = "Create a Profile";
		private String joinSupportGroupTutPage = "Join a Support Group";
		private String meetOthersTutPage = "Meet Others";
		private String postContent = "Hello Appium Android";
		private String greetingsAppium = "Graatings from Appium!";
		private String terms = "Terms and conditions";
		private String next = "NEXT";
		private String done = "DONE";
		private String gallery = "Gallery";
		private String addPostFailed = "Add Post Failed";
		private String yourSupportGroup = "YOUR SUPPORT GROUPS";
		private String groupName = "AppiumAndroid";
		private String groupDesc = "Appium Test Android Description";
		private String groupKeyword = "AppiumTestAndroidKeyword";
		private String groupNewKeyword = "AppiumTestAndroidNewKeyword";
		private String privateGroupName = "#AppiumPrivateAndroid";
		private String appiumChatMsg = null;
		private boolean newGroupAdded = false;
		private boolean upgradeUserToPremium = false;
		private boolean upgradeGroupToPremium = false;
		int randomNum = 25 + (int) (Math.random() * 10000);;

		/**
		 * Initialization.
		 */
		// @BeforeGroups("main")
		@BeforeClass(alwaysRun = true)
		public void setUp() throws Exception {

			// File app = new File(TARGET_APP_PATH);

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
					"Galaxy Note 4");

			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
					capabilities);

			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			System.out.println("setup done");
		}

		/**
		 * Finishing. When the test is over. Disable the driver
		 */
		@AfterClass(groups = { "main" }, alwaysRun = true)
		public void afterClass() throws Exception {
			if (driver != null) {
				driver.quit();
				System.out.println("clean up done");

			}

		}

		@Test(groups = { "main" }, priority = 1)
		public void signIn() throws Exception {

			System.out.println("signIn");
			WebElement element = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("tutorial_signin_button")));
			try {
				swipeThroughFrames();
				element.click();
			} catch (NoSuchElementException e) {
				element.click();
			}

		}

		public void swipeThroughFrames() {
			if (isElementPresent(By.name(projectToeTutPage))) {
				driver.findElement(By.name(projectToeTutPage)).click();
				swipeLeft();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

				if (isElementPresent(By.name(createProfileTutPage))) {
					driver.findElement(By.name(createProfileTutPage)).click();
					swipeLeft();
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

					if (isElementPresent(By.name(joinSupportGroupTutPage))) {
						driver.findElement(By.name(joinSupportGroupTutPage))
								.click();
						swipeLeft();
						driver.manage().timeouts()
								.implicitlyWait(20, TimeUnit.SECONDS);
					}
				}
			}
		}

		@Test(groups = "main", priority = 9, enabled = true)
		public void logIn() throws Exception {
			System.out.println("logIn");

			WebElement elementUserName = driver.findElement(By
					.id("login_username_edittext"));
			WebElement elementUserPassword = driver.findElement(By
					.id("login_password_edittext"));
			WebElement elementUserSubmit = driver.findElement(By
					.id("login_submit_button"));

			elementUserName.sendKeys(username);
			elementUserPassword.sendKeys(password);
			elementUserSubmit.click();

			driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);

		}

		@Test(groups = "loginIssues", priority = 2, enabled = true)
		public void forgotPasswordInValidEmail() throws MalformedURLException {
			System.out.println("forgot Password InValid Email");

			WebElement elementTvForgetPassword = driver.findElement(By
					.id("login_forgot_password_textview"));
			elementTvForgetPassword.click();

			WebElement elementEtEmail = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/forgotPassword_email")));

			// no such email in DB
			elementEtEmail.sendKeys("test212@gmail.com");
			WebElement elementBSubmit = driver
					.findElement(By
							.id("com.microdoers.projecttoe:id/forgotPassword_submit_button"));
			elementBSubmit.click();

			// wait until reset password msg appears
			WebElement elementLoginFailedMsgTitle = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/alertTitle")));

			// check for reset password title
			assertEquals(elementLoginFailedMsgTitle.getText(), resetPassFailed);
			WebElement elementOk = driver.findElement(By.id("android:id/button1"));
			elementOk.click();
			elementEtEmail.clear();

			System.out.println("forgot Password InValid Email Successful!");

			driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);

		}

		@Test(groups = "loginIssues", priority = 3, enabled = true)
		public void forgotPasswordValidEmail() throws MalformedURLException {
			System.out.println("forgot Password Valid Email");

			WebElement elementEtEmail = driver.findElement(By
					.id("com.microdoers.projecttoe:id/forgotPassword_email"));

			elementEtEmail.sendKeys("noha.elmetwally@microdoers.com");
			WebElement elementBSubmit = driver
					.findElement(By
							.id("com.microdoers.projecttoe:id/forgotPassword_submit_button"));
			elementBSubmit.click();

			// wait until reset password msg appears
			WebElement elementLoginFailedMsgTitle = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/alertTitle")));

			// check for reset password title
			assertEquals(elementLoginFailedMsgTitle.getText(), resetPassSuccess);
			WebElement elementOk = driver.findElement(By.id("android:id/button1"));
			elementOk.click();

			System.out.println("forgot Password Valid Email Successful!");

			driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);

		}

		@Test(groups = "loginIssues", priority = 4, enabled = true)
		public void loginWithInvalidCredentials() throws MalformedURLException {
			// replace here to make test fail
			System.out.println("login With Invalid Credentials");

			WebElement elementUserName = driver.findElement(By
					.id("login_username_edittext"));
			elementUserName.sendKeys("invalidEmail");
			WebElement elementUserPassword = driver.findElement(By
					.id("login_password_edittext"));
			elementUserPassword.sendKeys("wrongPassword");
			WebElement elementUserSubmit = driver.findElement(By
					.id("login_submit_button"));
			elementUserSubmit.click();

			// wait until msg appears
			WebElement elementLoginFailedMsgTitle = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/alertTitle")));

			// check for login failed dialog title
			assertEquals(elementLoginFailedMsgTitle.getText(), loginFailedMsg);
			WebElement elementOk = driver.findElement(By.id("android:id/button1"));
			elementOk.click();

			elementUserName.clear();
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
			elementUserPassword.clear();

			System.out.println("login With Invalid Credentials Successfully!");

			driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);

		}

		@Test(groups = "newsfeed", priority = 12, enabled = true)
		public void makePost() throws Exception {
			System.out.println("makePost");
			WebElement elementAddNewPost = driver.findElement(By
					.id("fab_add_new_post"));

			elementAddNewPost.click();
			WebElement elementAddNewPostTxt = driver.findElement(By
					.id("add_new_post_txt"));

			elementAddNewPostTxt.sendKeys(postContent);
			driver.findElement(By.id("action_addNewPost")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			if (isElementPresent(By.id("com.microdoers.projecttoe:id/alertTitle"))) {
				WebElement elementPostFailed = driver.findElement(By
						.id("com.microdoers.projecttoe:id/alertTitle"));
				assertEquals(elementPostFailed.getText(), addPostFailed);
				System.out.println(driver.findElement(By.id("android:id/message"))
						.getText());
				driver.findElement(By.id("android:id/button1")).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			} else {
				System.out.println("Making a new post successful");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				checkPostSuccessful();
			}
		}

		public void checkPostSuccessful() throws Exception {
			// replace here to make test fail
			// first view in RecyclerView is a table
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			List<WebElement> elementOneInPostsUserName = driver
					.findElements(By
							.xpath("//android.widget.TextView[@resource-id='com.microdoers.projecttoe:id/post_userName']"));

			// check that username is correct and message is correct
			WebElement element = elementOneInPostsUserName.get(0);
			assertEquals(adminUsername, element.getText());
			List<WebElement> elementOneInPostsContent = driver
					.findElements(By
							.xpath("//android.widget.TextView[@resource-id='com.microdoers.projecttoe:id/post_comment']"));
			element = elementOneInPostsContent.get(0);
			assertEquals(postContent, element.getText());
			System.out.println("Finding post just added successful");
		}

		@Test(groups = "newsfeed", priority = 13, enabled = true)
		public void hugPost() throws Exception {
			// replace here to make test fail
			System.out.println("hug Post");
			List<WebElement> firstElementInPosts = driver
					.findElements(By
							.xpath("//android.widget.TextView[@resource-id='com.microdoers.projecttoe:id/post_likesNum']"));
			WebElement elementHugsNum = firstElementInPosts.get(0);
			int prevHugsNum = Integer.parseInt(elementHugsNum.getText());
			List<WebElement> firstElementHug = driver
					.findElements(By
							.xpath("//android.widget.ImageView[@resource-id='com.microdoers.projecttoe:id/post_hug_img']"));
			WebElement elementHug = firstElementHug.get(0);
			elementHug.click();
			int currentHugsNum = Integer.parseInt(elementHugsNum.getText());
			if (prevHugsNum < currentHugsNum) {
				System.out.println("Hug Post Successfully!");
			} else if (prevHugsNum > currentHugsNum) {
				System.out.println("Unhug Post Successfully!");
			} else {
				System.out.println("Hug Post Failed!");
			}
		}

		@Test(groups = "newsfeed", priority = 14, enabled = true)
		public void commentPost() throws Exception {
			// replace here to make test fail
			System.out.println("Comment Post");
			WebElement elementComment = driver.findElement(By
					.id("com.microdoers.projecttoe:id/comments_num_layout"));
			elementComment.click();
			WebElement elementCommentsNum = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/post_details_commentsNum")));
			int prevCommentsNum = Integer.parseInt(elementCommentsNum.getText());
			WebElement elementWriteComment = driver.findElement(By
					.id("com.microdoers.projecttoe:id/add_comment_txt"));
			elementWriteComment.sendKeys(greetingsAppium);
			driver.findElement(
					By.id("com.microdoers.projecttoe:id/add_comment_btn")).click();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			int currentCommentsNum = Integer.parseInt(elementCommentsNum.getText());

			if (prevCommentsNum < currentCommentsNum) {
				System.out.println("comment On Post Successfully!");
			} else {
				System.out.println("comment On Post Failed!");
			}
			((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);

		}

		@Test(groups = "groupsTab", priority = 20, enabled = true)
		public void upgradeUserToPremium() throws Exception {
			// replace here to make test fail
			try {
				System.out.println("Upgrade User To Premium");
				WebElement elementProfileIcon = driver.findElement(By
						.id("com.microdoers.projecttoe:id/action_profile"));
				elementProfileIcon.click();
				// found premium badge, user is premium
				(new WebDriverWait(driver, 60)).until(ExpectedConditions
						.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/admin_badge")));
				if (isElementPresent(By
						.id("com.microdoers.projecttoe:id/premium_badge"))) {
					upgradeUserToPremium = true;
					((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
					System.out.println("User is Premium");
					System.out.println("Upgrade User To Premium succeed");
					// user is not premium
				} else {
					try {
						driver.scrollTo("Become a Premium Member").click();
						(new WebDriverWait(driver, 60))
								.until(ExpectedConditions.presenceOfElementLocated(By
										.id("com.microdoers.projecttoe:id/subscription_month_btn")))
								.click();
						if (isElementPresent(By.name("Error"))) {
							driver.findElement(
									By.id("com.android.vending:id/continue_button"))
									.click();
							((AndroidDriver) driver)
									.pressKeyCode(AndroidKeyCode.BACK);
							(new WebDriverWait(driver, 60))
									.until(ExpectedConditions.presenceOfElementLocated(By
											.id("com.microdoers.projecttoe:id/subscription_month_btn")));
							((AndroidDriver) driver)
									.pressKeyCode(AndroidKeyCode.BACK);
							System.out.println("couldn't upgrade error occurred ");
						} else {

							WebElement elementDialogButton = (new WebDriverWait(
									driver, 60))
									.until(ExpectedConditions.presenceOfElementLocated(By
											.id("com.android.vending:id/continue_button")));
							(new WebDriverWait(driver, 60))
									.until(ExpectedConditions.presenceOfElementLocated(By
											.id("com.microdoers.projecttoe:id/admin_badge")));
							elementDialogButton.click();
							upgradeUserToPremium = true;
							((AndroidDriver) driver)
									.pressKeyCode(AndroidKeyCode.BACK);
							System.out
									.println("subscribe To Premium Monthely succeed");
							System.out.println("else");

						}
					} catch (Exception e) {
						((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
						System.out.println("Couldn't find Become a Premium Member");
						System.out.println("Upgrade User To Premium Failed");
					}
				}
			} catch (NotFoundException e) {
				System.out.println("Upgrade User To Premium");
			}
		}

		@Test(groups = "groupsTab", priority = 21, enabled = true)
		public void loadGroups() throws Exception {
			// replace here to make test fail
			System.out.println("load Groups");
			driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
			List<WebElement> elementGroupTab = driver
					.findElements(By
							.xpath("//android.widget.ImageView[@resource-id='com.microdoers.projecttoe:id/tabIcon']"));
			elementGroupTab.get(1).click();
			driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
			// check for YOUR SUPPORT GROUP
			List<WebElement> elementYourSupportGroupSection = (driver
					.findElements(By
							.xpath("//android.widget.TextView[@resource-id='com.microdoers.projecttoe:id/group_header_txt']")));
			assertNotNull(elementYourSupportGroupSection.get(0));
			// check if there's actual groups showing
			List<WebElement> elementYourSupportGroups = driver
					.findElements(By
							.xpath("//android.widget.ImageButton[@resource-id='com.microdoers.projecttoe:id/group_wall_bttn']"));
			assertNotEquals(elementYourSupportGroups.size(), 0);
			System.out.println(elementYourSupportGroupSection.get(0).getText());

			// check for RECOMMENDED SUPPORT GROUPS
			/*
			 * driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS); try
			 * { if (!isElementPresent(By.name("RECOMMENDED SUPPORT GROUPS"))) {
			 * WebElement elementRecommendedGroups = driver
			 * .scrollTo("RECOMMENDED SUPPORT GROUPS");
			 * assertNotNull(elementRecommendedGroups); } } catch (NotFoundException
			 * e) { System.out.println("Could not find RECOMMENDED SUPPORT GROUPS");
			 * }
			 */

			System.out.println("load Groups successfully!");
		}

		@Test(groups = "groupsTab", priority = 22, enabled = true)
		public void searchGroup() throws Exception {
			// replace here to make test fail
			System.out.println("search group");
			try {
				WebElement elementJoinSuppGroup = driver.findElement(By
						.id("com.microdoers.projecttoe:id/join_support_group_btn"));
				elementJoinSuppGroup.click();
				WebElement elementTypeYourSearch = (new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/autoCompleteSearch")));
				elementTypeYourSearch.sendKeys("Self Confiden");
				driver.findElement(By.name("Self Confidence")).click();
				System.out.println("Self Confidence found!");
				// go back to search activity
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);

				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/autoCompleteSearch")));
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				// go back to groups tab
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);

				System.out.println("search group success!");
			} catch (NotFoundException e) {
				System.out.println("search group failed, something went wrong");
			}
		}

		@Test(groups = "groupsTab", priority = 23, enabled = true)
		public void addGroup() throws Exception {
			// replace here to make test fail
			System.out.println("add group");
			WebElement elementStartGroup = driver.findElement(By
					.id("com.microdoers.projecttoe:id/start_group_bttn"));
			elementStartGroup.click();
			// group name edit text
			WebElement elementGroupName = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/group_name")));
			dateNow = new SimpleDateFormat("dd-MM-YY-hh:mm").format(new Date());
			groupName = groupName + dateNow;
			elementGroupName.sendKeys(groupName);
			groupName = groupName.replace(' ', '_');
			((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
			// group description edit text
			WebElement elementGroupDesc = driver.findElement(By
					.id("com.microdoers.projecttoe:id/group_description"));
			elementGroupDesc.sendKeys(groupDesc);
			((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
			// group keywords edit text
			WebElement elementGroupKeyWords = driver.findElement(By
					.id("com.microdoers.projecttoe:id/group_keywords"));
			elementGroupKeyWords.sendKeys(groupKeyword);
			((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
			// click add button
			try {
				// It's not always visible on the screen so we scroll to it first
				if (!isElementPresent(By
						.id("com.microdoers.projecttoe:id/add_group_bttn"))) {
					driver.scrollTo("Add");
				}
				driver.findElement(
						By.id("com.microdoers.projecttoe:id/makeGroupPrivate"))
						.click();
				WebElement elementAddGroup = driver.findElement(By
						.id("com.microdoers.projecttoe:id/add_group_bttn"));
				elementAddGroup.click();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			} catch (NotFoundException e) {
				System.out.println("Could not click Add");
			}
			// check if creating group failed and dialog shows up
			if (isElementPresent(By.name("Create group Failed"))) {
				driver.findElement(By.id("android:id/button1")).click();
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				// change in new apk
			} else if (isElementPresent(By.name("Ready to add \"Helpers?\""))) {
				driver.findElement(By.id("android:id/button2")).click();
				// boost dialog
				if (isElementPresent(By.name("Boost"))) {
					driver.findElement(By.id("android:id/button2")).click();
					WebElement elementCancelButton = (new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/pre_group_view_cancel")));
					elementCancelButton.click();
					newGroupAdded = true;
					System.out.println("add group successfully!");
				}
			} else {
				fail("Did not add group successfully");

			}

		}

		@Test(groups = "groupsTab", priority = 24, enabled = true)
		public void ubgradeGroupToPremium() throws Exception {
			// replace here to make test fail
			System.out.println("Ubgrade Group To Premium");
			if (upgradeUserToPremium) {
				// scroll to upgrade to premium
				try {
					driver.scrollTo("Upgrade To Premium").click();
					WebElement elementOK = (new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("android:id/button1")));
					elementOK.click();
					if (!isElementPresent(By
							.name("Sorry, you exceeded the threshold of premium networks allowed"))) {
						upgradeGroupToPremium = true;
					} else {
						System.out
								.println("Sorry, you exceeded the threshold of premium networks allowed");
						elementOK.click();
					}
				} catch (Exception e) {
					System.out.println("Couldn't find Upgrade To Premium");
					System.out.println("Upgrade To Premium Failed");
				}
			} else {
				System.out.println("user is not premium");
			}
		}

		@Test(groups = "groupsTab", priority = 25, enabled = true)
		public void makeGroupHelper() throws Exception {
			// replace here to make test fail
			if (upgradeGroupToPremium) {
				System.out.println("Make Group Helper");
				// check for reviews
				try {
					(new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/action_edit_profile")))
							.click();
					(new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/admin_options_button")))
							.click();
					(new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/makeNetworkHelperMode")))
							.click();
					((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
					System.out.println("Make Group Helper-Mode Successfully!");
				} catch (Exception e) {
					System.out.println("Couldn't Make Group in Helper-Mode");
					System.out.println("Make Group Helper Failed");
				}
			}
		}

		@Test(groups = "groupsTab", priority = 26, enabled = true)
		public void checkGroupDetails() throws Exception {
			// replace here to make test fail
			System.out.println("check group details");
			// check for reviews
			try {
				if (isElementPresent(By
						.id("com.microdoers.projecttoe:id/number_of_reviews_txt"))) {
					WebElement elementReviews = (new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/number_of_reviews_txt")));
					int reviewsNum = Integer.parseInt(elementReviews.getText()
							.substring(1, elementReviews.getText().length() - 1));
					if (reviewsNum > 0) {
						System.out.println("#" + groupName + " has " + reviewsNum
								+ " reviews");
					}
				} else {
					if (isElementPresent(By.name("Write Review")))
						System.out.println(groupName + " doesn't have reviews");
				}
			} catch (Exception e) {
				System.out.println("Couldn't find group reviews");
			}
			// check for private icone, this group is private
			try {
				// Maybe It's not visible on the screen so we scroll to it first
				if (!isElementPresent(By
						.id("com.microdoers.projecttoe:id/private_group_lock"))) {
					driver.scrollTo(groupDesc);
				}
				WebElement elementlockedgroup = driver.findElement(By
						.id("com.microdoers.projecttoe:id/private_group_lock"));
				System.out.println("group lock found this group is private");

			} catch (NotFoundException e) {
				System.out.println("Couldn't find lock icon, something went wrong");
			}
			if (upgradeGroupToPremium) {
				// check for helper icone, this group is helper try
				try {
					// Maybe It's not visible on the screen so we scroll to it first
					if (!isElementPresent(By
							.id("com.microdoers.projecttoe:id/helper_mode_icon"))) {
						System.out
								.println("Couldn't find helper icon, something went wrong");
					} else {
						System.out.println("group helper mode found");
					}

				} catch (NotFoundException e) {
					System.out
							.println("Couldn't find helper icon, something went wrong");
				}
				// check for premium icone, this group is premium

				if (!isElementPresent(By
						.id("com.microdoers.projecttoe:id/premium_icon"))) {
					System.out
							.println("Couldn't find premium icon, something went wrong");
				} else {
					System.out.println("group is premium found");
				}

			}
			// check for group members
			try {
				// Maybe It's not visible on the screen so we scroll to it first
				if (!isElementPresent(By
						.id("com.microdoers.projecttoe:id/group_members"))) {
					driver.scrollTo("GROUP MEMBERS ");
				}
				WebElement elementGroupMembers = driver.findElement(By
						.id("com.microdoers.projecttoe:id/group_members"));
				System.out.println("group members found");
				int groupMembersNum = Integer.parseInt(elementGroupMembers
						.getText().substring(15,
								elementGroupMembers.getText().length() - 1));
				if (groupMembersNum > 0) {
					System.out.println("group members count is " + groupMembersNum);
				} else {

					System.out
							.println("something went wrong, group has no members");
				}
			} catch (NotFoundException e) {
				System.out
						.println("Couldn't find group members, something went wrong");
			}
			// check for group alerts
			// Someone joins
			try {
				// scroll to group posts alert to make sure all three alerts are
				// visible in the screen
				driver.scrollTo("Group Posts");
				if (!isElementPresent(By
						.id("com.microdoers.projecttoe:id/alert_broadcast_message_bttn")))
					System.out.println("found Someone join alert");
			} catch (NotFoundException e) {
				System.out
						.println("Couldn't find Someone join alert, something went wrong");
			}
			// Someone posts
			try {
				if (isElementPresent(By
						.id("com.microdoers.projecttoe:id/alert_someone_posts_bttn")))
					System.out.println("found Someone posts");
			} catch (NotFoundException e) {
				System.out
						.println("Couldn't find Someone posts, something went wrong");
			}
			// Group Posts
			try {
				if (isElementPresent(By
						.id("com.microdoers.projecttoe:id/alert_broadcast_message_bttn")))
					System.out.println("found Group Posts");
			} catch (NotFoundException e) {
				System.out
						.println("Couldn't find Group Posts, something went wrong");
			}
			// check for view group wall button
			try {
				if (isElementPresent(By
						.id("com.microdoers.projecttoe:id/post_on_group_button")))
					System.out.println("found View Group Wall Button");
			} catch (NotFoundException e) {
				System.out
						.println("Couldn't find Group Posts, something went wrong");
			}
			// ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
			System.out.println("Group Details Successful!");
		}

		// make write review after create group
		@Test(groups = "groupsTab", priority = 27, enabled = true)
		public void canWriteReview() throws Exception {
			System.out.println("can Write Review");
			if (newGroupAdded) {
				try {
					// check to see if we just added a group
					// if no group added choose another group this user is joining
					// check to see if this group has no reviews
					if (isElementPresent(By
							.id("com.microdoers.projecttoe:id/write_review_button"))) {
						System.out.println("this group has no reviews");
						WebElement elementWriteReview = driver
								.findElement(By
										.id("com.microdoers.projecttoe:id/write_review_button"));
						elementWriteReview.click();
						writeReview();
					} else {
						// this group has previuos reviews
						if (isElementPresent(By
								.id("com.microdoers.projecttoe:id/ratingBar"))) {
							System.out.println("this group has reviews");
							WebElement elementRatingBar = driver.findElement(By
									.id("com.microdoers.projecttoe:id/ratingBar"));
							elementRatingBar.click();
							// click on add reviews button (circle)
							(new WebDriverWait(driver, 60))
									.until(ExpectedConditions.presenceOfElementLocated(By
											.id("com.microdoers.projecttoe:id/fab_add_new_review")))
									.click();
							writeReview();
						} else {
							System.out
									.println("didn't find review, something went wrong");
						}
					}
				} catch (NotFoundException e) {
					System.out.println("Could not click on group");
				}
			} else {
				System.out
						.println("Could not write review no group has been created");
			}
		}

		// for choosing rate and write review
		private void writeReview() {
			// TODO Auto-generated method stub
			WebElement elementActualRatingBar = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/rating_bar_add")));
			elementActualRatingBar.click();
			WebElement elementWritReviewet = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/add_new_review_txt")));
			elementWritReviewet.sendKeys("Great!! Appium");
			driver.findElement(
					By.id("com.microdoers.projecttoe:id/action_addNewReview"))
					.click();
			// check to see if failed alert appears
			if (isElementPresent(By.name("Add Review Failed"))) {
				WebElement elementErrorMsg = driver.findElement(By
						.id("android:id/message"));
				System.out.println(elementErrorMsg.getText());
				driver.findElement(By.id("android:id/button1")).click();
				// get rid of keyboard
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				if (isElementPresent(By.name("Great!! Appium"))) {
					((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				}
				// back to group wall
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/action_edit_profile")));
				// back to group tab
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
			} else {
				// if no alert appears then review has been written
				// successfully
				/*
				 * (new WebDriverWait(driver, 60))
				 * .until(ExpectedConditions.presenceOfElementLocated(By
				 * .id("com.microdoers.projecttoe:id/action_edit_profile")));
				 * ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				 */
				System.out.println("Review has been written successfully!");
			}

		}

		@Test(groups = "groupsTabNonadmin", priority = 28, enabled = true)
		public void canBoostGroup() throws Exception {
			// replace here to make test fail
			System.out.println("Can Boost Group");
			if (newGroupAdded) {
				try {
					// click on action bar menu
					(new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/action_edit_profile")))
							.click();
					// click on BOOST
					(new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/boost_button")))
							.click();
					// click on one day
					(new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/one_day_bttn")))
							.click();
					if (isElementPresent(By.name("Error"))) {
						driver.findElement(
								By.id("com.android.vending:id/continue_button"))
								.click();
						((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
						(new WebDriverWait(driver, 60))
								.until(ExpectedConditions.presenceOfElementLocated(By
										.id("com.microdoers.projecttoe:id/one_day_bttn")));
						((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
						System.out.println("couldn't boost error occurred");
					} else {

						WebElement elementDialogButton = (new WebDriverWait(driver,
								60))
								.until(ExpectedConditions.presenceOfElementLocated(By
										.id("com.android.vending:id/continue_button")));
						elementDialogButton.click();
						System.out.println("can boost group succeed");
					} // go to groups tab
					(new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/action_edit_profile")));
				} catch (Exception e) {
					System.out.println("Couldn't find menu");
				}
			} else {
				System.out.println("no new group added, cannot boost");
			}
		}

		@Test(groups = "groupsTabNonadmin", priority = 29, enabled = true)
		public void canDowngradeGroup() throws Exception {
			// replace here to make test fail
			System.out.println("Can Downgrade Group");
			if (newGroupAdded && upgradeGroupToPremium) {
				try {
					// click on action bar menu
					(new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/action_edit_profile")))
							.click();
					if (!isElementPresent(By.name("Admin Panel"))) {
						(new WebDriverWait(driver, 60))
								.until(ExpectedConditions.presenceOfElementLocated(By
										.id("com.microdoers.projecttoe:id/action_edit_profile")))
								.click();
					}
					// click on option menu
					(new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/admin_options_button")))
							.click();
					// click on downgrade network
					(new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/downgrade_network")))
							.click();
					// click on yes
					(new WebDriverWait(driver, 60)).until(
							ExpectedConditions.presenceOfElementLocated(By
									.id("android:id/button1"))).click();
					((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
					// check for helper icone, this group is helper try
					try {
						// Maybe It's not visible on the screen so we scroll to it
						// first
						if (!isElementPresent(By
								.id("com.microdoers.projecttoe:id/helper_mode_icon"))) {
							System.out.println("can downgrade group successfully!");
						} else {
							System.out.println("can downgrade group Failed");
						}

					} catch (NotFoundException e) {
						System.out
								.println("Couldn't find helper icon, something went wrong");
					}
					(new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/action_edit_profile")));
					((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				} catch (Exception e) {
					System.out.println("Couldn't find menu");
				}
			} else {
				System.out.println("Group is not even premium");
			}
		}

		@Test(groups = "groupsTabNonadmin", priority = 30, enabled = true)
		public void canLeaveJoinPublicGroup() throws Exception {
			// replace here to make test fail
			System.out.println("Can Leave Join Public Group");
			try {
				// search for #My_School and open group wall
				(new WebDriverWait(driver, 60)).until(
						ExpectedConditions.presenceOfElementLocated(By
								.name("#My_School"))).click();
			} catch (NotFoundException e) {
				System.out.println("Could not click #My_School");
			}
			try {
				// open menu
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/action_edit_profile")))
						.click();

				// click on leave group
				WebElement editProfile;
				if (!isElementPresent(By
						.id("com.microdoers.projecttoe:id/leave_group_button"))) {
					editProfile = (AndroidElement) driver
							.findElement(By
									.id("com.microdoers.projecttoe:id/action_edit_profile"));
					editProfile.click();
				}
				WebElement elementLeaveGroup = (AndroidElement) driver
						.findElement(By
								.id("com.microdoers.projecttoe:id/leave_group_button"));
				elementLeaveGroup.click();
				// wait for alert "Leave Group"
				WebElement elementLeaveGroupAlert = (new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/alertTitle")));
				assertEquals(elementLeaveGroupAlert.getText(), "Leave Group!");
				// choose yes
				WebElement elementYes = driver.findElement(By
						.id("android:id/button1"));
				elementYes.click();
				// check if "Failed" alert appears
				if (isElementPresent(By.name("Leaving Group Failed"))) {
					System.out.println("leaving group faild, something went wrong");
				} else {
					// if it's not visible so everything went good
					System.out.println("leaving group succeed");
					try {
						// go to "#My_School" group
						AndroidElement list = (AndroidElement) driver
								.findElement(By
										.id("com.microdoers.projecttoe:id/groups_list"));
						MobileElement mySchool = list
								.findElement(MobileBy
										.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
												+ "new UiSelector().text(\"#My_School\"));"));
						assertNotNull(mySchool.getLocation());
						driver.findElement(By.name("#My_School")).click();
						// join "#My_School" group again
						WebElement elementJoinGroupButton = (new WebDriverWait(
								driver, 60))
								.until(ExpectedConditions.presenceOfElementLocated(By
										.id("com.microdoers.projecttoe:id/join_group_button")));
						elementJoinGroupButton.click();
						// check if the alert appears is introduce yourself to
						// others
						// has "no thanks" button
						WebElement elementNoThanks = (new WebDriverWait(driver, 60))
								.until(ExpectedConditions
										.presenceOfElementLocated(By
												.id("android:id/button2")));
						assertEquals(elementNoThanks.getText(), "No Thanks");
						elementNoThanks.click();
						System.out.println("joined #My_School successfully!");
						System.out.println("Can Leave Join Public Group succeed");
						// go to griup tab
						((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);

					} catch (Exception e) {
						System.out
								.println("something went wrong couldn't join group");
					}

				}
			} catch (Exception e) {
				System.out
						.println("Couldn't find admin panel, Can't leave join public group");
			}
		}

		@Test(groups = "groupsTabNonadmin", priority = 31, enabled = true)
		public void canLeaveJoinPrivateGroup() throws Exception {
			// replace here to make test fail
			System.out.println("can Leave Join Private Group");
			try {
				AndroidElement list = (AndroidElement) driver.findElement(By
						.id("com.microdoers.projecttoe:id/groups_list"));
				MobileElement privateGorup = list
						.findElement(MobileBy
								.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
										+ "new UiSelector().text(\""
										+ privateGroupName + "\"));"));
				assertNotNull(privateGorup.getLocation());
				driver.findElement(By.name(privateGroupName)).click();
			} catch (Exception e) {
				System.out.println("Could not click " + privateGroupName);
			}
			try {
				// open menu
				WebElement elementActionBar = (new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/action_edit_profile")));
				elementActionBar.click();
				// click on leave group
				WebElement elementLeaveGroup = (new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/leave_group_button")));
				elementLeaveGroup.click();
				// wait for alert "Leave Group"
				WebElement elementLeaveGroupAlert = (new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/alertTitle")));
				assertEquals(elementLeaveGroupAlert.getText(), "Leave Group!");
				// choose yes
				WebElement elementYes = driver.findElement(By
						.id("android:id/button1"));
				elementYes.click();
				// check if "Failed" alert appears
				if (isElementPresent(By.name("Leaving Group Failed"))) {
					System.out.println("leaving group faild, something went wrong");
				} else {
					// if it's not visible so everything went good
					System.out.println("leaving group succeed");
					try {
						// go to privateGroupName group
						AndroidElement list = (AndroidElement) driver
								.findElement(By
										.id("com.microdoers.projecttoe:id/groups_list"));
						MobileElement privateGroup = list
								.findElement(MobileBy
										.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
												+ "new UiSelector().text(\""
												+ privateGroupName + "\"));"));
						assertNotNull(privateGroup.getLocation());
						driver.findElement(By.name(privateGroupName)).click();
						// join privateGroupName group again
						WebElement elementJoinGroupButton = (new WebDriverWait(
								driver, 60))
								.until(ExpectedConditions.presenceOfElementLocated(By
										.id("com.microdoers.projecttoe:id/join_group_button")));
						elementJoinGroupButton.click();
						// check if "Join Request sent !" button appears
						WebElement elementJoinRequestSent = (new WebDriverWait(
								driver, 60))
								.until(ExpectedConditions.presenceOfElementLocated(By
										.id("com.microdoers.projecttoe:id/join_hidden_button")));
						elementJoinRequestSent.click();
						System.out.println("join " + privateGroupName
								+ " request sent successfully!");
						System.out.println("Can Leave Join private Group succeed");
						// go to griup tab
						((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);

					} catch (NotFoundException e) {
						System.out
								.println("something went wrong couldn't join group");
					}
				}
			} catch (Exception e) {
				System.out.println("didn't find leave group");

			}
		}

		@Test(groups = "adminPanel", priority = 32, enabled = true)
		public void canBlockUnblockUsers() throws Exception {
			// replace here to make test fail
			System.out.println("Can Block Users");
			try { // go to "#AppiumTestAndroid" group
				AndroidElement list = (AndroidElement) driver.findElement(By
						.id("com.microdoers.projecttoe:id/groups_list"));
				MobileElement appiumTestAndroid2 = list
						.findElement(MobileBy
								.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
										+ "new UiSelector().text(\"#AppiumTestAndroid2\"));"));
				assertNotNull(appiumTestAndroid2.getLocation());
				driver.findElement(By.name("#AppiumTestAndroid2")).click();
				// click on action bar menu
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/action_edit_profile")))
						.click();
				// click on admin panel in menu
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/admin_options_button")))
						.click();
				// click on clocked users
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/blocked_users_lable")))
						.click();

				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				// configure block/unlock buttons in this screen
				List<WebElement> elementBlockedUsers = driver
						.findElements(By
								.xpath("//android.widget.Button[@resource-id='com.microdoers.projecttoe:id/admin_helper_bttn']"));
				// no blocked users in this group so the first button is block
				elementBlockedUsers.get(0).click();
				System.out.println("blocked user");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				// when we hit block, first button becomes unlock so we hit it again
				// to unlock this user
				elementBlockedUsers.get(0).click();
				System.out.println("unblocked user");
				// back to admin panel screen
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/blocked_users_lable")));

				System.out.println("Can Block Users successful!");
			} catch (NotFoundException e) {
				System.out.println("couldn't block user, Something went wrong!");
			}
		}

		@Test(groups = "adminPanel", priority = 33, enabled = true)
		public void canBroadcastMessage() throws Exception {
			// replace here to make test fail
			System.out.println("can Broadcast Message");

			try {
				// click on msg all users
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/message_all_users_lable")))
						.click();
				// write post
				dateNow = new SimpleDateFormat("dd-MM-YY-hh:mm").format(new Date());
				postContent = postContent + dateNow;
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/add_new_post_txt")))
						.sendKeys(postContent);
				driver.findElement(
						By.id("com.microdoers.projecttoe:id/action_addNewPost"))
						.click();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				// configure all comments
				List<WebElement> elementBlockedUsers = driver
						.findElements(By
								.xpath("//android.widget.TextView[@resource-id='com.microdoers.projecttoe:id/post_comment']"));
				assertEquals(postContent, elementBlockedUsers.get(0).getText());
				// go to admin panel activity
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/message_all_users_lable")));

				System.out.println("can Broadcast Message successfully!");

			} catch (Exception e) {
				fail("Did not broadcast successfully: " + e.getLocalizedMessage());
				throw e;
			}

		}

		@Test(groups = "adminPanel", priority = 34, enabled = true)
		public void canAddRemoveHelper() throws Exception {
			// replace here to make test fail
			System.out.println("Can add remove helper");

			try {
				// click on group admins
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/group_admins_lable")))
						.click();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				// configure block/unlock buttons in this screen
				List<WebElement> elementBlockedUsers = driver
						.findElements(By
								.xpath("//android.widget.Button[@resource-id='com.microdoers.projecttoe:id/admin_helper_bttn']"));
				// no helper in this group so the first button is add helper
				elementBlockedUsers.get(0).click();
				System.out.println("added helper");
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				// when we hit add helper, first button becomes remove helper so
				// we hit it again
				// to remove helper
				elementBlockedUsers.get(0).click();
				System.out.println("removed helper");

				// go to admin panel activity
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/message_all_users_lable")));
				// go to group profile
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/action_edit_profile")));
				System.out.println("can add remove helper successfully!");

			} catch (Exception e) {
				fail("Did not add/remove successfully: " + e.getLocalizedMessage());
				throw e;
			}

		}

		@Test(groups = "adminPanel", priority = 35, enabled = true)
		public void canTogglePrivateSwitch() throws Exception {
			// replace here to make test fail
			System.out.println("can Toggle Private Switch");
			try {
				// click on action bar menu
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/action_edit_profile")))
						.click();
				// click on admin panel in menu
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/admin_options_button")))
						.click();
				// click on Make Group Private OFF
				System.out.println("making group public");
				try {
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					driver.findElement(
							By.id("com.microdoers.projecttoe:id/makeNetworkPrivate"))
							.click();
					driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

					if (isElementPresent(By
							.id("com.microdoers.projecttoe:id/pending_requests_label"))) {
						System.out.println("group didn't become public!");
					} else {
						System.out.println("group is public");
					}

				} catch (Exception e) {
					System.out.println("didn't find make group private"
							+ e.getMessage());
				}
				// click on Make Group Private ON
				try {
					System.out.println("making group private again");
					driver.findElement(
							By.id("com.microdoers.projecttoe:id/makeNetworkPrivate"))
							.click();
					driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
					if (isElementPresent(By
							.id("com.microdoers.projecttoe:id/pending_requests_label"))) {
						System.out.println("group is private");

					} else {
						System.out.println("group didn't become private!");
					}

				} catch (Exception e) {
					System.out.println("didn't find make group private"
							+ e.getMessage());
				}

				// go to group profile
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/action_edit_profile")));
				System.out.println("Can Toggle Private successfully!");
			} catch (Exception e) {
				System.out.println("didn't find admin panel, something went wrong");
			}

		}

		@Test(groups = "adminPanel", priority = 36, enabled = true)
		public void canToggleHelperSwitch() throws Exception {
			// replace here to make test fail
			System.out.println("Can Toggle Helper Switch");
			if (upgradeGroupToPremium) {
				try {
					// click on action bar menu
					(new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/action_edit_profile")))
							.click();
					// click on admin panel in menu
					(new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/admin_options_button")))
							.click();
					// click on Helper Mode ON
					try {
						if (!isElementPresent(By
								.id("com.microdoers.projecttoe:id/makeNetworkHelperMode"))) {
							driver.scrollTo("Helper Mode ON");
						}
						driver.findElement(
								By.id("com.microdoers.projecttoe:id/makeNetworkHelperMode"))
								.click();
						System.out.println("switched helper mode on");
						driver.manage().timeouts()
								.implicitlyWait(2, TimeUnit.SECONDS);
						driver.findElement(
								By.id("com.microdoers.projecttoe:id/makeNetworkHelperMode"))
								.click();
						System.out.println("switched helper mode off");

					} catch (Exception e) {
						System.out.println("didn't find helper mode"
								+ e.getMessage());
					}
					// go to group profile
					((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
					(new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/action_edit_profile")));
					/*
					 * // go to group tab ((AndroidDriver)
					 * driver).pressKeyCode(AndroidKeyCode.BACK);
					 */
					System.out.println("Can Toggle Helper Switch successfully!");
				} catch (Exception e) {
					System.out
							.println("didn't find admin panel, something went wrong");
				}
			} else {
				System.out.println("Group is not premium");
			}
		}

		@Test(groups = "adminPanel", priority = 37, enabled = true)
		public void canAddPending() throws Exception {
			// replace here to make test fail
			System.out.println("Can Add Pending");
			int currentUsersNum = 0;
			/*
			 * try { driver.scrollToExact("#AppiumTestAndroid2").click(); } catch
			 * (NotFoundException e) { fail("Unable to find group"); throw e; }
			 */

			try {
				// get old members count
				WebElement elementGroupMembers = (new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/group_members")));
				int oldUsersNum = Integer.parseInt(elementGroupMembers.getText()
						.substring(15, elementGroupMembers.getText().length() - 1));
				// click on action bar menu
				driver.findElement(
						By.id("com.microdoers.projecttoe:id/action_edit_profile"))
						.click();
				// click on admin panel in menu
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/admin_options_button")))
						.click();
				try {
					// click on group admins
					(new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/pending_requests_label")))
							.click();
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					// check to see if No Pending Requests Yet! is visible
					if (!isElementPresent(By
							.id("com.microdoers.projecttoe:id/no_note_txt"))) {
						// configure add buttons
						List<WebElement> elementAddUsers = driver
								.findElements(By
										.xpath("//android.widget.Button[@resource-id='com.microdoers.projecttoe:id/add_pending_bttn']"));
						elementAddUsers.get(0).click();
						driver.manage().timeouts()
								.implicitlyWait(600, TimeUnit.SECONDS);

						// go to admin panel activity
						(new WebDriverWait(driver, 60)).until(ExpectedConditions
								.presenceOfElementLocated(By
										.name("Pending Requests")));
					} else {
						System.out.println("there're no pending requests");
					}
					((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);

					(new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/message_all_users_lable")));
					// go to group profile
					((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
					(new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/action_edit_profile")));
					elementGroupMembers = (new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/group_members")));
					currentUsersNum = Integer.parseInt(elementGroupMembers
							.getText().substring(15,
									elementGroupMembers.getText().length() - 1));
					if (currentUsersNum > oldUsersNum) {
						System.out.println("user added succssefully");
					} else {
						System.out.println("didn't add user");
					}

					System.out.println("Can Add Pending successfully!");

				} catch (Exception e) {
					fail("something went wrong in add pending: "
							+ e.getLocalizedMessage());
					throw e;
				}
			} catch (NotFoundException e) {
				System.out.println("didn't find admin panel, something went wrong");
			}

		}

		@Test(groups = "adminPanel", priority = 38, enabled = true)
		public void canChangeGroupKeywords() throws Exception {
			// replace here to make test fail
			System.out.println("Can Change Group Keywords");
			try {
				// click on action bar menu
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/action_edit_profile")))
						.click();
				// click on admin panel in menu
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/admin_options_button")))
						.click();
				// click on Change Group Keywords
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/group_keywords_lable")))
						.click();
				try {
					// change keywords
					WebElement elementKeyWords = (new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/change_keywords_txt")));
					elementKeyWords.clear();
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					dateNow = new SimpleDateFormat("dd-MM-YY-hh:mm")
							.format(new Date());
					groupNewKeyword = groupNewKeyword + dateNow;
					elementKeyWords.sendKeys(groupNewKeyword);
					driver.findElement(
							By.id("com.microdoers.projecttoe:id/action_save_profile"))
							.click();
					System.out.println("keywords has changed");
				} catch (Exception e) {
					System.out.println("couldn't change keywords, exception is: "
							+ e.getMessage());
				}
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/message_all_users_lable")));
				// go to group profile
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/action_edit_profile")));
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				System.out.println("Can Change Group Keywords successfully!");
			} catch (Exception e) {
				System.out.println("didn't find admin panel, something went wrong");
			}
		}

		@Test(groups = "requestsTab", priority = 39, enabled = true)
		public void goToRequestsTab() throws Exception {
			// replace here to make test fail
			System.out.println("Go To Requests Tab");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			try {
				List<WebElement> elementTabs = driver
						.findElements(By
								.xpath("//android.widget.ImageView[@resource-id='com.microdoers.projecttoe:id/tabIcon']"));
				elementTabs.get(2).click();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				if (isElementPresent(By.name("FRIEND REQUESTS"))) {
					System.out.println("Go To Requests Tab succeed!");
				}
			} catch (NoSuchElementException e) {
				System.out
						.println("didn't find requests tab, something went wrong");
			}
		}

		@Test(groups = "requestsTab", priority = 40, enabled = true)
		public void loadRequests() throws Exception {
			// replace here to make test fail
			System.out.println("load Requests");

			if (isElementPresent(By.name("No Friend Requests"))) {
				System.out.println("there're no fiend requests");
			} else {
				if (isElementPresent(By.name("Confirm"))) {
					System.out.println("there're some friend requests");
				} else {
					System.out.println("No friend Requests title!");
				}
			}
			if (isElementPresent(By.name("Add Friend"))) {
				System.out.println("there're some recommended users");
			} else {
				System.out.println("there're no recommended users");
			}
			System.out.println("load Requests successfully");
		}

		@Test(groups = "chatTab", priority = 41, enabled = true)
		public void goToChatTab() throws Exception {
			// replace here to make test fail
			System.out.println("Open contacts tab");
			try {
				List<WebElement> elementTabs = driver
						.findElements(By
								.xpath("//android.widget.ImageView[@resource-id='com.microdoers.projecttoe:id/tabIcon']"));
				elementTabs.get(3).click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				if (isElementPresent(By.name("Chats"))) {
					System.out.println("Open chat tab succeed!");
				}
			} catch (NoSuchElementException e) {
				System.out.println("didn't find chat tab");
			}
			System.out.println("Open contacts tab successfully!");
		}

		@Test(groups = "chatTab", priority = 42, enabled = true)
		public void loadMessages() throws Exception {
			// replace here to make test fail
			System.out.println("Load messages");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			try {
				(new WebDriverWait(driver, 20)).until(ExpectedConditions
						.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/last_message")));

				// fonud conversations
				System.out.println("Load messages succeed");
			} catch (Exception e) {
				System.out.println("No messages found!");

			}
			System.out.println("Load messages successful");

		}

		@Test(groups = "chatTab", priority = 43, enabled = true)
		public void startPrivateChat() throws Exception {
			// replace here to make test fail
			System.out.println("Start Private Chat");
			// click on new chat
			driver.findElement(By.className("android.widget.ImageButton")).click();
			// click on new conversation
			(new WebDriverWait(driver, 60)).until(
					ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/menu_item_newConv")))
					.click();
			try {
				if (isElementPresent(By.name("test570"))) {
					// click on user test570
					(new WebDriverWait(driver, 60)).until(
							ExpectedConditions.presenceOfElementLocated(By
									.name("test570"))).click();
				}
			} catch (NoSuchElementException e) {
				// if start new conversation activity opened so soone, friends list
				// may not be show up
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				// click on new conversation
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/menu_item_newConv")))
						.click();
				// click on user test570
				(new WebDriverWait(driver, 60)).until(
						ExpectedConditions.presenceOfElementLocated(By
								.name("test570"))).click();
			}

			WebElement elementWriteMsg = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/message_edit_text")));
			dateNow = new SimpleDateFormat("dd-MM-YY-hh:mm").format(new Date());
			appiumChatMsg = postContent + " " + dateNow;
			elementWriteMsg.sendKeys(appiumChatMsg);
			driver.findElement(By.id("com.microdoers.projecttoe:id/send_button"))
					.click();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			(new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.name(appiumChatMsg)));
			try {
				List<WebElement> elementmsgs = driver
						.findElements(By
								.xpath("//android.widget.TextView[@resource-id='com.microdoers.projecttoe:id/cell_text']"));
				if (elementmsgs.get(elementmsgs.size() - 1).getText()
						.equals(appiumChatMsg)) {
					System.out.println("message has been posted successfully");
				} else {
					System.out.println(elementmsgs.get(elementmsgs.size() - 1)
							.getText());
					System.out.println(appiumChatMsg);
					System.out.println("message hasn't been posted successfully");
				}
			} catch (NoSuchElementException e) {
				System.out.println("didn't find the message, something wnet wrong "
						+ e.getMessage());
			}
			((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
			((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
			System.out.println("Start Private Chat Succeed!");
		}

		@Test(groups = "chatTab", priority = 44, enabled = true)
		public void startGroupChat() throws Exception {
			// replace here to make test fail
			System.out.println("Start Group Chat");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			// click on new chat
			driver.findElement(By.className("android.widget.ImageButton")).click();
			// click on new conversation
			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/menu_item_newGroup")))
					.click();
			try {
				if (isElementPresent(By.name("test570"))) {
					// click on user test570
					(new WebDriverWait(driver, 60)).until(
							ExpectedConditions.presenceOfElementLocated(By
									.name("test570"))).click();
					driver.findElement(By.name("test555")).click();
				}
			} catch (NoSuchElementException e) {
				// if start new conversation activity opened so soone, friends list
				// may not be show up
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				// click on new conversation
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/menu_item_newConv")))
						.click();
				// click on user test570
				(new WebDriverWait(driver, 60)).until(
						ExpectedConditions.presenceOfElementLocated(By
								.name("test570"))).click();
				driver.findElement(By.name("test555")).click();
			}
			driver.findElement(
					By.id("com.microdoers.projecttoe:id/action_start_group_conversation"))
					.click();
			WebElement elementWriteMsg = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/message_edit_text")));
			dateNow = new SimpleDateFormat("dd-MM-YY-hh:mm").format(new Date());
			appiumChatMsg = postContent + " " + dateNow;
			elementWriteMsg.sendKeys(appiumChatMsg);
			driver.findElement(By.id("com.microdoers.projecttoe:id/send_button"))
					.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			(new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By.name(appiumChatMsg)));
			try {
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				List<WebElement> elementmsgs = driver
						.findElements(By
								.xpath("//android.widget.TextView[@resource-id='com.microdoers.projecttoe:id/cell_text']"));
				if (elementmsgs.get(elementmsgs.size() - 1).getText()
						.equals(appiumChatMsg)) {
					System.out
							.println("message has been sent to group chat successfully");
				} else {

					System.out.println(elementmsgs.get(elementmsgs.size() - 1)
							.getText());

					System.out
							.println("message hasn't been sent to group chat successfully");
				}
			} catch (NoSuchElementException e) {
				System.out.println("didn't find the message, something wnet wrong "
						+ e.getMessage());
			}
			((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
			((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
			System.out.println("Start Group Chat Succeed!");
		}

		@Test(groups = "notificationsTab", priority = 50, enabled = true)
		public void goToNotificationsTab() throws Exception {
			// replace here to make test fail
			System.out.println("GoTo Notifications Tab");
			try {
				List<WebElement> elementTabs = driver
						.findElements(By
								.xpath("//android.widget.ImageView[@resource-id='com.microdoers.projecttoe:id/tabIcon']"));
				elementTabs.get(4).click();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				if (isElementPresent(By.name("Notifications"))) {
					System.out.println("Open Notifications tab succeed!");
				}
			} catch (NoSuchElementException e) {
				System.out.println("didn't find Notifications tab");
			}
			System.out.println("GoTo Notifications Tab successfully!");

		}

		@Test(groups = "notificationsTab", priority = 51, enabled = true)
		public void loadNotifications() throws Exception {
			// replace here to make test fail
			System.out.println("load Notifications");
			List<WebElement> elementNotifications = driver
					.findElements(By
							.xpath("//android.widget.TextView[@resource-id='com.microdoers.projecttoe:id/notification_desc_txt']"));
			if (elementNotifications.size() > 0) {
				System.out.println("load Notifications successfully!");
			} else {
				System.out.println("Notifications didn't load successfully!");
			}
		}

		@Test(groups = "notificationsTab", priority = 52, enabled = true)
		public void browseFromNotifications() throws Exception {
			// replace here to make test fail
			System.out.println("browseFromNotifications");
			String[] searchTerms = { "hug", "commented", "their profile",
					"approval", "posted", "reviewed", "congratulations",
					"assigned", "accepted" };
			for (String searchTerm : searchTerms) {
				scrollToNotification(searchTerm);
			}
		}

		private void scrollToNotification(String searchTerm) {

			try {
				System.out.println("searching for " + searchTerm);
				AndroidElement list = (AndroidElement) driver.findElement(By
						.id("com.microdoers.projecttoe:id/notifications_list"));
				MobileElement elemnet = list
						.findElement(MobileBy
								.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
										+ "new UiSelector().text(\""
										+ searchTerm
										+ "\"));"));
				assertNotNull(elemnet.getLocation());
				WebElement element = driver.findElement(By.name(searchTerm));
				String rowString = element.getAttribute("text");
				String[] words = rowString.split("\\s");
				element.click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				List<WebElement> elementTitle;
				switch (searchTerm) {
				case "hug":
				case "commented":
				case "posted":
					try {
						if (isElementPresent(By.name("Post"))) {
							System.out.println("found Post successfully!");
							((AndroidDriver) driver)
									.pressKeyCode(AndroidKeyCode.BACK);
						} else {
							if (isElementPresent(By.name("Post Not Found"))) {
								System.out.println("Post might be deleted");
								driver.findElement(By.name("OK")).click();
							} else {
								System.out
										.println("Expected to find post, but didn't");
								((AndroidDriver) driver)
										.pressKeyCode(AndroidKeyCode.BACK);

							}
						}
					} catch (NotFoundException e) {
						// TODO: handle exception
						((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
					}
					break;
				case "their profile":
				case "accepted":
					// either profile or messages
					try {
						elementTitle = driver.findElements(By
								.xpath("//android.widget.TextView"));

						if (elementTitle.get(0).getText().equals(words[0])) {
							System.out.println("found friend's profile");
						} else {
							System.out
									.println("Expected to find friend's profile, but didn't");
						}
						((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
					} catch (NotFoundException e) {
						// TODO: handle exception
						((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
					}

					break;
				case "approval":
					try {
						elementTitle = driver.findElements(By
								.xpath("//android.widget.TextView"));
						if (elementTitle.get(0).getText()
								.equals("Pending Requests")) {
							System.out.println("found Pending Requests");
						} else {
							System.out.println("didn't find Pending Requests");

						}
						((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
					} catch (NotFoundException e) {
						((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
					}
					break;
				case "reviewed":
					try {
						elementTitle = driver.findElements(By
								.xpath("//android.widget.TextView"));
						if (elementTitle.get(0).getText().equals("Reviews")) {
							System.out.println("found Reviews");
						} else {
							System.out.println("didn't find Reviews");

						}
						((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
					} catch (NotFoundException e) {
						((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
					}

					break;
				case "congratulations":
					try {
						elementTitle = driver.findElements(By
								.xpath("//android.widget.TextView"));
						if (elementTitle.get(0).getText().equals("My Profile")) {
							System.out.println("found My Profile");
						} else {
							System.out.println("didn't find My Profile");

						}
						((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
					} catch (NotFoundException e) {
						((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
					}
					break;
				default:
					break;
				}

			} catch (Exception e) {
				System.out.println("Could not find notification with search term: "
						+ searchTerm);

			}
		}

		@Test(groups = "signup", priority = 80, enabled = true)
		public void signUpValidValues() throws Exception {
			System.out.println("signUp Valid Values");

			WebElement elementJoinNow = driver.findElement(By
					.id("com.microdoers.projecttoe:id/login_join_now_textview"));
			elementJoinNow.click();
			try {
				WebElement elementnewUserEmail = (new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/join_projecttoe_email_edittext")));
				String fullUserName = newUserName + randomNum;
				elementnewUserEmail.sendKeys(fullUserName + "@gmail.com");

				WebElement elementNewUserName = driver
						.findElement(By
								.id("com.microdoers.projecttoe:id/join_projecttoe_username_edittext"));
				elementNewUserName.sendKeys(fullUserName);
				WebElement elementNewUserPass = driver
						.findElement(By
								.id("com.microdoers.projecttoe:id/join_projecttoe_password_edittext"));
				elementNewUserPass.sendKeys(newUserPass);
				WebElement elementNewUserConfirmPass = driver
						.findElement(By
								.id("com.microdoers.projecttoe:id/join_projecttoe_confirm_password_edittext"));
				elementNewUserConfirmPass.sendKeys(newUserPass);
				WebElement elementNext = (new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/action_go_next")));
				assertEquals(elementNext.getText(), next);
				elementNext.click();
			} catch (Exception e) {
				fail("Signup failed in step 1/3 issue is: " + e.getMessage());
				throw e;
			}
			try {
				WebElement elementNewUserTellurStory = (new WebDriverWait(driver,
						60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/join_projecttoe_tell_your_story_edittext")));
				elementNewUserTellurStory.sendKeys("I'm Appium!");
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				driver.findElement(
						By.id("com.microdoers.projecttoe:id/action_go_next"))
						.click();
			} catch (Exception e) {
				fail("Signup failed in step 2/3 issue is: " + e.getMessage());
				throw e;
			}
			try {
				driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
				try {
					driver.findElement(By.name("self harm")).click();
					System.out.println("self harm group clicked");
				} catch (Exception e) {
					System.out.println("Could not join self harm group");
				}

				try {
					WebElement elementSearchGroup = driver
							.findElement(By
									.id("com.microdoers.projecttoe:id/join_projecttoe_search_group_edittext"));
					elementSearchGroup.sendKeys("happy lif");
					driver.findElement(By.name("happy life")).click();
					System.out.println("happy life group clicked");
					driver.findElement(By.name(done)).click();
				} catch (Exception e) {
					System.out.println("Could not join happy life group");
				}
				WebElement elementCancel = (new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/dim_newsfeed_view_cancel")));
				elementCancel.click();
			} catch (Exception e) {
				fail("Signup failed in step 3/3 issue is: " + e.getMessage());
				throw e;
			}

		}

		@Test(groups = "myProfile", priority = 69, enabled = true)
		public void EditProfile() throws Exception {
			// replace here to make test fail
			System.out.println("Edit Profile");
			try {
				// click on Profile
				WebElement elementProfileIcon = driver.findElement(By
						.id("com.microdoers.projecttoe:id/action_profile"));
				elementProfileIcon.click();
				(new WebDriverWait(driver, 60)).until(ExpectedConditions
						.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/admin_badge")));
				// click on settings
				driver.scrollTo("Settings").click();

				// click on Edit
				WebElement elementEdit = (new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/action_settings")));
				elementEdit.click();

				// edit first name
				WebElement elementFirstName = driver
						.findElement(By
								.id("com.microdoers.projecttoe:id/your_edit_first_name_label"));
				elementFirstName.click();
				elementFirstName.clear();
				elementFirstName.sendKeys("Appium");

				// edit last name
				WebElement elementLasttName = driver
						.findElement(By
								.id("com.microdoers.projecttoe:id/your_edit_last_name_label"));
				elementLasttName.click();
				elementLasttName.clear();
				elementLasttName.sendKeys("AutomatedTest");

				// edit city
				WebElement elementCity = driver.findElement(By
						.id("com.microdoers.projecttoe:id/your_edit_city_label"));
				elementCity.click();
				elementCity.clear();
				elementCity.sendKeys("Mansoura");

				// edit state
				List<WebElement> elementDropList = driver
						.findElements(By
								.xpath("//android.widget.TextView[@resource-id='android:id/text1']"));
				elementDropList.get(0).click();
				driver.findElement(By.name("Not Applicable")).click();

				// edit Country
				elementDropList.get(1).click();
				driver.findElement(By.name("Egypt")).click();

				// edit phone number
				WebElement elementPhoneNo = driver.findElement(By
						.id("com.microdoers.projecttoe:id/your_edit_phone_label"));
				elementPhoneNo.click();
				elementPhoneNo.clear();
				elementPhoneNo.sendKeys("01003928740");

				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);

				driver.findElement(By.name("SAVE")).click();
				System.out.println("edit profile successfully");

			} catch (NoSuchElementException e) {
				if (isElementPresent(By.name("OK"))) {
					driver.findElement(By.name("OK"));
				}
				System.out.println("something wrong happened, edit profile failed");
			}
		}

		
		@Test(groups = "main", priority = 70, enabled = true)
		public void logout() throws Exception {
			System.out.println("logOut");

			WebElement elementLogout = driver.findElement(By
					.id("com.microdoers.projecttoe:id/logout_button"));
			elementLogout.click();

			WebElement elementLogoutMsg = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("android:id/message")));

			// check to see if it shows msg in logout dialog
			assertEquals(elementLogoutMsg.getText(), logOutMsg);
			WebElement elementLogoutYes = driver.findElement(By
					.id("android:id/button1"));
			elementLogoutYes.click();
			// check to see if the app is back to login activity
			assertEquals(
					driver.findElement(By.id("login_submit_button")).getText(),
					"Submit");
			System.out.println("Logged out successfully!");

		}

		private void swipeLeft() {
			Dimension size = driver.manage().window().getSize();
			int startx = (int) (size.width * 0.8);
			int endx = (int) (size.width * 0.20);
			int starty = size.height / 2;
			driver.swipe(startx, starty, endx, starty, 1000);
		}

		protected boolean isElementPresent(By by) {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			try {
				driver.findElement(by);
				return true;
			} catch (NoSuchElementException e) {
				return false;
			} finally {
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
		}



}
