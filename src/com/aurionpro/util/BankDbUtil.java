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

import com.aurionpro.model.Account;
import com.aurionpro.model.Transaction;
import com.aurionpro.model.User;
import com.aurionpro.model.UserAccount;

public class BankDbUtil {
	private DataSource dataSource;

	public BankDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;

	}

	public Boolean verifyUser(String userName, String userPassword) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		int userId;
		User userObj = null;
		int userType = 0;
		System.out.println("hello");
		try {
			conn = getConnectionToDb();
//			conn=dataSource.getConnection();
			System.out.println("in try block");
			String sql = "select * from bankdb.user where username=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			result = stmt.executeQuery();
			String password = null;
//			if (!result.next()) {
//				System.out.println("result is null");
//				return false;
//			}
//			System.out.println("result.next() "+result.next());
			if (result.next()) {
				password = result.getString("password");
				userId = Integer.parseInt(result.getString("user_id"));
				userType = result.getInt("isAdmin");
//				System.out.println("inside result from next");
//				System.out.println("user password from db   :"+password);
			}

//			if (!userPassword.equals(password)) {
//				return false;
//			}

			System.out.println("userPassword :" + userPassword + "   password from db :" + password);
			if (userPassword != null && password != null) {
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
		} finally {
			conn.close();
		}

		return null;

	}

	public Connection getConnectionToDb() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb", "root", "admin#123");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;
	}

	public User getUserByUserName(String userName) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		int userId;
		User userObj = null;
		int userType = 0;
		System.out.println("hello");
		try {
			conn = getConnectionToDb();
//			conn=dataSource.getConnection();
			System.out.println("in try block");
			String sql = "select * from bankdb.user where username=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			result = stmt.executeQuery();
			String password = null;

			if (result.next()) {
				userId = Integer.parseInt(result.getString("user_id"));
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String UserNameFromDb = result.getString("username");
				password = result.getString("password");
				userType = result.getInt("isAdmin");

				userObj = new User(userId, firstName, lastName, UserNameFromDb, password, userType);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return userObj;
	}

	public List<User> getAllUsers() throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		int userId;
		User userObj = null;
		List<User> usersList = new ArrayList<User>();
		int userType = 0;
		try {
			conn = getConnectionToDb();
//			conn=dataSource.getConnection();
			System.out.println("in try block");
			String sql = "select * from bankdb.user";
			stmt = conn.prepareStatement(sql);

			result = stmt.executeQuery();

			while (result.next()) {
				userId = Integer.parseInt(result.getString("user_id"));
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String UserNameFromDb = result.getString("username");
//				String password = result.getString("password");
				userType = result.getInt("isAdmin");

				userObj = new User(userId, firstName, lastName, UserNameFromDb, userType);
				usersList.add(userObj);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return usersList;
	}

	public List<Transaction> getAllTransactions() throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		Transaction transactionObj = null;
		List<Transaction> transactionList = new ArrayList<Transaction>();
		int userType = 0;
		try {
			conn = getConnectionToDb();
//			conn=dataSource.getConnection();
			System.out.println("in try block");
			String sql = "select * from bankdb.transaction";
			stmt = conn.prepareStatement(sql);

			result = stmt.executeQuery();

			while (result.next()) {
				int transactionId = result.getInt(1);
				int accountId = result.getInt(2);
				String transactionType = result.getString(3);
				Date time = result.getDate(4);
				int recievedAccountNo = result.getInt(5);
				int amount = result.getInt(6);
				transactionObj = new Transaction(accountId, accountId, transactionType, time, recievedAccountNo,
						amount);
				transactionList.add(transactionObj);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return transactionList;
	}

	public Account getAccountObject(User userObj) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		Account accountObj = null;

		try {
			conn = getConnectionToDb();
			// conn=dataSource.getConnection();
			System.out.println("in try block");
			String sql = "select * from bankdb.account where user_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userObj.getUserId());
			result = stmt.executeQuery();

			if (result.next()) {
//				userId=Integer.parseInt(result.getString("user_id"));
//				
//				userType=result.getInt("isAdmin");

				int accountNo = result.getInt("account_no");
				String accountType = result.getString("account_type");
				int userId = result.getInt("user_id");
				int balance = result.getInt("balance");
				accountObj = new Account(accountNo, accountType, userId, balance);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountObj;
	}

	public List<Transaction> getTransactionByAccountId(Account accountObj) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		List<Transaction> particularUserTransactionList = new ArrayList<Transaction>();
		Transaction transactionObj = null;

		conn = getConnectionToDb();
		// conn=dataSource.getConnection();

		String sql = "select * from bankdb.transaction where account_no=? or recieved_account_no=?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, accountObj.getAccountNo());
		stmt.setInt(2, accountObj.getAccountNo());
		result = stmt.executeQuery();

		while (result.next()) {
			int transactionId = result.getInt(1);
			int accountId = result.getInt(2);
			String transactionType = result.getString(3);
			Date time = result.getDate(4);
			int recievedAccountNo = result.getInt(5);
			int amount = result.getInt(6);
			transactionObj = new Transaction(transactionId, accountId, transactionType, time, recievedAccountNo,
					amount);
			System.out.println(transactionObj);
			particularUserTransactionList.add(transactionObj);

		}

		return particularUserTransactionList;

	}

	public void addTransaction(Transaction transaction) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		int accountNo = transaction.accountNo;
		String transactionType = transaction.transactionType;

		conn = getConnectionToDb();
		// conn=dataSource.getConnection();

		try {
			String sql = "insert into bankdb.transaction (account_no,transaction_type) values(?,?)";
			stmt.setInt(1, accountNo);
			stmt.setString(2, transactionType);
			stmt = conn.prepareStatement(sql);
			stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateBalance(Account accountObj, int amount) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		try {
			conn = getConnectionToDb();
			stmt = conn.prepareStatement("UPDATE account SET balance = balance + ? WHERE account_no = ?");
			stmt.setInt(1, amount);
			stmt.setInt(2, accountObj.getAccountNo());
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void withdrawMoney(Account accountObj, int amount) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		try {
			conn = getConnectionToDb();
			stmt = conn.prepareStatement("UPDATE account SET balance = balance - ? WHERE account_no = ?");
			stmt.setInt(1, amount);
			stmt.setInt(2, accountObj.getAccountNo());
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addTransaction(Account accountObj, int amount, String transactionType) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

//		int accountNo = accountObj.accountNo;

		conn = getConnectionToDb();
		// conn=dataSource.getConnection();

		try {
			String sql = "insert into bankdb.transaction (account_no,transaction_type,amount)" + "values(?,?,?)";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, accountObj.getAccountNo());
			stmt.setString(2, transactionType);
			stmt.setInt(3,amount);

			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateUser(User tempUser) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			String sql = "update bankdb.user set first_name =?, last_name=?, username=?, password=? where user_id=?";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, tempUser.getFirstName());
			stmt.setString(2, tempUser.getLastName());
			stmt.setString(3, tempUser.getUserName());
			stmt.setString(4, tempUser.getPassword());
			stmt.setInt(5, tempUser.getUserId());
			stmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt);
		}
	}

	public void deleteUserById(int user_id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		User userObj = null;

		try {
			conn = dataSource.getConnection();
			String sql = "delete from bankdb.user where user_id=?";
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, user_id);
			stmt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt);
		}

	}

	public User getUserById(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		User userObj = null;

		try {
			conn = dataSource.getConnection();
			String sql = "select * from bankdb.user where user_id=?";
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, id);
			result = stmt.executeQuery();

			if (result.next()) {
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String username = result.getString("username");
				String password = result.getString("password");
				System.out.println(firstName + "updating....");

				userObj = new User(id, firstName, lastName, username, password, 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt);
		}
		return userObj;
	}

	public void addNewUser(User tempUser) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			String sql = "insert into bankdb.user(first_name,last_name,username,password,isAdmin) values(?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tempUser.getFirstName());
			stmt.setString(2, tempUser.getLastName());
			stmt.setString(3, tempUser.getUserName());
			stmt.setString(4, tempUser.getPassword());
			stmt.setInt(5, tempUser.getIsAdmin());
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt);
		}
	}

	public List<UserAccount> getAllUsersAccount() throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		int userId;
		UserAccount userObj = null;
		List<UserAccount> usersList = new ArrayList<>();
		int accountNo;
		try {
			conn = getConnectionToDb();
//			conn=dataSource.getConnection();
			System.out.println("in try block");
			String sql = "select user.user_id,user.first_name,user.last_name,user.username,account.account_no,account.account_type,account.balance from user left join account on user.user_id=account.user_id";
			stmt = conn.prepareStatement(sql);

			result = stmt.executeQuery();

			while (result.next()) {
				userId = Integer.parseInt(result.getString("user_id"));
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String UserNameFromDb = result.getString("username");
				String accountType = result.getString("account_type");
				accountNo = result.getInt("account_no");
				int balance = result.getInt("balance");

				userObj = new UserAccount(userId, firstName, lastName, UserNameFromDb, accountNo, accountType, balance);
				usersList.add(userObj);
//				usersList.add(firstName);
//				usersList.add(lastName);
//				usersList.add(UserNameFromDb);
//				usersList.add(accountNo);
//				usersList.add(accountType);
//				usersList.add(balance);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return usersList;
	}

	private void close(Connection conn, PreparedStatement stmt) throws SQLException {
		if (conn != null) {
			conn.close();
		}
		if (stmt != null) {
			conn.close();
		}

	}

	public void addNewAccount(Account accountObj) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();
			String sql = "insert into bankdb.account(account_type,user_id,balance) values(?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, accountObj.getAccountType());
			stmt.setInt(2, accountObj.getUserId());
			stmt.setInt(3, accountObj.getBalance());
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt);
		}
	}

	public void updateUserByUser(User user) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();// String sqlUpdate = "UPDATE candidates "
//                + "SET last_name = ? " //                + "WHERE id = ?";
			String sql = "UPDATE bankdb.user SET first_name=?, last_name=?, username=?, password=? WHERE user_id=?";
			System.out.println("SQL Query: " + sql);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getUserName());
			stmt.setString(4, user.getPassword());

			stmt.setInt(5, user.getUserId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt);
		}

	}

	public User getUserByTdForUpdate(int userID) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		User tempUser = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select * from bankdb.user where user_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userID);
			result = stmt.executeQuery();
			if (result.next()) {
				String firstName = result.getString("firstName");
				String lastName = result.getString("lastName");
				String userName = result.getString("userName");
				String password = result.getString("password");
				tempUser = new User(userID, firstName, lastName, userName, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt);
			
		}
		return tempUser;
	}
}
