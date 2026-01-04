import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CPUSchedulingGUI extends JFrame {

    JComboBox<String> algoBox;
    JSpinner processCount;
    JTextField tqField;
    JTable inputTable, outputTable;

    public CPUSchedulingGUI() {

        setTitle("CPU Scheduling Simulator");
        setSize(850, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ---------- TOP PANEL ----------
        JPanel top = new JPanel();

        algoBox = new JComboBox<>(new String[]{
                "FCFS", "SJF", "Priority", "Round Robin"
        });

        processCount = new JSpinner(new SpinnerNumberModel(3, 1, 10, 1));
        tqField = new JTextField("2", 3);

        top.add(new JLabel("Algorithm:"));
        top.add(algoBox);
        top.add(new JLabel("Processes:"));
        top.add(processCount);
        top.add(new JLabel("Time Quantum:"));
        top.add(tqField);

        add(top, BorderLayout.NORTH);

        // ---------- INPUT TABLE ----------
        inputTable = new JTable(new DefaultTableModel(
                new Object[]{"PID", "Arrival", "Burst", "Priority"}, 0
        ));
        add(new JScrollPane(inputTable), BorderLayout.CENTER);

        // ---------- OUTPUT TABLE ----------
        outputTable = new JTable(new DefaultTableModel(
                new Object[]{"PID", "AT", "BT", "CT", "TAT", "WT"}, 0
        ));
        add(new JScrollPane(outputTable), BorderLayout.SOUTH);

        // ---------- RUN BUTTON ----------
        JButton runBtn = new JButton("Run Simulation");
        add(runBtn, BorderLayout.EAST);

        // ---------- EVENTS ----------
        processCount.addChangeListener(e -> updateInputRows());
        runBtn.addActionListener(e -> runSimulation());

        updateInputRows();
        setVisible(true);
    }

    // ---------- UPDATE INPUT TABLE ----------
    void updateInputRows() {
        DefaultTableModel m = (DefaultTableModel) inputTable.getModel();
        m.setRowCount(0);
        int n = (int) processCount.getValue();
        for (int i = 1; i <= n; i++) {
            m.addRow(new Object[]{i, 0, 0, 0});
        }
    }

    // ---------- RUN SIMULATION ----------
    void runSimulation() {

        List<Process> processes = new ArrayList<>();
        DefaultTableModel in = (DefaultTableModel) inputTable.getModel();

        for (int i = 0; i < in.getRowCount(); i++) {
            processes.add(new Process(
                    i + 1,
                    Integer.parseInt(in.getValueAt(i, 1).toString()),
                    Integer.parseInt(in.getValueAt(i, 2).toString()),
                    Integer.parseInt(in.getValueAt(i, 3).toString())
            ));
        }

        Scheduler scheduler;
        String algo = algoBox.getSelectedItem().toString();

        if (algo.equals("FCFS")) {
            scheduler = new FCFS();
        } else if (algo.equals("SJF")) {
            scheduler = new SJF();
        } else if (algo.equals("Priority")) {
            scheduler = new PriorityScheduling();
        } else {
            int tq = Integer.parseInt(tqField.getText());
            scheduler = new RoundRobin(tq);
        }

        scheduler.schedule(processes);
        showOutput(processes);

        // ---------- GANTT CHART ANIMATION ----------
        List<GanttEntry> gantt = scheduler.getGanttChart();

//        JFrame ganttFrame = new JFrame("Gantt Chart Animation");
//        ganttFrame.add(new GanttChartPanel(gantt));
//        ganttFrame.setSize(900, 250);
//        ganttFrame.setLocationRelativeTo(this);
//        ganttFrame.setVisible(true);
        JFrame ganttFrame = new JFrame("Gantt Chart Animation");

        GanttChartPanel panel = new GanttChartPanel(gantt);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        ganttFrame.setContentPane(scrollPane);
        ganttFrame.setSize(900, 260);
        ganttFrame.setLocationRelativeTo(this);
        ganttFrame.setVisible(true);


    }

    // ---------- OUTPUT TABLE ----------
    void showOutput(List<Process> processes) {
        DefaultTableModel out = (DefaultTableModel) outputTable.getModel();
        out.setRowCount(0);
        for (Process p : processes) {
            out.addRow(new Object[]{
                    p.pid,
                    p.arrivalTime,
                    p.burstTime,
                    p.completionTime,
                    p.turnaroundTime,
                    p.waitingTime
            });
        }
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {
        new CPUSchedulingGUI();
    }
}
