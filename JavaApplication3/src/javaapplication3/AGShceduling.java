package javaapplication3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Deque;


public class AGShceduling {
    private ArrayList<Process> processes;
  //  private ArrayList<Process> readyQueue = new ArrayList<Process>() ;
    Deque<Process> readyQueue = new LinkedList<>();
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


    private static boolean searchQueue(Deque<Process> queue, Process p ) {
        for (Process element : queue) {
            if (element.getName() == p.getName()) {
                return true; 
            }
        }
        return false; // Element not found
    }
      
    private  Process searchLeastAGQueue(Deque<Process> queue) {
        Process temp = queue.getFirst();
        for (Process element : queue) {
            if (temp.getAG() < element.getAG()) {
                temp = element ;
            }
        }
        return temp; // Element not found
    }


    public void sheduling()
    {
       
        generateAGFactor();
        int index = 0 ;
        int counter = 1 ;
        // when starting the process ; 
        index = getLeastArrivalTime() ;
        readyQueue.addFirst(processes.get(index)) ; 
        readyQueue.peekFirst().startTime = curr  ;
        while(true)
        {
            for( ; counter <=readyQueue.peekFirst().getQuantum() ; counter ++)
            {
                curr ++ ;
                readyQueue.peekFirst().remainingTime -- ; 
                if(readyQueue.peekFirst().remainingTime == 0 )
                {
                    readyQueue.peekFirst().finishTime = curr ;
                    readyQueue.peekFirst().setBurst(0);
                    deleteProceess(readyQueue.peekFirst());
                    readyQueue.removeFirst() ; 
                }


                // check if the currend burst time == ceil of 50 of total burst time
                if(counter >= Math.ceil(readyQueue.peekFirst().getBurst()/2))
                {
                    
                    // check if there are any process that has AG_factor less than the current process
                    AtomicInteger INDEX = new AtomicInteger(index) ;
                    if(switching(readyQueue.peekFirst(), INDEX))
                    {
                        // senario one if process used all its quantum time
                        if(counter == readyQueue.peekFirst().getQuantum())
                        {
                            senario1(readyQueue.peekFirst());
                        }
                        // senario two if the process don't use all of it's quantum
                        else if(counter < readyQueue.peekFirst().getQuantum())
                        {
                            senario2(readyQueue.peekFirst(), counter);
                        }
                        index = INDEX.get() ;

                        // in case if the process not in ready queue unitil yet
                        // add the new process to the queue
                        if(!searchQueue(readyQueue, processes.get(index)))
                        {
                            Process temp  = readyQueue.peekFirst() ;
                            readyQueue.removeFirst() ; 
                            readyQueue.addFirst(processes.get(index)) ;
                            readyQueue.addLast(temp);
                        }

                        // in case the process in the ready qeueu ; 
                        else if (searchQueue(readyQueue, processes.get(index)))
                        {
                            Process temp = searchLeastAGQueue(readyQueue) ; 
                            readyQueue.remove(temp);
                            readyQueue.addLast(readyQueue.getFirst());
                            readyQueue.removeFirst(); 
                            readyQueue.addFirst(temp); 
                        }

                       
                    }
                }
            }

        }

    }


}
