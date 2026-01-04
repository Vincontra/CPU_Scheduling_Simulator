import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoundRobin implements Scheduler {

    int timeQuantum;

    // Gantt chart storage
    private List<GanttEntry> gantt = new ArrayList<>();

    public RoundRobin(int tq) {
        this.timeQuantum = tq;
    }

    @Override
    public void schedule(List<Process> processes) {

        // Clear old gantt data
        gantt.clear();

        Queue<Process> queue = new LinkedList<>();
        int currentTime = 0;
        int completed = 0;
        int n = processes.size();
        boolean[] added = new boolean[n];

        while (completed < n) {

            // Add newly arrived processes
            for (int i = 0; i < n; i++) {
                Process p = processes.get(i);
                if (!added[i] && p.arrivalTime <= currentTime) {
                    queue.add(p);
                    added[i] = true;
                }
            }

            if (queue.isEmpty()) {
                currentTime++;
                continue;
            }

            Process p = queue.poll();

            int execTime = Math.min(timeQuantum, p.remainingTime);

            // ---- GANTT START ----
            int start = currentTime;
            currentTime += execTime;
            p.remainingTime -= execTime;
            gantt.add(new GanttEntry(p.pid, start, currentTime));
            // ---- GANTT END ----

            // Add processes that arrived during execution
            for (int i = 0; i < n; i++) {
                Process q = processes.get(i);
                if (!added[i] && q.arrivalTime <= currentTime) {
                    queue.add(q);
                    added[i] = true;
                }
            }

            if (p.remainingTime > 0) {
                queue.add(p);
            } else {
                p.completionTime = currentTime;
                p.turnaroundTime = p.completionTime - p.arrivalTime;
                p.waitingTime = p.turnaroundTime - p.burstTime;
                completed++;
            }
        }
    }

    @Override
    public List<GanttEntry> getGanttChart() {
        return gantt;
    }
}
