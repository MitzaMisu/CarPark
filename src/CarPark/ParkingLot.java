package CarPark;
import java.util.*;

import static java.lang.Thread.*;

public class ParkingLot implements Runnable{
    public String history;
    private Queue<Car> entryQueue = new LinkedList<Car>();
    private ArrayList<Car> carList = new ArrayList<Car>();
    private FeeHandler feeHandler;
    private final int maxNrOfCars = 72;
    private Calendar currentDate;
    private Thread t;
    private GUI gui;
    private ArrayList<Integer> freeSpaces = new ArrayList<Integer>();
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public int getEmptySpaces()
    {
        return maxNrOfCars - carList.size();
    }
    public String getHistory()
    {
        return history;
    }
    public void addObserver(Observer obv)
    {
        observers.add(obv);
    }

    public void notifyObservers()
    {
        observers.forEach(observer -> observer.update());
    }

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
    public ArrayList<Car> getCars()
    {
        return carList;
    }

    public boolean enterParking(Car c, Date d)
    {
        int aux;
        Random random = new Random();
        if(carList.size() < maxNrOfCars)
        {
            c.setEntryTicket(new Ticket(d));
            carList.add(c);

            aux = random.nextInt(freeSpaces.size());
            c.setParkingSpace(freeSpaces.get(aux));
            freeSpaces.remove(aux);

            history = history + " \nCar " + c.getLicence() + " entered at time " + c.getEntryTicket().getTicketDate();
            System.out.println(history);
            return true;
        }
        return false;
    }
    public void exitParking(Car c, Date d)
    {
        c.setExitTicket(new Ticket(d));
        history =  history + "\nCar " + c.getLicence() + " exited the parking at time " + c.getExitTicket().getTicketDate() + " and paid "+ feeHandler.calculateFee(c) +" initial entrance: " + c.getEntryTicket().getTicketDate();
        System.out.println(history);
        freeSpaces.add(c.getParkingSpace());
        c.setParkingSpace(-1);

        carList.remove(c);
    }


    private void initialize()
    {
        for(int i = 0; i < maxNrOfCars; i++)
            freeSpaces.add(Integer.valueOf(i));
        int initialCars = 10;
        for(int i = 0; i < initialCars; i++)
            entryQueue.add(new Car());
    }
    public void run()
    {
        try {
            t.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int ct = 100;
        initialize();
        Random random = new Random();
        Car c;
        //for(int i = 0; i < ct; i++)
        while(true)
        {
            history = "";
            try {
                t.sleep(1000);
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
            notifyObservers();
        }
        //System.out.println("Total fees collected : " + feeHandler.getTotalFeesColected());
    }
}
