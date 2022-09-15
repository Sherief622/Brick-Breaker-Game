package BrickBreaker;
import java.awt.*;
import java.util.*;

public class Stars
{
    //Add stars to the background of the game

    public int stars[][];
    public int diameter = 5;
    Random random = new Random();

    public Stars(int row, int col)
    {
        stars = new int[row][col];
        for(int i = 0; i < stars.length; i++)
        {
            for(int j = 0; j < stars[0].length; j++)
            {
                stars[i][j] = random.nextInt(100);
            }
        }
    }
    public void draw(Graphics g)
    {
        for(int i = 0; i < stars.length; i++)
        {
            for(int j = 0; j < stars[0].length; j++)
            {
                if(stars[i][j] % 4 == 0)
                {
                    g.setColor(Color.yellow);
                    g.fillOval(j * diameter, i * diameter, diameter, diameter);
                }
            }
        }
    }

}
