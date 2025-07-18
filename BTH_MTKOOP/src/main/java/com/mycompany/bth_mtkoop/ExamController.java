/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bth_mtkoop;

import com.tnm.pojo.choice;
import com.tnm.pojo.question;
import com.tnm.services.exam.ExamStrategy;
import com.tnm.services.exam.ExamTypes;
import com.tnm.services.exam.FixedExamService;
import com.tnm.services.exam.SpecificExamService;
import com.tnm.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
public class ExamController implements Initializable {

    @FXML
    private ComboBox<ExamTypes> cbTypes;
    @FXML
    private ListView<question> lvQuestions;
    @FXML
    private TextField txtNum;

    private ExamStrategy exService;
    private List<question> questions;
    private Map<Integer, choice> results;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        this.cbTypes.setItems(FXCollections.observableArrayList(ExamTypes.values()));
        this.txtNum.setVisible(false);
        
        this.cbTypes.getSelectionModel().selectedItemProperty().addListener(e->{
            if(this.cbTypes.getSelectionModel().getSelectedItem()==ExamTypes.SPECIFIC){
                this.txtNum.setVisible(true);
            }else{
                this.txtNum.setVisible(false);
            } 
        });
    } 
    
    public void handleStart(ActionEvent e) throws SQLException{
        if(this.cbTypes.getSelectionModel().getSelectedItem()==ExamTypes.SPECIFIC)
            try{
                exService = new SpecificExamService(Integer.parseInt(this.txtNum.getText()));
                
            }catch(NumberFormatException ex){
                MyAlert.getInstance().showMsg("Vui long nhap so cau ho hop le!", Alert.AlertType.ERROR);
            }
        else{
            exService= new FixedExamService();
        }
        
        this.results= new HashMap<>();
        this.questions=exService.getQuestions();
        
       this.lvQuestions.setItems(FXCollections.observableList(questions));
        
        this.lvQuestions.setCellFactory(params-> new ListCell<question>(){
            @Override
            protected void updateItem(question q, boolean empty){
                   super.updateItem(q, empty);
                   if(q==null || empty == true)
                       this.setGraphic(null);
                   
                   else{
                       VBox v =  new VBox(5);
                       v.setStyle("-fx-padding: 10; -fx-border-color: gray; -fx-border-width: 2;");

                       
                       Text t = new Text(q.getContent());
                       v.getChildren().add(t);
                       
                       ToggleGroup toggle =  new ToggleGroup();
                       for(var c: q.getChoices()){
                           RadioButton r = new RadioButton(c.getContent());
                           r.setToggleGroup(toggle);
                           
                           if (results.get(q.getId()) == c) {
                            r.setSelected(true);
                        }
                        

                        r.setOnAction(e -> {
                            if (r.isSelected()) {
                                results.put(q.getId(), c);
                            }
                        });
                           
                           v.getChildren().add(r);
                           
                       }
                    this.setGraphic(v);
                       
                   
                }
            
        }
    });
    }           
                
    public void handleFinish(ActionEvent e){
        int count = 0 ;
        for (var c: this.results.values()){
            if(c.isCorrect()==true){
                count++;
            }
        }
        
        MyAlert.getInstance().showMsg(String.format("Ban lam dung %d/%d! Bam duoc %.1f diem", count, questions.size(), (double)(count)/(double)(questions.size())*10), Alert.AlertType.INFORMATION);
    }
    
    public void handleSave(ActionEvent e){
        if(questions==null || questions.isEmpty()){
            MyAlert.getInstance().showMsg("Khong co cau hoi de luu", Alert.AlertType.WARNING);
        }
        else{
            Optional<ButtonType> type =  MyAlert.getInstance().showMsg("Ban co chac chan luu de thi?", Alert.AlertType.CONFIRMATION);
            if(type.isPresent() && type.get().equals(ButtonType.OK)){
                try{
                    exService.addExam(questions);
                    MyAlert.getInstance().showMsg("Luu bai thi thanh cong!", Alert.AlertType.INFORMATION);
                }
                catch(SQLException s){
                    MyAlert.getInstance().showMsg("He thong xay ra loi, ly do: "+ s.getMessage(), Alert.AlertType.INFORMATION);
                }
            }
        }
    }
}
