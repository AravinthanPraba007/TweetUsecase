package com.maven.tweetapp.service;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.maven.tweetapp.App;
import com.maven.tweetapp.dao.UserDao;
import com.maven.tweetapp.model.SampleUser;

public class UserService {

	public void login() {
		UserDao userDao = new UserDao();
		Scanner userInput = new Scanner(System.in);
		System.out.println("Login Service running");
		
		System.out.println("Enter email");
		String email = userInput.nextLine();
		System.out.println("Enter password");
		
		String password = userInput.nextLine();
		boolean isUserInputValid = false;
		try {
			isUserInputValid = userDao.login(email, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isUserInputValid) {
			App.islogged=true;
			App.currentUser=email;
			System.out.println("Login sucessfull");
		}
		else {
			System.out.println("Login failed");
		}
	}
	
	public void register() {
		UserDao userDao = new UserDao();
		Scanner userInput = new Scanner(System.in);
		System.out.println("Register Service running");
		
		System.out.println("Enter First Name");
		String firstName = userInput.nextLine();
		System.out.println("Enter last name");
		String lastName = userInput.nextLine();
		System.out.println("Enter email");
		String email = userInput.nextLine();
		System.out.println("Enter password");
		String password = userInput.nextLine();
		Date dob = UserService.dateHelper();
		System.out.println("Enter Gender");
		String gender = userInput.nextLine();

		SampleUser userDetails = new SampleUser(1,firstName, lastName, gender, dob, email, password);
		boolean isUserRegistered = false;
		isUserRegistered = userDao.register(userDetails);
		if(isUserRegistered) {
			System.out.println("Registeration sucessfull");
		}
		else {
			System.out.println("Registeration failed");
		}
		
	}
	
	public void viewUsers() {
		System.out.println("View all Users Service running");
		UserDao userDao = new UserDao();
		List<String> users = new ArrayList<String>(); 
		users = userDao.getAllUser();
		System.out.println("Users list : "+users.toString());
	}
	
	public void resetPassword() {
		UserDao userDao = new UserDao();
		Scanner userInput = new Scanner(System.in);
		boolean isUserInputValid = false;
		System.out.println("Reset password Service running");
		
		System.out.println("Enter Your email");
		String email = userInput.nextLine();
		System.out.println("Enter your old password");
		String oldPassword = userInput.nextLine();
		
		isUserInputValid = userDao.validateResetPassord(email, oldPassword);
		if(isUserInputValid) {
			boolean isPasswordUpdated = false;
			
			System.out.println("Enter new password");
			String newPassword = userInput.nextLine();
			
			isPasswordUpdated = userDao.updatePassword(email,newPassword);
			if(isPasswordUpdated)
			System.out.println("Sucesssfully password reset done");
		}
		else
		System.out.println("Password reset process failed");	
	}
	
	public void forgotPassword() {
		UserDao userDao = new UserDao();
		Scanner userInput = new Scanner(System.in);
		boolean isUserInputValid = false;
		
		System.out.println("Forgot password Service running");
		System.out.println("Enter your email");
		String email = userInput.nextLine();
		
		Date dob = UserService.dateHelper();
		isUserInputValid = userDao.validateForgetPassword(email, dob);
		if(isUserInputValid) {
			boolean isPasswordUpdated = false;
			
			System.out.println("Enter new password");
			String newPassword = userInput.nextLine();
			
			isPasswordUpdated = userDao.updatePassword(email,newPassword);
			if(isPasswordUpdated)
			System.out.println("Sucesssfully new password registered");
		}
		else
		System.out.println("Password forgot process failed");	
	}
	
	public static Date dateHelper() {
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Enter your Date of birth");
		System.out.println("Enter date");
		int date = userInput.nextInt();
		userInput.nextLine();
		System.out.println("Enter month");
		int month = userInput.nextInt();
		userInput.nextLine();
		System.out.println("Enter Year");
		int year = userInput.nextInt();
		userInput.nextLine();
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = date+"/"+month+"/"+year;
		Date dob = new Date();
		try {
			dob = format.parse(formattedDate);
			System.out.println("Date of birth : "+dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dob;
	}
}
