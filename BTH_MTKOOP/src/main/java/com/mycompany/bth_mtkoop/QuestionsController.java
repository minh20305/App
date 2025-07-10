
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
import com.tnm.utils.Configs;
import com.tnm.utils.MyAlert;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML private TableView<question> tbQuestions;
    @FXML private TextField txtSearch;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            this.cbCates.setItems(FXCollections.observableList(Configs.cateServices.getCates()));
            this.cbLevels.setItems(FXCollections.observableList(Configs.levelServices.getLevels()));
            
            this.loadColumns();
            this.loadQuestion(Configs.questionServices.getQuestions());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        this.txtSearch.textProperty().addListener((e)->{
            try {
                this.loadQuestion(Configs.questionServices.getQuestions(this.txtSearch.getText()));
            } catch (SQLException ex) {
                Logger.getLogger(QuestionsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    } 
    public void addChoice(ActionEvent event) {
        HBox h = new HBox();
        h.getStyleClass().add("main");
        
        RadioButton r = new RadioButton();
        r.setToggleGroup(toggleChoice);
        
        TextField txt = new TextField();
        txt.getStyleClass().add("input");
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
                
                choice ch = new choice(((TextField)h.getChildren().get(1)).getText(), 
                            ((RadioButton)h.getChildren().get(0)).isSelected());
                b.addChoice(ch);
            }
            
            Configs.questionServices.addQuestion(b.build());
            MyAlert.getInstance().ShowMsg("Thêm câu hỏi thành công!");
            
            
            this.tbQuestions.getItems().add(0,b.build());
        } catch (SQLException ex) {
            System.out.println(ex);
            MyAlert.getInstance().ShowMsg("Thêm câu hỏi thất bại!");
        } catch (Exception ex) {
            MyAlert.getInstance().ShowMsg("Dữ liệu không hợp lệ!");
        }
    }
    private void loadColumns(){
        TableColumn colId=new TableColumn("Id");
        colId.setCellValueFactory((new PropertyValueFactory("id")));
        colId.setPrefWidth(100);
        
        TableColumn colContent=new TableColumn("Cotent");
        colContent.setCellValueFactory((new PropertyValueFactory("content")));
        colContent.setPrefWidth(250);
        
        TableColumn colAction = new TableColumn();
        colAction.setCellFactory((e)->{
            TableCell cell=new TableCell();
            Button b=new Button("Xoa");
            
            b.setOnAction((event)->{
                Optional<ButtonType> t= MyAlert.getInstance().showMsg("Xoa cau hoi thi lua chon cung mat. Ban chac chua?"
                        , Alert.AlertType.CONFIRMATION);
                
                if(t.isPresent() && t.get().equals(ButtonType.OK)){
                    question q=(question) cell.getTableRow().getItem();
                    try {
                         Configs.questionServices.deleteQuestion(q.getId());
                        this.tbQuestions.getItems().remove(q);
                    } catch (SQLException ex) {
                        MyAlert.getInstance().showMsg("Xoa that bai", Alert.AlertType.WARNING);
                    }
                }
            });
            cell.setGraphic(b);
            
            return cell;
        });
        
        
        
        this.tbQuestions.getColumns().addAll(colId, colContent, colAction);
    }
    
    private void loadQuestion(List<question> questions){
        this.tbQuestions.setItems(FXCollections.observableList(questions));
    }
}
