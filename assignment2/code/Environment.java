// Name : Buğra Keser
// Student ID : 2021400144
// Date : 16/04/2023
// has an environment constructor, has methods that checks collision situations
// has a method that creates the game screen
// has the method which creates the environment and animates all objects

import java.awt.*;
import java.util.ArrayList;

public class Environment {
    private static final double GAME_DURATION = 40000.0;
    private static final double scaleX = 16.0;
    private static final double scaleY = 9.0;
    private static final int canvasWidth = 800;
    private static final int canvasHeight = 500;
    private static final int PAUSE_DURATION = 15;
    String filename = "background.png";
    Environment(){
        StdDraw.picture(scaleX/2.0, (scaleY+1.0)/2.0, filename, scaleX, (scaleY+1.0));
    }

    // creates the environment with player, 3 balls in an array list and time bar initially
    // can also create arrow according to the commands
    // manipulates balls in array list and checks if the game is won via that array list
    // game screen is created in reference to the game result
    // all animations are come true in this method
        public static void createEnvironment(){
        Arrow.isCreated = false;
        StdDraw.setXscale(0.0, scaleX);
        StdDraw.setYscale(-1.0, scaleY);
        StdDraw.enableDoubleBuffering();
        double startTime = System.currentTimeMillis();
        double x1 = scaleX/2.0;
        double y0 = 0.0;
        Ball ball2 = new Ball(2, scaleX/4.0, 1.5, 80.0/1000.0,  32.0 * Math.sqrt(1.75) * Math.sqrt(1.75)/1000.0);
        Ball ball1 = new Ball(1, scaleX/3.0, 1.5, -80.0/1000.0, 32.0 * Math.sqrt(1.75)/1000.0);
        Ball ball0 = new Ball(0, scaleX/4.0, 1.5, 80.0/1000.0,  32.0/1000.0);
        // creates a new array list which contains balls
        ArrayList<Ball> ballArrayList = new ArrayList<>();
        // adds balls in the array list for the initial condition
        ballArrayList.add(ball0);
        ballArrayList.add(ball1);
        ballArrayList.add(ball2);
        while (Bar.isTime) {
            new Environment();
            boolean ballPlayerCollision = false;

            // draws the time bar
            Bar.drawBar(startTime);
            for(Ball ball : ballArrayList) {
                // moves balls
                Ball.moveBall(ball);
            }
            x1 = Player.changeX(x1);

            // creates arrow if space bar button is pressed and if there is no active arrow
            Arrow.createArrow();
            if(Arrow.isCreated){
                double arrowX = Arrow.getX();
                y0 = Arrow.changeY(y0);
                Arrow.moveArrow(arrowX,y0);
                for(Ball ball : ballArrayList) {
                    if (isHit(arrowX, y0, ball)) {
                        ballArrayList.remove(ball);
                        Ball.split(ball, ballArrayList);
                        y0 = 0.0;
                        Arrow.isCreated = false;
                        break;
                    }
                }
            }
            // moves the player
            Player.move(x1);
            for(Ball ball : ballArrayList){
                if(isCollided(ball, x1)){
                    // checks if a ball and the arrow is collided
                    ballPlayerCollision = true;
                    break;
                }
            }
            if(ballPlayerCollision) break;
            if (isWin(ballArrayList)) {
                // checks if the user has won
                break;
            }
            StdDraw.show();
            StdDraw.pause(PAUSE_DURATION);
        }
        new Environment();
        Bar.drawBar(startTime);
        x1 = Player.changeX(x1);
        Player.move(x1);
        // creates game screen
        gameScreen(isWin(ballArrayList));
    }

    // checks if arrow hits the ball
    public static boolean isHit(double arrowX, double arrowY, Ball ball){
        double radius = ball.getRadius();
        double ballY = ball.getY();
        double ballX = ball.getX();
        if((Math.abs(arrowX-ballX) < radius) && (arrowY > ball.getY()))
            return true;
        else if(Math.pow(arrowX-ballX,2) + Math.pow(arrowY-ballY,2) <= radius)
            return true;
        else
            return false;
    }

    // checks if the player has won
    public static boolean isWin(ArrayList<Ball> ballArrayList){
        if(ballArrayList.size() == 0)
            return true;
        else
            return false;
    }
    // if player has won game screen appears with "You Won!" text
    // if player could not win the game, game screen appears with "Game Over!" text
    // game screen also asks players if they want to play again or quit the game
    public static void gameScreen(boolean isWin){
        String filename = "game_screen.png";
        StdDraw.picture(scaleX/2.0, scaleY/2.18, filename, scaleX/3.8, scaleY/4.0);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(new Font("Helvetica", Font.BOLD, 30));
        if(isWin)
            StdDraw.text(scaleX/2.0, scaleY/2.0, "You Won!");
        else
            StdDraw.text(scaleX/2.0, scaleY/2.0, "Game Over!");
        StdDraw.setFont(new Font("Helvetica", Font.ITALIC, 15));
        StdDraw.text(scaleX/2.0, scaleY/2.3, "To Replay Click \"Y\"");
        StdDraw.text(scaleX/2.0, scaleY/2.6, "To Quit Click \"N\"");
        StdDraw.show();
    }

    // checks if ball is collided with the player
    private static boolean isCollided(Ball ball, double x1){
        double ballX = ball.getX();
        double ballY = ball.getY();
        double radius = ball.getRadius();
        double height = Player.getPlayerHeight();
        double width = Player.getPlayerWidth();
        if(ballY-radius <= height && Math.abs((ballX - x1)) <= width/2 + radius)
            return true;
        else if(Math.pow(ballX-(x1-width/2.0), 2) + Math.pow((ballY-height), 2) <= radius*radius)
            return true;
        else if(Math.pow(ballX-(x1+width/2.0), 2) + Math.pow((ballY-height), 2) <= radius*radius)
            return true;
        else return false;
    }

    // getter methods

    public static double getGameDuration() {
        return GAME_DURATION;
    }

    public static int getCanvasHeight() {
        return canvasHeight;
    }

    public static int getCanvasWidth() {
        return canvasWidth;
    }

    public static double getScaleY(){
        return scaleY;
    }

    public static double getScaleX() {
        return scaleX;
    }

}
