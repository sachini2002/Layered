package lk.ijse.dao.impl;

import lk.ijse.Database.DBConnection;
import lk.ijse.dao.RoomDao;
import lk.ijse.dao.custom.SQLUtil;
import lk.ijse.dto.RoomDTO;
import lk.ijse.entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {
    public  boolean Save(Room room) throws SQLException {
           /* String sql = "INSERT INTO Room (Room_id, Room_no, Type, Rate, Status) VALUES (?,?,?,?,?)";

            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, room.getRoomId());
            pstm.setObject(2, room.getRoomNumber());
            pstm.setObject(3, room.getRoomType());
            pstm.setObject(4, room.getRoomRate());
            pstm.setObject(5, room.getRoomStatus());

            return pstm.executeUpdate() > 0;

            */

            return SQLUtil.execute("INSERT INTO Room (Room_id, Room_no, Type, Rate, Status) VALUES (?,?,?,?,?)",room.getRoomId(),room.getRoomNumber(),room.getRoomType(),room.getRoomRate(),room.getRoomStatus());
        }

    public  boolean update(Room room) throws SQLException {
       /* String sql = "UPDATE Room SET Room_no = ?, Type = ?, Rate = ?, Status = ? WHERE Room_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, room.getRoomNumber());
        pstm.setObject(2, room.getRoomType());
        pstm.setObject(3, room.getRoomRate());
        pstm.setObject(4, room.getRoomStatus());
        pstm.setObject(5, room.getRoomId());

        return pstm.executeUpdate() > 0;

        */

      return   SQLUtil.execute("UPDATE Room SET Room_no = ?, Type = ?, Rate = ?, Status = ? WHERE Room_id = ?", room.getRoomNumber(), room.getRoomType(), room.getRoomRate(), room.getRoomStatus(), room.getRoomId());
    }

/*
    public  Room search(String roomId) throws SQLException {

       /* String sql = "SELECT * FROM Room WHERE Room_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, roomId); // Use setString instead of setObject for String parameters

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String roomID = resultSet.getString("Room_id");
            String roomNumber = resultSet.getString("Room_no");
            String type = resultSet.getString("Type");
            String rate = resultSet.getString("Rate");
            String status = resultSet.getString("Status");

            // Use the retrieved values to create a new Room object
            Room room = new Room(roomID, roomNumber, type, rate, status);

            return room;
        } else {
            return null;
        }



       return SQLUtil.execute("SELECT * FROM Room WHERE Room_id = ?", roomId);
    }

 */

    public  boolean delete(String roomId) throws SQLException {
       /* String sql = "DELETE FROM Room WHERE Room_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, roomId);

        return pstm.executeUpdate() > 0;

        */
        return SQLUtil.execute("DELETE FROM Room WHERE Room_id = ?",roomId );
    }

    public  List<Room> getAll() throws SQLException {

        /*
        String  sql = "SELECT * FROM Room";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

         */
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Room");

        List<Room> roomList = new ArrayList<>();
        while (resultSet.next()){
            String RoomId = resultSet.getString(1);
            String RoomNo = resultSet.getString(2);
            String Type = resultSet.getString(3);
            String Rate = resultSet.getString(4);
            String Status = resultSet.getString(5);

            Room room = new Room(RoomId,RoomNo,Type,Rate,Status);
            roomList.add(room);
        }
        return roomList;






    }




    public  Room search(String roomid) throws SQLException {
        /*
        String sql = "SELECT * FROM Room WHERE Room_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, roomid); // Use setString instead of setObject for String parameters

        ResultSet resultSet = pstm.executeQuery();

         */
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Room WHERE Room_id = ?",roomid);
        if (resultSet.next()) {
            String roomID = resultSet.getString(1);
            String roomNumber = resultSet.getString(2);
            String type = resultSet.getString(3);
            String rate = resultSet.getString(4);
            String status = resultSet.getString(5);

            // Use the retrieved values to create a new Room object
            Room room = new Room(roomID, roomNumber, type, rate, status);

            return room;
        } else {
            return null;
        }



    }



    public  List<String> getIds() throws SQLException {
       /*String sql = "SELECT Room_id FROM Room";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        */
        ResultSet resultSet =  SQLUtil.execute("SELECT Room_id FROM Room");
        List<String> mtIDList = new ArrayList<>();
        while (resultSet.next()){
            mtIDList.add(resultSet.getString(1));
        }
        return mtIDList;




    }
    public boolean updateRoomStatus(String room ) throws SQLException {

//        String roomUpdateSql = "UPDATE Room SET Status = 'Booked' WHERE Room_id = ?";
//        Connection connection = DBConnection.getInstance().getConnection();
//        PreparedStatement roomUpdatePstm = connection.prepareStatement(roomUpdateSql);
//        roomUpdatePstm.setString(1, room);
//        int roomUpdateResult = roomUpdatePstm.executeUpdate();
//        if (roomUpdateResult > 0 ){
//            return true;
//        }
//        return false;
       return SQLUtil.execute("UPDATE Room SET Status = 'Booked' WHERE Room_id = ?",room);


    }


}

