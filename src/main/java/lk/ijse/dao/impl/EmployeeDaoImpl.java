package lk.ijse.dao.impl;


import lk.ijse.Database.DBConnection;
import lk.ijse.dao.EmployeeDao;
import lk.ijse.dao.custom.SQLUtil;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {


    public  boolean Save(Employee employee) throws SQLException {
        /*
        String sql = "INSERT INTO Employee VALUES(?,?,?,?,?,?,?)";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, employee.getEmployeeId());
        pstm.setObject(2, employee.getName());
        pstm.setObject(3, employee.getAddress());
        pstm.setObject(4, employee.getSalary());
        pstm.setObject(5, employee.getType());
        pstm.setObject(6, employee.getAvailability());
        pstm.setObject(7, employee.getRoom_id());
        pstm.executeUpdate() > 0

         */
        boolean b = SQLUtil.execute("INSERT INTO Employee VALUES(?,?,?,?,?,?,?)",employee.getEmployeeId(),
                employee.getName(),employee.getAddress(),employee.getSalary(),employee.getType(),employee.getAvailability(),
                employee.getRoom_id());

        if (b) {
            return true;
        } else {
            return false;
        }
    }

    public  boolean update(Employee employee) throws SQLException {
        /*
        String sql = "UPDATE Employee SET Name = ?, Address = ?, Salary = ?, Type = ?, availability = ?, Room_id = ? WHERE Employee_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, employee.getName());
        pstm.setObject(2, employee.getAddress());
        pstm.setObject(3, employee.getSalary());
        pstm.setObject(4, employee.getType());
        pstm.setObject(5, employee.getAvailability());
        pstm.setObject(6, employee.getRoom_id());
        pstm.setObject(7, employee.getEmployeeId());

        return pstm.executeUpdate() > 0;

         */
        return  SQLUtil.execute("UPDATE Employee SET Name = ?, Address = ?, Salary = ?, Type = ?, availability = ?, Room_id = ? WHERE Employee_id = ?",
                employee.getName(),employee.getAddress(),employee.getSalary(),employee.getType(),employee.getAvailability(),employee.getRoom_id(),
                employee.getEmployeeId());
    }



    public  boolean delete(String employeeId) throws SQLException {
        /*
        String sql = "DELETE FROM Employee WHERE Employee_id = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, employeeId);
        int rowsAffected = pstm.executeUpdate();
        return rowsAffected > 0;

         */
        return  SQLUtil.execute("DELETE FROM Employee WHERE Employee_id = ?",employeeId);
    }

    public  List<Employee> getAll() throws SQLException {
        /*
        String sql = "SELECT * FROM Employee";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

         */
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Employee");

        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()) {
            String employeeId = resultSet.getString(1);
            String employeeName = resultSet.getString(2);
            String employeeAddress = resultSet.getString(3);
            String employeeSalary = resultSet.getString(4);
            String employeeType = resultSet.getString(5);
            String employeeAvailability = resultSet.getString(6);
            String rooID = resultSet.getString(7);


            Employee employee = new Employee(employeeId, employeeName, employeeAddress, employeeSalary, employeeType, employeeAvailability, rooID);
            employeeList.add(employee);
        }
        return employeeList;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }
    @Override
    public Employee search(String Id) throws SQLException {
        return null;
    }
}
