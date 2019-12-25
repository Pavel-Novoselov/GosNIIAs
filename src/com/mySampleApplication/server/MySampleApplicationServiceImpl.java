package com.mySampleApplication.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mySampleApplication.client.MySampleApplicationService;
import com.mySampleApplication.server.entities.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class MySampleApplicationServiceImpl extends RemoteServiceServlet implements MySampleApplicationService {
    ArrayList<Contact> phonebook = new ArrayList<>();
    // Implementation of sample interface method

    public String getMessage(String msg){
//        String str = "";
//        DataRepository.connect();
//        phonebook = DataRepository.getFromDB();
//        for (int i = 0; i < phonebook.size(); i++) {
//            Contact contact = phonebook.get(i);
//            str+=contact.getName() + " "+ contact.getAddress() + " " + contact.getPhone() +"\n";
//        }
        System.out.println("11111111111111111******************" + msg);
        DataRepository.setToDB(msg);
        return "Client said: \"" + msg + "\"<br>Server answered: \"OK!\"";
    }

    @Override
    public String insertContact(String str) {
//        System.out.println("11111111111111111******************" + str);
        DataRepository.setToDB(str);
        return "OK!!!";
    }

    @Override
    public String getPhoneBook() {

        String str = "";
        DataRepository.connect();
        phonebook = DataRepository.getFromDB();
        for (int i = 0; i < phonebook.size(); i++) {
            Contact contact = phonebook.get(i);
            str+="<tr><td>"+contact.getName() + "</td><td>"+ contact.getAddress() + "</td><td>" + contact.getPhone() +"</td></tr>";
        }
        return "PhoneBook:<BR><table>" + str+"</table>";
    }

//    @Override
//    public ArrayList<Contact> getAllContacts() {
//        return null;
//    }
}