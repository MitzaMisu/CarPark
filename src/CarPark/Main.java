package CarPark;

public class Main {

    public static void main(String[] args) {
        GUI localGUI = GUI.getInstance();
        localGUI.start();

        ParkingLot parkingLot = new ParkingLot(localGUI);
        parkingLot.addObserver(new Observer() {
            @Override
            public void update() {
                localGUI.updateCars(parkingLot.getCars());
            }
        });
        parkingLot.addObserver(new Observer() {
            @Override
            public void update() {
                localGUI.updateHistory(parkingLot.getHistory());
            }
        });
        parkingLot.addObserver(new Observer() {
            @Override
            public void update() {
                localGUI.updateInsideCars(parkingLot.getCars());
            }
        });
        parkingLot.addObserver(new Observer() {
            @Override
            public void update() {
                localGUI.updateDatePanel(parkingLot.getCurrentDate().getTime().toString());
            }
        });
        parkingLot.addObserver(new Observer() {
            @Override
            public void update() {
                localGUI.updateEmptySpacesLabel("  Available spaces: " + parkingLot.getEmptySpaces());
            }
        });
        parkingLot.addObserver(new Observer() {
            @Override
            public void update() {
                localGUI.updateTotalFees(parkingLot.getTotalFeeString());
            }
        });
        parkingLot.addObserver(new Observer() {
            @Override
            public void update() {
                localGUI.updateFee(parkingLot.getFeeString());
            }
        });
        parkingLot.start();


    }
}
