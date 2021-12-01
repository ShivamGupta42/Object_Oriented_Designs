package elevator;

import designs.elevator.Direction;
import designs.elevator.ElevatorManagementSystem;

public class ElTest {

    public static void main(String[] args) {

        ElevatorManagementSystem ems = new ElevatorManagementSystem(3, 5);
        ems.addPickup(1, Direction.UP);
        ems.addPickup(2, Direction.UP);
        ems.addPickup(3, Direction.UP);
        ems.addPickup(4, Direction.UP);
        ems.addPickup(2, Direction.DOWN);

    }
}
