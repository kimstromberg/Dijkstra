import java.util.Comparator;

/** A class made for the sole puropse of comparing Edges (consisting of an
 * int which represents the time/length of the Edge and a Node which it leads to)
 * @author Kim Strömberg & Davor Pejic
 */
public class cmpTo implements Comparator<Edge>
{
	@Override
	public int compare(Edge o1, Edge o2) 
	{
		if(o1.getDistance()<o2.getDistance())
		{
			return -1;
		}
		else if(o1.getDistance()>o2.getDistance())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
}