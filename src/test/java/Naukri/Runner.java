package Naukri;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Runner {
	
	
	static WebDriver driver;
	static int no;
	
	static Date date = new Date();
	
	
	@BeforeMethod
	public void setUp() 
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://www.naukri.com/");
	}

	@AfterTest
	public void tearDown() 
	{
		driver.quit();
	}

	
	@Test(dataProvider = "getData", dataProviderClass = DataSet.class, retryAnalyzer=MyRetry.class)
	public void applyForJob(String jobName,String location) throws InterruptedException, IOException 
	{

		// login to naukari
		Utilities.getLogin("bobades340@gmail.com", "Swapnil");

		// For Which job
		System.out.println(jobName);

		// click on search jobs
		Utilities.clickOnSearchJob();

		// entering job name
		Utilities.enterJobName(jobName);

		// selecting experience
		Utilities.selectExperience();

		//click on Fresher
		Utilities.clickOnFresher();

		// entering location
		Utilities.enterLocation(location);

		// click on search button
		Utilities.clickOnSearchButton();

		//click on company jobs 
//		Utilities.clickOnComponyJobs();
		
//		//selecting education
//		Utilities.clickOnAnyGraduate();
//		Utilities.clickOnAnyPostGraduate();
		
		//Selecting department (Engineering)
//		Utilities.clickOnDeptEng();
		
		//selecting freshness of posting jobs (3days)
		Utilities.selectFreshness3Days();
		
		// Applying for jobs
		Utilities.Applying();
		
	}
}















