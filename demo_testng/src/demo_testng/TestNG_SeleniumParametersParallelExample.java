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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestNG_SeleniumParametersParallelExample {
	
	WebDriver driver;
	String baseURL;
	ExtentReports report;
	ExtentTest test;
	
	@BeforeClass
	@Parameters({"browser"})
	public void setUp(String browser) {
		baseURL = "http://18.134.60.157/";
		if (browser.equalsIgnoreCase("firefox")) {
			report=ExtentFactory.getInstance();
			
			driver = new FirefoxDriver();
			
			test = report.startTest("Verify NavLinks Firefox");
		} else if (browser.equalsIgnoreCase("chrome")) {
			report=ExtentFactory.getInstance();
			
			driver = new ChromeDriver();
			test = report.startTest("Verify NavLinks Chrome");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);
	}
	
	@Test
	public void testLogin() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		
		
		test.log(LogStatus.INFO, "Web application opened");
		WebElement header= driver.findElement(By.cssSelector("#om"));
		 
		 int i = header.findElements(By.cssSelector("li a")).size();
		 System.out.println(i);//Get number of links
		 test.log(LogStatus.INFO, "getting all the links");
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
				      // System.out.println(link);
				       
				       
				       
				      }
				   else {
					   String link2=driver.getCurrentUrl();
					   test.log(LogStatus.PASS, "Link  Accesible:"+link2);
				  }
				   }
	
	}
	
	@AfterClass
	public void cleanUp() {
		driver.quit();
		report.endTest(test);
		report.flush();
	}
}