/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tnm.utils;

import com.tnm.services.CategoryServices;
import com.tnm.services.LevelServices;
import com.tnm.services.questions.BaseQuestionServices;
import com.tnm.services.questions.QuestionServices;
import com.tnm.services.questions.UpdateQuestionServices;

/**
 *
 * @author MINH
 */
public class Configs {
    public static final CategoryServices cateServices = new CategoryServices();
    public static BaseQuestionServices questionServices = new QuestionServices();
    public static final LevelServices levelServices = new LevelServices();
    public static final UpdateQuestionServices uqs= new UpdateQuestionServices();
}
