
/**A class that is used to represent an Edge in a Graph of Nodes
 * @author kim & Davor
 */
public class Edge 
{
	private int distance;
	private Node node;
	
	/**A Standard Constructor for Edge that takes two parameters
	 * and gives the Edge an initial value
	 * Time Complexity: O(1), we assign our variable with the params we provide.
	 * @param n, node it reaches
	 * @param d, distance of the Edge
	 */
	public Edge(Node n, int d)
	{
		node = n;
		distance = d;
	}
	
	/**A Setter for Distance variable
	 * Time Complexity: O(1)
	 * @param d, the new distance
	 */
	public void setDistance(int d)
	{
		distance = d;
	}
	/**
	 * A Getter for distance variable
	 * Time Complexity: O(1)
	 * @return distance, the length of the Edge
	 */
	public int getDistance()
	{
		return distance;
	}
	
	/**A Getter for the Node variable
	 * Time Complexity: O(1)
	 * @return node, the node our Edge points to.
	 */
	public Node getNode()
	{
		return node;
	}
}
