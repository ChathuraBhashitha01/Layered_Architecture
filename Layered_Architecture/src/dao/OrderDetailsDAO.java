package dao;

import db.DBConnection;
import model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailsDAO {
 public boolean saveOrderDetails(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
     return SQLUtil.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)",dto.getOrderID(),dto.getItemCode(),dto.getUnitPrice(),dto.getQty());
 }
}
