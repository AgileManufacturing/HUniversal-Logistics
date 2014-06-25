
import java.util.ArrayList;

public class calculate{
	
	/* system variables */
	int lenght;
	int width;
	int current_frame;
	int[] grid;
	int max;
	
	/* transport unit variables */
	int frames_forward;
	int frames_left;
	int frames_right;
	int frames_back;
	int current_direction;
	
	ArrayList<ArrayList<Integer>> data;
	
	calculate(){
		
		/* 
		 * No access to a database yet......
		 * Hard coded values below :(
		 * Replace them later
		 */
	 
		lenght = 4;
		width = 4;
		max = lenght*width;
	 
		frames_forward = 1;
		frames_left = 2;
		frames_right = 2;
		frames_back = 3;
		
		/*  something like:
		 *  transport_id = database.getTransportUnitID(startposition);
		 * 	frames_forward = database.getForwardFrames(transport_id);
		 * 	etc
		 * 
		 *  or put it all in a "transport unit" class/struct
		 */
	}
	
	private int calculateDirection(int start, int end){
		
		/*
		 * Requires width to have a value.
		 * Determines the direction from X to Y 

		 * This function assumes the grid looks similar to this (not the size):
		 * 
			0 1 2
			3 4 5
			6 7 8
		 * 
		 */ 
		 
		if (start - end == width){
			return 0; // north
		} else if (end - start == 1){
			return 90; // east
		} else if (end - start == width){
			return 180; // south
		} else if (start - end == 1){
			return 270; // west
		} 
		
		return 0;
	}
	
	public int calculateTimeframes(int startdirection, int start, int end){
	
		/* 
		 * Only use this function for neighboring nodes.
		 * Calculates the Time frames a transport unit requires to get from it's current node to its neighbor.
		 * 
		 * It performs the following steps
		   - Gets the direction the transport unit is facing. 
		   - Asks wich direction is needed to get from point X to point Y.
		   - Compares the directions and determines how the transport unit should turn.
		   - returns the corresponding number of time frames required for that.
		 */ 
		
		int enddirection = calculateDirection(start, end);
		
		if (startdirection - enddirection == 0){	
			return frames_forward;
		} else if (Math.abs(startdirection - enddirection) == 180){ 
			return frames_back;
		} else if ((startdirection - enddirection == -270) || (startdirection - enddirection == 90)){
			return frames_left;
		} else if ((startdirection - enddirection == 270) || (startdirection - enddirection == -90)){
			return frames_right;
		}
		return 0;
	}
	
	
	private int calculateDistance(int start, int end){
		
		/*
		 * Calculates x and y distances between 2 nodes. That's it.
		 */
		
		int temp_t = end+1;
		int target_rownr = 1;
		while (temp_t > width){
			temp_t -= width;
			target_rownr++;
		}
		
		int temp_n = start+1;
		int neighbor_rownr = 1;
		while (temp_n > width){
			temp_n -= width;
			neighbor_rownr++;
		}

		int distance_to_check = Math.abs(temp_n-temp_t) + Math.abs(neighbor_rownr - target_rownr);
		return distance_to_check;
	}
	
	private boolean nodeWillNotBeUsed(int frame, int node, int future_frames){
	
		/*
		 * Checks if a node will be used in any of the given future timeframes
		 * Does only check some custom generated data at the moment because there is no database yet.
		 */
		 
		for (int i =1; i <= future_frames; i++){
			ArrayList<Integer> temp = data.get(frame+i);
			if (temp.size() > 0){ 
				if (temp.get(node) != max+1){
					return false;
				}
			}
			
		}
		return true;
	}
	
	
	
