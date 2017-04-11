package pl.edu.pjwstk.lab6;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SomeSiteTest {

	private static WebDriver driver;
	WebElement element;
	private static int id = 1;

	@BeforeClass
	public static void driverSetup() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability("takesScreenshot", true);

//		char sep = '\\';
//		char sep = '/';
//		String path = ".." + sep + "Phantom" + sep + "phantomjs-2.1.1-windows" + sep + "bin" + sep + "phantomjs.exe";
		String path = "/tmp/phantomjs";
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, path);
		driver = new PhantomJSDriver(caps);

		driver.get("https://javastart.pl/");
	}

	@AfterClass
	public static void cleanp() {
		driver.quit();
	}

	@After
	public void takeScreenshot() {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String fileName = "Test" + id++ + ".png";

		try {
			FileUtils.copyFile(screenshot, new File("target\\img\\" + fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void clearField(String username, String password) {
		driver.findElement(By.name(username)).clear();
		driver.findElement(By.name(password)).clear();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void correctLoginTest() {
		driver.findElement(By.partialLinkText("Zaloguj")).click();
		driver.findElement(By.name("username")).sendKeys("tau");
		driver.findElement(By.name("password")).sendKeys("tau");
		driver.findElement(By.name("password")).submit();

		// http://sqa.stackexchange.com/questions/2696/selenium-how-to-identify-the-button-webelement
		element = driver.findElement(By.xpath("//button[contains(.,'Wyloguj')]"));
		assertNotNull(element);

		takeScreenshot();

		element = driver.findElement(By.name("password"));

		element.click(); // Wylogowanie się, żeby powrócić do stanu przed testem
	}

	@Test
	public void failedLoginTest() {
		driver.findElement(By.partialLinkText("Zaloguj")).click();
		driver.findElement(By.name("username")).sendKeys("błędnyużytkownik");
		driver.findElement(By.name("password")).sendKeys("błędnehasło");
		driver.findElement(By.name("password")).click();

		element = driver.findElement(By.name("password"));
		assertNotNull(element);

		driver.findElement(By.name("username")).sendKeys("tau");
		driver.findElement(By.name("password")).sendKeys("błędnehasło");
		driver.findElement(By.name("password")).click();

		element = driver.findElement(By.name("password"));
		assertNotNull(element);

		driver.findElement(By.name("username")).sendKeys("błędnyużytkownik");
		driver.findElement(By.name("password")).sendKeys("tau");
		driver.findElement(By.name("password")).click();

		element = driver.findElement(By.name("password"));
		assertNotNull(element);
	}
}
