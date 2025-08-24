import java.util.ArrayList;
import java.util.List;

class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String department;

    public Employee(String employeeId, String firstName, String lastName, String position, String department) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.department = department;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public void update(String firstName, String lastName, String position, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.department = department;
    }
}

class EmployeeManager {
    List<Employee> employees = new ArrayList<>();

    public Employee getEmployeeById(String employeeId) {
        var employee = employees.stream()
                .filter(e -> e.getEmployeeId().equals(employeeId))
                .findFirst();
        if (employee.isPresent()) return employee.get();
        return null;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void updateEmployeeInformation(String employeeId, String firstName, String lastName, String position, String department) {
        var employee = getEmployeeById(employeeId);
        if (employee != null) employee.update(firstName, lastName, position, department);
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }
}

public class EmployeeManagementApplication {
    public static void main(String[] args) {
        // Create an EmployeeManager instance
        EmployeeManager manager = new EmployeeManager();

        // Add new employees to the system
        manager.addEmployee(new Employee("E001", "John", "Doe", "Software Engineer", "Engineering"));
        manager.addEmployee(new Employee("E002", "Jane", "Smith", "HR Manager", "Human Resources"));

        // Update employee information
        manager.updateEmployeeInformation("E001", "John", "Doe", "Senior Software Engineer", "Engineering");

        // Retrieve information about an employee by their ID
        Employee employee = manager.getEmployeeById("E001");
        System.out.println("Employee ID: " + employee.getEmployeeId());
        System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
        System.out.println("Position: " + employee.getPosition());
        System.out.println("Department: " + employee.getDepartment());

        // Retrieve a list of all employees in the system
        List<Employee> employees = manager.getAllEmployees();
        System.out.println("Employees in the System:");
        for (Employee emp : employees) {
            System.out.println(emp.getEmployeeId() + "\t" + emp.getFirstName() + " " + emp.getLastName() + "\t" + emp.getPosition() + "\t" + emp.getDepartment());
        }
    }
}
