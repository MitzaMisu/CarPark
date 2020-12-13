package CarPark;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class FeeHandler {
    private Calendar currentDate;
    private long totalFeesColected;
    private final Integer dayFee = 10;
    private final Integer nightFee = 2;
    public FeeHandler(Calendar currentDate)
    {
        this.currentDate = currentDate;
        this.totalFeesColected = 0;
    }

    public long calculateFee(Car c)
    {
        //System.out.println("AAAAAAAAAAAAAAAAaaa " + c.getExitTicket().getTicketDate().getTime() + " BBBBBBBBBBBB " + c.getEntryTicket().getTicketDate().getTime());
        long diffInMillies = c.getExitTicket().getTicketDate().getTime() - c.getEntryTicket().getTicketDate().getTime();
        long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
        //System.out.println("AAAAAAAAAAAAAAAAaaa" + diffInMillies);
        long fee = dayFee * diff;
        totalFeesColected += fee;
        return fee;
    }
    public long getTotalFeesColected()
    {
        return totalFeesColected;
    }

}
