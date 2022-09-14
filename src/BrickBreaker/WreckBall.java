package BrickBreaker;
import java.awt.*;
import java.util.*;

public class WreckBall extends Rectangle
{
    int xVel; // x-axis velocity of our ball
    int yVel; // y-axis velocity of our ball
    Random random;
    int initialSpeed = 2; // initial speed of our ball

    WreckBall(int x, int y, int width, int height) //Set position and random direction for our ball
    {
        super(x, y, width, height);
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if(randomXDirection == 0)
            randomXDirection--;
        setXDirection(randomXDirection*initialSpeed);
        int randomYDirection = random.nextInt(2);
        if(randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection*initialSpeed);
    }
    public void setXDirection(int randomXDirection) //Assign the new direction to the x-axis velocity
    {
        xVel = randomXDirection;
    }
    public void setYDirection(int randomYDirection) //Assign the new direction to the y-axis velocity
    {
        yVel = randomYDirection;
    }
    public void move() //to move the ball, add the velocity to the x and y coordinates
    {
        x += xVel;
        y += yVel;
    }//this method is to be called in GamePanel class
    public void draw(Graphics g) //draw our ball
    {
        g.setColor(Color.white);
        g.fillOval(x, y, width, height);
    }//This method is to be called in GamePanel class
}
