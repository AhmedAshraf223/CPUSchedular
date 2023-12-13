package javaapplication3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;



public class AGShceduling {
    private ArrayList<Process> processes;
  //  private ArrayList<Process> readyQueue = new ArrayList<Process>() ;
    Queue<Process> readyQueue = new LinkedList<>();
    private int curr =  0 ;
    private ArrayList<String> timeline = new ArrayList<>() ;

    AGShceduling(ArrayList<Process> processes)
    {
        this.processes = processes ;
    }

    // a private function to generate AG factor
    
    private void generateAGFactor()
    {
        for(Process e : processes)
        {
            Random rand = new Random();
            int RF = rand.nextInt(21);

            if(RF  < 10 )
            {
                e.setAG(RF + e.getArrivalTime() + e.getBurst()) ;
            }
            else if (RF > 10 )
            {
                e.setAG(10 + e.getArrivalTime() + e.getBurst());
            }
            else 
            {
                e.setAG(e.getPriorty() + e.getArrivalTime() + e.getBurst());
            }
        }
    }

    // a function to calculate the mean of quantums ;
    private float meanOfQuantum()
    {
        float avg = 0 ;
        for(Process e : processes)
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
        for(Process e : processes)
        {
            if(e.getBurst() == 0)
            {
                flag = true ;
            }
            else {
                flag = false ;
                break ;
            }
           
        }
        return flag ;
    }

    // a process to check if there are any process has ag factor less than the current process ;
    // we pass an int as refrencess to get the index of the smalles ag factor ; 
    private boolean switching(Process p , AtomicInteger index)
    {
        int minAGFactor = p.getAG() ;
        int ind = 0 ; 
        boolean flag = false ;
        for(int i = 0 ; i < processes.size() ; i ++)
        {
            if(processes.get(i).getArrivalTime() <= curr && processes.get(i).getAG() < minAGFactor)
            {
                ind = i  ;
                minAGFactor = processes.get(ind).getAG() ; 
                flag = true ;
            }
            index.set(ind);
        }
        return flag ;
    }

    // senario 1 when the running process used it's all quantum time ;
    private void senario1(Process p)
    {
        float mean = meanOfQuantum() ;
        p.setQuantum((int)Math.ceil(10 % mean));
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

    public void sheduling()
    {
       
        generateAGFactor();
        int index = 0 ;
        int counter = 1 ;
        // when starting the process ; 
        index = getLeastArrivalTime() ;
        readyQueue.offer(processes.get(index)) ; 
        processes.get(index).startTime = curr ;
        while(true)
        {
            for( ; counter <=processes.get(index).getQuantum() ; counter ++)
            {
                curr ++ ;
                // check if the currend burst time == ceil of 50 of total burst time
                if(counter >= Math.ceil(processes.get(index).getQuantum() / 2))
                {
                    
                    // check if there are any process that has AG_factor less than the current process
                    AtomicInteger INDEX = new AtomicInteger(index) ;
                    if(switching(processes.get(index), INDEX))
                    {
                        index = INDEX.get() ;
                        processes.get(index).startTime = curr ;
                        counter = 0  ; 
                    }
                }
            }

        }

    }


}
