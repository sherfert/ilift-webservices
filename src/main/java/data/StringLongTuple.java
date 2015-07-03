/**
 * 
 */
package data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A tuple of String and Long. It is just used
 *  to build the corresponding json for a certain
 *  database query and send it to the web client.
 * 
 * @author satia
 */
public class StringLongTuple {
	
	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM hh:mm a");

	private String name;
	private long count;
	
	public StringLongTuple(String name, long count) {
		this.name = name;
		this.count = count;
	}
	
	public StringLongTuple(Date date, int count) {
		this.name = DATE_FORMAT.format(date);
		this.count = count;
	}
	
	public String getName() {
		return name;
	}
	
	public long getCount() {
		return count;
	}
	
	@Override
	public String toString() {
		return "StringLongTuple [name=" + name + ", count=" + count + "]";
	}
}
