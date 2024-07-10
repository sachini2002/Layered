package lk.ijse.dao.impl;

import lk.ijse.Database.DBConnection;
import lk.ijse.dao.PackageDao;
import lk.ijse.dao.custom.SQLUtil;
import lk.ijse.entity.Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackageDaoImpl implements PackageDao<Package> {


    public  boolean Save(Package Package) throws SQLException {
        /*
        String sql = "INSERT INTO Package VALUES (?,?,?,?)";

        Connection connection = DBConnection.getInstance().getConnection();
        ;
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, Package.getPackageId());
        pstm.setObject(2, Package.getName());
        pstm.setObject(3, Package.getType());
        pstm.setObject(4, Package.getPrice());

        return pstm.executeUpdate() > 0;

         */
        return  SQLUtil.execute("INSERT INTO Package VALUES (?,?,?,?)",Package.getPackageId(),
                Package.getName(),Package.getType(),Package.getPrice());
    }

    public  boolean update(Package Package) throws SQLException {
        /*
        String sql = "UPDATE Package SET Name = ?, Type = ?, Price = ? WHERE Package_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, Package.getName());
        pstm.setObject(2, Package.getType());
        pstm.setObject(3, Package.getPrice());
        pstm.setObject(4, Package.getPackageId());

        return pstm.executeUpdate() > 0;

         */
        return  SQLUtil.execute("UPDATE Package SET Name = ?, Type = ?, Price = ? WHERE Package_id = ?",Package.getName(),
                Package.getType(),Package.getPrice(),Package.getPackageId());
    }

    public  boolean delete(String PackageId) throws SQLException {
        /*
        String sql = "DELETE FROM Package WHERE Package_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, PackageId);

        return pstm.executeUpdate() > 0;

         */
        return  SQLUtil.execute("DELETE FROM Package WHERE Package_id = ?",PackageId);
    }

    public  Package search(String packageID) throws SQLException {
        /*
        String sql = "SELECT * FROM Package WHERE Package_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, packageID); // Use setString instead of setObject for String parameters

        ResultSet resultSet = pstm.executeQuery();

         */
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Package WHERE Package_id = ?",packageID);
        if (resultSet.next()) {
            String packageId = resultSet.getString(1);
            String packageName = resultSet.getString(2);
            String description = resultSet.getString(3);
            double price = resultSet.getDouble(4); // Directly retrieve as double

            // Use the retrieved values to create a new Package object
            Package pkg = new Package(packageId, packageName, description, price);

            return pkg;
        } else {
            return null;
        }
    }

    public  List<Package> getAll() throws SQLException {
        /*
        String  sql = "SELECT * FROM Package";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

         */
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Package");

        List<Package> cuList = new ArrayList<>();
        while (resultSet.next()){
            String packageId = resultSet.getString(1);
            String packageName = resultSet.getString(2);
            String description = resultSet.getString(3);
            double price = Double.parseDouble(resultSet.getString(4));

            Package Package = new Package(packageId, packageName, description, price);
            cuList.add(Package);
        }
        return cuList;

    }



    public  List<String> getIds() throws SQLException {
        /*
        String sql = "SELECT Package_id FROM Package";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

         */
        ResultSet resultSet = SQLUtil.execute("SELECT Package_id FROM Package");

        List<String> mtIDList = new ArrayList<>();
        while (resultSet.next()){
            mtIDList.add(resultSet.getString(1));
        }
        return mtIDList;
    }
}
