package lk.ijse.bo.impl;

import lk.ijse.Database.DBConnection;
import lk.ijse.bo.RoomBO;
import lk.ijse.dao.RoomDao;
import lk.ijse.dao.custom.DAOFactory;
import lk.ijse.dao.custom.DAOTypes;
import lk.ijse.dto.RoomDTO;
import lk.ijse.entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RoomBoImpl implements RoomBO {

    RoomDao roomDao = (RoomDao) DAOFactory.getDaoFactory().getDAO(DAOTypes.ROOM);

    public  boolean saveRoom(RoomDTO room) throws SQLException {
      return roomDao.Save(new Room(room.getRoomId(), room.getRoomNumber(), room.getRoomType(), room.getRoomRate(), room.getRoomStatus()));
    }

    public  boolean updateRoom(RoomDTO room) throws SQLException {
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

      return roomDao.update(new Room(room.getRoomId(), room.getRoomNumber(), room.getRoomType(), room.getRoomRate(), room.getRoomStatus()));

    }


    public  RoomDTO searchRoomId(String roomId) throws SQLException {

      /*  String sql = "SELECT * FROM Room WHERE Room_id = ?";

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
            RoomDTO room = new RoomDTO(roomID, roomNumber, type, rate, status);

            return room;
        } else {
            return null;
        }

       */

        Room search = roomDao.search(roomId);
      return   new RoomDTO(search.getRoomId(), search.getRoomNumber(), search.getRoomType(), search.getRoomRate(), search.getRoomStatus());
    }

    public  boolean deleteRoom(String roomId) throws SQLException {
       /* String sql = "DELETE FROM Room WHERE Room_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, roomId);

        return pstm.executeUpdate() > 0;

        */

        return roomDao.delete(roomId);
    }

    public  List<RoomDTO> getAllRoom() throws SQLException {
       /* String  sql = "SELECT * FROM Room";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        List<RoomDTO> roomList = new ArrayList<>();
        while (resultSet.next()){
            String RoomId = resultSet.getString(1);
            String RoomNo = resultSet.getString(2);
            String Type = resultSet.getString(3);
            String Rate = resultSet.getString(4);
            String Status = resultSet.getString(5);

            RoomDTO room = new RoomDTO(RoomId,RoomNo,Type,Rate,Status);
            roomList.add(room);
        }
        return roomList;

        */
        List<RoomDTO> roomList = new ArrayList<>();
        List<Room> rooms = roomDao.getAll();
        for (Room room : rooms){
            RoomDTO roomDTO = new RoomDTO(
                    room.getRoomId(),
                    room.getRoomNumber(),
                    room.getRoomType(),
                    room.getRoomRate(),
                    room.getRoomStatus()
            );
            roomList.add(roomDTO);
        }
        return roomList;


    }

    public  RoomDTO searchRoomById(String roomid) throws SQLException {
       /* String sql = "SELECT * FROM Room WHERE Room_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, roomid); // Use setString instead of setObject for String parameters

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String roomID = resultSet.getString(1);
            String roomNumber = resultSet.getString(2);
            String type = resultSet.getString(3);
            String rate = resultSet.getString(4);
            String status = resultSet.getString(5);

            // Use the retrieved values to create a new Room object
            RoomDTO room = new RoomDTO(roomID, roomNumber, type, rate, status);

            return room;
        } else {
            return null;
        }

        */

        Room search = roomDao.search(roomid);
       return new RoomDTO(search.getRoomId(), search.getRoomNumber(), search.getRoomType(), search.getRoomRate(), search.getRoomStatus());
    }

    public  List<String> getIDs() throws SQLException {
       /* String sql = "SELECT Room_id FROM Room";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        List<String> mtIDList = new ArrayList<>();
        while (resultSet.next()){
            mtIDList.add(resultSet.getString(1));
        }
        return mtIDList;

        */

       return roomDao.getIds();
    }

}
