import javax.tools.Tool;
import java.awt.*;
import java.awt.image.RGBImageFilter;
import java.awt.image.FilteredImageSource;
import imagereader.IImageProcessor;

/**
 * Created by Lenovo on 2018/4/27.
 */
public class ImplementImageProcessor implements IImageProcessor{
    public int alphaRGB = 0xFF000000;
    public int redRGB = 0xFFFF0000;
    public int greenRGB = 0xFF00FF00;
    public int blueRGB = 0xFF0000FF;

    public Image showChanelR(Image sourceImage){
        MyRGBFilter redFilter = new MyRGBFilter("red");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(new FilteredImageSource(sourceImage.getSource(), redFilter));
        return img;
    }

    public Image showChanelG(Image sourceImage){
        MyRGBFilter greenFilter = new MyRGBFilter("green");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(new FilteredImageSource(sourceImage.getSource(), greenFilter));
        return img;
    }

    public Image showChanelB(Image sourceImage){
        MyRGBFilter blueFilter = new MyRGBFilter("blue");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(new FilteredImageSource(sourceImage.getSource(), blueFilter));
        return img;
    }

    public Image showGray(Image sourceImage){
        MyRGBFilter grayFilter = new MyRGBFilter("gray");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(new FilteredImageSource(sourceImage.getSource(), grayFilter));
        return img;
    }

    public class MyRGBFilter extends RGBImageFilter{
        /*
        * colorType decides the kind of this RGBfilter
         */
        private String colorType;

        public MyRGBFilter(String type){
            colorType = type;
            canFilterIndexColorModel = true;
        }
        /*
        * Override filterRGB method, provide four choices for color filting
        */
        public int filterRGB(int x, int y, int rgb){
            switch(colorType){
                case "red": return rgb & redRGB;
                case "green": return rgb & greenRGB;
                case "blue": return rgb & blueRGB;
                default: return getGrayValue(x, y, rgb);
            }
        }

        /*
        * Gte Gray color RGB value method
        */
        public int getGrayValue(int x, int y, int rgb){
            // Get the alpha value from 24 to 31 bit
            int alpha = rgb & alphaRGB; 
            int result_rgb = (int)(((rgb & 0x00FF0000) >> 16)*0.299 + ((rgb & 0x0000FF00) >> 8)*0.587+
                    (rgb & 0x000000FF)*0.114);
            return alpha + (result_rgb << 16) + (result_rgb << 8) + result_rgb;
        }
    }
}
