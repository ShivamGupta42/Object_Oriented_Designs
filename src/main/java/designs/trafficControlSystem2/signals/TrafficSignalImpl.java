package designs.trafficControlSystem2.signals;

import designs.trafficControlSystem2.management.SignalManagementSystem;
import designs.trafficControlSystem2.state.TrafficState;

public class TrafficSignalImpl implements TrafficSignal{

    private volatile boolean continueExecution =true;
    private TrafficState state;
    private final int id;
    private final SignalManagementSystem managementSystem;

    public TrafficSignalImpl(int id, SignalManagementSystem managementSystem) {
        this.id=id;
        this.managementSystem = managementSystem;
    }

    @Override
    public void stopExecution() {
        continueExecution=false;
    }

    @Override
    public void setState(TrafficState state) {
        this.state=state;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public void run() {
        while(continueExecution){
            state.handle(this);
        }
    }
}
