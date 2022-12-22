package threads;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;

public class ReceiveEvents extends Thread
{
    BufferedReader bufferedReader;
    public ReceiveEvents(BufferedReader bufferedReader)
    {
        this.bufferedReader = bufferedReader;
        start();
    }
    @Override
    public void run()
    {
        super.run();
        while (true)
        {
            try
            {
                Robot robot = new Robot();
                switch (bufferedReader.readLine())
                {
                    case "keyPressed":
                        robot.keyPress(Integer.parseInt(bufferedReader.readLine()));
                        break;

                    case "keyReleased":
                        robot.keyRelease(Integer.parseInt(bufferedReader.readLine()));
                        break;

                    case "mousePressed":
                        robot.mousePress(Integer.parseInt(bufferedReader.readLine()));
                        break;

                    case "mouseReleased":
                        robot.mouseRelease(Integer.parseInt(bufferedReader.readLine()));
                        break;

                    case "mouseMoved":
                        double index = Double.parseDouble(bufferedReader.readLine());
                        robot.mouseMove((int) index,(int) Double.parseDouble(bufferedReader.readLine()));
                        break;
                }
            }
            catch (AWTException | IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

