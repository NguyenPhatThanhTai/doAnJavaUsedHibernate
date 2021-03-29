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
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane paneLoad;

    @FXML
    private JFXButton btnMinimized;

    @FXML
    private JFXButton btnThongTinKhachHang;

    @FXML
    private JFXButton btnHome;

    @FXML
    private Label lbName;

    public void showInfomation(InfStaffEntity infStaffEntity){
        String name;
        if ( infStaffEntity != null){
            name = infStaffEntity.getStaffName();
        }
        else {
            name = "Không định dạng được";
        }
        lbName.setText(name);
    }

    public void showCustomerPage(){
        setEffectClick("#4777A7", "ThongTinKhachHang");

        new Thread(()->{
                Platform.runLater(()->{
                    pageLoad object = new pageLoad();
                    Pane view = object.getPage("customerInf.fxml");
                    paneLoad.getChildren().add(view);
                });
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start(); 
    }

    public void showWelcomePage(){
        setEffectClick("#4777A7", "Home");
        new Thread(()->{
            Platform.runLater(()->{
                pageLoad object = new pageLoad();
                Pane view = object.getPage("welcome.fxml");
                paneLoad.getChildren().add(view);
            });
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void setEffectClick(String color, String button){

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
