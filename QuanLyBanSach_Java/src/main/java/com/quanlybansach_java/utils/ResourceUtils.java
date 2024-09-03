package com.quanlybansach_java.utils;

import javafx.scene.image.Image;

public class ResourceUtils {
    public static Image getImage(String imageName) {
        String imagePath = "/com/img/" + imageName;
        return new Image(ResourceUtils.class.getResourceAsStream(imagePath));
    }
}
