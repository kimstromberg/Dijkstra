/**An Exception class that extends the ordinary
 * RuntimeException and lets us more easily
 * takes care of all exceptions
 * 
 * @author Kim Strömberg & Davor Pejic
 *	Time Complexity: O(f) where f is the time RunTimeException takes
 * (most likely O(1)), which we are unaware of.
 */
public class QueueException extends RuntimeException
{
	/** A Constructor that sends our parameter s to it's superclass
	 * and lets the superclass's constructor handle it
	 * @param s, the message we want to print
	 */
	public QueueException(String s)
	{
		super(s);
	}
}
