package com.nit.empService;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.empDAO.EmployeeDAO;
import com.nit.empModel.Employee;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    public void addEmployee(Employee employee) {
        if ("IT".equalsIgnoreCase(employee.getDepartment())) {
            employee.setSalary(employee.getSalary() * 1.10);
        }
        employeeDAO.addEmployee(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public void updateEmployeeSalary(long id, double newSalary) {
        employeeDAO.updateEmployeeSalary(id, newSalary);
    }

    public void deleteEmployee(long id) {
        employeeDAO.deleteEmployee(id);
    }
}
