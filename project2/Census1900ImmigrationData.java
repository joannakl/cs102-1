import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Jeff Ma
public class Census1900ImmigrationData {
	
	private RegionList regionList = new RegionList();
	private ArrayList<String> regionNames = new ArrayList<>();
	private ArrayList<String> originNames = new ArrayList<>();
	private ArrayList<String> originLowerNames = new ArrayList<>();
	
	public Census1900ImmigrationData(File name) throws FileNotFoundException{
		
		//read the CSV data
		CSV data = new CSV(new Scanner(name));		
		ArrayList<String> line;
		
		
		//process each line
		for(int num = 0; num<data.getNumOfRows();num++ ){
			line = data.getNextRow();
			if(line.contains("(sum of column) - (Total US Value)")) {continue;}
			if(line.contains("States and territories")) {
				for (int i = 2; i<line.size();i++) {
					originNames.add(line.get(i));
					originLowerNames.add(line.get(i).toLowerCase());
				}
				continue;
			}
			if(line.size()!=48) {continue;}
			Region region = new Region(line.get(0));
			region.setTotal(Integer.valueOf(line.get(1)));		
			regionNames.add(line.get(0).toLowerCase());
			for (int i = 2; i<48;i++) {
				region.add(new Origin(originNames.get(i-2), Integer.valueOf(line.get(i))));
			}
			regionList.add(region);
		}	
	}
	
	public void regionTotal(String name) {
		Region ans = regionList.getByName(name);
		if(ans == null)
		{
			System.out.println("No region found");
			return;
		}
		System.out.println("\n" + ans.getName() + ": total foreign born population is "+ 
				ans.getTotal());
	}
	
	public void fromOrigion(String aLine) {
		System.out.println();
		String[] words = aLine.split(" from ");
		//filter
		if(words.length != 2) {
			System.out.println("This is not a valid query. Try again.\n");
		}
		
		//find the Region
		Region ans = regionList.getByName(words[0]);
		if(ans == null)
		{
			System.out.println("No region found. Try again.");
			return;
		}
		
		//find the origins
		words[1] = words[1].trim();
		boolean flag = true;
		for(String s: originLowerNames) {
			if (s.contains(words[1].toLowerCase())) {
				flag = false;
				break;
			}
		}
		if (flag) {
			System.out.println("No origin keyword found. Try again.");
			return;
		}
		ArrayList<Origin> result = ans.getByName(words[1]);
		
		//print the information
		System.out.println(ans.getName() + ": foreign born population \n");
		for (Origin o: result) {
			System.out.println(o.toString());
		}
		
	}
	
	public void regionAll(String name) {
		Region ans = regionList.getByName(name);
		if(ans == null)
		{
			System.out.println("No region found");
			return;
		}
		System.out.println(ans.toString());
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		try 
		{
		//create the object, read and process data
		File inFile = new File(args[0]);
		Census1900ImmigrationData census = new Census1900ImmigrationData(inFile);
		
		System.out.println("Enter one of the following instructions.\n"
				+ "\n"
				+ "REGION total\n"
				+ "REGION from ORIGIN\n"
				+ "REGION all\n"
				+ "quit");
		System.out.println();
		
		Scanner scan = new Scanner(System.in);
		String line;
		System.out.println("Enter your instruction:");
		
		//asking for queries until quit
		while (!(line=scan.nextLine()).equals("quit")) 
		{
			
			if (line.contains("total")) 
			{
				census.regionTotal(line.split(" total")[0]);
				System.out.println();
			} else if(line.contains("all")) 
			{
				census.regionAll(line.split(" all")[0]);
				System.out.println();
			} else if (line.contains("from"))
			{
				census.fromOrigion(line);
				System.out.println();
			} else {
				System.out.println("This is not a valid query. Try again.\n");
			}
			System.out.println("Enter your instruction:");
		}
		scan.close();
		}catch (ArrayIndexOutOfBoundsException e) {
			//if there is not an argument passed in
			System.err.println("Usage Error: the program expects file name as an argument.");
			return;
		}catch (FileNotFoundException e) {
			//if cannot find the file
			System.err.println("Error: the file "+ args[0] +" cannot be opened.");
			return;
		}
		System.out.println("Good Bye!");
	}
}
