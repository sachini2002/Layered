package lk.ijse.Controller.classController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.AdminBo;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.BOTypes;
import lk.ijse.dto.AdminDTO;
import lk.ijse.dao.impl.AdminDaoImpl;
import lk.ijse.entity.Admin;

import java.io.IOException;
import java.sql.SQLException;

public class RegistrationFormController {



    @FXML
    private PasswordField Password;

    @FXML
    private TextField UserId;

    @FXML
    private TextField Email;

    @FXML
    private TextField UserName;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label diya;

    @FXML
    private Button registerButton;

    //AdminDaoImpl AdminDaoImpl = new AdminDaoImpl();

    AdminBo adminBo = (AdminBo) BOFactory.getBoFactory().getBOTYpes(BOTypes.ADMIN);
    public void RejisterOnAction(ActionEvent actionEvent) {
        String userId = UserId.getText();
        String userName = UserName.getText();
        String email = Email.getText();
        String password = Password.getText();

        try {
            if ((userId.isEmpty()) || (userName.isEmpty()) || (password.isEmpty()) || (email.isEmpty())) {
                new Alert(Alert.AlertType.INFORMATION, "Empty Files!Try again").show();
            }
            else {
                AdminDTO admin = new AdminDTO(userId, userName, email, password);
                boolean isSaved = adminBo.saveUsers(admin);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "User saved successfully!").show();

                }
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }

    public void LoginBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/View/Loging_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.anchorPane.getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("Login Form");
        stage.centerOnScreen();

    }
}

