/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tnm.services;

import com.tnm.pojo.category;
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
 * @author MINH
 */
public class CategoryServices extends BaseServices<category> {
    
    @Override
    public PreparedStatement getStatement(Connection conn) throws SQLException{
        return conn.prepareCall("select * from category");
    }

    @Override
    public List<category> getResults(ResultSet rs) throws SQLException {
        List<category> cates = new ArrayList<>();
        while(rs.next()){
            int id= rs.getInt("id");
            String name=rs.getString("name");
            
            category c = new category(id, name);
            cates.add(c);
        }
        return cates;
    }
}
