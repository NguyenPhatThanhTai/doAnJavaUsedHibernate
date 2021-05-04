package viewForm.Controller;

import Model.AccountStaffEntity;
import Model.DetailInfRepairEntity;
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
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONObject;

import javax.swing.text.html.ImageView;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
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
    @FXML
    private Label lbSaiMatKhau;

    public void DangNhap(ActionEvent e) throws IOException{
        if (txtTaiKhoan.getText().equals("") || txtMatKhau.getText().equals("")){
            lbSaiMatKhau.setStyle("-fx-background-color: #D6D263; -fx-background-radius: 20");
            lbSaiMatKhau.setText("Không được để trống");
            lbSaiMatKhau.setVisible(true);
            new animatefx.animation.BounceIn(lbSaiMatKhau).play();
        }else {
            serviceImplement serviceImplement = new serviceImplement();
            AccountStaffEntity accountStaffEntity = new AccountStaffEntity();
            accountStaffEntity.setStaffAccount(txtTaiKhoan.getText());
            accountStaffEntity.setStaffPassword(txtMatKhau.getText());
            if(serviceImplement.checkLogin(txtTaiKhoan.getText(), txtMatKhau.getText())){

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
                lbSaiMatKhau.setStyle("-fx-background-color: #E16363; -fx-background-radius: 20");
                lbSaiMatKhau.setText("Sai mật khẩu hoặc tài khoản!");
                lbSaiMatKhau.setVisible(true);
                new animatefx.animation.BounceIn(lbSaiMatKhau).play();
            }
        }
    }

    public void closeAlert(){
        new animatefx.animation.BounceOut(lbSaiMatKhau).play();
        lbSaiMatKhau.setVisible(false);
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
