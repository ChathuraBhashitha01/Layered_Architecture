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

    public enum DAOTypes{
        CUSTOMER,ITEM,ORDER,ORDER_DETAILS,QUERY_DAO
    }

    public SuperDAO  getDAO(DAOTypes res){
        switch (res){
            case CUSTOMER:
               return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAILS:
                return new OrderDetailsDAOImpl();
            case QUERY_DAO:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }


}
