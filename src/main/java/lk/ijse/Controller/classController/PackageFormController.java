package lk.ijse.Controller.classController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.BOTypes;
import lk.ijse.bo.PackageBO;
import lk.ijse.dto.PackageDTO;
import lk.ijse.dto.tm.PackageTm;
import lk.ijse.dao.impl.PackageDaoImpl;
import lk.ijse.Util.Regex;
import lk.ijse.Util.TextFields;
import lk.ijse.entity.Package;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PackageFormController {

    @FXML
    private Button Backpackage;

    @FXML
    private Button Clearpackage;

    @FXML
    private Button Deletepackage;

    @FXML
    private Button Savepackage;

    @FXML
    private Button Updatepackage;

    @FXML
    private AnchorPane rooNode;


    @FXML
    private TableView<PackageTm> TblPackage;

    @FXML
    private Button btnBack;

    @FXML
    private TableColumn<?, ?> collDescription;

    @FXML
    private TableColumn<?, ?> collId;

    @FXML
    private TableColumn<?, ?> collName;

    @FXML
    private TableColumn<?, ?> collPrice;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtPackageId;

    @FXML
    private TextField txtPackageName;

    @FXML
    private TextField txtPrice;

    //PackageDaoImpl PackageDaoImpl =  new PackageDaoImpl();

    PackageBO packageBO = (PackageBO) BOFactory.getBoFactory().getBOTYpes(BOTypes.PACKAGE);

    public void initialize(){
        setCellValueFactory();
        loadAllPackage();
    }


    private void loadAllPackage() {

        ObservableList<PackageTm> obList = FXCollections.observableArrayList();

        try {
            List<PackageDTO> PackagList = packageBO.getAllPackage();
            for(PackageDTO Package : PackagList){
                PackageTm ptm = new PackageTm(
                        Package.getPackageId(),
                        Package.getName(),
                        Package.getType(),
                        Package.getPrice()
                );
                obList.add(ptm);
            }
            TblPackage.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        collId.setCellValueFactory(new PropertyValueFactory<>("PackageId"));
        collName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        collDescription.setCellValueFactory(new PropertyValueFactory<>("Type"));
        collPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
    }

    @FXML
    void BackbtnOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/View/Dashboard_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage =(Stage) this.rooNode.getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    @FXML
    void ClearbtnOnAction(ActionEvent event) {
    clearFields();
    }

    private void clearFields() {
        txtPackageId.setText("");
        txtPackageName.setText("");
        txtDescription.setText("");
        txtPrice.setText("");
    }

    @FXML
    void DeletebtnOnAction(ActionEvent event) {
        String Package_id = txtPackageId.getText();

        try {
            boolean isDeleted = packageBO.deletePackage(Package_id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Package deleted!").show();
                loadAllPackage();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void SavebtnOnAction(ActionEvent event) {

        String PackageId = txtPackageId.getText();
        String PackageName = txtPackageName.getText();
        String Description = txtDescription.getText();
        double Price = Double.parseDouble(txtPrice.getText());

        if(isValied()) {
            PackageDTO Package = new PackageDTO(PackageId, PackageName, Description, Price);
            try {
                if ((PackageId.isEmpty()) || (PackageName.isEmpty()) || (Description.isEmpty())) {
                    new Alert(Alert.AlertType.INFORMATION, "Empty Files!Try again").show();
                } else {

                    boolean isSaved = packageBO.savePackage(Package);
                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Package saved successfully!").show();
                        loadAllPackage();
                    }
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Check your fields correctly !").show();
        }
    }

    @FXML
    void UpdatebtnOnAction(ActionEvent event) {
        String PackageId = txtPackageId.getText();
        String PackageName = txtPackageName.getText();
        String Description = txtDescription.getText();
        double Price = Double.parseDouble(txtPrice.getText());

        PackageDTO Package = new PackageDTO(PackageId, PackageName, Description, Price);
        try {
            boolean isUpdated = packageBO.updatePackage(Package);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Package updated successfully!").show();
                loadAllPackage();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        try {
            String packageID = txtPackageId.getText();

            PackageDTO pkg = packageBO.searchPackageById(packageID);
            if (pkg != null) {
                txtPackageId.setText(pkg.getPackageId());
                txtPackageName.setText(pkg.getName());
                txtDescription.setText(pkg.getType());
                txtPrice.setText(String.valueOf(pkg.getPrice()));
            } else {
                new Alert(Alert.AlertType.ERROR, "Package is not found!").show();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle or log the exception as needed
            new Alert(Alert.AlertType.ERROR, "An error occurred while searching for the package.").show();
        }
    }

    public void NameOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtPackageName);
    }

    public void PriceOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DOUBLE,txtPrice);
    }

    public void TypeOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.LETTERS_AND_NUMBERS,txtDescription);
    }

    public void PackageIdOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PackageID,txtPackageId);
    }

    private boolean isValied() {
        if (!Regex.setTextColor(TextFields.NAME,txtPackageName)) return false;
        if (!Regex.setTextColor(TextFields.DOUBLE,txtPrice)) return false;
        if (!Regex.setTextColor(TextFields.LETTERS_AND_NUMBERS,txtDescription)) return false;
        if (!Regex.setTextColor(TextFields.PackageID,txtPackageId)) return false;
        return true;
    }
}
