/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tnm.services.questions;

import com.tnm.bojo.choice;
import com.tnm.bojo.question;
import com.tnm.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public abstract class BaseQuestionServices {
    public abstract String getSQL(List<Object> params);
    
    public List<question> list() throws SQLException {
        Connection conn=JdbcConnector.getInstance().connect();
        
        List<Object> params=new ArrayList<>();
        PreparedStatement stm = conn.prepareCall(this.getSQL(params));
        for(int i=0;i<params.size();i++){
            stm.setObject(i+1, params.get(i));
        }
        ResultSet rs=stm.executeQuery();
        
        List<question> questions = new ArrayList<>();
        
        while(rs.next()){
            int id=rs.getInt("id");
            String content=rs.getString("content");
            question q=new question.Builder(id,content).build();
            questions.add(q);
        }
        
        return questions;
    }
    
    public List<choice> getChoicesByQuestionId(int questionId) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm = conn.prepareCall("SELECT * FROM choice WHERE question_id=? ");
        stm.setInt(1, questionId);
        ResultSet rs = stm.executeQuery();

        List<choice> choices = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");
            boolean correct = rs.getBoolean("is_correct");
            choice c = new choice(id, content, correct);
            
            choices.add(c);
        }
        return choices;
    }
}
