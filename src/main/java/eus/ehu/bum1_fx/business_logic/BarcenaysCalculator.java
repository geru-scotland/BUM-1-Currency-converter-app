
package eus.ehu.bum1_fx.business_logic;

public class BarcenaysCalculator implements ExchangeCalculator{

    public BarcenaysCalculator(){}
    @Override
    public String[] getCurrencyLongNames() {
        return Currency.longNames();
    }

    @Override
    public double getChangeValue(String cur1, double amount, String cur2) throws Exception {
        ForexOperator forex = new ForexOperator(cur1, amount, cur2);
        return forex.getChangeValue();
    }

    @Override
    public double calculateCommission(double amount, String origCurrency) throws Exception {
        CommissionCalculator calculator = new CommissionCalculator(amount, origCurrency);
        return calculator.calculateCommission();
    }
}
