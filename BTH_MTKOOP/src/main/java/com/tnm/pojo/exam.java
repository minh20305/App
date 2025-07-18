/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tnm.pojo;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author admin
 */
public class exam {
    private int id;
    private String title;
    private LocalDateTime createdDate;
    private List<question> questions;

    public exam(List<question> questions) {
        this.title=String.format("Exam-%s", LocalDateTime.now());
        this.createdDate=LocalDateTime.now();
        this.questions=questions;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the createdDate
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the questions
     */
    public List<question> getQuestions() {
        return questions;
    }

    /**
     * @param questions the questions to set
     */
    public void setQuestions(List<question> questions) {
        this.questions = questions;
    }
    
    
   
}
