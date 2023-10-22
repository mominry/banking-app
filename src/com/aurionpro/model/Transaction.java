package com.aurionpro.model;

import java.sql.Date;

public class Transaction {
	private int transactionId;
	private int accountNo;
	private String transactionType;
	private Date time;
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
	
	
	
}
