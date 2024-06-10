// Name : Buğra Keser
// Student ID : 2021400144
// Date : 16/04/2023
// has an arrow constructor, methods that creates, moves and animates the arrow

import java.awt.event.KeyEvent;

public class Arrow {
    private static double x;
    private static final double velocity = 18.0/100.0;
    public static boolean isCreated = false;
    Arrow(double x, double y, double scaledX, double scaledY){
        String filename = "arrow.png";
        Arrow.x = x;
        StdDraw.picture(x, y, filename, scaledX, scaledY);
    }

    // creates an arrow if no arrow is created in the game
    public static void createArrow(){
        if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
            if(!isCreated) {
                StdDraw.pause(15);
                double x0 = Player.getX();
                new Arrow(x0, 0.0, 0.0, 0.0);
                isCreated = true;
            }
        }
    }

    // increments the y coordinate of the arrow if it does not reach the ceiling or hits a ball
    public static double changeY(double y0){
        if(y0 + velocity < 9.0)
            y0 += velocity;
        else {
            y0 = 0.0;
            isCreated = false;
        }
        return y0;
    }

    // animates arrow moving
    public static void moveArrow(double x0, double y0){
        changeY(y0);
        if(y0 != 0.0) {
            new Arrow(x0, y0/2, 0.2, y0);
        }
    }

    // getter method
    public static double getX(){
        return x;
    }
}
