/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.tnm.utils.theme;import javafx.scene.Scene;

/**
 *
 * @author admin
 */
public enum Theme {
    DEFAULT {
        public void updateTheme(Scene scene) {
            ThemeManager.setThemeFactory(new DefaultFactory());
            ThemeManager.applyTheme(scene);
        }
    }, DARK {
       public void updateTheme(Scene scene) {
            ThemeManager.setThemeFactory(new DarkFactory());
            ThemeManager.applyTheme(scene);
        }
    }, LIGHT {
       public void updateTheme(Scene scene) {
            ThemeManager.setThemeFactory(new LightFactory());
            ThemeManager.applyTheme(scene);
        }
    };
    
    public abstract void updateTheme(Scene scene);
}
