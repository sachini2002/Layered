package lk.ijse.bo.impl;

import lk.ijse.Database.DBConnection;
import lk.ijse.bo.EmployeeBo;
import lk.ijse.dao.EmployeeDao;
import lk.ijse.dao.custom.DAOFactory;
import lk.ijse.dao.custom.DAOTypes;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBoImpl implements EmployeeBo {

    EmployeeDao employeeDao = (EmployeeDao) DAOFactory.getDaoFactory().getDAO(DAOTypes.EMPLOYEE);

    public  boolean saveEmployee(EmployeeDTO employee) throws SQLException {
        return  employeeDao.Save(new Employee(employee.getEmployeeId(),employee.getName(),employee.getAddress(),employee.getSalary(),employee.getType(),employee.getAvailability(),
                employee.getRoom_id()));

    }

    public  boolean updateEmployee(EmployeeDTO employee) throws SQLException {
       return  employeeDao.update(new Employee(employee.getEmployeeId(),employee.getName(),employee.getAddress(),employee.getSalary(),
               employee.getType(),employee.getAvailability(),employee.getRoom_id()));
    }

    public  boolean deleteEmployee(String employeeId) throws SQLException {
        return  employeeDao.delete(employeeId);
    }

    public List<EmployeeDTO> getAllEmployees() throws SQLException {
        List<EmployeeDTO> employeeList = new ArrayList<>();
        List<Employee> employees = employeeDao.getAll();
        for (Employee employee : employees){
            EmployeeDTO employeeDTO = new EmployeeDTO(employee.getEmployeeId(),employee.getName(),employee.getAddress(),employee.getSalary(),
                    employee.getType(),employee.getAvailability(),employee.getRoom_id());
            employeeList.add(employeeDTO);
        }
        return employeeList;
    }

}
