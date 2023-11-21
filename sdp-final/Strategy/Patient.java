package Strategy;

public class Patient {

    private PaymentStrategy paymentStrategy;

    public Patient(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void pay(double amount) {
        paymentStrategy.pay(amount);
    }
}