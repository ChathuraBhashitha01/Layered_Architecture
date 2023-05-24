package dao;

import dao.custom.CustomerDAO;
import dao.custom.ItemDAO;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailsDAO;
import dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoObjectCreator;
    private DAOFactory(){

    }
    public static DAOFactory getDAOFactory(){
        if (daoObjectCreator==null){
            daoObjectCreator= new DAOFactory();
        }
        return daoObjectCreator;
    }

    public SuperDAO  getDAO(String res){
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
