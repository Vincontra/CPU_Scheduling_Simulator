public class Process {

    int pid;
    int arrivalTime;
    int burstTime;
    int priority;

    int remainingTime;

    int completionTime;
    int turnaroundTime;
    int waitingTime;

    // Constructor without priority (for FCFS, SJF, RR)
    public Process(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }

    // Constructor with priority (for Priority Scheduling)
    public Process(int pid, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
    }
}
