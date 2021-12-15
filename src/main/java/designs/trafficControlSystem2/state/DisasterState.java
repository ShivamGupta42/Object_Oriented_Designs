package designs.trafficControlSystem2.state;

import designs.trafficControlSystem2.signals.TrafficSignal;

public class DisasterState implements TrafficState {

    private TrafficState nextState;
    private StateColor color;

    @Override
    public void handle(TrafficSignal signal) {

        alarmAuthorities(signal);

        if (nextState != null) {
            nextState.handle(signal);
        }
    }

    private void alarmAuthorities(TrafficSignal signal) {
        System.out.println(signal.getId() + " is malfunctioning");
    }

    @Override
    public TrafficState linkNext(TrafficState state) {
        nextState = state;
        return this;
    }
}
