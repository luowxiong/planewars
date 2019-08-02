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

public class Prop extends BaseSprite implements Moveable, Drawable {

    private Image image;
    private int speed = FrameConstant.GAME_SPEED * 5;
    private Random random = new Random();

    public Prop() {
        this(0, 0, ImageMap.get("prop01"));
    }

    public Prop(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        move();
//        add();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);

    }


    private boolean right = true;

    @Override
    public void move() {
        if (right) {
            setX(getX() + speed);
            setY(getY() + speed);
        } else {
            setX(getX() - speed);
        }
        borderTesting();

    }

    public void borderTesting() {
        if (getY() > FrameConstant.FRAME_HEIGHT) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.propList.remove(this);
        }
        if (getX() + image.getWidth(null) >= FrameConstant.FRAME_WIDTH) {
            right = false;
        } else if (getX() < 0) {
            right = true;
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    //道具与我方飞机碰撞
    public void collisionTesting(MyPlane myPlane) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (myPlane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.propList.remove(this);
            myPlane.setType(1);
            myPlane.setImage(ImageMap.get("plane03"));
        }

    }

}
