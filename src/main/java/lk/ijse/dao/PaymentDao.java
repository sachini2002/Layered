package lk.ijse.dao;

import lk.ijse.dao.custom.CrudDAO;
import lk.ijse.entity.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDao extends CrudDAO<Payment> {

    public boolean Save(Payment payment) throws SQLException;

    public Payment search(String bookiID);

    public boolean update(Payment payment) throws SQLException;

    public boolean delete(String paymentIdText) throws SQLException;

    public List<Payment> getAll() throws SQLException;


}
