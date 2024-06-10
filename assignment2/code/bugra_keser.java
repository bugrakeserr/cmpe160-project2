// Name : Buğra Keser
// Student ID : 2021400144
// Date : 16/04/2023
// creates the canvas, initialize the environment and runs the game
// at the end of the game, takes user input and replays or quits the game and terminates the code according to the input

import java.awt.event.KeyEvent;
public class bugra_keser {
    public static void main(String[] args) {
        // creates the canvas
        StdDraw.setCanvasSize(Environment.getCanvasWidth(),Environment.getCanvasHeight());
        // initializes the environment and runs the game
        Environment.createEnvironment();
        while (true) {
            //checks if the player wants to play again or quit the game
            if (StdDraw.isKeyPressed(KeyEvent.VK_Y)){
                StdDraw.pause(100);
                // replays the game
                Environment.createEnvironment();
            }
            else if (StdDraw.isKeyPressed(KeyEvent.VK_N)) {
                StdDraw.pause(100);
                // closes the canvas and terminates the code
                System.exit(0);
                break;
            }
        }
    }
}