/*
* ImageReaderTest.java
*/

import org.junit.*;
import static org.junit.Assert.*;
import java.awt.image.*;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;

public class ImageProcessorTest{
    private static final String STR0 = "img/0.bmp";
    private static final String STR1 = "img/1.bmp";
    // Test the myRead method in ImplementImageIO
    @Test
    public void test1() throws IOException{
        ImplementImageIO imageioer = new ImplementImageIO();
        FileInputStream firstImg = new FileInputStream(STR1);
        
        BufferedImage buff1 = ImageIO.read(firstImg);
        //Invoke myRead
        BufferedImage buff2 = imageToBufferedImage(imageioer.myRead(STR1));
        assertEquals(buff1.getWidth(), buff2.getWidth());
        assertEquals(buff1.getHeight(), buff2.getHeight());
        for(int i=0;i<buff1.getWidth();i++){
            for(int j=0;j<buff1.getHeight();j++){
                assertEquals(buff1.getRGB(i, j), buff2.getRGB(i, j));
            }
        }
    }

    // Test the myWrite method in ImplementImageIO
    @Test
    public void test2() throws IOException{
        ImplementImageIO imageioer = new ImplementImageIO();
        Image secondImg = imageioer.myRead(STR1);
        imageioer.myWrite(secondImg, STR0);

        FileInputStream target = new FileInputStream(STR0);
        BufferedImage buff1 = ImageIO.read(target);
        BufferedImage buff2 = imageToBufferedImage(secondImg);
        assertEquals(buff1.getWidth(), buff2.getWidth());
        assertEquals(buff1.getHeight(), buff2.getHeight());
        for(int i=0;i<buff1.getWidth();i++){
            for(int j=0;j<buff1.getHeight();j++){
                assertEquals(buff1.getRGB(i, j), buff2.getRGB(i, j));
            }
        }
    }

    // Test the show chanel methods in my Image processor with first bmp image
    @Test
    public void test3() throws IOException{
        ImplementImageProcessor processor = new ImplementImageProcessor();
        File firstImgBlue = new File("img/goal/1_blue_goal.bmp");
        File firstImgGray = new File("img/goal/1_gray_goal.bmp");
        File firstImgGreen = new File("img/goal/1_green_goal.bmp");
        File firstImgRed = new File("img/goal/1_red_goal.bmp");
        BufferedImage buffBlue = ImageIO.read(firstImgBlue);
        BufferedImage buffGray = ImageIO.read(firstImgGray);
        BufferedImage buffGreen = ImageIO.read(firstImgGreen);
        BufferedImage buffRed = ImageIO.read(firstImgRed);
        //Invoke myRead
        BufferedImage resultBlue = imageToBufferedImage(processor.showChanelB(ImageIO.read(new File(STR1))));
        BufferedImage resultGray = imageToBufferedImage(processor.showGray(ImageIO.read(new File(STR1))));
        BufferedImage resultGreen = imageToBufferedImage(processor.showChanelG(ImageIO.read(new File(STR1))));
        BufferedImage resultRed = imageToBufferedImage(processor.showChanelR(ImageIO.read(new File(STR1))));
        
        assertEquals(buffBlue.getWidth(), resultBlue.getWidth());
        assertEquals(buffGray.getHeight(), resultGray.getHeight());
        assertEquals(buffGreen.getWidth(), resultGreen.getWidth());
        assertEquals(buffRed.getWidth(), resultRed.getWidth());
        for(int i=0;i<buffBlue.getWidth();i++){
            for(int j=0;j<buffBlue.getHeight();j++){
                assertEquals(buffBlue.getRGB(i, j), resultBlue.getRGB(i, j));
                assertEquals(buffGray.getRGB(i, j), resultGray.getRGB(i, j));
                assertEquals(buffGreen.getRGB(i, j), resultGreen.getRGB(i, j));
                assertEquals(buffRed.getRGB(i, j), resultRed.getRGB(i, j));
            }
        }
    }

    public BufferedImage imageToBufferedImage(Image img){
        BufferedImage temp = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics graphic = temp.createGraphics();
        graphic.drawImage(img, 0, 0, null);
        graphic.dispose();
        return temp;
    }

}