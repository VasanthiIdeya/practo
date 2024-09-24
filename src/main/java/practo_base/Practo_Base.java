package practo_base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Practo_Base 
{
protected static ExtentSparkReporter htmlreport;
protected static ExtentReports report;
protected static ExtentTest test;
protected static Properties properties;
public static WebDriver driver;

@BeforeSuite
public static void Report_Generation()
{
	    htmlreport = new ExtentSparkReporter(new File("C:\\Users\\VasanthiM\\Downloads\\practoreport.html"));
	    htmlreport.config().setReportName("saucelabs cucumber");
	    htmlreport.config().setDocumentTitle("saucelabs Test");
	    htmlreport.config().setTheme(Theme.DARK);
	    report = new ExtentReports();
	    report.setSystemInfo("Environment", "TestEnv");
	    report.setSystemInfo("TesterName", "Priya");
	    report.attachReporter(htmlreport);
}
@BeforeMethod
@Parameters({"x"})
public static void Browser_Setup(String x)
{
	properties = new Properties();
	try 
	{
        FileInputStream file = new FileInputStream("C:\\Users\\VasanthiM\\Downloads\\eclipsedocs\\practo\\src\\main\\java\\properties\\configure.properties");
        properties.load(file);
        file.close();
    } catch (IOException e) 
	{
        System.out.println("Error loading configuration.properties file: " + e.getMessage());
    }
	if(x.matches("edge"))
	{
	driver=new EdgeDriver();
	}
	if(x.matches("chrome"))
	{
		driver=new ChromeDriver();
	}
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    driver.manage().window().maximize();
    
}
public static void Open_URL()
{
	driver.get(properties.getProperty("url"));
}
@AfterMethod
public static void Driver_close()
{
	driver.close();
}
@AfterSuite
public static void Report_flush()
{
	report.flush();
}
}
