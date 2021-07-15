package demo_testng;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import demo_testng.Screenshots;
import org.testng.ITestResult;
import demo_testng.ExtentFactory;

public class SeleniumLoginTest {
	
	private WebDriver driver;
	private String baseUrl;
	
	
	
	ExtentReports report;
	ExtentTest test;
	
	@BeforeSuite
	 public void beforesuit() {
		 
	 } 

	
	


	@BeforeTest
	@Parameters({"browser"})
	public void beforeClass(String browser) {
		baseUrl = "http://18.134.60.157/";
		
		
		
		if (browser.equalsIgnoreCase("Chrome")) {
			report = ExtentFactory.getInstance();
			
			
			
			
			//report = new ExtentReports("C:\\Users\\DELL\\Documents\\selnium_java\\demo_testng\\New folder\\logintestFirefox.html");
			
			test = report.startTest("chrome");
			driver = new ChromeDriver();
			
			test.log(LogStatus.INFO, " Browser Started...");
		} else if (browser.equalsIgnoreCase("firefox")) {
			report = ExtentFactory.getInstance();
			//report = new ExtentReports("C:\\Users\\DELL\\Documents\\selnium_java\\demo_testng\\New folder\\logintestchrome.html");
			test = report.startTest("firefox");
			
			driver = new FirefoxDriver();
			
		
			
			
			
			
			//test = report.startTest("Verify NavLinks");
			test.log(LogStatus.INFO, " Browser Started...");
		}
		
		
		//driver = new FirefoxDriver();
		

		// Maximize the browser's window
		driver.manage().window().maximize();
		//test.log(LogStatus.INFO, "Browser Maximized");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
		//test.log(LogStatus.INFO, "Web application opened");
	}
	
	@Test
	public void test1_validLoginTest() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		driver.get(baseUrl);
		//test.log(LogStatus.INFO, "Web application opened");
		
		
		WebElement header= driver.findElement(By.cssSelector("#om"));
		 
		 int i = header.findElements(By.cssSelector("li a")).size();
		 System.out.println(i);//Get number of links
		 //test.log(LogStatus.INFO, "getting all the links");
		 for(int j = 0;j<i;j++){    //create loop based upon number of links to traverse all links
			   //header= driver.findElement(By.cssSelector("#om li"));   // We are re-creating "footer" webelement as DOM changes after navigate.back()
				  WebElement nav= driver.findElements(By.cssSelector("#om li a")).get(j);   // We are re-creating "footer" webelement as DOM changes after navigate.back()
				   //WebElement elem =nav.findElements(By.tagName("a")).get(j);
				   js.executeScript("arguments[0].click();", nav);
				   
				      Thread.sleep(1000);
				   System.out.println(driver.getTitle());
				   if(driver.getTitle().contains("404")) {
					   Assert.assertFalse(driver.getTitle().contains("404"));
					   
					   
				       String link=driver.getCurrentUrl();
				       
				       test.log(LogStatus.FAIL, "Link  Accesible:"+link);
				       
				      }
				   else {
					   String link2=driver.getCurrentUrl();
					   test.log(LogStatus.PASS, "Link  Accesible:"+link2);
				   }
				   }
	}
	
	//@AfterMethod
   // public void getResult(ITestResult testResult) throws IOException
   // {
		//if (testResult.getStatus() == ITestResult.SUCCESS) {
			//String path = Screenshots.takeScreenshot(driver, testResult.getName());
			//String imagePath = test.addScreenCapture(path);
			//test.log(LogStatus.FAIL, "Verify Welcome Text Failed", imagePath);
		//}
        
   // }
	
	@AfterTest
	public void afterClass() {
	   test.log(LogStatus.PASS, "passed");
		
		//report.endTest(test);
		
		
		driver.quit();
		
		
	}
	@AfterClass
	public void aftersuit() {
		
		report.flush();
		
	}
}