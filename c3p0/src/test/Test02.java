package test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * testing connection pool maxPoolSize
 */
public class Test02 {
    public static void main(String[] args) throws SQLException {
        //create c3p0 connection pool
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        for(int i=1; i<=20; i++){
            Connection connection = comboPooledDataSource.getConnection();// getting connection from connection pool
            System.out.println(i+", "+connection);
            if(i==10){
                connection.close(); //release the connection
            }
        }
    }
}
