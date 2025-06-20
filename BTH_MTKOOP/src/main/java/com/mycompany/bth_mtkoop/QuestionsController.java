










/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bth_mtkoop;

import com.tnm.bojo.category;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuestionsController implements Initializable {
    @FXML private ComboBox<category> cbcates;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/quizdb","root","root");
            
            Statement stm=conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from category");
            
            List<category> cates= new ArrayList<>();
            while(rs.next()){
               int id = rs.getInt("id");
               String name = rs.getString("name");
               System.out.printf("%d/%s", id, name);
               
               category c= new category(id, name);
               cates.add(c);
            }
            
            conn.close();
            this.cbcates.setItems(FXCollections.observableList(cates));
        } catch(ClassNotFoundException | SQLException ex){
            ex.printStackTrace();
        } 
    }    
    
}
