//package javaapplication3;
import java.util.ArrayList;
import java.util.Scanner;


public class main {
    static public void main(String[] args)
    {
        int n ; 
        
		Scanner input = new Scanner(System.in);
        System.out.println("Enter n of processes :");
		n = input.nextInt() ;
		
		mainLoop:
		while (true){
			System.out.println("1.SJF\n 2.SRTF\n 3.Priorty\n 4.AG\n 0.Exit\n");
			System.out.println("Choose the algorithm : ");
			int d = input.nextInt();
			
			switch (d) {
				case 1:
					ArrayList<Process> Processes = new ArrayList<Process>();
					inputP(n , Processes);
					int sw = 0;
					String c ;
					boolean wait = true ;
					System.out.println("Do yow want to add context switch value ? Y/N (Default is 0)");
					while (wait) {
						c = input.nextLine();
						if (c.equalsIgnoreCase("Y")) {
							System.out.println("Enter context switch value:");
							sw = input.nextInt();
							input.nextLine(); // Consume the newline character
							wait = false;
						} else if (c.equalsIgnoreCase("N")) {
							wait = false;
						}
					}
					
					SJF sjf = new SJF(Processes , sw );
					sjf.Scheduling();
					sjf.stat();
					break;
				case 2:
					SRTF srtf = new SRTF(Processes);
					srtf.Scheduling();
					srtf.stats();
					break;
				case 3:
					int q = 1;	
					for (Process p : Processes){
					System.out.println("p"+ q + " "+ "Enter Process priority :");
					p.setPriorty(input.nextInt());	
					q++;
					}	
					System.out.println("Enter aging value for starvation :");
					int ag= input.nextInt();
					for (Process x : Processes){
						x.setAging(ag);
					}
					PriortyScheduling pt =  new PriortyScheduling(Processes);
					pt.Scheduling();
					pt.stats();
					break;
				case 4:
					System.out.println("Enter Quantum Value :");
					int Quantum = input.nextInt();
					for (Process p :Processes){
						p.setQuantum(Quantum);
					}
					
					int qq = 1;	
					for (Process p : Processes){
					System.out.println("p"+ qq + " "+ "Enter Process priority :");
					p.setPriorty(input.nextInt());	
					qq++;
					}
					ArrayList<Process> Processes2 = new ArrayList<Process>();
					for (int i = 0 ; i <Processes.size() ; i++ ){
					Process p = new Process(Processes.get(i).getName() , Processes.get(i).getArrivalTime() , Processes.get(i).getBurst() , Processes.get(i).getPriorty() , Processes.get(i).getQuantum());
					Processes2.add(p);
					}
					
					AGShceduling ags =  new AGShceduling(Processes2);
					ags.sheduling();
					ags.getResults();
					break;
				case 0:
					break mainLoop;
			}
		

		static void inputP(int n ,ArrayList<Process> Processes ){
			Scanner input = new Scanner(System.in);
			for(int i = 0 ; i < n ;i++)
			{
				
				input = new Scanner(System.in);
				Process p = new Process();
				System.out.println( (i+1) + " Enter Process Name :");
				p.setName(input.nextLine());
				
				
				System.out.println("Enter Process Burst Time :");
				p.setBurst(input.nextInt());
			
				
				Processes.add(p);
			
		}
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
		//Process p1 = new Process("p1" , 0 , 17 , 4 , 4  ) ;
		//Process p2 = new Process("p2" , 3 , 6 , 9 , 4  ) ;
		//Process p3 = new Process("p3" , 4 , 10 , 2 , 4 ) ;
		//Process p4 = new Process("p4" , 29 , 4 , 8 , 4 ) ;
		//Processes.add(p1) ; Processes.add(p2) ; Processes.add(p3) ; Processes.add(p4) ; 
        //AGShceduling algo = new AGShceduling(Processes);
        //algo.sheduling(); 
		
		//algo.getResults();
		}
    }
}

