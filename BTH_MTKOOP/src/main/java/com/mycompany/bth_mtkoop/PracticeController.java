/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bth_mtkoop;

import com.tnm.bojo.question;
import com.tnm.services.QuestionServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class PracticeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private VBox vboxChoices;
    @FXML private Text txtContent;
    private static final QuestionServices questionServices=new QuestionServices();
    private List<question> questions;
    private int currentQuest=0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            this.questions=questionServices.getQuestions(3);
        } catch (SQLException ex) {
            Logger.getLogger(PracticeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public void handleStart(ActionEvent e) {
        try {
            this.questions=questionServices.getQuestions(Integer.parseInt(this.txtContent.getText()));
            loadQuestion();
        } catch (SQLException ex) {
            Logger.getLogger(PracticeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void loadQuestion(){
        question q = this.questions.get(this.currentQuest);
        this.txtContent.setText(q.getContent());
        
        this.vboxChoices.getChildren().clear();
        ToggleGroup t = new ToggleGroup();
        for(var c: q.getChoices()){
            RadioButton r= new RadioButton(c.getContent());
            r.setToggleGroup(t);
            this.vboxChoices.getChildren().add(r);
        }
    }
}
