package com.neu.edu.main;

import com.neu.edu.constant.FrameConstant;
import com.neu.edu.runtime.Backgroud;
import com.neu.edu.runtime.PlaneFly;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends Frame {

    private Backgroud backgroud = new Backgroud();
    private PlaneFly planeFly = new PlaneFly();


    @Override
    public void paint(Graphics g) {

        backgroud.draw(g);
        planeFly.draw(g);
    }

    /**
     * 使用这个方法初始化窗口
     */

    public void init(){
        setSize(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);

        setLocationRelativeTo(null);
        enableInputMethods(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
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
