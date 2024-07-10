package lk.ijse.bo;


import lk.ijse.dto.EmployeeDTO;


import java.sql.SQLException;

import java.util.List;

public interface EmployeeBo extends SuperBO{
    public  boolean saveEmployee(EmployeeDTO employee) throws SQLException ;

    public  boolean updateEmployee(EmployeeDTO employee) throws SQLException ;

    public  boolean deleteEmployee(String employeeId) throws SQLException ;

    public List<EmployeeDTO> getAllEmployees() throws SQLException ;

}
