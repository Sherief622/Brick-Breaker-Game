package BrickBreaker;
import java.awt.*;
public class Score
{
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player;
    int miss = 3;

    Score(int GAME_WIDTH, int GAME_HEIGHT)
    {
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }
    public void draw(Graphics g)
    {
        g.setColor(Color.green);
        g.setFont(new Font("Consoles", Font.PLAIN, 30));
        g.drawString(("Score:")+String.valueOf(player/10)+String.valueOf(player%10), (GAME_WIDTH/2) - (GAME_WIDTH/3) - 110 , (GAME_HEIGHT/2));
        g.drawString(("Lives:")+String.valueOf(miss),(GAME_WIDTH/2) - (GAME_WIDTH/3) - 110, (GAME_HEIGHT/2)+30); //Life counter display
    }
}

