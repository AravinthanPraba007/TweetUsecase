package com.maven.tweetapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.maven.tweetapp.App;
import com.maven.tweetapp.model.SampleTweet;

public class TweetDao {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/tweetapp?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";
        
        private static final String INSERT_TWEET_SQL = "INSERT INTO tweet" + "  (tweet, user_email) VALUES" +" (?, ?);";
        private static final String SHOW_TWEETS_SQL = "select * from tweet";
        private static final String SHOW_MY_TWEETS_SQL = "select * from tweet where user_email = ?";
        
        protected Connection getConnection() {
            Connection connection = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return connection;
        }
        
        private void printSQLException(SQLException ex) {
            for (Throwable e: ex) {
                if (e instanceof SQLException) {
                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                    System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                    System.err.println("Message: " + e.getMessage());
                    Throwable t = ex.getCause();
                    while (t != null) {
                        ////System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
        
	public boolean postTweet(SampleTweet tweet) {
		////System.out.println(INSERT_TWEET_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TWEET_SQL)) {
//        	firstName, lastName, email, password, dob, gender
        	preparedStatement.setString(1, tweet.getTweetContent());
            preparedStatement.setString(2, tweet.getTweetAuthor());
            ////System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            }
     catch (SQLException e) {
            printSQLException(e);
        }
		return true;
		}
		public List<SampleTweet> viewMyTweet() {
			////System.out.println(SHOW_MY_TWEETS_SQL);
            List<SampleTweet> tweets = new ArrayList<SampleTweet>();
            // try-with-resource statement will auto close the connection.
            try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SHOW_MY_TWEETS_SQL)) {
            	preparedStatement.setString(1, App.currentUser);
            	////System.out.println(preparedStatement);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String tweetContent = resultSet.getString("tweet");
                    String tweetAuthor = resultSet.getString("user_email");
                    tweets.add(new SampleTweet(id, tweetContent, tweetAuthor));
                }
                }
         catch (SQLException e) {
                printSQLException(e);
            }
			return tweets;
			
		}
		public List<SampleTweet> viewAllTweet() {
			 ////System.out.println(SHOW_TWEETS_SQL);
	            List<SampleTweet> tweets = new ArrayList<SampleTweet>();
	            // try-with-resource statement will auto close the connection.
	            try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SHOW_TWEETS_SQL)) {
	                ////System.out.println(preparedStatement);
	                ResultSet resultSet = preparedStatement.executeQuery();
	                while (resultSet.next()) {
	                    int id = resultSet.getInt("id");
	                    String tweetContent = resultSet.getString("tweet");
	                    String tweetAuthor = resultSet.getString("user_email");
	                    tweets.add(new SampleTweet(id, tweetContent, tweetAuthor));
	                }
	                }
	         catch (SQLException e) {
	                printSQLException(e);
	            }
				return tweets;
		

		}
}
