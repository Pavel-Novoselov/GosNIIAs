package com.mySampleApplication.server;

import com.mySampleApplication.server.entities.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DataRepository {
    private static Connection connection;
    private static Statement stmt;
    private ArrayList<Contact> phoneBook;

    public static void connect() {
        try {
            String url = "jdbc:postgresql://ec2-54-217-221-21.eu-west-1.compute.amazonaws.com:5432/d1ggjj6m6h73ae";
            Properties props = new Properties();
            props.setProperty("user","ffbcufwblpsrvi");
            props.setProperty("password","0ecad0e7c14dcf20e76d2581124fe8a261df8b9a6583d2eda7ceb4538c7a21b5");
            props.setProperty("ssl","false");
            Connection connection = DriverManager.getConnection(url, props);
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass) {
        String sql = String.format("SELECT nickname FROM main where login = '%s' and password = '%s'",
                login, pass.hashCode());

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(rs.next()) {
                try {
                    return rs.getString(1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Contact> getFromDB(){
        ArrayList<Contact> phonebook = new ArrayList<>();
        Contact contact;
        ResultSet rs = null;
        String str = "SELECT name, address, phone FROM phonebook";
        try {
            rs = stmt.executeQuery(str);
        while(rs.next()){
            contact = new Contact();
            contact.setName(rs.getString(1));
            contact.setAddress(rs.getString(2));
            contact.setPhone(rs.getString(3));
            phonebook.add(contact);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phonebook;
    }

    public static void setToDB(String string){
        System.out.println(string);
        String[] str = string.split(" ");
        System.out.println(str[0]+" "+ str[1]+ " "+ str[2]);
        String str1 = String.format("INSERT INTO phonebook (name, address, phone) VALUES ('%s', '%s', '%s')", str[0], str[1], str[2]);
        System.out.println(str1);
        try {
            stmt.execute(str1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
