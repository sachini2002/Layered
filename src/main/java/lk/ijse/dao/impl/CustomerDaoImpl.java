package lk.ijse.dao.impl;

import lk.ijse.Database.DBConnection;
import lk.ijse.dao.CustomerDao;
import lk.ijse.dao.custom.SQLUtil;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
public  boolean Save(Customer customer) throws SQLException {
   /* String sql = "INSERT INTO Customer VALUES (?,?,?,?)";

    Connection connection = DBConnection.getInstance().getConnection();;
    PreparedStatement pstm = connection.prepareStatement(sql);
    pstm.setObject(1,customer.getCustomerId());
    pstm.setObject(2,customer.getName());
    pstm.setObject(3,customer.getContact());
    pstm.setObject(4,customer.getAddress());


    return pstm.executeUpdate() > 0;

    */

    return SQLUtil.execute("INSERT INTO Customer VALUES (?,?,?,?)", customer.getCustomerId(), customer.getName(), customer.getContact(), customer.getAddress());
    }

    public  boolean update(Customer customer) throws SQLException {
       /* String sql = "UPDATE Customer SET Name = ?,Contact = ?,Address = ?  WHERE customer_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1,customer.getName());
        pstm.setObject(2,customer.getContact());
        pstm.setObject(3,customer.getAddress());
        pstm.setObject(4,customer.getCustomerId());


        return pstm.executeUpdate() > 0;

        */

       return SQLUtil.execute("UPDATE Customer SET Name = ?,Contact = ?,Address = ?  WHERE customer_id = ?", customer.getName(), customer.getContact(), customer.getAddress(), customer.getCustomerId());

    }

    public  boolean delete(String Customer_id) throws SQLException {
       /* String sql = "DELETE FROM Customer WHERE customer_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,Customer_id);

        return pstm.executeUpdate() > 0;
    }
    public  List<Customer> getAll() throws SQLException {
        String  sql = "SELECT * FROM Customer";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        List<Customer> cuList = new ArrayList<>();
        while (resultSet.next()){
            String customer_id = resultSet.getString(1);
            String customer_name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String contact_number = resultSet.getString(4);

            Customer customer = new Customer(customer_id,customer_name,address,contact_number);
            cuList.add(customer);
        }
        return cuList;

        */

        return SQLUtil.execute("DELETE FROM Customer WHERE customer_id = ?", Customer_id);
    }

    @Override
    public List<Customer> getAll() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Customer");
        List<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            String id = resultSet.getString("customer_id");
            String name = resultSet.getString("name");
            String contact = resultSet.getString("contact");
            String adreess = resultSet.getString("address");

            Customer customer = new Customer(id, name, contact, adreess);
            customers.add(customer);


        }
        return customers;


    }

    public  Customer search(String customerId) throws SQLException {
       /* String sql = "SELECT * FROM Customer WHERE customer_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,customerId);

        ResultSet resultSet = pstm.executeQuery();

        */
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Customer WHERE customer_id = ?", customerId);

        if(resultSet.next()){
            String customer_id = resultSet.getString(1);
            String customer_name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String contact_number = resultSet.getString(4);

            Customer customer = new Customer(customer_id,customer_name,address,contact_number);

            return customer;
        }
        else{
            return null;
        }
    }

    public  List<String> getIds() throws SQLException {
      /*  String sql = "SELECT Customer_id FROM Customer";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

       */

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Customer");

        List<String> BookingIdList = new ArrayList<>();
        while (resultSet.next()){
            BookingIdList.add(resultSet.getString(1));
        }
        return BookingIdList;


    }
}

