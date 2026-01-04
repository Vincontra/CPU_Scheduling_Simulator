import java.util.ArrayList;
import java.util.List;

public class PriorityScheduling implements Scheduler {

    // Gantt chart storage
    private List<GanttEntry> gantt = new ArrayList<>();

    @Override
    public void schedule(List<Process> processes) {

        // Clear old gantt data
        gantt.clear();

        int n = processes.size();
        boolean[] completed = new boolean[n];
        int completedCount = 0;
        int currentTime = 0;

        while (completedCount < n) {

            int idx = -1;
            int bestPriority = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                Process p = processes.get(i);

                if (!completed[i] &&
                        p.arrivalTime <= currentTime &&
                        p.priority < bestPriority) {

                    bestPriority = p.priority;
                    idx = i;
                }
            }

            if (idx == -1) {
                currentTime++;
                continue;
            }

            Process p = processes.get(idx);

            // ---- GANTT START ----
            int start = currentTime;
            currentTime += p.burstTime;
            gantt.add(new GanttEntry(p.pid, start, currentTime));
            // ---- GANTT END ----

            p.completionTime = currentTime;
            p.turnaroundTime = p.completionTime - p.arrivalTime;
            p.waitingTime = p.turnaroundTime - p.burstTime;

            completed[idx] = true;
            completedCount++;
        }
    }

    @Override
    public List<GanttEntry> getGanttChart() {
        return gantt;
    }
}
