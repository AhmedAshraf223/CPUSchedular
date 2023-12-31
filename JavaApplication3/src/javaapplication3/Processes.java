//package javaapplication3;
import java.util.PriorityQueue;


class Process {
    private String name ; 
    private int arrivalTime ;
    private int burst ; 
    private int priority ;
    private int WaitingTime;
	private int TurnaroundTime;
    public int remainingTime;
    public int startTime = -1;
    public int finishTime = 0;
    private int aging ;
    private int quantum ; 
    private int AG_factor ;

    Process(){
        this.aging = 0 ; 
    }  
    Process(String name , int arrivalTime , int burst ){
        this.name = name ;
        this.arrivalTime = arrivalTime ; 
        this.burst = burst ;
        this.remainingTime = burst;
    }

    Process(String name , int arrivalTime , int burst , int priority ){
        this.name = name ;
        this.arrivalTime = arrivalTime ; 
        this.burst = burst ;
        this.priority = priority ; 
        this.remainingTime = burst ; 
    }
    // the constructor will be used
    Process(String name , int arrivalTime , int burst , int priority , int quantum ){
        this.name = name ;
        this.arrivalTime = arrivalTime ; 
        this.burst = burst ;
        this.priority = priority ; 
        this.remainingTime = burst ; 
        this.quantum = quantum ;
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

    int getRemainingTime(){
        return remainingTime; 
    }

    void setRemainingTime(int remainingTime){
        this.remainingTime = remainingTime;
    }

    void setAging(int aging)
    {
        this.aging = aging ;
    }
    int getAging()
    {
        return this.aging ;
    }

    void solveStarvation()
    {
        priority -= aging ; 
    }


    public int compareTo(Process other) {
        // Compare based on the 'burst' attribute
        return Integer.compare(this.burst, other.burst);
    }

    public void setQuantum(int quantum)
    {
        this.quantum = quantum ;
    }

    public int getQuantum()
    {
        return this.quantum ; 
    }

    public void setAG(int AG_factor)
    {
        this.AG_factor = AG_factor;
    }

    public int getAG()
    {
        return this.AG_factor ; 
    }


}
    
    

