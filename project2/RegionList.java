import java.util.ArrayList;

//Jeff Ma
public class RegionList extends ArrayList<Region>{

	private static final long serialVersionUID = 1L;

	public RegionList() {
		
	}
	
	public Region getByName (String keyword) {
		if (keyword == null || keyword.isEmpty()) {
			throw new IllegalArgumentException("Keyword cannot be null or empty!");}
		for (int i = 0;i<this.size();i++)
		{
			if (this.get(i).getName().equalsIgnoreCase(keyword))
				return this.get(i);
		}

		return null;
	}

}
