/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tnm.services.questions;


    
        
        
import java.util.List;

/**
 *
 * @author MINH
 */
public class QuestionServices extends BaseQuestionServices{
   
    @Override
    public String getSQL(List<Object> params) {
        return "select * from question where 1=1";
    }
}
