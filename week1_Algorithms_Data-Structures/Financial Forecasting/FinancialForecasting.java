import java.util.Scanner;

public class FinancialForecasting {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the initial value:");
        double initialValue = Double.parseDouble(scanner.nextLine());

        System.out.println("Enter the growth rate (as a percentage):");
        double growthRate = Double.parseDouble(scanner.nextLine()) / 100;

        System.out.println("Enter the number of periods:");
        int periods = Integer.parseInt(scanner.nextLine());

        double futureValue = calculateFutureValue(initialValue, growthRate, periods);
        System.out.println("The future value after " + periods + " periods is: " + futureValue);

        scanner.close();
    }

   
    public static double calculateFutureValue(double currentValue, double growthRate, int periods) {
     
        if (periods == 0) {
            return currentValue;
        }
        
        return calculateFutureValue(currentValue * (1 + growthRate), growthRate, periods - 1);
    }
}
