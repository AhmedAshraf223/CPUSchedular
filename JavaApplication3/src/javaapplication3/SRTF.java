package javaapplication3;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;


public class SRTF {

    private ArrayList<Process> processes;
    private ArrayList<Process> readyQueue = new ArrayList<>();

    SRTF(ArrayList<Process> processes) {
        this.processes = processes;
        this.readyQueue.addAll(processes);
        this.readyQueue.sort(Comparator.comparingInt(Process::getArrivalTime));
    }

    public void Scheduling() {
        ArrayList<String> timeline = new ArrayList<>();
        int currentTime = 0;
        int executed = 0;
        Process currentProcess = null;
     
        while (executed < processes.size()) {
            Process shortestProcess = null;
            int shortestRemainingTime = Integer.MAX_VALUE;
    
            for (Process process : readyQueue) {
                if (process.getArrivalTime() <= currentTime && process.getRemainingTime() > 0 && process.getRemainingTime() < shortestRemainingTime && process.remainingTime != 0) {
                    shortestProcess = process;
                    shortestRemainingTime = process.getRemainingTime();
                }
            }
            currentProcess = shortestProcess;

            if (shortestProcess != null) {
                if (currentProcess != null) {
                    // Check if the current process is different from the last one in the timeline
                    if (timeline.isEmpty() || !currentProcess.getName().equals(timeline.get(timeline.size() - 1))) {
                        timeline.add(currentProcess.getName());
                        if (currentProcess.startTime == -1) {
                            currentProcess.startTime = currentTime;
                        }
                    }
                }
                currentProcess.remainingTime -= 1;
                if (currentProcess.remainingTime == 0) {
                    currentProcess.finishTime = currentTime + 1;
                    executed++;
                    currentProcess = null;
                }
            }
    
            currentTime++;
        }
        for (Process p : readyQueue){
            p.setTurnArroundTime(p.finishTime - p.getArrivalTime());
            p.SetWaitingTime(p.getTurnArroundTime() - p.getBurst());
        }
        System.out.println("SRTF Schedule: " + timeline);
        System.out.println("*************************************");
        System.out.println("Name      Start time     Finish time");
    
        for (Process process : readyQueue) {
            String formattedOutput = String.format("%-10s %-14d %-11d",
                    process.getName(), process.startTime, process.finishTime);
            System.out.println(formattedOutput);
        }
    }
    public void getRangeProcess()
    {
        for(Process e : readyQueue)
        {
            
            System.out.println("Proccess Name : " + e.getName());
            System.out.println("process wating time : " +  e.getWaitingTime());
            System.out.println("process turnarround time : "+ e.getTurnArroundTime());
            System.out.println("*************************");
        }
    }    
    public void stat()
    {
        float avg_w = 0 ;
        float avg_t = 0; 
        for(Process e : readyQueue)
        {
            avg_w += e.getWaitingTime() ;
            avg_t += e.getTurnArroundTime();
        }
        avg_w = avg_w/readyQueue.size();
        avg_t = avg_t/readyQueue.size();
        System.out.println("Average Waiting time : " + avg_w);
        System.out.println("Average Trun Around time : " + avg_t);
    }

};