//package javaapplication3;

import java.util.ArrayList;

public class PriortyScheduling {
    private ArrayList<Process> processes;
    private ArrayList<Process> readyQueue = new ArrayList<Process>() ;
    private ArrayList<String> timeline = new ArrayList<>() ;

    PriortyScheduling(ArrayList<Process> processes)
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
                
                // checking if arrival time for all process = 0 or not 
                int flag= 1 ;
                for(Process e : processes)
                {
                    if(e.getArrivalTime() == 0)
                    {
                        flag = 0 ;
                    }
                    else{
                        flag = 1 ; 
                        break ;
                    }
                }

                // if flage = false then we will choose the most pritory process 
                // else we will choose the first process attachecd to CPU 
                int index = 0; 
                if (flag == 0)
                {
                    index = getMaxPriorty() ;
                }
                else 
                {
                    index = getLeastArrivalTime(); 
                }

                processes.get(index).startTime = 0  ;
                processes.get(index).finishTime = processes.get(index).getBurst() ;
                processes.get(index).setArrivalTime(0);
                processes.get(index).setTurnArroundTime(processes.get(index).getBurst()); // setting the turn arround time to be equal to burst time for first process
                processes.get(index).SetWaitingTime(0);  // as it's the first process
                readyQueue.add(processes.get(index)) ;
                timeline.add(processes.get(index).getName()) ; 
                processes.remove(index) ; 
                solveStarvation();
         }
        // adding the least burst time to the list 
            else{
                
                int index = getMaxPriorty(); 

                processes.get(index).setTurnArroundTime(processes.get(index).getBurst() + readyQueue.get(readyQueue.size()-1).getTurnArroundTime());
                processes.get(index).SetWaitingTime(readyQueue.get(readyQueue.size()-1).getTurnArroundTime() - processes.get(index).getArrivalTime());
                processes.get(index).startTime = readyQueue.get(readyQueue.size()-1).getTurnArroundTime() ;
                processes.get(index).finishTime = processes.get(index).startTime + processes.get(index).getBurst() ;
                readyQueue.add(processes.get(index)); 
                timeline.add(processes.get(index).getName()) ; 
                processes.remove(index) ;
                // solving starvation problem 
                solveStarvation();
            }

        }  
        
        System.out.println("Proirity Schedule: " + timeline);
        System.out.println("*************************************");
        System.out.println("Name      Start time     Finish time");
    
        for (Process process : readyQueue) {
            String formattedOutput = String.format("%-10s %-14d %-11d",
                    process.getName(), process.startTime, process.finishTime);
            System.out.println(formattedOutput);
        }
   
    }


    public void stats()
    {
        float avg_w = 0 ;
        float avg_t = 0; 
        
        for(Process e : readyQueue)
        {
            System.out.println("Proccess Name : " + e.getName());
            System.out.println("process waiting time : " +  e.getWaitingTime());
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

    private void solveStarvation()
    {
        for(Process e : processes)
        {
            e.solveStarvation();
        }
    }

    private int getMaxPriorty()
    {
          int maxProirity  =  processes.get(0).getPriorty();
                int index = 0 ;
                if(readyQueue.isEmpty())
                {
                       for (int i = 1 ; i < processes.size() ; i ++)
                    {
                        if(processes.get(i).getPriorty() < maxProirity)
                        {
                            maxProirity = processes.get(i).getPriorty() ;
                            index = i ;  
                        }
                    }
                    return index ;
                }
                else 
                {
                    for (int i = 1 ; i < processes.size() ; i ++)
                    {
                        if(processes.get(i).getPriorty() < maxProirity &&  
                        processes.get(i).getArrivalTime() <= readyQueue.get(readyQueue.size()-1).getTurnArroundTime())
                        {
                            maxProirity = processes.get(i).getPriorty() ;
                            index = i ;  
                        }
                    }
                    return index ;
                }
             
    }

    private int getLeastArrivalTime()
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
                return index ;
    }

}
