package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {

    private static String sqlStatement;
    public static void main(String[] args) throws Exception {
        //通过工具类获取数据库连接对象
        Connection con = JDBCUtils.getConnection();
        //通过连接创建数据库执行对象
        Statement sta = con.createStatement();
        //为查询的结果集准备接收对象
        ResultSet rs = null;
        //查询
        sqlStatement = "SELECT * FROM STUDENT";
        qry(sta,sqlStatement,rs);
        //增加
        sqlStatement = "INSERT INTO STUDENT VALUES('1','ZHANGSAN')";
        System.out.println("插入执行结果:"+update(sta,sqlStatement));
        //更新
        sqlStatement = "UPDATE STUDENT SET NAME='LISI' WHERE ID = '1'";
        System.out.println("更新执行结果:"+update(sta,sqlStatement));
        //删除
        sqlStatement = "DELETE FROM STUDENT WHERE ID = '1'";
        System.out.println("删除执行结果:"+update(sta,sqlStatement));
        JDBCUtils.closeResource(con, sta, rs);
    }

    /**
     * 查询
     * @param sta
     * @param sql
     * @param rs
     * @throws SQLException
     */
    private static void qry(Statement sta, String sql, ResultSet rs) throws SQLException {
        rs = sta.executeQuery(sql);
        while(rs.next()) {
            System.out.println(rs.getObject("ID" ));
        }
    }

    /**
     * 增删改
     * @param sta
     * @param sql
     * @return
     * @throws SQLException
     */
    private static int update(Statement sta,String sql) throws SQLException {
        return sta.executeUpdate(sql);
    }
}
