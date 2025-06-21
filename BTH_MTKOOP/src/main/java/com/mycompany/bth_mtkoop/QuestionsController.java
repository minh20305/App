










/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bth_mtkoop;

import com.tnm.bojo.category;
import com.tnm.services.CategoryServices;
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
    private final static CategoryServices cateServices = new CategoryServices();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            this.cbcates.setItems(FXCollections.observableList(cateServices.getCates()));
   
        } catch(SQLException ex){
            ex.printStackTrace();
        } 
    }    
    
}
