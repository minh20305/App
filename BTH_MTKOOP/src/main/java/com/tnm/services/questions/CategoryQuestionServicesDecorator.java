/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tnm.services.questions;

import com.tnm.bojo.category;
import java.util.List;

/**
 *
 * @author admin
 */
public class CategoryQuestionServicesDecorator extends QuestionDecorator {
     private category cate;

    public CategoryQuestionServicesDecorator(BaseQuestionServices decorator, category c) {
        super(decorator);
        this.cate = c;
    }
    
    public CategoryQuestionServicesDecorator(BaseQuestionServices decorator, int id) {
        super(decorator);
        this.cate = new category(id);
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params)+" and category_id=?";
        params.add(this.cate.getId());
        return sql;
    }
   


    
}
