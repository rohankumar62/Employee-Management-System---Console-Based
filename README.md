# Employee Management System - Spring Boot Console Application

A comprehensive console-based Employee Management System built with Spring Boot and Oracle Database, featuring full CRUD operations, department management, salary processing, and employee record management with RESTful architecture.

## 🚀 Features

- **Employee Management**: Add, view, update, and delete employee records
- **Department Management**: Organize employees by departments
- **Salary Management**: Track and update employee salaries
- **Search Functionality**: Find employees by ID, name, department, or designation
- **Attendance Tracking**: Monitor employee attendance records
- **Role-Based Access**: Different access levels for HR and employees
- **Report Generation**: Generate employee reports and statistics
- **Spring Boot Console Runner**: Interactive console-based interface
- **Database Persistence**: All data stored in Oracle Database with JPA/Hibernate

## 🛠️ Technologies Used

- **Framework**: Spring Boot 3.x
- **Programming Language**: Java 17+
- **Database**: Oracle SQL
- **ORM**: Hibernate/JPA
- **Build Tool**: Maven
- **Additional Libraries**: Spring Data JPA, Lombok, Bean Validation
- **Tools**: IntelliJ IDEA, Oracle SQL Developer

## 📋 Prerequisites

Before running this application, ensure you have:

- Java Development Kit (JDK) 17 or higher
- Oracle Database 11g or higher
- Maven 3.6+
- IDE (IntelliJ IDEA or Eclipse)

## 📦 Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/rohankumar62/Employee-Management-System---Console-Based.git
cd Employee-Management-System---Console-Based
```

### 2. Database Setup

Create the database schema in Oracle:

```sql
-- Employees Table
CREATE TABLE employees (
    employee_id NUMBER PRIMARY KEY,
    first_name VARCHAR2(50) NOT NULL,
    last_name VARCHAR2(50) NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    phone VARCHAR2(15),
    date_of_birth DATE,
    hire_date DATE NOT NULL,
    department_id NUMBER,
    designation VARCHAR2(50),
    salary NUMBER(10,2),
    manager_id NUMBER,
    status VARCHAR2(20) DEFAULT 'ACTIVE',
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP,
    CONSTRAINT fk_department FOREIGN KEY (department_id) REFERENCES departments(department_id),
    CONSTRAINT fk_manager FOREIGN KEY (manager_id) REFERENCES employees(employee_id)
);

