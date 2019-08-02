package com.neu.edu.runtime;

import com.neu.edu.base.BaseSprite;
import com.neu.edu.base.Drawable;
import com.neu.edu.util.ImageMap;

import java.awt.Graphics;
import java.awt.Image;

public class Blood extends BaseSprite implements Drawable {

    private Image image;

    public Blood() {
        this(10, 30, ImageMap.get("bd01"));
    }

    public Blood(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
    }

}
