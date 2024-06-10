// Name : Buğra Keser
// Student ID : 2021400144
// Date : 16/04/2023
// has a time bar constructor, has the method which draws the time bar over time

public class Bar {
    public static boolean isTime = true;
    private static final int colorScale = 225;
    public Bar(double x0){
        String filename = "bar.png";
        StdDraw.picture(Environment.getScaleX()/2.0, -0.5, filename,Environment.getScaleX(), 1.0);
        StdDraw.setPenColor(colorScale, (int)(x0/Environment.getScaleX() * colorScale), 0);
        StdDraw.filledRectangle(x0/2.0, -0.5, x0/2.0, 0.25);
    }

    // draws the time bar so that it becomes linearly redder and shorter over time
    public static void drawBar(double startTime){
        double time = System.currentTimeMillis()- startTime;
        double x0 = Environment.getScaleX() - time * Environment.getScaleX() / Environment.getGameDuration();
        if(x0 > 0) {
            new Bar(x0);
        }
        else
            isTime = false;
    }
}
