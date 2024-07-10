package lk.ijse.bo.impl;

import lk.ijse.Database.DBConnection;
import lk.ijse.bo.AdminBo;
import lk.ijse.dao.AdminDao;
import lk.ijse.dao.custom.DAOFactory;
import lk.ijse.dao.custom.DAOTypes;
import lk.ijse.dto.AdminDTO;
import lk.ijse.entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminBoImpl implements AdminBo {

    AdminDao adminDao = (AdminDao) DAOFactory.getDaoFactory().getDAO(DAOTypes.ADMIN);
    public  boolean updateUser(String pw, String userName) throws SQLException {
       return adminDao.updateUser(pw,userName);

    }

    public  boolean saveUsers(AdminDTO admin) throws SQLException {
        return  adminDao.Save(new Admin(admin.getUserid(),admin.getUsername(),admin.getEmail(),admin.getPw()));
    }

    public boolean executeUpdate(Connection connection, String userId, String newPassword) throws SQLException {

        return  adminDao.executeUpdate(userId,newPassword);
    }

}
