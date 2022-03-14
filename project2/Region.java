import java.util.ArrayList;
import java.util.Collections;

//Jeff Ma
public class Region {
	private String name;
	private ArrayList<Origin> origins;
	private int total;
	
	public Region(String name) {
		if (name == null || name.isBlank())
			throw new IllegalArgumentException("name cannot be null");
		this.name = name;
		this.origins = new ArrayList<>();
	}
	
	public boolean add(Origin origin) {
		if (origin == null )
			throw new IllegalArgumentException("Origin cannot be null");
		for(Origin o : this.origins) {
			if (origin.getOriginName().equalsIgnoreCase(o.getOriginName()))
				return false;
		}
		this.origins.add(origin);
		return true;
	}
	
	public ArrayList<Origin> getByName(String keyword) {
		ArrayList<Origin> ans = new ArrayList<>();
		if (keyword == null || keyword.isBlank())
			throw new IllegalArgumentException("Keyword cannot be null or empty!");
		for (Origin o : this.origins) {
			if (o.getOriginName().toLowerCase().contains(keyword.toLowerCase())) {
				ans.add(o);
			}
		}
		if (ans.isEmpty())
			return null;
		else
			return ans;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Region))
			return false;
		Region other = (Region) obj;
		if (this.name == null) {
			if (other.name != null)
				return false;
		else if (!this.name.equals(other.name))
			return false;	
		}
		if (this.name != other.name)
			return false;
		return this.name.equalsIgnoreCase(other.name);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.name + ": foreign born population\n" );
		Collections.sort(this.origins, Collections.reverseOrder());
		for (Origin o: this.origins) {
			sb.append(o.getOriginName() + "\t" + o.getCount() + "\n");
		}
		return sb.toString();
	}
	
	public String getName() {
		return name;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getTotal() {
		return total;
	}
	
}
