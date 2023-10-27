package com.aurionpro.model;

public class Account {
	public int accountNo;
	public String accountType;
	public int userId;
	public int balance;
	public Account(int accountNo, String accountType, int userId, int balance) {
		super();
		this.accountNo = accountNo;
		this.accountType = accountType;
		this.userId = userId;
		this.balance = balance;
	}
	
	
	public Account(String accountType, int userId, int balance) {
		super();
		this.accountType = accountType;
		this.userId = userId;
		this.balance = balance;
	}


	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", accountType=" + accountType + ", userId=" + userId + ", balance="
				+ balance + "]";
	}
	
	
}
