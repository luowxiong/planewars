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
import java.util.ArrayList;
import java.util.List;

public class MyBullet extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int speed = FrameConstant.GAME_SPEED * 5;

    public MyBullet() {
        this(0,0,null);
    }

    public MyBullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }


    @Override
    public void draw(Graphics g) {
        move();
        borderTesting();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);

    }

    @Override
    public void move() {
        setY(getY() - speed);

    }

    public void borderTesting() {
        if (getY() < 30 - image.getHeight(null)) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.myBulletList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    //我方子弹与敌方飞机的碰撞
    public void collisionTesting(List<EnempPlane> enempPlaneList) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (EnempPlane enempPlane : enempPlaneList) {
            if (enempPlane.getRectangle().intersects(this.getRectangle())) {
                enempPlaneList.remove(enempPlane);
                gameFrame.fireworksList.add(new Fireworks(enempPlane.getX(), enempPlane.getY(), Fireworks.igList));
                gameFrame.myBulletList.remove(this);
                gameFrame.score += 1;

            }
        }
    }


    //我方子弹与boss的碰撞
    public void collisionBoss(Boss boss) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (boss.getRectangle().intersects(this.getRectangle())) {
            gameFrame.myBulletList.remove(this);
            if (gameFrame.bossBloodList.size() > 0) {
                gameFrame.bossBloodList.remove(0);
            }

        }
    }

    //我方子弹与敌方子弹的碰撞
    public void collisionBullet(List<EnempBullet> enempBulletList) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (EnempBullet enempBullet : enempBulletList) {
            if (enempBullet.getRectangle().intersects(this.getRectangle())) {
                enempBulletList.remove(enempBullet);
                gameFrame.myBulletList.remove(this);
            }
        }
    }
}
