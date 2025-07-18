/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tnm.services.exam;

import com.tnm.pojo.exam;
import com.tnm.pojo.question;
import com.tnm.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public abstract class ExamStrategy{
    public abstract List<question> getQuestions()throws SQLException;
    
    public void addExam(List<question> q) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        conn.setAutoCommit(false);
        exam ex = new exam(q);
        
        String sql = "insert into exam(title, created_date) values(?,?)";
        PreparedStatement stm=conn.prepareCall(sql);
        stm.setString(1, ex.getTitle());
        stm.setString(2, ex.getCreatedDate().toString());
        
        if(stm.executeUpdate()>0){
            int exId=-1;
            ResultSet r=stm.getGeneratedKeys();
            if(r.next())
                exId=r.getInt(1);
            
            sql="insert into exam_question(exam_id, question_id) values(?,?)";
            stm=conn.prepareCall(sql);
            
            for(var qu: q){
                stm.setInt(1,exId);
                stm.setInt(2,qu.getId());
                
                stm.executeUpdate();
                
            }
            
            conn.commit();
            
        }else{
            conn.rollback();
        }
    }
}
