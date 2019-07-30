package com.neu.edu.util;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;

public class ImageUtil {
    public static Image getImage(String path){
        Image image = null;
        try {
            image = ImageIO.read(ImageUtil.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
