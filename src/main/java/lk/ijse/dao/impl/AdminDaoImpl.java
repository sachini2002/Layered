package lk.ijse.dao.impl;

import lk.ijse.dao.AdminDao;
import lk.ijse.dao.custom.SQLUtil;
import lk.ijse.entity.Admin;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    public  boolean updateUser(String pw, String userName) throws SQLException {
        /*
        String sql = "UPDATE Admin SET Passward = ? WHERE User_name = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, pw);
        pstm.setObject(2, userName);

        return pstm.executeUpdate() > 0;

         */
        return  SQLUtil.execute("UPDATE Admin SET Passward = ? WHERE User_name = ?",pw,userName);
    }

    public  boolean Save(Admin admin) throws SQLException {
        /*

        String sql = "INSERT INTO Admin VALUES(?,?,?,?) ";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, admin.getUserid());
        pstm.setString(2, admin.getUsername());

        pstm.setString(3, admin.getEmail());
        pstm.setInt(4, admin.getPw());


        return pstm.executeUpdate() > 0;

         */
        return  SQLUtil.execute("INSERT INTO Admin VALUES(?,?,?,?) ",admin.getUserid(),admin.getUsername(),admin.getEmail(),admin.getPw());
    }

    public boolean executeUpdate(String userId, String newPassword) throws SQLException {
        /*
        String query = "UPDATE Admin SET pw = ? WHERE userid = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, userId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }

         */
        return  SQLUtil.execute("UPDATE Admin SET pw = ? WHERE userid = ?",newPassword,userId);
    }

    @Override
    public boolean update(Admin T) throws SQLException {
        return false;
    }

    @Override
    public Admin search(String Id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String roomId) throws SQLException {
        return false;
    }

    @Override
    public List<Admin> getAll() throws SQLException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }



}
