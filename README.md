# Scheduling Algorithms

This repository provides implementations of three popular scheduling algorithms: First-Come, First-Served (FCFS), Shortest Job First (SJF), and Round Robin (RR). These algorithms are commonly used in operating systems and job scheduling to efficiently manage the execution of processes or tasks.

## Algorithms

### 1. First-Come, First-Served (FCFS)

The FCFS algorithm is a non-preemptive scheduling algorithm that executes processes in the order they arrive. The process that arrives first is scheduled first and continues executing until it completes or is interrupted by another higher-priority process. This algorithm follows a queue-like structure, where new processes are added to the end of the queue and executed in the order they are added.

### 2. Shortest Job First (SJF)

The SJF algorithm is a non-preemptive scheduling algorithm that selects the process with the shortest burst time for execution. It prioritizes processes based on their expected execution time, where the process with the shortest burst time is selected first. This algorithm aims to minimize the average waiting time and optimize the overall completion time of processes.

### 3. Round Robin (RR)

The RR algorithm is a preemptive scheduling algorithm that assigns a fixed time quantum or time slice to each process in a cyclic manner. Each process is allowed to execute for a specific time quantum, typically a small value, before being interrupted and moved to the end of the queue. This algorithm provides fair execution time to all processes and is commonly used in time-sharing systems.

## License

This repository is licensed under the [MIT License](LICENSE)
