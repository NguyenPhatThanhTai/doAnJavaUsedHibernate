package viewForm.Controller;

import Model.AccountStaffEntity;
import Service.serviceImplement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    @FXML
    public TextField txtTaiKhoan;
    @FXML
    public TextField txtMatKhau;
    @FXML
    private JFXButton btnMinimized;
    @FXML
    private JFXProgressBar psLogin;

    public void DangNhap(ActionEvent e) throws IOException{
        if (txtTaiKhoan.getText().equals("") && txtMatKhau.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Không được để trống!");
            alert.showAndWait();
        }else {
            loadProcess(true);
            serviceImplement serviceImplement = new serviceImplement();
            AccountStaffEntity accountStaffEntity = new AccountStaffEntity();
            accountStaffEntity.setStaffAccount(txtTaiKhoan.getText());
            accountStaffEntity.setStaffPassword(txtMatKhau.getText());
            if(serviceImplement.checkLogin(txtTaiKhoan.getText(), txtMatKhau.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Đăng nhập thành công!");
                alert.showAndWait();

                //Chuyển form
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Main.fxml"));
                Parent root = loader.load();

                mainController mainController = loader.getController();
                mainController.showInfomation(serviceImplement.getStaffData(txtTaiKhoan.getText()));

                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED); // tắt thanh top bar
                stage.setScene(new Scene(root));
                stage.setTitle("Main");
                stage.show();
                ((Node)(e.getSource())).getScene().getWindow().hide();
                //
            }
            else {
                loadProcess(false);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Sai mật khẩu hoặc tài khoản!");
                alert.showAndWait();
            }
        }
    }

    public void loadProcess(boolean flag){
        Thread t = new Thread(new Runnable() {
            public void run() {
                psLogin.setVisible(flag);
            }
        });
        t.start();
    }

    public void Quit(){
        ButtonType foo = new ButtonType("Ok tôi thoát", ButtonBar.ButtonData.OK_DONE);
        ButtonType bar = new ButtonType("Không tôi nhấn nhầm", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Bạn có chắc chắn muốn thoát",
                foo,
                bar);

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
