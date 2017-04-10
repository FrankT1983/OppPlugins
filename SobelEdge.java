package de.c3e.Blocks;

import de.c3e.BlockTemplates.ImageDimension;
import de.c3e.BlockTemplates.Templates.Helpers.TemplateHelper;
import de.c3e.BlockTemplates.Templates.PlaneCalculator;

/**
 * Edge detection with a Sobel operator
 * see: https://de.wikipedia.org/wiki/Sobel-Operator
 */
@SuppressWarnings("unused") // class will be found through reflection => can suppress this warning
public class SobelEdge extends PlaneCalculator<Byte>
{
    public SobelEdge()
    {
        // setup which plane is required
        super(ImageDimension.X,ImageDimension.Y);
    }

    @Override
    public Byte[][] Calculate(Byte[][] data)
    {
        Byte[][] result = TemplateHelper.CreateNewWithSameSize(data);


        for (int y=0; y< data[0].length; y++)
        {
            for (int x=0; x< data.length; x++)
            {
                if (x==0 || y==0 || x==data.length-1 || y == data[0].length-1)
                {
                    result[y][x] = 0;
                }
                else
                {
                    int gx = 1*data[y-1][x-1] + 0* data[y-1][x-1] + -1*data[y-1][x-1] +
                             2*data[y+0][x-1] + 0* data[y+0][x-1] + -2*data[y+0][x-1] +
                             1*data[y+1][x-1] + 0* data[y+1][x-1] + -1*data[y+1][x-1] ;

                    int gy = 1*data[y-1][x-1] +  2* data[y-1][x-1] +  1*data[y-1][x-1] +
                             0*data[y+0][x-1] +  0* data[y+0][x-1] +  0*data[y+0][x-1] +
                            -1*data[y+1][x-1] + -2* data[y+1][x-1] + -1*data[y+1][x-1] ;

                    result[y][x] = (byte)Math.sqrt( gx*gx +gy*gy);
                }
            }
        }
        return result;
    }
}
