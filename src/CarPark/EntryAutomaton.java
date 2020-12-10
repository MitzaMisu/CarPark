package CarPark;

public class EntryAutomaton {
    private ParkingLot parkingLot;
    public EntryAutomaton(ParkingLot parkingLot)
    {
        this.parkingLot = parkingLot;
    }
    public boolean giveTicket(Car c)
    {
        if(parkingLot.isFull())
            return false;
        else
        {
            c.receiveEntryTicket(new Ticket(parkingLot.getCurrentDate()));
            parkingLot.EnterParking(c);
            return true;
        }

    }
}
