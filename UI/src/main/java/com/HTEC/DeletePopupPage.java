package com.HTEC;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeletePopupPage {

	WebDriver driver;
	WebDriverWait wait;
	
	public DeletePopupPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 5);
	}	
	
	By deleteButtonBy = By.xpath("//button [@class = 'btn btn-lg btn-danger ']");
	
	public WebElement GetDeleteButton() {
		return driver.findElement(deleteButtonBy);
	}
}
