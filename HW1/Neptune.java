//Jeff Ma
public class Neptune extends Currency {

	public Neptune(double totalFunds) {
		super(totalFunds);
		this.setCurrencyName("NeptuneNuggets");
	}

	@Override
	public double toEarthDollars(double amount) {
		return amount / Exchangeable.NeptuneNuggets;
	}

	@Override
	public double fromEarthDollars(double EarthDollars) {
		return EarthDollars * Exchangeable.NeptuneNuggets;
	}
	
	@Override
	public String toString() {
		return "Neptune";
	}

}
