package com.neu.edu.util;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {
    private static final Map<String, Image> map = new HashMap<>();

    static {
        map.put("bg01",ImageUtil.getImage("com\\neu\\edu\\imgs\\bg\\bg01.png"));
        map.put("plane01",ImageUtil.getImage("com\\neu\\edu\\imgs\\plane\\plane03.png"));
    }
    public static Image get(String key){
        return map.get(key);
    }
}
