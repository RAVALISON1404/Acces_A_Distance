package clients;

import java.awt.*;
import java.io.*;
import java.net.Socket;

import threads.ReceiveEvents;
import threads.SendScreen;

public class Client
{
    public Client(String host, int port) throws IOException
    {
        Socket socket = new Socket(host, port);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    dataOutputStream.writeDouble(Toolkit.getDefaultToolkit().getScreenSize().width);
                    dataOutputStream.writeDouble(Toolkit.getDefaultToolkit().getScreenSize().height);

                    new SendScreen(dataOutputStream);
                    new ReceiveEvents(bufferedReader);
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