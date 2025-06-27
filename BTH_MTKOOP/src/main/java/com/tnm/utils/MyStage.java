/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tnm.utils;

import com.mycompany.bth_mtkoop.App;
import com.tnm.utils.theme.ThemeManager;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class MyStage {
    private static MyStage instance;
    private static Scene scene;
    private final Stage stage;
    private MyStage() throws IOException{
        
        stage = new Stage();
        stage.setTitle("Quiz App");
    }
    
    public static MyStage getInstance() throws IOException {
        if(instance == null)
            instance = new MyStage();
        return instance;
    }
    
    public void showStage(String fxml) throws IOException{
        if(!stage.isShowing()){
            if(scene==null){
            scene = new Scene(new FXMLLoader(App.class.getResource(fxml)).load());
            
            }
            else 
                scene.setRoot(new FXMLLoader(App.class.getResource(fxml)).load());
            
            ThemeManager.applyTheme(scene);
            
            this.stage.setScene(scene);
            this.stage.show();
        }
        
    }
}
