package BrickBreaker;
import java.awt.event.*;
import java.awt.*;

public class BreakPad extends Rectangle
{
    int xVelocity; //Speed of our pad in the x direction
    int speed = 10; //speed of our pad

    BreakPad(int x, int y, int width, int height) //Constructor for our breakpad where we will later set the x and y coordinates along with the width and height
    {
        super(x, y, width, height);
    }
    public void keyPressed(KeyEvent e)//Method for when a key is pressed
    {
        if(e.getKeyCode() == KeyEvent.VK_A) //if A is pressed, set the x direction to the negative speed to move it to the left and call Move
        {
            setXDirection(-speed);
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_D) //if D is pressed, set the x direction to the positive speed to move it to the right and call Move
        {
            setXDirection(speed);
            move();
        }
    }
    public void keyReleased(KeyEvent e)//Method for when the key is released
    {
        if(e.getKeyCode() == KeyEvent.VK_A) //if A is released, set the x direction to 0 to stop the pad from moving and call Move with updated x direction
        {
            setXDirection(0);
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_D)//if D is released, set the x direction to 0 to stop the pad from moving and call Move with updated x direction
        {
            setXDirection(0);
            move();
        }
    }

    public void setXDirection(int xDirection)//Method to set the x direction
    {
        xVelocity = xDirection;
    }
    public void move() //Method to move the pad, add xVelocity to the x coordinate
    {
        x += xVelocity;
    }
    public void draw(Graphics g) //Set the color of the pad to white and draw it
    {
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
    }
}
