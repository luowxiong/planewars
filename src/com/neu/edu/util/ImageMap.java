package com.neu.edu.util;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {
    private static final Map<String, Image> map = new HashMap<>();

    static {
        //背景
        map.put("bg01",ImageUtil.getImage("com\\neu\\edu\\imgs\\bg\\bg01.png"));
        //我方飞机
        map.put("plane01",ImageUtil.getImage("com\\neu\\edu\\imgs\\plane\\plane02.png"));
        //敌方飞机
        map.put("ep01",ImageUtil.getImage("com\\neu\\edu\\imgs\\plane\\ep_1.png"));
        //我方子弹
        map.put("myb01",ImageUtil.getImage("com\\neu\\edu\\imgs\\bullet\\myb_1.png"));
        //敌方子弹
        map.put("epb01",ImageUtil.getImage("com\\neu\\edu\\imgs\\bullet\\epb_1.png"));
    }
    public static Image get(String key){
        return map.get(key);
    }
}
