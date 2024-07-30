public class GpayAdapter implements PaymentProcessor {
    private Gpay gpay;

    public GpayAdapter(Gpay gpay) {
        this.gpay = gpay;
    }

    @Override
    public void processPayment(double amount) {
        gpay.pay(amount);
    }
}
