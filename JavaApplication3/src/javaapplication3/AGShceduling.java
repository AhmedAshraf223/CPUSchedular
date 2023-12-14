//package javaapplication3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Deque;
import java.util.Scanner;
import java.util.Scanner;


public class AGShceduling {
    private ArrayList<Process> processes;
    private int size ;
  //  private ArrayList<Process> readyQueue = new ArrayList<Process>() ;
    Deque<Process> readyQueue = new LinkedList<>();
    private ArrayList<Process> DieList = new ArrayList<>();
    private int curr =  0 ;
    private ArrayList<String> timeline = new ArrayList<>() ;

    AGShceduling(ArrayList<Process> processes)
    {
        this.processes = processes ;
        this.size = processes.size() ;
    }

   // a private function to generate AG factor
    
    private void generateAGFactor()
    {
        for(Process e : processes)
        {
        //     Random rand = new Random();
        //     int RF = rand.nextInt(21);

        //     if(RF  < 10 )
        //     {
        //         e.setAG(RF + e.getArrivalTime() + e.getBurst()) ;
        //     }
        //     else if (RF > 10 )
        //     {
        //         e.setAG(10 + e.getArrivalTime() + e.getBurst());
        //     }
        //     else 
        //     {
        //         e.setAG(e.getPriorty() + e.getArrivalTime() + e.getBurst());
        //     }
        
            //////////////// lec example ////////////////////
            if(e.getName() =="p1")
            {
                e.setAG(20);
            }
            else if(e.getName()=="p2")
            {
                e.setAG(17);
            }
            else if(e.getName() == "p3")
            {
                e.setAG(16);
            }
            else 
            {
                e.setAG(43);
            }
        }
    }

    // a function to calculate the mean of quantums ;
    private float meanOfQuantum()
    {
        float avg = 0 ;
        for(Process e : readyQueue)
        {
            avg += e.getQuantum() ;
        }
        avg = avg / processes.size() ;
        return avg ;
    }

    // a function to get the least arrival time in the queue
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

    // a function to check if burst time for all process is 0 or not to end the algorithm ; 
    private boolean checkFinish()
    {
        boolean flag = false ; 
        if(DieList.size() == this.size)
        {
            flag = true ;
        }
        return flag ;
    }

    // a process to check if there are any process has ag factor less than the current process ;
    // we pass an int as refrencess to get the index of the smallest ag factor ; 
    private boolean checKswitching(Process p)
    {
        int minAGFactor = p.getAG() ;
        boolean flag = false ;
        for(int i = 0 ; i < processes.size() ; i ++)
        {
            if(processes.get(i).getArrivalTime() <= curr && processes.get(i).getAG() < minAGFactor)
            {
                minAGFactor = processes.get(i).getAG() ; 
                flag = true ;
            }
        }
        return flag ;
    }

    private int switching(Process p)
    {
        int minAGFactor = p.getAG() ;
        int index = 0 ; 
  
        for(int i = 0 ; i < processes.size() ; i ++)
        {
            if(processes.get(i).getArrivalTime() <= curr && processes.get(i).getAG() < minAGFactor)
            {
                index = i  ;
                minAGFactor = processes.get(index).getAG() ; 
            }
        
        }
        return index ;
    }


    // senario 1 when the running process used it's all quantum time ;
    private void senario1(Process p)
    {
        float mean = meanOfQuantum() ;
        p.setQuantum((int)Math.ceil((.1 * mean )+ p.getQuantum()));
    }
    // senario 2 when the running process didn't use all it's quantum time ;
    private void senario2(Process p , int counter )
    {
        p.setQuantum( p.getQuantum() + (p.getQuantum() - counter));
    }
    // senario 3 when the process is finished 
    private void senario3(Process p)
    {
        for(int i = 0 ; i < processes.size() ; i ++)
        {
            if(processes.get(i).getName() == p.getName())
            {
                processes.remove(i) ; 
                break ;
            }
        }
    }

    private void deleteProceess(Process p )
    {
        for (int i = 0 ; i < processes.size() ; i ++)
        {
            if(processes.get(i).getName() == p.getName())
            {
                processes.remove(i) ; 
                break ;
            }
        }
    }


    private boolean searchQueue(Deque<Process> queue, Process p ) {
        for (Process element : queue) {
            if (element.getName() == p.getName()) {
                return true; 
            }
        }
        return false; // Element not found
    }

    
      
    private  Process searchLeastAGQueue(Deque<Process> queue) {
        Process temp = queue.peekFirst();
        for (Process element : queue) {
            if (element.getAG() < temp.getAG()) {
                temp = element ;
            }
        }
        return temp; // Element not found
    }


