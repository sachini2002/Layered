package lk.ijse.bo;

import lk.ijse.Database.DBConnection;
import lk.ijse.dto.PaymentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO{

    public boolean savePayment(PaymentDTO payment) throws SQLException;

    public PaymentDTO searchBookingId(String bookiID);

    public boolean updatePayment(PaymentDTO payment) throws SQLException;

    public boolean deletePayment(String paymentIdText) throws SQLException;

    public List<PaymentDTO> getAllPayments() throws SQLException;
}
