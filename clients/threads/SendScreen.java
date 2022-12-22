package threads;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class SendScreen extends Thread
{
    DataOutputStream dataOutputStream;
    public SendScreen(DataOutputStream dataOutputStream) throws IOException
    {
        this.dataOutputStream = dataOutputStream;
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
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                ImageIO.write(new Robot(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()).createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())),"jpg",byteArrayOutputStream);

                byte[] bytes = ByteBuffer.allocate(10).putInt(byteArrayOutputStream.size()).array();

                dataOutputStream.write(bytes);
                dataOutputStream.write(byteArrayOutputStream.toByteArray());
                dataOutputStream.flush();
            }
            catch (IOException | AWTException e)
            {
                e.printStackTrace();
            }
        }
    }
}
