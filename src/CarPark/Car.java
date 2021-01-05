package CarPark;

import java.util.Random;

public class Car {
    private String licencePlate;
    private Ticket entryTicket;
    private Ticket exitTicket;
    private int parkingSpace;

    private Random rand = new Random();

    private final String[] county= {
            "AB",
            "AG",
            "AR",
            "B",
            "BC",
            "BH",
            "BN",
            "BR",
            "BT",
            "BV",
            "BZ",
            "CJ",
            "CL",
            "CS",
            "CT",
            "CV",
            "DB",
            "DJ",
            "GJ",
            "GL",
            "GR",
            "HD",
            "HR",
            "IF",
            "IL",
            "IS",
            "MH",
            "MM",
            "MS",
            "NT",
            "OT",
            "PH",
            "SB",
            "SJ",
            "SM",
            "SV",
            "TL",
            "TM",
            "TR",
            "VL",
            "VN"
    };
    private final String letters= "ABCDEFGHIJKLMNOPQRSTUXYZ";


    public Car()
    {
        licencePlate = county[rand.nextInt(county.length)] + " " + Integer.toString(rand.nextInt(10)) + Integer.toString(rand.nextInt(10)) + " " + letters.charAt(rand.nextInt(letters.length())) + letters.charAt(rand.nextInt(letters.length())) + letters.charAt(rand.nextInt(letters.length()));
        entryTicket = null;
        exitTicket = null;
    }
    public String getLicence()
    {
        return licencePlate;
    }
    public Ticket getEntryTicket()
    {
        return entryTicket;
    }
    public Ticket getExitTicket()
    {
        return exitTicket;
    }
    public void setParkingSpace(int space)
    {
        this.parkingSpace = space;
    }
    public int getParkingSpace()
    {
        return parkingSpace;
    }

    public void setEntryTicket(Ticket ticket)
    {
        this.entryTicket = ticket;
    }
    public void setExitTicket(Ticket ticket)
    {
        this.exitTicket = ticket;
    }

}