    public void sheduling()
    {
        generateAGFactor();
        int index = 0 ;
       // when starting the process ; 
        index = getLeastArrivalTime() ;
        readyQueue.addFirst(processes.get(index)) ; 
        readyQueue.peekFirst().startTime = curr  ;
        
        while(true)
        {
            //System.out.println("currend process" + readyQueue.getFirst().getName());

            for(int counter = 1; counter <= readyQueue.peekFirst().getQuantum(); counter ++)
            {   

                timeline.add(readyQueue.peekFirst().getName());

//                System.out.println("currend process" + readyQueue.getFirst().getName());
                curr ++ ;
                readyQueue.peekFirst().remainingTime -- ; 
                if(readyQueue.peekFirst().remainingTime == 0   )
                {
                    readyQueue.peekFirst().finishTime = curr ;
                    DieList.add(readyQueue.peekFirst()) ; 
                    readyQueue.peekFirst().setQuantum(0);
                    getQuantumHis();
                    deleteProceess(readyQueue.peekFirst()) ; 
                    readyQueue.removeFirst() ; 
                }

                if(readyQueue.isEmpty() && !processes.isEmpty())
                {
                        index =  switching(processes.get(0));
                        readyQueue.addFirst(processes.get(index)) ; 
                        readyQueue.peekFirst().startTime = curr  ;
                        break ; 
                }
                if (checkFinish())
                {
                    break ; 
                }
            
                // check if the current burst time == ceil of 50 of total burst time
                if(counter >= (int) Math.ceil((float)readyQueue.peekFirst().getQuantum()/(float)2))
                {
                    // check if there are any process that has AG_factor less than the current process
                    if(checKswitching(readyQueue.peekFirst()))
                    {
                        // senario one if process used all its quantum time
                        if(counter == readyQueue.peekFirst().getQuantum())
                        {
                            senario1(readyQueue.peekFirst());
                            getQuantumHis();
                        }
                        // senario two if the process don't use all of it's quantum
                        else if(counter < readyQueue.peekFirst().getQuantum())
                        {
                            senario2(readyQueue.peekFirst(), counter);
                            getQuantumHis();
                        }
                        index = switching(readyQueue.peekFirst());
                 
                        // in case if the process not in ready queue unitil yet
                        // add the new process to the queue
                        if(!searchQueue(readyQueue, processes.get(index)))
                        {
                            Process temp  = readyQueue.peekFirst() ;
                            readyQueue.removeFirst() ; 
                            readyQueue.addFirst(processes.get(index)) ;
                            readyQueue.addLast(temp);
                          //  timeline.add(readyQueue.peekFirst().getName());
                            readyQueue.peekFirst().startTime = curr ;
                            break;
                        }

                        // in case the process in the ready qeueu ; 
                        else if (searchQueue(readyQueue, processes.get(index)))
                        {
        
                            Process temp = searchLeastAGQueue(readyQueue) ; 
                            readyQueue.remove(temp);
                            readyQueue.addLast(readyQueue.peekFirst());
                            readyQueue.removeFirst(); 
                            readyQueue.addFirst(temp); 

//                            timeline.add(readyQueue.peekFirst().getName());
                            break;
                        }
                                                           
                    }

                    else if(!checKswitching(readyQueue.peekFirst()) && counter == readyQueue.peekFirst().getQuantum())
                    {
                        //first we will implelment senario one 
                        senario1(readyQueue.peekFirst());
                        getQuantumHis();
                        // tehn add the current process to the end of the queue
                        readyQueue.addLast(readyQueue.peekFirst());
                        readyQueue.removeFirst(); 
                       // timeline.add(readyQueue.peekFirst().getName());
                        break;
                    }
                }
                if(checkFinish())
                {
                    break ; 
                }
            }
            if(checkFinish())
            {
                    break ; 
            }
        }
    
} 

    public void getResults()
    {
        System.out.println("Time Line IS : " + timeline);
        for (Process p : DieList){
            p.setTurnArroundTime(p.finishTime - p.getArrivalTime());
            p.SetWaitingTime(p.getTurnArroundTime() - p.getBurst());

        }

        System.out.println("*************************************");
        System.out.println("Name      Start time     Finish time");
    
        for (Process process : DieList) {
            String formattedOutput = String.format("%-10s %-14d %-11d",
                    process.getName(), process.startTime, process.finishTime);
            System.out.println(formattedOutput);
        }

        stats();
    }

    private void stats()
    {
        float avg_w = 0 ;
        float avg_t = 0; 
        
        for(Process e : DieList)
        {
            System.out.println("Proccess Name : " + e.getName());
            System.out.println("Burst Time " + e.getBurst());
            System.out.println("process wating time : " +  e.getWaitingTime());
            System.out.println("process turnarround time : "+ e.getTurnArroundTime());
            System.out.println("*************************");
            avg_w += e.getWaitingTime() ;
            avg_t += e.getTurnArroundTime();
        }
        avg_w = avg_w/DieList.size();
        avg_t = avg_t/DieList.size();
        System.out.println("Average Waiting time : " + avg_w);
        System.out.println("Average Trun Around time : " + avg_t);
    }

    void getQuantumHis()
    {
        System.out.print(" History of quantum =>( ");
        for(Process e : processes)
        {
            System.out.print(e.getQuantum() +  "  ");
        }
        System.out.println(")");

    }


}

