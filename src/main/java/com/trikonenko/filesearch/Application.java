package com.trikonenko.filesearch;

import com.trikonenko.filesearch.gui.SearchApp;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(
                Application.class).headless(false).run(args);

        SearchApp appFrame = context.getBean(SearchApp.class);
        appFrame.setVisible(true);

    }
}
