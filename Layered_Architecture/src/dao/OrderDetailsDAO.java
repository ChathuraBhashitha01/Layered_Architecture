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
     stm.setString(1, dto.get);
     stm.setString(2, detail.getItemCode());
     stm.setBigDecimal(3, detail.getUnitPrice());
     stm.setInt(4, detail.getQty());
 }
}
