package javaapplication3;
import java.util.PriorityQueue;


class Process {
    private String name ; 
    private int arrivalTime ;
    private int burst ; 
    private int priority ;
    private int WaitingTime;
	private int TurnaroundTime;

    Process(){
        this.arrivalTime = this.burst = this.priority = 0 ; 

    }  
    Process(String name , int arrivalTime , int burst ){
        this.name = name ;
        this.arrivalTime = arrivalTime ; 
        this.burst = burst ;
    }

    Process(String name , int arrivalTime , int burst , int priority ){
        this.name = name ;
        this.arrivalTime = arrivalTime ; 
        this.burst = burst ;
        this.priority = priority ; 
    }

    void setName(String name)
    {
        this.name = name ;
    }
    String getName()
    {
        return name ;
    }

    void setArrivalTime(int arrivalTime)
    {
        this.arrivalTime = arrivalTime ;
    }
    int getArrivalTime()
    {
        return arrivalTime ; 
    }

    void setBurst(int burst)
    {
        this.burst = burst ;
    }
    int getBurst()
    {
        return burst ;
    }
    
    void setPriorty(int priority)
    {
        this.priority = priority ; 
    }
    int getPriorty()
    {
        return priority ;
    } 

    void SetWaitingTime(int WaitingTime)
    {
        this.WaitingTime = WaitingTime ;
    }
    int getWaitingTime()
    {
        return WaitingTime ; 
    }

    void setTurnArroundTime(int TurnaroundTime)
    {
        this.TurnaroundTime = TurnaroundTime ;
    }
    int getTurnArroundTime()
    {
        return TurnaroundTime ; 
    }
    
    
}
