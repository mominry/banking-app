package com.aurionpro.model;

import java.sql.Date;

public class Transaction {
	public int transactionId;
	public int accountNo;
	public String transactionType;
	public Date time;
	public Transaction(int transactionId, int accountNo, String transactionType, Date time) {
		super();
		this.transactionId = transactionId;
		this.accountNo = accountNo;
		this.transactionType = transactionType;
		this.time = time;
	}
	public Transaction(int accountNo, String transactionType, Date time) {
		super();
		this.accountNo = accountNo;
		this.transactionType = transactionType;
		this.time = time;
	}
	
	
	public Transaction(int accountNo, String transactionType) {
		super();
		this.accountNo = accountNo;
		this.transactionType = transactionType;
	}
	
	
	
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", accountNo=" + accountNo + ", transactionType="
				+ transactionType + ", time=" + time + "]";
	}
	
	
	
}
