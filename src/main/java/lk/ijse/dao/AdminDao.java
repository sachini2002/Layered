package lk.ijse.dao;


import lk.ijse.dao.custom.CrudDAO;
import lk.ijse.entity.Admin;

import java.sql.SQLException;

public interface AdminDao extends CrudDAO<Admin> {
    public  boolean updateUser(String pw, String userName) throws SQLException;

    public  boolean Save(Admin admin) throws SQLException ;

    public boolean executeUpdate(String userId, String newPassword) throws SQLException ;

}
