package viewForm.Controller;

import DAO.*;
import Model.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class staffInfController implements Initializable {
    @FXML
    private TableView<AccountStaffEntity> tableViewNhanVien;

    @FXML
    private TableColumn<AccountStaffEntity, String> clMaNhanVien;

    @FXML
    private TableColumn<AccountStaffEntity, String> clTenNhanVien;

    @FXML
    private TableColumn<AccountStaffEntity, String> clGioiTinh;

    @FXML
    private TableColumn<AccountStaffEntity, String> clNgaySinh;

    @FXML
    private TableColumn<AccountStaffEntity, String> clDiaChi;

    @FXML
    private TableColumn<AccountStaffEntity, String> clSoDienThoai;

    @FXML
    private TableColumn<AccountStaffEntity, String> clChucVu;

    @FXML
    private TableColumn<AccountStaffEntity, String> clNgayThem;

    @FXML
    private TextField txtMaNhanVien;

    @FXML
    private TextField txtNgayThem;

    @FXML
    private TextField txtTenNhanVien;

    @FXML
    private TextField txtDiaChi;

    @FXML
    private TextField txtSoDienThoai;

    @FXML
    private JFXToggleButton txtGioiTinh;

    @FXML
    private DatePicker txtNgaySinh;

    @FXML
    private JFXComboBox<String> cbChucVu;

    @FXML
    private JFXButton btnThem;

    @FXML
    private JFXButton btnHuyThem;

    @FXML
    private JFXButton btnXacNhanThem;

    @FXML
    private JFXButton btnHuySua;

    @FXML
    private JFXButton btnXacNhanSua;

    @FXML
    private JFXButton btnSua;

    @FXML
    private JFXButton btnHuyXoa;

    @FXML
    private JFXButton btnXacNhanXoa;

    @FXML
    private JFXButton btnXoa;

    @FXML
    private JFXButton btnllamMoi;

    accountStaffDao dao = new accountStaffDao();

    ObservableList<AccountStaffEntity> nvList;

    public void loadNV(){
        nvList = dao.getALl();
        tableViewNhanVien.setItems(nvList);
        clMaNhanVien.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfStaffByStaffId().getStaffId()));
        clTenNhanVien.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfStaffByStaffId().getStaffName()));
        clGioiTinh.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfStaffByStaffId().staffSex()));
        clNgaySinh.setCellValueFactory(cell -> new ReadOnlyStringWrapper(String.valueOf(cell.getValue().getInfStaffByStaffId().getStaffBirth())));
        clDiaChi.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfStaffByStaffId().getStaffAddress()));
        clSoDienThoai.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfStaffByStaffId().getStaffPhone()));
        clChucVu.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfStaffByStaffId().staffDepartment()));
        clNgayThem.setCellValueFactory(cell -> new ReadOnlyStringWrapper(String.valueOf(cell.getValue().getInfStaffByStaffId().getStaffTimeAdd())));
    }

    public void getItemsFromTableViewNV(){
        try {
            btnThem.setDisable(true);
            btnSua.setDisable(false);
            btnXoa.setDisable(false);

            AccountStaffEntity cus = (AccountStaffEntity) tableViewNhanVien.getItems().get(tableViewNhanVien.getSelectionModel().getSelectedIndex());
            txtMaNhanVien.setText(cus.getInfStaffByStaffId().getStaffId());
            txtTenNhanVien.setText(cus.getInfStaffByStaffId().getStaffName());
            if (cus.getInfStaffByStaffId().getStaffDeparment().equals("1")){
                cbChucVu.getSelectionModel().select(0);
            }
            if (cus.getInfStaffByStaffId().getStaffDeparment().equals("2")){
                cbChucVu.getSelectionModel().select(1);
            }
            if (cus.getInfStaffByStaffId().getStaffDeparment().equals("3")){
                cbChucVu.getSelectionModel().select(2);
            }
            if (cus.getInfStaffByStaffId().getStaffSex().equals("1")){
                txtGioiTinh.setSelected(true);
            }
            else {
                txtGioiTinh.setSelected(false);
            }
            txtNgaySinh.setValue(LocalDate.parse(String.valueOf(cus.getInfStaffByStaffId().getStaffBirth())));
            txtDiaChi.setText(cus.getInfStaffByStaffId().getStaffAddress());
            txtSoDienThoai.setText(cus.getInfStaffByStaffId().getStaffPhone());
            txtNgayThem.setText(String.valueOf(cus.getInfStaffByStaffId().getStaffTimeAdd()));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void addNV(){
        if (txtTenNhanVien.getText().equals("") || txtSoDienThoai.getText().equals("") || txtDiaChi.getText().equals("") || txtNgaySinh.getValue().equals("") || cbChucVu.getValue().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Không được để trống");
            alert.showAndWait();
        }
        else {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter day = DateTimeFormatter.ofPattern("dd");
            DateTimeFormatter min = DateTimeFormatter.ofPattern("mm");
            DateTimeFormatter sec = DateTimeFormatter.ofPattern("ss");

            String staffId = "NV" + day.format(now) + min.format(now) + sec.format(now);
            txtMaNhanVien.setText(staffId);

            String sex = "2";
            String quyen = "1";


            if (txtGioiTinh.isSelected()) {
                sex = "1";
            }

            if (cbChucVu.getValue().toString().equals("Nhân viên")) {
                quyen = "2";
            }

            if (cbChucVu.getValue().toString().equals("Kế toán")) {
                quyen = "3";
            }

            accountStaffDao accountStaffDao = new accountStaffDao();
            salaryStaffDao salaryStaffDao = new salaryStaffDao();
            infStaffDao infStaffDao = new infStaffDao();

            InfStaffEntity infStaffEntity = new InfStaffEntity(txtMaNhanVien.getText(), txtTenNhanVien.getText(), sex, Date.valueOf(txtNgaySinh.getValue()), txtDiaChi.getText(), txtSoDienThoai.getText(), quyen, Date.valueOf(txtNgayThem.getText()));
            AccountStaffEntity accountStaffEntity = new AccountStaffEntity(txtMaNhanVien.getText(), "123456", quyen, infStaffEntity);
            SalaryStaffEntity salaryStaffEntity = new SalaryStaffEntity(txtMaNhanVien.getText(), "3000000", "0", "0", "3000000", infStaffEntity);

            if (infStaffDao.addData(infStaffEntity) && accountStaffDao.addData(accountStaffEntity) && salaryStaffDao.addData(salaryStaffEntity)) {
                refreshView();
                openTextField(false);
                btnXacNhanThem.setVisible(false);
                btnHuyThem.setVisible(false);
                btnSua.setDisable(false);
                btnXoa.setDisable(false);
                btnThem.setVisible(true);
                tableViewNhanVien.setDisable(false);
            }
        }
    }

    public void updateNV(){
        String sex = "2";
        String quyen = "1";


        if (txtGioiTinh.isSelected()){
            sex = "1";
        }

        if (cbChucVu.getValue().toString().equals("Nhân viên")){
            quyen = "2";
        }

        if (cbChucVu.getValue().toString().equals("Kế toán")){
            quyen = "3";
        }


        InfStaffEntity infStaffEntity = new InfStaffEntity(txtMaNhanVien.getText(), txtTenNhanVien.getText(), sex, Date.valueOf(txtNgaySinh.getValue()), txtDiaChi.getText(), txtSoDienThoai.getText(), quyen, Date.valueOf(txtNgayThem.getText()));
        infStaffDao infStaffDao = new infStaffDao();
        if(infStaffDao.updateData(infStaffEntity)){
            refreshView();
            openTextField(false);
            btnXacNhanSua.setVisible(false);
            btnHuySua.setVisible(false);
            btnThem.setDisable(false);
            btnXoa.setDisable(false);
            btnSua.setVisible(true);
            tableViewNhanVien.setDisable(false);
            clearAllKhachHang();
        }
    }

    public void xoaNV(){
        accountStaffDao accountStaffDao = new accountStaffDao();
        AccountStaffEntity accountStaffEntity = new AccountStaffEntity();
        accountStaffEntity.setStaffAccount(txtMaNhanVien.getText());

        salaryStaffDao salaryStaffDao = new salaryStaffDao();
        SalaryStaffEntity salaryStaffEntity = new SalaryStaffEntity();
        salaryStaffEntity.setSalaStaffId(txtMaNhanVien.getText());

        infStaffDao infStaffDao = new infStaffDao();
        InfStaffEntity infStaffEntity = new InfStaffEntity();
        infStaffEntity.setStaffId(txtMaNhanVien.getText());

        if(salaryStaffDao.dellData(salaryStaffEntity) && accountStaffDao.dellData(accountStaffEntity) && infStaffDao.dellData(infStaffEntity)){
            refreshView();
            openTextField(false);
            btnXacNhanXoa.setVisible(false);
            btnHuyXoa.setVisible(false);
            btnSua.setDisable(false);
            btnThem.setDisable(false);
            btnXoa.setVisible(true);
            tableViewNhanVien.setDisable(false);
            clearAllKhachHang();
        }
    }

    public void refreshView(){
        accountStaffDao dao = new accountStaffDao();
        nvList = dao.getALl();
        tableViewNhanVien.setItems(nvList);
        tableViewNhanVien.refresh();
    }

    public void openTextField(boolean flag){
        txtTenNhanVien.setDisable(flag);
        txtGioiTinh.setDisable(flag);
        txtDiaChi.setDisable(flag);
        txtNgaySinh.setDisable(flag);
        txtSoDienThoai.setDisable(flag);
        cbChucVu.setDisable(flag);
    }

    public void ThemNhanVienButton(){
        txtMaNhanVien.setText("Hệ thống định sẵn");
        openButton(false, "Add");
    }

    public void HuyThemNhanVienButton(){
        openButton(true, "HuyAdd");
        clearAllKhachHang();
    }

    public void SuaNhanVienButton(){
        openButton(false, "Sua");
    }

    public void HuySuaNhanVienButton(){
        openButton(true, "HuySua");
        clearAllKhachHang();
    }

    public void XoaNhanVienButton(){
        openButton(false, "Xoa");
    }

    public void HuyXoaNhanVienButton(){
        openButton(true, "HuyXoa");
        clearAllKhachHang();
    }

    public void openButton(boolean flag, String nut){
        switch (nut){
            case "Add":
                btnThem.setVisible(flag);
                btnSua.setDisable(true);
                btnXoa.setDisable(true);
                btnXacNhanThem.setVisible(true);
                btnHuyThem.setVisible(true);

                tableViewNhanVien.setDisable(!flag);

                openTextField(flag);
                break;
            case "HuyAdd":
                btnThem.setVisible(flag);
                btnSua.setDisable(!flag);
                btnXoa.setDisable(!flag);
                btnXacNhanThem.setVisible(!flag);
                btnHuyThem.setVisible(!flag);

                tableViewNhanVien.setDisable(!flag);

                openTextField(flag);
                break;
            case "Sua":
                btnSua.setVisible(flag);
                btnThem.setDisable(true);
                btnXoa.setDisable(true);
                btnXacNhanSua.setVisible(true);
                btnHuySua.setVisible(true);

                tableViewNhanVien.setDisable(!flag);

                openTextField(flag);
                break;
            case "HuySua":
                btnSua.setVisible(flag);
                btnThem.setDisable(!flag);
                btnXoa.setDisable(!flag);
                btnXacNhanSua.setVisible(!flag);
                btnHuySua.setVisible(!flag);

                tableViewNhanVien.setDisable(!flag);

                openTextField(flag);
                break;
            case "Xoa":
                btnXoa.setVisible(flag);
                btnThem.setDisable(true);
                btnSua.setDisable(true);
                btnXacNhanXoa.setVisible(true);
                btnHuyXoa.setVisible(true);

                tableViewNhanVien.setDisable(!flag);

                openTextField(flag);
                break;
            case "HuyXoa":
                btnXoa.setVisible(flag);
                btnThem.setDisable(!flag);
                btnThem.setDisable(!flag);
                btnXacNhanXoa.setVisible(!flag);
                btnHuyXoa.setVisible(!flag);

                tableViewNhanVien.setDisable(!flag);

                openTextField(flag);
                break;
        }
    }

    public void clearAllKhachHang(){
        txtTenNhanVien.setText("");
        txtGioiTinh.setText("");
        txtDiaChi.setText("");
        txtSoDienThoai.setText("");
        btnSua.setDisable(true);
        btnXoa.setDisable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadNV();
        cbChucVu.getItems().add("Admin");
        cbChucVu.getItems().add("Nhân viên");
        cbChucVu.getItems().add("Kế toán");

        DateTimeFormatter dayAdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        txtNgayThem.setText(dayAdd.format(now));
    }
}
