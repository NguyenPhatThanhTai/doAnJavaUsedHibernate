package viewForm.Controller;

import DAO.infCustomerDao;
import Model.InfCustomersEntity;
import Model.customerModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class customerInfController implements Initializable {
    ObservableList<customerModel> listCustomer;
    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

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

    ObservableList<InfCustomersEntity> clist;

    public void loadData(){
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


//        doDao dao = new doDao();
//        // khi load form sẽ hiển thị luôn
//        colCustomerId.setCellValueFactory(new PropertyValueFactory<customerModel, String>("Customer_Id"));
//        colCustomerName.setCellValueFactory(new PropertyValueFactory<customerModel, String>("Customer_Name"));
//        colCustomerSex.setCellValueFactory(new PropertyValueFactory<customerModel, String>("Customer_Sex"));
//        colCustomerBirth.setCellValueFactory(new PropertyValueFactory<customerModel, String>("Customer_Birth"));
//        colCustomerEmail.setCellValueFactory(new PropertyValueFactory<customerModel, String>("Customer_Email"));
//        colCustomerPhone.setCellValueFactory(new PropertyValueFactory<customerModel, String>("Customer_Phone"));
//        colCustomerTimeAdd.setCellValueFactory(new PropertyValueFactory<customerModel, Date>("Customer_TimeAdd"));
//
//        listCustomer = dao.getAllCustomer();
//        tableListCustomer.setItems(listCustomer);
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
                lbXinchao.setText(cus.getCustomerName());
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }
}
