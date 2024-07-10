package lk.ijse.dao;

import lk.ijse.dao.custom.CrudDAO;
import lk.ijse.entity.Package;

import java.sql.SQLException;
import java.util.List;

public interface PackageDao<P> extends CrudDAO<Package> {
    public  boolean Save(Package Package) throws SQLException ;

    public  boolean update(Package Package) throws SQLException ;

    public  boolean delete(String PackageId) throws SQLException ;

    public  Package search(String packageID) throws SQLException ;
    public List<Package> getAll() throws SQLException ;
    public  List<String> getIds() throws SQLException ;
}
