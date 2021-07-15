package demo_testng;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentFactory {
	public static ExtentReports getInstance() {
		ExtentReports extent;
		ExtentTest test;
		String Path = "C:\\Users\\DELL\\Documents\\selnium_java\\demo_testng\\New folder\\logintest.html";
		extent = new ExtentReports(Path, false);
		extent
	     .addSystemInfo("Selenium Version", "2.52")
	     .addSystemInfo("OS", "Windowsuuu");
	     

		return extent;
	}
	

}


