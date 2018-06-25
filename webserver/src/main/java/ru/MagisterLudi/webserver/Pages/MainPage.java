package ru.MagisterLudi.webserver.Pages;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
public class MainPage extends UI{

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout vl = new VerticalLayout();
        Button button = new Button("Щелкни меня!");
        vl.addComponent(button);
        setContent(vl);
    }
}
