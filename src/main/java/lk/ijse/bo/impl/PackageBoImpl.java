package lk.ijse.bo.impl;

import lk.ijse.Database.DBConnection;
import lk.ijse.bo.PackageBO;
import lk.ijse.dao.PackageDao;
import lk.ijse.dao.custom.DAOFactory;
import lk.ijse.dao.custom.DAOTypes;
import lk.ijse.dto.PackageDTO;
import lk.ijse.entity.Package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackageBoImpl implements PackageBO {

    PackageDao packageDao = (PackageDao) DAOFactory.getDaoFactory().getDAO(DAOTypes.PACKAGE);
    public  boolean savePackage(PackageDTO Package) throws SQLException {
//        String sql = "INSERT INTO Package VALUES (?,?,?,?)";
//
//        Connection connection = DBConnection.getInstance().getConnection();
//        ;
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, Package.getPackageId());
//        pstm.setObject(2, Package.getName());
//        pstm.setObject(3, Package.getType());
//        pstm.setObject(4, Package.getPrice());
//
//        return pstm.executeUpdate() > 0;

        return  packageDao.Save(new Package(Package.getPackageId(),Package.getName(),Package.getType(),Package.getPrice()));

    }

    public  boolean updatePackage(PackageDTO Package) throws SQLException {
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
        return  packageDao.update(new Package(Package.getPackageId(),Package.getName(),Package.getType(),Package.getPrice()));
    }

    public  boolean deletePackage(String PackageId) throws SQLException {
        /*
        String sql = "DELETE FROM Package WHERE Package_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, PackageId);

        return pstm.executeUpdate() > 0;

         */
        return  packageDao.delete(PackageId);
    }

    public  PackageDTO searchPackageById(String packageID) throws SQLException {
        /*
        String sql = "SELECT * FROM Package WHERE Package_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, packageID); // Use setString instead of setObject for String parameters

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String packageId = resultSet.getString(1);
            String packageName = resultSet.getString(2);
            String description = resultSet.getString(3);
            double price = resultSet.getDouble(4); // Directly retrieve as double

            // Use the retrieved values to create a new Package object
            PackageDTO pkg = new PackageDTO(packageId, packageName, description, price);

            return pkg;
        } else {
            return null;
        }

         */
        Package search = packageDao.search(packageID);
        return new PackageDTO(search.getPackageId(),search.getName(),search.getType(),search.getPrice());
    }

    public List<PackageDTO> getAllPackage() throws SQLException {
        /*
        String  sql = "SELECT * FROM Package";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        List<PackageDTO> cuList = new ArrayList<>();
        while (resultSet.next()){
            String packageId = resultSet.getString(1);
            String packageName = resultSet.getString(2);
            String description = resultSet.getString(3);
            double price = Double.parseDouble(resultSet.getString(4));

            PackageDTO Package = new PackageDTO(packageId, packageName, description, price);
            cuList.add(Package);
        }
        return cuList;

         */
        List<PackageDTO> paList = new ArrayList<>();
        List<Package> packagee = packageDao.getAll();
        for (Package packages : packagee){
            PackageDTO packageDTO = new PackageDTO(packages.getPackageId(),
                    packages.getName(),packages.getType(),packages.getPrice());
            paList.add(packageDTO);
        }
        return paList;

    }



    public  List<String> getPackageId() throws SQLException {
        /*
        String sql = "SELECT Package_id FROM Package";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        List<String> mtIDList = new ArrayList<>();
        while (resultSet.next()){
            mtIDList.add(resultSet.getString(1));
        }
        return mtIDList;

         */
        return packageDao.getIds();
    }
}
