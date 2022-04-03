package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Habitat habitat = fxmlLoader.getController();
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case B:
                    habitat.start();
                    break;
                case E:
                    try {
                        habitat.end();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case T:
                    habitat.show();
                default:
                    break;
            }
        });
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