	ArrayList<ArrayList<Integer>> calculatePath(ArrayList<Integer[]> node_data, int direction, 
			int path_number, int current_timeframe){

		/* Calculates the path from start to end. 
		 * Needs the direction the transport unit is facing.
		 * Gets the transport unit from start position
		 * 
		   - Create a list of nodes. Used to keep track if a node has been visited. 

		 * (Based uphon A* algoritm)
		   - Start loop
		   - Check the neighbors of the current node. 
		   - Select only the neighbors who have not been visited and who are avaible.
		   - Get from the selection the neighbor that is closest to the goal (in terms of x/y distances)
			 if 2 neighbors have the same distance then choose the one that takes the least time (frames)
		   - Mark current node as visited. 
		   - Go to the chosen neighbor. 
		   - Check if destination is reached.
		   - If not. The chosen neighbor is now the current node.
		   - Add said node to a list.
		   - Go to start loop.
		   - 
		   - If no neighbors where found then we have hit a dead end or the destination is unreachable.
		     The function will then go back to the previous node and re-check. This will be repeated
		     until a suitable node is found or the list of previous nodes is empty.
		 */

		
		/* vars to be "replaced" as soon as the gateway and the database are finished */
		
		/* the calulator needs a grid/structure to work with */
		int grid[] = { 0, 0, 0, 0,
					   0, 0, 0, 0, /* nodes who are 0 can be visited. */  
					   0, 0, 0, 0, /* other values will be considered unaccessable */ 
					   0, 0, 0, 0 };	
		
					/*
					  00 01 02 03
					  04 05 06 07
					  08 09 10 11
					  12 13 14 15
					*/
				
		/* There is no database with planned routes yet so we substitute our own. */			    
		data = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> data_line;
		for (int f=0; f<100; f++){
			data_line = new ArrayList<Integer>();
			for (int n=0; n<20; n++){
				data_line.add(max+1);
				

				
			}
			data.add(data_line);
		} 
		
		
					   			
		/* end replacement */		   
					   
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>(); // this list will be filled with paths at the end.
		
		
		for (int h = 0; h < path_number; h++){

			ArrayList<Integer> path = new ArrayList<Integer>();
			current_frame = current_timeframe; 
			int previous_node = max;
			int current_direction = direction;
			
			assemble_path:
			for (int d = 0; d < node_data.size(); d++){
				
				
				/* vars used during runtime */

				Integer[] current_node_data = node_data.get(d);
				
				int start = current_node_data[0];
				int end = current_node_data[1];
				System.out.println( "start: "+start);
				System.out.println( "end: "+end);
				int min_start_frame = current_node_data[2]; // if x is only allowed to start from time y
				int max_start_frame = current_node_data[3]; // if x is only allowed to start before time z
				int min_end_frame = current_node_data[4]; // if x is only allowed to end afther time w
				int deadline = current_node_data[5]; // if x is only allowed to end before time v
				
				int visited[] = new int[max];
				int current_node = start;
				int next_node = current_node;
				int wait_frame = 0; 

				/* Copy the grid in the visited list. This list will be updated & checked */
				for (int j=0; j<max; j++) {
					visited[j]=grid[j];
				}
				
				/* If the transport unit is not allowed to move yet, update the path the fit the waiting time */
				
				while (current_frame < min_start_frame){
					current_frame++;
					path.add(current_node);
					System.out.println("derp");
				}
				
				/* If the current frame is already past  */
				if (current_frame > max_start_frame){
					/* destination cannot be reached */
					System.out.println("Destination cannot be reached ");
					path.clear();
					break assemble_path;
				}
				
				while(true){
					
					ArrayList<Integer> neighbors = new ArrayList<Integer>();
					ArrayList<Integer> weight = new ArrayList<Integer>();

					int temp = current_node;
					int rownr = 0;
					while (!(temp < width)){
						temp -= width;
						rownr++;
					}
					
					if (rownr !=0){ //if not first row
						neighbors.add(current_node-width);
					}
					
					if (max > width+current_node){ //if not last row
						neighbors.add(width+current_node);
					}
					
					if (current_node != (width*(rownr+1))-1){ //if not end of row
						neighbors.add(current_node+1);
					}
					
					if (current_node != width*rownr){ //if not start of row
						neighbors.add(current_node-1);
					}

					int temp_distance = max;
					
					for (int g=0; g<neighbors.size(); g++) {
						
						int neighborint = neighbors.get(g);
						int neighbor_frames = calculateTimeframes(current_direction, current_node, neighborint);
						boolean check = nodeWillNotBeUsed(current_frame, neighborint, neighbor_frames);
						
						if (neighborint == end &&
							!nodeWillNotBeUsed(current_frame, current_node, min_end_frame)){
							check = false;
						}
						
						System.out.println("neighbor " + neighborint + " is found");
						
						if (visited[neighborint] == 0 && neighborint != current_node && check == true){
							System.out.println(neighborint + " is not visited");
							int distance_to_check = calculateDistance(end, neighborint);
							if (distance_to_check <= temp_distance){
								if (distance_to_check == temp_distance){
									int currently_planned_frames = calculateTimeframes(current_direction, current_node, next_node);
									if (neighbor_frames > currently_planned_frames){
										next_node = next_node;
									} else {
										next_node = neighborint;
									}
								} else {
									temp_distance = distance_to_check;
									next_node = neighborint;
								}
							}
						}
					}
					visited[current_node] = 1;

					if (visited[end] > 0){
						//Destination has been reached.
						System.out.println("current_frame: "+current_frame);
						//System.out.println("current_frame: "+current_frame);
						
						if (current_frame < min_end_frame){
							int second_to_last;
							if (path.size() > 0){
								second_to_last = path.get(path.size()-1);
							} else {
								second_to_last = start;
							}
							
							while (current_frame < min_end_frame){
									current_frame++;
									path.add(second_to_last);
							}
							
							
							//if (nodeWillNotBeUsed(current_frame, second_to_last, min_end_frame)){
							//	System.out.println("node is safe?");
							//	while (current_frame < min_end_frame){
							//		current_frame++;
							//		path.add(second_to_last);
							//	}
							//} else {
							//	System.out.println("node is not safe?");
							//	
							//	visited[end] = 0;
							//	visited[second_to_last] = 1;
							//	current_node = second_to_last;
							//	
							//}
							 

						}
						
						path.add(end);
						break;
					}
					
					if (next_node != current_node & current_frame < deadline){
						current_frame += calculateTimeframes(current_direction, current_node, next_node);
						int used_frames = calculateTimeframes(current_direction, current_node, next_node);
						
						for (int i = 0; i<used_frames; i++){
							path.add(current_node);
							previous_node = current_node;
						}

						current_direction = calculateDirection(current_node, next_node);
						current_node = next_node;
						
						System.out.println("going to " + next_node);
						System.out.println("I'm now at frame " + current_frame);
						System.out.println("This action will use " + used_frames + " frames");
						System.out.println("I'm facing " + current_direction);

					} else {
						
						// When reaching this point it means that the function
						// has either hit a dead end and needs to go back
						// ór that it needs to wait a while before resuming
						// 
						// This part does not wait. It merely adds 1 timeframe to the path
						// and then goes back to the start of a loop to check for new openings.
						// 
						// If it has waited too long. It will go back to previously visited nodes 
						// check for different openings.
						
						wait_frame += 1;
						current_frame ++;
						path.add(current_node);
						System.out.println("Waiting on " + current_node + " with frame " +current_frame);
						
						if (current_frame >= deadline){
							
							for (int i = 0; i<wait_frame+1; i++){
								path.remove(path.size() - 1);
								current_frame --;
							}
							
							wait_frame = 0;

							if (path.size() > 0 && previous_node != path.get(path.size()-1)) {
								System.out.println("I've waited long enough. We have to go back ");
								current_node = path.get(path.size()-1);
								next_node = current_node;
								path.remove(path.size() - 1);
								//current_frame--;
								
							} else {
								// destination cannot be reached
								System.out.println("Destination cannot be reached ");
								path.clear();
								break assemble_path;
							}
							System.out.println("I'm going back to " + current_node);
						}
					}
				}
			}
			
			if (path.size() > 0){
				list.add(path);
			}
			
			data_line = new ArrayList<Integer>();
			
			// The earlier calcualted path gets put into the memory and will 
			// be taken into account when calculating a different path.
			for (int j = 0; j < path.size(); j++){
				int node = path.get(j);
				int frame = j+current_timeframe;

				ArrayList<Integer> temp_list = data.get(frame);
				temp_list.set(node, node);
				data.set(frame, temp_list);
			}
			
			for (int derp = 0; derp < data.size(); derp++){
				ArrayList<Integer> temp_list = data.get(derp);
				for (int herp = 0; herp < temp_list.size(); herp++){
					System.out.print(temp_list.get(herp)+" ");
				}
				System.out.println();
			}
			
			//ystem.out.println(
			
			
			
		}
		return list;
	}
}
