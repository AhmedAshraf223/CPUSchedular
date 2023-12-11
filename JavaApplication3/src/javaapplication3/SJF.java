package javaapplication3;
import java.util.ArrayList;

public class SJF {
    private ArrayList<Process> processes;
    private ArrayList<Process> readyQueue = new ArrayList<Process>() ;

    SJF(ArrayList<Process> processes)
    {
        this.processes = processes ; 
    }

    public void Scheduling()
    {
        // get the min arrival process in to the ready queue
        int counter = processes.size() ; 
        for (int iter = 0 ; iter < counter ; iter ++)
        {
            if (readyQueue.isEmpty())
            {
                int minArrivalTime = processes.get(0).getArrivalTime() ; 
                int index = 0 ; 
                for(int i = 0 ; i < processes.size() ; i ++)
                {
                    int curr =  processes.get(i).getArrivalTime() ;
                    if(curr < minArrivalTime)
                    {
                        minArrivalTime = curr ;
                        index = i ; 
                    } 
                }
                processes.get(index).setArrivalTime(0);
                processes.get(index).setTurnArroundTime(processes.get(index).getBurst()); // setting the turn arround time to be equal to burst time for first process
                processes.get(index).SetWaitingTime(0);  // as it's the first process
                readyQueue.add(processes.get(index)) ;
                processes.remove(index) ; 
         }
        // adding the least burst time to the list 
            else{
                int minBurst  =  processes.get(0).getBurst();
                int index = 0 ;
                for (int i = 1 ; i < processes.size() ; i ++)
                {
                    if(processes.get(i).getBurst() < minBurst &&  
                    processes.get(i).getArrivalTime() <= readyQueue.get(readyQueue.size()-1).getTurnArroundTime())
                    {
                        minBurst = processes.get(i).getBurst() ;
                        index = i ;  
                    }
                }

                processes.get(index).setTurnArroundTime(processes.get(index).getBurst() + readyQueue.get(readyQueue.size()-1).getTurnArroundTime());
                processes.get(index).SetWaitingTime(readyQueue.get(readyQueue.size()-1).getTurnArroundTime() - processes.get(index).getArrivalTime());
                readyQueue.add(processes.get(index)); 
                processes.remove(index) ;
            }

        }   
   
    }


       

    public void stats()
    {
        float avg_w = 0 ;
        float avg_t = 0; 
        
        for(Process e : readyQueue)
        {
            System.out.println("Proccess Name : " + e.getName());
            System.out.println("process wating time : " +  e.getWaitingTime());
            System.out.println("process turnarround time : "+ e.getTurnArroundTime());
            System.out.println("*************************");
            avg_w += e.getWaitingTime() ;
            avg_t += e.getTurnArroundTime();
        }
        avg_w = avg_w/readyQueue.size();
        avg_t = avg_t/readyQueue.size();
        System.out.println("Average Waiting time : " + avg_w);
        System.out.println("Average Trun Around time : " + avg_t);
    }


    


    
}
