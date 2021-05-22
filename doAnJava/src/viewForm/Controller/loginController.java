package viewForm.Controller;

import DAO.accountStaffDao;
import Model.AccountStaffEntity;
import Model.DetailInfRepairEntity;
import Service.serviceImplement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
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
    @FXML
    private JFXProgressBar processbar;
    @FXML
    private Text lbStatus;
    @FXML
    private JFXButton btnDangNhap;
    Thread thread;
    String token, key, typ;

    ActionEvent e;

    public void StartThreadDangNhap(ActionEvent e){
        this.e = e;
        thread = new Thread(this::DangNhap);
        thread.start();
    }

    public void DangNhap(){
        btnDangNhap.setDisable(true);
        txtTaiKhoan.setDisable(true);
        txtMatKhau.setDisable(true);

        processbar.setVisible(true);
        try {
            if (txtTaiKhoan.getText().equals("") || txtMatKhau.getText().equals("")){
                lbSaiMatKhau.setStyle("-fx-background-color: #D6D263; -fx-background-radius: 20");
                btnDangNhap.setDisable(false);
                txtTaiKhoan.setDisable(false);
                txtMatKhau.setDisable(false);
                processbar.setVisible(false);
                Platform.runLater(()->{
                    lbSaiMatKhau.setText("Không được để trống");
                });
                lbSaiMatKhau.setVisible(true);
                new animatefx.animation.BounceIn(lbSaiMatKhau).play();
            }else {
                lbStatus.setText("-Đang kiểm tra tài khoản-");
                serviceImplement serviceImplement = new serviceImplement();
                AccountStaffEntity accountStaffEntity = new AccountStaffEntity();
                accountStaffEntity.setStaffAccount(txtTaiKhoan.getText());
                accountStaffEntity.setStaffPassword(txtMatKhau.getText());

                if(serviceImplement.checkLogin(txtTaiKhoan.getText(), txtMatKhau.getText())){
                    //Chuyển form
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Main.fxml"));
                    Parent root = loader.load();

                    mainController mainController = loader.getController();

                    lbStatus.setText("-Đang lấy token-");
                    getToken();
                    mainController.showInfomation(serviceImplement.getStaffData(txtTaiKhoan.getText()), token, key, typ);

                    Platform.runLater(new Runnable(){
                        @Override
                        public void run() {
                            Stage stage = new Stage();
                            stage.initStyle(StageStyle.UNDECORATED); // tắt thanh top bar
                            stage.setScene(new Scene(root));
                            stage.setTitle("Trang chính");
                            stage.show();

                            ((Node)(e.getSource())).getScene().getWindow().hide();
                        }
                    });
                    //
                }
                else {
                    btnDangNhap.setDisable(false);
                    txtTaiKhoan.setDisable(false);
                    txtMatKhau.setDisable(false);
                    processbar.setVisible(false);
                    lbSaiMatKhau.setStyle("-fx-background-color: #E16363; -fx-background-radius: 20");
                    Platform.runLater(()->{
                        lbSaiMatKhau.setText("Sai mật khẩu hoặc tài khoản!");
                    });
                    lbSaiMatKhau.setVisible(true);
                    new animatefx.animation.BounceIn(lbSaiMatKhau).play();
                }
            }
            thread.interrupt();
        }catch (Exception Ex){
            Ex.printStackTrace();
        }
    }

    public void getToken(){
        String keyWorld = "Logined";
        String query_url = "https://dht-api.000webhostapp.com/APIDoAnJava/sendToken.php";
        String json = "{ \"name\" : \""+keyWorld+"\", " +
                "       \"name1\" : \""+keyWorld+"\"}";
        try {
            URL url = new URL(query_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();
            // read the response
            BufferedReader br = null;
            if (100 <= conn.getResponseCode() && conn.getResponseCode() <= 399) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String array1 = br.readLine();

            JSONObject json2 = new JSONObject(array1);

            JSONArray jsonArray2 = json2.getJSONArray("token");

            //get token
            String token = jsonArray2.get(0).toString();

            this.token = token;

            //get key and typ
            String[] chunks = token.split("\\.");

            Base64.Decoder decoder = Base64.getDecoder();

            String header = new String(decoder.decode(chunks[0]));
            String payload = new String(decoder.decode(chunks[1]));

            String[] strArray = new String[] {payload};

            JSONObject json3 = new JSONObject(strArray[0]);

            System.out.println(json3);

            Object key = json3.get("key");
            Object typ = json3.get("typ");

            this.key = key.toString();
            this.typ=typ.toString();

            br.close();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println(e);
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
