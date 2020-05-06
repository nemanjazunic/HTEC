package com.HTEC;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UseCasesPage {

	WebDriver driver;
	WebDriverWait wait;
	
	By createUseCase = By.xpath("//a [@data-testid = 'create_use_case_btn']");
	
	
	public UseCasesPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 5);
	}
	
	public void WaitUntilPageIsLoaded() {
		wait.until(ExpectedConditions.elementToBeClickable(createUseCase));
	}
	
	public WebElement FindUseCaseByName(String useCaseName) {				
		return driver.findElement(By.xpath("//a [contains(text(), '" + useCaseName + "')]"));
	}
	
	
	
	
}
