import java.util.ArrayList;
import java.util.Comparator;

/**A class for a generic Priority Queue
 * @author Kim Strömberg & Davor Pejic
 */

public class PriorityQueue<E> 
{
	private Comparator<? super E> comp;
	private int currentSize = 0;
	private ArrayList<E> heap = new ArrayList<E>();
	
	/**A constructor for our Priority Queue which tells the queue
	 * how it should act.
	 * @param comp is a Comparator which tells the Queue if
	 * 1<2 should return 1 or -1
	 */
	public PriorityQueue(Comparator<? super E> comp)
	{
		this.comp = comp;
	}
	
	/**A method that inserts nodes to the binary heap
	 * Time Complexity: the worst case scenario is when add needs to double the size
	 * of our array, which is done in linear time.
	 * That means that our total time complexity for insert is O(1) + O(n) + O(log n) -> O(n)
	 * Time Complexity = O(n)
	 * @param e is the element we wish to add
	 */
	public void insert(E e)
	{
		heap.add(e);
		int node = currentSize++;
		bubbleUp(node);
	}
	
	/**A method that finds the minimum value of the priority queue.
	 * Time Complexity:O(1)
	 * @return it returns the minimum value of the Priority Queue
	 */
	public E findMin()
	{
		if (isEmpty())
		{
			return null;
		}
		return heap.get(0);
	}
	
	/**A method that deletes the minimum value,
	 * and restores the priority queue (binary heap) to a
	 * valid/stable state (leaving no holes and such)
	 * Time Complexity: O(1) + O(bubbleDown), it might have to go though the entire heap
	 * when bubbling => O(log n)
	 */
	public void deleteMin()
	{
		if(isEmpty())
		{
			throw new QueueException("The Binary Heap is already empty");
		}
		if(heap.size() > 1)
		{	
			heap.set(0,heap.get(--currentSize));
			heap.remove(currentSize);
			bubbleDown(0);
		}
		else
		{
			heap.remove(0);
			currentSize--;
		}
	}
	
	/**A method that checks if the heap is empty.
	 * Time Complexity: O(1), just sets a value
	 * @return a boolean, true = empty
	 */
	public boolean isEmpty()
	{
		return currentSize == 0;
	}
	
	/**A method that empties the queue.
	 * Time Complexity: O(1), just sets a value
	 */
	public void makeEmpty()
	{
		currentSize = 0;
	}
	
	/**A getter for the i:th element in the heap
	 * Time Complexity: O(1), since we already have the index we want to find. 
	 */
	public E getHeap(int i)
	{
		return heap.get(i);
	}
	
	/**A method that deletes the index where the node was/is placed in the heap.
	 * An empty heap will result in a QueueException.
	 * Use: The function is used in connection with update. If one wishes to update
	 * the last instance of a node. One has to remove it, and that node may not be place on the
	 * top of the heap.
	 * Time Complexity: O(1) The If case, throw of Q.E, removing node 
	 * and decreasing currentSize all takes linear time O(1).
	 * @param node is the position of the node we wish to delete.
	 */
	public void deleteIndex(int node)
	{
		if(isEmpty())
		{
			throw new QueueException("The Binary Heap is already empty");
		}
		heap.set(node, heap.get(--currentSize));
		heap.remove(currentSize);
		bubbleDown(node);		
	}
	
	/**A method that updates the the value of a node
	 * and bubbles it to its right position.
	 * Time Complexity: O(n) + O(log n),O(n) the time it takes to go through
	 * the entire heap. O(log n) worst case is if we take
	 * the element farthest away from it's final position (being
	 * from top -> bottom etc.) => O(n+log n) => O(n)
	 * @param value the current value the node that is about to change has
	 * @param newValue the value after the update
	 */
	public void updateValue(E value, E newValue)
	{
		for(int i = 0; i<heap.size();i++)
		{
			if(comp.compare(heap.get(i), value)==0)
			{
				heap.set(i, newValue);
				bubbleDown(i);
				bubbleUp(i);
			}
		}
	}
	
	/**A method that bubbles the node upwards to its right position.
	 * Time Complexity: O(log n), worst case is if it has to go though the entire heap. 
	 * @param node, the node we might want to move
	 */
	private void bubbleUp(int node) 
	{
		E temp = heap.get(node);
		for(;node>=1 && comp.compare(temp,heap.get(node/2)) <0; node/=2)
		{
			heap.set(node,heap.get(node/2));
		}
		heap.set(node,temp);
	}
	
	/**A recursive method that bubbles a node downwards to its right position.
	 * Note: found this online, but I presume it's ok since we were allowed to copy
	 * from the book anyhow (and I found this function easier to follow)
	 * Time Complexity: O(log n) + O(f(swap)) where n is the height of the tree
	 *  => O(log n) + O(1) => O(log n) 
	 * @param node, the node we might want to move
	 */
	private void bubbleDown(int node)
	{
		if(secondChild(node) < heap.size() && comp.compare(heap.get(secondChild(node)), heap.get(firstChild(node))) < 0) 
		{
            if(comp.compare(heap.get(secondChild(node)), heap.get(node)) < 0) 
            {
                swap(node, secondChild(node));
                bubbleDown(secondChild(node));
            }
        } 
		else 
		{
            if(firstChild(node) < heap.size() && comp.compare(heap.get(firstChild(node)), heap.get(node)) < 0) 
            {
                swap(node, firstChild(node));
                bubbleDown(firstChild(node));
            }
        }
	}
	
	/**A method that swaps two nodes in the heap.
	 * Time Complexity: O(1), it only initiate and change
	 * values, which position we are aware of.
	 * @param i, the node that will take j's position.
	 * @param j, the node that will take i's position.
	 */
	private void swap(int i, int j)
	{
		E temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}
	
	/**A  help function that calculates the right child of the tree.
	 * Time Complexity: O(1), because it only performs a very simple addition
	 * (self + self + 1) 
	 * @return The index of i's right child.
	 */
	private int secondChild(int i)
	{
		return (i*2)+1;
	}
	
	/**A  help function that calculates the left child of the tree.
	 * Time Complexity: O(1), because it only performs a very simple addition
	 * (self + self + 1)
	 * @return The index of i's left child.
	 */
	private int firstChild(int i)
	{
		return i*2;
	}
	/**A function that checks if an element e can be found in our queue
	 * Time Complexity: O(n), it might have to go though the entire heap
	 * @param e, element which we want to find
	 * @return boolean
	 */
	public boolean contains(E e)
	{
		if(heap.contains(e))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}