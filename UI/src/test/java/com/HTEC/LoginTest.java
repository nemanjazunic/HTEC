package com.HTEC;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginTest extends BaseTest {	
	
	WebDriver driver;
	WebDriverWait wait;
	protected LoginPage loginPage;
	
	@BeforeEach
	public void SetUp() {
		
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 5);
		loginPage = new LoginPage(driver);
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://qa-sandbox.apps.htec.rs/");
	}
	
	@Test
	public void ValidLoginTest() {				
		loginPage.Login();				
	}
	
	@AfterEach
	public void End() {
		driver.close();
		driver.quit();
	}	
}
