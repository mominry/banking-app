package com.aurionpro.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Transaction {
	public int transactionId;
	public int accountNo;
	public String transactionType;
	public String time;
	public int recievedAccountNo;
	public int amount;
	public Transaction(int transactionId, int accountNo, String transactionType, Date time) {
		super();
		this.transactionId = transactionId;
		this.accountNo = accountNo;
		this.transactionType = transactionType;
		this.time = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(time);
	}
	public Transaction(int accountNo, String transactionType, Date time) {
		super();
		this.accountNo = accountNo;
		this.transactionType = transactionType;
		this.time = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(time);
	}
	
	
	public Transaction(int accountNo, String transactionType) {
		super();
		this.accountNo = accountNo;
		this.transactionType = transactionType;
	}
	
	
	
	
	
	public Transaction(int transactionId, int accountNo, String transactionType, Date time, int recievedAccountNo,
			int amount) {
		super();
		this.transactionId = transactionId;
		this.accountNo = accountNo;
		this.transactionType = transactionType;
		this.time = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(time);
		this.recievedAccountNo = recievedAccountNo;
		this.amount = amount;
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
	public String getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(time);
	}
	public int getRecievedAccountNo() {
		return recievedAccountNo;
	}
	public void setRecievedAccountNo(int recievedAccountNo) {
		this.recievedAccountNo = recievedAccountNo;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", accountNo=" + accountNo + ", transactionType="
				+ transactionType + ", time=" + time + ", recievedAccountNo=" + recievedAccountNo + ", amount=" + amount
				+ "]";
	}
	
	
	
	
}
