package utils;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataSourceUtils {
    private static DataSource dataSource;
    private DataSourceUtils(){
    }
    /**
     * 使用静态代码款
     */
    static{
        try{
            //load druid.properties file
            Properties properties = new Properties();
            InputStream resourceAsStream = DataSourceUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(resourceAsStream);
            // give it to Druid Connection Pool
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Encapsulate connection method
     * @return
     */
    public static Connection getConnection(){
        try{
            return dataSource.getConnection();
        }catch (Exception e){
            return null;
        }
    }

    /**
     * encapsulate close method
     */

    public static void close(Connection connection, Statement statement, ResultSet resultSet){
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(statement!=null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(resultSet!=null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void close(Connection connection, Statement statement) {
        close(connection, statement, null);
    }
}
