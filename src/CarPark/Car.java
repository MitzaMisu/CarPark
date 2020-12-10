package CarPark;

public class Car {
    private String licencePlate;
    private Ticket entryTicket;
    private Ticket exitTicket;
    public Car(String licencePlate)
    {
        this.licencePlate = licencePlate;
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
