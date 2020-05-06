package com.HTEC.tests;

import com.HTEC.models.LoginData;
import com.HTEC.models.LoginResponse;
import com.HTEC.utils.PropertiesLoader;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.Properties;

public class Login {

	public static LoginResponse ValidLogin() {
		
		Properties properties = null;
		try {
			properties = PropertiesLoader.loadProperties("application.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		LoginData data = new LoginData(properties.getProperty("username"), properties.getProperty("password"));
		LoginResponse response = given()
			.contentType(ContentType.JSON)
			.body(data)
		.when()
			.post("/api/users/login")
			.as(LoginResponse.class);
		
		return response;
	}
}
