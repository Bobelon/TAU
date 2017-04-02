package pl.edu.pjwstk.lab5;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTest {
	
	private static WebDriver driver;
	WebElement element;
	private static int id = 1;

	@BeforeClass
	public static void driverSetup() {
		System.setProperty("webdriver.chrome.driver", "..\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();		

		// Przechodzenie do sekcji logowania
		driver.get("http://localhost/TIN/Projekt/Kod/src/");	
		driver.findElement(By.linkText("Zaloguj")).click();
	}
	
	@After
	public void takeScreenshot(){		
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String fileName = "Test" + id++ + ".png";
		
		try {
			FileUtils.copyFile(screenshot, new File("target\\img\\" + fileName));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void clean(){		
		driver.quit();
	}

	@Test
	public void loginPageTest(){		
		element = driver.findElement(By.name("username"));
		assertNotNull(element);
		
		element = driver.findElement(By.name("password"));
		assertNotNull(element);
	}
	
	@Test
	public void wrongLoginTest() {
		// wrong username and password
		driver.findElement(By.name("username")).sendKeys("błędnyużytkownik");
		driver.findElement(By.name("password")).sendKeys("błędnehasło");
		driver.findElement(By.name("password")).submit();
		
		element = driver.findElement(By.linkText("Zaloguj"));
		assertNotNull(element);
		
		// wrong username
		driver.findElement(By.name("username")).sendKeys("błędnyużytkownik");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.name("password")).submit();
		
		element = driver.findElement(By.linkText("Zaloguj"));
		assertNotNull(element);
		
		// wrong password
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("błędnehasło");
		driver.findElement(By.name("password")).submit();
		
		element = driver.findElement(By.linkText("Zaloguj"));
		assertNotNull(element);
	}
	
	@Test
	public void correctLoginTest() {
		// correct username and password
		driver.findElement(By.name("username")).sendKeys("admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.name("password")).submit();
		
		element = driver.findElement(By.linkText("Wyloguj"));
		assertNotNull(element);
		
		takeScreenshot();
		
		element.click(); // Wylogowanie się, żeby powrócić do stanu przed testem
	}

	@AfterClass
	public static void cleanp() {
		driver.quit();
	}
}
