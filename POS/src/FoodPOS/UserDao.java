package FoodPOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	    private Connection connection;
	
	    public UserDao(Connection connection) {
	        this.connection = connection;
	    }

    public boolean isUsernameExists(String username) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM userinfos WHERE username = ?");
            statement.setString(1, username);
            resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
    
	    public boolean isIdExists(String id) {
	    	   PreparedStatement statement = null;
	           ResultSet resultSet = null;
	
	           try {
	               statement = connection.prepareStatement("SELECT * FROM producttbl WHERE `Product ID` = ?");
	               statement.setString(1, id);
	               resultSet = statement.executeQuery();
	
	               return resultSet.next();
	           } catch (SQLException e) {
	               e.printStackTrace();
	           } finally {
	               try {
	                   if (resultSet != null) {
	                       resultSet.close();
	                   }
	
	                   if (statement != null) {
	                       statement.close();
	                   }
	               } catch (SQLException e) {
	                   e.printStackTrace();
	               }
	           }
	
	           return false;
	
	    	
	    }
    
    
    public boolean isIdExistsinQue(String id ) {
 	   PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM queingtbl WHERE `Product ID` = ?");
            statement.setString(1, id);
            resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;

 	
 } 
    public boolean isIdExistsinCus(String id) {
	 
    	PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM customertbl WHERE `Customer ID` = ?");
            statement.setString(1, id);
            resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
	 

 }
 
    
    
    
    
    
    
    
    
}
