package com.aurionpro.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.aurionpro.util.BankDbUtil;

/**
 * Servlet implementation class TransactionController
 */
@WebServlet("/TransactionController")
public class TransactionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private DataSource dataSource;

	    @Override
	    public void init() throws ServletException {
	        // Get the DataSource object from the JNDI registry
	        dataSource = (DataSource) getServletContext().getAttribute("jdbc/bank-source");
	    }

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	        int userBalance = 0;
	        HttpSession session = req.getSession();
	        int id = (int) session.getAttribute("id");
	        BankDbUtil dbUtil = new BankDbUtil(dataSource);
	        Connection conn=null;
	        
	        try {
	        	
	            conn = dbUtil.getConnectionToDb();
	            PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM account WHERE user_id = ?");
	            stmt.setInt(1, id);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                userBalance = rs.getInt(1);
	            }
	            stmt.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        String senderUsername = req.getParameter("senderUsername");
	        int transferAmount = Integer.parseInt(req.getParameter("transferAmount"));

	        if (userBalance < transferAmount) {
	            // Set the error message and forward the request to the JSP
	            req.setAttribute("errorMessage", "Insufficient balance");
	            req.getRequestDispatcher("/fund-transfer.jsp").forward(req, resp);
	            return;
	        }

	        try {
//	            Connection conn = dataSource.getConnection();
	            PreparedStatement stmt = conn.prepareStatement("UPDATE account SET balance = balance - ? WHERE account_no = ?");
	            stmt.setInt(1, transferAmount);
	            stmt.setInt(2, Integer.parseInt(req.getParameter("userBalance")));
	            stmt.executeUpdate();

	            stmt = conn.prepareStatement("UPDATE account SET balance = balance + ? WHERE username = ?");
	            stmt.setInt(1, transferAmount);
	            stmt.setString(2, senderUsername);
	            stmt.executeUpdate();

	            stmt.close();
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

//	        req.setAttribute("successMessage", "Fund transfer successful");
//	        req.getRequestDispatcher("/fund-transfer.jsp").forward(req, resp);
	    }
	}

