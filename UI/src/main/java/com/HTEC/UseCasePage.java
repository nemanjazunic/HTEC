package com.HTEC;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UseCasePage {

	WebDriver driver;
	WebDriverWait wait;
	
	By titleBy = By.xpath("//input [@name = 'title']");
	By descriptionBy = By.xpath("//textarea [@name = 'description']");
	By expectedResultBy = By.xpath("//input [@name = 'expected_result']");
	By useCaseStepOneBy = By.xpath("//input [@name = 'testStepId-0']");
	By addStepButtonBy = By.xpath("//button [@data-testid = 'add_step_btn']");
	By submitButtonBy = By.xpath("//button [@data-testid = 'submit_btn']");
	By deleteUseCaseButtonBy = By.xpath("//button [@data-testid = 'remove_usecase_btn']");
		
	By titleValidationBy = By.xpath("//input [@name = 'title']/following-sibling::div [@class = 'invalid-feedback']");
	By expectedResultValidationBy = By.xpath("//input [@name = 'expected_result']/following-sibling::div [@class = 'invalid-feedback']");
	By useCaseStepOneValidationBy = By.xpath("//input [@name = 'testStepId-0']/following-sibling::div [@class = 'invalid-feedback']");

	
	public UseCasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 5);
	}
	
	public void WaitUntilPageIsLoaded() {
		wait.until(ExpectedConditions.elementToBeClickable(submitButtonBy));
	}
	
	public WebElement GetSpecificUseCaseStep(int stepNumber) {
		String stepNumberString = String.valueOf(stepNumber-1);
		return driver.findElement(By.xpath("//input [@data-id = '" + stepNumberString + "']"));
	}
	
	public WebElement GetTitle() {
		return driver.findElement(titleBy);
	}
	
	public void SetTitle(String title) {
		WebElement Title = driver.findElement(titleBy);
		Title.clear();
		Title.sendKeys(title);
	}
	
	public WebElement GetDescription() {
		return driver.findElement(descriptionBy);
	}

	public void SetDescription(String description) {
		WebElement Description = driver.findElement(descriptionBy); 
		Description.clear();
		Description.sendKeys(description);
	}
	
	public WebElement GetExpectedResult() {
		return driver.findElement(expectedResultBy);
	}
	
	public void SetExpectedResult(String expectedResult) {
		WebElement ExpectedResult = driver.findElement(expectedResultBy); 
		ExpectedResult.clear();
		ExpectedResult.sendKeys(expectedResult);
	}
	
	public WebElement GetFirstStep() {
		return driver.findElement(useCaseStepOneBy);
	}
	
	public void SetFirstStep(String firstStep) {
		WebElement FirstStep = driver.findElement(useCaseStepOneBy); 
		FirstStep.clear();
		FirstStep.sendKeys(firstStep);
	}
	
	public WebElement GetTitleValidation() {
		return driver.findElement(titleValidationBy);
	}
	
	public WebElement GetExpectedResultValidation() {
		return driver.findElement(expectedResultValidationBy);
	}
	
	public WebElement GetStepOneValidation() {
		return driver.findElement(useCaseStepOneValidationBy);
	}
	
}
