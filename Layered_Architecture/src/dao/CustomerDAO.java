package dao;

import db.DBConnection;
import model.CustomerDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class CustomerDAO {

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer");
        while (rst.next()) {
            String id = rst.getString(1);
            String name = rst.getString(2);
            String address = rst.getString(3);
            CustomerDTO customerDTO = new CustomerDTO(id, name, address);
            allCustomers.add(customerDTO);
        }
        return allCustomers;
    }


    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        return pstm.executeUpdate()>0;
    }
}
