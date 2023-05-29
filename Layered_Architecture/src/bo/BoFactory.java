package bo;

import bo.custom.CustomerBO;
import bo.custom.ItemBO;
import bo.custom.impl.CustomerBOImpl;
import bo.custom.impl.ItemBOImpl;
import bo.custom.impl.PurchaseOrderBOImpl;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory(){

    }
    public static BoFactory getBoFactory(){
        return (boFactory==null) ? boFactory= new BoFactory():boFactory;
    }

    public enum BOTypes{
        CUSTOMER_BO,ITEM_BO,PURCHASE_ORDER_BO,
    }
    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER_BO:
               return new CustomerBOImpl();
            case ITEM_BO:
                return new ItemBOImpl();
            case PURCHASE_ORDER_BO:
                return new PurchaseOrderBOImpl();
            default:
                return null;
        }
    }

}
