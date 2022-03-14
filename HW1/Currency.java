import java.text.DecimalFormat;

//Jeff Ma

public abstract class Currency implements Exchangeable {
	private String currencyName;
	private double totalFunds;

	public Currency(double totalFunds) {
		this.totalFunds = totalFunds;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public double getTotalFunds() {
		return totalFunds;
	}

	public void setTotalFunds(double totalFunds) {
		this.totalFunds = totalFunds;
	}

	public abstract double toEarthDollars(double amount);

	public abstract double fromEarthDollars(double EarthDollars);

	@Override
	public void exchange(Currency other, double amount) {
		double ear = this.toEarthDollars(amount);
		DecimalFormat df = new DecimalFormat("$0.00");
		if (this.getTotalFunds() < amount) {
			System.out.println("Uh oh - " + this.toString() + " only has an availble balance of "+
					df.format(this.getTotalFunds()) + ", which is less than " + df.format(amount) + "!");
			System.out.println();
			return;
		}
		double tar = other.fromEarthDollars(ear);
		System.out.println("Converting from " + this.getCurrencyName() +  " to " + other.getCurrencyName() +
				" and initiating transfer...");

		System.out.println(df.format(amount) +" " + this.getCurrencyName() + 
				" = " + df.format(ear) + " EarthDollars" + 
				" = " + df.format(tar) + " " + other.getCurrencyName());
		
		this.setTotalFunds(this.getTotalFunds() - amount);
		other.setTotalFunds(other.getTotalFunds() + tar);
		System.out.println( this.toString() + " has a total of " +df.format(this.getTotalFunds()) + " " + this.getCurrencyName());
		System.out.println( other.toString() + " has a total of " +df.format(other.getTotalFunds()) + " " + other.getCurrencyName());
		System.out.println();
	}

	public static void main(String[] args) {
		Currency mars = new Mars(100.00);
		Currency neptune = new Neptune(100.00);
		Currency saturn = new Saturn(100.00);
		System.out.println("<-- Exchanges -->");
		mars.exchange(saturn, 25.0);
		neptune.exchange(saturn, 10.0);
		saturn.exchange(mars, 122.0);
		saturn.exchange(mars, 121.0);
	}
}