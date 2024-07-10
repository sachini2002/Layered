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
import lk.ijse.bo.CutomerBO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.tm.CustomerTm;
import lk.ijse.dao.impl.CustomerDaoImpl;
import lk.ijse.Util.Regex;
import lk.ijse.Util.TextFields;
import lk.ijse.entity.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {

    @FXML
    private Button Backcu;

    @FXML
    private Button Clearcu;

    @FXML
    private Button Deletecu;

    @FXML
    private Button Savecu;

    @FXML
    private Button Updatecu;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerTel;

    @FXML
    private AnchorPane rooNode;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableView<CustomerTm> tblCustomer;

   //CustomerDaoImpl CustomerDaoImpl=  new CustomerDaoImpl();

    CutomerBO cutomerBO = (CutomerBO) BOFactory.getBoFactory().getBOTYpes(BOTypes.CUSTOMER);


    public void initialize(){
        setCellValueFactory();
        loadAllCustomers();
    }

    private void loadAllCustomers() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<CustomerDTO> customerList = cutomerBO.getAllCustomers();
            for(CustomerDTO customer : customerList){
                CustomerTm cusTm = new CustomerTm(
                        customer.getCustomerId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getContact()
                );
                obList.add(cusTm);
            }
            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/View/Dashboard_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage =(Stage) this.rooNode.getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

        clearFields();

    }
    private void clearFields() {
        txtCustomerId.setText("");
        txtCustomerName.setText("");
        txtCustomerAddress.setText("");
        txtCustomerTel.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String customer_id = txtCustomerId.getText();

        try {
            boolean isDeleted = cutomerBO.deleteCustomers(customer_id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted!").show();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String customer_id = txtCustomerId.getText();
        String customer_name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();
        String contact_number = txtCustomerTel.getText();

        if (isValied()) {
            CustomerDTO customer = new CustomerDTO(customer_id, customer_name, address, contact_number);
            try {
                if ((customer_id.isEmpty()) || (customer_name.isEmpty()) || (address.isEmpty()) || (contact_number.isEmpty())) {
                    new Alert(Alert.AlertType.INFORMATION, "Empty Files!Try again").show();
                } else {

                    boolean isSaved = cutomerBO.saveCustomer(customer);
                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Customer saved successfully!").show();
                        loadAllCustomers();

                    }
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Check your fields correctly !").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String customer_id = txtCustomerId.getText();
        String customer_name = txtCustomerName.getText();
        String address = txtCustomerAddress.getText();
        String contact_number = txtCustomerTel.getText();

        CustomerDTO customer = new CustomerDTO(customer_id,customer_name,address,contact_number);
        try {
            boolean isUpdated = cutomerBO.updateCustomer(customer);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer updated successfully!").show();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        try {
            String cusID = txtCustomerId.getText();

            CustomerDTO pkg = cutomerBO.searchCustomerById(cusID);
            if (pkg != null) {
                txtCustomerId.setText(pkg.getCustomerId());
                txtCustomerName.setText(pkg.getName());
                txtCustomerAddress.setText(pkg.getAddress());
                txtCustomerTel.setText(pkg.getContact());
            } else {
                new Alert(Alert.AlertType.ERROR, "Package is not found!").show();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle or log the exception as needed
            new Alert(Alert.AlertType.ERROR, "An error occurred while searching for the package.").show();
        }
    }

    public void IDOnKeyReleased(KeyEvent keyEvent) {

    }

    public void NameOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtCustomerName);
    }

    public void ContactOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PHONE,txtCustomerTel);
    }

    public void AddressOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ADDRESS,txtCustomerAddress);
    }

    public boolean isValied(){
        if (!Regex.setTextColor(TextFields.ciddd,txtCustomerId)) return false;
        if (!Regex.setTextColor(TextFields.NAME,txtCustomerName)) return false;
        if (!Regex.setTextColor(TextFields.PHONE,txtCustomerTel)) return false;
        if (!Regex.setTextColor(TextFields.ADDRESS,txtCustomerAddress)) return false;
        return true;
    }

    public void OnkeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ciddd,txtCustomerId);

    }
}
