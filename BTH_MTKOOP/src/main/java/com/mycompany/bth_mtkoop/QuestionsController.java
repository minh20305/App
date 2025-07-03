
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bth_mtkoop;

import com.tnm.bojo.category;
import com.tnm.bojo.choice;
import com.tnm.bojo.level;
import com.tnm.bojo.question;
import com.tnm.services.CategoryServices;
import com.tnm.services.LevelServices;
import com.tnm.services.QuestionServices;
import com.tnm.utils.MyAlert;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuestionsController implements Initializable {
    @FXML private ComboBox<category> cbCates;
    @FXML private TextArea txtContent;
    @FXML private ComboBox<level> cbLevels;
    @FXML private VBox vboxChoices;
    @FXML private ToggleGroup toggleChoice;
    
    private final static CategoryServices cateServices = new CategoryServices();
    private final static LevelServices levelServices=new LevelServices();
    private final static QuestionServices questionServices=new QuestionServices();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            this.cbCates.setItems(FXCollections.observableList(cateServices.getCates()));
            this.cbLevels.setItems(FXCollections.observableList(levelServices.getLevels()));
   
        } catch(SQLException ex){
            ex.printStackTrace();
        } 
    } 
    public void addChoice(ActionEvent event) {
        HBox h = new HBox();
        h.getStyleClass().add("Main");
        
        RadioButton r = new RadioButton();
        r.setToggleGroup(toggleChoice);
        
        TextField txt = new TextField();
        txt.getStyleClass().add("Input");
        h.getChildren().addAll(r, txt);
        
        this.vboxChoices.getChildren().add(h);
    }
    
    public void addQuestion(ActionEvent event) {
        try {
            question.Builder b = new question.Builder(this.txtContent.getText(),
                    this.cbCates.getSelectionModel().getSelectedItem(),
                    this.cbLevels.getSelectionModel().getSelectedItem());
            
            for (var c: this.vboxChoices.getChildren()) {
                HBox h = (HBox) c;
                
                choice choice = new choice(((TextField)h.getChildren().get(1)).getText(), 
                            ((RadioButton)h.getChildren().get(0)).isSelected());
                b.addChoice(choice);
            }
            
            questionServices.addQuestion(b.build());
            MyAlert.getInstance().ShowMsg("Thêm câu hỏi thành công!");
        } catch (SQLException ex) {
            MyAlert.getInstance().ShowMsg("Thêm câu hỏi thất bại!");
        } catch (Exception ex) {
            MyAlert.getInstance().ShowMsg("Dữ liệu không hợp lệ!");
        }
    }
    
}
