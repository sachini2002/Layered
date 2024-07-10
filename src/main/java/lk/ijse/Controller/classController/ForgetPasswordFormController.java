/*
package lk.ijse.Controller.classController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Controller.classController.LogingPageController;
import lk.ijse.Repository.AdminRepo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.w3c.dom.Text;
import javafx.scene.input.MouseEvent;

public class ForgotPasswordFormController {
    @FXML
    private Button changePwButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField rePasswordField;

    public String tempUsername = LogingPageController.tempUsername;
    public void ChangeOnAction(ActionEvent event) {
        String newPassword = passwordField.getText();
        String confirmPassword = rePasswordField.getText();

        if(!newPassword.equals(confirmPassword)){
            new Alert(Alert.AlertType.ERROR,"Passwords are do not match! Try again").show();
        } else if((newPassword.isEmpty())&&(confirmPassword.isEmpty())) {
            new Alert(Alert.AlertType.INFORMATION, "Empty Fields!").show();
        }
        else{
            try {
                AdminRepo.updateUser(newPassword,tempUsername);
                passwordField.setText("");
                rePasswordField.setText("");
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }
    }

    public void BackOnAction(ActionEvent actionEvent) {

    }
}


 */

package lk.ijse.Controller.classController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.AdminBo;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.BOTypes;
import lk.ijse.dao.impl.AdminDaoImpl;

import java.io.IOException;
import java.sql.SQLException;

public class ForgetPasswordFormController {

    @FXML
    private Button ChangePw;

    @FXML
    private TextField Password;

    @FXML
    private TextField RePassword;

    @FXML
    private ImageView logo;

    @FXML
    private AnchorPane rootnode;

    public String tempUsername = LogingPageController.tempUsername;

    //AdminDaoImpl AdminDaoImpl = new AdminDaoImpl();

    AdminBo adminBo = (AdminBo) BOFactory.getBoFactory().getBOTYpes(BOTypes.ADMIN);

    @FXML
    void ChangeOnAction(ActionEvent event) {
        String newPassword = Password.getText();
        String confirmPassword = RePassword.getText();

        if(!newPassword.equals(confirmPassword)){
            new Alert(Alert.AlertType.ERROR,"Passwords are do not match! Try again").show();
        } else if((newPassword.isEmpty())&&(confirmPassword.isEmpty())) {
            new Alert(Alert.AlertType.INFORMATION, "Empty Fields!").show();
        }
        else{
            try {
                adminBo.updateUser(newPassword,tempUsername);
                new Alert(Alert.AlertType.INFORMATION, "Password change successfully!").show();
                Password.setText("");
                RePassword.setText("");
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }

    }

    public void btnback(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/View/Loging_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage =(Stage) this.rootnode.getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();

    }
}
