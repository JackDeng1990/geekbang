package com.geektime.seven;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

@SpringBootTest
class SevenApplicationTests {

    @Test
    void contextLoads() {
    }
    private String url = "jdbc:mysql://8.140.45.11:3306/geektime?rewriteBatchedStatements=true&characterEncoding=utf-8&autoReconnect=true";
    private String user = "root";
    private String password = "dengchao**";

    @Test
    void test() {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO user(`id`, `password`, `name`, `telphone`, `birthday`, `email`, `create_time`, `last_update_time`) VALUES(NULL, ?, ?, ?, ?, ?, ?, ?)";
            pstm = conn.prepareStatement(sql);
            //关闭事务自动提交
            conn.setAutoCommit(false);
            Long startTime = System.currentTimeMillis();
            Random rand = new Random();
            int a, b, c, d;
            for (int i = 1; i <= 1000000; i++) {
                a = rand.nextInt(10);
                b = rand.nextInt(10);
                c = rand.nextInt(10);
                d = rand.nextInt(10);
                pstm.setString(1, String.valueOf(i));
                pstm.setString(2, String.valueOf(i));
                pstm.setString(3, "188" + a + "88" + b + c + "66" + d);
                pstm.setString(4, "199" + a + "-0" + b + "-0" + c );
                pstm.setString(5, "19" + a + d + "-0" + b + "-0" + c + "@126.com");
                pstm.setLong(6,System.currentTimeMillis());
                pstm.setLong(7,System.currentTimeMillis());
                pstm.addBatch();
                System.out.println(i);
            }
            //
            System.out.println(System.currentTimeMillis());
            pstm.executeBatch();
            //提交事务
            conn.commit();
            Long endTime = System.currentTimeMillis();
            System.out.println("OK 耗时：" + (endTime - startTime) + "毫秒");
        } catch (Exception e) {
            System.out.println(System.currentTimeMillis());
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
