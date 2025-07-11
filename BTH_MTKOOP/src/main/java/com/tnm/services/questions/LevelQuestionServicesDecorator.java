/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tnm.services.questions;

import com.tnm.bojo.level;
import java.util.List;

/**
 *
 * @author admin
 */
public class LevelQuestionServicesDecorator extends QuestionDecorator{
    private level lv;

    public LevelQuestionServicesDecorator(BaseQuestionServices decorator, level lv) {
        super(decorator);
        this.lv=lv;
    }

    public LevelQuestionServicesDecorator(BaseQuestionServices decorator,int id) {
        super(decorator);
       this.lv = new level(id);
       

    }
    
    @Override
    public String getSQL(List<Object> params) {
        String sql=this.decorator.getSQL(params)+" and level_id=?";
        params.add(this.lv.getId());
        
        return sql;
    }
    
}
