package viewForm.Controller;

import Model.InfStaffEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class welcomeController implements Initializable {
    @FXML
    private Label lbTime;

    @FXML
    private Label lbDate;

    @FXML
    private Label lbTb1;

    @FXML
    private Label lbTb2;

    @FXML
    private Label lbTb3;

    @FXML
    private Label lbTb4;

    @FXML
    private Label labelThongBao;

    Thread thread;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
