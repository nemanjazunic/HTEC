package com.HTEC;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.HTEC.utils.PropertiesLoader;

public class LoginPage {

	WebDriver driver;
	WebDriverWait wait;
	
	By welcomeMessage = By.cssSelector(".lead");
	By loginButton = By.xpath("//a [@class = 'btn btn-lg btn-secondary']");
	By usernameField = By.xpath("//input [@type = 'email']");
	By passwordField = By.xpath("//input [@type = 'password']");
	By submittButton = By.xpath("//button [@type = 'submit']");
	
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 5);
	}
	
	private void WaitUntilPageIsLoaded() {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
	}
	
	public void Login() {
		
		Properties properties = null;
		try {
			properties = PropertiesLoader.loadProperties("application.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WaitUntilPageIsLoaded();
		driver.findElement(loginButton).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(usernameField));
		
		driver.findElement(usernameField).sendKeys(properties.getProperty("username"));
		driver.findElement(passwordField).sendKeys(properties.getProperty("password"));
		driver.findElement(submittButton).click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(submittButton));
		
	}

	
	
}
