package com.example.demo1;

import javafx.scene.image.ImageView;

public abstract class Workers {
    protected double x,y;
    protected double height,width;
    protected ImageView imageView;
    public Workers(double x, double y, double height, double weight){
        this.x=x;
        this.y=y;
        this.height=height;
        this.width=width;
        imageView=new ImageView();

    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
    public ImageView getImageView() {
        return imageView;
    }
    public void setX(double x) {
        this.x=x;
    }
    public void setY(double y)  {
        this.y=y;
    }
    public void setHeight(double height){
        this.height=height;
    }
    public void setWidth(double width){
        this.width=width;
    }
}
