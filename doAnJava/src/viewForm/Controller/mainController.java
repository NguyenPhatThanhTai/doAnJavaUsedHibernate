package viewForm.Controller;

import DAO.doDao;
import Model.InfStaffEntity;
import Model.accountStaffModel;
import Model.customerModel;
import Model.infStaffModel;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import viewForm.Main;


import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.scene.input.KeyCode.R;

public class mainController implements Initializable {


    @FXML
    private BorderPane paneLoad;

    @FXML
    private JFXButton btnMinimized;

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

    @FXML
    private JFXButton btnThongTinKhachHang;

    @FXML
    private JFXButton btnHome;

    public void showInfomation(InfStaffEntity infStaffEntity){
        String name;

        SimpleDateFormat formatter= new SimpleDateFormat("'Hôm nay là ngày' dd-MM-yyyy ");
        SimpleDateFormat time= new SimpleDateFormat("HH:mm");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        if ( infStaffEntity != null){
            name = infStaffEntity.getStaffName();
        }
        else {
            name = "Không định dạng được";
        }
        lbTime.setText(time.format(date));
        lbDate.setText(formatter.format(date)+"Xin chào: " + name + "");
    }

    public void showCustomerPage(){
//        pageLoad object = new pageLoad();
//        Pane view = object.getPage("customerInf.fxml");
//        paneLoad.setCenter(view);


        setEffectClick(false, "#4777A7", "ThongTinKhachHang");

        new Thread(()->{
                Platform.runLater(()->{
                    pageLoad object = new pageLoad();
                    Pane view = object.getPage("customerInf.fxml");
                    paneLoad.setTop(view);
                });
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start(); 
    }

    public void showWelcomePage(){
        new Thread(()->{
            Platform.runLater(()->{
                paneLoad.setTop(lbTime);
            });
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        setEffectClick(true, "#4777A7", "Home");
    }

    public void setEffectClick(boolean set, String color, String button){
        lbDate.setVisible(set);
        lbTb1.setVisible(set);
        lbTb2.setVisible(set);
        lbTb3.setVisible(set);
        lbTb4.setVisible(set);
        lbTime.setVisible(set);
        labelThongBao.setVisible(set);

        switch (button){
            case "ThongTinKhachHang":
                btnThongTinKhachHang.setStyle("-fx-background-color: "+color);
                btnHome.setStyle("-fx-background-color: transparent");
                break;
            case "Home":
                btnHome.setStyle("-fx-background-color: "+color);
                btnThongTinKhachHang.setStyle("-fx-background-color: transparent");
                break;
        }
    }

    public void Quit(){
        ButtonType foo = new ButtonType("Ok tôi thoát", ButtonBar.ButtonData.OK_DONE);
        ButtonType bar = new ButtonType("Không tôi nhấn nhầm", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Bạn có chắc chắn muốn thoát",
                foo, bar);

        alert.setTitle("Xác nhận thoát chương trình");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(bar) == foo) {
            System.exit(1);
        }
    }

    public void Minimized(){
        btnMinimized.setOnMouseClicked( event -> {
            Stage obj = (Stage) btnMinimized.getScene().getWindow();
            obj.setIconified(true);
        });
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
