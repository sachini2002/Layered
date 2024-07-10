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
import lk.ijse.bo.BookingBo;
import lk.ijse.bo.PaymentBO;
import lk.ijse.dto.BookingDTO;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.dto.tm.PaymentTm;
import lk.ijse.dao.impl.BookingDaoImpl;
import lk.ijse.Util.Regex;
import lk.ijse.Util.TextFields;
import lk.ijse.entity.Booking;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PaymentFormController {

    @FXML
    private Button Backpay;

    @FXML
    private Button Clearpay;

    @FXML
    private Button Deletepay;

    @FXML
    private Button SavePay;

    @FXML
    private Button Updatepay;

    @FXML
    private TextField amount;

    @FXML
    private ComboBox<String> cmbBooking;

    @FXML
    private TableColumn<?, ?> colBid;

    @FXML
    private TableColumn<?, ?> colamount;

    @FXML
    private TableColumn<?, ?> coldate;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colmethod;

    @FXML
    private TextField date;

    @FXML
    private TextField method;

    @FXML
    private TableView<PaymentTm> tblBooking;

    @FXML
    private TextField txtPayId;

    @FXML
    private AnchorPane anchorpane;

   // PaymentDaoImpl PaymentDaoImpl = new PaymentDaoImpl();
  // BookingDaoImpl  BookingDaoImpl= new BookingDaoImpl();

    BookingBo bookingBo = (BookingBo) BOFactory.getBoFactory().getBOTYpes(BOTypes.BOOKING);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBOTYpes(BOTypes.PAYMENT);

    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/View/Dashboard_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.anchorpane.getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("Login Form");
        stage.centerOnScreen();

    }

    @FXML
    void clearOnAction(ActionEvent event) {
        clearFields();

    }



    @FXML
    void comboBookingOnAction(ActionEvent event) {

        String BookiID = cmbBooking.getValue();

        try {
            BookingDTO Booking = bookingBo.searchBookingById(BookiID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void initialize(){
        loadAllPayments();
        getBookingId();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colmethod.setCellValueFactory(new PropertyValueFactory<>("method"));
        colamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colBid.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
    }

    private void loadAllPayments() {
        ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

        try {
            List<PaymentDTO> paymentList = paymentBO.getAllPayments();
            for (PaymentDTO payment : paymentList) {
                PaymentTm paymentTm = new PaymentTm(
                        payment.getPaymentId(),
                        payment.getDate(),
                        payment.getMethod(),
                        payment.getAmount(),
                        payment.getBookingId()
                );
                obList.add(paymentTm);
            }
            tblBooking.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getBookingId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> BookingIdList = bookingBo.getAllBookigId();

            for (String BookingId : BookingIdList) {
                obList.add(BookingId);
            }
            cmbBooking.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        String paymentIdText = txtPayId.getText();

        try {
            boolean isDeleted = paymentBO.deletePayment(paymentIdText);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment deleted!").show();
                loadAllPayments();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    void clearFields() {
        // Clear the text fields
        txtPayId.clear();
        amount.clear();
        date.clear();
        method.clear();

        // Clear the ComboBox selection
        cmbBooking.getSelectionModel().clearSelection();
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        String id = txtPayId.getText();
        String Date = date.getText();
        String Method = method.getText();
        String Amount = amount.getText();
        String bookingId = String.valueOf(cmbBooking.getValue());

        if (isValied()) {
            PaymentDTO payment = new PaymentDTO(id, Date, Method, Amount, bookingId);

            try {
                if (id.isEmpty() || Date.isEmpty() || Method.isEmpty() || Amount.isEmpty() || bookingId.isEmpty()) {
                    new Alert(Alert.AlertType.INFORMATION, "Empty Fields! Try again").show();
                } else {
                    boolean isSaved = paymentBO.savePayment(payment);
                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Payment saved successfully!").show();
                        loadAllPayments();
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
    void updateOnAction(ActionEvent event) {
        String id = txtPayId.getText();
        String Date = date.getText();
        String Method = method.getText();
        String Amount = amount.getText();
        String bookingId = String.valueOf(cmbBooking.getValue());


            PaymentDTO payment = new PaymentDTO(id, Date, Method, Amount, bookingId);
            try {
                boolean isUpdated = paymentBO.updatePayment(payment);
                if(isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Payment updated successfully!").show();
                   loadAllPayments();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }


    public void PaymentIDOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PayId,txtPayId);
    }

    public void MethodOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.PayMethod,method);
    }

    public void DateOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DATE,date);
    }

    public void AmountOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DOUBLE,amount);
    }

    private boolean isValied() {
        if (!Regex.setTextColor(TextFields.PayId,txtPayId)) return false;
        if (!Regex.setTextColor(TextFields.PayMethod,method)) return false;
        if (!Regex.setTextColor(TextFields.DATE,date)) return false;
        if (!Regex.setTextColor(TextFields.DOUBLE,amount)) return false;
        return true;
    }
}
