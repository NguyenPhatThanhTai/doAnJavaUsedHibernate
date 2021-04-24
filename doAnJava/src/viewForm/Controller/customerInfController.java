package viewForm.Controller;

import DAO.detailInfRepairDao;
import DAO.infCustomerDao;
import DAO.infLKDao;
import DAO.infRepairDao;
import Model.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Array;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
    private JFXComboBox cbLocLK;

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

    @FXML
    private TableView<InfLkEntity> tableListLinhKien;

    @FXML
    private TableColumn<InfLkEntity, String> colMLK;

    @FXML
    private TableColumn<InfLkEntity, String> colTLK;

    @FXML
    private TableColumn<InfLkEntity, String> colSL;

    @FXML
    private TableColumn<InfLkEntity, String> colNSX;

    @FXML
    private TableColumn<InfLkEntity, String> colGT;

    @FXML
    private JFXTextArea txtLkDaChon;

    ObservableList<DetailInfRepairEntity> rlist;
    ObservableList<InfLkEntity> lkList;
    detailInfRepairDao dao = new detailInfRepairDao();
    infLKDao lkDao = new infLKDao();
    List<String> listLK = new ArrayList<>();

    String maNv = "";
    String name = "";
    String chucVu = "";

    //hello

    public void showInfomation(InfStaffEntity infStaffEntity){
        if ( infStaffEntity != null){
            name = infStaffEntity.getStaffName();
            maNv = infStaffEntity.getStaffId();
            txtNhanVienTiepNhan.setText(maNv);
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
    }

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
                if (cus.getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerSex().equals("1")){
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
        colRepairStaffId.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfRepairByRepairId().getInfStaffByStaffId().getStaffId()));
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
                txtNhanVienTiepNhan.setText(de.getInfRepairByRepairId().getInfStaffByStaffId().getStaffId());
                txtTinhTrang.setText(de.getRepairReason());
                txtLaptopName.setText(String.valueOf(de.getInfRepairByRepairId().getLaptopName()));
                txtTien.setText(de.getRepairMoney());
            }
        });
    }

    public void loadInfLk(String lkName){
        lkList = lkDao.getFilterNameLk(lkName);
        tableListLinhKien.setItems(lkList);
        colMLK.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getLkId()));
        colTLK.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getLkName()));
        colSL.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getLkNumber()));
        colNSX.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getLkProducer()));
        colGT.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getLkPrice()));
    }

    public void filterLK(){
        lkList = lkDao.getFilterNameLk(cbLocLK.getValue().toString());
        tableListLinhKien.setItems(lkList);
        tableListLinhKien.refresh();
    }

    public void setItemSelected(){
        String lkTien = txtTien.getText();
        tableListLinhKien.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                InfLkEntity de = (InfLkEntity) tableListLinhKien.getItems().get(tableListLinhKien.getSelectionModel().getSelectedIndex());
                listLK.add(de.getLkName());
                txtLkDaChon.setText(listLK.toString());

                long money1 = Long.parseLong(txtTien.getText());
                long money2 = Long.parseLong(de.getLkPrice());
                long tienLK = Long.parseLong(txtTien.getText() + de.getLkPrice());
                long tien = money1+money2;
                txtTien.setText(String.valueOf(tien));
            }
        });
    }

    public void resetDanhSach(){
        listLK.clear();
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
        InfStaffEntity infStaffEntity = new InfStaffEntity(maNv);
        InfRepairEntity infRepairEntity = new InfRepairEntity(repairId, "Chưa biết", "Chưa biết", infCustomersEntity, infStaffEntity);
        DetailInfRepairEntity detailInfRepairEntity = new DetailInfRepairEntity(detailId, "Chưa biết", "Sửa lấy ngay", "2", Date.valueOf(txtNgayThem.getText()), "0", infRepairEntity);


        if (addCustomer.addData(infCustomersEntity) && addRepair.addData(infRepairEntity) && addDetail.addData(detailInfRepairEntity)){
            refreshView();
            openTextField(false);
            btnXacNhanThem.setVisible(false);
            btnHuyThem.setVisible(false);
            btnSua.setDisable(false);
            btnXoa.setDisable(false);
            btnThem.setVisible(true);
            tableListCustomer.setDisable(false);
        }
    }

    public void updateCustomer(){
        String sex = "2";

        if (txtGioiTinh.isSelected()){
            sex = "1";
        }
        InfCustomersEntity infCustomersEntity = new InfCustomersEntity(txtMaKhachHang.getText(), txtTenKhachHang.getText(), sex, Date.valueOf(txtNgaySinh.getValue()), txtEmail.getText(), txtSoDienThoai.getText(), Date.valueOf(txtNgayThem.getText()));
        infCustomerDao updateCustomer = new infCustomerDao();
        if(updateCustomer.updateData(infCustomersEntity)){
            refreshView();
            openTextField(false);
            btnXacNhanSua.setVisible(false);
            btnHuySua.setVisible(false);
            btnThem.setDisable(false);
            btnXoa.setDisable(false);
            btnSua.setVisible(true);
            tableListCustomer.setDisable(false);
        }
    }

    public void deleteCustomer(){
        String idSplit = txtMaKhachHang.getText();

        String[] parts = idSplit.split("KH");

        detailInfRepairDao delDetail = new detailInfRepairDao();
        DetailInfRepairEntity detail = new DetailInfRepairEntity();
        detail.setDetailId("DT" + parts[1]);

        infRepairDao delRepair = new infRepairDao();
        InfRepairEntity repair = new InfRepairEntity();
        repair.setRepairId("RP" + parts[1]);

        infCustomerDao delCustomer = new infCustomerDao();
        InfCustomersEntity customer = new InfCustomersEntity();
        customer.setCustomerId(txtMaKhachHang.getText());

        if(delDetail.dellData(detail) && delRepair.dellData(repair) && delCustomer.dellData(customer)){
            refreshView();
            openTextField(false);
            btnXacNhanXoa.setVisible(false);
            btnHuyXoa.setVisible(false);
            btnSua.setDisable(false);
            btnThem.setDisable(false);
            btnXoa.setVisible(true);
            tableListCustomer.setDisable(false);
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
        txtMaKhachHang.setText("Hệ thống định sẵn");
        openButton(false, "Add");
    }

    public void HuyThemKhachHangButton(){ openButton(true, "HuyAdd");}

    public void SuaKhachHangButton(){openButton(false, "Sua");}

    public void HuySuaKhachHangButton(){openButton(true, "HuySua");}

    public void XoaKhachHangButton(){openButton(false, "Xoa");}

    public void HuyXoaKhachHangButton(){openButton(true, "HuyXoa");}

    public void openButton(boolean flag, String nut){
        switch (nut){
            case "Add":
                btnThem.setVisible(flag);
                btnSua.setDisable(true);
                btnXoa.setDisable(true);
                btnXacNhanThem.setVisible(true);
                btnHuyThem.setVisible(true);

                tableListCustomer.setDisable(!flag);

                openTextField(flag);
                break;
            case "HuyAdd":
                btnThem.setVisible(flag);
                btnSua.setDisable(!flag);
                btnXoa.setDisable(!flag);
                btnXacNhanThem.setVisible(!flag);
                btnHuyThem.setVisible(!flag);

                tableListCustomer.setDisable(!flag);

                openTextField(flag);
                break;
            case "Sua":
                btnSua.setVisible(flag);
                btnThem.setDisable(true);
                btnXoa.setDisable(true);
                btnXacNhanSua.setVisible(true);
                btnHuySua.setVisible(true);

                tableListCustomer.setDisable(!flag);

                openTextField(flag);
                break;
            case "HuySua":
                btnSua.setVisible(flag);
                btnThem.setDisable(!flag);
                btnXoa.setDisable(!flag);
                btnXacNhanSua.setVisible(!flag);
                btnHuySua.setVisible(!flag);

                tableListCustomer.setDisable(!flag);

                openTextField(flag);
                break;
            case "Xoa":
                btnXoa.setVisible(flag);
                btnThem.setDisable(true);
                btnThem.setDisable(true);
                btnXacNhanXoa.setVisible(true);
                btnHuyXoa.setVisible(true);

                tableListCustomer.setDisable(!flag);

                openTextField(flag);
                break;
            case "HuyXoa":
                btnXoa.setVisible(flag);
                btnThem.setDisable(!flag);
                btnThem.setDisable(!flag);
                btnXacNhanXoa.setVisible(!flag);
                btnHuyXoa.setVisible(!flag);

                tableListCustomer.setDisable(!flag);

                openTextField(flag);
                break;
        }
    }

    public void openTextField(boolean flag){
        txtTenKhachHang.setDisable(flag);
        txtGioiTinh.setDisable(flag);
        txtEmail.setDisable(flag);
        txtNgaySinh.setDisable(flag);
        txtSoDienThoai.setDisable(flag);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
        loadDataRepair();
        loadInfLk("Acer");
        cbLocLK.getItems().add("Acer");
        cbLocLK.getItems().add("Msi");
        cbLocLK.getItems().add("Lenovo");
        cbLocLK.getItems().add("Asus");
        cbLocLK.getItems().add("Apple");

        DateTimeFormatter dayAdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        txtNgayThem.setText(dayAdd.format(now));
    }
}
