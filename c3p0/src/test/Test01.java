
package test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import entity.UserEntity;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * testing c3p0 connection (default and assigned configName)
 */
public class Test01 {
    public static void main(String[] args) throws SQLException, PropertyVetoException {
        // create c3p0 connection pool
        // either default or set configName
        ComboPooledDataSource pool = new ComboPooledDataSource("mayikt-otherc3p0");
        // configure jdbc connection xml/configure.properties yml
//        pool.setUser("root"); //set user
//        pool.setPassword("root"); //set pwd
//        pool.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/sql_student?serverTimezone=UTC"); //set jdbc url
//        pool.setDriverClass("com.mysql.jdbc.Driver"); //set driver
        //default loading src/c3p0-config.xml
        // get connection object
        Connection connection = pool.getConnection();
        // get preparedStatement object, avoid sql injection
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
        resultSet.close();
        connection.close();

    }
}