-- Departments Table
CREATE TABLE departments (
    department_id NUMBER PRIMARY KEY,
    department_name VARCHAR2(100) NOT NULL,
    location VARCHAR2(100),
    manager_id NUMBER,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Attendance Table
CREATE TABLE attendance (
    attendance_id NUMBER PRIMARY KEY,
    employee_id NUMBER NOT NULL,
    attendance_date DATE NOT NULL,
    check_in_time TIMESTAMP,
    check_out_time TIMESTAMP,
    status VARCHAR2(20),
    CONSTRAINT fk_emp_attendance FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

-- Create Sequences
CREATE SEQUENCE employee_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE department_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE attendance_seq START WITH 1 INCREMENT BY 1;
```

### 3. Configure Database Connection

Update `application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.Oracle12cDialect
spring.jpa.properties.hibernate.format_sql=true

# Application Configuration
spring.application.name=Employee-Management-System
logging.level.org.hibernate.SQL=DEBUG
logging.level.com.rohan.employeemanagement=INFO
```

### 4. Build and Run

```bash
# Using Maven
mvn clean install
mvn spring-boot:run

# Or run the JAR
java -jar target/employee-management-system-0.0.1-SNAPSHOT.jar
```

## 💻 Usage

### Main Console Menu

```
========================================
   EMPLOYEE MANAGEMENT SYSTEM
   Powered by Spring Boot
========================================
1. Employee Management
2. Department Management
3. Attendance Management
4. Salary Management
5. Reports & Statistics
6. Search Employee
7. Exit
========================================
Enter your choice:
```

### Employee Management Menu

```
========================================
      EMPLOYEE MANAGEMENT
========================================
1. Add New Employee
2. View All Employees
3. View Employee by ID
4. Update Employee
5. Delete Employee
6. Back to Main Menu
========================================
Enter your choice:
```

### Sample Operations

**Adding an Employee:**
```
Enter First Name: Rohan
Enter Last Name: Kumar
Enter Email: rohan.kumar@company.com
Enter Phone: +91-9876543210
Enter Date of Birth (YYYY-MM-DD): 1995-05-15
Enter Hire Date (YYYY-MM-DD): 2024-01-15
Enter Department ID: 1
Enter Designation: Software Engineer
Enter Salary: 65000
Enter Manager ID (0 for none): 5
✓ Employee added successfully! Employee ID: 101
```

**Viewing Employees:**
```
========================================
         ALL EMPLOYEES
========================================
ID: 101
Name: Rohan Kumar
Email: rohan.kumar@company.com
Phone: +91-9876543210
Department: IT Department
Designation: Software Engineer
Salary: ₹65,000.00
Hire Date: 2024-01-15
Status: ACTIVE
========================================
ID: 102
Name: Priya Sharma
Email: priya.sharma@company.com
...
========================================
Total Employees: 25
========================================
```

## 📂 Project Structure

```
Employee-Management-System---Console-Based/
│
├── src/main/java/com/rohan/employeemanagement/
│   ├── EmployeeManagementApplication.java   # Main Spring Boot class
│   ├── ConsoleRunner.java                   # CommandLineRunner
│   │
│   ├── model/
│   │   ├── Employee.java                    # Employee Entity
│   │   ├── Department.java                  # Department Entity
│   │   └── Attendance.java                  # Attendance Entity
│   │
│   ├── repository/
│   │   ├── EmployeeRepository.java          # Employee Repository
│   │   ├── DepartmentRepository.java        # Department Repository
│   │   └── AttendanceRepository.java        # Attendance Repository
│   │
│   ├── service/
│   │   ├── EmployeeService.java             # Employee Service Interface
│   │   ├── EmployeeServiceImpl.java         # Service Implementation
│   │   ├── DepartmentService.java           # Department Service
│   │   ├── AttendanceService.java           # Attendance Service
│   │   └── ReportService.java               # Report Generation Service
│   │
│   ├── dto/
│   │   ├── EmployeeDTO.java                 # Data Transfer Object
│   │   └── DepartmentDTO.java               # Department DTO
│   │
│   ├── exception/
│   │   ├── EmployeeNotFoundException.java   # Custom Exception
│   │   └── GlobalExceptionHandler.java      # Exception Handler
│   │
│   └── util/
│       ├── ConsoleUtil.java                 # Console utilities
│       ├── DateUtil.java                    # Date formatting
│       └── ValidationUtil.java              # Input validation
│
├── src/main/resources/
│   ├── application.properties               # Configuration
│   ├── data.sql                            # Sample data
│   └── schema.sql                          # Database schema
│
├── pom.xml                                  # Maven dependencies
└── README.md
```

## 🔧 Spring Boot Features Used

- **Spring Boot Starter**: Auto-configuration and embedded server
- **CommandLineRunner**: Interactive console interface
- **Spring Data JPA**: Repository pattern with custom queries
- **Dependency Injection**: @Autowired and constructor injection
- **Transaction Management**: @Transactional for data consistency
- **Exception Handling**: Custom exceptions and handlers
- **Lombok**: @Data, @NoArgsConstructor, @AllArgsConstructor
- **Bean Validation**: @Valid, @NotNull, @Email annotations
- **Spring Profiles**: Development and production configurations

## 🗄️ Key Components

### 1. Employee Entity (JPA)
```java
@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", allocationSize = 1)
    private Long employeeId;
    
    @Column(nullable = false, length = 50)
    private String firstName;
    
    @Column(nullable = false, length = 50)
    private String lastName;
    
    @Column(unique = true, nullable = false)
    @Email
    private String email;
    
    private String phone;
    
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date hireDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    
    private String designation;
    
    @Column(precision = 10, scale = 2)
    private Double salary;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;
    
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;
    
    @Column(updatable = false)
    private LocalDateTime createdDate;
    
    private LocalDateTime updatedDate;
    
    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
        if (status == null) status = EmployeeStatus.ACTIVE;
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDateTime.now();
    }
}
```

### 2. Repository Layer
```java
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    List<Employee> findByDepartment(Department department);
    
    List<Employee> findByDesignation(String designation);
    
    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE %:name% OR e.lastName LIKE %:name%")
    List<Employee> searchByName(@Param("name") String name);
    
    @Query("SELECT e FROM Employee e WHERE e.status = :status")
    List<Employee> findByStatus(@Param("status") EmployeeStatus status);
    
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.department.departmentId = :deptId")
    Long countByDepartment(@Param("deptId") Long deptId);
    
    Optional<Employee> findByEmail(String email);
}
```

### 3. Service Layer
```java
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Override
    public Employee addEmployee(Employee employee) {
        validateEmployee(employee);
        return employeeRepository.save(employee);
    }
    
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
    }
    
    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = getEmployeeById(id);
        updateEmployeeDetails(existingEmployee, employee);
        return employeeRepository.save(existingEmployee);
    }
    
    @Override
    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employee.setStatus(EmployeeStatus.INACTIVE);
        employeeRepository.save(employee);
    }
    
    private void validateEmployee(Employee employee) {
        if (employee.getEmail() != null && 
            employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
    }
}
```

### 4. Console Runner
```java
@Component
public class ConsoleRunner implements CommandLineRunner {
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private DepartmentService departmentService;
    
