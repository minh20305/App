/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tnm.services;

import com.tnm.bojo.choice;
import com.tnm.bojo.level;
import com.tnm.bojo.question;
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
public class QuestionServices {
    
    public void addQuestion(question q) throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        conn.setAutoCommit(false);
        String sql="insert into question(content,hint,imagecategory_id, level_id) VALUES(?, ?, ?, ?, ?)"; 
    
      PreparedStatement stm = conn.prepareCall(sql);
      stm.setString(1, q.getContent());
      stm.setString(2, q.getHint());
        stm.setString(3, q.getImage());
        stm.setInt(4, q.getCate().getId());
        stm.setInt(5, q.getLevel().getId());
       
        if (stm.executeUpdate() > 0) {
            int questionId = -1;
           ResultSet r = stm.getGeneratedKeys();
           if (r.next())
                questionId = r.getInt(1);
           
            sql = "INSERT INTO choice(content, is_correct, question_id) VALUES(?, ?, ?)";
            
            for (var c: q.getChoices()) {
                stm = conn.prepareCall(sql);
                stm.setString(1, c.getContent());
                stm.setBoolean(2, c.isCorrect());
                stm.setInt(3, questionId);
                
                stm.executeUpdate();
            }
            
            conn.commit();
        } else
            conn.rollback();
    }
    
    public List<question> getQuestions() throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        Statement stm = conn.createStatement();
        
        ResultSet rs = stm.executeQuery("select * from question");
        
        List<question> questions = new ArrayList<>();
        while(rs.next()){
            String content=rs.getString("content");
            int id = rs.getInt("id");
            question q= new question.Builder(id,content).build();
            questions.add(q);
            
        }
        return questions;
    }
    
    public List<question> getQuestions(String kw) throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm = conn.prepareCall("select * from question where content like concat('%',?,'%')");
        stm.setString(1, kw);
        ResultSet rs = stm.executeQuery();
        
        List<question> questions = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("id");
            String content=rs.getString("content");
            question q= new question.Builder(id,content).build();
            questions.add(q);
            
        }
        return questions;
    }
    
    public List<question> getQuestions(int num) throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm = conn.prepareCall("select * from question order by rand() LIMIT ? ");
        stm.setInt(1, num);
        ResultSet rs = stm.executeQuery();
        
        List<question> questions = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("id");
            String content=rs.getString("content");
            question q= new question.Builder(id,content).build();
            questions.add(q);
            
        }
        return questions;
    }
    
    public boolean deleteQuestion(int id) throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm=conn.prepareCall("delete from question where id=?");
        stm.setInt(1, id);
           
        return stm.executeUpdate()>0;
    }
    
    public List<choice> getChoicesByQuest(int idQ) throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm = conn.prepareCall("select * from question order by rand() LIMIT ? ");
        stm.setInt(1, idQ);
        ResultSet rs = stm.executeQuery();
        
        List<choice> choices = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("id");
            String content=rs.getString("content");
            boolean correct = rs.getBoolean("is_Correct");
            choice c=new choice(id,content,correct);
            choices.add(c);
            
        }
        return choices;
    }
}
