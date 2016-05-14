import java.util.List;

/**A class with a function that calculates the shortest path between two given nodes
 * in a Graph, using Dijkstras Algorithm.
 * @author Kim & Davor
 */
public class Dijkstras 
{
	private Node current;
	
	/**A method that performs our implementation of Dijkstras Algorithm 
	 * It is implemented through the use of a priorityQueue of edges, edges and nodes.
	 * The concept is to add all edges from the node we're currently in to the queue, set the node to visited and then
	 * take the edge with the lowest priority, until we reach our destination.
	 * Time Complexity: For each edge we call on insert which is done in O(n) time, but since that's the worst case complexity (which only happens
	 * when the array in the PriorityQueue needs to be doubled, we use the ammorted time complexity of insert which is O(log n) and for each node
	 * we use findMin() and deleteMin(), which is done in O(log n) we get a time complexity of O((e+n)log n) where e = number of edges and n = 
	 * number of nodes. We also have a small initiation before the algorithm itself starts, but since it's smaller than O((e+n)log n) we can 
	 * ignore it (it = O(2*n) -> O(n))
	 * Total Time complexity = O((e+n)log n)
	 * @param i, the initial node we want to start on.
	 * @param n, a list of all the nodes.
	 * @param f, the final node, whose quickest way we want to find
	 * @return, returns the final node because it contains all the information we need to calculate the path and the path length.
	 * @throws Exception, throws an exception if starting node or the destination doesn't exist
	 */
	public Node calcDijkstra(Node i, List<Node> n, Node f) throws Exception
	{
		//Check if the parameters are correct
		if(i == null || f == null)
		{
			throw new Exception("Please search for existing nodes within the graph");
		}
		PriorityQueue<Edge> edge = new PriorityQueue<Edge>(new cmpTo());
		//Sets all the nodes to unvisited and gives them a unrealistically high numbers so that they will be overwritten
		for(Node temp : n)
		{
			temp.setVisited(false);
			temp.setWeight(Integer.MAX_VALUE);
		}
		//Initiates the the starting node
		i.setWeight(0);
		Edge init = new Edge(i,0);
		edge.insert(init);
		//The loop that takes the lowest edge out of the priorityQueue
		while(!edge.isEmpty())
		{
			current = edge.findMin().getNode();
			edge.deleteMin();
			//checks if we've already been to the node, if so, we jump straight back and take the next node 
			if(current.getVisited())
			{
				continue;
			}
			current.setVisited(true);
			//checks if our current node is our destination
			if(current.getName().equals(f.getName()))
			{
				return f;
			}
			for(Edge e : current.getEdge())
			{
				if(!e.getNode().getVisited())
				{
					int tempInt = current.getWeight() + e.getDistance();
					if(tempInt < e.getNode().getWeight())
					{
						//updates the node
						e.getNode().setWeight(tempInt);
						e.getNode().setPrevNode(current);
						//creates a new Edge and adds it into the queue
						Edge temp = new Edge(e.getNode(),tempInt);
						edge.insert(temp);
					}
				}
			}
		}
		return null;
	}
}