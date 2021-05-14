package viewForm.Controller;

import DAO.doanhthuDao;
import Model.InfDoanhThuSuaEntity;
import Model.InfDoanhThuThangEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;



import java.net.URL;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class doanhThuController implements Initializable {
    @FXML
    private Text txtMoneyToday;

    @FXML
    private Text txtTotalToday;

    @FXML
    private Text txtProfit;

    @FXML
    private Text txtMoneyMonth;

    @FXML
    private Text txtTotalMonth;

    @FXML
    private Label lbNgayThu1;

    @FXML
    private Label lbNgayThu2;

    @FXML
    private Label lbNgayThu3;

    @FXML
    private Label lbNgayThu4;

    @FXML
    private Label lbNgayThu5;

    @FXML
    private Label lbNgayThu6;

    @FXML
    private Label lbNgayThu7;

    @FXML
    private Label lbNgayThu8;

    @FXML
    private Label lbNgayThu9;

    @FXML
    private Label lbNgayThu10;

    @FXML
    private Label lbNgayThu11;

    @FXML
    private Label lbNgayThu12;

    @FXML
    private Label lbNgayThu13;

    @FXML
    private Label lbNgayThu14;

    @FXML
    private Label lbNgayThu15;

    @FXML
    private Label lbNgayThu16;

    @FXML
    private Label lbNgayThu17;

    @FXML
    private Label lbNgayThu18;

    @FXML
    private Label lbNgayThu19;

    @FXML
    private Label lbNgayThu20;

    @FXML
    private Label lbNgayThu21;

    @FXML
    private Label lbNgayThu22;

    @FXML
    private Label lbNgayThu23;

    @FXML
    private Label lbNgayThu24;

    @FXML
    private Label lbNgayThu25;

    @FXML
    private Label lbNgayThu26;

    @FXML
    private Label lbNgayThu27;

    @FXML
    private Label lbNgayThu28;

    @FXML
    private Label lbNgayThu29;

    @FXML
    private Label lbNgayThu30;

    Thread thread;

    public void startThreadLoadData(){
        thread = new Thread(this::loadData);
        thread.start();
        thread.interrupt();
    }

    public void loadData(){

        DateTimeFormatter dayAdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter month = DateTimeFormatter.ofPattern("MM");
        String DTT = year.format(now) + "-" + month.format(now) + "-01";

        doanhthuDao dao = new doanhthuDao();
        InfDoanhThuSuaEntity infDoanhThuSuaEntity = dao.getByDate(Date.valueOf(dayAdd.format(now)));
        InfDoanhThuThangEntity infDoanhThuThangEntity = dao.getByDateDoanhThuThang(Date.valueOf(DTT));

        Locale usa = new Locale("vn", "VN");

        Currency dollars = Currency.getInstance(usa);

        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);

        double money1 = Double.parseDouble(infDoanhThuSuaEntity.getMoney());
        double money2 = Double.parseDouble(infDoanhThuThangEntity.getInputMoney());
        double money3 = Double.parseDouble(infDoanhThuThangEntity.getProfitMoney());

        String fomat1 = dollarFormat.format(money1);
        String fomat2 = dollarFormat.format(money2);
        String fomat3 = dollarFormat.format(money3);

        txtMoneyToday.setText(fomat1 + " VNĐ");
        txtTotalToday.setText(infDoanhThuSuaEntity.getEntity());
        txtMoneyMonth.setText(fomat2 + " VNĐ");
        txtTotalMonth.setText(infDoanhThuThangEntity.getEntity());
        txtProfit.setText(fomat3 + " VNĐ");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startThreadLoadData();
    }
}
