package com.aurionpro.model;

public class UserAccount {
	
	private int userId;
	private String firstName;
	private String lastName;
	private String userName;
	private int accountNo;
	private String accountType;
	private int balance;
	
	public UserAccount(int userId, String firstName, String lastName, String userName, int accountNo,
			String accountType, int balance) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.accountNo = accountNo;
		this.accountType = accountType;
		this.balance = balance;
	}
	public UserAccount(String firstName, String lastName, String userName, int accountNo, String accountType,
			int balance) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.accountNo = accountNo;
		this.accountType = accountType;
		this.balance = balance;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
}
