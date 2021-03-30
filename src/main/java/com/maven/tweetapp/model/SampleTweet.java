package com.maven.tweetapp.model;

public class SampleTweet {

	public int id;
	public String tweetContent;
	public String tweetAuthor;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTweetContent() {
		return tweetContent;
	}
	public void setTweetContent(String tweetContent) {
		this.tweetContent = tweetContent;
	}
	public String getTweetAuthor() {
		return tweetAuthor;
	}
	public void setTweetAuthor(String tweetAuthor) {
		this.tweetAuthor = tweetAuthor;
	}
	public SampleTweet(int id, String tweetContent, String tweetAuthor) {
		super();
		this.id = id;
		this.tweetContent = tweetContent;
		this.tweetAuthor = tweetAuthor;
	}
	public SampleTweet() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "[tweetContent=" + tweetContent + ", tweetAuthor=" + tweetAuthor + "]";
	}
	
	
}
