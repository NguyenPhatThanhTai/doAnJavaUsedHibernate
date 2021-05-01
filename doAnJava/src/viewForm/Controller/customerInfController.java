package viewForm.Controller;

import DAO.detailInfRepairDao;
import DAO.infCustomerDao;
import DAO.infLKDao;
import DAO.infRepairDao;
import Model.*;
import com.jfoenix.controls.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class customerInfController implements Initializable {

    //Phần tab thông tin khách hàng

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
    private TableView<DetailInfRepairEntity> tableListCustomer;
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
    private JFXToggleButton txtSuaRepair;
    @FXML
    private DatePicker txtNgayHen;
    @FXML
    private TextField txtTien;
    @FXML
    private JFXComboBox cbLocLK;
    @FXML
    private JFXButton btnHuySua;
    @FXML
    private JFXButton btnHuySuaRepair;
    @FXML
    private JFXButton btnSuaRepair;
    @FXML
    private JFXButton btnXacNhanSua;
    @FXML
    private JFXButton btnXacNhanSuaRepair;
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

    //Phần tab Tiếp nhận đơn

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

    //Phần chọn linh kiện trong tab Tiếp nhận đơn

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
    private JFXButton btnResetDanhSach;
    @FXML
    private JFXTextArea txtLkDaChon;

    //Phần tab tình trạng sữa

    @FXML
    private TableView<DetailInfRepairEntity> tableListStatus;
    @FXML
    private TableColumn<DetailInfRepairEntity, String> colRepairIdStatus;
    @FXML
    private TableColumn<DetailInfRepairEntity, String> colCustomerIdStatus;
    @FXML
    private TableColumn<DetailInfRepairEntity, String> colCustomerNameStatus;
    @FXML
    private TableColumn<DetailInfRepairEntity, String> colLaptopNameStatus;
    @FXML
    private TableColumn<DetailInfRepairEntity, String> colStatusStatus;
    @FXML
    private TableColumn<DetailInfRepairEntity, String> colMoneyStatus;
    @FXML
    private TableColumn<DetailInfRepairEntity, String> colStaffIdStatus;
    @FXML
    private TableColumn<DetailInfRepairEntity, String> colNgayThemStatus;
    @FXML
    private JFXButton btnSuaStatus;
    @FXML
    private JFXButton btnHuySuaStatus;
    @FXML
    private JFXButton btnXacNhanSuaStatus;
    @FXML
    private JFXButton btnHoanThanhDon;
    @FXML
    private JFXButton btnHuyHoanThanhDon;
    @FXML
    private JFXButton btnXacNhanHoanThanhDon;
    @FXML
    private JFXTextField txtMaSuaChuaRepair;
    @FXML
    private JFXTextField txtTinhTrangSuaStatus;
    @FXML
    private ImageView loading;
    @FXML
    private Label lbHoanThanh;
    @FXML
    private Label txtCapNhatDuLieu;
    ObservableList<DetailInfRepairEntity> rlist;
    ObservableList<InfLkEntity> lkList;
    detailInfRepairDao dao = new detailInfRepairDao();
    infLKDao lkDao = new infLKDao();
    List<String> listLK = new ArrayList<>();

    Thread thread;
    String idForSever, statusForServer, tinhtrangsua;

    String maNv = "";
    String name = "";
    String chucVu = "";

    InfStaffEntity infStaffEntity;

    //Phần tab Thông tin khách hàng

    public void showInfomation(InfStaffEntity infStaffEntity){
        this.infStaffEntity = infStaffEntity;
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
        try {
            btnThem.setDisable(true);
            btnSua.setDisable(false);
            btnXoa.setDisable(false);

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
        }catch (Exception ex){
            ex.printStackTrace();
        }
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
            clearAllKhachHang();
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
            clearAllKhachHang();
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
            openTextField(true);
            btnXacNhanXoa.setVisible(false);
            btnHuyXoa.setVisible(false);
            btnSua.setDisable(false);
            btnThem.setDisable(false);
            btnXoa.setVisible(true);
            tableListCustomer.setDisable(false);
            clearAllKhachHang();
            clearAllSuaChua();
        }
    }

    //Phần tab Tiếp nhận đơn

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
        try {
            btnSuaRepair.setDisable(false);

            DetailInfRepairEntity de = (DetailInfRepairEntity) tableListRepair.getItems().get(tableListRepair.getSelectionModel().getSelectedIndex());
            txtMaSuaChua.setText(de.getInfRepairByRepairId().getRepairId());
            txtTenkhachHangSuaChua.setText(de.getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerName());
            if (de.getRepairNote().equals("Sửa lấy ngay")){
                txtSuaRepair.setSelected(false);
            }
            else {
                txtSuaRepair.setSelected(true);
            }
            txtNgayHen.setValue(LocalDate.parse(String.valueOf(de.getRepairAppointment())));
            txtNhanVienTiepNhan.setText(de.getInfRepairByRepairId().getInfStaffByStaffId().getStaffId());
            txtTinhTrang.setText(de.getInfRepairByRepairId().getLaptopStatus());
            txtLkDaChon.setText(de.getRepairReason());
            txtLaptopName.setText(String.valueOf(de.getInfRepairByRepairId().getLaptopName()));
            txtTien.setText(de.getRepairMoney());
        }catch (Exception ex){
            ex.printStackTrace();
        }
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
                InfLkEntity de = (InfLkEntity) tableListLinhKien.getItems().get(tableListLinhKien.getSelectionModel().getSelectedIndex());
                listLK.add(de.getLkName());

        txtLkDaChon.setText(listLK.toString());

        long money1 = Long.parseLong(txtTien.getText());
        long money2 = Long.parseLong(de.getLkPrice());
        long tienLK = Long.parseLong(txtTien.getText() + de.getLkPrice());
        long tien = money1+money2;
        txtTien.setText(String.valueOf(tien));
    }

    public void resetDanhSach(){
        listLK.clear();
        txtLkDaChon.setText("");
        txtTien.setText("0");
    }

    public void refreshView(){
        detailInfRepairDao dao = new detailInfRepairDao();
        rlist = dao.getALl();
        tableListCustomer.setItems(rlist);
        tableListRepair.setItems(rlist);
        tableListStatus.setItems(rlist);

        tableListStatus.refresh();
        tableListCustomer.refresh();
        tableListRepair.refresh();
    }

    public void updateRepair(){
        String idSplit = txtMaSuaChua.getText();
        String[] parts = idSplit.split("RP");
        String suaRepair = "Sủa lấy ngay";
        infCustomerDao infCustomerDao = new infCustomerDao();
        InfRepairEntity infRepairEntity = new InfRepairEntity(txtMaSuaChua.getText(), txtLaptopName.getText(), txtTinhTrang.getText(),infCustomerDao.getDataById("KH"+parts[1]), infStaffEntity);
        if (txtSuaRepair.isSelected()){
            suaRepair = "Hẹn ngày lấy";
        }
        DetailInfRepairEntity detailInfRepairEntity = new DetailInfRepairEntity("DT"+parts[1], txtLkDaChon.getText(), suaRepair, "2", Date.valueOf(txtNgayHen.getValue()), txtTien.getText(), infRepairEntity);
        infRepairDao infRepairDao = new infRepairDao();
        detailInfRepairDao detailInfRepairDao = new detailInfRepairDao();
        if(infRepairDao.updateData(infRepairEntity) && detailInfRepairDao.updateData(detailInfRepairEntity)){
            refreshView();
            openTextFieldRepair(false);
            openButton(true, "SuaRepair");
            clearAllSuaChua();
        }
    }

    //Phần tab Tình trạng sữa

    public void loadStatus(){
        tableListStatus.setItems(rlist);
        colRepairIdStatus.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfRepairByRepairId().getRepairId()));
        colCustomerIdStatus.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerId()));
        colCustomerNameStatus.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerName()));
        colLaptopNameStatus.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfRepairByRepairId().getLaptopName()));
        colStatusStatus.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getRepairStatus()));
        colMoneyStatus.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getRepairMoney()));
        colStaffIdStatus.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getInfRepairByRepairId().getInfStaffByStaffId().getStaffId()));
        colNgayThemStatus.setCellValueFactory(cell -> new ReadOnlyStringWrapper(String.valueOf(cell.getValue().getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerTimeAdd())));
    }

    public void getItemFormTableListStatus(){
        try{
            btnHoanThanhDon.setDisable(false);
            btnSuaStatus.setDisable(false);
            String status = "Chưa hoàn thành";

            DetailInfRepairEntity de = (DetailInfRepairEntity) tableListStatus.getItems().get(tableListStatus.getSelectionModel().getSelectedIndex());
            txtMaSuaChuaRepair.setText(de.getInfRepairByRepairId().getRepairId());
            if (de.getRepairStatus().equals("1")){
                status = "Hoàn thành";
            }
            txtTinhTrangSuaStatus.setText(status);
            tinhtrangsua = status;
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void startTheardUpdateTinhTrang(){
        thread = new Thread(this::updateTinhTrang);
        thread.start();
    }

    public void updateTinhTrang(){
        try{
            txtCapNhatDuLieu.setVisible(true);
            txtCapNhatDuLieu.setText("-Đang cập nhật dữ liệu-");
            btnXacNhanSuaStatus.setDisable(true);
            btnHuySuaStatus.setDisable(true);
            loading.setVisible(true);
            String idSplit2 = txtMaSuaChuaRepair.getText();
            String[] parts2 = idSplit2.split("RP");
            String status = "2";
            infRepairDao infRepairDao = new infRepairDao();
            if (txtTinhTrangSuaStatus.getText().equals("Chưa hoàn thành")){
                status = "1";
            }

            detailInfRepairDao detailInfRepairDao = new detailInfRepairDao();
            DetailInfRepairEntity detailInfRepairEntity1 = detailInfRepairDao.getDataById("DT"+parts2[1]);
            DetailInfRepairEntity detailInfRepairEntity2 =
                    new DetailInfRepairEntity(
                            detailInfRepairEntity1.getDetailId(),
                            detailInfRepairEntity1.getRepairReason(),
                            detailInfRepairEntity1.getRepairNote(),
                            status,
                            detailInfRepairEntity1.getRepairAppointment(),
                            detailInfRepairEntity1.getRepairMoney(),infRepairDao.getDataById(txtMaSuaChuaRepair.getText()));
            if(detailInfRepairDao.updateData(detailInfRepairEntity2)){
                if (txtTinhTrangSuaStatus.getText().equals("Hoàn thành")){
                    idForSever=txtMaSuaChuaRepair.getText();
                    statusForServer="not";
                    thread = new Thread(this::updateInSever);
                    thread.start();
                }
                else {
                    idForSever=txtMaSuaChuaRepair.getText();
                    statusForServer="Ok";
                    thread = new Thread(this::updateInSever);
                    thread.start();
                }
                refreshView();
                txtMaSuaChuaRepair.setText("");
                txtTinhTrangSuaStatus.setText("");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateInSever(){
        try {
        URL urlForGetRequest = new URL("https://apimywebsite.000webhostapp.com/APIDoAnJava/update.php?Id="+idForSever+"&Status="+statusForServer);
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
//        conection.setRequestProperty("Id", "Status"); // set userId its a sample here
        int responseCode = conection.getResponseCode();
            System.out.println(responseCode);
        if (tinhtrangsua.equals("Chưa hoàn thành")){
            sendMail();
        }
        loading.setVisible(false);
        btnXacNhanSuaStatus.setDisable(false);
        btnHuySuaStatus.setDisable(false);
        openButton(true, "CapNhat");
        lbHoanThanh.setVisible(true);
        Thread.sleep(3000);
        lbHoanThanh.setVisible(false);
        txtCapNhatDuLieu.setVisible(false);
        thread.interrupt();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public boolean sendMail(){
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("herroseven@gmail.com", "@#Taitutoi952*000@#");
                        }
                    });
            try {
                Message message = new MimeMessage(session);
                message.setHeader("Content-Type", "text/plain; charset=UTF-8");
                message.setFrom(new InternetAddress("UNIFY"));

                String idSplit3 = idForSever;
                String[] parts3 = idSplit3.split("RP");

                detailInfRepairDao detailInfRepairDao = new detailInfRepairDao();
                DetailInfRepairEntity de2 = detailInfRepairDao.getDataById("DT"+ parts3[1]);

                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(de2.getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerEmail()));

                Locale usa = new Locale("en", "US");

                Currency dollars = Currency.getInstance(usa);

                NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);

                double money = Double.parseDouble(de2.getRepairMoney());

                String fomat = dollarFormat.format(money);

                message.setSubject("Công ty DHT - Thông báo về việc hoàn thành sữa chữa.");
                String text ="<img src="+"/viewForm/Picture/Pic/anhsendmail.png"+">" + "<br>" +
                        "<b>Xin chào khách hàng: </b>"+ de2.getInfRepairByRepairId().getInfStaffByStaffId().getStaffId() + "<br>" + "<br>" +
                        "<b>Họ và tên: </b>" + de2.getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerName() + "<br>" + "<br>" +
                        "<b>Tên laptop: </b>" + de2.getInfRepairByRepairId().getLaptopName() + "<br>" + "<br>" +
                        "<b>Cần sữa chữa: </b>" + de2.getRepairReason() + "<br>" + "<br>" +
                        "<b>Số tiền: </b>" + fomat + " VNĐ" + "<br>" + "<br>" +
                        "<b>Được gửi sữa vào ngày: </b>" + de2.getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerTimeAdd() + "<br>" + "<br>" +
                        "<b>Xin được thông báo với quý khách về việc đã hoàn thành sữa chữa laptop, xin quý khách vui lòng đến cửa hàng thanh toán và nhận lại laptop, chân thành cảm ơn!!!</b>" + "<br>" + "<br>" + "<br>" +
                        "<b>-Công ty tư nhân hữu hạng ba thành viên DHT trân trọng thông báo!!!-</b>";
                message.setContent(text, "text/html; charset=utf-8");
                Transport.send(message);
                txtCapNhatDuLieu.setVisible(false);
                return true;
            }catch (Exception ex){
                ex.printStackTrace();
                return false;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    //Phần hiệu ứng

    public void ThemKhachHangButton(){
        txtMaKhachHang.setText("Hệ thống định sẵn"); openButton(false, "Add");
    }

    public void HuyThemKhachHangButton(){
        openButton(true, "HuyAdd");
        clearAllKhachHang();
    }

    public void SuaKhachHangButton(){
        openButton(false, "Sua");
    }

    public void HuySuaKhachHangButton(){
        openButton(true, "HuySua");
        clearAllKhachHang();
    }

    public void XoaKhachHangButton(){
        openButton(false, "Xoa");
    }

    public void HuyXoaKhachHangButton(){
        openButton(true, "HuyXoa");
        clearAllKhachHang();
    }

    public void SuaRepairButton(){
        openButton(false, "SuaRepair");
        tableListLinhKien.setDisable(false);
        btnResetDanhSach.setDisable(false);
        cbLocLK.setDisable(false);
        txtLkDaChon.setDisable(false);
    }

    public void HuySuaRepairButton(){
        openButton(true, "SuaRepair");
        clearAllSuaChua();
    }

    public void CapNhatTinhTrangButton(){
        openButton(false, "CapNhat");
    }

    public void HuyCapNhatTinhTrang(){
        openButton(true, "CapNhat");
    }

    public void HoanThanhDonButtom(){
        openButton(false, "HoanThanh");
    }

    public void HuyHoanThanhDonButton(){
        openButton(true, "HoanThanh");
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
            case "SuaRepair":
                btnSuaRepair.setVisible(flag);
                btnXacNhanSuaRepair.setVisible(!flag);
                btnHuySuaRepair.setVisible(!flag);

                tableListRepair.setDisable(!flag);

                openTextFieldRepair(flag);
                break;
            case "CapNhat":
                btnSuaStatus.setVisible(flag); // false
                btnXacNhanSuaStatus.setVisible(!flag);
                btnHuySuaStatus.setVisible(!flag);
                btnHoanThanhDon.setDisable(!flag);
                tableListStatus.setDisable(!flag);
                break;
            case "HoanThanh":
                btnHoanThanhDon.setVisible(flag); // false
                btnXacNhanHoanThanhDon.setVisible(!flag);
                btnHuyHoanThanhDon.setVisible(!flag);
                btnSuaStatus.setDisable(!flag);
                tableListStatus.setDisable(!flag);
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

    public void openTextFieldRepair(boolean flag){
        txtLaptopName.setDisable(flag);
        txtTinhTrang.setDisable(flag);
        txtSuaRepair.setDisable(flag);
        txtNgayHen.setDisable(flag);
        txtTien.setDisable(flag);
    }

    public void clearAllKhachHang(){
        txtTenKhachHang.setText("");
        txtGioiTinh.setText("");
        txtEmail.setText("");
        txtSoDienThoai.setText("");
        txtMaKhachHang.setText("");
        btnSua.setDisable(true);
        btnXoa.setDisable(true);
    }

    public void clearAllSuaChua(){
        txtMaSuaChua.setText("");
        txtTenKhachHang.setText("");
        txtLaptopName.setText("");
        txtTinhTrang.setText("");
        txtSuaRepair.setSelected(false);
        txtTien.setText("");
        txtTenkhachHangSuaChua.setText("");
        txtNhanVienTiepNhan.setText("");

        btnSuaRepair.setDisable(true);
        tableListLinhKien.setDisable(true);
        btnResetDanhSach.setDisable(true);
        cbLocLK.setDisable(true);
        txtLkDaChon.setDisable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
        loadDataRepair();
        loadStatus();
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
