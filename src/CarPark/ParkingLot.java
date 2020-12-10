package CarPark;

import java.util.ArrayList;
import java.util.Calendar;

public class ParkingLot {
    private ArrayList<Car> carList = new ArrayList<Car>();
    private int nrOfCars;
    private final int maxNrOfCars = 50;
    private Calendar currentDate;

    public ParkingLot()
    {
        this.nrOfCars = 0;
        currentDate = Calendar.getInstance();
    }

    public boolean isFull()
    {
        return nrOfCars == maxNrOfCars - 1;
    }
    public Calendar getCurrentDate()
    {
        return currentDate;
    }

    private int firstFreeSpace()
    {
        for(int i = 0; i < maxNrOfCars; i++)
        {
            if(carList.get(i) == null)
                return i;
        }
        return - 1;
    }

    public void EnterParking(Car c)
    {
        int index = firstFreeSpace();
        if(index != -1)
        {
            carList.add(index,c);
            nrOfCars++;
        }
    }

}
