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
        @Override
        public void updateTheme() {
            ThemeManager.setThemeFactory(new DefaultFactory());
        }
    }, DARK {
        @Override
        public void updateTheme() {
            ThemeManager.setThemeFactory(new DarkFactory());
        }
    }, LIGHT {
        @Override
        public void updateTheme() {
            ThemeManager.setThemeFactory(new LightFactory());
        }
    };
    
    public abstract void updateTheme();
}
