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

public class EnempBullet extends BaseSprite implements Moveable, Drawable {

    private Image image;
    private int speed = FrameConstant.GAME_SPEED * 5;

    public EnempBullet() {
        this(0,0, ImageMap.get("epb01"));
    }

    public EnempBullet(int x, int y, Image image) {
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
        setY(getY() + speed);
    }

    public void borderTesting(){
        if (getY() > FrameConstant.FRAME_HEIGHT){
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.enempBulletList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    public void collisionTesting(MyPlane myPlane){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (myPlane.getRectangle().intersects(this.getRectangle())){
            gameFrame.enempBulletList.remove(this);
            gameFrame.gameOver = true;
        }
    }
}
