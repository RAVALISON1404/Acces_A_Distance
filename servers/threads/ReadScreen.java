package threads;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ReadScreen extends Thread
{
    DataInputStream dataInputStream;
    JPanel jPanel;
    public ReadScreen(DataInputStream dataInputStream, JPanel jPanel)
    {
        this.dataInputStream = dataInputStream;
        this.jPanel = jPanel;
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
                byte[] bytes = new byte[10];

                dataInputStream.readFully(bytes);

                int size = ByteBuffer.wrap(bytes).asIntBuffer().get();

                byte[] bytes1 = new byte[size];

                int count = 0;
                int readNow;
                while (count < size && (readNow = dataInputStream.read(bytes1, count, size - count)) > 0)
                {
                    count += readNow;
                }
                Image image = ImageIO.read(new ByteArrayInputStream(bytes1));

                Graphics2D graphics2D = (Graphics2D) jPanel.getGraphics();
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                graphics2D.drawImage(image,0,0, jPanel.getWidth(), jPanel.getHeight(), jPanel);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
