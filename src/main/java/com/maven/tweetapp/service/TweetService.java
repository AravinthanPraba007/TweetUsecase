package com.maven.tweetapp.service;

import java.util.List;
import java.util.Scanner;

import com.maven.tweetapp.App;
import com.maven.tweetapp.dao.TweetDao;
import com.maven.tweetapp.model.SampleTweet;

public class TweetService {
	
	public void viewAllTweet() {
		System.out.println("View All Tweets Service running");
		TweetDao tweetDao = new TweetDao();
		List<SampleTweet>tweets=tweetDao.viewAllTweet();
		System.out.println("All Tweets: "+tweets);
	}
	
	public void viewMyTweet() {
		System.out.println("My Tweets Service running");
		TweetDao tweetDao = new TweetDao();
		List<SampleTweet>tweets=tweetDao.viewMyTweet();
		System.out.println("My Tweets: "+tweets);
	}
	
	public void postTweet() {
		Scanner userInput = new Scanner(System.in);
		TweetDao tweetDao = new TweetDao();
		System.out.println("Post tweet Service running");
		System.out.println("Enter your tweet");
		String tweetContent = userInput.nextLine();
		SampleTweet tweet = new SampleTweet(0, tweetContent, App.currentUser);
		tweetDao.postTweet(tweet);
		
	}

}
