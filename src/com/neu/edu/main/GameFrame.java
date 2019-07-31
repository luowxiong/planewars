package com.neu.edu.main;

import com.neu.edu.constant.FrameConstant;
import com.neu.edu.runtime.Backgroud;
import com.neu.edu.runtime.EnempBullet;
import com.neu.edu.runtime.EnempPlane;
import com.neu.edu.runtime.MyBullet;
import com.neu.edu.runtime.MyPlane;
import com.neu.edu.util.ImageMap;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {
    //创建背景对象
    private Backgroud backgroud = new Backgroud();
    //创建飞机对象
    private MyPlane myPlane = new MyPlane();
    //创建自己子弹集合
    public final List<MyBullet> myBulletList= new CopyOnWriteArrayList<>();
    //创建敌方飞机集合
    public final List<EnempPlane> enempPlaneList = new CopyOnWriteArrayList<>();
    //创建敌方子弹集合
    public final List<EnempBullet> enempBulletList = new CopyOnWriteArrayList<>();

    public boolean gameOver = false;

    @Override
    public void paint(Graphics g) {
        if (!gameOver){
            backgroud.draw(g);
            myPlane.draw(g);
            for (MyBullet myBullet : myBulletList) {
                myBullet.draw(g);
            }
            for (EnempPlane enempPlane : enempPlaneList) {
                enempPlane.draw(g);
            }

            for (EnempBullet enempBullet : enempBulletList) {
                enempBullet.draw(g);
            }

            for (MyBullet myBullet : myBulletList) {
                myBullet.collisionTesting(enempPlaneList);
            }
            for (EnempBullet enempBullet : enempBulletList) {
                enempBullet.collisionTesting(myPlane);
            }

//            g.setColor(Color.RED);
//            g.drawString("" + enempBulletList.size(),200,200);
        }


    }

    /**
     * 使用这个方法初始化窗口
     */

    public void init(){
        setSize(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);

        setLocationRelativeTo(null);
        enableInputMethods(false);

        setResizable(false);
        //关闭窗口监听
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //按键监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                myPlane.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                myPlane.keyReleased(e);
            }
        });


        new Thread(){
            @Override
            public void run() {
                while (true){
                    repaint();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        //游戏初始化前添加一些敌方飞机
        Random random = new Random();

        enempPlaneList.add(new EnempPlane(random.nextInt(700),random.nextInt(100), ImageMap.get("ep01")));
        enempPlaneList.add(new EnempPlane(random.nextInt(700),random.nextInt(100), ImageMap.get("ep01")));
        enempPlaneList.add(new EnempPlane(random.nextInt(700),random.nextInt(100), ImageMap.get("ep01")));
        enempPlaneList.add(new EnempPlane(random.nextInt(700),random.nextInt(100), ImageMap.get("ep01")));

        setVisible(true);
    }

    private Image offScreenImage = null;//创建缓冲区
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);
        }
        Graphics gOff = offScreenImage.getGraphics();//创建离线图片的实例，在图片缓冲区绘图
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);//将缓冲图片绘制到窗口目标
    }
}
