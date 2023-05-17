package dao;

import db.DBConnection;
import model.ItemDTO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemDAO {
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        while (rst.next()) {
            String code = rst.getString(1);
            String itemName = rst.getString(2);
            int qty = rst.getInt(3);
            BigDecimal price = rst.getBigDecimal(4);
            ItemDTO itemDTO = new ItemDTO(code, itemName, price, qty);
            allItems.add(itemDTO);
        }
        return allItems;
    }
}
