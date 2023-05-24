package dao;

import dao.custom.CustomerDAO;
import dao.custom.impl.CustomerDAOImpl;

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
}
