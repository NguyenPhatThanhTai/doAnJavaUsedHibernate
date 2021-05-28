package viewForm.Controller;

import DAO.*;
import Model.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class staffInfController implements Initializable {
    @FXML
    private TableView<AccountStaffEntity> tableViewNhanVien;

    @FXML
    private TableColumn<AccountStaffEntity, String> clMaNhanVien;//TableColumn<AccountStaffEntity, String> là dữ liệu thuộc bảng nhân viên kiểu dữ liệu là String

    @FXML
    private TableColumn<AccountStaffEntity, String> clTenNhanVien;//tương tự như trên

    @FXML
    private TableColumn<AccountStaffEntity, String> clGioiTinh;//tương tự như trên

    @FXML
    private TableColumn<AccountStaffEntity, String> clNgaySinh;//tương tự như trên

    @FXML
    private TableColumn<AccountStaffEntity, String> clDiaChi;//tương tự như trên

    @FXML
    private TableColumn<AccountStaffEntity, String> clSoDienThoai;//tương tự như trên

    @FXML
    private TableColumn<AccountStaffEntity, String> clChucVu;//tương tự như trên

    @FXML
    private TableColumn<AccountStaffEntity, String> clNgayThem;//tương tự như trên

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

    @FXML
    private TableView<AccountStaffEntity> tableviewTK;

    @FXML
    private TableColumn<AccountStaffEntity, String> colStaffID;

    @FXML
    private TableColumn<AccountStaffEntity, String> colAccount;

    @FXML
    private TableColumn<AccountStaffEntity, String> colPassword;

    @FXML
    private TableColumn<AccountStaffEntity, String> colRole;

    @FXML
    private TableColumn<AccountStaffEntity, String> colTenNhanVienTK;

    @FXML
    private TextField txtMaNhanVienTK;

    @FXML
    private TextField txtTaiKhoanNhanVien;

    @FXML
    private TextField txtMatKhauNhanVien;

    @FXML
    private TextField txtSoDonDaSua;

    @FXML
    private TextField txtSoTienHienTai;

    @FXML
    private JFXComboBox<String> cbChucVuTK;

    @FXML
    private JFXButton btnHuySuaTK;

    @FXML
    private JFXButton btnXacNhanSuaTK;

    @FXML
    private JFXButton btnSuaLuong;

    @FXML
    private JFXButton btnllamMoiTK;

    @FXML
    private TextField txtLuongCoBan;

    @FXML
    private TextField txtTienThuong;

    @FXML
    private Text lbTienHienTai;

    @FXML
    private JFXButton btnHuySuaLuong;

    @FXML
    private JFXButton btnXacNhanSuaLuong;

    @FXML
    private JFXButton btnSuaTK;

    @FXML
    private JFXButton btnllamMoiLuong;

    @FXML
    private TextField txtMaNhanVienLuong;

    @FXML
    private TableView<SalaryStaffEntity> tableviewLuong;

    @FXML
    private TableColumn<SalaryStaffEntity, String> colStaffIDLuong;

    @FXML
    private TableColumn<SalaryStaffEntity, String> colDefaultSalary;

    @FXML
    private TableColumn<SalaryStaffEntity, String> colReward;

    @FXML
    private TableColumn<SalaryStaffEntity, String> colEntity;

    @FXML
    private TableColumn<SalaryStaffEntity, String> colCurentMoney;

    @FXML
    private TableColumn<SalaryStaffEntity, String> colTenNhanVienLuong;

    @FXML
    private ImageView imgStaff;

    @FXML
    private JFXButton btnPicStaff;

    accountStaffDao dao = new accountStaffDao();//gọi tới DAO (dùng để trả dữ liệu về bên đây)

    ObservableList<AccountStaffEntity> nvList;//Muốn đưa dữ liệu vào tableView thì cần phải xài cái ObservableList
    ObservableList<SalaryStaffEntity> luongList;

    public void loadNV(){
        nvList = dao.getALl();//Nhét hết đống dữ liêu về nhân viên mà thằng DAO lấy được vào ObservableList
        tableViewNhanVien.setItems(nvList);//set cái đống dữ liêu đó vào bảng nhân viên
        clMaNhanVien.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfStaffByStaffId().getStaffId()));
        clTenNhanVien.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfStaffByStaffId().getStaffName()));
        clGioiTinh.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfStaffByStaffId().staffSex()));
        clNgaySinh.setCellValueFactory(cell -> new ReadOnlyStringWrapper(String.valueOf(cell.getValue().getInfStaffByStaffId().getStaffBirth())));
        clDiaChi.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfStaffByStaffId().getStaffAddress()));
        clSoDienThoai.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfStaffByStaffId().getStaffPhone()));
        clChucVu.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfStaffByStaffId().staffDepartment()));
        clNgayThem.setCellValueFactory(cell -> new ReadOnlyStringWrapper(String.valueOf(cell.getValue().getInfStaffByStaffId().getStaffTimeAdd())));
    }

    public void getItemsFromTableViewNV(){ // cái này thì khi nhấn dữ liệu trên bảng nó hiện vô mấy khung nhập
        try {
            btnThem.setDisable(true);
            btnSua.setDisable(false);
            btnXoa.setDisable(false);

            AccountStaffEntity cus = (AccountStaffEntity) tableViewNhanVien.getItems().get(tableViewNhanVien.getSelectionModel().getSelectedIndex());//.getSelectionModel().getSelectedIndex() lấy dữ liệu đang dc chọn cho vào model
            //gọi bảng accountStaff vì nó là bảng con, bảng con có thể gọi lên bảng cha thông qua khoá phụ ( private InfStaffEntity infStaffByStaffId ) bên model
            txtMaNhanVien.setText(cus.getInfStaffByStaffId().getStaffId());//setText cho từng ô nhập bằng model ở trên
            txtTenNhanVien.setText(cus.getInfStaffByStaffId().getStaffName());
            if (cus.getInfStaffByStaffId().getStaffDeparment().equals("1")){ // nếu chức vụ bằng 1 thì cái chỗ chọn chức vụ sẽ hiện quản lý
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
            txtNgaySinh.setValue(LocalDate.parse(String.valueOf(cus.getInfStaffByStaffId().getStaffBirth())));// set value vì cái chỗ nhập là datetimepicker
            txtDiaChi.setText(cus.getInfStaffByStaffId().getStaffAddress());
            txtSoDienThoai.setText(cus.getInfStaffByStaffId().getStaffPhone());
            txtNgayThem.setText(String.valueOf(cus.getInfStaffByStaffId().getStaffTimeAdd()));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void addNV() {
        //kiểm tra nếu bị bỏ trống thì thông báo

        if (txtTenNhanVien.getText().equals("") || txtSoDienThoai.getText().equals("") || txtDiaChi.getText().equals("") || txtNgaySinh.getValue().equals("") || cbChucVu.getValue().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Không được để trống");
            alert.showAndWait();
        } else {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter day = DateTimeFormatter.ofPattern("dd");
            DateTimeFormatter min = DateTimeFormatter.ofPattern("mm");
            DateTimeFormatter sec = DateTimeFormatter.ofPattern("ss");

            String staffId = "NV" + day.format(now) + min.format(now) + sec.format(now); // sinh mã nhân viên ngẫu nhiên theo ngày phút giây
            txtMaNhanVien.setText(staffId);

            String sex = "2";
            String quyen = "1";


            if (txtGioiTinh.isSelected()) {
                sex = "1";
            }

            if (cbChucVu.getValue().toString().equals("Nhân viên")) {
                quyen = "2";
            }

            accountStaffDao accountStaffDao = new accountStaffDao();
            salaryStaffDao salaryStaffDao = new salaryStaffDao();
            infStaffDao infStaffDao = new infStaffDao();

            //tất cả đều chơi với model nên khi muốn thêm 1 đối tượng mới ta sẽ gọi mới 1 model và truyền qua bên Dao xử lý
            //tạo mới model thông tin nhân viên
            InfStaffEntity infStaffEntity = new InfStaffEntity(txtMaNhanVien.getText(), txtTenNhanVien.getText(), sex, Date.valueOf(txtNgaySinh.getValue()), txtDiaChi.getText(), txtSoDienThoai.getText(), quyen, Date.valueOf(txtNgayThem.getText()));
            //tạo mới model tài khoản
            AccountStaffEntity accountStaffEntity = new AccountStaffEntity(txtMaNhanVien.getText(), "123456", quyen, infStaffEntity);
            //tạo mới model lương
            SalaryStaffEntity salaryStaffEntity = new SalaryStaffEntity(txtMaNhanVien.getText(), "3000000", "0", "0", "3000000", infStaffEntity);

            //Truyền qua bên DAO xử lý, bên DAO sẽ lấy những Model đó thêm vào sql
            if (infStaffDao.addData(infStaffEntity) && accountStaffDao.addData(accountStaffEntity) && salaryStaffDao.addData(salaryStaffEntity)) {
                //nếu thêm thành công sẽ làm mới lại bảng dữ liệu
                refreshView();
                refreshTableViewTK();
                refreshTableViewLuong();
                openTextField(true);
                btnXacNhanThem.setVisible(false);
                btnHuyThem.setVisible(false);
                btnSua.setDisable(false);
                btnXoa.setDisable(false);
                btnThem.setVisible(true);
                tableViewNhanVien.setDisable(false);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Lỗi, không thêm được");
                alert.showAndWait();
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

        //update cũng tương tự, truyền qua bên DAO nó sẽ cập nhật theo mã nhân viên
        InfStaffEntity infStaffEntity = new InfStaffEntity(txtMaNhanVien.getText(), txtTenNhanVien.getText(), sex, Date.valueOf(txtNgaySinh.getValue()), txtDiaChi.getText(), txtSoDienThoai.getText(), quyen, Date.valueOf(txtNgayThem.getText()));
        AccountStaffEntity accountDao = dao.getDataById(txtMaNhanVien.getText());
        AccountStaffEntity accountStaffEntity = new AccountStaffEntity(accountDao.getStaffAccount(), accountDao.getStaffPassword(), quyen, infStaffEntity);
        infStaffDao infStaffDao = new infStaffDao();
        if(infStaffDao.updateData(infStaffEntity) && dao.updateData(accountStaffEntity)){
            refreshView();
            refreshTableViewTK();
            refreshTableViewLuong();
            openTextField(true);
            btnXacNhanSua.setVisible(false);
            btnHuySua.setVisible(false);
            btnThem.setDisable(false);
            btnXoa.setDisable(false);
            btnSua.setVisible(true);
            tableViewNhanVien.setDisable(false);
            clearAllKhachHang();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Lỗi, không sửa được");
            alert.showAndWait();
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

        // Xoá cũng y như trên, truyền Model vào nó sẽ xoá theo mã
        if(salaryStaffDao.dellData(salaryStaffEntity) && accountStaffDao.dellData(accountStaffEntity) && infStaffDao.dellData(infStaffEntity)){
            refreshView();
            refreshTableViewTK();
            refreshTableViewLuong();
            openTextField(true);
            btnXacNhanXoa.setVisible(false);
            btnHuyXoa.setVisible(false);
            btnSua.setDisable(false);
            btnThem.setDisable(false);
            btnXoa.setVisible(true);
            tableViewNhanVien.setDisable(false);
            clearAllKhachHang();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Lỗi, không xoá được");
            alert.showAndWait();
        }
    }

    public void refreshView(){
        //Làm mới lại dữ liệu các bảng
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

    //Tab tài khoản
    public void loadTaiKhoan(){
        nvList=dao.getALl();
        tableviewTK.setItems(nvList);
        colStaffID.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfStaffByStaffId().getStaffId()));
        colAccount.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getStaffAccount()));
        colPassword.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getStaffPassword()));
        colRole.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().quyen()));
        colTenNhanVienTK.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfStaffByStaffId().getStaffName()));
    }

    public void getItemsFromTableViewTaiKhoan(){
        btnSuaTK.setDisable(false);
        AccountStaffEntity cus = (AccountStaffEntity) tableviewTK.getItems().get(tableviewTK.getSelectionModel().getSelectedIndex());
        txtMaNhanVienTK.setText(cus.getInfStaffByStaffId().getStaffId());
        txtTaiKhoanNhanVien.setText(cus.getStaffAccount());
        txtMatKhauNhanVien.setText(cus.getStaffPassword());
        if (cus.getStaffRole().equals("1")){
            cbChucVuTK.getSelectionModel().select(0);
        }
        if (cus.getStaffRole().equals("2")){
            cbChucVuTK.getSelectionModel().select(1);
        }
        File file = new File(System.getProperty("user.dir")+"\\src\\viewForm\\Picture\\StaffPic\\"+txtMaNhanVienTK.getText()+".jpg");
        if (file.exists()){
            Image image = new Image(file.toURI().toString());
            imgStaff.setImage(image);
        }
        else {
            imgStaff.setImage(new Image("viewForm/Picture/StaffPic/default.jpg"));
        }
    }

    public void updateTaiKhoanNhanVien(){
        infStaffDao infStaffDao = new infStaffDao();
        InfStaffEntity staff = infStaffDao.getDataById(txtMaNhanVienTK.getText());
        String sex = "1";
        if (staff.getStaffSex().equals("Nữ")){
            sex = "2";
        }
        String chucvu = "1";
        if (cbChucVuTK.getValue().equals("Nhân viên")){
            chucvu = "2";
        }
        InfStaffEntity infStaffEntity = new InfStaffEntity(txtMaNhanVienTK.getText(), staff.getStaffName(), sex,
                staff.getStaffBirth(), staff.getStaffAddress(),
                staff.getStaffPhone(), chucvu, staff.getStaffTimeAdd());
        AccountStaffEntity accountStaffEntity = new AccountStaffEntity(txtTaiKhoanNhanVien.getText(),
                txtMatKhauNhanVien.getText(), chucvu, infStaffEntity);
        if (dao.updateData(accountStaffEntity) && infStaffDao.updateData(infStaffEntity)){
            openButton(true, "SuaTK");
            btnSuaTK.setDisable(true);
            clearAllTK();
            openTextFieldTK(true);
            refreshTableViewTK();
            refreshView();
            btnPicStaff.setDisable(true);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Xảy ra lỗi");
            alert.setContentText("Không thêm được");
            alert.showAndWait();
        }
    }

    public void updatePicture(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn ảnh của nhân viên");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile == null){
            selectedFile = new File(System.getProperty("user.dir")+"\\src\\viewForm\\Picture\\StaffPic\\default.jpg");
        }

        File file = new File(String.valueOf(selectedFile));
        Image image = new Image(file.toURI().toString());
        imgStaff.setImage(image);
        File f = new File(System.getProperty("user.dir")+"\\src\\viewForm\\Picture\\StaffPic\\"+txtMaNhanVienTK.getText()+".jpg");
        if (f.exists()){
            try {
                BufferedImage img = ImageIO.read(selectedFile);
                ImageIO.write(img, "jpg", f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
                BufferedImage img = ImageIO.read(selectedFile);
                ImageIO.write(img, "jpg", f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void SuaTaiKhoanButton(){
        btnPicStaff.setDisable(false);
        openButton(false, "SuaTK");
        openTextFieldTK(false);
    }

    public void HuySuaTaiKhoanButton(){
        openButton(true, "SuaTK");
        btnSuaTK.setDisable(true);
        btnPicStaff.setDisable(true);
        clearAllTK();
        openTextFieldTK(true);
    }

    public void SuaLuongButton(){
        openButton(false, "SuaLuong");
        openTextFieldLuong(false);
    }

    public void HuySuaLuongButton(){
        openButton(true, "SuaLuong");
        btnSuaLuong.setDisable(true);
        clearAllLuong();
        openTextFieldLuong(true);
        lbTienHienTai.setText("0 VNĐ");
    }

    public void clearAllTK(){
        txtMaNhanVienTK.setText("");
        txtTaiKhoanNhanVien.setText("");
        txtMatKhauNhanVien.setText("");
    }

    public void clearAllLuong(){
        txtMaNhanVienLuong.setText("");
        txtLuongCoBan.setText("");
        txtTienThuong.setText("");
    }

    public void openTextFieldTK(boolean flag){
        txtMatKhauNhanVien.setDisable(flag);
        cbChucVuTK.setDisable(flag);
    }

    public void openTextFieldLuong(boolean flag){
        txtLuongCoBan.setDisable(flag);
        txtTienThuong.setDisable(flag);
    }

    public void refreshTableViewTK(){
        accountStaffDao dao = new accountStaffDao();
        nvList=dao.getALl();
        tableviewTK.setItems(nvList);
        tableviewTK.refresh();
    }

    public void refreshTableViewLuong(){
        luongList=dao.getAllSalary();
        tableviewLuong.setItems(luongList);
        tableviewLuong.refresh();
    }

    //tab Lương

    public void loadLuong(){
        luongList=dao.getAllSalary();
        tableviewLuong.setItems(luongList);
        colStaffIDLuong.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfStaffByStaffId().getStaffId()));
        colDefaultSalary.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getStaffDefaultSalary()));
        colReward.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getStaffReward()));
        colEntity.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getNumberRepair()));
        colCurentMoney.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getCurrentMoney()));
        colTenNhanVienLuong.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfStaffByStaffId().getStaffName()));
    }

    public void getItemsFromTableViewLuong(){
        btnSuaLuong.setDisable(false);
        SalaryStaffEntity cus = (SalaryStaffEntity) tableviewLuong.getItems().get(tableviewLuong.getSelectionModel().getSelectedIndex());
        txtMaNhanVienLuong.setText(cus.getInfStaffByStaffId().getStaffId());
        txtLuongCoBan.setText(cus.getStaffDefaultSalary());
        txtTienThuong.setText(cus.getStaffReward());
        txtSoDonDaSua.setText(cus.getNumberRepair());
        txtSoTienHienTai.setText(cus.getCurrentMoney());

        Locale usa = new Locale("vn", "VN");

        Currency dollars = Currency.getInstance(usa);

        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);

        double money = Double.parseDouble(cus.getCurrentMoney());

        String fomat = dollarFormat.format(money);

        lbTienHienTai.setText(fomat);
    }

    public void updateLuongNhanVien(){
        SalaryStaffEntity sa = dao.getSalaryById(txtMaNhanVienLuong.getText());
        long oldMoney = Long.valueOf(sa.getCurrentMoney()) - (Long.valueOf(sa.getStaffDefaultSalary()) + Long.valueOf(sa.getStaffReward()));
        System.out.println(oldMoney);
        long newMoney = oldMoney + (Long.valueOf(txtLuongCoBan.getText()) + Long.valueOf(txtTienThuong.getText()));
        System.out.println(newMoney);

        InfStaffEntity infStaffEntity = new InfStaffEntity(txtMaNhanVienLuong.getText());
        SalaryStaffEntity salaryStaffEntity = new SalaryStaffEntity(txtMaNhanVienLuong.getText(),
                txtLuongCoBan.getText(), txtTienThuong.getText(), txtSoDonDaSua.getText(), String.valueOf(newMoney), infStaffEntity);
        if (dao.updateDataSalary(salaryStaffEntity)){
            openButton(true, "SuaLuong");
            btnSuaLuong.setDisable(true);
            clearAllLuong();
            openTextFieldLuong(true);
            refreshTableViewLuong();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Xảy ra lỗi");
            alert.setContentText("Không sửa được");
            alert.showAndWait();
        }
    }

    //Phần hiệu ứng

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
            case "SuaTK":
                btnSuaTK.setVisible(flag);
                btnHuySuaTK.setVisible(!flag);
                btnXacNhanSuaTK.setVisible(!flag);

                tableviewTK.setDisable(!flag);

                break;
            case "SuaLuong":
                btnSuaLuong.setVisible(flag);
                btnXacNhanSuaLuong.setVisible(!flag);
                btnHuySuaLuong.setVisible(!flag);

                tableviewLuong.setDisable(!flag);

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
        loadTaiKhoan();
        loadLuong();
        cbChucVu.getItems().add("Admin");
        cbChucVu.getItems().add("Nhân viên");

        cbChucVuTK.getItems().add("Admin");
        cbChucVuTK.getItems().add("Nhân viên");

        DateTimeFormatter dayAdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        txtNgayThem.setText(dayAdd.format(now));

        imgStaff.setImage(new Image("viewForm/Picture/StaffPic/default.jpg"));
    }
}
