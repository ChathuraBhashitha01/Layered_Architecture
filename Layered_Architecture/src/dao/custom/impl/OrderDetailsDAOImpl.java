package dao.custom.impl;

import dao.custom.OrderDetailsDAO;
import dao.custom.impl.util.SQLUtil;
import model.OrderDetailDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public ArrayList<OrderDetailDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM OrderDetails");
        ArrayList<OrderDetailDTO> allItems = new ArrayList<>();
        while (rst.next()) {
            allItems.add(new OrderDetailDTO(rst.getString(1), rst.getString(2), rst.getInt(3),rst.getBigDecimal(4)));
        }
        return allItems;
    }

    @Override
    public boolean save(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)", dto.getOrderID(), dto.getItemCode(), dto.getUnitPrice(), dto.getQty());
    }

    @Override
    public boolean update(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE OrderDetails SET itemCode=?, qty=?,unitPrice=? WHERE oid=?", dto.getItemCode(), dto.getQty(), dto.getUnitPrice(),dto.getOrderID());
    }

    @Override
    public boolean exist(String oid) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT oid FROM OrderDetails WHERE oid=?", oid);
        return rst.next();
    }

    @Override
    public boolean delete(String oid) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM OrderDetails WHERE oid=?", oid);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature yet to be developed");
    }

    @Override
    public OrderDetailDTO search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature yet to be developed");
    }

}
