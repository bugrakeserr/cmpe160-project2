// Name : Buğra Keser
// Student ID : 2021400144
// Date : 16/04/2023
// has a player constructor, has methods that can move and animate the player

import java.awt.event.KeyEvent;
public class Player {
    private static double x;
    private final static double velocity = 32.0/400.0;
    private static final double PLAYER_HEIGHT_WIDTH_RATE = 37.0/27.0;
    private static final double PLAYER_HEIGHT_SCALE_Y_RATE = 1.0/8.0;
    private static final double playerHeight = Environment.getScaleY() * PLAYER_HEIGHT_SCALE_Y_RATE;
    private static final double playerWidth = playerHeight/PLAYER_HEIGHT_WIDTH_RATE;
    Player(double x){
        String filename = "player_back.png";
        Player.x = x;
        StdDraw.picture(x, playerHeight/2.0 ,filename, playerWidth, playerHeight);
    }

    // changes x coordinate of the player if space bar is pressed and if it is not one of ends of the canvas
    public static double changeX(double x0){
        if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) && (x0+velocity) < Environment.getScaleX()-playerWidth/2) {
            StdDraw.pause(15);
            x0 += velocity;
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT) && (x0-velocity) > playerWidth/2) {
            StdDraw.pause(15);
            x0 -= velocity;
        }
        return x0;
    }

    // animates player moving
    public static void move(double x0){
            if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) && (x0+velocity) < 16.0-37.0/27.0/2) {
                x0 += velocity;
            }
            else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT) && (x0-velocity) > 37.0/27.0/2) {
                x0 -= velocity;
            }
            new Player(x0);
            StdDraw.show();
    }

    // getter methods
    public static double getX(){
        return x;
    }
    public static double getPlayerHeight(){
        return playerHeight;
    }

    public static double getPlayerWidth() {
        return playerWidth;
    }
}
