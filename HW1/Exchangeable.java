//Jeff Ma

public interface Exchangeable {
	static final double MarsMoney = 1.30;
	static final double SaturnSilver = 0.87;
	static final double NeptuneNuggets = 2.00;
	
	public void exchange(Currency other, double amount);
}
