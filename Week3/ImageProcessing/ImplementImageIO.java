import imagereader.IImageIO;
import java.io.FileInputStream;
import java.io.File;
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
            return null;
        }
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
            return null;
        }
    }

    /*
    * My image info reader for myRead method
    */
    public class ImageInfoReader{

        // The height of the bitmap image
        private int bitHeight; 
        // The width of the bitmap image
        private int bitWidth; 
        // The bitmap size
        private int bitSize; 
        // The bit number of every pixel
        private int pixelBit; 
        // The pixel array of the image
        private int[] imagePixels; 
        // The head message of bitmap image
        private byte[] imageHead; 
        // The content info of bitmap Image
        private byte[] imageContent; 

        private static final int FAC = 0xFF;
        /*
        * ImageInfoReader constructer, accept the binary file stream
        */
        public ImageInfoReader(FileInputStream stream){
            imageHead = new byte[14];
            imageContent = new byte[40];
            try{
                stream.read(imageHead, 0, 14);
                stream.read(imageContent, 0, 40);
            }catch(Exception e){
                return;
            }
            int tempVar1 = (imageContent[23] & FAC) << 24 | (imageContent[22] & FAC) << 16;
            int tempVar2 = (imageContent[21] & FAC) << 8 | (imageContent[20] & FAC);
            bitSize = (int)( tempVar1 | tempVar2);
            tempVar1 = (imageContent[11] & FAC) << 24 | (imageContent[10] & FAC) << 16;
            tempVar2 = (imageContent[9] & FAC) << 8 | (imageContent[8] & FAC);
            bitHeight = (int)( tempVar1 | tempVar2);
            tempVar1 = (imageContent[7] & FAC) << 24 | (imageContent[6] & FAC) << 16;
            tempVar2 = (imageContent[5] & FAC) << 8 | (imageContent[4] & FAC);
            bitWidth = (int)( tempVar1 | tempVar2);
            pixelBit = (int)((imageContent[15] & FAC) << 8 | (imageContent[14] & FAC));
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
                try{
                    stream.read(allPixels, 0, bitSize);
                }catch(Exception e){
                    return;
                }
                int count = 0;
            /*
            * Loop the fill the pixel array of this bmp image, storing from down to
            * up, left to right
            */
                for(int i=bitHeight-1; i>=0; i--){
                    for(int j=0; j<bitWidth; j++){
                        // They say this make the opacity correct
                        int temp = FAC << 24 | (allPixels[count+2] & FAC) << 16;
                        int tenp = (allPixels[count+1] & FAC) << 8| (allPixels[count] & FAC);
                        imagePixels[j + i * bitWidth] = temp | tenp;    
                            count += 3; 
                    }
                    count += emptyBytesNum;
                }
            }
    }

        /*
        * Turn binary file stream into BMP image
        */
        public Image turnStreamIntoImage(){
            Toolkit kit = Toolkit.getDefaultToolkit();
            MemoryImageSource source = new MemoryImageSource(bitWidth, bitHeight, 
                                                        imagePixels, 0, bitWidth);
            return kit.createImage((ImageProducer)source);
        }

    }
}
