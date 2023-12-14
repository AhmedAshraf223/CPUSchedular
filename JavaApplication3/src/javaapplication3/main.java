//package javaapplication3;
import java.util.ArrayList;
import java.util.Scanner;


public class main {
    static public void main(String[] args)
    {
		System.out.println((int)Math.ceil(2.5));
        //int counter ; 
        ArrayList<Process> Processes = new ArrayList<Process>();
		// Scanner input = new Scanner(System.in);
        // System.out.println("Enter n of processes :");
		// counter = input.nextInt() ;

		// System.out.println("Enter Quantum value :");
		// int quantum = input.nextInt() ;

        // for(int i = 0 ; i < counter ;i++)
		// {
		// 	input = new Scanner(System.in);
		// 	Process p = new Process();
		// 	System.out.println( (i+1) + " Enter Process Name :");
		// 	p.setName(input.nextLine());
		
		// 	System.out.println("Enter Process Burst Time :");
		// 	p.setBurst(input.nextInt());

		// 	System.out.println("Enter Process Arraival Time :");
		// 	p.setArrivalTime(input.nextInt()) ;
			
		// 	System.out.println("Enter Process priority :");
		// 	p.setPriorty(input.nextInt()) ;
		// 	p.setQuantum(quantum);
		// 	p.setRemainingTime(p.getBurst());
		// 	Processes.add(p);	
		// }
		Process p1 = new Process("p1" , 0 , 17 , 4 , 4  ) ;
		Process p2 = new Process("p2" , 3 , 6 , 9 , 4  ) ;
		Process p3 = new Process("p3" , 4 , 10 , 2 , 4 ) ;
		Process p4 = new Process("p4" , 29 , 4 , 8 , 4 ) ;
		Processes.add(p1) ; Processes.add(p2) ; Processes.add(p3) ; Processes.add(p4) ; 
        AGShceduling algo = new AGShceduling(Processes);
        algo.sheduling(); 
		
		algo.getResults();

    }
}

