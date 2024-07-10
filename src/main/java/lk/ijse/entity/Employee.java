package lk.ijse.entity;

public class Employee {
    private String employeeId;
    private String name;
    private String address;
    private String salary;
    private String type;
    private String availability;
    private String Room_id;

    public Employee(String employeeId, String name, String address, String salary, String type, String availability, String room_id) {
        this.employeeId = employeeId;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.type = type;
        this.availability = availability;
        this.Room_id = room_id;
    }

    // Getters and Setters
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getRoom_id() {
        return Room_id;
    }

    public void setRoom_id(String room_id) {
        Room_id = room_id;
    }

    // toString() method
    @Override
    public String toString() {
        return "EmployeeTm{" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary='" + salary + '\'' +
                ", type='" + type + '\'' +
                ", availability='" + availability + '\'' +
                ", Room_id='" + Room_id + '\'' +
                '}';
    }
}
