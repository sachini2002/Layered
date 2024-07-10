package lk.ijse.bo;

import lk.ijse.dto.RoomDTO;
import lk.ijse.entity.Room;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RoomBO extends SuperBO {

    public  boolean saveRoom(RoomDTO room) throws SQLException;

    public  boolean updateRoom(RoomDTO room) throws SQLException;

    public  RoomDTO searchRoomId(String roomId) throws SQLException;
    ;
    public  boolean deleteRoom(String roomId) throws SQLException;

    public List<RoomDTO> getAllRoom() throws SQLException;

    public  RoomDTO searchRoomById(String roomid) throws SQLException;

    public  List<String> getIDs() throws SQLException;

}
