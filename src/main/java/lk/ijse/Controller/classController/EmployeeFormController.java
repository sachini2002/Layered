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
import lk.ijse.bo.EmployeeBo;
import lk.ijse.bo.RoomBO;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.tm.EmployeeTm;
import lk.ijse.dao.impl.EmployeeDaoImpl;
import lk.ijse.dao.impl.RoomDaoImpl;
import lk.ijse.Util.Regex;
import lk.ijse.Util.TextFields;
import lk.ijse.entity.Employee;
import lk.ijse.entity.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeFormController {

    @FXML
    private Button Backem;

    @FXML
    private Button Clearem;

    @FXML
    private Button Deleteem;

    @FXML
    private Button Saveem;

    @FXML
    private Button Updateem;

    @FXML
    private TableView<EmployeeTm> TblEmployee;

    @FXML
    private ComboBox<String> cmbRoom;

    @FXML
    private TableColumn<?, ?> collAddress;

    @FXML
    private TableColumn<?, ?> collAvailability;

    @FXML
    private TableColumn<?, ?> collEmployeeId;

    @FXML
    private TableColumn<?, ?> collName;

    @FXML
    private TableColumn<?, ?> collSalary;

    @FXML
    private TableColumn<?, ?> collType;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAvailability;

    @FXML
    private TableColumn<?, ?> colRoomId;




    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtType;

    @FXML
    private Button anchorPane;

   // RoomDaoImpl RoomDaoImpl = new RoomDaoImpl();

   // EmployeeDaoImpl  EmployeeDaoImpl = new EmployeeDaoImpl();

    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBOTYpes(BOTypes.ROOM);
    EmployeeBo employeeBo = (EmployeeBo) BOFactory.getBoFactory().getBOTYpes(BOTypes.EMPLOYEE);

    @FXML
    void ClearOnAction(ActionEvent event) {
        clearFields();


    }
    private void clearFields() {
        txtEmployeeId.setText("");
        txtEmployeeName.setText("");
        txtAddress.setText("");
        txtSalary.setText("");
        txtType.setText("");
        txtAvailability.setText("");
        cmbRoom.setValue(null);


    }

    @FXML
    void DeleteOnAction(ActionEvent event) {

        try {
            String employeeId = txtEmployeeId.getText();
            boolean isDeleted = employeeBo.deleteEmployee(employeeId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted successfully!").show();
               loadAllEmployees();
                // Optionally, clear the form fields or update UI after deletion
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void SaveOnAction(ActionEvent event) {
        String employeeId = txtEmployeeId.getText();
        String employeeName = txtEmployeeName.getText();
        String employeeAddress = txtAddress.getText();
        String employeeSalaryText = txtSalary.getText();
        String employeeType = txtType.getText();
        String employeeAvailability = txtAvailability.getText();
        String rooID = String.valueOf(cmbRoom.getValue());

        if(isValied()) {
            if (employeeId.isEmpty() || employeeName.isEmpty() || employeeAddress.isEmpty() ||
                    employeeSalaryText.isEmpty() || employeeType.isEmpty() || employeeAvailability.isEmpty() || rooID.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please fill all fields").show();
                return;
            }
            EmployeeDTO employee = new EmployeeDTO(employeeId, employeeName, employeeAddress, employeeSalaryText, employeeType, employeeAvailability, rooID);

            try {
                boolean isSaved = employeeBo.saveEmployee(employee);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved successfully!").show();
                    loadAllEmployees();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Check your fields correctly !").show();
        }
    }



    private void setCellValueFactory() {
        collEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        collName.setCellValueFactory(new PropertyValueFactory<>("name"));
        collAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        collSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        collType.setCellValueFactory(new PropertyValueFactory<>("type"));
        collAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("Room_id"));


    }
    private void loadAllEmployees() {
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try {
            List<EmployeeDTO> employeeList = employeeBo.getAllEmployees();
            for(EmployeeDTO employee : employeeList){
                EmployeeTm empTm = new EmployeeTm(
                        employee.getEmployeeId(),
                        employee.getName(),
                        employee.getAddress(),
                        employee.getSalary(),
                        employee.getType(),
                        employee.getAvailability(),
                        employee.getRoom_id()
                );
                obList.add(empTm);
            }
            TblEmployee.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void UpdateOnAction(ActionEvent event) {
        String employeeId = txtEmployeeId.getText();
        String employeeName = txtEmployeeName.getText();
        String employeeAddress = txtAddress.getText();
        String employeeSalaryText = txtSalary.getText();
        String employeeType = txtType.getText();
        String employeeAvailability = txtAvailability.getText();
        String rooID = String.valueOf(cmbRoom.getValue());

        // Check if any of the input fields are empty
        if (employeeId.isEmpty() || employeeName.isEmpty() || employeeAddress.isEmpty() ||
                employeeSalaryText.isEmpty() || employeeType.isEmpty() || employeeAvailability.isEmpty() || rooID.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all fields").show();
            return; // Exit the method if any field is empty
        }

        // Parse the salary to double

        // Create the employee object
        EmployeeDTO employee = new EmployeeDTO(employeeId, employeeName, employeeAddress, employeeSalaryText, employeeType, employeeAvailability, rooID);

        try {
            boolean isUpdated = employeeBo.updateEmployee(employee);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated successfully!").show();
               loadAllEmployees();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {

        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/View/Dashboard_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage =(Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();

    }

    @FXML
    void cmbRoomOnAction(ActionEvent event) {

        String Roomid = cmbRoom.getValue();

        try {
            RoomDTO room = roomBO.searchRoomById(Roomid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void initialize(){
        setCellValueFactory();
        loadAllEmployees();
        //loadAllRoom();
        getRoomId();
    }
    private void getRoomId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> rIDList = roomBO.getIDs();

            for (String materialId : rIDList) {
                obList.add(materialId);
            }
            cmbRoom.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtsearchOnAction(ActionEvent event) {

    }

    public void EmployeeIDOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.EmployeeID,txtEmployeeId);
    }

    public void TypeOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.EmpType,txtType);
    }

    public void SalaryOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DOUBLE,txtSalary);
    }

    public void NameOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.NAME,txtEmployeeName);
    }

    public void AvailabilityOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.EmpAva,txtAvailability);
    }

    public void AddressOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.ADDRESS,txtAddress);
    }

    private boolean isValied() {
        if (!Regex.setTextColor(TextFields.EmployeeID,txtEmployeeId)) return false;
        if (!Regex.setTextColor(TextFields.EmpType,txtType)) return false;
        if (!Regex.setTextColor(TextFields.DOUBLE,txtSalary)) return false;
        if (!Regex.setTextColor(TextFields.NAME,txtEmployeeName)) return false;
        if (!Regex.setTextColor(TextFields.EmpAva,txtAvailability)) return false;
        if (!Regex.setTextColor(TextFields.ADDRESS,txtAddress)) return false;
        return true;
    }
}
