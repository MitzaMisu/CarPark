package CarPark;
import java.util.Calendar;

public class Ticket {
    private Calendar date;
    public Ticket(Calendar date)
    {
        this.date = date;
    }
    public Calendar getDate()
    {
        return date;
    }
}
