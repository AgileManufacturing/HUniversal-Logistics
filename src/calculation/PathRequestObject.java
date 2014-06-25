
import java.util.ArrayList;
import java.util.Timer;
import java.util.Arrays;

public class PathRequestObject {


	private ArrayList<Integer[]> _data;
	private int _start_direction;
	private int _number_of_paths;
	private int _current_timeframe;

	public PathRequestObject(int start_direction, int number_of_paths, int current_timeframe){
	
		_data = new ArrayList<Integer[]>();
		_start_direction = start_direction;
		_number_of_paths = number_of_paths;
		_current_timeframe = current_timeframe;
	
	}
	
	public void addNode(int start_node_id, 
						int end_node_id, 
						int min_start_frame, 
						int max_start_frame,
						int min_end_frame, 
						int max_end_frame){
											
		_data.add(new Integer[] {start_node_id,
								 end_node_id,
								 min_start_frame,
								 max_start_frame,
								 min_end_frame,
								 max_end_frame});
	}
	
	public void removeNode(int node_location){
		_data.remove(node_location);
	}
	
	
	public ArrayList<ArrayList<Integer>> calculatePaths(){
		calculate c = new calculate();
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> path = new ArrayList<Integer>();
		
		//System.out.println(_data.size());
		list = c.calculatePath(_data, _start_direction, _number_of_paths, _current_timeframe);
		
		//list = c.calculatePath(nodes_to_visit, start_direction, current_timeframe, number_of_paths, deadline);
		return list;
		//return null;
	}
	
}
