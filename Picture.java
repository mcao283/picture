import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** To pixelate by dividing area into size x size.
 * @param size Side length of square area to pixelate.
 */
	public void pixelate(int size)
	{
		
		int red = 0;
		int green = 0;
		int blue = 0;
		Pixel[][] pixels = this.getPixels2D();
		
		for(int xCord = 0; xCord < pixels.length; xCord +=size)
		{
			for(int yCord = 0; yCord < pixels[yCord].length; yCord+=size)
			{
				int count = 0;
				
				for(int x = xCord; xCord < size + xCord; x ++)
				{
					for(int y = yCord; y < size + yCord; y ++)
					{
						if(x < pixels.length && y < pixels[0].length)
						{
							red += pixels[x][y].getRed();
							blue += pixels[x][y].getBlue();
							green += pixels[x][y].getGreen();
							count++;
						}
					}
				}
				
				red = red/(count);
				blue = blue/(count);
				green = green/(count);
				
				for(int x = xCord; x < size + xCord; x ++)
				{
					for(int y = yCord; y < size + yCord; y ++)
					{
						if(x < pixels.length && y < pixels[0].length)
						{
							pixels[x][y].setRed(red);
							pixels[x][y].setBlue(blue);
							pixels[x][y].setGreen(green);
						}
					}
				}
				
			}
		}
	}
	
  //blurs the picture
	public Picture blur(int size)
	{
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		
		
		for(int x = 0; x < resultPixels.length; x++)
		{
			for(int y = 0; y < resultPixels[x].length; y ++)
			{
				int red = 0;
				int blue = 0;
				int green = 0;
				int counter = 0;
				
				for(int xVal = x - size/2; xVal < x + size; xVal ++)
				{
					for(int yVal = y - size/2; yVal < y + size; yVal ++)
					{
						if(xVal > -1 && xVal < pixels.length && yVal > 0 && yVal < pixels[0].length)
						{
							red += pixels[xVal][yVal].getRed();
							green += pixels[xVal][yVal].getGreen();
							blue += pixels[xVal][yVal].getBlue();
							counter++;
						}	
					}
				}
				
				red = red/counter;
				blue = blue/counter;
				green = green/counter;
				
				resultPixels[x][y].setRed(red);
				resultPixels[x][y].setBlue(blue);
				resultPixels[x][y].setGreen(green);
			}
		}
		
		return result;
	}
	
	public Picture blur(int size)
	{
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		
		
		for(int x = 0; x < resultPixels.length; x++)
		{
			for(int y = 0; y < resultPixels[x].length; y ++)
			{
				int red = 0;
				int blue = 0;
				int green = 0;
				int counter = 0;
				
				for(int xVal = x - size/2; xVal < x + size; xVal ++)
				{
					for(int yVal = y - size/2; yVal < y + size; yVal ++)
					{
						if(xVal > -1 && xVal < pixels.length && yVal > 0 && yVal < pixels[0].length)
						{
							red += pixels[xVal][yVal].getRed();
							green += pixels[xVal][yVal].getGreen();
							blue += pixels[xVal][yVal].getBlue();
							counter++;
						}	
					}
				}
				
				red = 2 * pixels[x][y].getRed() - (red/counter);
				blue = 2 * pixels[x][y].getBlue() - (blue/counter);
				green = 2 * pixels[x][y].getGreen() - (green/counter);
				
				resultPixels[x][y].setRed(red);
				resultPixels[x][y].setBlue(blue);
				resultPixels[x][y].setGreen(green);
			}
		}
		
		return result;
	}
  
	
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method to remove all RGB values except blue values, set red and green to 0 */
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
            pixelObj.setRed(0);
			pixelObj.setGreen(0);
      }
    }
  }
  
  /** Method to negate all pixels */
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(255- pixelObj.getRed());
        pixelObj.setBlue(255- pixelObj.getBlue());
        pixelObj.setGreen(255- pixelObj.getGreen());
      }
    }
  }
  
  /** Sets every pixel to a shade of grey */
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
		int avg = (pixelObj.getBlue() + pixelObj.getRed() + pixelObj.getGreen())/3;
        pixelObj.setRed(avg);
        pixelObj.setBlue(avg);
        pixelObj.setGreen(avg);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
	
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
