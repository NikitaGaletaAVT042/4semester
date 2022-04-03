package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller_information {
    @FXML
    private Button  continueWork;
    @FXML
    private Button endWork;
    @FXML
    private TextArea text;
    @FXML
    private AnchorPane root;
    Habitat habitat=null;
    public void ContinueWork() throws IOException {

        ((Stage)root.getScene().getWindow()).close();
        habitat.start();
        
    }
    public void EndWork() throws IOException {

        if(habitat!=null) {
            habitat.clean();
        }
        ((Stage)root.getScene().getWindow()).close();
    }
    public void SetHabitat(Habitat habitat){
        this.habitat=habitat;

    }
    public void textArea(){
        text.setText(String.format("%d", habitat.second)+"-Время \n"
                +String.format("%d", habitat.manag)+"-Менеджеров \n"
                +String.format("%d", habitat.program)+"-Программистов \n");
    }
}
