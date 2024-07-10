package lk.ijse.dao.impl;

import lk.ijse.Database.DBConnection;
import lk.ijse.dao.PaymentDao;
import lk.ijse.dao.custom.SQLUtil;
import lk.ijse.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {
    public boolean Save(Payment payment) throws SQLException {
       /* String sql = "INSERT INTO Payment (Payment_id, Date, Method, Amount, Booking_id) VALUES (?,?,?,?,?)";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, payment.getPaymentId());
        pstm.setObject(2, payment.getDate());
        pstm.setObject(3, payment.getMethod());
        pstm.setObject(4, payment.getAmount());
        pstm.setObject(5, payment.getBookingId());

        return pstm.executeUpdate() > 0;

        */

      return SQLUtil.execute("INSERT INTO Payment (Payment_id, Date, Method, Amount, Booking_id) VALUES (?,?,?,?,?)", payment.getPaymentId(), payment.getDate(), payment.getMethod(), payment.getAmount(), payment.getBookingId());


    }

    public Payment search(String bookiID) {
        return null;
    }

    public boolean update(Payment payment) throws SQLException {

        /*String sql = "UPDATE Payment SET Date = ?, Method = ?, Amount = ?, Booking_id = ? WHERE Payment_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, payment.getDate());
        pstm.setObject(2, payment.getMethod());
        pstm.setObject(3, payment.getAmount());
        pstm.setObject(4, payment.getBookingId());
        pstm.setObject(5, payment.getPaymentId());


        return pstm.executeUpdate() > 0;

         */

       return SQLUtil.execute("UPDATE Payment SET Date = ?, Method = ?, Amount = ?, Booking_id = ? WHERE Payment_id = ?", payment.getDate(), payment.getMethod(), payment.getAmount(), payment.getBookingId(), payment.getPaymentId());
    }

    public boolean delete(String paymentIdText) throws SQLException {
       /* String sql = "DELETE FROM Payment WHERE Payment_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, paymentIdText);

        return pstm.executeUpdate() > 0;

        */

       return SQLUtil.execute("DELETE FROM Payment WHERE Payment_id = ?", paymentIdText);
    }

    public List<Payment> getAll() throws SQLException {
        /*String sql = "SELECT * FROM Payment";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

         */

       ResultSet resultSet = SQLUtil.execute("SELECT * FROM Payment");


        List<Payment> paymentList = new ArrayList<>();
        while (resultSet.next()) {
            String paymentId = resultSet.getString("Payment_id");
            String date = resultSet.getString("Date");
            String method = resultSet.getString("Method");
            String amount = resultSet.getString("Amount");
            String bookingId = resultSet.getString("Booking_id");

            Payment payment = new Payment(paymentId, date, method, amount, bookingId);
            paymentList.add(payment);
        }
        return paymentList;


    }

    @Override
    public List<String> getIds() throws SQLException {
        return List.of();
    }

}
