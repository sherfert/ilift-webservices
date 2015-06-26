/**
 * 
 */
package data;

/**
 * @author satia
 *
 */
public class StringLongTuple {

	private String name;
	private long count;
	public StringLongTuple(String name, long count) {
		this.name = name;
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
