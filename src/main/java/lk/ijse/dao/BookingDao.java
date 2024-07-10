package lk.ijse.dao;


import lk.ijse.dao.custom.CrudDAO;
import lk.ijse.entity.Booking;


import java.sql.SQLException;

import java.util.List;

public interface BookingDao<B> extends CrudDAO<Booking> {

    public Booking search(String bookingId) throws SQLException ;


    public List<String> getIds() throws SQLException ;


    public  List<Booking> getAll() throws SQLException ;

    public  boolean saveBookingAndUpdate(String booking, String cutomer, String Packagee, String room) throws SQLException ;

}
