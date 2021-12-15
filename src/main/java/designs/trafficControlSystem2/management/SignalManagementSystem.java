package designs.trafficControlSystem2.management;

import designs.trafficControlSystem2.signals.TrafficSignal;
import designs.trafficControlSystem2.state.GreenState;
import designs.trafficControlSystem2.state.RedState;
import designs.trafficControlSystem2.state.TrafficState;
import designs.trafficControlSystem2.state.YellowState;

public class SignalManagementSystem {

    private TrafficState stateMachine;
    private final TrafficSignal[] signals;

    public SignalManagementSystem(int totalJunctions) {
        this.signals = new TrafficSignal[totalJunctions];
    }


    private void defineStateMachine(){
        GreenState greenState = new GreenState();
        YellowState yellowState = new YellowState();
        RedState redState = new RedState();
        stateMachine = greenState.linkNext(yellowState).linkNext(redState);
    }


    private void initializeSignals(int totalJunctions){

        for(int i=0;i<totalJunctions;i++){
            //signals[i] = new TrafficSignal();
        }
    }

}
