package com.simpleFileParser.models;

import java.nio.file.Path;

public class Statistic {
    private String name;
    private String data;
    private String fileName;

    public Statistic(String name, String data, String fileName) {
        this.name = name;
        this.data = data;
        this.fileName = fileName;
    }

    public void printStats(){
        System.out.println("file name: "+ fileName +"||"+ name +" = "+ data);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
