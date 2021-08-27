package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.exception.UserNotApprovedException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.DBUtil;
import org.apache.log4j.Logger;

/**
 *
 * @author JEDI-07
 * Class to implement User Dao Operations
 */
public class UserDaoOperations implements UserDaoInterface{
    private static UserDaoOperations instance=null;
    private static Logger logger = Logger.getLogger(UserDaoOperations.class);

    /**
     * Method to make UserDaoOperation Singleton
     * @return
     */
    public static UserDaoOperations getInstance()
    {
        if(instance==null)
        {
            instance=new UserDaoOperations();
        }
        return instance;
    }
    /**
     * Method to verify credentials of Users from DataBase
     * @param userId
     * @param password
     * @return Verify credentials operation status
     * @throws UserNotFoundException
     */
    @Override
    public boolean verifyCredentials(String userId, String password)  throws UserNotFoundException {
        Connection connection = DBUtil.getConnection();
        try
        {
            //open db connection
            PreparedStatement preparedStatement=connection.prepareStatement(SQLQueriesConstants.VERIFY_CREDENTIALS);
            preparedStatement.setString(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next())
                throw new UserNotFoundException(userId);
            else if(password.equals(resultSet.getString("password")))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(SQLException ex)
        {
            logger.error("Something went wrong, please try again! "+ ex.getMessage());
        }

        finally
        {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;

    }

    @Override
    public boolean verifyApproved(String userId) throws UserNotApprovedException {
        Connection connection = DBUtil.getConnection();
        try
        {
            //open db connection
            PreparedStatement preparedStatement=connection.prepareStatement(SQLQueriesConstants.VERIFY_APPROVAL);
            preparedStatement.setString(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Boolean isApproved;
            if(resultSet.next())
            {
                isApproved= resultSet.getBoolean("isApproved");
            }
            else
            {
                isApproved=false;
            }
            if(isApproved == true){
                return true;
            }
            else {
                throw new UserNotApprovedException(userId);
            }
        }
        catch(SQLException ex)
        {
            logger.error("Something went wrong, please try again! "+ ex.getMessage());
        }
        finally
        {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Method to get Role of User from DataBase
     * @param userId
     * @return Role
     */
    @Override
    public String getRole(String userId) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_ROLE);
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
    /**
     * Method to update password of user in DataBase
     * @param userID
     * @return Update Password operation Status
     */
    @Override
    public boolean updatePassword(String userID, String newPassword) {
        Connection connection= DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.UPDATE_PASSWORD);

            statement.setString(1, newPassword);
            statement.setString(2, userID);

            int row = statement.executeUpdate();

            if(row==1)
                return true;
            else
                return false;
        }
        catch(SQLException e)
        {
            logger.error(e.getMessage());
        }
        finally
        {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }
}
