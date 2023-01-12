package test;

import entity.UserEntity;
import utils.DataSourceUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * test encapsulate method for Druid Database
 */
public class Test02 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DataSourceUtils.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("select * from mayikt_users where id=?");
        // set parameters
        preparedStatement.setLong(1,5);
        //execute the query
        ResultSet resultSet = preparedStatement.executeQuery();
        if(!resultSet.next()){
            return;
            // clean up the result
        }

        Long dbId = resultSet.getLong("id");
        String dbPhone = resultSet.getString("phone");
        String dbPwd = resultSet.getString("pwd");
        UserEntity userEntity = new UserEntity(dbId, dbPhone, dbPwd);
        System.out.println(userEntity.toString());
        DataSourceUtils.close(connection,preparedStatement,resultSet);
    }
}
