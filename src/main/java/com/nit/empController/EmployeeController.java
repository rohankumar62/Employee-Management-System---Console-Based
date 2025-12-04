package com.nit.empController;


import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nit.empModel.Employee;
import com.nit.empService.EmployeeService;

@Component
public class EmployeeController implements CommandLineRunner {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Employee Management System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee Salary");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    long id = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String dept = scanner.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();
                    employeeService.addEmployee(new Employee(id, name, dept, salary));
                    break;

                case 2:
                    employeeService.getAllEmployees().forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Enter Employee ID: ");
                    long updateId = scanner.nextLong();
                    System.out.print("Enter New Salary: ");
                    double newSalary = scanner.nextDouble();
                    employeeService.updateEmployeeSalary(updateId, newSalary);
                    break;

                case 4:
                    System.out.print("Enter Employee ID: ");
                    long deleteId = scanner.nextLong();
                    employeeService.deleteEmployee(deleteId);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
