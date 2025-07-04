/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tnm.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author admin
 */
public class MyAlert {
    private static MyAlert instance;
    private final Alert a;
    private MyAlert(){
        this.a=new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText("Quiz app");
        a.setContentText("Coming soon...");    
    }
    
    public static MyAlert getInstance(){
        if(instance == null){
            instance = new MyAlert();
            
        }
        return instance;
    }
    
    public void ShowMsg(String msg){
        this.a.setContentText(msg); 
        this.a.showAndWait();
    }
    
    public Optional <ButtonType> showMsg(String message, Alert.AlertType type ){
        this.a.setContentText(message);
        this.a.setAlertType(type);
        return this.a.showAndWait();
    }
}
