package dao;

import model.OrderDTO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDAO {
    public String generateNewOID() throws SQLException, ClassNotFoundException ;

    public boolean existOrder(String orderID) throws SQLException, ClassNotFoundException ;

    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException ;
}
