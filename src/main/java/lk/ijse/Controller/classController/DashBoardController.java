package lk.ijse.Controller.classController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardController {

    @FXML
    private Button Customer;

    @FXML
    private Button Dash;

    @FXML
    private Button Employee;

    @FXML
    private Button Package;

    @FXML
    private Button Payment;

    @FXML
    private Button Room;


    @FXML
    private AnchorPane CenterPane;

    @FXML
    private Button BackDash;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private Button bookingroon;

    @FXML
    void CutomerLogin(ActionEvent event) throws IOException {
        AnchorPane CustomerPane = FXMLLoader.load(this.getClass().getResource("/View/Customer_form.fxml"));
        CenterPane.getChildren().clear();
        CenterPane.getChildren().add(CustomerPane);

    }

    @FXML
    void EmployeeLogin(ActionEvent event) throws IOException {
        AnchorPane EmployeePane = FXMLLoader.load(this.getClass().getResource("/View/Employee_form.fxml"));
        CenterPane.getChildren().clear();
        CenterPane.getChildren().add(EmployeePane);

    }

    @FXML
    void RoomLogin(ActionEvent event) throws IOException {
        AnchorPane RoomPane = FXMLLoader.load(this.getClass().getResource("/View/Room_form.fxml"));
        CenterPane.getChildren().clear();
        CenterPane.getChildren().add(RoomPane);
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/View/Loging_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage =(Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();

    }

    @FXML
    void ordersLogin(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/View/Orders_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage =(Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    @FXML
    void paymentLogin(ActionEvent event) throws IOException {
        AnchorPane PaymentPane = FXMLLoader.load(this.getClass().getResource("/View/Payment_form.fxml"));
        CenterPane.getChildren().clear();
        CenterPane.getChildren().add(PaymentPane);

    }

    public void PackageLogin(ActionEvent actionEvent) throws IOException {
        AnchorPane PackagePane = FXMLLoader.load(this.getClass().getResource("/View/Package_form.fxml"));
        CenterPane.getChildren().clear();
        CenterPane.getChildren().add(PackagePane);

    }

    public void BookingLogin(ActionEvent actionEvent) throws IOException {
        AnchorPane BookingPane = FXMLLoader.load(this.getClass().getResource("/View/Booking_form.fxml"));
        CenterPane.getChildren().clear();
        CenterPane.getChildren().add(BookingPane);

    }

    public void DashOnControl(ActionEvent actionEvent) throws IOException {
        AnchorPane BookingPane = FXMLLoader.load(this.getClass().getResource("/View/Dashboard1.fxml"));
        CenterPane.getChildren().clear();
        CenterPane.getChildren().add(BookingPane);

    }
}
