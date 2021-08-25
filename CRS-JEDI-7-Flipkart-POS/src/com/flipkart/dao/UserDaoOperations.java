package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.utils.DBUtil;
public class UserDaoOperations implements UserDaoInterface{
    private static UserDaoOperations instance=null;
    public static UserDaoOperations getInstance()
    {
        if(instance==null)
        {
            instance=new UserDaoOperations();
        }
        return instance;
    }
    @Override
    public boolean verifyCredentials(String userId, String password) {
        Connection connection = DBUtil.getConnection();
        try
        {
            //open db connection
            PreparedStatement preparedStatement=connection.prepareStatement(SQLQueriesConstants.VERIFY_CREDENTIALS);
            preparedStatement.setString(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                if (password.equals(resultSet.getString("password"))) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        catch(SQLException ex)
        {
            System.out.println("Something went wrong, please try again! "+ ex.getMessage());
        }

        return false;
    }


    @Override
    public String getRole(String userId) {
        Connection connection = DBUtil.getConnection();
        try {
            System.out.println(userId);
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_ROLE);
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

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
            System.out.println(e.getMessage());
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
