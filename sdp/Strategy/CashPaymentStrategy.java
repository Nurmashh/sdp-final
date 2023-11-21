package Strategy;

public class CashPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Cash payment in the amount of " + amount + " tenge");
    }
}