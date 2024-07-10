package lk.ijse.dto;

public class PackageDTO {
    private String packageId;
    private String name;
    private String type;
    private Double price;

    public PackageDTO(String packageId, String packageName, String description, String price) {}

    public PackageDTO(String packageId, String name, String type, Double price) {
        this.packageId = packageId;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Package{" +
                "packageId='" + packageId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
