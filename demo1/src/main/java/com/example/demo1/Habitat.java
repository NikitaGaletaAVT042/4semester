package com.example.demo1;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.skin.ComboBoxBaseSkin;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Habitat implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label labelTime;
    @FXML
    private Label labelSimulation;
    @FXML
    private Label labelProgrammer;
    @FXML
    private Label labelManager;
    /*@FXML
    private ProgressBar songProgressBar;
    @FXML
    private Button  playButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Slider volumeSlider;*/
    @FXML
    private RadioButton radioTimeopen;
    @FXML
    private RadioButton radioTimeclose;
    @FXML
    private CheckBox radioInformation;
    @FXML
    private Button StartButton;
    @FXML
    private Button EndButton;
    @FXML
    private CheckMenuItem INFO;
    @FXML
    private TextField intervalManager;
    @FXML
    private TextField intervalProgrammist;
    @FXML
    private ComboBox <Integer> persentManager;
    @FXML
    private ComboBox <Integer> persentProgrammist;


    private File directory;
    private File [] files;
    private  ArrayList<File> songs;
    private TimerTask task;
    private boolean running;
    private boolean inf=false;
    private double P1;
    private double K;
    private Timer timer;
    public  int  second=0;
    public  int  program=0;
    public  int  manag=0;
    private  double getP1(){
        return P1;
    }
    private void setP1(double pro){
         P1 =pro;
    }
    private double getK(){
        return K;
    }
    private void setK(double man){
        K = man;
    }
    private  double N1 = 5;
    private  double N2 = 10;
    boolean checkingEnd=true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i=0;i<=100;i+=10){
            persentManager.getItems().add(i);
        }
        persentManager.getSelectionModel().select((Integer) 10);
        setK(persentManager.getValue());
        for(int i=0;i<=100;i+=10){
            persentProgrammist.getItems().add(i);
        }
        persentProgrammist.getSelectionModel().select((Integer) 10);
        setP1(persentProgrammist.getValue());
    }
    public void button(){
        setP1(persentProgrammist.getValue());
        setK(persentManager.getValue());
        int helperManager;
        try {
            helperManager = Integer.parseInt(intervalManager.getText());
            if (helperManager<=0){
                throw new Exception();
            }
        }
        catch (Exception e) {
            helperManager=5;
            showWarn("ERROR Не корректные данные");
        }
        N1=helperManager;
        int helper;
        try {
            helper = Integer.parseInt(intervalProgrammist.getText());
            if (helper<=0){
                throw new Exception();
            }
        }
        catch (Exception e) {
            helper=5;
            showWarn("ERROR Не корректные данные");
        }
        N2=helper;
    }
    public void start()  {
        if (!checkingEnd){
            return;
        }
        StartButton.setDisable(true);
        EndButton.setDisable(false);
        radioTimeopen();
        radioTimeclose();
        checkingEnd=false;
        labelSimulation.setText("Система активна");
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                second+=1;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            update();
                            labelTime.setText("Текущее время: "+second);
                        }
                        catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        },1000,1000);

    }
    public void end() throws IOException {
        if (checkingEnd){
            return;
        }
        StartButton.setDisable(false);
        EndButton.setDisable(true);
        checkingEnd=true;
        labelSimulation.setText("Система неактивна");
        timer.cancel();
        timer=null;

        if(inf){
            Stage stage =new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("INFORMATION.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            Controller_information controller_information=fxmlLoader.getController();
            controller_information.SetHabitat(this);
            controller_information.textArea();
            stage.show();
        }
        else{
            second=0;
            program=0;
            manag=0;
            for (Workers worker:WorkersList.getWorkers()){
                anchorPane.getChildren().remove(worker.getImageView());
            }
            WorkersList.getWorkers().clear();
            labelTime.setText("Текущее время: "+second);

        };
    }

    public void update() throws URISyntaxException {
        if((second%N1==0)&&(Math.random()<=getP1())) {
            Programmer programmer = new Programmer(getRandom(), getRandom(), 50, 50);
            WorkersList.getWorkers().add(programmer);
            anchorPane.getChildren().add(programmer.getImageView());
            program += 1;
        }
        if((second%N2==0)&&(getK()>((manag+1)/(program+1))*100)){
            Manager manager = new Manager(getRandom(), getRandom(), 50, 50);
            WorkersList.getWorkers().add(manager);
            anchorPane.getChildren().add(manager.getImageView());
            manag += 1;
        }


    }

    public void show(){
        labelTime.setDisable(!labelTime.isDisable());
        if (radioTimeopen.isDisable()==false){
            radioTimeopen.setDisable(true);
            radioTimeclose.setDisable(false);
        }
        else {
            radioTimeclose.setDisable(true);
            radioTimeopen.setDisable(false);
        }
    }
    public void radioTimeopen(){
        radioTimeopen.selectedProperty().addListener(((observableValue, aBoolean, t1) -> {
          radioTimeopen.setDisable(true);
          radioTimeclose.setDisable(false);
          labelTime.setVisible(true);
        }) );

    }
    public void radioTimeclose(){
        radioTimeclose.selectedProperty().addListener(((observableValue, aBoolean, t1) -> {
            radioTimeopen.setDisable(false);
            radioTimeclose.setDisable(true);
            labelTime.setVisible(false);
                })
        );
    }

    /*public void RadioInformation() {
        radioInformation.isSelected(){

        }
    }*/
    public void playMedia(){
        //Media hit = new Media(new File(Smash_Mouth_-_All_Star_65719503.mp3).toURI().toString());
    }
    public void pauseMedia(){

    }
    private double getRandom(){
        double x = (int)(Math.random()*((anchorPane.getPrefWidth()-50)+1));
        return x;
    }
    public void clean(){
        second=0;
        program=0;
        manag=0;
        for (Workers worker:WorkersList.getWorkers()){
            anchorPane.getChildren().remove(worker.getImageView());
        }
        WorkersList.getWorkers().clear();
        labelTime.setText("Текущее время: "+second);
        labelProgrammer.setText("Программистов "+program);
        labelManager.setText("Менеджеров "+manag);

    }

    private void showWarn(String msg)
    {
        Alert warningWindow = new Alert(Alert.AlertType.WARNING);
        warningWindow.setTitle("Предупреждение");
        warningWindow.setHeaderText("Не правильные данные");
        warningWindow.setContentText(msg);
        warningWindow.show();
    }
    public void ButtonInformation(){
        inf=!inf;
        radioInformation.setSelected(inf);
        INFO.setSelected(inf);
    }
}

