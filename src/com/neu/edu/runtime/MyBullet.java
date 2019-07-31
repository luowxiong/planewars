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
import java.util.List;

public class MyBullet extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int speed = FrameConstant.GAME_SPEED * 5;

    public MyBullet() {
        this(0,0, ImageMap.get("myb01"));
    }

    public MyBullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        move();
        borderTesting();
    }

    @Override
    public void move() {
        setY(getY() - speed);

    }

    public void borderTesting(){
        if (getY() < 30 - image.getHeight(null)){
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.myBulletList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    public void collisionTesting(List<EnempPlane> enempPlaneList){
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (EnempPlane enempPlane : enempPlaneList) {
            if (enempPlane.getRectangle().intersects(this.getRectangle())){
                enempPlaneList.remove(enempPlane);
                gameFrame.myBulletList.remove(this);
            }
        }
    }
}
