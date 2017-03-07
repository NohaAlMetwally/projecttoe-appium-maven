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
	private AppiumDriver<WebElement> driver;
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
	private String greetingsAppium = "Greetings from Appium!";
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
		capabilities
				.setCapability(
						"app",
						"http://127.0.0.1:8080/job/ProjectToeAndroid/lastSuccessfulBuild/artifact/com.microdoers.projecttoe/build/outputs/apk/com.microdoers.projecttoe-versionDevelopment-debug.apk");

		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "5.1");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
				"Nexus_7_API_22_2");
		capabilities.setCapability("androidInstallTimeout", 2000000);
		driver = new AndroidDriver<WebElement>(new URL(
				"http://127.0.0.1:4726/wd/hub"), capabilities);

		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		Thread.sleep(10000 * 2);
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
		try {
			System.out.println("************ signIn ************");
			WebElement element = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("tutorial_signin_button")));
			try {
				// swipeThroughFrames();
				element.click();
			} catch (NoSuchElementException e) {
				element.click();
			}
		} catch (NotFoundException e) {
			System.out.println("coudn't find sign in button");
		}
		System.out.println("************ signIn Done ************");
	}

	public void swipeThroughFrames() {
		if (isElementPresent(By.name(projectToeTutPage))) {
			System.out.println("find element by name " + projectToeTutPage);
			driver.findElement(By.name(projectToeTutPage)).click();
			swipeLeft();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

			if (isElementPresent(By.name(createProfileTutPage))) {
				driver.findElement(By.name(createProfileTutPage)).click();
				swipeLeft();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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
		System.out.println("************ logIn ************");
		try {
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
		} catch (Exception e) {
			System.out.println("Couldn't login, something went wrong");
		}
		System.out.println("************ logIn Done ************");
	}

	@Test(groups = "loginIssues", priority = 2, enabled = true)
	public void forgotPasswordInValidEmail() throws MalformedURLException {
		System.out
				.println("************ forgot Password InValid Email ************");
		try {
			WebElement elementTvForgetPassword = driver.findElement(By
					.id("login_forgot_password_textview"));
			elementTvForgetPassword.click();

			WebElement elementEtEmail = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/forgotPassword_email")));

			// no such email in DB
			elementEtEmail.sendKeys("212@gmail.com");
			WebElement elementBSubmit = driver
					.findElement(By
							.id("com.microdoers.projecttoe:id/forgotPassword_submit_button"));
			elementBSubmit.click();

			// wait until reset password msg appears
			WebElement elementLoginFailedMsgTitle = (new WebDriverWait(driver,
					60)).until(ExpectedConditions.presenceOfElementLocated(By
					.id("com.microdoers.projecttoe:id/alertTitle")));

			// check for reset password title
			assertEquals(elementLoginFailedMsgTitle.getText(), resetPassFailed);
			WebElement elementOk = driver.findElement(By
					.id("android:id/button1"));
			elementOk.click();
			elementEtEmail.clear();

			System.out.println("forgot Password InValid Email Successful!");

			driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out
					.println("Couldn't do forgot Password InValid Email, something went wrong");
		}
		System.out
				.println("************ forgot Password InValid Email Done ************");
	}

	@Test(groups = "loginIssues", priority = 3, enabled = true)
	public void forgotPasswordValidEmail() throws MalformedURLException {
		System.out
				.println("************ forgot Password Valid Email ************");
		try {
			WebElement elementEtEmail = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/forgotPassword_email")));
			elementEtEmail.sendKeys("noha.elmetwally@microdoers.com");
			WebElement elementBSubmit = driver
					.findElement(By
							.id("com.microdoers.projecttoe:id/forgotPassword_submit_button"));
			elementBSubmit.click();

			// wait until reset password msg appears
			WebElement elementLoginFailedMsgTitle = (new WebDriverWait(driver,
					60)).until(ExpectedConditions.presenceOfElementLocated(By
					.id("com.microdoers.projecttoe:id/alertTitle")));

			// check for reset password title
			assertEquals(elementLoginFailedMsgTitle.getText(), resetPassSuccess);
			WebElement elementOk = driver.findElement(By
					.id("android:id/button1"));
			elementOk.click();

			System.out.println("forgot Password Valid Email Successful!");

			driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out
					.println("Couldn't do forgot Password Valid Email, something went wrong");
		}
		System.out
				.println("************ forgot Password Valid Email Done ************");
	}

	@Test(groups = "loginIssues", priority = 4, enabled = true)
	public void loginWithInvalidCredentials() throws MalformedURLException {
		// replace here to make test fail
		System.out
				.println("************ login With Invalid Credentials ************");
		try {
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
			WebElement elementLoginFailedMsgTitle = (new WebDriverWait(driver,
					60)).until(ExpectedConditions.presenceOfElementLocated(By
					.id("com.microdoers.projecttoe:id/alertTitle")));

			// check for login failed dialog title
			assertEquals(elementLoginFailedMsgTitle.getText(), loginFailedMsg);
			WebElement elementOk = driver.findElement(By
					.id("android:id/button1"));
			elementOk.click();

			elementUserName.clear();
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
			elementUserPassword.clear();

			System.out.println("login With Invalid Credentials Successfully!");

			driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out
					.println("Couldn't login With Invalid Credentials, something went wrong");
		}
		System.out
				.println("************ login With Invalid Credentials Done ************");
	}

	@Test(groups = "newsfeed", priority = 12, enabled = true)
	public void makePost() throws Exception {
		System.out.println("************ Make Post ************");
		try {
			WebElement elementAddNewPost = driver.findElement(By
					.id("fab_add_new_post"));

			elementAddNewPost.click();
			WebElement elementAddNewPostTxt = driver.findElement(By
					.id("add_new_post_txt"));

			elementAddNewPostTxt.sendKeys(postContent);
			driver.findElement(By.id("action_addNewPost")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			if (isElementPresent(By
					.id("com.microdoers.projecttoe:id/alertTitle"))) {
				WebElement elementPostFailed = driver.findElement(By
						.id("com.microdoers.projecttoe:id/alertTitle"));
				assertEquals(elementPostFailed.getText(), addPostFailed);
				System.out.println(driver.findElement(
						By.id("android:id/message")).getText());
				driver.findElement(By.id("android:id/button1")).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				((AndroidDriver<WebElement>) driver)
						.pressKeyCode(AndroidKeyCode.BACK);
				((AndroidDriver<WebElement>) driver)
						.pressKeyCode(AndroidKeyCode.BACK);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			} else {
				System.out.println("Making a new post successful");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				checkPostSuccessful();
			}
		} catch (Exception e) {
			System.out
					.println("Couldn't make a new post, something went wrong");
		}
		System.out.println("************ Make Post Done ************");
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
		System.out.println("************ hug Post ************");
		try {
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
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			int currentHugsNum = Integer.parseInt(elementHugsNum.getText());
			// System.out.println("prev" + prevHugsNum);
			// System.out.println("current" + currentHugsNum);
			if (prevHugsNum < currentHugsNum) {
				// System.out.println("Hug Post Successfully!");
			} else if (prevHugsNum > currentHugsNum) {
				// System.out.println("Unhug Post Successfully!");
			} else {
				// System.out.println("Hug Post Failed!");
			}
		} catch (Exception e) {
			System.out.println("Couldn't Hug Post, something went wrong");
		}
		System.out.println("************ hug Post Done ************");
	}

	@Test(groups = "newsfeed", priority = 14, enabled = true)
	public void commentPost() throws Exception {
		// replace here to make test fail
		System.out.println("************ Comment on Post ************");

		try {
			WebElement elementComment = driver.findElement(By
					.id("com.microdoers.projecttoe:id/comments_num_layout"));
			elementComment.click();
			WebElement elementCommentsNum = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/post_details_commentsNum")));
			int prevCommentsNum = Integer
					.parseInt(elementCommentsNum.getText());
			WebElement elementWriteComment = driver.findElement(By
					.id("com.microdoers.projecttoe:id/add_comment_txt"));
			elementWriteComment.sendKeys(greetingsAppium);
			driver.findElement(
					By.id("com.microdoers.projecttoe:id/add_comment_btn"))
					.click();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			int currentCommentsNum = Integer.parseInt(elementCommentsNum
					.getText());

			if (prevCommentsNum < currentCommentsNum) {
				System.out.println("comment On Post Successfully!");
			} else {
				System.out.println("comment On Post Failed!");
			}
			((AndroidDriver<WebElement>) driver)
					.pressKeyCode(AndroidKeyCode.BACK);
		} catch (Exception e) {
			System.out
					.println("Couldn't comment On post, something went wrong");
		}
		System.out.println("************ Comment on Post Done ************");
	}

	@Test(groups = "groupsTab", priority = 20, enabled = false)
	public void upgradeUserToPremium() throws Exception {
		// replace here to make test fail
		try {
			System.out
					.println("************ Upgrade User To Premium ************");
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
				((AndroidDriver<WebElement>) driver)
						.pressKeyCode(AndroidKeyCode.BACK);
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
						((AndroidDriver<WebElement>) driver)
								.pressKeyCode(AndroidKeyCode.BACK);
						(new WebDriverWait(driver, 60))
								.until(ExpectedConditions.presenceOfElementLocated(By
										.id("com.microdoers.projecttoe:id/subscription_month_btn")));
						((AndroidDriver<WebElement>) driver)
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
						((AndroidDriver<WebElement>) driver)
								.pressKeyCode(AndroidKeyCode.BACK);
						System.out
								.println("subscribe To Premium Monthely succeed");
						System.out.println("else");

					}
				} catch (Exception e) {
					((AndroidDriver<WebElement>) driver)
							.pressKeyCode(AndroidKeyCode.BACK);
					System.out.println("Couldn't find Become a Premium Member");
					System.out.println("Upgrade User To Premium Failed");
				}
			}
		} catch (NotFoundException e) {
			System.out.println("Upgrade User To Premium");
		}
		System.out
				.println("************ Upgrade User To Premium Done ************");
	}

	@Test(groups = "groupsTab", priority = 21, enabled = true)
	public void loadGroups() throws Exception {
		// replace here to make test fail
		System.out.println("************ load Groups ************");
		try {
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
			System.out.println("load Groups successfully!");
		} catch (Exception e) {
			System.out.println("Couldn't load Groups, something went wrong");
		}
		System.out.println("************ load Groups Done ************");
	}

	@Test(groups = "groupsTab", priority = 22, enabled = false)
	public void searchGroup() throws Exception {
		// replace here to make test fail
		System.out.println("************ search group ************");
		try {
			WebElement elementJoinSuppGroup = driver.findElement(By
					.id("com.microdoers.projecttoe:id/join_support_group_btn"));
			elementJoinSuppGroup.click();
			WebElement elementTypeYourSearch = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/autoCompleteSearch")));
			elementTypeYourSearch.sendKeys("Self Confidence");
			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/network_search_name")));
			List<WebElement> elementTabs = driver
					.findElements(By
							.xpath("//android.widget.TextView[@resource-id='com.microdoers.projecttoe:id/network_search_name']"));
			elementTabs.get(0).click();
			if (isElementPresent(By
					.id("com.microdoers.projecttoe:id/autoCompleteSearch"))) {
				if (isElementPresent(By
						.id("com.microdoers.projecttoe:id/network_search_name"))) {
					List<WebElement> elementGroupsResult = driver
							.findElements(By
									.xpath("//android.widget.TextView[@resource-id='com.microdoers.projecttoe:id/network_search_name']"));
					elementGroupsResult.get(0).click();
				}
			}
			System.out.println("Self Confidence found!");
			// go back to search activity
			(new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/group_name")));
			((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/autoCompleteSearch")));
			// go back to groups tab
			((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);

			System.out.println("search group success!");
		} catch (NotFoundException e) {
			System.out.println("search group failed, something went wrong");
		}
		System.out.println("************ search group Done ************");
	}

	@Test(groups = "groupsTab", priority = 23, enabled = true)
	public void addGroup() throws Exception {
		// replace here to make test fail
		System.out.println("************ add group ************");
		try {
			WebElement elementStartGroup = driver.findElement(By
					.id("com.microdoers.projecttoe:id/start_group_bttn"));
			elementStartGroup.click();
			if (isElementPresent(By
					.id("com.microdoers.projecttoe:id/start_group_bttn"))) {
				elementStartGroup.click();
			}
			// group name edit text
			WebElement elementGroupName = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/group_name")));
			dateNow = new SimpleDateFormat("dd-MM-YY-hh:mm").format(new Date());
			groupName = groupName + dateNow;
			elementGroupName.sendKeys(groupName);
			groupName = groupName.replace(' ', '_');
			// ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
			// group description edit text
			WebElement elementGroupDesc = driver.findElement(By
					.id("com.microdoers.projecttoe:id/group_description"));
			elementGroupDesc.sendKeys(groupDesc);
			// ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
			// group keywords edit text
			WebElement elementGroupKeyWords = driver.findElement(By
					.id("com.microdoers.projecttoe:id/group_keywords"));
			elementGroupKeyWords.sendKeys(groupKeyword);
			// ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
			// click add button
			try {
				// It's not always visible on the screen so we scroll to it
				// first
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
				// driver.manage().timeouts().implicitlyWait(60,
				// TimeUnit.SECONDS);
			} catch (NotFoundException e) {
				System.out.println("Could not click Add");
			}
			WebElement ElemetDialogTitle = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/alertTitle")));
			// check if creating group failed and dialog shows up
			if (ElemetDialogTitle.getText().equals("Create group Failed")) {
				driver.findElement(By.id("android:id/button1")).click();
				((AndroidDriver<WebElement>) driver)
						.pressKeyCode(AndroidKeyCode.BACK);
				// change in new apk
			} else if (ElemetDialogTitle.getText().equals(
					"Ready to add \"Helpers?\"")) {
				newGroupAdded = true;
				driver.findElement(By.id("android:id/button2")).click();
				// boost dialog
				WebElement ElemetBoostDialogTitle = (new WebDriverWait(driver,
						60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/alertTitle")));
				if (ElemetBoostDialogTitle.getText().equals("Boost")) {
					driver.findElement(By.id("android:id/button2")).click();
					WebElement elementCancelButton = (new WebDriverWait(driver,
							60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/pre_group_view_cancel")));
					elementCancelButton.click();
					System.out.println("add group successfully!");
				}
			} else {
				fail("Did not add group successfully");

			}
		} catch (NotFoundException e) {
			System.out.println("didn't add group, something went wrong");
		}
		System.out.println("************ add group Done ************");
	}

	@Test(groups = "groupsTab", priority = 24, enabled = true)
	public void ubgradeGroupToPremium() throws Exception {
		// replace here to make test fail
		System.out
				.println("************ Ubgrade Group To Premium ************");
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
		System.out
				.println("************ Ubgrade Group To Premium Done ************");
	}

	@Test(groups = "groupsTab", priority = 25, enabled = true)
	public void makeGroupHelper() throws Exception {
		// replace here to make test fail
		if (upgradeGroupToPremium) {
			System.out.println("************ Make Group Helper ************");
			// check for reviews
			try {
				// wait until group's name shows up
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/group_name")));
				// open menu
				driver.findElement(
						By.id("com.microdoers.projecttoe:id/action_edit_profile"))
						.click();
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/admin_options_button")))
						.click();
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/makeNetworkHelperMode")))
						.click();
				((AndroidDriver<WebElement>) driver)
						.pressKeyCode(AndroidKeyCode.BACK);
				System.out.println("Make Group Helper-Mode Successfully!");
			} catch (Exception e) {
				System.out.println("Couldn't Make Group in Helper-Mode");
				System.out.println("Make Group Helper Failed");
			}
		}
		System.out.println("************ Make Group Helper Done ************");
	}

	@Test(groups = "groupsTab", priority = 26, enabled = true)
	public void checkGroupDetails() throws Exception {
		// replace here to make test fail
		System.out.println("************ check group details ************");
		if (newGroupAdded) {
			// check for reviews
			try {
				if (isElementPresent(By
						.id("com.microdoers.projecttoe:id/number_of_reviews_txt"))) {
					WebElement elementReviews = (new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/number_of_reviews_txt")));
					int reviewsNum = Integer
							.parseInt(elementReviews.getText().substring(1,
									elementReviews.getText().length() - 1));
					if (reviewsNum > 0) {
						System.out.println("#" + groupName + " has "
								+ reviewsNum + " reviews");
					}
				} else {
					if (isElementPresent(By
							.id("com.microdoers.projecttoe:id/write_review_button")))
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
				System.out
						.println("Couldn't find lock icon, something went wrong");
			}
			if (upgradeGroupToPremium) {
				// check for helper icone, this group is helper try
				try {
					// Maybe It's not visible on the screen so we scroll to it
					// first
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
					System.out.println("group members count is "
							+ groupMembersNum);
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
		} else {
			System.out
					.println("Couldn't check details, no new group has been added");
		}
		// ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
		System.out.println("Group Details Successful!");
		System.out
				.println("************ check group details Done ************");
	}

	// make write review after create group
	@Test(groups = "groupsTab", priority = 27, enabled = true)
	public void canWriteReview() throws Exception {
		System.out.println("************ can Write Review ************");
		// check to see if we just added a group
		if (newGroupAdded) {
			try {
				driver.scrollTo("Write Review");

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
		System.out.println("************ can Write Review Done ************");
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
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		// check to see if failed alert appears
		if (isElementPresent(By.id("com.microdoers.projecttoe:id/alertTitle"))) {
			WebElement elementErrorMsg = driver.findElement(By
					.id("android:id/message"));
			System.out.println(elementErrorMsg.getText());
			driver.findElement(By.id("android:id/button1")).click();
			// get rid of keyboard
			// ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
			/*
			 * if (isElementPresent(By.name("Great!! Appium"))) {
			 * ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK); }
			 */
			// back to group wall
			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/action_edit_profile")));
			// back to group tab
			((AndroidDriver<WebElement>) driver)
					.pressKeyCode(AndroidKeyCode.BACK);
		} else {
			// if no alert appears then review has been written
			// successfully

			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/action_edit_profile")));
			((AndroidDriver<WebElement>) driver)
					.pressKeyCode(AndroidKeyCode.BACK);
			System.out.println("Review has been written successfully!");
		}

	}

	@Test(groups = "groupsTabNonadmin", priority = 28, enabled = false)
	public void canBoostGroup() throws Exception {
		// replace here to make test fail
		System.out.println("************ Can Boost Group ************");
		if (newGroupAdded) {
			try {
				// wait until group's name shows up
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/group_name")));
				// open menu
				driver.findElement(
						By.id("com.microdoers.projecttoe:id/action_edit_profile"))
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
				driver.findElement(
						By.id("com.android.vending:id/continue_button"))
						.click();
				if (!isElementPresent(By.name("Want Members Faster?"))) {
					System.out.println("can boost group succeed");
				} else {
					((AndroidDriver<WebElement>) driver)
							.pressKeyCode(AndroidKeyCode.BACK);
					System.out.println("couldn't boost error occurred");
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
		System.out.println("************ Can Boost Group Done ************");
	}

	@Test(groups = "groupsTabNonadmin", priority = 29, enabled = false)
	public void canDowngradeGroup() throws Exception {
		// replace here to make test fail
		System.out.println("************ Can Downgrade Group ************");
		if (newGroupAdded && upgradeGroupToPremium) {
			try {
				// wait until group's name shows up
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/group_name")));
				// open menu
				driver.findElement(
						By.id("com.microdoers.projecttoe:id/action_edit_profile"))
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
				((AndroidDriver<WebElement>) driver)
						.pressKeyCode(AndroidKeyCode.BACK);
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
				((AndroidDriver<WebElement>) driver)
						.pressKeyCode(AndroidKeyCode.BACK);
			} catch (Exception e) {
				System.out.println("Couldn't find menu");
			}
		} else {
			System.out.println("Group is not even premium");
			((AndroidDriver<WebElement>) driver)
					.pressKeyCode(AndroidKeyCode.BACK);
		}
		System.out
				.println("************ Can Downgrade Group Done ************");
	}

	@Test(groups = "groupsTabNonadmin", priority = 30, enabled = true)
	public void canLeaveJoinPublicGroup() throws Exception {
		// replace here to make test fail
		System.out
				.println("************ Can Leave Join Public Group ************");
		try {
			// search for #My_School and open group wall
			searchSpecificGroup("My_School");
		} catch (NotFoundException e) {
			System.out.println("Could not click #My_School");
		}
		try {
			// open menu
			(new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/group_name")));
			driver.findElement(
					By.id("com.microdoers.projecttoe:id/action_edit_profile"))
					.click();
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
			driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
			if (isElementPresent(By
					.id("com.microdoers.projecttoe:id/alertTitle"))) {
				WebElement elementLeaveGroupFailedAlert = (new WebDriverWait(
						driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/alertTitle")));
				assertEquals(elementLeaveGroupFailedAlert.getText(),
						"Leaving Group Failed");

				System.out.println("leaving group faild, something went wrong");
			} else {
				// if it's not visible so everything went good
				System.out.println("leaving group succeed");
				// back to groups tab
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/autoCompleteSearch")));
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				try {
					// go to "#My_School" group
					searchSpecificGroup("My_School");
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
					// go to search
					(new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/group_name")));
					((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
					WebElement elementTypeYourSearch = (new WebDriverWait(
							driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/autoCompleteSearch")));
					// go to group tab
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
		System.out
				.println("************ Can Leave Join Public Group Done ************");
	}

	@Test(groups = "groupsTabNonadmin", priority = 31, enabled = true)
	public void canLeaveJoinPrivateGroup() throws Exception {
		// replace here to make test fail
		System.out
				.println("************ can Leave Join Private Group ************");
		try {
			searchSpecificGroup("AppiumPrivateAndroid");
		} catch (Exception e) {
			System.out.println("Could not click " + privateGroupName);
		}
		try {
			// wait until group's name shows up
			(new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/group_name")));
			// open menu
			driver.findElement(
					By.id("com.microdoers.projecttoe:id/action_edit_profile"))
					.click();
			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/cancel_group_button")));
			if (isElementPresent(By
					.id("com.microdoers.projecttoe:id/leave_group_button"))) {
				// click on leave group
				WebElement elementLeaveGroup = (new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/leave_group_button")));
				elementLeaveGroup.click();
				// wait for alert "Leave Group"
				WebElement elementLeaveGroupAlert = (new WebDriverWait(driver,
						60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/alertTitle")));
				assertEquals(elementLeaveGroupAlert.getText(), "Leave Group!");
				// choose yes
				WebElement elementYes = driver.findElement(By
						.id("android:id/button1"));
				elementYes.click();
				// check if "Failed" alert appears
				driver.manage().timeouts()
						.implicitlyWait(600, TimeUnit.SECONDS);
				if (isElementPresent(By
						.id("com.microdoers.projecttoe:id/alertTitle"))) {
					WebElement elementLeaveGroupFailedAlert = (new WebDriverWait(
							driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/alertTitle")));
					assertEquals(elementLeaveGroupFailedAlert.getText(),
							"Leaving Group Failed");

					System.out
							.println("leaving group faild, something went wrong");
				} else {
					// if it's not visible so everything went good
					System.out.println("leaving group succeed");
					// go to groups tab
					(new WebDriverWait(driver, 60))
							.until(ExpectedConditions.presenceOfElementLocated(By
									.id("com.microdoers.projecttoe:id/autoCompleteSearch")));
					((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
					try {
						// go to privateGroupName group
						searchSpecificGroup("AppiumPrivateAndroid");
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
						System.out
								.println("Can Leave Join private Group succeed");
						// go to search
						(new WebDriverWait(driver, 60))
								.until(ExpectedConditions.presenceOfElementLocated(By
										.id("com.microdoers.projecttoe:id/group_name")));
						((AndroidDriver<WebElement>) driver)
								.pressKeyCode(AndroidKeyCode.BACK);
						WebElement elementTypeYourSearch = (new WebDriverWait(
								driver, 60))
								.until(ExpectedConditions.presenceOfElementLocated(By
										.id("com.microdoers.projecttoe:id/autoCompleteSearch")));
						// go to group tab
						((AndroidDriver) driver)
								.pressKeyCode(AndroidKeyCode.BACK);

					} catch (NotFoundException e) {
						System.out
								.println("something went wrong couldn't join group");
						// go to search
						((AndroidDriver) driver)
								.pressKeyCode(AndroidKeyCode.BACK);
						WebElement elementTypeYourSearch = (new WebDriverWait(
								driver, 60))
								.until(ExpectedConditions.presenceOfElementLocated(By
										.id("com.microdoers.projecttoe:id/autoCompleteSearch")));
						// go to group tab
						((AndroidDriver) driver)
								.pressKeyCode(AndroidKeyCode.BACK);
					}
				}
				// if user is didn't join group
			} else {
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				(new WebDriverWait(driver, 60)).until(ExpectedConditions
						.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/group_name")));
				if (isElementPresent(By
						.id("com.microdoers.projecttoe:id/join_group_button"))) {
					driver.findElement(
							By.id("com.microdoers.projecttoe:id/join_group_button"))
							.click();
					// user sent request to this group
				} else {
					System.out
							.println("request hase been sent and waiting to be accepted");
				}
				// go to search
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
				WebElement elementTypeYourSearch = (new WebDriverWait(driver,
						60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/autoCompleteSearch")));
				// go to group tab
				((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
			}
		} catch (Exception e) {
			System.out.println("didn't find leave group");

		}
		System.out
				.println("************ can Leave Join Private Group Done ************");
	}

	public void searchSpecificGroup(String groupName) throws Exception {
		System.out.println("**** search specific group ****");
		try {
			WebElement elementJoinSuppGroup = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/join_support_group_btn")));
			elementJoinSuppGroup.click();
			/*if (isElementPresent(By
					.id("com.microdoers.projecttoe:id/join_support_group_btn"))) {
				elementJoinSuppGroup.click();
			}*/
			WebElement elementTypeYourSearch = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/autoCompleteSearch")));
			elementTypeYourSearch.sendKeys(groupName);
			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/network_search_name")));
			List<WebElement> elementGroups = driver
					.findElements(By
							.xpath("//android.widget.TextView[@resource-id='com.microdoers.projecttoe:id/network_search_name']"));
			elementGroups.get(0).click();
			if (isElementPresent(By
					.id("com.microdoers.projecttoe:id/autoCompleteSearch"))) {
				if (isElementPresent(By
						.id("com.microdoers.projecttoe:id/network_search_name"))) {
					List<WebElement> elementGroupsResult = driver
							.findElements(By
									.xpath("//android.widget.TextView[@resource-id='com.microdoers.projecttoe:id/network_search_name']"));
					elementGroupsResult.get(0).click();
				}
			}
			System.out.println(groupName + " found!");
		} catch (NotFoundException e) {
			System.out
					.println("search specific group failed, something went wrong");
		}
		System.out.println("**** search specific group Done ****");
	}

	@Test(groups = "adminPanel", priority = 32, enabled = true)
	public void canBlockUnblockUsers() throws Exception {
		// replace here to make test fail
		System.out.println("************ Can Block Users ************");
		try { // go to "#AppiumTestAndroid" group
			searchSpecificGroup("AppiumTestAndroid2");
			// wait until group's name shows up
			(new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/group_name")));
			// open menu
			driver.findElement(
					By.id("com.microdoers.projecttoe:id/action_edit_profile"))
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
			((AndroidDriver<WebElement>) driver)
					.pressKeyCode(AndroidKeyCode.BACK);
			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/blocked_users_lable")));

			System.out.println("Can Block Users successful!");
		} catch (NotFoundException e) {
			System.out.println("couldn't block user, Something went wrong!");
		}
		System.out.println("************ Can Block Users Done ************");
	}

	@Test(groups = "adminPanel", priority = 33, enabled = true)
	public void canBroadcastMessage() throws Exception {
		// replace here to make test fail
		System.out.println("************ can Broadcast Message ************");
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
			((AndroidDriver<WebElement>) driver)
					.pressKeyCode(AndroidKeyCode.BACK);
			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/message_all_users_lable")));

			System.out.println("can Broadcast Message successfully!");

		} catch (Exception e) {
			fail("Did not broadcast successfully: " + e.getLocalizedMessage());
			throw e;
		}
		System.out
				.println("************ can Broadcast Message Done ************");
	}

	@Test(groups = "adminPanel", priority = 34, enabled = true)
	public void canAddRemoveHelper() throws Exception {
		// replace here to make test fail
		System.out.println("************ Can add remove helper ************");
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
			((AndroidDriver<WebElement>) driver)
					.pressKeyCode(AndroidKeyCode.BACK);
			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/message_all_users_lable")));
			// go to group profile
			((AndroidDriver<WebElement>) driver)
					.pressKeyCode(AndroidKeyCode.BACK);
			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/action_edit_profile")));
			System.out.println("can add remove helper successfully!");

		} catch (Exception e) {
			fail("Did not add/remove successfully: " + e.getLocalizedMessage());
			throw e;
		}
		System.out
				.println("************ Can add remove helper Done ************");
	}

	@Test(groups = "adminPanel", priority = 35, enabled = true)
	public void canTogglePrivateSwitch() throws Exception {
		// replace here to make test fail
		System.out
				.println("************ can Toggle Private Switch ************");
		try {
			// wait until group's name shows up
			(new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/group_name")));
			// open menu
			driver.findElement(
					By.id("com.microdoers.projecttoe:id/action_edit_profile"))
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
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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
			((AndroidDriver<WebElement>) driver)
					.pressKeyCode(AndroidKeyCode.BACK);
			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/action_edit_profile")));
			System.out.println("Can Toggle Private successfully!");
		} catch (Exception e) {
			System.out.println("didn't find admin panel, something went wrong");
		}
		System.out
				.println("************ can Toggle Private Switch Done ************");
	}

	@Test(groups = "adminPanel", priority = 36, enabled = false)
	public void canToggleHelperSwitch() throws Exception {
		// replace here to make test fail
		System.out
				.println("************ Can Toggle Helper Switch ************");
		try {
			// wait until group's name shows up
			(new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/group_name")));
			// open menu
			driver.findElement(
					By.id("com.microdoers.projecttoe:id/action_edit_profile"))
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
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.findElement(
						By.id("com.microdoers.projecttoe:id/makeNetworkHelperMode"))
						.click();
				System.out.println("switched helper mode off");

			} catch (Exception e) {
				System.out.println("didn't find helper mode" + e.getMessage());
			}
			// go to group profile
			((AndroidDriver<WebElement>) driver)
					.pressKeyCode(AndroidKeyCode.BACK);
			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/action_edit_profile")));
			/*
			 * // go to group tab ((AndroidDriver)
			 * driver).pressKeyCode(AndroidKeyCode.BACK);
			 */
			System.out.println("Can Toggle Helper Switch successfully!");
		} catch (Exception e) {
			System.out.println("didn't find admin panel, something went wrong");
		}
		System.out
				.println("************ Can Toggle Helper Switch Done ************");
	}

	@Test(groups = "adminPanel", priority = 37, enabled = true)
	public void canAddPending() throws Exception {
		// replace here to make test fail
		System.out.println("************ Can Add Pending ************");
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
			// wait until group's name shows up
			(new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/group_name")));
			// open menu
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
					/*
					 * (new WebDriverWait(driver, 60)).until(ExpectedConditions
					 * .presenceOfElementLocated(By .name("Pending Requests")));
					 */
				} else {
					System.out.println("there're no pending requests");
				}
				((AndroidDriver<WebElement>) driver)
						.pressKeyCode(AndroidKeyCode.BACK);

				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/message_all_users_lable")));
				// go to group profile
				((AndroidDriver<WebElement>) driver)
						.pressKeyCode(AndroidKeyCode.BACK);
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
		System.out.println("************ Can Add Pending Done ************");
	}

	@Test(groups = "adminPanel", priority = 38, enabled = true)
	public void canChangeGroupKeywords() throws Exception {
		// replace here to make test fail
		System.out
				.println("************ Can Change Group Keywords ************");
		try {
			// wait until group's name shows up
			(new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/group_name")));
			// open menu
			driver.findElement(
					By.id("com.microdoers.projecttoe:id/action_edit_profile"))
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
			((AndroidDriver<WebElement>) driver)
					.pressKeyCode(AndroidKeyCode.BACK);
			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/action_edit_profile")));
			// go to search
			((AndroidDriver<WebElement>) driver)
					.pressKeyCode(AndroidKeyCode.BACK);
			WebElement elementTypeYourSearch = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/autoCompleteSearch")));
			// go to group tab
			((AndroidDriver<WebElement>) driver)
					.pressKeyCode(AndroidKeyCode.BACK);
			System.out.println("Can Change Group Keywords successfully!");
		} catch (Exception e) {
			System.out.println("didn't find admin panel, something went wrong");
		}
		System.out
				.println("************ Can Change Group Keywords Done ************");
	}

	@Test(groups = "requestsTab", priority = 39, enabled = true)
	public void goToRequestsTab() throws Exception {
		// replace here to make test fail
		System.out.println("************ Go To Requests Tab ************");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		try {
			List<WebElement> elementTabs = driver
					.findElements(By
							.xpath("//android.widget.ImageView[@resource-id='com.microdoers.projecttoe:id/tabIcon']"));
			elementTabs.get(2).click();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			List<WebElement> elementFriendRequests = driver
					.findElements(By
							.xpath("//android.widget.TextView[@com.microdoers.projecttoe:id/group_header_txt']"));

			if (elementTabs.get(0).getText().equals("FRIEND REQUESTS")) {
				System.out.println("Go To Requests Tab succeed!");
			}
		} catch (NoSuchElementException e) {
			System.out
					.println("didn't find requests tab, something went wrong");
		}
		System.out.println("************ Go To Requests Tab Done ************");
	}

	@Test(groups = "requestsTab", priority = 40, enabled = true)
	public void loadRequests() throws Exception {
		// replace here to make test fail
		System.out.println("************ load Requests ************");
		if (isElementPresent(By
				.id("com.microdoers.projecttoe:id/welcome_requests_msg"))) {
			System.out.println("there're no fiend requests");
		} else {
			if (isElementPresent(By
					.id("com.microdoers.projecttoe:id/add_friend_bttn"))) {
				System.out.println("there're some friend requests");
			} else {
				System.out.println("No friend Requests title!");
			}
		}
		if (isElementPresent(By
				.id("com.microdoers.projecttoe:id/add_friend_bttn"))) {
			System.out.println("there're some recommended users");
		} else {
			System.out.println("there're no recommended users");
		}
		System.out.println("load Requests successfully");
		System.out.println("************ load Requests Done ************");
	}

	@Test(groups = "chatTab", priority = 41, enabled = true)
	public void goToChatTab() throws Exception {
		// replace here to make test fail
		System.out.println("************ Open contacts tab ************");
		try {

			(new WebDriverWait(driver, 60)).until(ExpectedConditions
					.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/tabIcon")));
			driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
			List<WebElement> elementTabs = driver
					.findElements(By
							.xpath("//android.widget.ImageView[@resource-id='com.microdoers.projecttoe:id/tabIcon']"));
			elementTabs.get(3).click();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			if (isElementPresent(By.name("Chats"))) {
				System.out.println("Open chat tab succeed!");
			}
		} catch (NoSuchElementException e) {
			System.out.println("didn't find chat tab");
		}
		System.out.println("Open contacts tab successfully!");
		System.out.println("************ Open contacts tab Done ************");
	}

	@Test(groups = "chatTab", priority = 42, enabled = true)
	public void loadMessages() throws Exception {
		// replace here to make test fail
		System.out.println("************ Load messages ************");
		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		try {
			(new WebDriverWait(driver, 120)).until(ExpectedConditions
					.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/last_message")));

			// fonud conversations
			System.out.println("Load messages succeed");
		} catch (Exception e) {
			System.out.println("No messages found!");

		}
		System.out.println("Load messages successful");
		System.out.println("************ Load messages Done ************");
	}

	@Test(groups = "chatTab", priority = 43, enabled = true)
	public void startPrivateChat() throws Exception {
		// replace here to make test fail
		System.out.println("************ Start Private Chat ************");
		// click on new chat
		driver.findElement(By.className("android.widget.ImageButton")).click();
		// click on new conversation
		(new WebDriverWait(driver, 60)).until(
				ExpectedConditions.presenceOfElementLocated(By
						.id("com.microdoers.projecttoe:id/menu_item_newConv")))
				.click();
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.presenceOfElementLocated(By
						.id("com.microdoers.projecttoe:id/participant_list")));
		List<WebElement> contactsList = driver
				.findElements(By
						.xpath("//android.widget.TextView[@resource-id='com.microdoers.projecttoe:id/title']"));
		WebElement contactTest570 = contactsList.get(2);
		// assertEquals("test570", contactTest570.getText());
		try {
			// click on user test570
			contactTest570.click();
		} catch (NoSuchElementException e) {
			// if start new conversation activity opened so soone, friends list
			// may not be show up
			((AndroidDriver<WebElement>) driver)
					.pressKeyCode(AndroidKeyCode.BACK);
			// click on new conversation
			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/menu_item_newConv")))
					.click();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			// click on user test570
			contactTest570.click();
		}
		try {
			WebElement elementWriteMsg = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/message_edit_text")));
			dateNow = new SimpleDateFormat("dd-MM-YY-hh:mm").format(new Date());
			appiumChatMsg = postContent + " " + dateNow;
			elementWriteMsg.sendKeys(appiumChatMsg);
			driver.findElement(
					By.id("com.microdoers.projecttoe:id/send_button")).click();
			System.out.println("message has been sent successfully");
			WebElement elementSent = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/receipt")));
		} catch (NoSuchElementException e) {
			System.out.println("message hasn't been posted successfully"
					+ e.getMessage());
		}
		((AndroidDriver<WebElement>) driver).pressKeyCode(AndroidKeyCode.BACK);
		// ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
		System.out.println("Start Private Chat Succeed!");
		System.out.println("************ Start Private Chat Done ************");
	}

	@Test(groups = "chatTab", priority = 44, enabled = true)
	public void startGroupChat() throws Exception {
		// replace here to make test fail
		System.out.println("************ Start Group Chat ************");
		// click on new chat
		(new WebDriverWait(driver, 60)).until(
				ExpectedConditions.presenceOfElementLocated(By
						.className("android.widget.ImageButton"))).click();
		// click on new conversation
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions.presenceOfElementLocated(By
						.id("com.microdoers.projecttoe:id/menu_item_newGroup")))
				.click();
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.presenceOfElementLocated(By
						.id("com.microdoers.projecttoe:id/participant_list")));
		List<WebElement> contactsList = driver
				.findElements(By
						.xpath("//android.widget.TextView[@resource-id='com.microdoers.projecttoe:id/title']"));
		try {
			contactsList.get(1).click();
			contactsList.get(1).click();
		} catch (NoSuchElementException e) {
			// if start new conversation activity opened so soone, friends list
			// may not be show up
			((AndroidDriver<WebElement>) driver)
					.pressKeyCode(AndroidKeyCode.BACK);
			// click on new conversation
			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/menu_item_newConv")))
					.click();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			// click on user test570
			contactsList.get(1).click();
			contactsList.get(1).click();
		}
		try {
			driver.findElement(
					By.id("com.microdoers.projecttoe:id/action_start_group_conversation"))
					.click();
			WebElement elementWriteMsg = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/message_edit_text")));
			dateNow = new SimpleDateFormat("dd-MM-YY-hh:mm").format(new Date());
			appiumChatMsg = postContent + " " + dateNow;
			elementWriteMsg.sendKeys(appiumChatMsg);
			driver.findElement(
					By.id("com.microdoers.projecttoe:id/send_button")).click();
			System.out.println("message has been sent successfully");
			WebElement elementSent = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/receipt")));
		} catch (NoSuchElementException e) {
			System.out.println("message hasn't been posted successfully"
					+ e.getMessage());
		}
		// ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
		((AndroidDriver<WebElement>) driver).pressKeyCode(AndroidKeyCode.BACK);
		System.out.println("Start Group Chat Succeed!");
		System.out.println("************ Start Group Chat Done ************");
	}

	@Test(groups = "notificationsTab", priority = 50, enabled = true)
	public void goToNotificationsTab() throws Exception {
		// replace here to make test fail
		System.out.println("************ GoTo Notifications Tab ************");
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
		System.out
				.println("************ GoTo Notifications Tab Done ************");
	}

	@Test(groups = "notificationsTab", priority = 51, enabled = true)
	public void loadNotifications() throws Exception {
		// replace here to make test fail
		System.out.println("************ load Notifications ************");
		List<WebElement> elementNotifications = driver
				.findElements(By
						.xpath("//android.widget.TextView[@resource-id='com.microdoers.projecttoe:id/notification_desc_txt']"));
		if (elementNotifications.size() > 0) {
			System.out.println("load Notifications successfully!");
		} else {
			System.out.println("Notifications didn't load successfully!");
		}
		System.out.println("************ load Notifications Done ************");
	}

	@Test(groups = "notificationsTab", priority = 52, enabled = false)
	public void browseFromNotifications() throws Exception {
		// replace here to make test fail
		System.out.println("************ browseFromNotifications ************");
		String[] searchTerms = { "hug", "commented", "their profile",
				"approval", "posted", "reviewed", "congratulations",
				"assigned", "accepted" };
		for (String searchTerm : searchTerms) {
			scrollToNotification(searchTerm);
		}
		System.out
				.println("************ Browse Notifications Done ************");
	}

	private void scrollToNotification(String searchTerm) {

		try {
			System.out.println("searching for " + searchTerm);
			AndroidElement list = (AndroidElement) driver.findElement(By
					.id("com.microdoers.projecttoe:id/notifications_list"));
			MobileElement element = list
					.findElement(MobileBy
							.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
									+ "new UiSelector().text(\""
									+ searchTerm
									+ "\"));"));
			String rowString = ((WebElement) element).getAttribute("text");
			System.out.println("rowString " + rowString);
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
						((AndroidDriver<WebElement>) driver)
								.pressKeyCode(AndroidKeyCode.BACK);
					} else {
						if (isElementPresent(By.name("Post Not Found"))) {
							System.out.println("Post might be deleted");
							driver.findElement(By.name("OK")).click();
						} else {
							System.out
									.println("Expected to find post, but didn't");
							((AndroidDriver<WebElement>) driver)
									.pressKeyCode(AndroidKeyCode.BACK);

						}
					}
				} catch (NotFoundException e) {
					// TODO: handle exception
					((AndroidDriver<WebElement>) driver)
							.pressKeyCode(AndroidKeyCode.BACK);
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
					((AndroidDriver<WebElement>) driver)
							.pressKeyCode(AndroidKeyCode.BACK);
				} catch (NotFoundException e) {
					// TODO: handle exception
					((AndroidDriver<WebElement>) driver)
							.pressKeyCode(AndroidKeyCode.BACK);
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
					((AndroidDriver<WebElement>) driver)
							.pressKeyCode(AndroidKeyCode.BACK);
				} catch (NotFoundException e) {
					((AndroidDriver<WebElement>) driver)
							.pressKeyCode(AndroidKeyCode.BACK);
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
					((AndroidDriver<WebElement>) driver)
							.pressKeyCode(AndroidKeyCode.BACK);
				} catch (NotFoundException e) {
					((AndroidDriver<WebElement>) driver)
							.pressKeyCode(AndroidKeyCode.BACK);
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
					((AndroidDriver<WebElement>) driver)
							.pressKeyCode(AndroidKeyCode.BACK);
				} catch (NotFoundException e) {
					((AndroidDriver<WebElement>) driver)
							.pressKeyCode(AndroidKeyCode.BACK);
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
		System.out.println("************ signUp Valid Values ************");
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
			// ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
			driver.findElement(
					By.id("com.microdoers.projecttoe:id/action_go_next"))
					.click();
		} catch (Exception e) {
			fail("Signup failed in step 2/3 issue is: " + e.getMessage());
			throw e;
		}
		try {
			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/network_search_name")));
			try {
				List<WebElement> elementTabs = driver
						.findElements(By
								.xpath("//android.widget.TextView[@resource-id='com.microdoers.projecttoe:id/network_search_name']"));
				elementTabs.get(0).click();
				System.out.println("group clicked");
			} catch (Exception e) {
				System.out.println("Could not join self harm group");
			}

			try {
				WebElement elementSearchGroup = driver
						.findElement(By
								.id("com.microdoers.projecttoe:id/join_projecttoe_search_group_edittext"));
				elementSearchGroup.sendKeys("happy lif");
				(new WebDriverWait(driver, 60))
						.until(ExpectedConditions.presenceOfElementLocated(By
								.id("com.microdoers.projecttoe:id/network_search_name")));
				List<WebElement> elementTabs = driver
						.findElements(By
								.xpath("//android.widget.TextView[@resource-id='com.microdoers.projecttoe:id/network_search_name']"));
				elementTabs.get(0).click();
				System.out.println("happy life group clicked");
				driver.findElement(
						By.id("com.microdoers.projecttoe:id/action_done"))
						.click();
			} catch (Exception e) {
				System.out.println("Could not join happy life group");
				driver.findElement(
						By.id("com.microdoers.projecttoe:id/action_done"))
						.click();
			}
			WebElement elementCancel = (new WebDriverWait(driver, 60))
					.until(ExpectedConditions.presenceOfElementLocated(By
							.id("com.microdoers.projecttoe:id/dim_newsfeed_view_cancel")));
			elementCancel.click();
			System.out.println("SignUp successfully");

		} catch (Exception e) {
			fail("Signup failed in step 3/3 issue is: " + e.getMessage());
			throw e;
		}
		System.out
				.println("************ signUp Valid Values Done ************");
	}

	@Test(groups = "myProfile", priority = 69, enabled = false)
	public void EditProfile() throws Exception {
		// replace here to make test fail
		System.out.println("************ Edit Profile ************");
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

			((AndroidDriver<WebElement>) driver)
					.pressKeyCode(AndroidKeyCode.BACK);

			driver.findElement(By.name("SAVE")).click();
			System.out.println("edit profile successfully");

		} catch (NoSuchElementException e) {
			if (isElementPresent(By.name("OK"))) {
				driver.findElement(By.name("OK"));
			}
			System.out.println("something wrong happened, edit profile failed");
		}
		System.out.println("************ Edit Profile Done ************");
	}

	@Test(groups = "main", priority = 70, enabled = true)
	public void logout() throws Exception {
		System.out.println("************ logOut ************");
		// click on Profile
		WebElement elementProfileIcon = driver.findElement(By
				.id("com.microdoers.projecttoe:id/action_profile"));
		elementProfileIcon.click();
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.presenceOfElementLocated(By
						.id("com.microdoers.projecttoe:id/admin_badge")));
		// click on settings
		driver.scrollTo("Settings").click();
		WebElement elementLogout = (new WebDriverWait(driver, 60))
				.until(ExpectedConditions.presenceOfElementLocated(By
						.id("com.microdoers.projecttoe:id/logout_button")));
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
		System.out.println("************ logOut Done ************");
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
