package com.neu.edu.runtime;

import com.neu.edu.base.BaseSprite;
import com.neu.edu.base.Drawable;
import com.neu.edu.base.Moveable;
import com.neu.edu.constant.FrameConstant;
import com.neu.edu.util.ImageMap;

import java.awt.Graphics;
import java.awt.Image;

public class Backgroud extends BaseSprite implements Moveable, Drawable {

    private Image image;

    public Backgroud() {
        this(0, FrameConstant.FRAME_HEIGHT - ImageMap.get("bg01").getHeight(null), ImageMap.get("bg01"));
    }

    public Backgroud(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void move() {
        setY(getY() + FrameConstant.GAME_SPEED);

    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
    }
}
