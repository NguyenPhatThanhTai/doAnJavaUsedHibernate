package viewForm.Controller;



import DAO.detailInfRepairDao;
import Model.DetailInfRepairEntity;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.json.JSONObject;
import sun.misc.IOUtils;


import java.awt.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerSystem implements Initializable {
    @FXML
    private Label txtDone;

    Thread thread;

    ObservableList<DetailInfRepairEntity> rlist;
    detailInfRepairDao dao = new detailInfRepairDao();

    public void Nhan(){
        thread=new Thread(this::uploaData);
        thread.start();
    }

    public void uploaData(){
        String query_url = "https://apimywebsite.000webhostapp.com/APIDoAnJava/upload.php";
        rlist = dao.getALl();
        String status="Chưa hoàn thành";
        int i = 1;
        for (DetailInfRepairEntity list:rlist) {
            if (list.getRepairStatus().equals("1")){
                status = "Đã hoàn thành";
            }
            String json = "{ \"name\" : \""+list.getInfRepairByRepairId().getRepairId()+"\", " +
                    "       \"name1\" : \""+list.getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerName()+"\", " +
                    "       \"name2\" : \""+list.getInfRepairByRepairId().getLaptopName()+"\", " +
                    "       \"name3\" : \""+list.getInfRepairByRepairId().getInfCustomersByCustomerId().getCustomerEmail()+"\", " +
                    "       \"name4\" : \""+status+"\" }";
            try {
                URL url = new URL(query_url);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5000);
                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestMethod("POST");
                OutputStream os = conn.getOutputStream();
                os.write(json.getBytes("UTF-8"));
                os.close();
                // read the response
                InputStream in = new BufferedInputStream(conn.getInputStream());
//            String result = IOUtils.;
                JSONObject myResponse = new JSONObject(in);
                System.out.println("Thành công khách hàng thứ" + ++i);
                in.close();
                conn.disconnect();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        txtDone.setVisible(true);
        thread.interrupt();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
