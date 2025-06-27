/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tnm.utils.theme;

import com.mycompany.bth_mtkoop.App;

/**
 *
 * @author admin
 */
public class DarkFactory implements ThemeFactory{

    @Override
    public String getStyleSheet() {
        return App.class.getResource("dark.css").toExternalForm();
    }
  
}
