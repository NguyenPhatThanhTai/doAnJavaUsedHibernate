package viewForm.Controller;

import DAO.detailInfRepairDao;
import DAO.infCustomerDao;
import DAO.infRepairDao;
import Model.DetailInfRepairEntity;
import Model.InfCustomersEntity;
import Model.InfRepairEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class customerInfController implements Initializable {

    @FXML
    private TableColumn<DetailInfRepairEntity, String> colCustomerId;

    @FXML
    private TableColumn<DetailInfRepairEntity, String> colCustomerName;

    @FXML
    private TableColumn<DetailInfRepairEntity, String> colCustomerSex;

    @FXML
    private TableColumn<DetailInfRepairEntity, String> colCustomerBirth;

    @FXML
    private TableColumn<DetailInfRepairEntity, String> colCustomerEmail;

    @FXML
    private TableColumn<DetailInfRepairEntity, String> colCustomerPhone;

    @FXML
    private TableColumn<DetailInfRepairEntity, String> colCustomerTimeAdd;

    @FXML
    private JFXButton btnThemKhachHang;

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
    private JFXButton btnHuySua;

    @FXML
    private JFXButton btnXacNhanSua;

    @FXML
    private JFXButton btnHuyXoa;

    @FXML
    private JFXButton btnXacNhanXoa;

    @FXML
    private JFXButton btnHuyThem;

    @FXML
    private JFXButton btnXacNhanThem;

    @FXML
    private JFXButton btnThem;

    @FXML
    private JFXButton btnXoa;

    @FXML
    private JFXButton btnSua;

    @FXML
    private JFXButton btnllamMoi;

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

    ObservableList<DetailInfRepairEntity> rlist;
    detailInfRepairDao dao = new detailInfRepairDao();

    public void loadData() {
        rlist = dao.getALl();
        tableListCustomer.setItems(rlist);
        colCustomerId.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerId()));
        colCustomerName.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerName()));
        colCustomerSex.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerSex()));
        colCustomerBirth.setCellValueFactory(cell -> new ReadOnlyStringWrapper(String.valueOf(cell.getValue().getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerBirth())));
        colCustomerEmail.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerEmail()));
        colCustomerPhone.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerPhone()));
        colCustomerTimeAdd.setCellValueFactory(cell -> new ReadOnlyStringWrapper(String.valueOf(cell.getValue().getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerTimeAdd())));
    }

    public void getItemFromTableView(){
        tableListCustomer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DetailInfRepairEntity cus = (DetailInfRepairEntity) tableListCustomer.getItems().get(tableListCustomer.getSelectionModel().getSelectedIndex());
                txtMaKhachHang.setText(cus.getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerId());
                txtTenKhachHang.setText(cus.getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerName());
                if (cus.getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerSex().equals("Nam")){
                    txtGioiTinh.setSelected(true);
                }
                else {
                    txtGioiTinh.setSelected(false);
                }
                txtNgaySinh.setValue(LocalDate.parse(String.valueOf(cus.getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerBirth())));
                txtEmail.setText(cus.getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerEmail());
                txtSoDienThoai.setText(cus.getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerPhone());
                txtNgayThem.setText(String.valueOf(cus.getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerTimeAdd()));
            }
        });
    }

    public void loadDataRepair(){
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

    public void addNewCustomer(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter day = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter min = DateTimeFormatter.ofPattern("mm");
        DateTimeFormatter sec = DateTimeFormatter.ofPattern("ss");

        String customerId = "KH" + day.format(now) + min.format(now) + sec.format(now);
        txtMaKhachHang.setText(customerId);
        String repairId = "RP" + day.format(now) + min.format(now) + sec.format(now);
        String detailId = "DT" + day.format(now) + min.format(now) + sec.format(now);

        String sex = "2";

        if (txtGioiTinh.isSelected()){
            sex = "1";
        }

        infCustomerDao addCustomer = new infCustomerDao();
        infRepairDao addRepair = new infRepairDao();
        detailInfRepairDao addDetail = new detailInfRepairDao();

        InfCustomersEntity infCustomersEntity = new InfCustomersEntity(customerId, txtTenKhachHang.getText(), sex, Date.valueOf(txtNgaySinh.getValue()), txtEmail.getText(), txtSoDienThoai.getText(), Date.valueOf(txtNgayThem.getText()));
        InfRepairEntity infRepairEntity = new InfRepairEntity(repairId, "Chưa biết", "Chưa biết", "Chưa biết", infCustomersEntity);
        DetailInfRepairEntity detailInfRepairEntity = new DetailInfRepairEntity(detailId, "Chưa biết", "Sửa lấy ngay", "2", Date.valueOf(txtNgayThem.getText()), "0", infRepairEntity);


        if (addCustomer.addData(infCustomersEntity) && addRepair.addData(infRepairEntity) && addDetail.addData(detailInfRepairEntity)){
            refreshView();
        }
    }

    public void refreshView(){
        detailInfRepairDao dao = new detailInfRepairDao();
        rlist = dao.getALl();
        tableListCustomer.setItems(rlist);
        tableListRepair.setItems(rlist);

        tableListCustomer.refresh();
        tableListRepair.refresh();
    }

    public void ThemKhachHangButton(){
        openButton(false, "Add");
    }

    public void HuyThemKhachHangButton(){
        openButton(true, "HuyAdd");
    }

    public void openButton(boolean flag, String nut){
        switch (nut){
            case "Add":
                btnThem.setVisible(flag);
                btnSua.setDisable(true);
                btnXoa.setDisable(true);
                btnXacNhanThem.setVisible(true);
                btnHuyThem.setVisible(true);

                tableListCustomer.setDisable(!flag);

                txtMaKhachHang.setDisable(flag);
                txtTenKhachHang.setDisable(flag);
                txtGioiTinh.setDisable(flag);
                txtEmail.setDisable(flag);
                txtNgaySinh.setDisable(flag);
                txtSoDienThoai.setDisable(flag);
                break;
            case "HuyAdd":
                btnThem.setVisible(flag);
                btnSua.setDisable(!flag);
                btnXoa.setDisable(!flag);
                btnXacNhanThem.setVisible(!flag);
                btnHuyThem.setVisible(!flag);

                tableListCustomer.setDisable(!flag);

                txtMaKhachHang.setDisable(flag);
                txtTenKhachHang.setDisable(flag);
                txtGioiTinh.setDisable(flag);
                txtEmail.setDisable(flag);
                txtNgaySinh.setDisable(flag);
                txtSoDienThoai.setDisable(flag);
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
        loadDataRepair();
        cbThietBi.getItems().add("hello");
        cbThietBi.getItems().add("hi");
        cbThietBi.getItems().add("alo");

        DateTimeFormatter dayAdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        txtNgayThem.setText(dayAdd.format(now));
    }
}
