//Jeff Ma
public class Saturn extends Currency {

	public Saturn(double totalFunds) {
		super(totalFunds);
		this.setCurrencyName("SaturnSilver");
	}

	@Override
	public double toEarthDollars(double amount) {
		return amount / Exchangeable.SaturnSilver;
	}

	@Override
	public double fromEarthDollars(double EarthDollars) {
		return EarthDollars * Exchangeable.SaturnSilver;
	}
	
	@Override
	public String toString() {
		return "Saturn";
	}

}
