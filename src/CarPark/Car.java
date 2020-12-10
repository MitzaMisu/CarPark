package CarPark;

import java.util.Random;

public class Car {
    private String licencePlate;
    private Ticket entryTicket;
    private Ticket exitTicket;

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
        licencePlate = county[rand.nextInt(county.length)] + " " + Integer.toString(rand.nextInt(100)) + " " + letters.charAt(rand.nextInt(county.length)) + letters.charAt(rand.nextInt(county.length)) + letters.charAt(rand.nextInt(county.length));
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

    public void receiveEntryTicket(Ticket ticket)
    {
        this.entryTicket = ticket;
    }
    public void receiveExitTicket(Ticket ticket)
    {
        this.exitTicket = ticket;
    }

}
