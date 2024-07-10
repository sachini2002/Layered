package lk.ijse.dao;


import lk.ijse.dao.custom.CrudDAO;

import lk.ijse.entity.Employee;


import java.sql.SQLException;

import java.util.List;

public interface EmployeeDao extends CrudDAO<Employee> {

    public  boolean Save(Employee employee) throws SQLException ;

    public  boolean update(Employee employee) throws SQLException ;

    public  boolean delete(String employeeId) throws SQLException ;

    public List<Employee> getAll() throws SQLException ;
}
