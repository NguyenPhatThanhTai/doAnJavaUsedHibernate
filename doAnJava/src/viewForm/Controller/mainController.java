package viewForm.Controller;


import DAO.doanhthuDao;
import DAO.infLKDao;
import Model.InfDoanhThuSuaEntity;
import Model.InfDoanhThuThangEntity;
import Model.InfLkEntity;
import Model.InfStaffEntity;
import Service.serviceImplement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

    @FXML
    private JFXButton btnLK;

    @FXML
    private JFXButton btnStaff;

    @FXML
    private JFXButton btnDoanhThu;

    InfStaffEntity infStaffEntity;
    String token, key, typ;

    Thread thread;

    public void showInfomation(InfStaffEntity infStaffEntity, String token, String key, String typ){
        this.infStaffEntity = infStaffEntity;
        this.token = token;
        this.key = key;
        this.typ = typ;
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
        if (!infStaffEntity.getStaffDeparment().equals("1")){
            btnLK.setDisable(true);
            btnStaff.setDisable(true);
            btnQuanTriSever.setDisable(true);
            btnDoanhThu.setDisable(true);
        }
    }

    public void checkDoanhThu(){
        DateTimeFormatter dayAdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        doanhthuDao doanhthuDao = new doanhthuDao();

        DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter month = DateTimeFormatter.ofPattern("MM");
        String DTT = year.format(now) + "-" + month.format(now) + "-01";
        System.out.println(DTT);
        InfDoanhThuThangEntity infDoanhThuThangEntity = doanhthuDao.getByDateDoanhThuThang(Date.valueOf(DTT));

//        infLKDao infLKDao = new infLKDao();
//        List<InfLkEntity> infLkEntity = infLKDao.getALl();
//        long totalMonelLK = 0;
//
//        for (InfLkEntity money:infLkEntity){
//            totalMonelLK += Long.parseLong(money.getLkPrice()) * Long.parseLong(money.getLkNumber());
//        }

        if (infDoanhThuThangEntity == null){
            //String dtt, String inputMoney, String outputMoney, String entity, String staffSalary, String profitMoney, Date month
            System.out.println(DTT);
            InfDoanhThuThangEntity infDoanhThuThangEntity1f = new InfDoanhThuThangEntity("DTT"+year.format(now) + month.format(now), "0", "0", "0", "0", "0",Date.valueOf(year.format(now) + "-" + month.format(now) + "-01"));
            doanhthuDao.addNewDoanhThuThang(infDoanhThuThangEntity1f);
        }

        InfDoanhThuSuaEntity infDoanhThuSuaEntity = doanhthuDao.getByDate(java.sql.Date.valueOf(dayAdd.format(now)));

        if (infDoanhThuSuaEntity == null){
            DateTimeFormatter day = DateTimeFormatter.ofPattern("dd");
            DateTimeFormatter month1 = DateTimeFormatter.ofPattern("MM");
            DateTimeFormatter year1 = DateTimeFormatter.ofPattern("yy");
            String DTN = "DTN" + day.format(now) + month1.format(now) + year1.format(now);
            System.out.println(DTN);
            InfDoanhThuThangEntity infDoanhThuThangEntity1 = new InfDoanhThuThangEntity("DTT"+year.format(now)+month.format(now));
            InfDoanhThuSuaEntity infDoanhThuSuaEntity1 = new InfDoanhThuSuaEntity(DTN, "0", "0", Date.valueOf(dayAdd.format(now)), infDoanhThuThangEntity1);
            doanhthuDao.addNewDoanhThu(infDoanhThuSuaEntity1);
        }
    }

    public void startTheardshowCustomerPage(){
        setPage("Loading.fxml");
        thread = new Thread(this::showCustomerPage);
        thread.start();
        thread.interrupt();
    }

    public void showCustomerPage() {
        try {
        setEffectClick("#4777A7", "ThongTinKhachHang");
//        setPage("customerInf.fxml");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/customerInf.fxml"));
        Parent root = null;
        root = loader.load();

        customerInfController mainController = loader.getController();
        mainController.showInfomation(infStaffEntity, token, key, typ);
        Pane p = (Pane) root;
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    paneLoad.getChildren().clear();
                    paneLoad.getChildren().add(p);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void startThreadshowLkPage(){
        setPage("Loading.fxml");
        thread = new Thread(this::showLkPage);
        thread.start();
        thread.interrupt();
    }

    public void showLkPage(){
        setEffectClick("#4777A7", "LK");
        setPage("linhKienInf.fxml");
    }

    public void startThreadshowWelcomePage(){
        setPage("Loading.fxml");
        thread = new Thread(this::showWelcomePage);
        thread.start();
        thread.interrupt();
    }

    public void showWelcomePage(){
        setEffectClick("#4777A7", "Home");
        setPage("welcome.fxml");
    }

    public void startThreadshowSystemManager(){
        setPage("Loading.fxml");
        thread = new Thread(this::showSystemManager);
        thread.start();
        thread.interrupt();
    }

    public void showSystemManager(){
        setEffectClick("#4777A7", "QuanTriHeThong");
        setPage("managerSystem.fxml");
    }

    public void startThreadshowStaffPage(){
        setPage("Loading.fxml");
        thread = new Thread(this::showStaffPage);
        thread.start();
        thread.interrupt();
    }

    public void showStaffPage(){
        try {
            setEffectClick("#4777A7", "Staff");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/staffInf.fxml"));
            Parent root = null;
            root = loader.load();

            staffInfController staffInfController = loader.getController();
            staffInfController.showInfomation(infStaffEntity);
            Pane p = (Pane) root;
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    paneLoad.getChildren().clear();
                    paneLoad.getChildren().add(p);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void startThreadshowProfitPage(){
        setPage("Loading.fxml");
        thread = new Thread(this::showProfitPage);
        thread.start();
        thread.interrupt();
    }

    public void showProfitPage(){
        setEffectClick("#4777A7", "Profit");
        setPage("doanhthu.fxml");
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
                btnLK.setStyle("-fx-background-color: transparent");
                btnStaff.setStyle("-fx-background-color: transparent");
                btnDoanhThu.setStyle("-fx-background-color: transparent");
                break;
            case "Home":
                btnHome.setStyle("-fx-background-color: "+color);
                btnQuanTriSever.setStyle("-fx-background-color: transparent");
                btnLK.setStyle("-fx-background-color: transparent");
                btnThongTinKhachHang.setStyle("-fx-background-color: transparent");
                btnStaff.setStyle("-fx-background-color: transparent");
                btnDoanhThu.setStyle("-fx-background-color: transparent");
                break;
            case "QuanTriHeThong":
                btnQuanTriSever.setStyle("-fx-background-color: "+color);
                btnThongTinKhachHang.setStyle("-fx-background-color: transparent");
                btnHome.setStyle("-fx-background-color: transparent");
                btnLK.setStyle("-fx-background-color: transparent");
                btnStaff.setStyle("-fx-background-color: transparent");
                btnDoanhThu.setStyle("-fx-background-color: transparent");
                break;
            case "LK":
                btnLK.setStyle("-fx-background-color: "+color);
                btnThongTinKhachHang.setStyle("-fx-background-color: transparent");
                btnQuanTriSever.setStyle("-fx-background-color: transparent");
                btnHome.setStyle("-fx-background-color: transparent");
                btnStaff.setStyle("-fx-background-color: transparent");
                btnDoanhThu.setStyle("-fx-background-color: transparent");
                break;
            case "Staff":
                btnStaff.setStyle("-fx-background-color: "+color);
                btnThongTinKhachHang.setStyle("-fx-background-color: transparent");
                btnQuanTriSever.setStyle("-fx-background-color: transparent");
                btnHome.setStyle("-fx-background-color: transparent");
                btnLK.setStyle("-fx-background-color: transparent");
                btnDoanhThu.setStyle("-fx-background-color: transparent");
                break;
            case "Profit":
                btnDoanhThu.setStyle("-fx-background-color: "+color);
                btnThongTinKhachHang.setStyle("-fx-background-color: transparent");
                btnQuanTriSever.setStyle("-fx-background-color: transparent");
                btnHome.setStyle("-fx-background-color: transparent");
                btnLK.setStyle("-fx-background-color: transparent");
                btnStaff.setStyle("-fx-background-color: transparent");
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

    public void Logouṭ(ActionEvent e){
        ButtonType foo = new ButtonType("Đăng xuất", ButtonBar.ButtonData.OK_DONE);
        ButtonType bar = new ButtonType("Thoát", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Bạn có chắc chắn muốn đăng xuất",
                foo, bar);

        alert.setTitle("Xác nhận đăng xuất chương trình");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(bar) == foo) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/sample.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED); // tắt thanh top bar
                stage.setScene(new Scene(root));
                stage.setTitle("Đăng nhập");
                stage.show();

                ((Node)(e.getSource())).getScene().getWindow().hide();

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showWelcomePage();
        checkDoanhThu();
    }
}
