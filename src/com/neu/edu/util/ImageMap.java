package com.neu.edu.util;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {
    private static final Map<String, Image> map = new HashMap<>();

    static {
        //背景
        map.put("bg01",ImageUtil.getImage("com\\neu\\edu\\imgs\\bg\\bg01.png"));
        map.put("bg02",ImageUtil.getImage("com\\neu\\edu\\imgs\\bg\\bg02.jpg"));
        map.put("bg03",ImageUtil.getImage("com\\neu\\edu\\imgs\\bg\\bg03.jpg"));
        map.put("bg04",ImageUtil.getImage("com\\neu\\edu\\imgs\\bg\\bg04.jpg"));
        //我方飞机
        map.put("plane01",ImageUtil.getImage("com\\neu\\edu\\imgs\\plane\\plane01.png"));
        map.put("plane02",ImageUtil.getImage("com\\neu\\edu\\imgs\\plane\\plane02.png"));
        map.put("plane03",ImageUtil.getImage("com\\neu\\edu\\imgs\\plane\\plane03.png"));

        //敌方飞机
        map.put("ep00",ImageUtil.getImage("com\\neu\\edu\\imgs\\plane\\ep_0.png"));
        map.put("ep01",ImageUtil.getImage("com\\neu\\edu\\imgs\\plane\\ep_1.png"));
        map.put("ep02",ImageUtil.getImage("com\\neu\\edu\\imgs\\plane\\ep_2.png"));
        map.put("ep03",ImageUtil.getImage("com\\neu\\edu\\imgs\\plane\\ep_3.png"));
        map.put("ep04",ImageUtil.getImage("com\\neu\\edu\\imgs\\plane\\ep_4.png"));
        map.put("ep05",ImageUtil.getImage("com\\neu\\edu\\imgs\\plane\\ep_5.png"));
        //我方子弹
        map.put("myb01",ImageUtil.getImage("com\\neu\\edu\\imgs\\bullet\\myb_1.png"));
        map.put("myb02",ImageUtil.getImage("com\\neu\\edu\\imgs\\bullet\\myb_2.png"));
        map.put("myb03",ImageUtil.getImage("com\\neu\\edu\\imgs\\bullet\\myb_3.png"));
        //敌方子弹
        map.put("epb01",ImageUtil.getImage("com\\neu\\edu\\imgs\\bullet\\epb_1.png"));
        map.put("epb02",ImageUtil.getImage("com\\neu\\edu\\imgs\\bullet\\epb_2.png"));
        map.put("epb03",ImageUtil.getImage("com\\neu\\edu\\imgs\\bullet\\epb_3.png"));
        //我方血量
        map.put("bd01",ImageUtil.getImage("com\\neu\\edu\\imgs\\blood\\bd01.png"));
        //道具
        map.put("prop01",ImageUtil.getImage("com\\neu\\edu\\imgs\\prop\\prop01.png"));
        //特效
        map.put("e1",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\BOSS01_1.png"));
        map.put("e2",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\BOSS01_2.png"));
        map.put("e3",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\BOSS01_3.png"));
        map.put("e4",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\BOSS01_4.png"));
        map.put("e5",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\BOSS01_5.png"));
        map.put("e6",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\BOSS01_6.png"));
        map.put("e7",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\BOSS01_7.png"));
        map.put("e8",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\BOSS01_8.png"));

        //特效2
        map.put("e_1",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\e1.png"));
        map.put("e_2",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\e2.png"));
        map.put("e_3",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\e3.png"));
        map.put("e_4",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\e4.png"));
        map.put("e_5",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\e5.png"));
        map.put("e_6",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\e6.png"));
        map.put("e_7",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\e7.png"));
        map.put("e_8",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\e8.png"));
        map.put("e_9",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\e9.png"));

        map.put("e01",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\1.png"));
        map.put("e02",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\2.png"));
        map.put("e03",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\3.png"));
        map.put("e04",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\4.png"));
        map.put("e05",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\5.png"));
        map.put("e06",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\6.png"));
        map.put("e07",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\7.png"));
        map.put("e08",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\8.png"));
        map.put("e09",ImageUtil.getImage("com\\neu\\edu\\imgs\\effect\\9.png"));



    }
    public static Image get(String key){
        return map.get(key);
    }
}
