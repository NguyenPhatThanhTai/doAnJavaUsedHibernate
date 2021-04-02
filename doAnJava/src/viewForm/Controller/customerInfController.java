package viewForm.Controller;

import DAO.infCustomerDao;
import DAO.infRepairDao;
import Model.DetailInfRepairEntity;
import Model.InfCustomersEntity;
import Model.InfRepairEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class customerInfController implements Initializable {

//    @FXML
//    private JFXComboBox txtGioiTinh;

    @FXML
    private TableColumn<InfCustomersEntity, String> colCustomerId;

    @FXML
    private TableColumn<InfCustomersEntity, String> colCustomerName;

    @FXML
    private TableColumn<InfCustomersEntity, String> colCustomerSex;

    @FXML
    private TableColumn<InfCustomersEntity, String> colCustomerBirth;

    @FXML
    private TableColumn<InfCustomersEntity, String> colCustomerEmail;

    @FXML
    private TableColumn<InfCustomersEntity, String> colCustomerPhone;

    @FXML
    private TableColumn<InfCustomersEntity, Date> colCustomerTimeAdd;

    @FXML
    private JFXButton btnTest;

    @FXML
    private TextField txtMaKhachHang;

    @FXML
    private JFXButton btnTest1;

    @FXML
    private JFXButton btnTest2;

    @FXML
    private JFXButton btnTest3;

    @FXML
    private TextField txtTenKhachHang;

    @FXML
    private JFXToggleButton txtGioiTinh;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSoDienThoai;

    @FXML
    private TextField txtNgayThem;

    @FXML
    private DatePicker txtNgaySinh;

    @FXML
    private Label lbXinchao;

    @FXML
    private TableView tableListCustomer;

    @FXML
    private TextField txtTenkhachHangSuaChua;

    @FXML
    private TextField txtMaSuaChua;

    @FXML
    private TextField txtNhanVienTiepNhan;

    @FXML
    private TextField txtTinhTrang;

    @FXML
    private TextField txtLaptopName;

    @FXML
    private JFXToggleButton txtSua;

    @FXML
    private DatePicker txtNgayHen;

    @FXML
    private TextField txtTien;

    @FXML
    private ComboBox<String> cbThietBi;


    @FXML
    private TableView<DetailInfRepairEntity> tableListRepair;

    @FXML
    private TableColumn<DetailInfRepairEntity, String> colRepairId;

    @FXML
    private TableColumn<DetailInfRepairEntity, String> colRepairCustomerName;

    @FXML
    private TableColumn<DetailInfRepairEntity, String> colRepairNameOfLaptop;

    @FXML
    private TableColumn<DetailInfRepairEntity, String> colRepairStatusLaptop;

    @FXML
    private TableColumn<DetailInfRepairEntity, String> colRepairStaffId;

    @FXML
    private TableColumn<DetailInfRepairEntity, String> colRepairNeedFix;

    @FXML
    private TableColumn<DetailInfRepairEntity, String> colRepairNote;

    @FXML
    private TableColumn<DetailInfRepairEntity, String> colRepairStatus;

    @FXML
    private TableColumn<DetailInfRepairEntity, String> colRepairAppointment;

    @FXML
    private TableColumn<DetailInfRepairEntity, String> colRepairMoney;

    ObservableList<InfCustomersEntity> clist;
    ObservableList<DetailInfRepairEntity> rlist;

    public void loadData() {
        infCustomerDao dao = new infCustomerDao();
        clist = dao.getALl();
        tableListCustomer.setItems(clist);

        colCustomerId.setCellValueFactory(new PropertyValueFactory("customerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory("customerName"));
        colCustomerSex.setCellValueFactory(new PropertyValueFactory("customerSex"));
        colCustomerBirth.setCellValueFactory(new PropertyValueFactory("customerBirth"));
        colCustomerEmail.setCellValueFactory(new PropertyValueFactory("customerEmail"));
        colCustomerPhone.setCellValueFactory(new PropertyValueFactory("customerPhone"));
        colCustomerTimeAdd.setCellValueFactory(new PropertyValueFactory("customerTimeAdd"));
    }

    public void getItemFromTableView(){
        tableListCustomer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                InfCustomersEntity cus = (InfCustomersEntity) tableListCustomer.getItems().get(tableListCustomer.getSelectionModel().getSelectedIndex());
                txtMaKhachHang.setText(cus.getCustomerId());
                txtTenKhachHang.setText(cus.getCustomerName());
                if (cus.getCustomerSex().equals("Nam")){
                    txtGioiTinh.setSelected(true);
                }
                else {
                    txtGioiTinh.setSelected(false);
                }
                txtNgaySinh.setValue(LocalDate.parse(String.valueOf(cus.getCustomerBirth())));
                txtEmail.setText(cus.getCustomerEmail());
                txtSoDienThoai.setText(cus.getCustomerPhone());
                txtNgayThem.setText(String.valueOf(cus.getCustomerTimeAdd()));
            }
        });
    }

    public void loadDataRepair(){
        infRepairDao dao2 = new infRepairDao();
        rlist = dao2.getALl();
        tableListRepair.setItems(rlist);
        colRepairId.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfRepairByRepairId().getRepairId()));
        colRepairCustomerName.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerName()));
        colRepairNameOfLaptop.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfRepairByRepairId().getLaptopName()));
        colRepairStatusLaptop.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfRepairByRepairId().getLaptopStatus()));
        colRepairStaffId.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfRepairByRepairId().getStaffId()));
        colRepairNeedFix.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getRepairReason()));
        colRepairNote.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getRepairNote()));
        colRepairStatus.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getRepairStatus()));
        colRepairAppointment.setCellValueFactory(cell -> new ReadOnlyStringWrapper(String.valueOf(cell.getValue().getRepairAppointment())));
        colRepairMoney.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getRepairMoney()));
    }

    public void getItemFromTableViewRepair(){
        tableListRepair.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DetailInfRepairEntity de = (DetailInfRepairEntity) tableListRepair.getItems().get(tableListRepair.getSelectionModel().getSelectedIndex());
                txtMaSuaChua.setText(de.getInfRepairByRepairId().getRepairId());
                txtTenkhachHangSuaChua.setText(de.getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerName());
                if (de.getRepairNote().equals("Sửa lấy ngay")){
                    txtSua.setSelected(false);
                }
                else {
                    txtSua.setSelected(true);
                }
                txtNgayHen.setValue(LocalDate.parse(String.valueOf(de.getRepairAppointment())));
                txtNhanVienTiepNhan.setText(de.getInfRepairByRepairId().getStaffId());
                txtTinhTrang.setText(de.getRepairReason());
                txtLaptopName.setText(String.valueOf(de.getInfRepairByRepairId().getLaptopName()));
                txtTien.setText(de.getRepairMoney());
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
        loadDataRepair();
        cbThietBi.getItems().add("hello");
        cbThietBi.getItems().add("hi");
        cbThietBi.getItems().add("alo");
    }
}
