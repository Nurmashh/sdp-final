package Strategy;

public class KaspiCardPaymentStrategy  implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Оплата через Kaspi в размере " + amount + " тенге");
    }
}