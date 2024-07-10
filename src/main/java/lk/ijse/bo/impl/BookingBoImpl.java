package lk.ijse.bo.impl;

import javafx.scene.control.Alert;
import lk.ijse.Database.DBConnection;
import lk.ijse.bo.BookingBo;
import lk.ijse.dao.BookingDao;
import lk.ijse.dao.RoomDao;
import lk.ijse.dao.custom.DAOFactory;
import lk.ijse.dao.custom.DAOTypes;
import lk.ijse.dto.BookingDTO;
import lk.ijse.entity.Booking;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingBoImpl implements BookingBo {

    BookingDao bookingDao = (BookingDao) DAOFactory.getDaoFactory().getDAO(DAOTypes.BOOKING);

    RoomDao roomDao = (RoomDao) DAOFactory.getDaoFactory().getDAO(DAOTypes.ROOM);

    public BookingDTO searchBookingById(String bookingId) throws SQLException {
        Booking search = bookingDao.search(bookingId);
        return  new BookingDTO(search.getBookingId(),search.getRoomId(),search.getPackageId(),search.getCustomerId());
    }

    public List<String> getAllBookigId() throws SQLException {
        /*
        String sql = "SELECT Booking_id FROM Booking";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        List<String> BookingIdList = new ArrayList<>();
        while (resultSet.next()){
            BookingIdList.add(resultSet.getString(1));
        }
        return BookingIdList;

         */
        return  bookingDao.getIds();

    }

    public  boolean saveBookingAndUpdate(String booking, String cutomer, String Packagee, String room) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            // Insert into Booking table
            boolean b = bookingDao.saveBookingAndUpdate(booking, cutomer, Packagee, room);


            boolean b1 = roomDao.updateRoomStatus(room);

            // If all operations are successful, commit the transaction
            if (b &&  b1) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);

        }

    }

    public  List<BookingDTO> getallBooking() throws SQLException {
        /*
        String sql = "SELECT Booking_id, Room_id, Package_id, Customer_id FROM Booking";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

         */
        List<BookingDTO> bookingList = new ArrayList<>();
        List <Booking> all = bookingDao.getAll();
        for (Booking booking : all){
            BookingDTO bookingDTO = new BookingDTO(
                    booking.getBookingId(),
                    booking.getRoomId(),
                    booking.getPackageId(),
                    booking.getCustomerId()
            );
            bookingList.add(bookingDTO);
        }
        return bookingList;
    }

}
