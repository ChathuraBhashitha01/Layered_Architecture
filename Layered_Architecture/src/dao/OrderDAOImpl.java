package dao;

import model.OrderDTO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAOImpl {
    public String generateNewOID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    public boolean existOrder(String orderID) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT oid FROM `Orders` WHERE oid=?",orderID);
        return rst.next();
    }

    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",dto.getOrderId(),Date.valueOf(dto.getOrderDate()),dto.getCustomerId());
    }

}
