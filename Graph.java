import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Lab3Help.BLineTable;
import Lab3Help.BStop;

/**A help class that takes a list of BStop and BlineTable and converts them into nodes
 * and edges, which are added into a List and a HashMap.
 * the HashMap is used to being able to more efficiently adding edges to our nodes 
 * @author kim & Davor
 */
public class Graph 
{
	private List<Node> nodeList;
	private List<BStop> stops;
	private HashMap<String, Node> hashTabel;
	private List<BLineTable> bLineTable;
	
	/** A standard constructor that initiates the parameters
	 * Time Complexity: O(1)
	 * @param bstop, a list of bstops
	 * @param bLineTable2, a list of BLineTables
	 */
	public Graph(List<BStop> bstop, List<BLineTable> bLineTable2)
	{
		bLineTable = bLineTable2;
		this.stops = bstop;
		nodeList = new ArrayList<Node>();
		hashTabel = new HashMap<String, Node>();
	}
	
	/**A function that takes the information in the List of BStop and List of BLineTable
	 * and creates nodes of them, which are inserted into a hashMap (done simply for making the
	 * task of adding edges easier to nodes) and a list of Nodes
	 * Time Complexity: We assume that our hashmap has a perfect hashfunction, and choose to handle
	 * the task as if the nodes names are short. 
	 * The first for-loop has a time complexity of:
	 * O(n) (looking though all of the objects in stops) + O(n) (the array has to be expanded) + O(1) (putting an object into
	 * the hashmap) -> O(n+n+1) -> O(2n) ->O(n) 
	 * The second for-loop has a time complexity of: O(t*s), the getting and setting of the nodes is done in O(1) time, but we do this 
	 * s times for each t where s = number of BStops and t = the number of BLineTables.
	 * Meaning that the Total Time Complexity = O(n) + O(t*s)
	 */
	public void init()
	{
		for(int i = 0; i<stops.size();i++)
		{
			Node node = new Node(stops.get(i).getName());
			hashTabel.put(stops.get(i).getName(), node);
			nodeList.add(node);	
		}
		for(BLineTable t : bLineTable)
		{
			for(int i = 0; i<(t.getStops().length-1);i++)
			{
				Node destination = hashTabel.get(t.getStops()[i+1].getName());
				Node current = hashTabel.get(t.getStops()[i].getName());
				int time = t.getStops()[i+1].getTime();
				Edge e = new Edge(destination, time);
				hashTabel.get(current.getName()).setEdge(e); 
			}
		}
	}
	
	/**
	 * A getter that returns the list of Nodes
	 * Time Complexity: O(1)
	 * OBS: use only after init has been called, else it's
	 * pretty useless
	 * @return nodeList, a list of nodes
	 */
	public List<Node> getNodes()
	{
		return nodeList;
	}
	
	/**
	 * A getter that returns the HashMap of keys(the name of the nodes) and the nodes themselves
	 * Time Complexity: O(1)
	 * OBS: use only after init has been called, else it's
	 * pretty useless
	 * @return hashTabel, a HashMap of keys(the name of the node) and the nodes themselves
	 */
	public HashMap<String, Node> getHashMap()
	{
		return hashTabel;
	}
}
