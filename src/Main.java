import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        ArrayList<Process> processes = new ArrayList<>();
//        System.out.println("Test 1: time to change 1 RR slice 2");
//        Process proc1 = new Process(6,2);
//        Process proc3 = new Process(8,1);
//        Process proc4 = new Process(3,0);
//        processes.add(proc1);
//        processes.add(proc3);
//        processes.add(proc4);
//        CPU cpu = new CPU(processes,1);
//        cpu.FCFS(true);
//        cpu.SJF(true);
//        cpu.RR(2,true);
        ArrayList<Process> processes1 = new ArrayList<>();
        for(int i= 0; i<100;i++){
            Process proc = new Process();
            processes1.add(proc);
        }
        System.out.println("Test 2 time to change 5 timeSlice RR 2 100 random processes");
        CPU cpu1 = new CPU(processes1,0);
        cpu1.FCFS(false);
        cpu1.SJF(false);
        cpu1.RR(101,false);
//        System.out.println("Test 3 time to change 0 timeSlice RR 2 100 random processes");
//        CPU cpu2 = new CPU(processes1,0);
//        cpu2.FCFS(false);
//        cpu2.SJF(false);
//        cpu2.RR(2,false);
//        System.out.println("Test 4 time to change 1 timeSlice RR 10 100 random processes");
//        CPU cpu3 = new CPU(processes1,1);
//        cpu3.FCFS(false);
//        cpu3.SJF(false);
//        cpu3.RR(15,false);
    }
}
