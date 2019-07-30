package com.neu.edu.runtime;

import com.neu.edu.base.BaseSprite;
import com.neu.edu.base.Drawable;
import com.neu.edu.base.Moveable;
import com.neu.edu.util.ImageMap;

import java.awt.Graphics;
import java.awt.Image;

public class Backgroud extends BaseSprite implements Moveable, Drawable {

    private Image image;

    public Backgroud() {
        this(0, 0, ImageMap.get("bg01"));
    }

    public Backgroud(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void move() {
        setY(getY() - 1);

    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
    }
}
