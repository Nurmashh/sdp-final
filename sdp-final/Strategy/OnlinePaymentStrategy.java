package Strategy;

public class OnlinePaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Оплата онлайн в размере " + amount + " тенге");
    }
}