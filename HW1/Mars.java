//Jeff Ma
public class Mars extends Currency {

	public Mars(double totalFunds) {
		super(totalFunds);
		this.setCurrencyName("MarsMoney");
	}

	@Override
	public double toEarthDollars(double amount) {
		return amount / Exchangeable.MarsMoney;
	}

	@Override
	public double fromEarthDollars(double EarthDollars) {
		return EarthDollars * Exchangeable.MarsMoney;
	}
	
	@Override
	public String toString() {
		return "Mars";
	}
}
