package com.aurionpro.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.aurionpro.model.Account;
import com.aurionpro.model.Transaction;
import com.aurionpro.model.User;
import com.aurionpro.util.BankDbUtil;

/**
 * Servlet implementation class TransactionController
 */
@WebServlet("/TransactionController")
public class TransactionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private DataSource dataSource;
	 private BankDbUtil dbUtil;
	    @Override
	    public void init() throws ServletException {
	        
	        dataSource = (DataSource) getServletContext().getAttribute("jdbc/bank-source");
	        dbUtil = new BankDbUtil(dataSource);
	    }

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	    	String senderUserName=req.getParameter("senderUsername");
	        int amount = Integer.parseInt(req.getParameter("transferAmount"));
	        HttpSession session = req.getSession();
//	        int id = (int) session.getAttribute("id");
			Account accountObj = (Account) session.getAttribute("account");
	        int userBalance	=accountObj.getBalance();
	        BankDbUtil dbUtil = new BankDbUtil(dataSource);
	        Connection conn=null;
	        int senderUserId=-5;
	        int senderAccountNo=-5;
	        
	        try {
	        	
	            conn = dbUtil.getConnectionToDb();
	            PreparedStatement stmt = conn.prepareStatement("SELECT user_id FROM bankdb.user WHERE username = ?");
//	            stmt.setInt(1, id);
	            stmt.setString(1, senderUserName);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	            	senderUserId = rs.getInt(1);
	            }
	            stmt.close();
	            
	            PreparedStatement stmt2 = conn.prepareStatement("SELECT account_no FROM bankdb.account WHERE user_id = ?");
//	            stmt.setInt(1, id);
	            stmt2.setInt(1, senderUserId);
	            ResultSet rs2 = stmt2.executeQuery();
	            if (rs2.next()) {
	            	senderAccountNo = rs2.getInt(1);
	            	System.out.println("senderAccountNo"+senderAccountNo);
	            }
	            
	            stmt.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

//	        String senderUsername = req.getParameter("senderUsername");
//	        int transferAmount = Integer.parseInt(req.getParameter("transferAmount"));

	        if (userBalance < amount) {
	            
	            
	            LoginController loginControllerObj = new LoginController();
	            loginControllerObj.cleanSessionAndLogoutUser(req, resp);
	        }

	        try {
	        	conn = dbUtil.getConnectionToDb();
//	            Connection conn = dataSource.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("UPDATE account SET balance = balance - ? WHERE account_no = ?");
	            stmt.setInt(1, amount);
	            stmt.setInt(2, accountObj.getAccountNo());
	            stmt.executeUpdate();
	            
//	            stmt = conn.prepareStatement("insert into transaction(account_no,transaction_type)"+"values(?,?)");
//	            stmt.setInt(1, accountObj.getAccountNo());
//	            stmt.setString(2, "Fund Transfer Sent");
//	            stmt.executeUpdate();
//	            
//	            stmt = conn.prepareStatement("insert into transaction(account_no,transaction_type)"+"values(?,?)");
//	            stmt.setInt(1, senderAccountNo);
//	            stmt.setString(2, "Fund Transfer Recieved");
//	            stmt.executeUpdate();
	            
	            stmt = conn.prepareStatement("insert into transaction(account_no,transaction_type,recieved_account_no,amount)"+"values(?,?,?,?)");
	            stmt.setInt(1, accountObj.getAccountNo());
	            stmt.setString(2, "Fund Transfer Sent");
	            stmt.setInt(3, senderAccountNo);
	            stmt.setInt(4, amount);
	            
	            stmt.executeUpdate();
	            
	            
	            
	            stmt = conn.prepareStatement("UPDATE account SET balance = balance + ? WHERE user_id = ?");
	            stmt.setInt(1, amount);
	            stmt.setInt(2, senderAccountNo);
	            stmt.executeUpdate();

	            stmt.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

//	        LoginController loginController = new LoginController();
	        updateTransactions(req,resp);
//	        resp.sendRedirect("user.jsp");
	    }
	    
	    public void updateTransactions(HttpServletRequest request, HttpServletResponse response) {
			HttpSession session = request.getSession();
			User userObj=(User) session.getAttribute("userObj");
			List<Transaction> particularUserTransactionList = getTransactionByAccountId(request,response,userObj);
			session.setAttribute("transactionList", particularUserTransactionList);
			try {
				response.sendRedirect("user.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    
	    public List<Transaction> getTransactionByAccountId(HttpServletRequest request, HttpServletResponse response, User userObj) {
			
	    	
	    	Account accountObj=dbUtil.getAccountObject(userObj);
			List<Transaction> particularUserTransactionList=null;
			try {
				particularUserTransactionList=dbUtil.getTransactionByAccountId(accountObj);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(particularUserTransactionList);
			request.getSession().setAttribute("userTransactionList",particularUserTransactionList );
			return particularUserTransactionList;
		}
	}

