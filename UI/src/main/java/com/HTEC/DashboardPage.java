package com.HTEC;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

	WebDriver driver;
	WebDriverWait wait;
	
	By profileCard = By.xpath("//div [@data-testid = 'profile_card_id']");
	By useCaseCard = By.xpath("//div [@data-testid = 'use_cases_card_id']");
	By playgroundCard = By.xpath("//div [@data-testid = 'playground_card_id']");
	By reportingIssuesCard = By.xpath("//div [@data-testid = 'reports_card_id']");
	
	
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 5);

	}
	
	private void WaitUntilPageIsLoaded() {
		wait.until(ExpectedConditions.elementToBeClickable(profileCard));
	}
	
	public void GoToUseCases() {
		WaitUntilPageIsLoaded();
		driver.findElement(useCaseCard).click();
	}
	
	
	
}
