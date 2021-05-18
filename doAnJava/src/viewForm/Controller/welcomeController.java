package viewForm.Controller;

import Model.InfStaffEntity;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class welcomeController implements Initializable {
    @FXML
    private Text lbTime;

    @FXML
    private Text lbDate;

    @FXML
    private Label lbTb1;

    @FXML
    private Label lbTb2;

    @FXML
    private Label lbTb3;

    @FXML
    private Label lbTb4;

    @FXML
    private Text labelThongBao;

    Thread thread;

    public void getTime(){
        while (true){
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter hour = DateTimeFormatter.ofPattern("HH");
            DateTimeFormatter min = DateTimeFormatter.ofPattern("mm");
            DateTimeFormatter sec = DateTimeFormatter.ofPattern("ss");

            DateTimeFormatter day = DateTimeFormatter.ofPattern("dd");
            DateTimeFormatter month = DateTimeFormatter.ofPattern("MM");
            DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");

            lbTime.setText(hour.format(now) + ":" + min.format(now) + ":" + sec.format(now));
            lbDate.setText(day.format(now)+"-"+month.format(now)+"-"+year.format(now));
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        thread = new Thread(this::getTime);
        thread.start();
        thread.interrupt();
    }
}
