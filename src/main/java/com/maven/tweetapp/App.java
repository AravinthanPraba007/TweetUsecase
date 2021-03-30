package com.maven.tweetapp;

import java.sql.SQLException;
import java.util.Scanner;

import com.maven.tweetapp.dao.UserDao;
import com.maven.tweetapp.service.TweetService;
import com.maven.tweetapp.service.UserService;

/**
 * Hello world!
 *
 */
public class App 
{
	public static boolean islogged = false;
	public static String currentUser;
    public static void main( String[] args )
    {
    	App tweetApp = new App();
    	UserDao userDaoo = new UserDao();
    	try {
			userDaoo.login("aravinthan@gmail.com", "password");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println( "Welcome to Tweet Application" );
        
        while(true) {
        System.out.println("*******************************");
        System.out.println( "Enter your choice" );
        System.out.println("-------------------------------");
        if(islogged) {
        	tweetApp.loggedUserMenu();
        }
        else {
        	tweetApp.notLoggedUserMenu();
        }
        }
    }
    
    public void notLoggedUserMenu() {
    	UserService userService = new UserService();
    	int choice;
    	System.out.println("Non-logged in user Menu :");
    	System.out.println( "Enter 1 for Register" );
    	System.out.println( "Enter 2 for Login" );
    	System.out.println( "Enter 3 for Forget password" );
    	Scanner userInput = new Scanner(System.in);
    	choice = userInput.nextInt();
    	switch (choice) {
		case 1:{
			userService.register();
			break;
		}
		case 2:{
			userService.login();
			break;
		}
		case 3:{
			userService.forgotPassword();
			break;
		}
		default:{
			System.out.println("Invalid Choice");
			break;
		}
		}
    }
    
    public void loggedUserMenu() {
    	UserService userService = new UserService();
    	TweetService tweetService = new TweetService();
    	int choice;
    	System.out.println("Logged in user Menu :");
    	System.out.println( "Enter 1 for Post a tweet" );
    	System.out.println( "Enter 2 for View my Tweets" );
    	System.out.println( "Enter 3 for View all Tweets" );
    	System.out.println( "Enter 4 for View all users" );
    	System.out.println( "Enter 5 for Reset Password" );
    	System.out.println( "Enter 6 for Logout" );
    	Scanner userInput = new Scanner(System.in);
    	choice = userInput.nextInt();
    	switch (choice) {
		case 1:{
			tweetService.postTweet();
			break;
		}
		case 2:{
			tweetService.viewMyTweet();
			break;
		}
		case 3:{
			tweetService.viewAllTweet();
			break;
		}
		case 4:{
			userService.viewUsers();
			break;
		}
		case 5:{
			userService.resetPassword();
			break;
		}
		case 6:{
			System.out.println("User logout sucessfully");
			App.islogged=false;
			break;
		}
		default:{
			System.out.println("Invalid Choice");
			break;
		}
		}
    }
}
