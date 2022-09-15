package BrickBreaker;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable
{
    public static int WIDTH = 700;
    public static int HEIGHT = (int)(WIDTH * 0.5555);
    static final Dimension SCREEN_SIZE = new Dimension(WIDTH, HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PAD_WIDTH = 100;
    static final int PAD_HEIGHT = 25;
    int missCrement = 0;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    BreakPad pad;
    WreckBall ball;
    Bricks bricks;
    Score score;
    Stars stars;

    public GamePanel()
    {
        newPad();
        newBall();
        newBricks();
        score = new Score(WIDTH, HEIGHT);
        stars = new Stars(100, 150);
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        this.addKeyListener(new AL());
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void newBall() //Create a new ball at specified position
    {
        ball = new WreckBall((WIDTH/2)-(BALL_DIAMETER/2), (((HEIGHT/2) + (HEIGHT/4)) - (BALL_DIAMETER/2)) , BALL_DIAMETER, BALL_DIAMETER);
    }
    public void newPad() //create a new pad at specified position
    {
        pad = new BreakPad(WIDTH/2 - PAD_WIDTH/2, HEIGHT - PAD_HEIGHT, PAD_WIDTH, PAD_HEIGHT);
    }
    public void newBricks()//create bricks with the specified number of rows and columns
    {
        bricks = new Bricks(5 , 13);
    }

    public void paint(Graphics g)
    {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }
    public void draw(Graphics g) //draw all our game components by calling their draw methods
    {
        stars.draw(g);
        pad.draw(g);
        bricks.draw(g);
        score.draw(g);
        ball.draw(g);
    }
    public void move() //Caller function to pad and ball move methods
    {
        pad.move();
        ball.move();
    }
    public void checkCollide()
    {
        //Check if ball collides with pad
        if(ball.intersects(pad))
        {
            ball.setYDirection(-ball.yVel);
        }
        //Check if ball collides with bricks
        for(int i = 0 ; i < bricks.bricks.length; i++)
        {
            for(int j = 0; j < bricks.bricks[0].length; j++)
            {
                if(bricks.bricks[i][j] > 0)
                {
                    Rectangle brickRect = new Rectangle(j * bricks.width, i * bricks.height, bricks.width, bricks.height);
                    if(ball.intersects(brickRect))
                    {
                        if(bricks.bricks[i][j] == 1)
                        {
                            bricks.hit(2, i, j);
                        }
                        else if(bricks.bricks[i][j] == 2)
                        {
                            score.player++;
                            bricks.hit(0, i, j);
                        }
                        ball.setYDirection(-ball.yVel);
                    }
                }
            }
        }
        //Check if ball collides with top and bottom
        if(ball.getY() <= 0)//Ball hits top
        {
            ball.setYDirection(-ball.yVel);
        }
        if(ball.getY() >= HEIGHT - BALL_DIAMETER)//Ball hits bottom
        {
            newPad(); //regenerate pad position
            newBall(); //regenerate ball position
            missCrement++; //increase our miss increment to keep track of how many times we missed the ball
            score.miss--; //decrease our lives counter
            if(missCrement == 4) //once we miss 4 times the map restarts along with the score
            {
                newBricks(); //new bricks
                missCrement = 0; //restart miss increment
                score.player = 0; //restart player score
                score.miss = 3; //restart lives
            }

        }
        //Check if ball collides with left and right
        if(ball.getX() <= 0) //Ball hits left
        {
            ball.setXDirection(-ball.xVel);
        }
        //Ball hits right
        if(ball.getX() >= WIDTH - BALL_DIAMETER)
        {
            ball.setXDirection(-ball.xVel);
        }
        //Check if pad is at edge of screen and stop it from moving
        if(pad.x <= 0)
        {
            pad.x = 0;
        }
        if(pad.x >= WIDTH - PAD_WIDTH)
        {
            pad.x = WIDTH - PAD_WIDTH;
        }
    }
    public void run()//Framerate manager, for 60 FPS
    {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1)
            {
                move();
                checkCollide();
                repaint();
                delta--;
            }
        }
    }
    public class AL extends KeyAdapter //Key listener to send key pressed and released events for pad controls
    {
        public void keyPressed(KeyEvent e)
        {
            pad.keyPressed(e);
        }
        public void keyReleased(KeyEvent e)
        {
            pad.keyReleased(e);
        }
    }
}