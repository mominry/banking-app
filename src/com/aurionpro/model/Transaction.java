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
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", accountNo=" + accountNo + ", transactionType="
				+ transactionType + ", time=" + time + "]";
	}
	
	
	
}
