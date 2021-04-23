package viewForm.Controller;


import Model.InfStaffEntity;
import Service.serviceImplement;
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
import java.io.IOException;
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

    @FXML
    private JFXButton btnQuanTriSever;

    InfStaffEntity infStaffEntity = new InfStaffEntity();

    public void showInfomation(InfStaffEntity infStaffEntity){
        this.infStaffEntity = infStaffEntity;
        String name = "";
        String chucVu = "";
        if ( infStaffEntity != null){
            name = infStaffEntity.getStaffName();
            if (infStaffEntity.getStaffDeparment().equals("1")){
                chucVu = "Quản lý";
            }
            else {
                chucVu = "Nhân viên";
            }
        }
        else {
            name = "Không định dạng được";
        }
        lbName.setText("Họ và tên: " + name + " - Chức vụ: " + chucVu);
    }

    public void showCustomerPage() throws IOException {
        setEffectClick("#4777A7", "ThongTinKhachHang");
        setPage("customerInf.fxml");
    }

    public void showWelcomePage(){
        setEffectClick("#4777A7", "Home");
        setPage("welcome.fxml");
    }

    public void showSystemManager(){
        setEffectClick("#4777A7", "QuanTriHeThong");
        setPage("managerSystem.fxml");
    }

    public void setPage(String page){
        new Thread(()->{
            Platform.runLater(()->{
                pageLoad object = new pageLoad();
                Pane view = object.getPage(page);

                paneLoad.getChildren().clear();
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
                btnQuanTriSever.setStyle("-fx-background-color: transparent");
                break;
            case "Home":
                btnHome.setStyle("-fx-background-color: "+color);
                btnQuanTriSever.setStyle("-fx-background-color: transparent");
                btnThongTinKhachHang.setStyle("-fx-background-color: transparent");
                break;
            case "QuanTriHeThong":
                btnQuanTriSever.setStyle("-fx-background-color: "+color);
                btnThongTinKhachHang.setStyle("-fx-background-color: transparent");
                btnHome.setStyle("-fx-background-color: transparent");
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
        showWelcomePage();
    }
}
