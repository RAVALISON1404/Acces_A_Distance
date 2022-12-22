package listeners;

import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Listeners implements MouseListener, KeyListener, MouseMotionListener
{
    PrintWriter printWriter;
    BufferedReader bufferedReader;
    JPanel jPanel;
    double width = 0, height = 0;

    public Listeners(Socket socket, JPanel jPanel, double width, double height) throws IOException
    {
        this.jPanel = jPanel;
        this.width = width;
        this.height = height;
        printWriter = new PrintWriter(socket.getOutputStream());
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        jPanel.addMouseMotionListener(this);
        jPanel.addMouseListener(this);
        jPanel.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        printWriter.println("keyPressed");
        printWriter.println(e.getKeyCode());
        printWriter.flush();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        printWriter.println("keyReleased");
        printWriter.println(e.getKeyCode());
        printWriter.flush();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        printWriter.println("mousePressed");
        int button = e.getButton();
        int xButton = 16;
        if (button == 3)
        {
            xButton = 4;
        }
        printWriter.println(xButton);
        printWriter.flush();
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        printWriter.println("mouseReleased");
        int button = e.getButton();
        int xButton = 16;
        if (button == 3)
        {
            xButton = 4;
        }
        printWriter.println(xButton);
        printWriter.flush();
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {


    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        printWriter.println("mouseMoved");
        printWriter.println(e.getX() * width / jPanel.getWidth());
        printWriter.println(e.getY() * height / jPanel.getHeight());
        printWriter.flush();
    }
}