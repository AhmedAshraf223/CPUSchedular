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

            if (shortestProcess != null) {
                if (currentProcess != null){
                    if(timeline.size()==0){
                        timeline.add(currentProcess.getName());
                        if (currentProcess.startTime == 0){
                            currentProcess.startTime = currentTime;
                        }
                    }else if (currentProcess.getName() != timeline.get(timeline.size() - 1)){
                        timeline.add(currentProcess.getName());
                        if (currentProcess.startTime == 0){
                            currentProcess.startTime = currentTime;
                        }
                    }
                }
            
                currentProcess = shortestProcess;
                currentProcess.remainingTime -= 1;
                if (currentProcess.remainingTime == 0) {
                    currentProcess.finishTime = currentTime;
                    executed++;
                    currentProcess = null;
                    
                }
            }

            currentTime++;
        }

        
        System.out.println("SRTF Schedule: " + timeline);
        for (Process process : readyQueue){
            System.out.println(process.getName() + "     " + process.startTime + "     " + process.finishTime);
        }
    }
};