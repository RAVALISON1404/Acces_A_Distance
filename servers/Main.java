import java.io.IOException;

import servers.Server;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            Server server = new Server(1);
            server.setVisible(true);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}