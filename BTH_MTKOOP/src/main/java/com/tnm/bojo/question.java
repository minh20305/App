/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tnm.bojo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class question {
    private int id;
    private String content;
    private String image;
     private String hint;
    private category cate;
    private level level;
    private List<choice> choices;
            
    private question(Builder b) {
        this.id = b.id;
        this.content = b.content;
        this.hint = b.hint;
        this.image = b.image;
        this.cate = b.cate;
        this.level = b.level;
        this.choices = b.choices;
    }
    
    public static class Builder{
        private int id;
        private String content;
        private String hint;
        private String image;
        private category cate;
        private level level;
        private List<choice> choices = new ArrayList<>();
        
        public Builder(String content, category c, level l) throws Exception{
            if(content.isEmpty()||c==null||l==null)
                throw new Exception("Invalid data!");
            
            this.content=content;
            this.cate=c;
            this.level=l;
            
        }
        
        public Builder addHint(String h){
            this.hint=h;
            return this;
        }
        
        public Builder addImage(String img){
            this.image=img;
            return this;
        }
        
        public Builder addChoice(choice c){
            this.choices.add(c);
            return this;
        }
        
        public question build(){
            return new question(this);
        }
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
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the hint
     */
    public String getHint() {
        return hint;
    }

    /**
     * @param hint the hint to set
     */
    public void setHint(String hint) {
        this.hint = hint;
    }

    /**
     * @return the cate
     */
    public category getCate() {
        return cate;
    }

    /**
     * @param cate the cate to set
     */
    public void setCate(category cate) {
        this.cate = cate;
    }

    /**
     * @return the level
     */
    public level getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(level level) {
        this.level = level;
    }

    /**
     * @return the choices
     */
    public List<choice> getChoices() {
        return choices;
    }

    /**
     * @param choices the choices to set
     */
    public void setChoices(List<choice> choices) {
        this.choices = choices;
    }
}
