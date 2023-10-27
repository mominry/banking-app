package com.aurionpro.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BankDbUtil dbUtil;
	@Resource(name = "jdbc/bank-source")
	public DataSource dataSource;

	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		dbUtil = new BankDbUtil(dataSource);

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		String userType = request.getParameter("user_type");
//		String userName = request.getParameter("username");
//		String userPassword = request.getParameter("password");
//		System.out.println("userType "+userType+"  userName"+userName+"   userPassword"+userPassword);

		String command = request.getParameter("action");
		System.out.println(command);
		if (command == null) {
			command = "login";
		}

		switch (command) {
		case "login":
			try {
				directUsersAccordingToType(request, response);
			} catch (IOException | ServletException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "deposit":
			depositInUserAccount(request, response);
			break;

		case "withdraw":
			withdrawMoney(request, response);
			break;

		case "redirect":
			updateTransactions(request, response);
			break;

		case "logout":
			cleanSessionAndLogoutUser(request, response);
			break;

		case "userDetail":
			listUsers(request, response);
			break;
		case "update":
			try {
				updateUser(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;

		case "load-update":
			try {
				loadUser(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
		}

	}

	private void loadUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		int UserID = Integer.parseInt(request.getParameter("userId"));
		User user = dbUtil.getUserByTdForUpdate(UserID);
		System.out.println("Student ID: " + user.getUserId());
		System.out.println("First Name: " + user.getFirstName());
		System.out.println("Last Name: " + user.getLastName());
		System.out.println("First Name: " + user.getUserName());
		System.out.println("Last Name: " + user.getPassword());
		request.setAttribute("theUser", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/updateUser.jsp");
		dispatcher.forward(request, response);
		System.out.println("in load");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		User user = new User(userId, firstName, lastName, userName, password);

		System.out.println("in update");
		try {
			dbUtil.updateUserByUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/LoginController");
	}
	
	

	public void updateTransactions(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User userObj = (User) session.getAttribute("userObj");
		List<Transaction> particularUserTransactionList = getTransactionByAccountId(request, response, userObj);
		session.setAttribute("transactionList", particularUserTransactionList);
		try {
			response.sendRedirect("user.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void listUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User listUser = (User) session.getAttribute("userObj");
		if (listUser != null) {
			request.setAttribute("listUser", listUser);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("userdetails.jsp");
		dispatcher.forward(request, response);
	}

	public void cleanSessionAndLogoutUser(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("login.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void withdrawMoney(HttpServletRequest request, HttpServletResponse response) {
		int amount = Integer.parseInt(request.getParameter("amount"));
		HttpSession session = request.getSession();
		Account accountObj = (Account) session.getAttribute("account");

		if (accountObj.getBalance() >= amount) {
			dbUtil.withdrawMoney(accountObj, amount);
			dbUtil.addTransaction(accountObj, amount, "withdraw");
		}

		updateTransactions(request, response);
//			response.sendRedirect("login.jsp");
	}

	private void depositInUserAccount(HttpServletRequest request, HttpServletResponse response) {
		int amount = Integer.parseInt(request.getParameter("amount"));
		HttpSession session = request.getSession();
		Account accountObj = (Account) session.getAttribute("account");
		Transaction transaction = new Transaction(accountObj.accountNo, "deposit");
//		List<Transaction>transactionList=(List<Transaction>) request.getAttribute("transactionList");
//		transactionList.add(transaction);
//		request.setAttribute("transactionList", transactionList);
		dbUtil.addTransaction(accountObj, amount, "deposit");
		dbUtil.updateBalance(accountObj, amount);
		updateTransactions(request, response);
//			response.sendRedirect("user.jsp");
	}

	private void listAllTransactions(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Transaction> transactionList = dbUtil.getAllTransactions();
			request.setAttribute("transactions", transactionList);
			HttpSession session = request.getSession();
			session.setAttribute("transactions", transactionList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void listUsersAll(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		List<User> listUsers = dbUtil.getAllUsers();

		request.setAttribute("usersList", listUsers);
		HttpSession session = request.getSession();
		session.setAttribute("usersListSession", listUsers);
//		response.sendRedirect(request.getContextPath()+"/LoginController");
	}

	private void directUsersAccordingToType(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {

		User UserObj = null;
		Account accountObj = null;
		String userType = request.getParameter("user_type");
		String userName = request.getParameter("username");
		String userPassword = request.getParameter("password");
		Boolean isValid = dbUtil.verifyUser(userName, userPassword);
		System.out.println("user name " + userName);
		System.out.println(" is user valid :" + isValid);

		if (isValid != null) {

			if (isValid == true) {
				UserObj = dbUtil.getUserByUserName(userName);
				HttpSession session = request.getSession();
				session.setAttribute("userObj", UserObj);
				session.setAttribute("id", UserObj.getUserId());
				session.setAttribute("isAdmin", UserObj.getIsAdmin());
				if (UserObj.getIsAdmin() == 0) {
					accountObj = getAccountDetails(request, response, UserObj);
					session.setAttribute("account", accountObj);
					List<Transaction> particularUserTransactionList = getTransactionByAccountId(request, response,
							UserObj);
					session.setAttribute("transactionList", particularUserTransactionList);
					response.sendRedirect("user.jsp");
//					Enumeration<String> attributeNames = request.getAttributeNames();
//					while (attributeNames.hasMoreElements()) {
//						System.out.println("attribute names  :"+attributeNames.nextElement());
//						
//					}

//					RequestDispatcher rd = request.getRequestDispatcher("user.jsp"); 
//					rd.forward(request, response);

				} else {
					listAllTransactions(request, response);
					listUsersAll(request, response);
					List<String> testAttribute = new ArrayList<String>();
					testAttribute.add("hello");
					testAttribute.add("hi");
					request.setAttribute("testAttribute", testAttribute);
//					Enumeration<String> attributeNames = request.getAttributeNames();
//					while (attributeNames.hasMoreElements()) {
//						System.out.println("attribute names  :"+attributeNames.nextElement());
//						
//					}
					response.sendRedirect("admin.jsp");
				}
				System.out.println("in set session block");
			}
		}

		if (isValid == null) {
			// response.sendRedirect(request.getContextPath()+"/LoginController");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	public List<Transaction> getTransactionByAccountId(HttpServletRequest request, HttpServletResponse response,
			User userObj) {
		Account accountObj = dbUtil.getAccountObject(userObj);
		List<Transaction> particularUserTransactionList = null;
		try {
			particularUserTransactionList = dbUtil.getTransactionByAccountId(accountObj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(particularUserTransactionList);
		request.getSession().setAttribute("userTransactionList", particularUserTransactionList);
		return particularUserTransactionList;
	}

	private Account getAccountDetails(HttpServletRequest request, HttpServletResponse response, User userObj) {
		Account accountObj = dbUtil.getAccountObject(userObj);
		request.setAttribute("account", accountObj);

		return accountObj;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
