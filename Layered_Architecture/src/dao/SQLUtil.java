package dao;

import db.DBConnection;

import java.sql.*;

public class SQLUtil {
    String testSQL="select * from Customer;";
    String testSQL1="insert into Customer values(?,?,?,?);";
    public static <T>T test(String sql,Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pstm.setObject((i+1),args[i]);
        }
        if (sql.startsWith("SELECT") || sql.startsWith("select")){
            return (T) pstm.executeQuery();
        }else{
            return (T)  new Boolean(pstm.executeUpdate()>0);
        }
    }

    public static void main(String[] args) {
        try {
            Boolean b = test("insert into Customer values(?,?,?)","C001","Dasun","Galle");
            System.out.println(b);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
