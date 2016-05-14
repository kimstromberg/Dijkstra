import java.util.ArrayList;
import java.util.List;

/**A class used to represent a Node in a Graph
 * A node has the following variables:
 * 		* A string name which is used as ID for the node
 * 		* int Weight which is used as cost for the node (cost to reach)
 * 		* boolean visited which can be used as a check for if we've been 
 * to the node already (when one has been to a node it means we've already found the
 * shortest possible way to reach it).
 * 		* List<Edge> edge which is a list of edges that the Node has (all the possible
 * ways one can move away from the node)
 * 		* Node prevNode which is the node one used to get here from
 * @author kim & Davor
 */
public class Node
{
	private String name;
	private int weight;
	private boolean visited;
	private List<Edge> edge;
	private Node prevNode;
	
	/**A basic constructor for Node, it gives the Node a name
	 * and creates an empty list of Edges which later (if the Node actually has edges)
	 * can be filled.
	 * 
	 * @param n, the name of our Node.
	 */
	public Node(String n)
	{
		name = n;
		edge = new ArrayList<Edge>();
	}
	
	/**A getter for the variable Name
	 * Time Complexity: O(1)
	 * @return name, the name of the Node
	 */
	public String getName()
	{
		return name;
	}
	
	/**A getter for the variable weight
	 * Time Complexity: O(1)
	 * @return weight, the cost it takes to reach the node 
	 * from the starting node
	 */
	public int getWeight()
	{
		return weight;
	}
	
	/**A setter for the variable weight
	 * Time Complexity: O(1)
	 * @param w, the cost it takes to reach the node from the
	 * starting node
	 */
	public void setWeight(int w)
	{
		weight = w;
	}
	
	/**A getter for the variable visited
	 * Time Complexity: O(1)
	 * @return visited, a boolean whether or not it has been visited
	 */
	public boolean getVisited()
	{
		return visited;
	}
	
	/**A setter for the variable visited
	 * Time Complexity: O(1)
	 * @param b, a boolean whether or not it has been visited
	 */
	public void setVisited(boolean b)
	{
		visited = b;
	}
	
	/**A getter for the List of edges
	 * Time Complexity: O(1)
	 * @return edge, the list of edges the node has
	 */
	public List<Edge> getEdge()
	{
		return edge;
	}
	
	/**A setter for the list of edges
	 * Time Complexity: O(1)
	 * @param e, the edge we want to add to the Node
	 */
	public void setEdge(Edge e)
	{
			edge.add(e);
	}
	
	/**A getter for the variable prevNode
	 * Time Complexity: O(1)
	 * @return prevNode, the node we came from
	 * OBS:Used when recursively finding the path you've
	 * had to travelled to reach the destination
	 */
	public Node getPrevNode()
	{
		return prevNode;
	}
	
	/**A setter for the variable prevNode
	 * Time Complexity: O(1)
	 * @param n, the node we want to set as the
	 * node we previously came from
	 */
	public void setPrevNode(Node n)
	{
		prevNode = n;
	}
}
