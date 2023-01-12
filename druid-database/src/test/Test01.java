package test;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import entity.UserEntity;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 *  use Druid Database
 */
public class Test01 {
    public static void main(String[] args) throws Exception {
        //load druid.properties
        Properties properties = new Properties();
        InputStream resourceAsStream =
                Test01.class.getClassLoader().getResourceAsStream("druid.properties");
        properties.load(resourceAsStream);
        //put data into druid connection pool
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        // use dataSource to get connection
        Connection connection = dataSource.getConnection();

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
