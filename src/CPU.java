import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class CPU {
    private ArrayList<Process> processes;
    private ArrayList<Process> processesInQueue;
    private int timeToChange;
    public CPU(ArrayList<Process> processes,int timeToChange) {
        if(timeToChange == 0){
            this.timeToChange = -1;
        }
        else{
            this.timeToChange = timeToChange;
        }
        this.processes = processes;
        this.processesInQueue = new ArrayList<Process>();
    }
    public CPU(int timeToChange){
        if(timeToChange == 0){
            this.timeToChange = -1;
        }
        else{
            this.timeToChange = timeToChange;
        }
        this.processes = new ArrayList<Process>();
        this.processesInQueue = new ArrayList<Process>();
    }
    public void FCFS(boolean showData){
        boolean changeTime = false;
        int time = 0;
        int timeInterval = 0;
        ArrayList<Process> processesFCFS = new ArrayList<Process>();
        for(Process process: processes){
            processesFCFS.add(process.clone());
        }

        while(true){
            for(Process process : processesFCFS){
                if(time == process.getArrivalTime()){
                    processesInQueue.add(process);
                    if(processesInQueue.size()==1){
                        process.setStartingTime(time);
                    }

                }

            }
            if(!changeTime || timeToChange == -1) {
                if (processesInQueue.size() >= 1) {
                    Process process = processesInQueue.get(0);
                    if (process.getBurstTime() == time - process.getStartingTime()) {
                        process.setExecuted(true);
                        changeTime = true;
                        process.setWaitingTime(time - process.getBurstTime() - process.getArrivalTime());
                        if(showData){
                            System.out.println(process.toString());
                        }
                        processesInQueue.remove(process);
                        if (processesInQueue.size() >= 1) {
                            if(timeToChange != -1){
                                processesInQueue.get(0).setStartingTime(time+timeToChange);
                            }
                            else{
                                processesInQueue.get(0).setStartingTime(time);
                            }

                        }

                    }

                }
            }
            else{
               timeInterval++;
               if(timeInterval == timeToChange){
                   timeInterval = 0;
                   changeTime = false;
               }
            }
            time++;
            int numberOfExecutedProcesses = 0;
            for(Process process : processesFCFS){
                if(process.isExecuted()){
                    numberOfExecutedProcesses++;
                }
            }
            if(numberOfExecutedProcesses == processesFCFS.size()){
                break;
            }

        }
        double averageWaitingTime = 0;
        double maxWaitingTime = 0;
        for(Process process: processesFCFS){
            averageWaitingTime+=process.getWaitingTime();
            if(process.getWaitingTime()>maxWaitingTime){
                maxWaitingTime = process.getWaitingTime();
            }
        }
        averageWaitingTime/=processesFCFS.size();
        System.out.println("FCFS average waiting time : "+averageWaitingTime);
        System.out.println("FCFS max waiting time: "+maxWaitingTime);
    }
    public void SJF(boolean showData){
        boolean busy = false;
        boolean changingTime = false;
        int timeInterval = 0;
        int time = 0;
        ArrayList<Process> processesSJF = new ArrayList<Process>();
        for(Process process: processes){
            processesSJF.add(process.clone());
        }
        while(true){
            for(Process process : processesSJF){
                if(time == process.getArrivalTime()){
                    processesInQueue.add(process);
                    if(processesInQueue.size()==1){
                        process.setStartingTime(time);
                        busy = true;
                    }

                }

            }
            if(!changingTime || timeToChange == -1){
                if(processesInQueue.size() >=1){
                    Process process = processesInQueue.get(0);
                    if(process.getBurstTime() == time - process.getStartingTime()){
                        process.setExecuted(true);
                        changingTime = true;
                        process.setWaitingTime(time-process.getBurstTime()-process.getArrivalTime());
                        if(showData){
                            System.out.println(process.toString());
                        }
                        processesInQueue.remove(process);
                        busy = false;

                    }
                    if(!busy && processesInQueue.size() != 0){
                        Collections.sort(processesInQueue,(Comparator.comparingDouble(Process::getBurstTime)));
                        if(timeToChange == -1){
                            processesInQueue.get(0).setStartingTime(time);
                        }
                        else{
                            processesInQueue.get(0).setStartingTime(time+timeToChange);
                        }
                        busy = true;

                    }

                }
            }
            else{
                timeInterval++;
                if(timeInterval == timeToChange){
                    timeInterval = 0;
                    changingTime = false;
                }
            }
            time++;
            int numberOfExecutedProcesses = 0;
            for(Process process : processesSJF){
                if(process.isExecuted()){
                    numberOfExecutedProcesses++;
                }
            }
            if(numberOfExecutedProcesses == processesSJF.size()){
                break;
            }

        }
        double averageWaitingTime = 0;
        double maxWaitingTime = 0;
        for(Process process: processesSJF){
            averageWaitingTime+=process.getWaitingTime();
            if(process.getWaitingTime()>maxWaitingTime){
                maxWaitingTime = process.getWaitingTime();
            }
        }

        averageWaitingTime/=processesSJF.size();
        System.out.println("SJF average waiting time : "+averageWaitingTime);
        System.out.println("SJF max waiting time: "+maxWaitingTime);

    }
    public void RR(int timeSlice, boolean showData){
        boolean changeTime = false;
        int time = 0;
        ArrayList<Process> processesRR = new ArrayList<Process>();
        HashMap<Process,Double> timeToEnd = new HashMap<>();
        for(Process process: processes){
            processesRR.add(process.clone());

        }
        for(Process process: processesRR){
            timeToEnd.put(process,process.getBurstTime());
        }

        int timeInterval = 0;
        while(true){
            // add to the queue
            for(Process process : processesRR){
                if(time == process.getArrivalTime()){
                    processesInQueue.add(process);

                }

            }
            // change the process
            if(timeInterval == timeSlice && (!changeTime || timeToChange == -1)){
                timeInterval = 0;
                if(processesInQueue.size()>=1){
                    Process process = processesInQueue.get(0);
                    processesInQueue.remove(0);
                    changeTime = true;
                    processesInQueue.add(process);
                    if (processesInQueue.get(0).getStartingTime() == -1) {
                        if(timeToChange == -1){
                            processesInQueue.get(0).setStartingTime(time);
                        }
                        else{
                            processesInQueue.get(0).setStartingTime(time+timeToChange);
                        }

                    }
                }


            }
            // execution process
            if(!changeTime || timeToChange == -1){
                if(processesInQueue.size() >=1){
                    Process process = processesInQueue.get(0);
                    if (processesInQueue.get(0).getStartingTime() == -1) {
                        processesInQueue.get(0).setStartingTime(time);
                    }
                    timeToEnd.put(process,timeToEnd.get(process)-1);
                    if(timeToEnd.get(process) == 0){
                        timeInterval = 0;
                        process.setExecuted(true);
                        changeTime = true;
                        process.setWaitingTime(time+1-process.getBurstTime()-process.getArrivalTime());
                        if(showData){
                            System.out.println(process.toString());
                        }
                        processesInQueue.remove(process);


                    }

                }

            }
            else{
                if(timeInterval == timeToChange){
                    timeInterval = 0;
                    changeTime = false;
                }

            }
            timeInterval++;
            time++;
            int numberOfExecutedProcesses = 0;
            for(Process process : processesRR){
                if(process.isExecuted()){
                    numberOfExecutedProcesses++;
                }
            }
            if(numberOfExecutedProcesses == processesRR.size()){
                break;
            }

        }
        double averageWaitingTime = 0;
        double maxWaitingTime = 0;
        for(Process process: processesRR){
            averageWaitingTime+=process.getWaitingTime();
            if(process.getWaitingTime()>maxWaitingTime){
                maxWaitingTime = process.getWaitingTime();
            }
        }
        averageWaitingTime/=processesRR.size();
        System.out.println("RR average waiting time : "+averageWaitingTime);
        System.out.println("RR average waiting time :"+maxWaitingTime);


    }


}
