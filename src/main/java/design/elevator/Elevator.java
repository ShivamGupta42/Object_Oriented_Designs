package design.elevator;

import java.util.Deque;
import java.util.LinkedList;

public class Elevator implements Runnable{

    public final int floors;
    private boolean isStopped = false;
    private Direction direction;
    private final int timeToMoveUpOrDown=1000;
    private int curFloor=0;
    private final int id;

    Elevator(int id, int floors) {
        this.id=id;
        this.floors = floors;
        direction=Direction.UP;
    }

    Deque<Integer> stopQ = new LinkedList<>();

    private void move() {

        while (!isStopped) {
            if (direction == Direction.UP) {

                for (int i = 0; i < floors; i++) {

                    curFloor=i;
                    while (!stopQ.isEmpty() && stopQ.peek() < i) {
                        stopQ.poll();
                    }

                    if (!stopQ.isEmpty() && stopQ.peek() == i) {
                        elevatorPause();
                        stopQ.poll();
                    }

                    timeToMoveUpOrDownAFloor();

                }
                direction = Direction.DOWN;
                stopQ = new LinkedList<>();
            }

            if (direction == Direction.DOWN) {

                for (int i = floors - 1; i >= 0; i--) {
                    curFloor=i;
                    while (!stopQ.isEmpty() && stopQ.peek() > i) {
                        stopQ.poll();
                    }

                    if (!stopQ.isEmpty() && stopQ.peek() == i) {
                        elevatorPause();
                        stopQ.poll();

                    }

                    timeToMoveUpOrDownAFloor();
                }

                direction = Direction.UP;
                stopQ = new LinkedList<>();

            }
        }
    }

    public int getCurFloor(){
        return curFloor;
    }

    public Direction getDirection(){
        return direction;
    }


    public void addStop(int i) {
        stopQ.add(i);
    }

    private void elevatorPause() {
        try {
            System.out.println("Elevator "+id+" moving "+direction+" has stopped at " +curFloor);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void timeToMoveUpOrDownAFloor() {
        try {
            System.out.println("Elevator "+id+" moving "+direction+" from "+curFloor);
            Thread.sleep(timeToMoveUpOrDown);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopElevator() {
        isStopped = true;
    }

    public void reStart() {
        isStopped = false;
    }

    @Override
    public void run() {
        move();
    }
}
