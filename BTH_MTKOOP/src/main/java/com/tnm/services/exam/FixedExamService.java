/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tnm.services.exam;

import com.tnm.pojo.question;
import com.tnm.services.questions.BaseQuestionServices;
import com.tnm.services.questions.LevelQuestionServicesDecorator;
import com.tnm.services.questions.LimitQuestionServicesDecorator;
import com.tnm.utils.Configs;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class FixedExamService extends ExamStrategy{
    @Override
    public List<question> getQuestions() throws SQLException{
        List<question> questions= new ArrayList<>();
        
        for(int i=0;i<Configs.RATES.length;i++){
            BaseQuestionServices s= new LimitQuestionServicesDecorator(
                    new LevelQuestionServicesDecorator(Configs.questionServices, i+1),(int) (Configs.RATES[i]*Configs.NUM_OF_QUEST));
            questions.addAll(s.list());
        }
        
        return questions;
    }
}
