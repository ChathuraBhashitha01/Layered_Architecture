package dao;

import model.CustomerDTO;
import model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements CrudDAO<CustomerDTO>{
    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(CustomerDTO id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(CustomerDTO id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public CustomerDTO search(CustomerDTO id) throws SQLException, ClassNotFoundException {
        return null;
    }


//    @Override
//    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
//        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");
//        ArrayList<ItemDTO> allItems = new ArrayList<>();
//        while (rst.next()) {
//            allItems.add(new ItemDTO(rst.getString(1), rst.getString(2), rst.getBigDecimal(4), rst.getInt(3)));
//        }
//        return allItems;
//    }
//
//    @Override
//    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
//        return SQLUtil.execute("DELETE FROM Item WHERE code=?",code);
//    }
//
//    @Override
//    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
//       return SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand());
//    }
//
//    @Override
//    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
//        return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",dto.getDescription(), dto.getUnitPrice(),dto.getQtyOnHand(), dto.getCode());
//    }
//
//    @Override
//    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
//        ResultSet rst = SQLUtil.execute("SELECT code FROM Item WHERE code=?",code);
//        return rst.next();
//    }
//
//    @Override
//    public String generateNewCode() throws SQLException, ClassNotFoundException {
//        ResultSet rst = SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
//        if (rst.next()) {
//            String id = rst.getString("code");
//            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
//            return String.format("I00-%03d", newItemId);
//        } else {
//            return "I00-001";
//        }
//    }
//
//    @Override
//    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
//        ResultSet rst= SQLUtil.execute("SELECT * FROM Item WHERE code=?",code);
//        if (rst.next()){
//            return new ItemDTO(rst.getString(1),rst.getString(2),rst.getBigDecimal(4),rst.getInt(3));
//        }
//        return null;
//    }


}
