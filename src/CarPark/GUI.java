package CarPark;

import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

public class GUI implements Runnable{
    private static GUI instance;
    private Thread t;

    private JTextArea historyText = new JTextArea();
    private JTextArea insideCarsText = new JTextArea();
    private JFrame frame = new JFrame("Car Parking");
    private JLayeredPane lpane = new JLayeredPane();
    private JLabel dateLabel = new JLabel();
    private JLabel emptySpacesLabel = new JLabel();

    private JLabel totalFeesLabel = new JLabel();
    private JTextArea feesColected = new JTextArea();

    private final int maxNrOfCars = 72;
    private JLabel[] carLabels = new JLabel[maxNrOfCars];
    private GUI(){};

    public void updateTotalFees(String s)
    {
        this.totalFeesLabel.setText(s);
    }
    public void updateDatePanel(String s)
    {
        dateLabel.setText(s);
    }

    public void updateEmptySpacesLabel(String s)
    {
        emptySpacesLabel.setText(s);
    }

    public void updateHistory(String s)
    {
        historyText.setText(historyText.getText() + "\n" + s);
    }
    public void updateFee(String s)
    {
        feesColected.setText(feesColected.getText() + "\n" + s);
    }

    public void updateInsideCars(ArrayList<Car> carList)
    {
        insideCarsText.setText("Cars inside the parking: \n");
        for(int i = 0; i < carList.size();i++)
        {
            insideCarsText.setText(insideCarsText.getText() + "\n" + carList.get(i).getLicence());
        }

    }


    public void start()
    {
        System.out.println("Starting GUI" );
        if (t == null) {
            t = new Thread (this, "GUI");
            t.start ();
        }
    }


    public static synchronized GUI getInstance()
    {
        if (instance == null)
            instance = new GUI();
        return instance;
    }

    public void updateCars(ArrayList<Car> carList)
    {
        int auxArray[] = new int[maxNrOfCars];
        for(int i = 0; i < maxNrOfCars;i++)
        {
            auxArray[i] = 0;
        }
        for(int i = 0; i < carList.size();i++)
        {
            auxArray[carList.get(i).getParkingSpace()] = 1;
        }
        for(int i = 0; i < maxNrOfCars;i++)
        {
            if(auxArray[i] == 1)
                carLabels[i].setVisible(true);
            else
                carLabels[i].setVisible(false);
        }
    }

    private void drawCars()
    {

        int imageHeight = frame.getHeight() / 5;
        int imageWidth = frame.getWidth() / 20;
        ImageIcon img2 = new ImageIcon(getClass().getResource("RedCar.png"));
        Image newImage2 = img2.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);
        img2 = new ImageIcon(newImage2);


        int x = 0;
        int y = frame.getHeight() / 50;
        for(int i = 0 ; i < maxNrOfCars; i++)
        {

            carLabels[i] = new JLabel(img2);
            carLabels[i].setBounds(x,y,imageWidth, imageHeight);
            if(i == 8 || i == 26 || i == 44 || i == 62)
                x+= frame.getWidth() / 20 * 2;

            x+= frame.getWidth() / 20;

            if(i == 17 || i == 53)
            {
                y+= imageHeight + frame.getHeight() / 50;
                x = 0;
            }

            if(i == 35)
            {
                y+= imageHeight + frame.getHeight() / 35 * 3;
                x = 0;
            }


            lpane.add(carLabels[i],0);
        }
        for(int i = 0; i < maxNrOfCars; i++)
            carLabels[i].setVisible(false);

        dateLabel.setOpaque(true);
        dateLabel.setBounds(frame.getWidth()/2 - frame.getWidth()/20,0,300,50);
        dateLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));

        emptySpacesLabel.setOpaque(true);
        emptySpacesLabel.setBounds(0,0,250,50);
        emptySpacesLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));

        lpane.add(dateLabel,0);
        lpane.add(emptySpacesLabel,0);

    }

    private void initialise()
    {


        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        //int width = 900;
        //int height = 600;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLayout(new BorderLayout());

        JTabbedPane jpt = new JTabbedPane();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();


        ImageIcon img = new ImageIcon(getClass().getResource("Parcare.jpg"));
        Image newImage = img.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_DEFAULT);
        img = new ImageIcon(newImage);
        JLabel labelImg = new JLabel(img);
        labelImg.setBounds(0,0,width,height);

        lpane.add(labelImg, 1);
        drawCars();
        //ImageIcon img2 = new ImageIcon(getClass().getResource("RedCar.png"));
        //Image newImage2 = img2.getImage().getScaledInstance(100, 200, Image.SCALE_DEFAULT);
        //img2 = new ImageIcon(newImage2);
        //JLabel labelImg2 = new JLabel(img2);
        //labelImg2.setBounds(0,0,100,200);
//
        //JLabel labelImg3 = new JLabel(img2);
        //labelImg3.setBounds(100,0,100,200);

        //parkingPanel.setBounds(0, 0, width, height);
        //parkingPanel.setOpaque(true);
        //carPanel.setBounds(200, 200, 100, 200);
        //carPanel.setOpaque(true);
        //lpane.setBounds(0, 0, width, height);
        lpane.setPreferredSize(new Dimension(width, height));


        //lpane.add(labelImg2, 0);
        //lpane.add(labelImg3, 0);
        panel1.add(lpane, BorderLayout.CENTER);
        //panel1.add(labelImg2, BorderLayout.CENTER);
        //panel1.add(labelImg, BorderLayout.CENTER);
        historyText.setRows(height/30);
        historyText.setColumns(width/25);
        historyText.setEditable(false);
        JScrollPane scroll = new JScrollPane(historyText);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        insideCarsText.setRows(height/30);
        insideCarsText.setColumns(width/40);
        insideCarsText.setEditable(false);
        JScrollPane scroll2 = new JScrollPane(insideCarsText);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        Box box = Box.createHorizontalBox();
        box.add(scroll);
        box.add(scroll2);
        panel2.add(box);

        feesColected.setRows(height/30);
        feesColected.setColumns(width/40);
        feesColected.setEditable(false);
        JScrollPane scroll3 = new JScrollPane(feesColected);
        scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        totalFeesLabel.setOpaque(true);
        totalFeesLabel.setBounds(0,0,250,50);
        totalFeesLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
        Box box2 = Box.createHorizontalBox();
        box2.add(scroll3);
        box2.add(totalFeesLabel);
        panel3.add(box2);



        jpt.addTab("Parking", panel1);
        jpt.addTab("History", panel2);
        jpt.addTab("Fees", panel3);


        frame.add(jpt);
        frame.setVisible(true);

    }

    public void run()
    {
        initialise();

    }
}
