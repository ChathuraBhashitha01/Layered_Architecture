package dao;

import dao.custom.CustomerDAO;
import dao.custom.ItemDAO;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailsDAO;
import dao.custom.impl.*;

public class DAOObjectCreator {
    private static DAOObjectCreator daoObjectCreator;
    private DAOObjectCreator(){

    }
    public static DAOObjectCreator getInstance(){
        if (daoObjectCreator==null){
            daoObjectCreator= new DAOObjectCreator();
        }
        return daoObjectCreator;
    }

    public CustomerDAO getCustomerDAO(){
        return new CustomerDAOImpl();
    }

    public ItemDAO getItemDAO(){
       return new ItemDAOImpl();
    }

    public OrderDAO getOrderDAO(){
        return new OrderDAOImpl();
    }

    public OrderDetailsDAO getOrderDetailsDAO(){
        return new OrderDetailsDAOImpl();
    }

    public   getDAO(String res){
        switch (res){
            case "Customer":
               return new CustomerDAOImpl();
            case "Item":
                return new ItemDAOImpl();
            case "Order":
                return new OrderDAOImpl();
            case "OrderDetails":
                return new OrderDetailsDAOImpl();
            case "QueryDAO":
                return new QueryDAOImpl();
            default:
                return null;

        }
    }


}
