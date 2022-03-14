//Jeff Ma
public class Origin implements Comparable<Origin>{
	private String originName;
	private int count;
	
	public Origin(String originName, int count) {
		if(originName == null || originName.isEmpty())
			throw new IllegalArgumentException("OriginName cannot be null!") ;
		if(count < 0)
			throw new IllegalArgumentException("Count cannot be negative!") ;
		this.originName = originName;
		this.count = count;
	}

	@Override
	public int compareTo(Origin o) {
		int c = Integer.compare(count, o.count);
		if (this.count == o.count) {
			return this.originName.compareToIgnoreCase(o.originName);
		}
		else
			return c;
	}
	
	@Override
	public String toString() {
		return originName + " " + count;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Origin))
			return false;
		Origin other = (Origin) obj;
		if (this.originName == null) {
			if (other.originName != null)
				return false;
		else if (!this.originName.equals(other.originName))
			return false;	
		}
		if (this.count != other.count)
			return false;
		return true;
	}

	public String getOriginName() {
		return originName;
	}

	public int getCount() {
		return count;
	}
}
