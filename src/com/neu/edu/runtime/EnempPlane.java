package com.neu.edu.runtime;

import com.neu.edu.base.BaseSprite;
import com.neu.edu.base.Drawable;
import com.neu.edu.base.Moveable;
import com.neu.edu.constant.FrameConstant;
import com.neu.edu.main.GameFrame;
import com.neu.edu.util.DataStore;
import com.neu.edu.util.ImageMap;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

public class EnempPlane extends BaseSprite implements Drawable, Moveable {

    private Image image;
    private Random random = new Random();

    public EnempPlane() {
        this(0,0, ImageMap.get("ep01"));
    }

    public EnempPlane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        move();
        fire();
    }

    public void fire(){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (random.nextInt(1000) > 985){
            gameFrame.enempBulletList.add(new EnempBullet(
                    getX() + (image.getWidth(null)) / 2 - (ImageMap.get("epb01").getWidth(null)) / 2,
                    getY() + image.getHeight(null),
                    ImageMap.get("epb01")));
        }
    }

    @Override
    public void move() {
        setY(getY() + FrameConstant.GAME_SPEED);
        borderTesting();
    }

    public void borderTesting(){
        if (getY() > FrameConstant.FRAME_HEIGHT){
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.enempPlaneList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }
}
