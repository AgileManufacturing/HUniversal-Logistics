
import java.util.ArrayList;
import java.util.Timer;

public class calculate_caller {

	public static void main(String[] args) {
		
		/*
		 * 00 01 02 03
		 * 04 05 06 07
		 * 08 09 10 11
		 * 12 13 14 15
		 */
		
		calculate c = new calculate();
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		int[] nodes_to_visit = {0,1,3};
		
		int start_direction = 90; // direction the transport unit is facing (in degrees)
		int current_timeframe = 0;
		int number_of_paths = 3; 
		int deadline = 15; // The maximum number of frames that a path is allowed to have
		
		long start_calc = System.currentTimeMillis();
		list = c.calculatePath(nodes_to_visit, start_direction, current_timeframe, number_of_paths, deadline);
		long end_calc = System.currentTimeMillis();
		
		System.out.print("Nodes to visit: ");
		
		for (int node: nodes_to_visit){
			System.out.print(node+" ");
		}
		
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
