package dao;

import db.DBConnection;
import model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderDetailsDAO {
 public boolean saveOrderDetails(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
     Connection connection = DBConnection.getDbConnection().getConnection();
     PreparedStatement stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");
     stm.setString(1, dto.getOrderID());
     stm.setString(2, dto.getItemCode());
     stm.setBigDecimal(3, dto.getUnitPrice());
     stm.setInt(4, dto.getQty());
     return stm.executeUpdate()>0;
 }
}