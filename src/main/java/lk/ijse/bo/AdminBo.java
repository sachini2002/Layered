package lk.ijse.bo;


import lk.ijse.dto.AdminDTO;

import java.sql.Connection;

import java.sql.SQLException;

public interface AdminBo extends SuperBO {
    public  boolean updateUser(String pw, String userName) throws SQLException;
    public  boolean saveUsers(AdminDTO admin) throws SQLException ;

    public boolean executeUpdate(Connection connection, String userId, String newPassword) throws SQLException ;
}
