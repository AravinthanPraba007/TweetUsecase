package com.maven.tweetapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.maven.tweetapp.model.SampleUser;

public class UserDao {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/tweetapp?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";
        
        private static final String LOGIN_USER_SQL = "select * from users where email = ? and password = ?";
        private static final String INSERT_USER_SQL = "INSERT INTO users (firstName, lastName, email, password, dob, gender) values" +
                " (?, ?, ?, ?, ?, ?);";
        private static final String RESET_PASSWORD_SQL = "select * from users where email = ? and password = ?";
        private static final String FORGOT_PASSWORD_SQL = "select * from users where email = ? and dob = ?";
        private static final String UPDATE_PASSWORD_SQL = "update users set password = ? where email = ?;";
        private static final String SHOW_USERS_SQL = "select firstName from users";
        
        
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
                        //System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
        
        public boolean login(String email, String password) throws SQLException {
            //System.out.println(LOGIN_USER_SQL);
            // try-with-resource statement will auto close the connection.
            try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_USER_SQL)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                //System.out.println(preparedStatement);
                ResultSet resultSet = preparedStatement.executeQuery();
                //System.out.println(resultSet);
                while(resultSet.next()) {
                	String emailString = resultSet.getString("email");
                	String passwordString = resultSet.getString("password");
                	if(emailString.equals(email) && passwordString.equals(password))
                		return true;
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
			return false;
        }
        
        public boolean register(SampleUser user) {
            //System.out.println(INSERT_USER_SQL);
            // try-with-resource statement will auto close the connection.
            try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
//            	firstName, lastName, email, password, dob, gender
            	preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.setString(5, user.getDob().toString());
                preparedStatement.setString(6, user.getGender());
                //System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
                }
         catch (SQLException e) {
                printSQLException(e);
            }
			return true;
        }
        
        public boolean validateResetPassord(String email,String oldPassword) {
            //System.out.println(RESET_PASSWORD_SQL);
            // try-with-resource statement will auto close the connection.
            try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(RESET_PASSWORD_SQL)) {
            	preparedStatement.setString(1, email);
                preparedStatement.setString(2, oldPassword);
                //System.out.println(preparedStatement);
                ResultSet resultSet = preparedStatement.executeQuery();
                //System.out.println(resultSet);
                while(resultSet.next()) {
                	String emailString = resultSet.getString("email");
                	String passwordString = resultSet.getString("password");
                	if(emailString.equals(email) && passwordString.equals(oldPassword))
                		return true;
                }
                }
         catch (SQLException e) {
                printSQLException(e);
            }
			return false;
        }
        
        public boolean validateForgetPassword(String email, Date dob) {
            //System.out.println(FORGOT_PASSWORD_SQL);
            // try-with-resource statement will auto close the connection.
            try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(FORGOT_PASSWORD_SQL)) {
            	preparedStatement.setString(1, email);
                preparedStatement.setString(2, dob.toString());
                //System.out.println(preparedStatement);
                ResultSet resultSet = preparedStatement.executeQuery();
                //System.out.println(resultSet);
                while(resultSet.next()) {
                	String emailString = resultSet.getString("email");
                	String passwordString = resultSet.getString("dob");
                	if(emailString.equals(email) && passwordString.equals(dob.toString()))
                		return true;
                }
                }
         catch (SQLException e) {
                printSQLException(e);
            }
			return false;
        }
        
        public boolean updatePassword(String email, String newPassword) {
            //System.out.println(UPDATE_PASSWORD_SQL);
            // try-with-resource statement will auto close the connection.
            try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD_SQL)) {
            	preparedStatement.setString(1, newPassword);
                preparedStatement.setString(2, email);
                //System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
                }
         catch (SQLException e) {
                printSQLException(e);
            }
			return true;
        }
        
        public List<String> getAllUser() {
            //System.out.println(SHOW_USERS_SQL);
            List<String> users = new ArrayList<String>();
            // try-with-resource statement will auto close the connection.
            try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SHOW_USERS_SQL)) {
                //System.out.println(preparedStatement);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("firstName");
                    users.add(name);
                }
                }
         catch (SQLException e) {
                printSQLException(e);
            }
			return users;
        }

}
