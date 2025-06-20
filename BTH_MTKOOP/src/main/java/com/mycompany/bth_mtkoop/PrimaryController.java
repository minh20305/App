package com.mycompany.bth_mtkoop;

import com.tnm.utils.MyAlert;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class PrimaryController {
    public void handleQuestionManegement(ActionEvent e) throws IOException{
        Scene scene = new Scene(new FXMLLoader(App.class.getResource("questions.fxml")).load());
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Quiz App");
        stage.show();
       
    }
    
    public void handlePratice(ActionEvent e){
        MyAlert.getInstance().ShowMsg("Coming soon....");
    }
    
    public void handleExam(ActionEvent e){
        MyAlert.getInstance().ShowMsg("Coming soon....");
    }
}
