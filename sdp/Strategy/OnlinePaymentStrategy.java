package Strategy;

public class OnlinePaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Online payment of " + amount + " tenge");
    }
}