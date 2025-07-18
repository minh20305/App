/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tnm.services.exam;

import com.tnm.pojo.question;
import com.tnm.services.questions.BaseQuestionServices;
import com.tnm.services.questions.LimitQuestionServicesDecorator;
import com.tnm.utils.Configs;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public class SpecificExamService extends ExamStrategy{
    private int num;

    public SpecificExamService(int num) {
        this.num = num;
    }

    @Override
    public List<question> getQuestions() throws SQLException {
        BaseQuestionServices s = new LimitQuestionServicesDecorator(Configs.questionServices, num);
        return s.list();
    }
    
    
}
