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
    private int speed = FrameConstant.GAME_SPEED * 3;

    public EnempPlane() {
        this(0, 0, ImageMap.get("ep01"));
    }

    public EnempPlane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }


    @Override
    public void draw(Graphics g) {
        move();
        fire();
        addPlane();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);

    }

    //当飞机剩余3只以下随机再生飞机
    public void addPlane() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (gameFrame.enempPlaneList.size() <= 3) {
            gameFrame.enempPlaneList.add(new EnempPlane(random.nextInt(700),
                    random.nextInt(30), ImageMap.get("ep0" + random.nextInt(5))));
        }
    }

    public void fire() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (random.nextInt(1000) > 985) {
            gameFrame.enempBulletList.add(new EnempBullet(
                    getX() + (image.getWidth(null)) / 2 - (ImageMap.get("epb01").getWidth(null)) / 2,
                    getY() + image.getHeight(null),
                    ImageMap.get("epb01")));
        }
    }

    private boolean right = true;
    private boolean left = true;
    private int type = random.nextInt(3);
    @Override
    public void move() {
        if (type == 0){
            if (right){
                setX(getX() + speed);
                setY(getY() + speed);
            }else {
                setX(getX() - speed);
                setY(getY() + speed);
            }
        }
        if (type == 1){
            if (left){
                setX(getX() - speed);
                setY(getY() + speed);
            }else {
                setX(getX() + speed);
                setY(getY() + speed);
            }
        }
        if (type == 2){
            setY(getY() + speed);
        }


        borderTesting();
    }

    public void borderTesting() {
        if (getY() > FrameConstant.FRAME_HEIGHT) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.enempPlaneList.remove(this);
        }
        if (getX() + image.getWidth(null) >= FrameConstant.FRAME_WIDTH) {
            right = false;
            left = true;
        }else if (getX() < 0){
            right = true;
            left = false;
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    //敌方飞机与我方飞机的碰撞
    public void collisionTesting(MyPlane myPlane) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (myPlane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.enempPlaneList.remove(this);
            gameFrame.bloodList.remove(0);
        }
    }
}
