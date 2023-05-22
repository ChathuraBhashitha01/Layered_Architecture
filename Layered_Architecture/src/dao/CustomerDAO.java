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
            allCustomers.add(new CustomerDTO(rst.getString(1), rst.getString(2), rst.getString(3)));
        }
        return allCustomers;
    }


    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",dto.getId(),dto.getName(), dto.getAddress());
    }

    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",dto.getName(),dto.getAddress(),dto.getId());
    }

    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer WHERE id=?",id);
        return rst.next();
    }


    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Customer WHERE id=?",id);
    }

    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }


    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst  = SQLUtil.execute("SELECT * FROM Customer WHERE id=?",id);
        if (rst.next()){
            String cusID = rst.getString(1);
            String name = rst.getString(2);
            String address = rst.getString(3);
           return new CustomerDTO(cusID,name,address);
        }
        return null;
    }
}
