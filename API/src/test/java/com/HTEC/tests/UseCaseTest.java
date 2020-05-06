package com.HTEC.tests;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import com.HTEC.models.LoginResponse;
import com.HTEC.models.UseCase;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;

public class UseCaseTest {
	
	LoginResponse response = null;
	
	@Before
	public void SetUp() {
		RestAssured.baseURI = "https://qa-sandbox.apps.htec.rs";
		response = Login.ValidLogin();
	}

	@Test
	public void CommaSeparatedTestStep() {		
		
		List<String> testSteps = new ArrayList<String>();
		testSteps.add("one,two,three,four");
		
		UseCase useCase = new UseCase("TestTitle", "TestDesc", "TestResult", testSteps, false);
		
		given()
			.header("Authorization", "Bearer " + response.getToken())
			.contentType(ContentType.JSON)
			.body(useCase)
		.when()
			.post("/api/usecases/usecase")
		.then()
			.assertThat().statusCode(200);
		
		UseCase createdUseCase = given()
			.header("Authorization", "Bearer " + response.getToken())
			.contentType(ContentType.JSON)
		.when()
			.get("/api/usecases/" + GetAllUseCases().get(0).getUsecaseId())
			.as(UseCase.class);
			
		assertEquals(createdUseCase.getTitle(), useCase.getTitle());
		assertEquals(createdUseCase.getDescription(), useCase.getDescription());
		assertEquals(createdUseCase.getExpectedResult(), useCase.getExpectedResult());
		assertEquals(createdUseCase.getTeststeps(), Arrays.asList(useCase.getTeststeps().get(0).split(",")));
			
		CleanUp();
	}
	
	@Test
	public void HappyFlow() {
		
		List<String> testSteps = new ArrayList<String>();
		testSteps.add("HappyStep");
		
		UseCase useCase = new UseCase("HappyTitle", "HappyDecsription", "HappyResult", testSteps, true);
		
		given()
			.header("Authorization", "Bearer " + response.getToken())
			.contentType(ContentType.JSON)
			.body(useCase)
		.when()
			.post("/api/usecases/usecase")
		.then()
			.assertThat().statusCode(200);
				
		UseCase createdUseCase = given()
			.header("Authorization", "Bearer " + response.getToken())
			.contentType(ContentType.JSON)
		.when()
			.get("/api/usecases/" + GetAllUseCases().get(0).getUsecaseId())
			.as(UseCase.class);
		
		assertEquals(createdUseCase.getTitle(), useCase.getTitle());
		assertEquals(createdUseCase.getDescription(), useCase.getDescription());
		assertEquals(createdUseCase.getExpectedResult(), useCase.getExpectedResult());
		assertEquals(createdUseCase.getTeststeps(), useCase.getTeststeps());
		
		CleanUp();
	}
	
	@Test
	public void AllEmptyFields() {
		
		List<String> testSteps = new ArrayList<String>();		
		
		UseCase useCase = new UseCase("", "", "", testSteps, true);
		
		given()
			.header("Authorization", "Bearer " + response.getToken())
			.contentType(ContentType.JSON)
			.body(useCase)
		.when()
			.post("/api/usecases/usecase")
		.then()
			.assertThat().statusCode(400);
	}
	
	@Test
	public void StringLengthValidations() {
		
		List<String> testSteps = new ArrayList<String>();	
		testSteps.add("1");
		
		UseCase useCase = new UseCase("1", "1", "1", testSteps, true);
		
		given()
			.header("Authorization", "Bearer " + response.getToken())
			.contentType(ContentType.JSON)
			.body(useCase)
		.when()
			.post("/api/usecases/usecase")
		.then()
			.assertThat().statusCode(400);
		
		useCase = new UseCase("1234", "1234", "1234", testSteps, true);
		
		given()
			.header("Authorization", "Bearer " + response.getToken())
			.contentType(ContentType.JSON)
			.body(useCase)
		.when()
			.post("/api/usecases/usecase")
		.then()
			.assertThat().statusCode(400);
		
		useCase = new UseCase(RandomStringUtils.randomAlphabetic(256), RandomStringUtils.random(256), RandomStringUtils.randomAlphabetic(256), testSteps, true);
		
		//bug??
		//useCase = new UseCase(RandomStringUtils.random256), RandomStringUtils.random(256), RandomStringUtils.random(256), testSteps, true);

		given()
			.header("Authorization", "Bearer " + response.getToken())
			.contentType(ContentType.JSON)
			.body(useCase)
		.when()
			.post("/api/usecases/usecase")
		.then()
			.assertThat().statusCode(400);		
	}
	
	@Test
	public void DeleteUseCase() {
		
		List<String> testSteps = new ArrayList<String>();
		testSteps.add("HappyStep");
		
		UseCase useCase = new UseCase("HappyTitle", "HappyDecsription", "HappyResult", testSteps, true);
		
		given()
			.header("Authorization", "Bearer " + response.getToken())
			.contentType(ContentType.JSON)
			.body(useCase)
		.when()
			.post("/api/usecases/usecase")
		.then()
			.assertThat().statusCode(200);
		
		int createdUseCaseId = GetAllUseCases().get(0).getUsecaseId();
		
		given()
			.header("Authorization", "Bearer " + response.getToken())
			.contentType(ContentType.JSON)
		.when()
			.delete("/api/usecases/usecase/" + createdUseCaseId)
		.then()
			.assertThat().statusCode(200);
		
		given()
			.header("Authorization", "Bearer " + response.getToken())
			.contentType(ContentType.XML)
		.when()
			.get("/api/usecases/" + createdUseCaseId)
		.then()
			.assertThat().statusCode(403);		
		}
	
	private void CleanUp() {
		
		int createdUseCaseId = GetAllUseCases().get(0).getUsecaseId();
		
		given()
			.header("Authorization", "Bearer " + response.getToken())
			.contentType(ContentType.JSON)
		.when()
			.delete("/api/usecases/usecase/" + createdUseCaseId)
		.then()
			.assertThat().statusCode(200);
	}

	private UseCase GetUseCaseById(String useCaseId){
				
		UseCase useCase = given()
			.header("Authorization", "Bearer " + response.getToken())
		.when()
			.get("/api/usecases/" + useCaseId)
			.as(UseCase.class);
		
		return useCase;			
	}
	
	private List<UseCase> GetAllUseCases(){
		
		List<UseCase> allUseCases = new ArrayList<UseCase>();
		LoginResponse response = Login.ValidLogin();
		
		allUseCases = given()
			.header("Authorization", "Bearer " + response.getToken())
			.contentType(ContentType.JSON)
		.when()
			.get("/api/usecases/all")
			.as(new TypeRef<List<UseCase>>() {});
		
		return allUseCases;
	}


}
