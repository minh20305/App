package com.mycompany.bth_mtkoop;

import com.tnm.utils.MyAlert;
import com.tnm.utils.MyStage;
import com.tnm.utils.theme.Theme;
import com.tnm.utils.theme.ThemeManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class PrimaryController implements  Initializable{
    
    @FXML private ComboBox<Theme> cbThemes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbThemes.setItems(FXCollections.observableArrayList(Theme.values()));
    }
    
    public void changeTheme(ActionEvent e){
       this.cbThemes.getSelectionModel().getSelectedItem().updateTheme(this.cbThemes.getScene());
       
    }    
            
    public void handleQuestionManegement(ActionEvent e) throws IOException{
        MyStage.getInstance().showStage("questions.fxml");   
    }
    
    public void handlePratice(ActionEvent e){
        MyAlert.getInstance().ShowMsg("Coming soon....");
    }
    
    public void handleExam(ActionEvent e){
        MyAlert.getInstance().ShowMsg("Coming soon....");
    }
    
    public void handleRegister(ActionEvent e) {
        MyAlert.getInstance().ShowMsg("Comming soon...");
    }
    
    public void handleLogin(ActionEvent e) {
        MyAlert.getInstance().ShowMsg("Comming soon...");
    }
}
