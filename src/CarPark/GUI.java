package CarPark;

import javax.swing.*;
import java.awt.*;
import java.awt.Image;
public class GUI {
    private static GUI instance;
    private GUI(){};
    public static synchronized GUI getInstance()
    {
        if (instance == null)
            instance = new GUI();
        return instance;
    }

    public void initialize()
    {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        JFrame frame = new JFrame("Car Parking");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        JTabbedPane jpt = new JTabbedPane();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        ImageIcon img = new ImageIcon(getClass().getResource("Parcare.jpg"));
        Image newImage = img.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT);
        img = new ImageIcon(newImage);
        JLabel labelImg = new JLabel(img);
        panel1.add(labelImg);

        JLabel label1 = new JLabel();
        label1.setText("Test");
        panel2.add(label1);

        jpt.addTab("Parking", panel1);
        jpt.addTab("History", panel2);
        jpt.addTab("Fees", panel3);


        frame.add(jpt);
        frame.setVisible(true);
    }
}
