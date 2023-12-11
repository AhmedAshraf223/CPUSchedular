package javaapplication3;
import java.util.ArrayList;
import java.util.Scanner;


public class main {
    static public void main(String[] args)
    {
        int counter ; 
        ArrayList<Process> Processes = new ArrayList<Process>();
		Scanner input = new Scanner(System.in);
        System.out.println("Enter n of processes :");
		counter = input.nextInt() ;

		// System.out.println("Enter aging value :");
		//int aging = input.nextInt() ;

        for(int i = 0 ; i < counter ;i++)
		{
			input = new Scanner(System.in);
			Process p = new Process();
			System.out.println( (i+1) + " Enter Process Name :");
			p.setName(input.nextLine());
		
			System.out.println("Enter Process Burst Time :");
			p.setBurst(input.nextInt());

			System.out.println("Enter Process Arraival Time :");
			p.setArrivalTime(input.nextInt()) ;
			
			// System.out.println("Enter Process priority :");
			// p.setPriorty(input.nextInt()) ;
			// p.setAging(aging) ;
			Processes.add(p);	
		}
        SJF algo = new SJF(Processes, 0 );
        algo.Scheduling(); 
		
		algo.stat();

    }
}

