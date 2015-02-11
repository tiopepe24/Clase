/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

/**
 *
 * @author dvd
 */
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
 
/**
 *
 * @author Jumi i Jaume
 */
public class AvioGraphics {
 
    public static BufferedImage imgAvio1;
 
    private static boolean isLoad = false;
 
    private static void loadCarImages() {
        if (AvioGraphics.isLoad) {
            return; 
        }
        AvioGraphics.imgAvio1 = AvioGraphics.loadImage("C://Pruebasficheros/avion_1.png");
        AvioGraphics.isLoad = true;
    }
 
    private static BufferedImage loadImage(String fileName) {
        BufferedImage img;
 
        img = null;
        try {
            img = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.out.println("Load image error: <" + fileName + ">");
            img = null;
        }
 
        return img;
    }
 
    public static BufferedImage processCarImg(BufferedImage imgSrc, int cmWidth, int cmHeight, float factorX, float factorY, double rotationAngle) {
        int xCenterTranslate, yCenterTranslate;
        float xCenterNew, yCenterNew;
        float xCenterFinal, yCenterFinal;
        int xyPixRotate, xPixFinal, yPixFinal;
        BufferedImage imgRotate, imgFinal;
        Graphics2D g2d;
 
        if (imgSrc == null) {
            return null; //====================================================>>
        }
        xyPixRotate = (int) (Math.sqrt(Math.pow(imgSrc.getHeight(), 2) + Math.pow(imgSrc.getWidth(), 2) / 4));
        imgRotate = new BufferedImage(xyPixRotate, xyPixRotate, BufferedImage.TYPE_INT_ARGB);
 
        xCenterNew = imgSrc.getWidth() / 2.0f;
        yCenterNew = imgSrc.getHeight() / 2.0f;
        xCenterFinal = xyPixRotate / 2.0f;
        yCenterFinal = imgRotate.getHeight() / 2.0f;
        xCenterTranslate = (int) (xCenterFinal - xCenterNew);
        yCenterTranslate = (int) (yCenterFinal - yCenterNew);
 
        g2d = imgRotate.createGraphics();
        g2d.translate(xCenterTranslate, yCenterTranslate);
        g2d.rotate(rotationAngle, xCenterNew, yCenterNew);
 
        g2d.drawImage(imgSrc, 0, 0, null);
        g2d.dispose();
 
        yPixFinal = (int) ((Math.sqrt(Math.pow(cmHeight, 2) + Math.pow(cmWidth, 2) / 4)) / factorY);
        xPixFinal = (int) ((Math.sqrt(Math.pow(cmHeight, 2) + Math.pow(cmWidth, 2) / 4)) / factorX);
 
        imgFinal = new BufferedImage(xPixFinal, yPixFinal, BufferedImage.TYPE_INT_ARGB);
        g2d = imgFinal.createGraphics();
        g2d.drawImage(imgRotate, 0, 0, xPixFinal, yPixFinal, 0, 0, xyPixRotate, xyPixRotate, null);
        g2d.dispose();
 
        return imgFinal;
    }
 
    public static BufferedImage getCarImage(Avion avio, float factorX, float factorY, double rotationAngle) {
        BufferedImage imgSrc;
 
        if (!AvioGraphics.isLoad) {
            AvioGraphics.loadCarImages();
        }
 
        imgSrc = AvioGraphics.imgAvio1;
 
 
        if (imgSrc == null) {
            imgSrc = AvioGraphics.createCarImg(avio, factorX, factorY);
        }
 
        imgSrc = AvioGraphics.processCarImg(imgSrc, avio.getWidthInCm(), avio.getLongInCm(), factorX, factorY, rotationAngle);
 
 
        return imgSrc;
    }
 
    public static BufferedImage getCarImage(Avion avio) {
        BufferedImage imgSource, imgTarget;
        Graphics2D g2d;
 
        if (!AvioGraphics.isLoad) {
            AvioGraphics.loadCarImages();
        }
 
        imgTarget = imgSource = null;
 
                imgSource = AvioGraphics.imgAvio1;
 
 
        if (imgSource != null) {
            imgTarget = new BufferedImage(imgSource.getWidth(), imgSource.getHeight(), BufferedImage.TYPE_INT_ARGB);
            g2d = (Graphics2D) imgTarget.getGraphics();
            g2d.drawImage(imgSource, 0, 0, imgSource.getWidth(), imgSource.getHeight(), null);
            g2d.dispose();
        }
 
        return imgTarget;
    }
 
    private static BufferedImage createCarImg(Avion avio, float factorX, float factorY) {
        BufferedImage imgSrc;
        Graphics2D g2d;
 
        imgSrc = new BufferedImage(
                (int) (Math.max(1, avio.getWidthInCm() / factorX)),
                (int) Math.max(1, avio.getLongInCm() / factorY),
                BufferedImage.TYPE_INT_ARGB);
 
        g2d = imgSrc.createGraphics();
 
        g2d.setColor(avio.getColor());
        g2d.fillRect(0, 0, imgSrc.getWidth(), imgSrc.getHeight());
        g2d.setColor(avio.getColor().darker());
        g2d.drawRect(0, 0, imgSrc.getWidth(), imgSrc.getHeight());
 
        g2d.dispose();
 
        return imgSrc;
    }
}
