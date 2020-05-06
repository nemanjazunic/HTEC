package com.HTEC.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UseCase {
	
	@JsonProperty("user_id")
	private int userId;
	@JsonProperty("usecase_id")
	private int usecaseId;

	private String title;
	private String description;
	@JsonProperty("expected_result")
	private String expectedResult;
	private List<String> teststeps;
	private boolean automated;	
	
	
	
	public UseCase(int userId, int usecaseId, String title, String description, String expectedResult,
			boolean automated) {
		super();
		this.userId = userId;
		this.usecaseId = usecaseId;
		this.title = title;
		this.description = description;
		this.expectedResult = expectedResult;
		this.automated = automated;
	}
	
	public UseCase() {
		super();
	}
	public UseCase(String title, String description, String expectedResult, List<String> teststeps, boolean automated) {
		super();
		this.title = title;
		this.description = description;
		this.expectedResult = expectedResult;
		this.teststeps = teststeps;
		this.automated = automated;
	}
	public UseCase(int userId, int usecaseId, String title, String description, String expectedResult,
			List<String> teststeps, boolean automated) {
		super();
		this.userId = userId;
		this.usecaseId = usecaseId;
		this.title = title;
		this.description = description;
		this.expectedResult = expectedResult;
		this.teststeps = teststeps;
		this.automated = automated;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getUsecaseId() {
		return usecaseId;
	}
	public void setUsecaseId(int usecaseId) {
		this.usecaseId = usecaseId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExpectedResult() {
		return expectedResult;
	}
	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}
	public List<String> getTeststeps() {
		return teststeps;
	}
	public void setTeststeps(List<String> teststeps) {
		this.teststeps = teststeps;
	}
	public boolean isAutomated() {
		return automated;
	}
	public void setAutomated(boolean automated) {
		this.automated = automated;
	}
	

	




}
