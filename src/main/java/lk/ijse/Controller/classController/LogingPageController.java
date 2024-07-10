package lk.ijse.Controller.classController;

import animatefx.animation.SlideInLeft;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.Database.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogingPageController implements Initializable {

    @FXML
    private ImageView logo;

    @FXML
    private Button LoginBtn;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField Username;

    @FXML
    private AnchorPane anchorpane;

    public static String tempUsername;

    @FXML
    private Label diya;

    @FXML
    private Label diya1;

    @FXML
    private Hyperlink txtForgotPassword;

    @FXML
    public void RegisterOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/View/Registration_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.anchorpane.getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("Register Form");
        stage.centerOnScreen();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(logo);
        rotate.setDuration(Duration.millis(2000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.play();
    }

    @FXML
   public void loginOnAction(ActionEvent event) {
        String userName = Username.getText();
        String pw = Password.getText();
        try {

                cheackCredential(userName, pw);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

    }

    private void cheackCredential(String userName, String pw) {
        try {
            if (userName.isEmpty() || pw.isEmpty()) {
                throw new IllegalArgumentException("Username or password is empty");
            }

            String sql = "SELECT * FROM Admin WHERE User_name = ?";
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String user = resultSet.getString("User_name");
                String pww = resultSet.getString("Passward");

                if (pw.equals(pww)) {
                    navigateToTheDashboard();

                } else {
                    throw new IllegalArgumentException("Sorry! Password is incorrect!");
                }
            } else {
                throw new IllegalArgumentException("Sorry! User ID cannot be found!");
            }
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    private void navigateToTheDashboard() throws IOException {
        AnchorPane root = FXMLLoader.load(this.getClass().getResource("/View/Dashboard_form.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) this.anchorpane.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
        new SlideInLeft(root).play();
    }


    @FXML
    void ForgetOnAction(ActionEvent event) {
        tempUsername = Username.getText();
        String pw = Password.getText();

        try {
            checkPasswordCredentials(tempUsername);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void checkPasswordCredentials(String tempUsername) throws SQLException, IOException {
        String sql = "SELECT * FROM Admin WHERE User_name = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setObject(1,tempUsername);

        ResultSet resultSet = pst.executeQuery();

        if(tempUsername.isEmpty()) {
            new Alert(Alert.AlertType.INFORMATION, "Empty Fields!Enter username").show();
        }
        else if (resultSet.next()) {
            String dbUsername = resultSet.getString("User_name");

            if (dbUsername.equals(tempUsername)) {
                AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/View/ForgetPassword_form.fxml"));
                Scene scene = new Scene(rootNode);
                Stage stage = (Stage) txtForgotPassword.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Reset Password");
                stage.centerOnScreen();


            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Sorry!Invalid username").show();
        }


    }
}

