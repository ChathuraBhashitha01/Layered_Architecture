package bo.custom;

import bo.SuperBO;
import model.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PurchaseOrderBO extends SuperBO {
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException ;
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException ;

    public boolean existItemByCode(String code) throws SQLException, ClassNotFoundException;

    public boolean existCustomerByID(String id) throws SQLException, ClassNotFoundException ;

    public String generateNewOrderID() throws SQLException, ClassNotFoundException ;

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    public boolean existOrderByID(String oid) throws SQLException, ClassNotFoundException ;

    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;

    public ItemDTO findItemByID(String code);

    public ArrayList<CustomEntityDTO> searchOrderByID(String oid) throws SQLException, ClassNotFoundException;
}
