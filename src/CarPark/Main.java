package CarPark;

public class Main {

    public static void main(String[] args) {
        GUI localGUI = GUI.getInstance();
        localGUI.start();

        ParkingLot parkingLot = new ParkingLot(localGUI);
        parkingLot.start();


    }
}
