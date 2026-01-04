import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FCFS implements Scheduler {

    // Gantt chart storage
    private List<GanttEntry> gantt = new ArrayList<>();

    @Override
    public void schedule(List<Process> processes) {

        // Clear old gantt data (important if reused)
        gantt.clear();

        // Sort processes by arrival time
        Collections.sort(processes, Comparator.comparingInt(p -> p.arrivalTime));

        int currentTime = 0;

        for (Process p : processes) {

            // CPU idle case
            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }

            // ---- GANTT START ----
            int start = currentTime;
            currentTime += p.burstTime;
            gantt.add(new GanttEntry(p.pid, start, currentTime));
            // ---- GANTT END ----

            // Calculate metrics
            p.completionTime = currentTime;
            p.turnaroundTime = p.completionTime - p.arrivalTime;
            p.waitingTime = p.turnaroundTime - p.burstTime;
        }
    }

    @Override
    public List<GanttEntry> getGanttChart() {
        return gantt;
    }
}
