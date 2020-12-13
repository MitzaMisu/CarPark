package CarPark;
import java.util.*;

import static java.lang.Thread.*;

public class ParkingLot {
    private Queue<Car> entryQueue = new LinkedList<Car>();
    private ArrayList<Car> carList = new ArrayList<Car>();
    private final int maxNrOfCars = 50;
    private Calendar currentDate;

    public ParkingLot()
    {
        currentDate = Calendar.getInstance();
    }

    public Calendar getCurrentDate()
    {
        return currentDate;
    }

    public boolean enterParking(Car c)
    {
        if(carList.size() < maxNrOfCars)
        {
            c.setEntryTicket(new Ticket(currentDate));
            carList.add(c);
            System.out.println("Car " + c.getLicence() + " entered at time " + c.getEntryTicket().getDate().getTime());
            return true;
        }
        return false;
    }
    public void exitParking(Car c)
    {
        c.setExitTicket(new Ticket(currentDate));
        System.out.println("Car " + c.getLicence() + " exited the parking at time " + c.getExitTicket().getDate().getTime());
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


            if(random.nextBoolean())
            {
                entryQueue.add(new Car());
            }

            c = entryQueue.peek();
            if(c != null)
            {
                if(enterParking(c))
                    entryQueue.remove(c);
            }

            if(random.nextBoolean())
            {
                if(carList.size() > 0)
                {
                    int index = random.nextInt(carList.size());
                    Car nextCar = carList.get(index);
                    exitParking(nextCar);
                }

            }
        }

    }
}
