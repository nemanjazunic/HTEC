package com.HTEC;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UseCaseTest extends BaseTest{

	WebDriver driver;
	WebDriverWait wait;
	LoginPage loginPage;
	DashboardPage dashboardPage;
	UseCasesPage useCasesPage;
	UseCasePage useCasePage;
	DeletePopupPage deletePopupPage;
	
	private String Title = "Test Title";
	private String Description = "Test Description";
	private String ExpectedResult = "Test Expected Result";
	private String FirstStep = "Test First Step";
	private String BaseString = "This field previously had %s characters";
	
	public UseCaseTest() {
		
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 5);
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
		useCasesPage = new UseCasesPage(driver);
		useCasePage = new UseCasePage(driver);	
		deletePopupPage = new DeletePopupPage(driver);
	}
	
	@BeforeEach
	public void SetUp() {

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://qa-sandbox.apps.htec.rs/");
	}
	
	private String GetFieldNumberOfCharacters(WebElement field) {
		
		return String.valueOf(field.getAttribute("value").length());
	}
	
	private void DeleteCreatedUseCase(String useCaseTitle) {
		
		assertTrue(useCasesPage.FindUseCaseByName(useCaseTitle) != null);
		
		useCasesPage.FindUseCaseByName(useCaseTitle).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(useCasePage.deleteUseCaseButtonBy));

		driver.findElement(useCasePage.deleteUseCaseButtonBy).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(deletePopupPage.deleteButtonBy));
		deletePopupPage.GetDeleteButton().click();
	}
	
	private void LoginAndCreateAUseCase() {
		
		loginPage.Login();
		wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.useCaseCard));
		
		driver.findElement(dashboardPage.useCaseCard).click();
		wait.until(ExpectedConditions.elementToBeClickable(useCasesPage.createUseCase));
		
		driver.findElement(useCasesPage.createUseCase).click();
		wait.until(ExpectedConditions.elementToBeClickable(useCasePage.submitButtonBy));
		
		useCasePage.SetTitle(Title);
		useCasePage.SetDescription(Description);
		useCasePage.SetExpectedResult(ExpectedResult);
		useCasePage.SetFirstStep(FirstStep);
		driver.findElement(useCasePage.submitButtonBy).click();	
		
		wait.until(ExpectedConditions.elementToBeClickable(useCasesPage.createUseCase));
	}
	
	@Test 
	public void HappyFlow() {	
		//login and create a use case
		LoginAndCreateAUseCase();
		
		useCasesPage.FindUseCaseByName(Title).click();
		wait.until(ExpectedConditions.elementToBeClickable(useCasePage.submitButtonBy));
		
		Assert.assertEquals(useCasePage.GetTitle().getAttribute("value"), Title);
		Assert.assertEquals(useCasePage.GetDescription().getAttribute("value"), Description);
		Assert.assertEquals(useCasePage.GetExpectedResult().getAttribute("value"), ExpectedResult);
		Assert.assertEquals(useCasePage.GetFirstStep().getAttribute("value"), FirstStep);		
		
		driver.findElement(useCasePage.submitButtonBy).click();
		wait.until(ExpectedConditions.elementToBeClickable(useCasesPage.createUseCase));
		
		//delete created use case		
		DeleteCreatedUseCase(Title);	
	}
	
	@Test
	public void SpecialCharactersTest() {
		
		Title = "!@#$%^&*() /*-++_[]{}|;:<>?./";
		Description = "/*-+=";
		ExpectedResult = "|}{][";
		FirstStep = "*&^%~`";
		
		//login and create a use case
		LoginAndCreateAUseCase();
		
		useCasesPage.FindUseCaseByName(Title).click();
		wait.until(ExpectedConditions.elementToBeClickable(useCasePage.submitButtonBy));
		
		Assert.assertEquals(useCasePage.GetTitle().getAttribute("value"), Title);
		Assert.assertEquals(useCasePage.GetDescription().getAttribute("value"), Description);
		Assert.assertEquals(useCasePage.GetExpectedResult().getAttribute("value"), ExpectedResult);
		Assert.assertEquals(useCasePage.GetFirstStep().getAttribute("value"), FirstStep);		
		
		driver.findElement(useCasePage.submitButtonBy).click();
		wait.until(ExpectedConditions.elementToBeClickable(useCasesPage.createUseCase));
		
		//delete created use case		
		DeleteCreatedUseCase(Title);	
	}
	
	@Test
	public void StringLengthValidationTest() {
		//to short input values
		Title = "1";
		Description = "1";
		ExpectedResult = "1";
		FirstStep = "1";
		
		loginPage.Login();
		wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.useCaseCard));
		
		driver.findElement(dashboardPage.useCaseCard).click();
		wait.until(ExpectedConditions.elementToBeClickable(useCasesPage.createUseCase));
		
		driver.findElement(useCasesPage.createUseCase).click();
		wait.until(ExpectedConditions.elementToBeClickable(useCasePage.submitButtonBy));
		
		useCasePage.SetTitle(Title);
		useCasePage.SetDescription(Description);
		useCasePage.SetExpectedResult(ExpectedResult);
		useCasePage.SetFirstStep(FirstStep);
		driver.findElement(useCasePage.submitButtonBy).click();	
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(useCasePage.titleValidationBy));
		
		Assert.assertTrue(useCasePage.GetTitleValidation().isDisplayed());
		Assert.assertEquals("Title needs to be between 5 and 255", useCasePage.GetTitleValidation().getText());
		Assert.assertTrue(useCasePage.GetExpectedResult().isDisplayed());
		Assert.assertEquals("Expected results needs to be between 5 and 255", useCasePage.GetExpectedResultValidation().getText());
		
		Title = "1234";
		Description = "1234";
		ExpectedResult = "1234";
		FirstStep = "1234";
		
		useCasePage.SetTitle(Title);
		useCasePage.SetDescription(Description);
		useCasePage.SetExpectedResult(ExpectedResult);
		useCasePage.SetFirstStep(FirstStep);
		driver.findElement(useCasePage.submitButtonBy).click();	
		
		Assert.assertTrue(useCasePage.GetTitleValidation().isDisplayed());
		Assert.assertEquals("Title needs to be between 5 and 255", useCasePage.GetTitleValidation().getText());
		Assert.assertTrue(useCasePage.GetExpectedResult().isDisplayed());
		Assert.assertEquals("Expected results needs to be between 5 and 255", useCasePage.GetExpectedResultValidation().getText());
		
		//too long input values		
		String longString = RandomStringUtils.randomAlphabetic(256);
		
		Title = longString;
		Description = longString;
		ExpectedResult = longString;
		FirstStep = longString;
		
		useCasePage.SetTitle(Title);
		useCasePage.SetDescription(Description);
		useCasePage.SetExpectedResult(ExpectedResult);
		useCasePage.SetFirstStep(FirstStep);
		driver.findElement(useCasePage.submitButtonBy).click();	
		
		Assert.assertTrue(useCasePage.GetTitleValidation().isDisplayed());
		Assert.assertEquals("Title needs to be between 5 and 255", useCasePage.GetTitleValidation().getText());
		Assert.assertTrue(useCasePage.GetExpectedResult().isDisplayed());
		Assert.assertEquals("Expected results needs to be between 5 and 255", useCasePage.GetExpectedResultValidation().getText());
	}
	
	@Test
	public void EditCreatedUseCaseTest() {		
		//login and create a use case
		LoginAndCreateAUseCase();
		
		//edit created use case
		WebElement CreatedUseCase = useCasesPage.FindUseCaseByName(Title);
		Assert.assertTrue(CreatedUseCase.isDisplayed());
		CreatedUseCase.click();
		wait.until(ExpectedConditions.elementToBeClickable(useCasePage.submitButtonBy));		
		
		String TitleNumberOfCharacters = GetFieldNumberOfCharacters(useCasePage.GetTitle());
		String DescriptionNumberOfCharacters = GetFieldNumberOfCharacters(useCasePage.GetDescription());
		String ExpectedResultNumberOfCharacters = GetFieldNumberOfCharacters(useCasePage.GetExpectedResult());
		String FirstStepNumberOfCharacters = GetFieldNumberOfCharacters(useCasePage.GetFirstStep());

		useCasePage.SetTitle(String.format(BaseString, TitleNumberOfCharacters));
		useCasePage.SetDescription(String.format(BaseString, DescriptionNumberOfCharacters));
		useCasePage.SetExpectedResult(String.format(BaseString, ExpectedResultNumberOfCharacters));
		useCasePage.SetFirstStep(String.format(BaseString, FirstStepNumberOfCharacters));
		
		driver.findElement(useCasePage.submitButtonBy).click();	
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(useCasesPage.createUseCase));
		useCasesPage.FindUseCaseByName(String.format(BaseString, TitleNumberOfCharacters)).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(useCasePage.submitButtonBy));
		
		Assert.assertEquals(String.format(BaseString, TitleNumberOfCharacters), useCasePage.GetTitle().getAttribute("value"));
		Assert.assertEquals(String.format(BaseString, DescriptionNumberOfCharacters), useCasePage.GetDescription().getAttribute("value"));
		Assert.assertEquals(String.format(BaseString, ExpectedResultNumberOfCharacters), useCasePage.GetExpectedResult().getAttribute("value"));
		Assert.assertEquals(String.format(BaseString, FirstStepNumberOfCharacters), useCasePage.GetFirstStep().getAttribute("value"));
		
		driver.findElement(useCasePage.submitButtonBy).click();	
		
		//delete created use case
		wait.until(ExpectedConditions.elementToBeClickable(useCasesPage.createUseCase));
		
		DeleteCreatedUseCase(String.format(BaseString, TitleNumberOfCharacters));		
	}
	
	@Test
	public void MandatoryFieldsTest() {
		//login
		loginPage.Login();
		wait.until(ExpectedConditions.elementToBeClickable(dashboardPage.useCaseCard));
		
		driver.findElement(dashboardPage.useCaseCard).click();
		wait.until(ExpectedConditions.elementToBeClickable(useCasesPage.createUseCase));
		
		driver.findElement(useCasesPage.createUseCase).click();
		wait.until(ExpectedConditions.elementToBeClickable(useCasePage.submitButtonBy));
		
		driver.findElement(useCasePage.submitButtonBy).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(useCasePage.titleValidationBy));
		
		Assert.assertTrue(useCasePage.GetTitleValidation().isDisplayed());
		Assert.assertTrue(useCasePage.GetExpectedResultValidation().isDisplayed());
		Assert.assertTrue(useCasePage.GetStepOneValidation().isDisplayed());			
	}
	
	@AfterEach
	public void End() {
		driver.close();
	}

}
