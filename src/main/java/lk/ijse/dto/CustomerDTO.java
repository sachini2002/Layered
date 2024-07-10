package lk.ijse.dto;

public class CustomerDTO {
    private String customerId;
    private String name;
    private String contact;
    private String address;

    // Constructors
    public CustomerDTO(String customerId, String name, String contact, String address) {
        this.customerId = customerId;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    // Default constructor
    public CustomerDTO() {
    }

    // Getters
    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }

    // Setters
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // toString method
    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
