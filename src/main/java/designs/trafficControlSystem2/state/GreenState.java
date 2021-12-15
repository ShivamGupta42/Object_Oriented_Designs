package designs.trafficControlSystem2.state;

import designs.trafficControlSystem2.signals.TrafficSignal;

public class GreenState implements TrafficState{

    private TrafficState nextState;
    private StateColor color;

    @Override
    public void handle(TrafficSignal signal) {

        try {

            System.out.println("Current State of "+signal.getId() +" "+color.name());
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(nextState!=null){
            nextState.handle(signal);
        }
    }

    @Override
    public TrafficState linkNext(TrafficState state) {
        nextState=state;
        return this;
    }
}
