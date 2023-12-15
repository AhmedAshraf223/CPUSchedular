//package javaapplication3;
import java.util.ArrayList;
import java.util.Scanner;


public class main {
    static public void main(String[] args)
    {
        int counter ;
		int quantum ; 
		int contextSwitch;
         ArrayList<Process> Processes = new ArrayList<Process>();
		Scanner input = new Scanner(System.in);
        System.out.println("Enter n of processes :");
		counter = input.nextInt() ;
		System.out.println("Enter Quantum value :");
		quantum = input.nextInt() ;
		System.out.println("Enter contex switching time :");
		contextSwitch = input.nextInt() ;
		for(int i = 0 ; i < counter ;i++){
				input = new Scanner(System.in);
				// Process p = new Process();
				System.out.println( (i+1) + " Enter Process Name :");
				String name = input.nextLine();

				System.out.println("Enter Process Burst Time :");
				int burst = input.nextInt();
				//p.setBurst(input.nextInt());
				//p.remainingTime = p.getBurst();
				System.out.println("Enter Process Arraival Time :");
				int arrivalTime = input.nextInt();
			//	p.setArrivalTime(input.nextInt());
				
				System.out.println("Enter Process Priority :");
				int priority = input.nextInt();
			//	p.setPriorty(input.nextInt());
			//	p.setQuantum(quantum);
				Process p = new Process(name , arrivalTime , burst, priority , quantum ) ; 
				Processes.add(p);
			}


		while(true)
		{
			int chioce  ; 
			System.out.println("Enter your chioce :");
			System.out.println("1-SJF ");
			System.out.println("2-SRTF ");
			System.out.println("3-Priority Scheduling ");
			System.out.println("4-AG Scheduling ");
			System.out.println("0- Exit ");
			chioce = input.nextInt();

			if(chioce == 1 )
			{
				ArrayList<Process> SJFProcess = new ArrayList<>() ;
				SJFProcess.addAll(Processes);
				SJF aglo1 = new SJF(SJFProcess, contextSwitch);
				aglo1.Scheduling();
				aglo1.stat();
			}
			else if (chioce == 2)
			{
				ArrayList<Process> SRTFProcess = new ArrayList<>() ;
				SRTFProcess.addAll(Processes);
				SRTF algo2 = new SRTF(SRTFProcess);
				algo2.Scheduling();
				algo2.stats();
			}
			else if (chioce == 3 )
			{
				ArrayList<Process> PriorityProcess = new ArrayList<>() ;
				PriorityProcess.addAll(Processes);
				PriortyScheduling aglo3 = new PriortyScheduling(PriorityProcess);
				aglo3.Scheduling();
				aglo3.stats();
			}

			else if(chioce ==4)
			{
				ArrayList<Process> AGProcess = new ArrayList<>();
				AGProcess.addAll(Processes);
				AGShceduling algo4 = new AGShceduling(AGProcess); 
				algo4.sheduling();
				algo4.getResults();
			}
			else {
				break ;
			}
		}

		// mainLoop:
		// while (true){
		// 	System.out.println("1.SJF\n 2.SRTF\n 3.Priorty\n 4.AG\n 0.Exit\n");
		// 	System.out.println("Choose the algorithm : ");
		// 	int d = input.nextInt();
			
		// 	switch (d) {
		// 		case 1:
		// 			int sw = 0;
		// 			String c ;
		// 			boolean wait = true ;
		// 			ArrayList<Process> SJFProcess = new ArrayList<>();
		// 			SJFProcess.addAll(Processes) ; 
		// 			System.out.println("Do yow want to add context switch value ? Y/N (Default is 0)");
		// 			while (wait) {
		// 				c = input.nextLine();
		// 				if (c.equalsIgnoreCase("Y")) {
		// 					System.out.println("Enter context switch value:");
		// 					sw = input.nextInt();
		// 					input.nextLine(); // Consume the newline character
		// 					wait = false;
		// 				} else if (c.equalsIgnoreCase("N")) {
		// 					wait = false;
		// 				}
		// 			}
					
		// 			SJF sjf = new SJF(SJFProcess , sw );
		// 			sjf.Scheduling();
		// 			sjf.stat();
		// 			break;
		// 		case 2:
		// 			ArrayList<Process> SRTFProceess = new ArrayList<>() ;
		// 			SRTFProceess.addAll(Processes) ; 
		// 			SRTF srtf = new SRTF(SRTFProceess);
		// 			srtf.Scheduling();
		// 			srtf.stats();
		// 			break;
		// 		case 3:
		// 			int q = 1;	
		// 			ArrayList<Process> PriortyProcess = Processes;
		// 			for (Process p : PriortyProcess){
		// 				System.out.println("p"+ q + " "+ "Enter Process priority :");
		// 				p.setPriorty(input.nextInt());	
		// 				q++;
		// 			}	
		// 			System.out.println("Enter aging value for starvation :");
		// 			int ag= input.nextInt();
		// 			for (Process x : PriortyProcess){
		// 				x.setAging(ag);
		// 			}
		// 			PriortyScheduling pt =  new PriortyScheduling(PriortyProcess);
		// 			pt.Scheduling();
		// 			pt.stats();
		// 			break;
		// 		case 4:
		// 			// ArrayList<Process> AGProcess = new ArrayList<>() ;
		// 			// AGProcess.addAll(Processes);
		// 			System.out.println("Enter Quantum Value :");
		// 			int Quantum = input.nextInt();
		// 			for (Process p :AGProcess){
		// 				p.setQuantum(Quantum);
		// 			}
					
		// 			int qq = 1;	
		// 			for (Process p : AGProcess){
		// 			System.out.println("p"+ qq + " "+ "Enter Process priority :");
		// 			p.setPriorty(input.nextInt());	
		// 			qq++;
		// 			}
		// 			// ArrayList<Process> Processes2 = new ArrayList<Process>();
		// 			// for (int i = 0 ; i <Processes.size() ; i++ ){
		// 			// Process p = new Process(Processes.get(i).getName() , Processes.get(i).getArrivalTime() , Processes.get(i).getBurst() , Processes.get(i).getPriorty() , Processes.get(i).getQuantum());
		// 			// Processes2.add(p);
		// 			// }
					
		// 			AGShceduling ags =  new AGShceduling(AGProcess);
		// 			ags.sheduling();
		// 			ags.getResults();
		// 			 break;
		// 		case 0:
		// 			break mainLoop;
		// 	}
		
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
		// Process p1 = new Process("p1" , 0 , 17 , 4 , 4  ) ;
		// Process p2 = new Process("p2" , 3 , 6 , 9 , 4  ) ;
		// Process p3 = new Process("p3" , 4 , 10 , 2 , 4 ) ;
		// Process p4 = new Process("p4" , 29 , 4 , 8 , 4 ) ;
		// Processes.add(p1) ; Processes.add(p2) ; Processes.add(p3) ; Processes.add(p4) ; 
        // AGShceduling algo = new AGShceduling(Processes);
        // algo.sheduling(); 
		
		// algo.getResults();
		}
}


