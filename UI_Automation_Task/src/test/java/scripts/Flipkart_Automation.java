package scripts;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart_Automation {
	
	public WebDriver driver = null;
	
	public class testAutomateFlipkart {
		
		@Test(priority=1)
		public void setUp() throws InterruptedException {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get("https://www.flipkart.com/");
		}
		
		@Test(priority=2)	
		public void login() throws InterruptedException {
			Actions act =  new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Enter Email/Mobile number')]"))).sendKeys("USEYOURUSERNAME").perform();
			driver.findElement(By.xpath("//form//input[@type='password']")).sendKeys("USEYOURPASSWORD");
			act.moveToElement(driver.findElement(By.xpath("//button//span[contains(text(),'Login')]"))).click().perform();			
			Thread.sleep(3000);
		}
		
		@Test(priority=3)
		public void addFirstItem() throws InterruptedException {
			driver.findElement(By.xpath("//div[text()='Mobiles']")).click();		
			driver.findElement(By.xpath("//div[contains(text(),'APPLE')]")).click();
			driver.findElement(By.xpath("//div[contains(text(),'APPLE iPhone 13 (Starlight, 128 GB)')]")).click();
			// Hold all the window handles in ArrayList
		    ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
		    //switch to new tab
		    driver.switchTo().window(newTb.get(1));
		    System.out.println("Page title of new tab: " + driver.getTitle());
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();
		}
		
		@Test(priority=4)
		public void addSecondItem() throws InterruptedException {
			Thread.sleep(2000);
		    driver.findElement(By.xpath("//img[@title='Flipkart'][@alt='Flipkart']")).click();
		    driver.findElement(By.xpath("//div[text()='Appliances']")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//div[text()='Sandwich Makers']")).click();
		    driver.findElement(By.xpath("//a[contains(text(),'AGARO Grand Grill')]")).click();
		    // Hold all the window handles in an ArrayList
		    ArrayList<String> newTb1 = new ArrayList<String>(driver.getWindowHandles());
		    // Switch to new tab
		    driver.switchTo().window(newTb1.get(2));
		    System.out.println("Page title of new tab: " + driver.getTitle());
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();
		}
		
		@Test(priority=5)
		public void removePreviouslyAddedItems() throws InterruptedException {
			Thread.sleep(3000);
		    driver.findElement(By.xpath("//a[contains(text(),'AGARO')]/../../../..//div[contains(text(),'Remove')]")).click();
		    driver.findElement(By.xpath("//div[text()='Remove Item']/..//div[text()='Remove']")).click();
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//a[contains(text(),'APPLE iPhone 13 (Starlight, 128 GB)')]/../../../..//div[contains(text(),'Remove')]")).click();
		    driver.findElement(By.xpath("//div[text()='Remove Item']/..//div[text()='Remove']")).click();
		}
		
		@Test(priority=6)
		public void teardown() {
			driver.quit();
		}
		
	}
}