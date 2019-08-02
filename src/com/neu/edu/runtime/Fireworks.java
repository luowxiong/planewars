package com.neu.edu.runtime;

import com.neu.edu.base.BaseSprite;
import com.neu.edu.base.Drawable;
import com.neu.edu.main.GameFrame;
import com.neu.edu.util.DataStore;
import com.neu.edu.util.ImageMap;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Fireworks extends BaseSprite implements Drawable {

    public static List<Image> igList = new ArrayList<>();

    static {
        igList.add(ImageMap.get("e_1"));
        igList.add(ImageMap.get("e_2"));
        igList.add(ImageMap.get("e_3"));
        igList.add(ImageMap.get("e_4"));
        igList.add(ImageMap.get("e_5"));
        igList.add(ImageMap.get("e_6"));
        igList.add(ImageMap.get("e_7"));
        igList.add(ImageMap.get("e_8"));
        igList.add(ImageMap.get("e_9"));


    }


    public Fireworks() {
        this(0,0,igList);
    }

    public Fireworks(int x, int y, List<Image> imageList) {
        super(x, y);
        this.igList = imageList;
    }

    GameFrame gameFrame = DataStore.get("gameFrame");
    private int index = 0;
    @Override
    public void draw(Graphics g) {

        g.drawImage(igList.get(index++ / 5), getX(), getY(), igList.get(0).getWidth(null),
                igList.get(0).getHeight(null), null);
        if (index >= 45) {
            gameFrame.fireworksList.remove(this);
        }
    }
}
