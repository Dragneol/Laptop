package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DuongPTH
 */
public class ImageLoader {

    public static void loadImage(String url, JLabel label) throws Exception { // auto fit image
        try {
            BufferedImage bi = ImageIO.read(new File(url));
            double imgRatio = 1.0 * bi.getWidth() / bi.getHeight();
            double lblRatio = 1.0 * label.getWidth() / label.getHeight();
            int w, h;
            if (imgRatio > lblRatio) {
                if (bi.getWidth() < label.getWidth()) {
                    w = -1;
                } else {
                    w = label.getWidth();
                }
                h = -1;
            } else {
                w = -1;
                if (bi.getHeight() < label.getHeight()) {
                    h = -1;
                } else {
                    h = label.getHeight();
                }
            }
            Image img = bi.getScaledInstance(w, h, Image.SCALE_FAST);
            label.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            throw e;
        }
    }

    public static void saveImage(File srcFile, String des) { // copy image from src to des
        File desFile = new File(des);
        try {
            Files.copy(srcFile.toPath(), desFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
