package lk.ijse.dao;

import lk.ijse.Database.DBConnection;
import lk.ijse.dao.custom.CrudDAO;
import lk.ijse.dto.RoomDTO;
import lk.ijse.entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RoomDao extends CrudDAO<Room> {

    public  boolean Save(Room room) throws SQLException;

    public  boolean update(Room room) throws SQLException;

    public  Room search(String roomId) throws SQLException;



    public  boolean delete(String roomId) throws SQLException;

    public List<Room> getAll() throws SQLException;



    public  List<String> getIds() throws SQLException ;

    public boolean updateRoomStatus(String room) throws SQLException ;



}
