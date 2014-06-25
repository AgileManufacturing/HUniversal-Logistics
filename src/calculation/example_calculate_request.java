
import java.util.ArrayList;
import java.util.Timer;
import java.util.ArrayList;
import java.util.Arrays;

public class example_calculate_request {

	public static void main(String[] args) {
		
		/* this class demonstrates how to perform a PathRequest */
		
		/* We assume the grid/structure looks like this
		 * 
		 * 00 01 02 03
		 * 04 05 06 07
		 * 08 09 10 11
		 * 12 13 14 15
		 */
		
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		//int[] nodes_to_visit = {4,0,1,3};
		
		int start_direction = 90; // direction the transport unit is facing (in degrees)
		int current_timeframe = 0; // We calculate in timeframes a timeframe is X amount of undertermined time. It is calculated from values from the 
									// transport units and the gateway
		int number_of_paths = 2; 
		
		PathRequestObject P= new PathRequestObject(start_direction, number_of_paths, current_timeframe);
		
		/* 
		 * We will be visiting the following nodes 4, 0, 1 //and 3
		 * */
		
		P.addNode(5, //starting node
				  9, //next node
				  0, //minimum starting time (clock time. not time it takes)
				  10, //maximum starting time
				  12, //minimum ending time
				  15); // maximum ending time
				  
		/* Now for the next node */
		
		P.addNode(9, //starting node
				  10, //next node
				  11, //minimum starting time (clock time. not time it takes)
				  13, //maximum starting time
				  19, //minimum ending time
				  23); // maximum ending time
		
		
		//int min_start_frame = 20;
		//int max_start_frame = 100;
		//int min_end_frame = 150;
		//int max_end_frame = 200;
		
		//int previous_node = -1; //give this value an impossible value
		
		//for (int node: nodes_to_visit){
		//	if (previous_node != -1){
		//		P.addNode(previous_node, node, min_start_frame, max_start_frame, min_end_frame, max_end_frame);
		//	}
		//	previous_node = node;
			
		//	min_start_frame -= 4;
		//	max_start_frame -= 10;
		//	min_end_frame -= 15;
		//	max_end_frame -= 20;
		//}
		
		
		
		
		
		long start_calc = System.currentTimeMillis(); //just some time measurement for debugging purposes
		list = P.calculatePaths();
		long end_calc = System.currentTimeMillis();
		
		
		/* paths are now calculated for us. Let's print them shall we? */
		//System.out.print("Nodes to visit: ");
		//for (int n: nodes_to_visit){
		//	System.out.print(n+" ");
		//}
		
		System.out.println();
		System.out.println("Paths:");
		
		for (int b = 0; b < list.size(); b++){
			path = list.get(b);
			for (int a=0; a<path.size(); a++) {
				System.out.print(path.get(a));
			}
			
			System.out.println();
		}
		System.out.println("This took " + (end_calc-start_calc) + "ms");
	}
}
