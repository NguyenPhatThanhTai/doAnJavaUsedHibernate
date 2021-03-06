package viewForm.Controller;

import DAO.*;
import Model.*;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class linhKienInfController implements Initializable {
    @FXML
    private TextField txtMaLinhKien;

    @FXML
    private TextField txtTenLinhKien;

    @FXML
    private TextField txtSoLuong;

    @FXML
    private TextField txtNhaSanXuat;

    @FXML
    private TextField txtDonGia;

    @FXML
    private TextField txtNgayThem;

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



    ObservableList<InfLkEntity> rlist;
    infLKDao daoLk = new infLKDao();

    String oldEntity;

    public void loadData(){
        rlist = daoLk.getALl();
        tableListLk.setItems(rlist);
        colLK_Id.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getLkId()));
        colLK_Name.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getLkName()));
        colLK_Number.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getLkNumber()));
        colLK_Producer.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getLkProducer()));
        colLK_Price.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getLkPrice()));
        colLK_Time_Add.setCellValueFactory(cell -> new ReadOnlyStringWrapper(String.valueOf(cell.getValue().getLkTimeAdd())));
    }

    public void addnewLk(){
        if (txtTenLinhKien.getText().equals("") || txtNhaSanXuat.getText().equals("") || txtSoLuong.getText().equals("") || txtDonGia.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Kh??ng ???????c ????? tr???ng");
            alert.showAndWait();
        }
        else {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter day = DateTimeFormatter.ofPattern("dd");
            DateTimeFormatter month = DateTimeFormatter.ofPattern("MM");
            DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
            DateTimeFormatter min = DateTimeFormatter.ofPattern("mm");
            DateTimeFormatter sec = DateTimeFormatter.ofPattern("ss");

            String lkId = "LK" + day.format(now) + min.format(now) + sec.format(now);
            txtMaLinhKien.setText(lkId);

            infLKDao infLKDao = new infLKDao();

            InfLkEntity infLkEntity = new InfLkEntity(lkId, txtTenLinhKien.getText() +" "+ txtNhaSanXuat.getText(), txtSoLuong.getText(), txtNhaSanXuat.getText(), txtDonGia.getText(), Date.valueOf(txtNgayThem.getText()));

            if (infLKDao.addData(infLkEntity)) {

                doanhthuDao doanhthuDao = new doanhthuDao();
                InfDoanhThuThangEntity infDoanhThuThangEntity1 =
                        doanhthuDao.getByDateDoanhThuThang(Date.valueOf(year.format(now) +"-"+ month.format(now)+"-1"));
                if (infDoanhThuThangEntity1 != null){
                    Long money = Long.valueOf(infDoanhThuThangEntity1.getOutputMoney()) + (Long.valueOf(txtDonGia.getText()) * Long.valueOf(txtSoLuong.getText()));
                    Long profit = Long.valueOf(infDoanhThuThangEntity1.getInputMoney()) - money;
                    InfDoanhThuThangEntity infDoanhThuThangEntity2 =
                            new InfDoanhThuThangEntity("DTT"+year.format(now)+month.format(now), infDoanhThuThangEntity1.getInputMoney(), String.valueOf(money),
                                    infDoanhThuThangEntity1.getEntity(), infDoanhThuThangEntity1.getStaffSalary(), String.valueOf(profit), Date.valueOf(year.format(now) +"-"+ month.format(now)+"-1"));
                    doanhthuDao.updateDoanhThuThang(infDoanhThuThangEntity2);
                }

                refreshView();
                openTextField(true);
                clearItem();
                btnXacNhanThem.setVisible(false);
                btnHuyThem.setVisible(false);
                btnSua.setDisable(false);
                btnXoa.setDisable(false);
                btnThem.setVisible(true);
                tableListLk.setDisable(false);
            }
        }
    }

    public void updateLK(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter day = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter month = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
        if (txtTenLinhKien.getText().equals("") || txtNhaSanXuat.getText().equals("") || txtSoLuong.getText().equals("") || txtDonGia.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Kh??ng ???????c ????? tr???ng");
            alert.showAndWait();
        }
        else {
            InfLkEntity infLkEntity = new InfLkEntity(txtMaLinhKien.getText(), txtTenLinhKien.getText() + " " + txtNhaSanXuat.getText(), txtSoLuong.getText(), txtNhaSanXuat.getText(), txtDonGia.getText(), Date.valueOf(txtNgayThem.getText()));
            infLKDao infLKDao = new infLKDao();
            if(infLKDao.updateData(infLkEntity)){
                doanhthuDao doanhthuDao = new doanhthuDao();
                InfDoanhThuThangEntity infDoanhThuThangEntity1 =
                        doanhthuDao.getByDateDoanhThuThang(Date.valueOf(year.format(now) +"-"+ month.format(now)+"-1"));
                if (infDoanhThuThangEntity1 != null){
                    int newEntity = 0;
                    if (Integer.parseInt(txtSoLuong.getText()) > Integer.parseInt(oldEntity)){
                        newEntity = Integer.parseInt(txtSoLuong.getText()) - Integer.parseInt(oldEntity);
                    }
                    Long money = Long.valueOf(infDoanhThuThangEntity1.getOutputMoney()) + (Long.valueOf(txtDonGia.getText()) * newEntity);
                    Long profit = Long.valueOf(infDoanhThuThangEntity1.getInputMoney()) - money;
                    InfDoanhThuThangEntity infDoanhThuThangEntity2 =
                            new InfDoanhThuThangEntity("DTT"+year.format(now)+month.format(now), infDoanhThuThangEntity1.getInputMoney(), String.valueOf(money),
                                    infDoanhThuThangEntity1.getEntity(), infDoanhThuThangEntity1.getStaffSalary(), String.valueOf(profit), Date.valueOf(year.format(now) +"-"+ month.format(now)+"-1"));
                    doanhthuDao.updateDoanhThuThang(infDoanhThuThangEntity2);
                }
                refreshView();
                openTextField(true);
                clearItem();
                btnXacNhanSua.setVisible(false);
                btnHuySua.setVisible(false);
                btnThem.setDisable(false);
                btnXoa.setDisable(true);
                btnSua.setDisable(true);
                btnSua.setVisible(true);
                tableListLk.setDisable(false);
            }
        }
    }

    public void getItemFromTableViewLK(){
        btnThem.setDisable(true);
        btnSua.setDisable(false);
        btnXoa.setDisable(false);
        InfLkEntity de = (InfLkEntity) tableListLk.getItems().get(tableListLk.getSelectionModel().getSelectedIndex());
            txtMaLinhKien.setText(de.getLkId());
            txtTenLinhKien.setText(de.getLkName());
            txtNhaSanXuat.setText(de.getLkProducer());
            txtDonGia.setText(de.getLkPrice());
            txtSoLuong.setText(de.getLkNumber());
            txtNgayThem.setText(String.valueOf(de.getLkTimeAdd()));
            oldEntity = txtSoLuong.getText();
    }

    public void deleteLK(){
        if (txtTenLinhKien.getText().equals("") || txtNhaSanXuat.getText().equals("") || txtSoLuong.getText().equals("") || txtDonGia.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Kh??ng ???????c ????? tr???ng");
            alert.showAndWait();
        }
        else {
            infLKDao infLKDao = new infLKDao();
            InfLkEntity infLkEntity = new InfLkEntity();
            infLkEntity.setLkId(txtMaLinhKien.getText());

            if(infLKDao.dellData(infLkEntity)){
                refreshView();
                openTextField(true);
                clearItem();
                btnXacNhanXoa.setVisible(false);
                btnHuyXoa.setVisible(false);
                btnSua.setDisable(false);
                btnThem.setDisable(false);
                btnXoa.setVisible(true);
                tableListLk.setDisable(false);
            }
        }
    }

    public void refreshView(){
        infLKDao dao = new infLKDao();
        rlist = dao.getALl();
        tableListLk.setItems(rlist);

        tableListLk.refresh();
    }

    public void checkDonGia(){
        if (!txtDonGia.getText().matches("\\d*")) {
            txtDonGia.setText(txtDonGia.getText().replaceAll("[^\\d]", ""));
        }
    }

    public void checkSoLuong(){
        if (!txtSoLuong.getText().matches("\\d*")) {
            txtSoLuong.setText(txtSoLuong.getText().replaceAll("[^\\d]", ""));
        }
    }

    public void ThemLinhKienButton(){
        txtMaLinhKien.setText("H??? th???ng ?????nh s???n");
        openButton(false, "Add");
    }

    public void HuyThemLKButton(){ openButton(true, "HuyAdd");}

    public void SuaLKButton(){openButton(false, "Sua");}

    public void HuySuaLKButton(){openButton(true, "HuySua");}

    public void XoaLKButton(){openButton(false, "Xoa");}

    public void HuyXoaLKButton(){openButton(true, "HuyXoa");}

    public void openButton(boolean flag, String nut){
        switch (nut){
            case "Add":
                openTextField(false);
                btnThem.setVisible(flag);
                btnXacNhanThem.setVisible(true);
                btnHuyThem.setVisible(true);

                tableListLk.setDisable(!flag);


                break;
            case "HuyAdd":
                openTextField(true);
                clearItem();
                btnThem.setVisible(flag);
                btnXacNhanThem.setVisible(!flag);
                btnHuyThem.setVisible(!flag);

                tableListLk.setDisable(!flag);

                break;
            case "Sua":
                openTextField(false);
                btnSua.setVisible(flag);
                btnXacNhanSua.setVisible(true);
                btnHuySua.setVisible(true);

                tableListLk.setDisable(!flag);

                break;
            case "HuySua":
                openTextField(true);
                clearItem();
                btnSua.setVisible(flag);
                btnSua.setDisable(flag);
                btnThem.setDisable(!flag);
                btnXoa.setDisable(flag);
                btnXacNhanSua.setVisible(!flag);
                btnHuySua.setVisible(!flag);

                tableListLk.setDisable(!flag);

                break;
            case "Xoa":
                btnXoa.setVisible(flag);
                btnXacNhanXoa.setVisible(true);
                btnHuyXoa.setVisible(true);

                tableListLk.setDisable(!flag);

                break;
            case "HuyXoa":
                btnXoa.setVisible(flag);
                btnSua.setDisable(flag);
                btnXoa.setDisable(flag);
                btnXacNhanXoa.setVisible(!flag);
                btnHuyXoa.setVisible(!flag);

                tableListLk.setDisable(!flag);

                break;
        }
    }

    public void openTextField(boolean flag){
        txtTenLinhKien.setDisable(flag);
        txtDonGia.setDisable(flag);
        txtNhaSanXuat.setDisable(flag);
        txtSoLuong.setDisable(flag);
    }

    public void clearItem(){
        txtTenLinhKien.setText("");
        txtNhaSanXuat.setText("");
        txtSoLuong.setText("");
        txtDonGia.setText("");
        txtMaLinhKien.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
        DateTimeFormatter dayAdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        txtNgayThem.setText(dayAdd.format(now));
    }
}
