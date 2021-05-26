package viewForm.Controller;

import DAO.doanhthuDao;
import Model.InfDoanhThuSuaEntity;
import Model.InfDoanhThuThangEntity;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
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
    private BarChart<String, Number> charts;

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

    public void loadChar(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter day = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter month = DateTimeFormatter.ofPattern("MM");

        doanhthuDao dao = new doanhthuDao();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Số lượng đơn sửa theo ngày");
        for (int i = 1; i <= Integer.parseInt(day.format(now)); i ++){
            InfDoanhThuSuaEntity infDoanhThuSuaEntity1 =
                    dao.getByDate(Date.valueOf(year.format(now) + "-" + month.format(now) + "-" + i));
            if (infDoanhThuSuaEntity1 != null){
                series.getData().add(new XYChart.Data<>("Ngày " + i, Integer.parseInt(infDoanhThuSuaEntity1.getEntity()), Integer.parseInt(infDoanhThuSuaEntity1.getEntity())));
            }
            else {
                series.getData().add(new XYChart.Data<>("Ngày " + i, 0, 0));
            }
//        series.getData().add(new XYChart.Data<>("Ngày 1", 63));
//        series.getData().add(new XYChart.Data<>("Ngày 2", 102));
//        series.getData().add(new XYChart.Data<>("Ngày 3", 45));
        }
        charts.getData().add(series);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startThreadLoadData();
        loadChar();
    }
}
