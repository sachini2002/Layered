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
import lk.ijse.bo.RoomBO;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.tm.RoomTm;
import lk.ijse.dao.impl.RoomDaoImpl;
import lk.ijse.Util.Regex;
import lk.ijse.Util.TextFields;
import lk.ijse.entity.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RoomFormController {

    @FXML
    private Button Backroo;

    @FXML
    private Button Clearroo;

    @FXML
    private Button Deleteroo;

    @FXML
    private Button Saveroo;

    @FXML
    private Button Updateroo;

    @FXML
    private TextField Id;

    @FXML
    private TableColumn<?, ?> collRate;

    @FXML
    private TableColumn<?, ?> collRoomId;

    @FXML
    private TableColumn<?, ?> collRoomNo;

    @FXML
    private TableColumn<?, ?> collStatus;

    @FXML
    private TableColumn<?, ?> collType;

    @FXML
    private TextField number;

    @FXML
    private TextField rate;

    @FXML
    private AnchorPane rooNode;

    @FXML
    private TextField status;

    @FXML
    private TableView<RoomDaoImpl> tblRoom;

    @FXML
    private TextField type;

   // RoomDaoImpl RoomDaoImpl = new RoomDaoImpl();

    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBOTYpes(BOTypes.ROOM);


    public void initialize(){
        setCellValueFactory();
        loadAllRoom();
    }

    private void setCellValueFactory() {
        collRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        collRoomNo.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        collRate.setCellValueFactory(new PropertyValueFactory<>("roomRate"));
        collType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        collStatus.setCellValueFactory(new PropertyValueFactory<>("roomStatus"));


    }

    private void loadAllRoom() {
        ObservableList<RoomDaoImpl> obList = FXCollections.observableArrayList();

        try {
            List<RoomDTO> roomList = roomBO.getAllRoom();
            for(RoomDTO room : roomList){
                RoomTm roomTm = new RoomTm(
                        room.getRoomId(),
                        room.getRoomNumber(),
                        room.getRoomType(),
                        room.getRoomRate(),
                        room.getRoomStatus()
                );
                obList.add(roomTm);
            }
            tblRoom.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void ClearOnAction(ActionEvent event) {
        Id.setText("");
        number.setText("");
        type.setText("");
        rate.setText("");
        status.setText("");
    }

    @FXML
    void DeleteOnAction(ActionEvent event) {
        String roomid = Id.getText();

        try {
            boolean isDeleted = roomBO.deleteRoom(roomid);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room deleted!").show();
                loadAllRoom();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void SaveOnAction(ActionEvent event) {
        String roomId = Id.getText();
        String roomNumber = number.getText();
        String roomType = type.getText();
        String roomRate = rate.getText();
        String roomStatus = status.getText();

        if (isValied()) {
            RoomDTO room = new RoomDTO(roomId, roomNumber, roomType, roomRate, roomStatus);

            try {
                if (roomId.isEmpty() || roomNumber.isEmpty() || roomType.isEmpty() || roomRate.isEmpty() || roomStatus.isEmpty()) {
                    new Alert(Alert.AlertType.INFORMATION, "Empty Fields! Try again").show();
                } else {
                    boolean isSaved = roomBO.saveRoom(room);
                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Room saved successfully!").show();
                        loadAllRoom();
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
    void UpdateOnAction(ActionEvent event) {
        String roomId = Id.getText();
        String roomNumber = number.getText();
        String roomType = type.getText();
        String roomRate = rate.getText();
        String roomStatus = status.getText();


        RoomDTO room1 = new RoomDTO(roomId, roomNumber, roomType, roomRate, roomStatus);
        try {
            boolean isUpdated = roomBO.updateRoom(room1);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room updated successfully!").show();
                loadAllRoom();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
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

    public void SearchOnACTION(ActionEvent actionEvent) {
        try {
            String roomId = Id.getText();

            RoomDTO room = roomBO.searchRoomId(roomId);
            if (room != null) {
                Id.setText(room.getRoomId());
                number.setText(room.getRoomNumber());
                type.setText(room.getRoomType());
                rate.setText(String.valueOf(room.getRoomRate()));
                status.setText(room.getRoomStatus());
            } else {
                new Alert(Alert.AlertType.ERROR, "Room is not found!").show();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle or log the exception as needed
            new Alert(Alert.AlertType.ERROR, "An error occurred while searching for the room.").show();
        }
    }

   public void RoomIDOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.RooID,Id);
    }

    public void TypeOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.LETTERS_AND_NUMBERS,type);
    }

    public void RoomNoOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.INT,number);
    }

    public void RateOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DOUBLE,rate);
    }

    public void StatusOnKey(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.RooStatus,status);
    }

    private boolean isValied() {
        if (!Regex.setTextColor(TextFields.RooID,Id)) return false;
        if (!Regex.setTextColor(TextFields.LETTERS_AND_NUMBERS,type)) return false;
        if (!Regex.setTextColor(TextFields.INT,number)) return false;
        if (!Regex.setTextColor(TextFields.DOUBLE,rate)) return false;
        if (!Regex.setTextColor(TextFields.RooStatus,status)) return false;
        return true;
    }
}
