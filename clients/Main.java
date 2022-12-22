import clients.Client;

import javax.swing.*;
import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            Client client = new Client(JOptionPane.showInputDialog("IP Host"),1);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}