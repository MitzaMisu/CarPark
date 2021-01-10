package CarPark;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class FeeHandler {
    private Calendar currentDate;
    private long totalFeesColected;
    private final Integer bigFee = 3;
    private final Integer mediumFee = 2;
    private final Integer smallFee = 1;
    public FeeHandler(Calendar currentDate)
    {
        this.currentDate = currentDate;
        this.totalFeesColected = 0;
    }

    public long calculateFee(Car c)
    {
        Integer payFee = bigFee;
        //System.out.println("AAAAAAAAAAAAAAAAaaa " + c.getExitTicket().getTicketDate().getTime() + " BBBBBBBBBBBB " + c.getEntryTicket().getTicketDate().getTime());
        long diffInMillies = c.getExitTicket().getTicketDate().getTime() - c.getEntryTicket().getTicketDate().getTime();
        long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
        //System.out.println("AAAAAAAAAAAAAAAAaaa" + diffInMillies);
        if(diff < 20)
            payFee = bigFee;
        if(diff < 40)
            payFee = mediumFee;
        if(diff < 60)
            payFee = smallFee;
        long fee = payFee * diff;
        totalFeesColected += fee;
        return fee;
    }
    public long getTotalFeesColected()
    {
        return totalFeesColected;
    }

}
