package Strategy;

public class CardPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Payment by card in the amount of " + amount + " tenge");
    }
}