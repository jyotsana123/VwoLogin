package TheTestingAcademy.VwoLoginAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class Login {

	//Negative test case -> Invalid username or password -> it should give error
		//Positive test case -> valid username and password -> open dashboard
		
		ChromeOptions option;
		WebDriver driver;
		
		@BeforeSuite
		public void setUp()
		{
			option = new ChromeOptions();
			option.addArguments("--start-maximized");
			driver = new ChromeDriver(option);
		}
		@Test(priority=1,groups = { "Negative" , "Sanity" })
		@Severity(SeverityLevel.BLOCKER)
		@Description("TC#1 - Verify that with Invalid username and password, Login is not successfull !!")
		public void TestInvalidLogin() throws InterruptedException
		{
			driver.get("https://app.vwo.com/#/login");
			driver.findElement(By.id("login-username")).sendKeys("93npu2yyb009@esiix.com");
			driver.findElement(By.name("password")).sendKeys("Wingify@123");
			driver.findElement(By.className("btn--positive")).click();
			Thread.sleep(2000);
			String errorMsg = driver.findElement(By.id("js-notification-box-msg")).getText();
			Assert.assertEquals(errorMsg, "Your email, password, IP address or location did not match");
		}
		
		@Test(priority=2,groups= {"Positive","Smoke"})
		@Description("Verify that with Valid username and password, Login is successfull !!")
		public void TestValidLogin() throws InterruptedException
		{
			driver.get("https://app.vwo.com/#/login");
			driver.findElement(By.id("login-username")).sendKeys("93npu2yyb0@esiix.com");
			driver.findElement(By.name("password")).sendKeys("Wingify@123");
			driver.findElement(By.className("btn--positive")).click();
			Thread.sleep(2000);
			Assert.assertEquals(driver.getCurrentUrl(), "https://app.vwo.com/#/dashboard");
			Assert.assertEquals(driver.getTitle(), "Dashboard");
			
			driver.get("https://app.vwo.com/logout");
		}
		
		@AfterSuite
		public void tearDown()
		{
			driver.quit();
		}

}
