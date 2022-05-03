package com.example.demo1;

import javafx.scene.image.Image;

import java.net.URISyntaxException;

public class Programmer extends Workers implements IBehaviour {

    public Programmer (double x, double y, double height, double width) throws URISyntaxException {
        super(x, y, height, width);
        imageView.setImage(new Image( HelloApplication.class.getResource("photo/programmers.jpeg").toURI().toString()));
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        imageView.setPreserveRatio(false);
    }


    @Override
    public void Transparency() {
        System.out.println("Hi world");
    }

}
