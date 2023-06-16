import java.util.Random;

public class Process implements Cloneable{
    private double arrivalTime;
    private double burstTime;
    private boolean executed;
    private double waitingTime;

    private double startingTime;

    public Process() {
        Random random = new Random();
        this.arrivalTime = Math.round(random.nextDouble()*100)+1;
        this.burstTime = Math.round(random.nextDouble()*100)+1;
        this.startingTime = -1;

    }
    public Process(double burstTime, double arrivalTime){
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.startingTime = -1;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public double getBurstTime() {
        return burstTime;
    }

    public double getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(double startingTime) {
        this.startingTime = startingTime;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public double getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(double waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setBurstTime(double burstTime) {
        this.burstTime = burstTime;
    }

    @Override
    public String toString() {
        return "Process{" +
                "arrivalTime=" + arrivalTime +
                ", burstTime=" + burstTime +
                ", executed=" + executed +
                ", waitingTime=" + waitingTime +
                ", startingTime=" + startingTime +
                '}';
    }

    @Override
    public Process clone() {
        try {
            Process clone = (Process) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
