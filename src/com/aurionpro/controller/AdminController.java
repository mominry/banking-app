package com.aurionpro.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

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
import com.aurionpro.model.UserAccount;
import com.aurionpro.util.BankDbUtil;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
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
	public AdminController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter("action");
		System.out.println(command);
		if (command == null) {
			command = "login";
		}

		switch (command) {

		case "add":
			try {
				addNewUser(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		case "load-update":
			try {
				loadUser(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;

		case "update":
			try {
				updateUser(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;

		case "delete":
			try {
				deleteUser(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
		case "search":
			System.out.println(" in search block");
			try {
				listUsersAll(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		case "add-account":
			addNewAccount(request,response);
		}

	}

	private void addNewAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int userId=Integer.parseInt(request.getParameter("user-id"));
		int balance=Integer.parseInt(request.getParameter("balance"));
		String accountType=request.getParameter("account-type");
		
		Account accountObj = new Account(accountType, userId, balance);
		try {
			dbUtil.addNewAccount(accountObj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 response.sendRedirect(request.getContextPath()+"/admin.jsp");
	}


	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("user_id"));
		String firstName = request.getParameter("first-name");
		String lastName = request.getParameter("last-name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
			User tempStudent = new User(id, firstName, lastName, username, password, 0);
			dbUtil.updateUser(tempStudent);
			System.out.println(tempStudent);
		
		response.sendRedirect(request.getContextPath() + "/admin.jsp");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		dbUtil.deleteUserById(id);
		response.sendRedirect(request.getContextPath() + "/admin.jsp");
	}

	private void loadUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		  int id = Integer.parseInt(request.getParameter("id"));
		  String firstName = request.getParameter("first-name");
		  String lastName = request.getParameter("last-name");
		  String username = request.getParameter("username");
		  String password = request.getParameter("password");
		  String accountType = request.getParameter("account-type");
		  User user = dbUtil.getUserById(id);
		  request.setAttribute("theUser", user);
		  RequestDispatcher dispatcher = request.getRequestDispatcher("/update-user.jsp");
		  dispatcher.forward(request, response);
		 }


		 private void addNewUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		  String firstName = request.getParameter("first-name");
		  String lastName = request.getParameter("last-name");
		  String username = request.getParameter("username");
		  String password = request.getParameter("password");
		  String accountType = request.getParameter("account-type");
		  
		  User tempUser = new User(firstName, lastName, username, accountType, 0);
		  dbUtil.addNewUser(tempUser);
		  //listStudents(request,response);
		  response.sendRedirect(request.getContextPath()+"/admin.jsp");
		 }

		 private void listAllTransactions(HttpServletRequest request, HttpServletResponse response) {
		  try {
		   List<Transaction> transactionList = dbUtil.getAllTransactions();
		   request.getSession().setAttribute("transactionsList", transactionList);
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }

		 }

		 private void listUsersAll(HttpServletRequest request, HttpServletResponse response)
		   throws SQLException, IOException, ServletException {
		  List<UserAccount> userList = dbUtil.getAllUsersAccount();
		  System.out.println(userList);
		  Enumeration<String> attributeNames = request.getAttributeNames();
			while (attributeNames.hasMoreElements()) {
				System.out.println("attribute names  :"+attributeNames.nextElement());
				
			}
		  String inputType  = request.getParameter("field");
		  String value=request.getParameter("search");
		  
		   if (inputType!=null&&value!=null) {
		      if(inputType.equals("firstName")) {
		       userList=userList.stream().filter((studentObj)->studentObj.getFirstName().equals(value)).collect(Collectors.toList());
		       System.out.println(userList);
		       }
		       
		       if (inputType.equalsIgnoreCase("lastName")) {
		        userList=userList.stream().filter((studentObj)->studentObj.getLastName().contains(value)).collect(Collectors.toList());
		       }
		       
		       if (inputType.equalsIgnoreCase("username")) {
		        userList=userList.stream().filter((studentObj)->studentObj.getUserName().contains(value)).collect(Collectors.toList());
		       }
		       if (inputType.equals("id")) {
		        int id=Integer.parseInt(value);
		        userList=userList.stream().filter((studentObj)->studentObj.getUserId()==id).collect(Collectors.toList());
		       }
		       if (inputType.equals("accountNo")) {
		         int accountNo=Integer.parseInt(value);
		         userList=userList.stream().filter((studentObj)->studentObj.getAccountNo()==accountNo).collect(Collectors.toList());
		        }
		     }
		  
		  request.getSession().setAttribute("usersList",userList );
		   RequestDispatcher dispatcher = request.getRequestDispatcher("/list-users.jsp");
		   dispatcher.forward(request, response);
		  //return userList;

		//  response.sendRedirect(request.getContextPath()+"/LoginController");
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
