package dao;

import db.DBConnection;
import model.CustomerDTO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
