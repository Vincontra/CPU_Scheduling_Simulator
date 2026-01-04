# CPU Scheduling Simulator

## Overview
This project is a Java-based simulator for **CPU Scheduling Algorithms** used in Operating Systems. It helps visualize how different scheduling policies decide the execution order of processes. The simulator supports popular algorithms such as:

- First-Come, First-Served (FCFS)
- Shortest Job Next (SJN)
- Round Robin (RR)
- Priority Scheduling

The goal of the simulator is to help users compare scheduling behavior and performance metrics like waiting time, turnaround time, and CPU utilization through an interface.

---

##  Problem Statement
Different scheduling algorithms perform better in different scenarios. Some minimize waiting time, others improve response time or throughput. Choosing the “best” algorithm depends on system goals — and this simulator allows you to **experiment and analyze** these trade-offs.

Users select a scheduling algorithm, enter process details (burst time, arrival time, priority, etc.), and the simulator visually demonstrates how processes are executed inside the CPU.

---

## Features
- **Multiple Scheduling Algorithms**
- **Graphical User Interface (GUI)**
- **Ready Queue View**
- **Performance Metrics Display**

---

## Files and Directories
```text
src/main/java/
│
├── Process.java
│   └─ Represents a CPU process (PID, arrival time, burst time, priority, etc.)
│
├── Scheduler.java
│   └─ Common interface for all scheduling algorithms
│
├── FCFS.java
│   └─ First-Come, First-Served scheduling implementation
│
├── SJF.java
│   └─ Shortest Job First (non-preemptive) scheduling implementation
│
├── PriorityScheduling.java
│   └─ Priority-based scheduling implementation
│
├── RoundRobin.java
│   └─ Round Robin scheduling with time quantum support
│
├── GanttEntry.java
│   └─ Model class representing execution slices for Gantt chart
│
├── GanttChartPanel.java
│   └─ Custom Swing panel for animated Gantt chart visualization
│
├── CPUSchedulingGUI.java
│   └─ Main GUI class to input processes, select algorithm, and display results
│
└── Main.java
    └─ Console-based testing entry point
```
---

## Getting Started

### Prerequisites
- Java JDK 8 or higher
- (Optional) Apache Maven
- Any Java IDE (IntelliJ, Eclipse, etc.)

---

## 📥 Installation
Clone the repository:
git clone https://github.com/Vincontra/CPU_Scheduling_Simulator.git


Open the project in your IDE.
---


##  Example — Round Robin
1. Open the simulator
2. Select **Round Robin**
3. Enter number of processes
4. Click **Start**
5. View the execution sequence and metrics

---

This simulator is designed to make OS scheduling concepts easier to **see, test, and understand.**


