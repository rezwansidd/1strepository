package mpack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NegetiveTest {

	private WebDriver browser;
	
@BeforeMethod(alwaysRun=true)

	public void setup() {
		System.out.println(" let the test begin .. begin ... begin....");
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

		browser = new ChromeDriver();
		browser.manage().window().maximize();
		browser.get("https://the-internet.herokuapp.com/login");
	}
@Test(priority = 1)
public void postest() {
browser.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("tomsmith");
browser.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("SuperSecretPassword!");
browser.findElement(By.xpath("//*[@id=\"login\"]/button/i")).click();		
WebElement logoutbutton = browser.findElement(By.xpath("//*[@id=\"content\"]/div/a"));
Assert.assertTrue(logoutbutton.isDisplayed(), "Logout button is NOT displayed");

//check message on the Logout Screen
String expectedMessage = "You logged into a secure area!";
String actualMessage = browser.findElement(By.xpath("//*[@id=\"flash\"]")).getText();
Assert.assertTrue(actualMessage.contains(expectedMessage), "The Message is not correct\n Actual Message: "+actualMessage+"\n Expected Message : "+expectedMessage);

WebElement LogoutButton = browser.findElement(By.xpath("//i[contains(text(),'Logout')]"));
Assert.assertTrue(LogoutButton.isDisplayed(),"The Button is not displayed");

LogoutButton.click();
}

	@Test(priority = 2)
	public void neg1() {

		browser.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("tomsmith");
		browser.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("1SuperSecretPassword!");
		browser.findElement(By.xpath("//*[@id=\"login\"]/button/i")).click();

		String ExpectedError =  "Your password is invalid!";
		String ActualError = browser.findElement(By.xpath("//*[@id=\"flash\"]")).getText();
		Assert.assertTrue(ActualError.contains(ExpectedError), "The Message is not correct\n Actual Message: "+ActualError+"\n Expected Message : "+ExpectedError);
	}

	@Test(priority = 3)
	public void neg2() {
	
		browser.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("1tomsmith1");
		browser.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("SuperSecretPassword!");
		browser.findElement(By.xpath("//*[@id=\"login\"]/button/i")).click();
		String ExpectedError = "Your username is invalid!";
		String ActualError = browser.findElement(By.xpath("//*[@id=\"flash\"]")).getText();
		Assert.assertTrue(ActualError.contains(ExpectedError), "The Message is not correct\n Actual Message: "+ActualError+"\n Expected Message : "+ExpectedError);
		}


	@AfterMethod
	public void closeall() {
		browser.quit();

	}
}
