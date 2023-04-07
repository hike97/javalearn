package mianshi.jdbc;

import java.sql.*;

/**
 * @author: hike97
 * @createTime: 2023/04/06 17:33
 * @description: TODO
 */
public class T02_PrepareStatement {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) throws SQLException {

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select `id`,`first`, `last`, age from employees where age > ?";
        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            ps = conn.prepareStatement(sql);
            ps.setInt(1,18);
            //这里不需要再传参数 否则会报错
            rs = ps.executeQuery();

            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");
                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs == null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }

            if (conn != null) {
                conn.close();
            }

        }
    }
}
