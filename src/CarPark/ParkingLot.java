package CarPark;
import java.util.*;

import static java.lang.Thread.*;

public class ParkingLot implements Runnable{
    private Queue<Car> entryQueue = new LinkedList<Car>();
    private ArrayList<Car> carList = new ArrayList<Car>();
    private FeeHandler feeHandler;
    private final int maxNrOfCars = 50;
    private Calendar currentDate;
    private Thread t;
    private GUI gui;
    public ParkingLot(GUI gui)
    {
        currentDate = Calendar.getInstance();
        feeHandler = new FeeHandler(currentDate);
        this.gui = gui;
    }

    public Calendar getCurrentDate()
    {
        return currentDate;
    }

    public void start()
    {
        System.out.println("Starting Simulation" );
        if (t == null) {
            t = new Thread (this, "Parking");
            t.start ();
        }
    }

    public boolean enterParking(Car c, Date d)
    {
        String history;
        if(carList.size() < maxNrOfCars)
        {
            c.setEntryTicket(new Ticket(d));
            carList.add(c);
            history = "Car " + c.getLicence() + " entered at time " + c.getEntryTicket().getTicketDate();
            System.out.println(history);
            gui.updateHistory(history);
            return true;
        }
        return false;
    }
    public void exitParking(Car c, Date d)
    {
        c.setExitTicket(new Ticket(d));
        System.out.println("Car " + c.getLicence() + " exited the parking at time " + c.getExitTicket().getTicketDate() + " and paid "+ feeHandler.calculateFee(c) +" initial entrance: " + c.getEntryTicket().getTicketDate());
        carList.remove(c);
    }


    private void initialize()
    {
        int initialCars = 30;
        for(int i = 0; i < initialCars; i++)
            entryQueue.add(new Car());
    }
    public void run()
    {
        int ct = 10;
        initialize();
        Random random = new Random();
        Car c;
        for(int i = 0; i < ct; i++)
        //while(true)
        {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            currentDate.add(Calendar.MINUTE,5);
            Date date = currentDate.getTime();
            if(random.nextBoolean())
            {
                entryQueue.add(new Car());
            }

            if(random.nextBoolean())
            {
                if(carList.size() > 0)
                {
                    int index = random.nextInt(carList.size());
                    Car nextCar = carList.get(index);
                    exitParking(nextCar, date);
                }

            }

            c = entryQueue.peek();
            if(c != null)
            {
                if(enterParking(c, date))
                    entryQueue.remove(c);
            }

        }
        System.out.println("Total fees collected : " + feeHandler.getTotalFeesColected());
    }
}
