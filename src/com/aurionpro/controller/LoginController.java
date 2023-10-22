package com.aurionpro.controller;

import java.io.IOException;
import java.sql.SQLException;
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
	@Resource(name="jdbc/bank-source")
	public DataSource dataSource;
	
	
	
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		dbUtil= new BankDbUtil(dataSource);
		
	}

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		String userType = request.getParameter("user_type");
//		String userName = request.getParameter("username");
//		String userPassword = request.getParameter("password");
//		System.out.println("userType "+userType+"  userName"+userName+"   userPassword"+userPassword);
		
		String command = request.getParameter("action");
		System.out.println(command);
		if(command==null) {
			command="login";
		}
		
		switch(command) {
		case "login":
			try {
				directUsersAccordingToType(request, response);
			} catch (IOException | ServletException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "admin":
			try {
				listUsersAll(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		case "transactions":
			listAllTransactions(request,response);
		
		}	
		
		
		
		
	}

	private void listAllTransactions(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Transaction>transactionList=dbUtil.getAllTransactions();
			request.setAttribute("transactions", transactionList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


	private void listUsersAll(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		List<User> listUsers= dbUtil.getAllUsers();
		
		request.setAttribute("usersList", listUsers);
		
//		response.sendRedirect(request.getContextPath()+"/LoginController");
	}


	private void directUsersAccordingToType(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		User UserObj=null;
		String userType =request.getParameter("user_type");
		String userName = request.getParameter("username");
		String userPassword = request.getParameter("password");
		Boolean isValid=dbUtil.verifyUser(userName,userPassword);
		System.out.println("user name "+userName);
		System.out.println(" is user valid :"+isValid);
		
		if (isValid!=null) {
			
			if (isValid==true) {
				UserObj = dbUtil.getUserByUserName(userName);
				HttpSession session = request.getSession();
				session.setAttribute("userObj", UserObj);
				session.setAttribute("id", UserObj.getUserId());
				session.setAttribute("isAdmin", UserObj.getIsAdmin());
				if (UserObj.getIsAdmin()==0) {
					response.sendRedirect("");
				} else {
					response.sendRedirect("");
				}
				System.out.println("in set session block");
			}
		}
		
		
		
//		response.sendRedirect(request.getContextPath()+"/LoginController");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
		requestDispatcher.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
}
