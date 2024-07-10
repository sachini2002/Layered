package lk.ijse.bo.impl;

import lk.ijse.Database.DBConnection;
import lk.ijse.bo.CutomerBO;
import lk.ijse.dao.CustomerDao;
import lk.ijse.dao.custom.DAOFactory;
import lk.ijse.dao.custom.DAOTypes;
import lk.ijse.dao.impl.CustomerDaoImpl;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CustomerBoImpl implements CutomerBO {

    CustomerDao customerDao = (CustomerDao) DAOFactory.getDaoFactory().getDAO(DAOTypes.CUTOMER);

    public  boolean saveCustomer(CustomerDTO customer) throws SQLException {
       /* String sql = "INSERT INTO Customer VALUES (?,?,?,?)";

        Connection connection = DBConnection.getInstance().getConnection();;
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,customer.getCustomerId());
        pstm.setObject(2,customer.getName());
        pstm.setObject(3,customer.getContact());
        pstm.setObject(4,customer.getAddress());


        return pstm.executeUpdate() > 0;

        */

       return customerDao.Save(new Customer(customer.getCustomerId(), customer.getName(), customer.getContact(), customer.getAddress()));
    }



    public  boolean updateCustomer(CustomerDTO customer) throws SQLException {
       /* String sql = "UPDATE Customer SET Name = ?,Contact = ?,Address = ?  WHERE customer_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1,customer.getName());
        pstm.setObject(2,customer.getContact());
        pstm.setObject(3,customer.getAddress());
        pstm.setObject(4,customer.getCustomerId());


        return pstm.executeUpdate() > 0;

        */

       return customerDao.update(new Customer(customer.getCustomerId(), customer.getName(), customer.getContact(), customer.getAddress()));
    }

    public  boolean deleteCustomers(String Customer_id) throws SQLException {
       /* String sql = "DELETE FROM Customer WHERE customer_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,Customer_id);

        return pstm.executeUpdate() > 0;

        */

        return customerDao.delete(Customer_id);
    }
    public  List<CustomerDTO> getAllCustomers() throws SQLException {
       /* String  sql = "SELECT * FROM Customer";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        List<CustomerDTO> cuList = new ArrayList<>();
        while (resultSet.next()){
            String customer_id = resultSet.getString(1);
            String customer_name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String contact_number = resultSet.getString(4);

            CustomerDTO customer = new CustomerDTO(customer_id,customer_name,address,contact_number);
            cuList.add(customer);
        }
        return cuList;

        */
        List<CustomerDTO> cuList = new ArrayList<>();
        List<Customer> customerList = customerDao.getAll();
        for (Customer customer : customerList) {

            cuList.add(new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getContact(), customer.getAddress()));
        }

        return cuList;

    }
    public  CustomerDTO searchCustomerById(String customerId) throws SQLException {
       /* String sql = "SELECT * FROM Customer WHERE customer_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,customerId);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            String customer_id = resultSet.getString(1);
            String customer_name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String contact_number = resultSet.getString(4);

            CustomerDTO customer = new CustomerDTO(customer_id,customer_name,address,contact_number);

            return customer;
        }
        else{
            return null;
        }

        */

        Customer search = customerDao.search(customerId);
        return new CustomerDTO(search.getCustomerId(), search.getName(), search.getContact(), search.getAddress());
    }

    public  List<String> getallcusId() throws SQLException {
        /*String sql = "SELECT Customer_id FROM Customer";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        List<String> BookingIdList = new ArrayList<>();
        while (resultSet.next()){
            BookingIdList.add(resultSet.getString(1));
        }
        return BookingIdList;


         */

       return customerDao.getIds();

    }


}
