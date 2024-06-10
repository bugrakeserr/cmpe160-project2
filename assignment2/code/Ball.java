// Name : Buğra Keser
// Student ID : 2021400144
// Date : 16/04/2023
// has a ball constructor, has methods that can move and animates balls
// has a method that splits the balls

import java.util.ArrayList;

public class Ball {
    private final int ballLevel;
    private double x;
    private double y;
    private double velocityX;
    private double velocityY;
    private static final double y2 = 32.0 * Math.sqrt(1.75) * Math.sqrt(1.75)/1000.0;
    private static final double y1 = 32.0 * Math.sqrt(1.75)/1000.0;
    private static final double y0 = 32.0/1000.0;
    private final double radius;
    private static final double GRAVITY = 0.000027;
    private final double RADIUS_MULTIPLIER = 2.0;
    private final double minPossibleRadius = 0.1575;

    Ball(int ballLevel, double x, double y, double velocityX, double velocityY){
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.ballLevel = ballLevel;
        radius = minPossibleRadius * Math.pow(RADIUS_MULTIPLIER, ballLevel);
        this.x = x;
        this.y = y;
        String filename = "ball.png";
        StdDraw.picture(x, y, filename, 2*radius, 2*radius);
    }


    //returns the current x coordinate of the ball
    public static double getCurrentX(double x0, double velocityX){
        return x0 + velocityX;
    }
    //changes ball's horizontal velocity in collisions with the wall
    public static double changeVelocityX(double x0, double velocityX, Ball ball){
        if(x0 + velocityX > 16.0 - ball.radius)
            velocityX = -velocityX;
        else if(x0 + velocityX < ball.radius){
            velocityX = -velocityX;
        }
        return velocityX;
    }
    // returns the y coordinate of the ball
    public static double getCurrentY(double y0, double velocityY, double GRAVITY){
        return y0 + velocityY - GRAVITY * 15.0 / 2.0;
    }
    // changes the vertical speed of the ball according to the gravity
    public static double acceleration(double velocityY, double GRAVITY){
        return velocityY - GRAVITY*15;
    }
    // changes the vertical speed of the ball in collision with the floor
    public static double changeVelocityY(double y0, double velocityY, double GRAVITY, Ball ball){
        if(y0 + velocityY - GRAVITY *15.0/2.0 < ball.radius){
            switch (ball.ballLevel) {
                case 0:
                    velocityY = Ball.y0;
                    break;
                case 1:
                    velocityY = Ball.y1;
                    break;
                case 2:
                    velocityY = Ball.y2;
                    break;
            }
        }
        return velocityY;
    }
    // animates ball moving
    public static void moveBall(Ball ball){
        ball.velocityX = changeVelocityX(ball.x ,ball.velocityX, ball);
        ball.x = getCurrentX(ball.x, ball.velocityX);
        ball.velocityY = acceleration(ball.velocityY, GRAVITY);
        ball.velocityY = changeVelocityY(ball.y, ball.velocityY, GRAVITY, ball);
        ball.y = getCurrentY(ball.y, ball.velocityY, GRAVITY);
        new Ball(ball.ballLevel, ball.x, ball.y, ball.velocityX, ball.velocityY);
    }

    // splits the ball  if it is big enough and creates new two balls that are one level lower
    public static void split(Ball ball, ArrayList<Ball> ballArrayList){
        if(ball.ballLevel > 0){
            Ball ball1 = new Ball(ball.ballLevel-1, ball.getX(), ball.getY(), 80.0/1000.0, ball.velocityY/Math.sqrt(1.75));
            Ball ball2 = new Ball(ball.ballLevel-1, ball.getX(), ball.getY(), -80.0/1000.0, ball.velocityY/Math.sqrt(1.75));
            ballArrayList.add(ball1);
            ballArrayList.add(ball2);
        }
    }

    // getter methods
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public double getRadius(){
        return radius;
    }

}
