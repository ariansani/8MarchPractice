package com.example.MarchPractice.model;

import java.io.Serializable;
import java.util.Random;

public class Book implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;
    private String author;
    private String title;
    private String imgFileName;
    
    public Book(){
        this.id=generateId(10);
    }

    private synchronized String generateId(int i) {
        Random r = new Random();
        StringBuilder strBuilder=new StringBuilder();
        while(strBuilder.length()<i){
            strBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return strBuilder.toString().substring(0,i);
    }

    public Book(String id, String author, String title, String imgFileName) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.imgFileName = imgFileName;
    }

    public Book( String author, String title, String imgFileName) {
        this.id = generateId(10);
        this.author = author;
        this.title = title;
        this.imgFileName = imgFileName;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImgFileName() {
        return imgFileName;
    }
    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }
    
}
