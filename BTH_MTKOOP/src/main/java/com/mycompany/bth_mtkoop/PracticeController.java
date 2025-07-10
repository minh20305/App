/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bth_mtkoop;

import com.tnm.bojo.question;
import com.tnm.services.QuestionServices;
import com.tnm.utils.Configs;
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
import javafx.scene.control.TextField;
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
    @FXML private TextField txtNum;
    @FXML private VBox vboxChoices;
    @FXML private Text txtContent;
    @FXML private Text txtResult;
    private List<question> questions;
    private int currentQuest=0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    
    public void handleStart(ActionEvent e) {
        try {
            this.questions = Configs.questionServices.getQuestions(Integer.parseInt(this.txtNum.getText()));
            loadQuestion();
        } catch (SQLException ex) {
            Logger.getLogger(PracticeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void handleNext(ActionEvent e){
        this.txtResult.setText("");
        if(this.currentQuest<this.questions.size()){
            this.currentQuest++;
            this.loadQuestion();    
        }
             
    }
    
    public void handleCheck(ActionEvent e){
        question q=this.questions.get(this.currentQuest);
        for(int i=0;i<q.getChoices().size();i++){
            if(q.getChoices().get(i).isCorrect()){
                RadioButton rd = (RadioButton)this.vboxChoices.getChildren().get(i);
                
                this.txtResult.getStyleClass().clear();
                if(rd.isSelected()){
                    this.txtResult.setText("Congratulation! Exactly1");
                    this.txtResult.getStyleClass().add("Correct");
                }
                else{
                    this.txtResult.setText("Wrong answer!");
                    this.txtResult.getStyleClass().add("Wrong");
                }
                
                
            }
            
            
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
