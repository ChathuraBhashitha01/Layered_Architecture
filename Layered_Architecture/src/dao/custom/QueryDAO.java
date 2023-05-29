package dao.custom;

import dao.SuperDAO;
import entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    public ArrayList<CustomEntity> searchOrderByOID(String oid) throws SQLException, ClassNotFoundException;
}
