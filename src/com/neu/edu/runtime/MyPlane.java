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
import java.awt.event.KeyEvent;

public class MyPlane extends BaseSprite implements Moveable, Drawable {
    private Image image;

    private int speed = FrameConstant.GAME_SPEED * 3;

    private boolean up, right, down, left;
    private boolean fire;

    public MyPlane() {
        this((FrameConstant.FRAME_WIDTH - ImageMap.get("plane01").getWidth(null)) / 2,
                FrameConstant.FRAME_HEIGHT - ImageMap.get("plane01").getHeight(null),
                ImageMap.get("plane01"));

    }

    public MyPlane(int x, int y, Image image) {
        super(x, y);
        this.image = image;

    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        move();
//        fire();
//        if (fire){
//            index++;
//            if (index >= 10){
//                index = 0;
//            }
//        }
    }

    /**
     * 开火方法
     */
//    private int index = 0;

    public void fire() {
        if (fire) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.myBulletList.add(new MyBullet(
                    getX() + (image.getWidth(null) / 2) - (ImageMap.get("myb01").getWidth(null) / 2),
                    getY() -ImageMap.get("myb01").getWidth(null),
                    ImageMap.get("myb01")

            ));
        }
    }

    @Override
    public void move() {
//        setY(getY() - 1);
        if (up) {
            setY(getY() - speed);
        }
        if (right) {
            setX(getX() + speed);
        }
        if (down) {
            setY(getY() + speed);
        }
        if (left) {
            setX(getX() - speed);
        }
        borderTesting();
    }

    //边缘检测
    public void borderTesting() {
        if (getX() < 0) {
            setX(0);
        }
        if (getX() > FrameConstant.FRAME_WIDTH - image.getWidth(null)) {
            setX(FrameConstant.FRAME_WIDTH - image.getWidth(null));
        }
        if (getY() < 30) {
            setY(30);
        }
        if (getY() > FrameConstant.FRAME_HEIGHT - image.getHeight(null)) {
            setY(FrameConstant.FRAME_HEIGHT - image.getHeight(null));
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
             fire = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            fire();
            fire = false;
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }
}
