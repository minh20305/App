/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tnm.services.questions;

import com.tnm.bojo.question;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public class LimitQuestionServicesDecorator extends QuestionDecorator {
    private int num;
    
    public List<question> list() throws SQLException{
        List<question> questions = super.list();
        for(var q: questions){
            q.setChoices(this.getChoicesByQuestionId(q.getId()));
            
        }
        return questions;
        
    
    }

    public LimitQuestionServicesDecorator(BaseQuestionServices decorator, int num) {
        super(decorator);
        this.num=num;
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " ORDER BY rand() LIMIT ?";
        params.add(this.num);
        
        return sql;
    }

    
}