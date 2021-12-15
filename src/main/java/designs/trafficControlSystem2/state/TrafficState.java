package designs.trafficControlSystem2.state;

import designs.trafficControlSystem2.signals.TrafficSignal;

public interface TrafficState {

    void handle(TrafficSignal signal);

    TrafficState linkNext(TrafficState state);
}
