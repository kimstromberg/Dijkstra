import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import Lab3Help.BLineTable;
import Lab3Help.BStop;
import Lab3Help.Lab3File;

/**A class that can calculate the shorest path from one node to another,
 * and also calculate the length and the path it takes.
 * The class implements the interface Path.
 * @author Kim & Davor
 */
public class MyPath implements Path
{
	private List<String> pathNodes;
	private HashMap<String, Node> hashTabel;
	private List<Node> listNodes;
	private Node destination;
	private Dijkstras dij = new Dijkstras();
	 
	/**A constructor that reads in two file names, converts them into readable files
	 * and calls on our help function (that initiates our Graph(the nodes and edges)).
	  * Time Complexity: Since we haven't written the class Lab3File, and are unaware
	  * of it's constructors and it's methods readStops and readLines time complexity
	  * we are unable to provide a adequate answer. But we could guess that it's 
	  * somewhere in the vicinity of O(1) + O(f(n)) + O(f(n))
	  * which would mean that the total time complexity of MyPaths constructor would  be:
	  * O(n) + O(t*s) + 2*O(f(n))
	  * @param linesNames, the name of the Busline.
	  * @param stopNames, the name of the Busstop.
	  */
	public MyPath(String linesNames, String stopNames)
	{
		Lab3File lab3file = new Lab3File();
		List<BStop> stops = lab3file.readStops(stopNames);
		List<BLineTable> lines = lab3file.readLines(linesNames);
		initGraph(stops,lines);
	}
	 
	/**
	  * A constructor that sends the given BStop and BLineTable
	  * to our help function (that initiates our Graph(the nodes and edges)).
	  * Time Complexity: O(n) + O(t*s), see the class Graph for explanation
	  * @param Bstop, a list of Busstop.
	  * @param BLineTable, a list of a Bus line table.
	  */
	public MyPath(List<BStop> Bstop, List<BLineTable> BLineTable)
	{
		initGraph(Bstop,BLineTable);
	}
	
	/** A help function that converts a list of Bstop and a List of BLineTable into nodes
	 * sets our empty list listNodes into a list of all the nodes (complete with Edges) that
	 * can be created out of the BStops and BLineTables. Also creates a hashmap (containing the same
	 * objects)
	 * Time Complexity = O(n) + O(t*s), see the class Graph for explanation
	 * @param Bstop, a list of Bstops
	 * @param BLineTable, a list of BLineTables
	 */
	public void initGraph(List<BStop> Bstop, List<BLineTable> BLineTable)
	{
		Graph graph = new Graph(Bstop, BLineTable);
		graph.init();
		listNodes = graph.getNodes();
		hashTabel = graph.getHashMap();
	}
	
	/**
	 * A function that calculates the length from one node to another.
	 * Since the information exists in the final node 
	 * after we performed the algorithm, we update the final node with the calculated path. 
	 * Time Complexity: O(dijkstras) + O(calcPath) == O((e+n)log n) + O(n)
	 * Total Time Complexity: O((e+n)log n) + O(n)
	 * @throws Exception, if one of/or both of the parameters are invalid an exception will be thrown 
	 * @param from, the name of the Node we want to start from
	 * @param to, the name of the Node we want to reach
	  */
	public void computePath(String from, String to) throws Exception 
	{
		destination = dij.calcDijkstra(hashTabel.get(from), listNodes, hashTabel.get(to));
		if(destination == null)
		{
			pathNodes = new ArrayList<String>();
		}
		else
		{
			calcPath(from, destination);
		}
	}

	
	/**A function that returns an iterator that contains the
	 * path from starting node to destination.
	 *  Time Complexity: O(f(n)) [where n = length of our List<String>] which we are unaware of,
	 *  we guess it's O(n), but not having seen the code for iterator it's 
	 *  a pretty sketchy guess.
	 * @return pathNodes.iterator is an iterator that can print out
	 * our path taken from the starting node to the destination.
	 */
	public Iterator<String> getPath() 
	{
		return pathNodes.iterator();
	}
	
	/** A function that given a destination calculates the path we've taken
	 * from the starting node to reach it and adds them all to a List<String>
	 * Time Complexity: O(n+n), n is the number of nodes we've had to go though
	 * and the other n is if the List has to expand -> O(n)
	 * @param from, the name of the node we came from
	 * @param to, the Node which is our destination
	 */
	private void calcPath(String from, Node to)
	{
		pathNodes = new ArrayList<String>();
		Node temp = to;
		while(!(temp.getName().equals(from)))
		{
			pathNodes.add(0,temp.getName());
			temp = temp.getPrevNode();
		}
		pathNodes.add(0,from);
	}
	
	/**
	 * A "sort of" getter function that returns the shortest path from a
	 * calculated path between a start node and a destination.
	 * Observe: Can only be used after using computePath
	 * Time Complexity: O(1)
	 * @return either the time it takes to get to the destination
	 * or -1 (if the destination couldn't be reached for some reason)
	 */
	public int getPathLength() 
	{
		if (destination != null)
		{
			return destination.getWeight();
		}
		else
		{
			return -1;
		}
	}
}
