package com.app.server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "password" })
public class UserModel {

    public String user_id;
    public String name;
    public String email;
    public String token;
    public String password;
    public int portrait;
    public int c;
    public int java;
    public int python;
    public int htcss;
    public int js;
    public int android;
    public int ios;
    public int bootstrap;
    public int ai;
    public int nlp;
    public int blockchain;
    public int datamining;
    public String resume;

    @Override
    public String toString() {
        return "UserModel{" +
                "user_id='" + user_id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", password='" + password + '\'' +
                ", portrait='" + portrait + '\'' +
                ", c='" + c + '\'' +
                ", java='" + java + '\'' +
                ", python='" + python + '\'' +
                ", html='" + htcss + '\'' +
                ", js='" + js + '\'' +
                ", android='" + android + '\'' +
                ", ios='" + ios + '\'' +
                ", bootstrap='" + bootstrap + '\'' +
                ", ai='" + ai + '\'' +
                ", nlp='" + nlp + '\'' +
                ", blockchain='" + blockchain + '\'' +
                ", datamining='" + datamining + '\'' +
                ", resume='" + resume + '\'' +
                '}';
    }



}
