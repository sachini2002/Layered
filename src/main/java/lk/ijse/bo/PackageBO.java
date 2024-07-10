package lk.ijse.bo;


import lk.ijse.dto.PackageDTO;


import java.sql.SQLException;

import java.util.List;

public interface PackageBO extends SuperBO {
    public  boolean savePackage(PackageDTO Package) throws SQLException ;
    public  boolean updatePackage(PackageDTO Package) throws SQLException ;
    public  boolean deletePackage(String PackageId) throws SQLException ;
    public  PackageDTO searchPackageById(String packageID) throws SQLException;
    public List<PackageDTO> getAllPackage() throws SQLException ;
    public  List<String> getPackageId() throws SQLException ;
}
