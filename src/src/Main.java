import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Process> processes = new ArrayList<>();

        processes.add(new Process(1, 0, 5));
        processes.add(new Process(2, 1, 3));
        processes.add(new Process(3, 2, 8));

// Scheduler scheduler = new FCFS();
 Scheduler scheduler = new SJF();
// Scheduler scheduler = new PriorityScheduling();
// Scheduler scheduler = new RoundRobin(2);


        //Scheduler scheduler = new FCFS();
        scheduler.schedule(processes);

        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");

        for (Process p : processes) {
            System.out.println(
                    p.pid + "\t" +
                            p.arrivalTime + "\t" +
                            p.burstTime + "\t" +
                            p.completionTime + "\t" +
                            p.turnaroundTime + "\t" +
                            p.waitingTime
            );
        }
    }
}
