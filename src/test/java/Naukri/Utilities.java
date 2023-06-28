package Naukri;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class Utilities extends Runner {

	// creating random time for heating user action
	static Random random = new Random();

	// Initialize Java script top casting
	static JavascriptExecutor js = (JavascriptExecutor) driver;

	static WebElement loc;
//	static FileWriter write;

	static String getParentWindows() {
		Set<String> windHandles = driver.getWindowHandles();
		Iterator<String> windIds = windHandles.iterator();
		String pWindId = windIds.next();

		return pWindId;
	}

	static String getChildWindows() {
		Set<String> windHandles = driver.getWindowHandles();
		Iterator<String> windIds = windHandles.iterator();
		String pWindId = windIds.next();
		String cWindId = windIds.next();

		return cWindId;
	}

	static void getLogin(String email, String password) throws InterruptedException {

		Utilities.sleep();
		// click on login button
		driver.findElement(By.linkText("Login")).click();
		Utilities.sleep();

		// entering an email
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys(email);
		Utilities.sleep();

		// entering password
		driver.findElement(By.cssSelector("input[placeholder='Enter your password']")).sendKeys(password);
		Utilities.sleep();

		// click on login button
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Utilities.sleep();
	}

	static int getTime() {
		int a = random.nextInt(1, 1000);
		int b = random.nextInt(8900, 9800);
		int c = random.nextInt(a, b);
		return c;
	}

	static void sleep() throws InterruptedException {
		Thread.sleep(getTime());
	}

	static void clickOnSearchJob() throws InterruptedException {
		driver.findElement(By.xpath("(//div[@class='nI-gNb-sb__main'])[1]")).click();
		Utilities.sleep();
	}

	static void enterJobName(String jobName) throws InterruptedException {
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys(jobName);
		Utilities.sleep();
	}

	static void selectExperience() throws InterruptedException {
		driver.findElement(By.id("experienceDD")).click();
		Utilities.sleep();
	}

	static void clickOnFresher() throws InterruptedException {
		driver.findElement(By.cssSelector("li[title='Fresher']")).click();
		Utilities.sleep();
	}

	static void enterLocation(String location) throws InterruptedException {
		WebElement loc = driver.findElement(By.cssSelector("input[placeholder='Enter location']"));
		loc.sendKeys(location);
		Utilities.sleep();
	}

	static void clickOnSearchButton() throws InterruptedException {
		driver.findElement(By.xpath("//span[normalize-space()='Search']")).click();
		Utilities.sleep();
	}

	static void clickOnComponyJobs() throws InterruptedException {
		driver.findElement(By.xpath("//span[@title='Company Jobs']")).click();
		Utilities.sleep();
	}

	static void clickOnDeptEng() throws InterruptedException {
		js.executeScript("window.scrollBy(0,300);");

		driver.findElement(By.xpath("//span[contains(text(),'Engineering - Software & QA')]")).click();
		Utilities.sleep();
	}

	static void clickOnAnyGraduate() throws InterruptedException {
		js.executeScript("window.scrollBy(0,1200);");

		driver.findElement(
				By.xpath("//label[@for='chk-Any Graduate-ugTypeGid-']//i[@class='fleft naukicon naukicon-checkbox']"))
				.click();
		Utilities.sleep();
	}

	static void clickOnAnyPostGraduate() throws InterruptedException {

		driver.findElement(By
				.xpath("//label[@for='chk-Any Postgraduate-pgTypeGid-']//i[@class='fleft naukicon naukicon-checkbox']"))
				.click();
		Utilities.sleep();
	}

	static void selectFreshness3Days() throws InterruptedException {

		js.executeScript("window.scrollBy(0,1200);");

		driver.findElement(By.xpath("//i[@class='icon srchIc naukicon naukicon-arrow grey-text icon-16 arw DDarwDwn']"))
				.click();
		Utilities.sleep();
		driver.findElement(By.linkText("Last 3 days")).click();
		Utilities.sleep();
	}

	public static void Applying() throws InterruptedException, IOException
	{
		FileWriter fw = null;
		
		fw = new FileWriter("D:\\ECLIPSE\\Naukri\\src\\test\\java\\Naukri" + date.getTime() + ".txt");
		fw.write("Company Name													" + "					Applied Role \n");
		fw.write("======".repeat(35)+ "\n");
		
		List<WebElement> low = driver.findElements(By.cssSelector("article[class='jobTuple']"));

		for (int i = 0; i < low.size(); i++) 
		{
			low = driver.findElements(By.cssSelector("article[class='jobTuple']"));
				
				low.get(i).click();
				Utilities.sleep();
				
					driver.switchTo().window(Utilities.getChildWindows());
					String companyName = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[2]/div[1]/section[1]/div[1]/div[1]/div[1]/a[1]")).getText();

					try {
						if (driver.findElement(By.cssSelector("div[class='apply-button-container'] button[class='apply-button']")).getText().contains("Apply")) 
						{

							driver.findElement(By.cssSelector("div[class='apply-button-container'] button[class='apply-button']")).click();
							Utilities.sleep();

							String appliedJob = driver.findElement(By.xpath("//span[@class='apply-message']//strong")).getText();

							fw.write(companyName + "																	" + appliedJob + "\n");

							driver.switchTo().window(Utilities.getChildWindows()).close();
							Utilities.sleep();
							driver.switchTo().window(Utilities.getParentWindows());
						}
					} catch (Exception e) {
						System.out.println("Should done manualy only...!!");
						driver.switchTo().window(Utilities.getChildWindows()).close();
						Utilities.sleep();
						driver.switchTo().window(Utilities.getParentWindows());
					}
		}
		fw.close();
	}
}
