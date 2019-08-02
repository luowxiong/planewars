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

public class Boss extends EnempPlane implements Moveable, Drawable {

    private Image image;
    private int speed = FrameConstant.GAME_SPEED * 5;
    private Random random = new Random();

    public Boss() {
        this((FrameConstant.FRAME_WIDTH - ImageMap.get("ep05").getWidth(null)) / 2,
                30,  ImageMap.get("ep01"), ImageMap.get("ep05"));
    }

    public Boss(int x, int y, Image image, Image image1) {
        super(x, y, image);
        this.image = image1;
    }

    @Override
    public void draw(Graphics g) {
        move();
        fire();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);

    }

    private boolean right = true;
    @Override
    public void move() {
        if (right){
            setX(getX() + speed);
        }else {
            setX(getX() - speed);
        }
        borderTesting();
    }

    @Override
    public void fire() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (random.nextInt(1000) > 900) {
            gameFrame.enempBulletList.add(new EnempBullet(
                    getX() + (image.getWidth(null)) / 2 - (ImageMap.get("epb02").getWidth(null)) / 2,
                    getY() + image.getHeight(null),
                    ImageMap.get("epb02")));
        }
    }

    @Override
    public void borderTesting(){
        if (getX() + image.getWidth(null) >= FrameConstant.FRAME_WIDTH) {
            right = false;
        }else if (getX() < 0){
            right = true;
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }
}
