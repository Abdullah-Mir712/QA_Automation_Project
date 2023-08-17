package testNG_sample;

import java.time.Duration;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;

public class Test1 {

	public static WebDriver driver;
	public static Logger log;

	@BeforeTest
	public void setup() {
		log= Logger.getLogger("Test1");
		PropertyConfigurator.configure("Log4j.properties");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		log.info("chromedriver setup is complete");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/");
		log.info("Logged into XYZ Bank");
		driver.manage().window().maximize();
	}

	@Test
	public void Test() {
		try {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Bank')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add Customer')]")))
				.click();
		log.info("logged in to bank manager account ");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder=\"First Name\"]")))
				.sendKeys("Muhammad");
		driver.findElement(By.xpath("//input[@placeholder=\"Last Name\"]")).sendKeys("Sufyan");
		driver.findElement(By.xpath("//input[@placeholder=\"Post Code\"]")).sendKeys("54000");
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		log.info("filled and submitted the fields of costumer account ");
		driver.switchTo().alert().accept();
		} catch(Exception e) {
			   captureScreenshot("Test1Failure");
	            log.error("Test1 failed: " + e.getMessage());
		}

	}

	@Test
	public void Test2() {
		try {
		driver.findElement(By.xpath("//button[contains(text(),'Open Account')]")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dropdownCustomer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='userSelect']")));
		Select selectDropdown = new Select(dropdownCustomer);
		selectDropdown.selectByVisibleText("Muhammad Sufyan");
		WebElement dropdownCurrency = driver.findElement(By.xpath("//*[@id=\"currency\"]"));
		Select selectDropdown2 = new Select(dropdownCurrency);
		selectDropdown2.selectByVisibleText("Dollar");
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		driver.switchTo().alert().accept();
		log.info("opened the customer account for transactions ");
		}catch(Exception e) {
			   captureScreenshot("Test2Failure");
	            log.error("Test2 failed: " + e.getMessage());
		}

	}
	
	@Test
	public void Test3(){
		try {
		driver.findElement(By.xpath("//button[@class=\"btn home\"]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Customer')]")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//select[@id=\"userSelect\"]")));
		
		
		WebElement dropdownUser = driver.findElement(By.xpath("//select[@id=\"userSelect\"]")); 
		Select selectDropdown = new Select(dropdownUser);
		selectDropdown.selectByVisibleText("Muhammad Sufyan");
		driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
		log.info("Costumer logged in to its account ");
		}catch(Exception e) {
			   captureScreenshot("Test3Failure");
	            log.error("Test3 failed: " + e.getMessage());
		}

	}
	
	@Test
	public void Test4(){
		try {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Deposit')]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Deposited')]//following-sibling::input"))).sendKeys("5500");			
		driver.findElement(By.xpath("//button[@type=\"submit\" and contains(text(),'Deposit'  )]")).click();	
		log.info("Costumer deposited its money");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@ng-class=\"btnClass3\"]"))).click();		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Withdrawn')]//following-sibling::input"))).sendKeys("2000");		
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();		
		log.info("Costumer made wiyhdrawal");
		}catch(Exception e) {
			   captureScreenshot("Test4Failure");
	            log.error("Test4 failed: " + e.getMessage());
		}
		
	}


	
	
	@Test
	public void Test5() {
		try {
		driver.findElement(By.xpath("//button[@class=\"btn btn-lg tab\" and @ng-click=\"transactions()\"]")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@ng-show=\"showDate\"]"))).click();
		log.info("Transaction History is deleted");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@ng-show=\"logout\"]")));
		log.info("Costumer logged out from its account");

		driver.findElement(By.xpath("//button[@class=\"btn home\"]")).click();
		driver.findElement(By.xpath("//button[@class=\"btn btn-primary btn-lg\" and contains(text(),'Bank')]")).click();
		driver.findElement(By.xpath("//button[@ng-class=\"btnClass3\"]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'Muhammad')]//following-sibling::td/button"))).click();
		driver.findElement(By.xpath("//button[@class=\"btn home\"]")).click();	
		log.info("Logged in to Bank Manager deleted the costumer account");
		} catch (Exception e) {
            captureScreenshot("Test5Failure");
            log.error("Test5 failed: " + e.getMessage());
        }
	}
	
	
	@AfterTest
	public void ending() {
		captureScreenshot("TestCompletion"); 
		driver.quit();
		log.info("Quitting the driver");
	}
	

	
	public void captureScreenshot(String screenshotName) {
	    try {
	        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        String destinationPath = "screenshots/" + screenshotName + ".png"; 
	        FileUtils.copyFile(screenshotFile, new File(destinationPath));
	        log.info("Screenshot captured: " + destinationPath);
	    } catch (Exception e) {
	        log.error("Failed to capture screenshot: " + e.getMessage());
	    }
	}
	

	

}