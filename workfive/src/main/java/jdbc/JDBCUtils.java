package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC连接数据库和关闭资源工具类
 * @author admin
 */
public class JDBCUtils {

    private static String driverName;
    private static String jdbcUrl;
    private static String username;
    private static String password;

    /**
     * 通过静态代码块，初始化数据库连接配置数据，并且注册数据库驱动
     */
    static {
        try {
            Properties pr = new Properties();
            //通过读取Properties文件给属性赋值，即每次使用该工具类都会读取最新配置进行连接
            pr.load(new FileInputStream(new File("jdbc_config.properties")));
            driverName = pr.getProperty("driverName");
            jdbcUrl = pr.getProperty("jdbcUrl");
            username = pr.getProperty("username");
            password = pr.getProperty("password");
            Class.forName(driverName);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     * @return
     */
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * 关闭JDBC相关资源
     * @param con
     * @param sta
     * @param rs
     */
    public static void closeResource(Connection con, Statement sta, ResultSet rs) {
        try {
            if(con!=null) {
                con.close();
            }
            if(sta!=null) {
                sta.close();
            }
            if(rs!=null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
