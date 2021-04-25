package viewForm.Controller;

import DAO.detailInfRepairDao;
import DAO.infCustomerDao;
import DAO.infLKDao;
import DAO.infRepairDao;
import Model.*;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter day = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter min = DateTimeFormatter.ofPattern("mm");
        DateTimeFormatter sec = DateTimeFormatter.ofPattern("ss");

        String lkId = "LK" + day.format(now) + min.format(now) + sec.format(now);
        txtMaLinhKien.setText(lkId);

        infLKDao infLKDao = new infLKDao();

        InfLkEntity infLkEntity = new InfLkEntity(lkId, txtTenLinhKien.getText(), txtSoLuong.getText(), txtNhaSanXuat.getText(), txtDonGia.getText(), Date.valueOf(txtNgayThem.getText()));

        if (infLKDao.addData(infLkEntity)){
            refreshView();
            openTextField(false);
            btnXacNhanThem.setVisible(false);
            btnHuyThem.setVisible(false);
            btnSua.setDisable(false);
            btnXoa.setDisable(false);
            btnThem.setVisible(true);
            tableListLk.setDisable(false);
        }
    }

    public void updateLK(){
        InfLkEntity infLkEntity = new InfLkEntity(txtMaLinhKien.getText(), txtTenLinhKien.getText(), txtSoLuong.getText(), txtNhaSanXuat.getText(), txtDonGia.getText(), Date.valueOf(txtNgayThem.getText()));
        infLKDao infLKDao = new infLKDao();
        if(infLKDao.updateData(infLkEntity)){
            refreshView();
            openTextField(false);
            btnXacNhanSua.setVisible(false);
            btnHuySua.setVisible(false);
            btnThem.setDisable(false);
            btnXoa.setDisable(false);
            btnSua.setVisible(true);
            tableListLk.setDisable(false);
        }
    }

    public void getItemFromTableViewLK(){
        tableListLk.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                InfLkEntity de = (InfLkEntity) tableListLk.getItems().get(tableListLk.getSelectionModel().getSelectedIndex());
                txtMaLinhKien.setText(de.getLkId());
                txtTenLinhKien.setText(de.getLkName());
                txtNhaSanXuat.setText(de.getLkProducer());
                txtDonGia.setText(de.getLkPrice());
                txtSoLuong.setText(de.getLkNumber());
                txtNgayThem.setText(String.valueOf(de.getLkTimeAdd()));
            }
        });
    }

    public void deleteLK(){
        infLKDao infLKDao = new infLKDao();
        InfLkEntity infLkEntity = new InfLkEntity();
        infLkEntity.setLkId(txtMaLinhKien.getText());

        if(infLKDao.dellData(infLkEntity)){
            refreshView();
            openTextField(false);
            btnXacNhanXoa.setVisible(false);
            btnHuyXoa.setVisible(false);
            btnSua.setDisable(false);
            btnThem.setDisable(false);
            btnXoa.setVisible(true);
            tableListLk.setDisable(false);
        }
    }

    public void refreshView(){
        infLKDao dao = new infLKDao();
        rlist = dao.getALl();
        tableListLk.setItems(rlist);

        tableListLk.refresh();
    }

    public void ThemLinhKienButton(){
        txtMaLinhKien.setText("Hệ thống định sẵn");
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
                btnThem.setVisible(flag);
                btnSua.setDisable(true);
                btnXoa.setDisable(true);
                btnXacNhanThem.setVisible(true);
                btnHuyThem.setVisible(true);

                tableListLk.setDisable(!flag);

                openTextField(flag);
                break;
            case "HuyAdd":
                btnThem.setVisible(flag);
                btnSua.setDisable(!flag);
                btnXoa.setDisable(!flag);
                btnXacNhanThem.setVisible(!flag);
                btnHuyThem.setVisible(!flag);

                tableListLk.setDisable(!flag);

                openTextField(flag);
                break;
            case "Sua":
                btnSua.setVisible(flag);
                btnThem.setDisable(true);
                btnXoa.setDisable(true);
                btnXacNhanSua.setVisible(true);
                btnHuySua.setVisible(true);

                tableListLk.setDisable(!flag);

                openTextField(flag);
                break;
            case "HuySua":
                btnSua.setVisible(flag);
                btnThem.setDisable(!flag);
                btnXoa.setDisable(!flag);
                btnXacNhanSua.setVisible(!flag);
                btnHuySua.setVisible(!flag);

                tableListLk.setDisable(!flag);

                openTextField(flag);
                break;
            case "Xoa":
                btnXoa.setVisible(flag);
                btnThem.setDisable(true);
                btnThem.setDisable(true);
                btnXacNhanXoa.setVisible(true);
                btnHuyXoa.setVisible(true);

                tableListLk.setDisable(!flag);

                openTextField(flag);
                break;
            case "HuyXoa":
                btnXoa.setVisible(flag);
                btnThem.setDisable(!flag);
                btnThem.setDisable(!flag);
                btnXacNhanXoa.setVisible(!flag);
                btnHuyXoa.setVisible(!flag);

                tableListLk.setDisable(!flag);

                openTextField(flag);
                break;
        }
    }

    public void openTextField(boolean flag){
        txtTenLinhKien.setDisable(flag);
        txtDonGia.setDisable(flag);
        txtNhaSanXuat.setDisable(flag);
        txtSoLuong.setDisable(flag);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
        DateTimeFormatter dayAdd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        txtNgayThem.setText(dayAdd.format(now));
    }
}
