package Strategy;

public class KaspiCardPaymentStrategy  implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Payment via Kaspi in the amount of " + amount + " tenge");
    }
}