package com.mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class MySampleApplication implements EntryPoint {

    public void onModuleLoad() {
        final Button button = new Button("Посмотреть");
        final Label label = new Label();

        final Button button1 = new Button("Записать контакт в базу");
        final TextBox nameField = new TextBox();
        nameField.setText("ФИО Город Телефон");
        final Label label2 = new Label();

        label2.setText("Нажмите кнопку что добавить новый контакт");

        //обработка нажатия кнопки для получения списка контаков
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                MySampleApplicationService.App.getInstance().getPhoneBook(new MyAsyncCallback(label));
            }
        });

        //обработка нажатия кнопки для записи нового контака в базу данных
        button1.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                String textToServer = nameField.getText();
                MySampleApplicationService.App.getInstance().insertContact(textToServer, new MyAsyncCallback(label2));
            }
        });

        RootPanel.get("slot1").add(button);
        RootPanel.get("slot2").add(label);
        RootPanel.get("slot3").add(nameField);
        RootPanel.get("slot4").add(button1);
        RootPanel.get("slot5").add(label2);

    }

    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;

        MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }
}
