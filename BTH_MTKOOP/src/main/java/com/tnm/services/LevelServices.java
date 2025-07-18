/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tnm.services;

import com.tnm.pojo.category;
import com.tnm.pojo.level;
import com.tnm.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class LevelServices extends BaseServices<level>{

    @Override
    public PreparedStatement getStatement(Connection conn) throws SQLException {
        return conn.prepareCall("select * from level");
    }

    @Override
    public List<level> getResults(ResultSet rs) throws SQLException {
        List<level> levels = new ArrayList<>();
        while(rs.next()){
            int id= rs.getInt("id");
            String name=rs.getString("name");
            String note=rs.getString("note");
            
            level l= new level(id,name,note);
            levels.add(l);
        }
        return levels;
    }
    
}
