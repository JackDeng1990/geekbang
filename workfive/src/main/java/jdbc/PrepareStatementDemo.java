package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrepareStatementDemo {
    private static String sqlStatement;
    public static void main(String[] args) throws SQLException {
        //通过工具类获取数据库连接对象
        Connection con = JDBCUtils.getConnection();
        //通过连接创建数据库执行对象
        PreparedStatement ps = null;
        //为查询的结果集准备接收对象
        ResultSet rs = null;
        //查询
        sqlStatement = "SELECT * FROM STUDENT WHERE ID = ?";
        ps = con.prepareStatement(sqlStatement);
        ps.setObject(1, "1");
        qry(ps,rs);
        //增加
        sqlStatement = "INSERT INTO DEPT VALUES(?,?)";
        ps = con.prepareStatement(sqlStatement);
        ps.setObject(1, "1");
        ps.setObject(2, "ZHANGSAN");
        System.out.println("插入执行结果:"+update(ps));
        //更新
        sqlStatement = "UPDATE STUDENT SET NAME=? WHERE ID = ?";
        ps = con.prepareStatement(sqlStatement);
        ps.setObject(1, "LISI");
        ps.setObject(2, "1");
        System.out.println("更新执行结果:"+update(ps));
        //删除
        sqlStatement = "DELETE FROM STUDENT WHERE ID = ?";
        ps = con.prepareStatement(sqlStatement);
        ps.setObject(1, "1");
        System.out.println("删除执行结果:"+update(ps));
        JDBCUtils.closeResource(con, ps, rs);
    }

    /**
     * 查询
     * @param sta
     * @param rs
     * @throws SQLException
     */
    private static void qry(PreparedStatement sta, ResultSet rs) throws SQLException {
        rs = sta.executeQuery();
        while(rs.next()) {
            System.out.println(rs.getObject("deptno"));
        }
    }

    /**
     * 增删改
     * @param sta
     * @return
     * @throws SQLException
     */
    private static int update(PreparedStatement sta) throws SQLException {
        return sta.executeUpdate();
    }
}
