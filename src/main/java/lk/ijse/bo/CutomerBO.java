package lk.ijse.bo;

import lk.ijse.Database.DBConnection;
import lk.ijse.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CutomerBO extends SuperBO {

    public  boolean saveCustomer(CustomerDTO customer) throws SQLException;

    public  boolean updateCustomer(CustomerDTO customer) throws SQLException;

    public  boolean deleteCustomers(String Customer_id) throws SQLException;

    public List<CustomerDTO> getAllCustomers() throws SQLException;

    public  CustomerDTO searchCustomerById(String customerId) throws SQLException;

    public  List<String> getallcusId() throws SQLException;

}
