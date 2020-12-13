package CarPark;
import java.util.Calendar;
import java.util.Date;

public class Ticket {
    private Date ticketDate;
    public Ticket(Date date)
    {
        this.ticketDate = date;
    }
    public Date getTicketDate()
    {
        return ticketDate;
    }
}
