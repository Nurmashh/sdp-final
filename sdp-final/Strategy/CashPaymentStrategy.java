package Strategy;

public class CashPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Оплата наличными в размере " + amount + " тенге");
    }
}