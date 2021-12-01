package designs.elevator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ElevatorManagementSystem {

    private final int noOfElevators;
    private final int floors;
    Elevator[] elevators;
    private final ExecutorService executorService;



    public ElevatorManagementSystem(int elevators, int floors) {
        this.noOfElevators = elevators;
        this.floors = floors;
        this.elevators = new Elevator[noOfElevators];
        executorService = Executors.newFixedThreadPool(elevators);
        initElevators();

    }

    private void initElevators() {
        for (int i = 0; i < elevators.length; i++) {
            elevators[i] = new Elevator(i+1,floors);
            executorService.submit(elevators[i]);
        }
    }

    public int getNoOfElevators() {
        return noOfElevators;
    }

    public int getFloors(int i) {
        return floors;
    }

    public Elevator getElevator(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("index should be more than 0");
        }
        return elevators[i];
    }


    public void addPickup(int floor, Direction direction) {
        boolean isPickUpAdded = false;

        while (!isPickUpAdded) {

            Elevator closestElevator = null;
            int distance = Integer.MAX_VALUE;

            for (Elevator elevator : elevators) {
                if (elevator.getDirection() == direction && elevator.getCurFloor() <= floor) {
                    if (Math.abs(elevator.getCurFloor() - floor) <= distance) {
                        closestElevator = elevator;
                        distance = Math.abs(elevator.getCurFloor() - floor);
                    }
                }
            }

            if (closestElevator != null) {
                closestElevator.addStop(floor);
                isPickUpAdded = true;
            }
        }

    }


}
