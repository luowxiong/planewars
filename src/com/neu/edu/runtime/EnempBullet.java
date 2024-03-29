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
import java.util.Random;

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
        move();
        borderTesting();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);

    }

    private Random random = new Random();
    private int type = random.nextInt(3);
    @Override
    public void move() {
        if (type == 0){
            setY(getY() + speed);
        }
        if (type == 1) {
            setY(getY() + speed);
            setX(getX() + speed);
        }
        if (type == 2){
            setY(getY() + speed);
            setX(getX() - speed);
        }
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

    //敌方子弹与我方飞机的碰撞
    public void collisionTesting(MyPlane myPlane){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (myPlane.getRectangle().intersects(this.getRectangle())){
            gameFrame.enempBulletList.remove(this);
            if (gameFrame.bloodList.size() > 0) {
                gameFrame.bloodList.remove(0);
            }
        }
    }
}
