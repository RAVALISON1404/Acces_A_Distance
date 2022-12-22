package servers;

import listeners.Listeners;
import threads.ReadScreen;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame
{
    public Server(int port) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            JPanel jPanel = new JPanel();
            jPanel.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
            jPanel.setFocusable(true);
            jPanel.setVisible(true);

            setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            
            add(jPanel);

            Thread thread = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        new Listeners(socket, jPanel, dataInputStream.readDouble(), dataInputStream.readDouble());
                        new ReadScreen(dataInputStream, jPanel);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
    }
}