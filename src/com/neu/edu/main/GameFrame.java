package com.neu.edu.main;

import com.neu.edu.constant.FrameConstant;
import com.neu.edu.runtime.Backgroud;
import com.neu.edu.runtime.Blood;
import com.neu.edu.runtime.Boss;
import com.neu.edu.runtime.Effect;
import com.neu.edu.runtime.EnempBullet;
import com.neu.edu.runtime.EnempPlane;
import com.neu.edu.runtime.Fireworks;
import com.neu.edu.runtime.MyBullet;
import com.neu.edu.runtime.MyPlane;
import com.neu.edu.runtime.Prop;
import com.neu.edu.util.ImageMap;

import java.awt.Color;
import java.awt.Font;
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
    //创建敌方飞机Boss对象
    private Boss boss = new Boss();
    //创建自己子弹集合
    public final List<MyBullet> myBulletList = new CopyOnWriteArrayList<>();
    //创建敌方飞机集合
    public List<EnempPlane> enempPlaneList = new CopyOnWriteArrayList<>();
    //创建敌方子弹集合
    public final List<EnempBullet> enempBulletList = new CopyOnWriteArrayList<>();
    //创建我方血量
    public final List<Blood> bloodList = new CopyOnWriteArrayList<>();
    //判断游戏是否结束
    public boolean gameOver = false;
    //创建积分变量
    public Integer score = 0;
    //创建boss血量集合
    public final List<Blood> bossBloodList = new CopyOnWriteArrayList<>();
    //创建道具集合
    public final List<Prop> propList = new CopyOnWriteArrayList<>();
    //创建特效对象
    private Effect effect = new Effect();
    //创建特效集合
    public List<Fireworks> fireworksList = new CopyOnWriteArrayList<>();



    @Override
    public void paint(Graphics g) {
        if (!gameOver) {
            backgroud.draw(g);
            myPlane.draw(g);
            effect.draw(g);

            for (Fireworks fireworks : fireworksList) {
                fireworks.draw(g);
            }


            for (Prop prop : propList) {
                prop.draw(g);
                prop.collisionTesting(myPlane);
            }


            if (score >= 20 && bossBloodList.size() > 0) {
                boss.draw(g);
            }
            for (Blood blood : bloodList) {
                blood.draw(g);
            }
            if (score >= 20 && bossBloodList.size() > 0) {
                for (Blood bossBlood : bossBloodList) {
                    bossBlood.draw(g);
                }
            }
            for (MyBullet myBullet : myBulletList) {
                myBullet.draw(g);
            }
            for (EnempPlane enempPlane : enempPlaneList) {
                enempPlane.draw(g);
                enempPlane.addPlane();
                enempPlane.collisionTesting(myPlane);
            }

            for (EnempBullet enempBullet : enempBulletList) {
                enempBullet.draw(g);
            }

            for (MyBullet myBullet : myBulletList) {
                myBullet.collisionTesting(enempPlaneList);
            }
//            for (EnempBullet enempBullet : enempBulletList) {
//                enempBullet.collisionTesting(myPlane);
//            }
            for (MyBullet myBullet : myBulletList) {
                myBullet.collisionBullet(enempBulletList);
                if (score >= 20 && bossBloodList.size() > 0) {
                    myBullet.collisionBoss(boss);
                }
            }


            //记分数
            g.setFont(new Font("行楷", Font.BOLD, 18));
            g.setColor(new Color(255, 255, 255));
            g.drawString("得分:" + score, 20, 80);

        }

        if (bloodList.size() == 0) {
            g.setFont(new Font("行楷", Font.BOLD, 18));
            g.setColor(new Color(87, 255, 31));
            g.drawString("GameOver,你真菜",20,100);
            gameOver = true;
        }

        if (bossBloodList.size() == 0) {
            g.setFont(new Font("行楷", Font.BOLD, 18));
            g.setColor(new Color(87, 255, 31));
            g.drawString("PassOne,你真强",20,100);

            backgroud.setImage(ImageMap.get("bg02"));
        }


    }

    /**
     * 使用这个方法初始化窗口
     */

    public void init() {
        setSize(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);

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



        new Thread() {
            @Override
            public void run() {
                while (true) {
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

        enempPlaneList.add(new EnempPlane(random.nextInt(700), random.nextInt(30), ImageMap.get("ep01")));
        enempPlaneList.add(new EnempPlane(random.nextInt(700), random.nextInt(30), ImageMap.get("ep02")));
        enempPlaneList.add(new EnempPlane(random.nextInt(700), random.nextInt(30), ImageMap.get("ep03")));
        enempPlaneList.add(new EnempPlane(random.nextInt(700), random.nextInt(30), ImageMap.get("ep04")));

        //游戏初始化道具
        propList.add(new Prop(400, -500, ImageMap.get("prop01")));
        propList.add(new Prop(400, -1200, ImageMap.get("prop01")));

        //游戏初始化前添加初始血量
        bloodList.add(new Blood(10, 30, ImageMap.get("bd01")));
        bloodList.add(new Blood(30, 30, ImageMap.get("bd01")));
        bloodList.add(new Blood(50, 30, ImageMap.get("bd01")));


        //游戏初始化前添加boss初始血量
        bossBloodList.add(new Blood(760, 30, ImageMap.get("bd01")));
        bossBloodList.add(new Blood(730, 30, ImageMap.get("bd01")));
        bossBloodList.add(new Blood(700, 30, ImageMap.get("bd01")));
        bossBloodList.add(new Blood(670, 30, ImageMap.get("bd01")));
        bossBloodList.add(new Blood(640, 30, ImageMap.get("bd01")));
        bossBloodList.add(new Blood(610, 30, ImageMap.get("bd01")));
        bossBloodList.add(new Blood(580, 30, ImageMap.get("bd01")));
        bossBloodList.add(new Blood(760, 50, ImageMap.get("bd01")));
        bossBloodList.add(new Blood(730, 50, ImageMap.get("bd01")));
        bossBloodList.add(new Blood(700, 50, ImageMap.get("bd01")));
        bossBloodList.add(new Blood(670, 50, ImageMap.get("bd01")));
        bossBloodList.add(new Blood(640, 50, ImageMap.get("bd01")));
        bossBloodList.add(new Blood(610, 50, ImageMap.get("bd01")));
        bossBloodList.add(new Blood(580, 50, ImageMap.get("bd01")));

        setVisible(true);
    }

    private Image offScreenImage = null;//创建缓冲区

    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);
        }
        Graphics gOff = offScreenImage.getGraphics();//创建离线图片的实例，在图片缓冲区绘图
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);//将缓冲图片绘制到窗口目标
    }
}
