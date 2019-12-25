package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mySampleApplication.server.entities.Contact;

import java.util.ArrayList;

public interface MySampleApplicationServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);

    void getPhoneBook(AsyncCallback<String> async);

    void insertContact(String str, AsyncCallback<String> async);

//    void getAllContacts(AsyncCallback<ArrayList<Contact>> async);
}
