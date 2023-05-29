package bo.custom.impl;

import bo.custom.PurchaseOrderBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dao.custom.ItemDAO;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailsDAO;
import db.DBConnection;
import entity.Customer;
import entity.Item;
import entity.OrderDetails;
import entity.Orders;
import model.CustomerDTO;
import model.ItemDTO;
import model.OrderDTO;
import model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {
    CustomerDAO customerDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDAO orderDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailsDAO orderDetailsDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer c = customerDAO.search(id);
        return new CustomerDTO(c.getId(), c.getName(), c.getAddress());
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item i = itemDAO.search(code);
        return new ItemDTO(i.getCode(), i.getDescription(), i.getUnitPrice(), i.getQtyOnHand());
    }

    @Override
    public boolean existItemByCode(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public boolean existCustomerByID(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public String generateNewOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> entityList = customerDAO.getAll();
        ArrayList<CustomerDTO> dtoList = new ArrayList<>();
        for (Customer c : entityList) {
            dtoList.add(new CustomerDTO(c.getId(), c.getName(), c.getAddress()));
        }
        return dtoList;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> entityList = itemDAO.getAll();
        ArrayList<ItemDTO> dtoList = new ArrayList<>();
        for (Item item : entityList) {
            dtoList.add(new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
        }
        return dtoList;
    }

    @Override
    public boolean existOrderByID(String oid) throws SQLException, ClassNotFoundException {
        return orderDAO.exist(oid);
    }

    @Override
    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        /*Transaction*/
        Connection connection = null;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            /*if order id already exist*/
            if (orderDAO.exist(dto.getOrderId())) {
                return false;
            }

            connection.setAutoCommit(false);

            Orders orderEntity = new Orders(dto.getOrderId(), dto.getOrderDate(), dto.getCustomerId());
            boolean orderAdded = orderDAO.save(orderEntity);
            if (!orderAdded) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            for (OrderDetailDTO odDTO : dto.getOrderDetaisList()) {
                OrderDetails orderDetailsEntity = new OrderDetails(odDTO.getOrderID(), odDTO.getItemCode(), odDTO.getQty(), odDTO.getUnitPrice());
                boolean odAdded = orderDetailsDAO.save(orderDetailsEntity);
                if (!odAdded) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

//                //Search & Update Item
                ItemDTO item = findItemByID(orderDetailsEntity.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - orderDetailsEntity.getQty());
                boolean itemUpdate = itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getQtyOnHand(), item.getUnitPrice()));

                if (!itemUpdate) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ItemDTO findItemByID(String code) {
        try {
            Item search = itemDAO.search(code);
            return new ItemDTO(search.getCode(),search.getDescription(),search.getUnitPrice(),search.getQtyOnHand());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
