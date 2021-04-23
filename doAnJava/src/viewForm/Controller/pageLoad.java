package viewForm.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import viewForm.Main;

import java.net.URL;

public class pageLoad {
    private Pane paneLoad;
    public Pane getPage(String Page){
        try{
            URL fileUrl = Main.class.getResource("/viewForm/View/"+Page);
            if (fileUrl == null){
                throw new java.io.FileNotFoundException("Page not found!!!");
            }
            paneLoad = new FXMLLoader().load(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paneLoad;
    }
}
