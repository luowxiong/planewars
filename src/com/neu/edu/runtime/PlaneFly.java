package com.neu.edu.runtime;

import com.neu.edu.base.BaseSprite;
import com.neu.edu.base.Drawable;
import com.neu.edu.base.Moveable;
import com.neu.edu.util.ImageMap;

import java.awt.Graphics;
import java.awt.Image;

public class PlaneFly extends BaseSprite implements Moveable, Drawable {
    private Image image;

    public PlaneFly() {
        this(0,400, ImageMap.get("plane01"));

    }

    public PlaneFly(int x, int y, Image image) {
        super(x, y);
        this.image = image;

    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
    }

    @Override
    public void move() {
        setX(getX() + 1);
    }
}
