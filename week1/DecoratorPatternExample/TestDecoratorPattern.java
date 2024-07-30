public class TestDecoratorPattern {
    public static void main(String[] args) {
        // Create an EmailNotifier
        Notifier emailNotifier = new EmailNotifier();

        // Decorate the EmailNotifier with SMSNotifier
        Notifier smsNotifier = new SMSNotifierDecorator(emailNotifier);

        // Decorate the SMSNotifier with SlackNotifier
        Notifier slackNotifier = new SlackNotifierDecorator(smsNotifier);

        // Send a message via Email, SMS, and Slack
        slackNotifier.send("Hello, this is a test notification!");
    }
}
