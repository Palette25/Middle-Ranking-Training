import java.awt.*;
import java.awt.image.RGBImageFilter;
import java.awt.image.FilteredImageSource;
import imagereader.IImageProcessor;

/**
 * Created by Lenovo on 2018/4/27.
 */
public class ImplementImageProcessor implements IImageProcessor{
    private static final int ALPHARGB = 0xFF000000;
    private static final int REDRGB = 0xFFFF0000;
    private static final int GREENRGB = 0xFF00FF00;
    private static final int BLUERGB = 0xFF0000FF;

    public Image showChanelR(Image sourceImage){
        MyRGBFilter redFilter = new MyRGBFilter("red");
        Toolkit kit = Toolkit.getDefaultToolkit();
        return kit.createImage(new FilteredImageSource(sourceImage.getSource(), redFilter));
    }

    public Image showChanelG(Image sourceImage){
        MyRGBFilter greenFilter = new MyRGBFilter("green");
        Toolkit kit = Toolkit.getDefaultToolkit();
        return kit.createImage(new FilteredImageSource(sourceImage.getSource(), greenFilter));
    }

    public Image showChanelB(Image sourceImage){
        MyRGBFilter blueFilter = new MyRGBFilter("blue");
        Toolkit kit = Toolkit.getDefaultToolkit();
        return kit.createImage(new FilteredImageSource(sourceImage.getSource(), blueFilter));
    }

    public Image showGray(Image sourceImage){
        MyRGBFilter grayFilter = new MyRGBFilter("gray");
        Toolkit kit = Toolkit.getDefaultToolkit();
        return kit.createImage(new FilteredImageSource(sourceImage.getSource(), grayFilter));
    }

    public class MyRGBFilter extends RGBImageFilter{
        /*
        * colorType decides the kind of this RGBfilter
         */
        private String colorType;
        private static final int REDFAC = 0x00FF0000;
        private static final int GREENFAC = 0x0000FF00;
        private static final int BLUEFAC = 0x000000FF;

        public MyRGBFilter(String type){
            colorType = type;
            canFilterIndexColorModel = true;
        }
        /*
        * Override filterRGB method, provide four choices for color filting
        */
        public int filterRGB(int x, int y, int rgb){
            switch(colorType){
                case "red": return rgb & REDRGB;
                case "green": return rgb & GREENRGB;
                case "blue": return rgb & BLUERGB;
                default: return getGrayValue(x, y, rgb);
            }
        }

        /*
        * Gte Gray color RGB value method
        */
        public int getGrayValue(int x, int y, int rgb){
            // Get the alpha value from 24 to 31 bit
            int alpha = rgb & ALPHARGB; 
            int resultRGB = (int)(((rgb & REDFAC) >> 16)*0.299 + ((rgb & GREENFAC) >> 8)*0.587+
                    (rgb & BLUEFAC)*0.114);
            return alpha + (resultRGB << 16) + (resultRGB << 8) + resultRGB;
        }
    }
}
