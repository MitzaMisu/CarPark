package CarPark;

import java.util.*;

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
            carList.add(c);
            System.out.println("Car " + c.getLicence() + " entered.");
            return true;
        }
        return false;
    }
    public void exitParking(int index)
    {
        System.out.println("Car " + carList.get(index).getLicence() + " exited the parking.");
         carList.remove(index);
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
                    exitParking(index);
                }

            }
        }
    }
}
