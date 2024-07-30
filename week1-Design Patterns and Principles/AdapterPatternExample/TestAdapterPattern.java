public class TestAdapterPattern {
    public static void main(String[] args) {
        // Using PayPal through the adapter
        PayPal payPal = new PayPal();
        PaymentProcessor payPalProcessor = new PayPalAdapter(payPal);
        payPalProcessor.processPayment(100.00);

        // Using Stripe through the adapter
        Gpay gpay = new Gpay();
        PaymentProcessor gpayProcessor = new GpayAdapter(gpay);
        gpayProcessor.processPayment(200.00);
    }
}
