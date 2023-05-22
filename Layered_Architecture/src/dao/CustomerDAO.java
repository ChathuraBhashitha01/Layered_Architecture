package dao;

import db.DBConnection;
import model.CustomerDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class CustomerDAO {

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
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
       return SQLUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",dto.getId(),dto.getName(), dto.getAddress());
    }

    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getId());
        return pstm.executeUpdate()>0;
    }

    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();
    }


    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeUpdate()>0;
    }

    public String generateNewID() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }


    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
        pstm.setString(1, id);
        ResultSet rst = pstm.executeQuery();
        if (rst.next()){
            String cusID = rst.getString(1);
            String name = rst.getString(2);
            String address = rst.getString(3);
           return new CustomerDTO(cusID,name,address);
        }
        return null;
    }
}
