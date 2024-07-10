package lk.ijse.bo;


import lk.ijse.dto.BookingDTO;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BookingBo extends SuperBO{
    public BookingDTO searchBookingById(String bookingId) throws SQLException ;

    public List<String> getAllBookigId() throws SQLException ;

    public  boolean saveBookingAndUpdate(String booking, String cutomer, String Packagee, String room) throws SQLException ;

    public  List<BookingDTO> getallBooking() throws SQLException ;

}
