package lk.ijse.bo.impl;

import lk.ijse.Database.DBConnection;
import lk.ijse.bo.PaymentBO;
import lk.ijse.dao.PaymentDao;
import lk.ijse.dao.custom.DAOFactory;
import lk.ijse.dao.custom.DAOTypes;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.entity.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBoImpl implements PaymentBO {

    PaymentDao paymentDao = (PaymentDao) DAOFactory.getDaoFactory().getDAO(DAOTypes.PAYMENT);

    public boolean savePayment(PaymentDTO payment) throws SQLException {

      return paymentDao.Save(new Payment(payment.getPaymentId(), payment.getDate(), payment.getMethod(), payment.getAmount(),payment.getBookingId()));
    }


    public PaymentDTO searchBookingId(String bookiID) {
        return null;
    }

    public boolean updatePayment(PaymentDTO payment) throws SQLException {

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

       return paymentDao.update(new Payment(payment.getPaymentId(), payment.getDate(), payment.getMethod(), payment.getAmount(),payment.getBookingId()));
    }

    public boolean deletePayment(String paymentIdText) throws SQLException {
       /* String sql = "DELETE FROM Payment WHERE Payment_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, paymentIdText);

        return pstm.executeUpdate() > 0;

        */

       return paymentDao.delete(paymentIdText);
    }

    public List<PaymentDTO> getAllPayments() throws SQLException {
        /*String sql = "SELECT * FROM Payment";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        List<PaymentDTO> paymentList = new ArrayList<>();
        while (resultSet.next()) {
            String paymentId = resultSet.getString("Payment_id");
            String date = resultSet.getString("Date");
            String method = resultSet.getString("Method");
            String amount = resultSet.getString("Amount");
            String bookingId = resultSet.getString("Booking_id");

            PaymentDTO payment = new PaymentDTO(paymentId, date, method, amount, bookingId);
            paymentList.add(payment);
        }
        return paymentList;

         */

        List<PaymentDTO> paymentList = new ArrayList<>();
        List<Payment> payments = paymentDao.getAll();
        for (Payment payment : payments) {
            PaymentDTO paymentDTO = new PaymentDTO(payment.getPaymentId(), payment.getDate(), payment.getMethod(), payment.getAmount(),payment.getBookingId());
            paymentList.add(paymentDTO);
        }
        return paymentList;
    }

}
