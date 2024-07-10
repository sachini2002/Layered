package lk.ijse.dao.impl;

import javafx.scene.control.Alert;
import lk.ijse.Database.DBConnection;
import lk.ijse.dao.BookingDao;
import lk.ijse.dao.custom.SQLUtil;
import lk.ijse.entity.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoImpl implements BookingDao<Booking> {



    public Booking search(String bookingId) throws SQLException {
        /*
        String sql = "SELECT * FROM Booking WHERE Booking_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, bookingId);

        ResultSet resultSet = pstm.executeQuery();

         */
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Booking WHERE Booking_id = ?",bookingId);
        if (resultSet.next()) {
            String roomId = resultSet.getString("Room_id");
            String packageId = resultSet.getString("Package_id");
            String customerId = resultSet.getString("Customer_id");

            // Create a new Booking object with retrieved values
            Booking booking = new Booking(bookingId, roomId, packageId, customerId);

            return booking;
        } else {
            return null;
        }
    }



    public List<String> getIds() throws SQLException {
        /*
        String sql = "SELECT Booking_id FROM Booking";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

         */
        ResultSet resultSet = SQLUtil.execute("SELECT Booking_id FROM Booking");

        List<String> BookingIdList = new ArrayList<>();
        while (resultSet.next()){
            BookingIdList.add(resultSet.getString(1));
        }
        return BookingIdList;

    }

    public  boolean saveBookingAndUpdate(String booking, String cutomer, String Packagee, String room) throws SQLException {

        /*

            // Insert into Booking table
            String bookingSql = "INSERT INTO Booking (Booking_id, Room_id, Package_id, Customer_id) VALUES (?, ?, ?, ?)";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement bookingPstm = connection.prepareStatement(bookingSql);
            bookingPstm.setString(1, booking);
            bookingPstm.setString(2, room);
            bookingPstm.setString(3, Packagee);
            bookingPstm.setString(4, cutomer);

            int bookingResult = bookingPstm.executeUpdate();
            if (bookingResult > 0){
                return true;
            }
            return false;

         */
        return  SQLUtil.execute("INSERT INTO Booking (Booking_id, Room_id, Package_id, Customer_id) VALUES (?, ?, ?, ?)",booking
        ,room,Packagee,cutomer);



    }

    public  List<Booking> getAll() throws SQLException {
        String sql = "SELECT Booking_id, Room_id, Package_id, Customer_id FROM Booking";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        List<Booking> bookingList = new ArrayList<>();
        while (resultSet.next()) {
            String bookingId = resultSet.getString("Booking_id");
            String roomId = resultSet.getString("Room_id");
            String packageId = resultSet.getString("Package_id");
            String customerId = resultSet.getString("Customer_id");

            Booking booking = new Booking(bookingId, roomId, packageId, customerId);
            bookingList.add(booking);
        }
        return bookingList;
    }

    @Override
    public boolean Save(Booking T) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Booking T) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String roomId) throws SQLException {
        return false;
    }
}
