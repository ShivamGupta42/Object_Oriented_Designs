package designs.trafficControlSystem2.signals;

import designs.trafficControlSystem2.state.TrafficState;

public interface TrafficSignal {
    void stopExecution();
    void setState(TrafficState state);
    int getId();
}
