package com.neu.edu.runtime;

import com.neu.edu.base.BaseSprite;
import com.neu.edu.base.Drawable;
import com.neu.edu.base.Moveable;
import com.neu.edu.constant.FrameConstant;
import com.neu.edu.util.ImageMap;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;


public class Effect extends BaseSprite implements Drawable, Moveable {

    private List<Image> imageList = new ArrayList<>();

    public Effect() {
        for (int i = 1; i < 9; i++) {
            imageList.add(ImageMap.get("e" + i));
        }
    }


    private int index = 0;


    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(imageList.get(index++ / 5), getX(), getY(), imageList.get(0).getWidth(null),
                imageList.get(0).getHeight(null), null);
        if (index >= 40) {
            index = 0;
        }
    }

    private boolean  right = true;
    private boolean  up, left, down;

    @Override
    public void move() {
        if (left){
            setX(getX() - FrameConstant.GAME_SPEED * 10);
            setY(getY() + FrameConstant.GAME_SPEED * 5);

        }
        if (up){
            setX(getX() - FrameConstant.GAME_SPEED * 10);
            setY(getY() - FrameConstant.GAME_SPEED * 5);

        }
        if (right){
            setX(getX() + FrameConstant.GAME_SPEED * 2);
            setY(getY() - FrameConstant.GAME_SPEED * 10);
        }
        if (down){
            setX(getX() + FrameConstant.GAME_SPEED * 10);
            setY(getY() + FrameConstant.GAME_SPEED * 5);
        }
        borderTesting();
    }

    public void borderTesting() {
        if (getX() + imageList.get(0).getWidth(null) >= FrameConstant.FRAME_WIDTH) {
            left = true;
            right = false;
            up = false;
            down = false;
        }
        if (getX() < 0){
            right = true;
            up = false;
            down = false;
            left = false;
        }
        if (getY() < 30){
            down = true;
            right = false;
            up = false;
            left = false;

        }
        if (getY() > FrameConstant.FRAME_HEIGHT - imageList.get(0).getHeight(null)){
            up = true;
            right = false;
            down = false;
            left = false;
        }
    }


}
