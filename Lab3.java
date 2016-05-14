import java.util.Iterator;
import java.util.List;

import Lab3Help.BLineTable;
import Lab3Help.BStop;
import Lab3Help.Lab3File;

/**A main class that runs the program
 * @author Kim & Davor
 */
public class Lab3 
{
	
	public static void main(String[] args) throws Exception
	{
		if(args.length != 4)
		{
			throw new Exception("Make sure to insert the following arguments: List<BStop> ' List<BLineTable> ' startNode ' endNode");
		}
		Lab3File lab3file = new Lab3File();
		List<BStop> stops = lab3file.readStops(args[0]);
		List<BLineTable> lines = lab3file.readLines(args[1]);
		Path p = new MyPath(stops,lines);
		System.out.println("from: " + args[2]);
		System.out.println("to: " + args[3]);
		p.computePath(args[2], args[3]);
		Iterator<String> itr = p.getPath();
		while(itr.hasNext())
		{
			System.out.println(itr.next());
		}
		System.out.println("Distance: " + p.getPathLength());
	}
}
		