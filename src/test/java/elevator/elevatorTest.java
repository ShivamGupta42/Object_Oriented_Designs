package elevator;

import designs.elevator.ElevatorManagementSystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class elevatorTest {

    //YAGNI -> You are not gonna need it

    @Test
    public void canary(){
        Assertions.assertTrue(true);
    }

    @Test
    public void whenEmsSetupAllElevatorsShouldBeInstantiated(){
        ElevatorManagementSystem ems = new ElevatorManagementSystem(6,100);
        Assertions.assertEquals(6,ems.getNoOfElevators());
        Assertions.assertEquals(100,ems.getFloors(100));
        Assertions.assertNotNull(ems.getElevator(1));
    }

    @Test
    public void MultipleElevators(){

    }


}
