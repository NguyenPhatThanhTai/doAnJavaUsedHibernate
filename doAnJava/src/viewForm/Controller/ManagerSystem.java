package viewForm.Controller;



import DAO.detailInfRepairDao;
import Model.DetailInfRepairEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sun.misc.IOUtils;


import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerSystem implements Initializable {
    @FXML
    private Label txtDone;
    @FXML
    private ListView viewMessage;
    @FXML
    private ListView viewCustomer;
    @FXML
    private JFXTextField txtMessage;
    @FXML
    private JFXButton btnDelete;

    Thread thread;
    String Id;

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

    public void getmessage() {
        JSONObject json = null;
        while (true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                getAPI getAPI = new getAPI();

                json = getAPI.readJsonFromUrl("https://dht-api.000webhostapp.com/APIDoAnJava/getMessage.php?Id=" + Id);

                JSONArray jsonArray = json.getJSONArray("Check");

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        viewMessage.getItems().clear();
                        for (int i = 0; i < jsonArray.length(); i ++){
                            try {
                                if (jsonArray.getJSONObject(i).getString("msg_Staff").equals("User")){
                                    viewMessage.getItems().add("Bạn: " + jsonArray.getJSONObject(i).getString("msg_Message"));
                                }
                                else{
                                    viewMessage.getItems().add("Khách hàng: " + jsonArray.getJSONObject(i).getString("msg_Message"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }catch (Exception ex){
//                ex.printStackTrace();
            }
        }
    }

    public void sendMessage(){
        String User = "User";
        String Admin = "Admin";
        String query_url = "https://dht-api.000webhostapp.com/APIDoAnJava/createMessage.php";
        String json = "{ \"name\" : \""+Id+"\", " +
                        "\"name1\" : \""+User+"\", " +
                        "\"name2\" : \""+Admin+"\", " +
                "       \"name3\" : \""+txtMessage.getText()+"\"}";
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
            BufferedReader br = null;
            if (100 <= conn.getResponseCode() && conn.getResponseCode() <= 399) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

//            String array1 = br.readLine();
//
//            JSONObject json2 = new JSONObject(array1);
//
//            JSONArray jsonArray2 = json2.getJSONArray("Check");
//
//            //get token
//            String token = jsonArray2.get(0).toString();
            txtMessage.setText("");
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllListMessage(){
        JSONObject json = null;
        viewMessage.getItems().clear();
        while (true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                getAPI getAPI = new getAPI();

                json = getAPI.readJsonFromUrl("https://dht-api.000webhostapp.com/APIDoAnJava/getAllList.php");

                try{
                    JSONArray jsonArray = json.getJSONArray("Check");

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        viewCustomer.getItems().clear();
                        for (int i = 0; i < jsonArray.length(); i ++){
                            try {
                                viewCustomer.getItems().add(jsonArray.getJSONObject(i).getString("msg_Id"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                }catch (Exception ex){
                    System.out.println("Không có");
                }
            }catch (Exception ex){
//                ex.printStackTrace();
            }
        }
    }

    public void loadMessage(){
        btnDelete.setDisable(false);
        Id = String.valueOf(viewCustomer.getSelectionModel().getSelectedItems());
        Id = Id.substring(1, Id.length() - 1);
        System.out.println(Id);
        thread = new Thread(this::getmessage);
        thread.start();
        thread.interrupt();
    }

    public void deleteMessage(){
        try {
        JSONObject json = null;
        getAPI getAPI = new getAPI();
        json = getAPI.readJsonFromUrl("https://dht-api.000webhostapp.com/APIDoAnJava/deleteMessage.php?Id="+Id);
            JSONArray jsonArray = json.getJSONArray("Check");
        } catch (Exception e) {
//            e.printStackTrace();
        }
        btnDelete.setDisable(true);
        viewMessage.getItems().clear();
        viewCustomer.getItems().clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        thread = new Thread(this::getAllListMessage);
        thread.start();
        thread.interrupt();
    }
}
