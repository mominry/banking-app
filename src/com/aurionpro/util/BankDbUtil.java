package com.aurionpro.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.aurionpro.model.Transaction;
import com.aurionpro.model.User;

public class BankDbUtil {
	private DataSource dataSource;
	
	public BankDbUtil(DataSource dataSource) {
		this.dataSource=dataSource;
		
	}

	public Boolean verifyUser(String userName, String userPassword) throws SQLException {
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet result=null;
		int userId;
		User userObj=null;
		int userType = 0;
		System.out.println("hello");
		try {
			conn=getConnectionToDb();
//			conn=dataSource.getConnection();
			System.out.println("in try block");
			String sql= "select * from bankdb.user where username=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, userName);
			result=stmt.executeQuery();
			String password=null;
//			if (!result.next()) {
//				System.out.println("result is null");
//				return false;
//			}
//			System.out.println("result.next() "+result.next());
			if (result.next()) {
				password = result.getString("password");
				userId=Integer.parseInt(result.getString("user_id"));
				userType=result.getInt("isAdmin");
//				System.out.println("inside result from next");
//				System.out.println("user password from db   :"+password);
			}
			
//			if (!userPassword.equals(password)) {
//				return false;
//			}
			
			System.out.println("userPassword :"+userPassword+"   password from db :"+password);
			if (userPassword!=null&&password!=null) {
				if (userPassword.equals(password)) {
					System.out.println("password is equal");
					return true;

				} 
				
			}
			
//			String sql="select * from user";
//			
//			Statement stmt1 = conn.createStatement();
//			
//			result = stmt.executeQuery(sql);
//			
//			while(result.next()) {
//				String password= result.getString("password");
//				System.out.println("password is :"+password);
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		
		
		return null;
		
	}

	public Connection getConnectionToDb() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb","root", "admin#123");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}

	public User getUserByUserName(String userName) throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet result=null;
		int userId;
		User userObj=null;
		int userType = 0;
		System.out.println("hello");
		try {
			conn=getConnectionToDb();
//			conn=dataSource.getConnection();
			System.out.println("in try block");
			String sql= "select * from bankdb.user where username=?";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, userName);
			result=stmt.executeQuery();
			String password=null;

			if (result.next()) {
				userId=Integer.parseInt(result.getString("user_id"));
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String UserNameFromDb=result.getString("username");
				password = result.getString("password");
				userType=result.getInt("isAdmin");
				
				userObj=new User(userId, firstName, lastName, UserNameFromDb, password, userType);
				
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return userObj;
	}

	public List<User> getAllUsers() throws SQLException {
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet result=null;
		int userId;
		User userObj=null;
		List<User> usersList=new ArrayList<User>();
		int userType = 0;
		try {
			conn=getConnectionToDb();
//			conn=dataSource.getConnection();
			System.out.println("in try block");
			String sql= "select * from bankdb.user";
			stmt=conn.prepareStatement(sql);
			
			result=stmt.executeQuery();
			

			while (result.next()) {
				userId=Integer.parseInt(result.getString("user_id"));
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String UserNameFromDb=result.getString("username");
//				String password = result.getString("password");
				userType=result.getInt("isAdmin");
				
				userObj= new User(userId, firstName, lastName, UserNameFromDb, userType);
				usersList.add(userObj);
				
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		
		return usersList;
	}

	public List<Transaction> getAllTransactions() throws SQLException {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet result=null;
		
		Transaction transactionObj=null;
		List<Transaction> transactionList=new ArrayList<Transaction>();
		int userType = 0;
		try {
			conn=getConnectionToDb();
//			conn=dataSource.getConnection();
			System.out.println("in try block");
			String sql= "select * from bankdb.transaction";
			stmt=conn.prepareStatement(sql);
			
			result=stmt.executeQuery();
			

			while (result.next()) {
				int transactionId=result.getInt(1);
				int accountId=result.getInt(2);
				String transactionType=result.getString(3);
				Date time = result.getDate(4);
				transactionObj=new Transaction(transactionId, accountId, transactionType, time);
				transactionList.add(transactionObj);
				
				
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return transactionList;
	}
	
	
}
