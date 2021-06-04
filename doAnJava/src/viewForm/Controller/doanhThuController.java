package viewForm.Controller;

import DAO.doanhthuDao;
import DAO.infLKDao;
import Model.InfDoanhThuSuaEntity;
import Model.InfDoanhThuThangEntity;
import Model.InfLkEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXProgressBar;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
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
    private Text txtLkMoney;

    @FXML
    private JFXButton txtClickViewLK;

    @FXML
    private JFXComboBox<String> cbMonth;

    @FXML
    private JFXProgressBar loading;

    @FXML
    private ImageView gifLoading;

    @FXML
    private SplitPane splitDoanhThu;

    @FXML
    private SplitPane splitMonth;

    @FXML
    private BarChart<String, Number> charts;

    @FXML
    private TableView<InfLkEntity> tableListLk;

    @FXML
    private TableColumn<InfLkEntity, String> colLK_Id;

    @FXML
    private TableColumn<InfLkEntity, String> colLK_Name;

    @FXML
    private TableColumn<InfLkEntity, String> colLK_Number;

    @FXML
    private TableColumn<InfLkEntity, String> colLK_Producer;

    @FXML
    private TableColumn<InfLkEntity, String> colLK_Price;

    @FXML
    private TableColumn<InfLkEntity, String> colLK_Time_Add;


    Thread thread;
    int day, month, year;

    public void startThreadLoadData(){
        thread = new Thread(this::loadData);
        thread.start();
        thread.interrupt();
    }

    public void loadData(){
        loading.setVisible(true);
        thread = new Thread(this::loadChar);
        thread.start();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter day1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String DTT = year + "-" + month + "-01";

        doanhthuDao dao = new doanhthuDao();
        InfDoanhThuSuaEntity infDoanhThuSuaEntity = dao.getByDate(Date.valueOf(day1.format(now)));
        InfDoanhThuThangEntity infDoanhThuThangEntity = dao.getByDateDoanhThuThang(Date.valueOf(DTT));

        Locale usa = new Locale("vn", "VN");

        Currency dollars = Currency.getInstance(usa);

        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);

        double money1 = Double.parseDouble(infDoanhThuSuaEntity.getMoney());
        double money2 = Double.parseDouble(infDoanhThuThangEntity.getInputMoney());
        double money3 = Double.parseDouble(infDoanhThuThangEntity.getProfitMoney());
        double money4 = Double.parseDouble(infDoanhThuThangEntity.getOutputMoney());

        String fomat1 = dollarFormat.format(money1);
        String fomat2 = dollarFormat.format(money2);
        String fomat3 = dollarFormat.format(money3);
        String fomat4 = dollarFormat.format(money4);

        txtMoneyToday.setText(fomat1 + " VNĐ");
        txtTotalToday.setText(infDoanhThuSuaEntity.getEntity() +" đơn");
        txtMoneyMonth.setText(fomat2 + " VNĐ");
        txtTotalMonth.setText(infDoanhThuThangEntity.getEntity() + " đơn");
        txtProfit.setText(fomat3 + " VNĐ");
        txtLkMoney.setText(fomat4 + " VNĐ");

        thread.interrupt();
    }

    public void loadChar(){
        cbMonth.setDisable(true);
        txtClickViewLK.setDisable(true);
        loading.setVisible(true);
        LocalDateTime now = LocalDateTime.now();
        doanhthuDao dao = new doanhthuDao();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Doanh thu theo từng ngày");
        for (int i = 1; i <= day; i ++){
            InfDoanhThuSuaEntity infDoanhThuSuaEntity1 =
                    dao.getByDate(Date.valueOf(year + "-" + month + "-" + i));
            if (infDoanhThuSuaEntity1 != null){
                series.getData().add(new XYChart.Data<>("Ngày " + i, Integer.parseInt(infDoanhThuSuaEntity1.getMoney()), Integer.parseInt(infDoanhThuSuaEntity1.getMoney())));
            }
            else {
                series.getData().add(new XYChart.Data<>("Ngày " + i, 0, 0));
            }
//        series.getData().add(new XYChart.Data<>("Ngày 1", 63));
//        series.getData().add(new XYChart.Data<>("Ngày 2", 102));
//        series.getData().add(new XYChart.Data<>("Ngày 3", 45));
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                charts.getData().add(series);
            }
        });

        cbMonth.setDisable(false);
        txtClickViewLK.setDisable(false);
        loading.setVisible(false);
    }

    public void startThreadListLK(){
        thread = new Thread(this::getListLK);
        thread.start();
        thread.interrupt();
    }

    public void getListLK(){
        gifLoading.setVisible(true);
        String query = year+"-"+month;

        if (month < 11){
            query = "0"+month;
        }

        infLKDao infLKDao = new infLKDao();
        ObservableList<InfLkEntity> infLkEntities = infLKDao.getLkByDate(query);

        tableListLk.setItems(infLkEntities);
        colLK_Id.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getLkId()));
        colLK_Name.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getLkName()));
        colLK_Number.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getLkNumber()));
        colLK_Producer.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getLkProducer()));
        colLK_Price.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getLkPrice()));
        colLK_Time_Add.setCellValueFactory(cell -> new ReadOnlyStringWrapper(String.valueOf(cell.getValue().getLkTimeAdd())));
        gifLoading.setVisible(false);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtClickViewLK.setText("Quay về");
            }
        });
        splitDoanhThu.setVisible(false);
        tableListLk.setVisible(true);
    }

    public void clickLK(){
        if (txtClickViewLK.getText().equals("Nhấn xem các đơn hàng đã nhập trong tháng")){
            startThreadListLK();
        }
        else {
            txtClickViewLK.setText("Nhấn xem các đơn hàng đã nhập trong tháng");
            splitDoanhThu.setVisible(true);
            tableListLk.setVisible(false);
        }
    }

    public void setChart(){
        charts.getData().clear();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter day = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter month = DateTimeFormatter.ofPattern("MM");

        if (cbMonth.getValue().equals("1") || cbMonth.getValue().equals("3") || cbMonth.getValue().equals("5") ||
        cbMonth.getValue().equals("7") || cbMonth.getValue().equals("8") || cbMonth.getValue().equals("10") || cbMonth.getValue().equals("12")){
            this.day = 31;
        }
        else {
            this.day = 30;
        }
        this.month = Integer.parseInt(cbMonth.getValue());
        this.year = Integer.parseInt(year.format(now));

        thread = new Thread(this::loadData);
        thread.start();
        thread.interrupt();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter day = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter month = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
        for (int i = 1; i <= Integer.parseInt(month.format(now)); i++){
            cbMonth.getItems().add(String.valueOf(i));
        }

        this.month = Integer.parseInt(month.format(now));
        if (month.equals("1") || month.equals("3") || month.equals("5") ||
                month.equals("7") || month.equals("8") || month.equals("10") || month.equals("12")){
            this.day = 31;
        }
        else {
            this.day = 30;
        }
        this.year = Integer.parseInt(year.format(now));

        startThreadLoadData();
    }
}
