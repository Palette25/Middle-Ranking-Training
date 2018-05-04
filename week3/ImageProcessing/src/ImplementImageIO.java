import imagereader.IImageIO;
import java.io.FileInputStream;
import java.io.File;
import javax.tools.Tool;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

/**
 * Created by Lenovo on 2018/4/27.
 */
public class ImplementImageIO implements IImageIO {
    /*
    * Implemented myRead method
     */
    public Image myRead(String filePath){
        try{
            FileInputStream stream = new FileInputStream(filePath);
            ImageInfoReader reader = new ImageInfoReader(stream);
            Image result = reader.turnStreamIntoImage();
            stream.close();
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /*
    * Implemented myWrite method
     */
    public Image myWrite(Image image, String filePath){
        try{
            File destFile = new File(filePath);
            BufferedImage buff = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
            Graphics graphic = buff.createGraphics();
            graphic.drawImage(image, 0, 0, null);
            graphic.dispose();
            ImageIO.write(buff, "bmp", destFile);
            return image;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public class ImageInfoReader{

        public int bitHeight; // The height of the bitmap image
        public int bitWidth; // The width of the bitmap image
        public int bitSize; // The bitmap size
        public int pixelBit; // The bit number of every pixel
        public int[] imagePixels; // The pixel array of the image
        public byte[] imageHead; // The head message of bitmap image
        public byte[] imageContent; // The content info of bitmap Image

        /*
        * ImageInfoReader constructer, accept the binary file stream
        */
        public ImageInfoReader(FileInputStream stream){
            try{
                imageHead = new byte[14];
                imageContent = new byte[40];
                stream.read(imageHead, 0, 14);
                stream.read(imageContent, 0, 40);
                bitSize = (int)((imageContent[23] & 0xFF) << 24 | (imageContent[22] & 0xFF) << 16 | 
                            (imageContent[21] & 0xFF) << 8 | (imageContent[20] & 0xFF));
                bitHeight = (int)((imageContent[11] & 0xff) << 24 | (imageContent[10] & 0xFF) << 16 |
                            (imageContent[9] & 0xFF) << 8 | (imageContent[8] & 0xFF));
                bitWidth = (int)((imageContent[7] & 0xFF) << 24 | (imageContent[6] & 0xFF) << 16 |
                            (imageContent[5] & 0xFF) << 8 | (imageContent[4] & 0xFF));
                pixelBit = (int)((imageContent[15] & 0xFF) << 8 | (imageContent[14] & 0xFF));
                /*
                * Judge whether the bit length of every pixel is 24
                */
                if(pixelBit == 24){
                    int emptyBytesNum = (bitSize / bitHeight) - 3*bitWidth;
                /*
                * Decide the empty byte in every row, and if it's four then don't need
                * to make empty spaces
                */
                    if(emptyBytesNum == 4){
                        emptyBytesNum = 0;
                    }
                    imagePixels = new int[bitWidth * bitHeight];
                    byte allPixels[] = new byte[bitSize];
                    stream.read(allPixels, 0, bitSize);
                    int count = 0;
                /*
                * Loop the fill the pixel array of this bmp image, storing from down to
                * up, left to right
                */
                    for(int i=bitHeight-1; i>=0; i--){
                        for(int j=0; j<bitWidth; j++){
                            imagePixels[j + i * bitWidth] = 0xFF << 24 // They say this make the opacity correct
                            | (allPixels[count+2] & 0xFF) << 16 | (allPixels[count+1] & 0xFF) << 8
                            | (allPixels[count] & 0xFF);    
                             count += 3; 
                        }
                       count += emptyBytesNum;
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        /*
        * Turn binary file stream into BMP image
        */
        public Image turnStreamIntoImage(){
            Toolkit kit = Toolkit.getDefaultToolkit();
            MemoryImageSource source = new MemoryImageSource(bitWidth, bitHeight, 
                                                        imagePixels, 0, bitWidth);
            Image result = kit.createImage((ImageProducer)source);
            return result;
        }

    }
}
