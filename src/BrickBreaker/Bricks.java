package BrickBreaker;
import java.awt.*;
public class Bricks //extends Rectangle
{
    public int bricks[][];
    public int width;
    public int height;
    public Bricks(int row, int col)
    {
        bricks = new int[row][col];
        for(int i = 0; i < bricks.length; i++)
        {
            for(int j = 0; j < bricks[0].length; j++)
            {
                bricks[i][j] = 1;
            }
        }
        width = 702/col;
        height = 150/row;
    }
    public void draw(Graphics g)
    {
        for(int i = 0; i < bricks.length; i++)
        {
            for(int j = 0; j < bricks[0].length; j++)
            {
                if(bricks[i][j] == 1)
                {
                    g.setColor(Color.white);
                    g.fillRect(j * width, i * height, width, height);
                    g.setColor(Color.black);
                    g.drawRect(j * width, i * height, width, height);
                }
                else if(bricks[i][j] == 2)
                {
                    g.setColor(Color.red);
                    g.fillRect(j * width, i * height, width, height);
                    g.setColor(Color.black);
                    g.drawRect(j * width, i * height, width, height);
                }
            }
        }
    }
    public void hit(int val, int i, int j)
    {
        bricks[i][j] = val;

    }
}