    private Scanner scanner = new Scanner(System.in);
    
    @Override
    public void run(String... args) {
        System.out.println("Starting Employee Management System...");
        displayMainMenu();
    }
    
    private void displayMainMenu() {
        while (true) {
            printMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1 -> employeeManagementMenu();
                case 2 -> departmentManagementMenu();
                case 3 -> attendanceManagementMenu();
                case 4 -> salaryManagementMenu();
                case 5 -> generateReports();
                case 6 -> searchEmployee();
                case 7 -> {
                    System.out.println("Thank you for using EMS. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
```

## 📊 Maven Dependencies

```xml
<dependencies>
    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- Oracle JDBC Driver -->
    <dependency>
        <groupId>com.oracle.database.jdbc</groupId>
        <artifactId>ojdbc8</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    
    <!-- Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    
    <!-- Spring Boot Starter Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## 🔐 Security & Best Practices

- Email validation and uniqueness constraint
- Input validation using Bean Validation annotations
- Soft delete implementation (status change instead of hard delete)
- Transaction management for data consistency
- Custom exception handling
- Proper entity relationships (ManyToOne, OneToMany)
- Audit fields (createdDate, updatedDate)
- Password encryption (if authentication added)

## 📈 Reports & Statistics

The system generates various reports:
- Total employees by department
- Salary statistics (min, max, average)
- Attendance summary
- Active vs inactive employees
- New hires in a date range
- Employees by designation

## 🎯 Future Enhancements

- [ ] Add REST API endpoints
- [ ] Implement Spring Security for authentication
- [ ] Add leave management module
- [ ] Implement payroll processing
- [ ] Add performance evaluation module
- [ ] Export reports to PDF/Excel
- [ ] Email notifications for important events
- [ ] Add dashboard with charts
- [ ] Create web UI with React or Thymeleaf
- [ ] Implement caching with Redis
- [ ] Add API documentation with Swagger

## 🧪 Testing

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=EmployeeServiceTest

# Run with coverage
mvn clean test jacoco:report
```

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is open source and available under the [MIT License](LICENSE).

## 👤 Author

**Rohan Kumar**
- GitHub: [@rohankumar62](https://github.com/rohankumar62)
- LinkedIn: [rohan62](https://linkedin.com/in/rohan62)
- Email: rohankumarengineer@gmail.com
- Phone: +91 620-368-6682

## 🙏 Acknowledgments

- Naresh i Technologies for comprehensive Spring Boot training
- Spring Boot and Spring Data JPA documentation
- Oracle Database documentation
- Java and Spring community for best practices
- Open source contributors

## 📞 Support

For questions, issues, or feature requests:
- Open an issue on GitHub
- Contact via email: rohankumarengineer@gmail.com
- Connect on LinkedIn

---

⭐ If you found this project helpful, please give it a star!

## 🔗 Related Projects

- [Product Management System](https://github.com/rohankumar62/Product_management_system-console-base)
- [LinkedIn Clone](https://github.com/rohankumar62/linkedinclone)
- [Chit-Chat Messaging App](https://github.com/rohankumar62/chitchat-app)

---

**Made with ❤️ by Rohan Kumar**
