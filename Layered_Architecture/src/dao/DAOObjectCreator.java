package dao;

import dao.custom.CustomerDAO;
import dao.custom.ItemDAO;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailsDAO;
import dao.custom.impl.CustomerDAOImpl;
import dao.custom.impl.ItemDAOImpl;
import dao.custom.impl.OrderDAOImpl;
import dao.custom.impl.OrderDetailsDAOImpl;

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
}
