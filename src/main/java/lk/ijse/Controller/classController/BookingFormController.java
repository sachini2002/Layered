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
import lk.ijse.Database.DBConnection;
import lk.ijse.bo.*;
import lk.ijse.dto.BookingDTO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.PackageDTO;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.tm.BookingTm;
import lk.ijse.dao.impl.BookingDaoImpl;
import lk.ijse.dao.impl.CustomerDaoImpl;
import lk.ijse.dao.impl.RoomDaoImpl;
import lk.ijse.Util.Regex;
import lk.ijse.Util.TextFields;
import lk.ijse.entity.Booking;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Room;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingFormController {

    @FXML
    private Button Savebooking;

    @FXML
    private TextField bookingId;

    @FXML
    private Button btnbooking;


    @FXML
    private TableColumn<?, ?> bookingcol;

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<String> cmbPackage;

    @FXML
    private ComboBox<String> cmbRoomId;

    @FXML
    private TableColumn<?, ?> customercol;

    @FXML
    private TableColumn<?, ?> packagecol;

    @FXML
    private TableColumn<?, ?> roomcol;

    @FXML
    private TableView<BookingTm> tblbooking;

    @FXML
    private AnchorPane rootNde;

    //PackageDaoImpl PackageDaoImpl =  new PackageDaoImpl();

    PackageBO packageBO = (PackageBO) BOFactory.getBoFactory().getBOTYpes(BOTypes.PACKAGE);

    BookingBo bookingBo = (BookingBo) BOFactory.getBoFactory().getBOTYpes(BOTypes.BOOKING);


    // CustomerDaoImpl CustomerDaoImpl = new CustomerDaoImpl();

    CutomerBO cutomerBO = (CutomerBO) BOFactory.getBoFactory().getBOTYpes(BOTypes.CUSTOMER);

    //RoomDaoImpl RoomDaoImpl = new RoomDaoImpl();

    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBOTYpes(BOTypes.ROOM);

    //BookingDaoImpl  BookingDaoImpl= new BookingDaoImpl();

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        String cusId = (String) cmbCustomerId.getValue();

        try {
            CustomerDTO customer = cutomerBO.searchCustomerById(cusId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        customercol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        bookingcol.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        roomcol.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        packagecol.setCellValueFactory(new PropertyValueFactory<>("packageId"));
    }

    private void loadAllCustomers() {
        ObservableList<BookingTm> obList = FXCollections.observableArrayList();

        try {
            List<BookingDTO> BookingList = bookingBo.getallBooking();
            for(BookingDTO booking : BookingList){
                BookingTm cusTm = new BookingTm(
                        booking.getBookingId(),
                        booking.getCustomerId(),
                        booking.getPackageId(),
                        booking.getRoomId()
                );
                obList.add(cusTm);
            }
            tblbooking.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCustomerId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> customerIdList = cutomerBO.getallcusId();

            for (String cusId : customerIdList) {
                obList.add(cusId);
            }
            cmbCustomerId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize(){
       setCellValueFactory();
        loadAllCustomers();
        getCustomerId();
        getRoomId();
        getPackageId();
    }
    @FXML
    void cmbPackageOnAction(ActionEvent event) {
        String package1 = (String) cmbPackage.getValue();

        try {
            PackageDTO Package = packageBO.searchPackageById(package1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void getPackageId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> packageIdList = packageBO.getPackageId();

            for (String paId : packageIdList) {
                obList.add(paId);
            }
            cmbPackage.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbRoomOnAction(ActionEvent event) {
        String room = (String) cmbRoomId.getValue();

        try {
            RoomDTO room1 = roomBO.searchRoomId(room);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void getRoomId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> RoomIdList = roomBO.getIDs();

            for (String roomId : RoomIdList) {
                obList.add(roomId);
            }
            cmbRoomId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void bookingOnAction(ActionEvent actionEvent) {
        String booking = bookingId.getText();
        String cutomer = cmbCustomerId.getValue(); // Assuming you're using a ComboBox for package selection
        String Package = cmbPackage.getValue(); // Assuming you're using a ComboBox for guest identity selection
        String room = cmbRoomId.getValue(); // Assuming you're using a ComboBox for payment selection

        if (isValied()) {
            if (booking.isEmpty() || cutomer == null || Package == null ||
                    room == null) {
                new Alert(Alert.AlertType.ERROR, "Please fill all fields").show();
                return;
            }

            BookingDTO booking1 = new BookingDTO(booking, cutomer, Package, room);

            try {
                boolean isSaved = bookingBo.saveBookingAndUpdate(booking, cutomer, Package, room);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Booking saved successfully!").show();
                    // Add any necessary actions after successful booking
                    loadAllCustomers();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Check your fields correctly !").show();
        }
    }

    public void backOnActionbooking(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/View/Dashboard_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage =(Stage) this.rootNde.getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    public void BookingIDOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.Bookigid,bookingId);
    }

    private boolean isValied() {
        if (!Regex.setTextColor(TextFields.Bookigid,bookingId)) return false;
        return true;
    }

    public void reportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/Book.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,Object> data = new HashMap<>();
        data.put("bookingID",bookingId.getText());


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }
}
