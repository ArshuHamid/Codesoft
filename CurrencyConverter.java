import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {

    private static final Map<String, Double> rates = new HashMap<>();

    static {
        rates.put("USD", 1.0);
        rates.put("EUR", 0.85);
        rates.put("GBP", 0.74);
        rates.put("SAR", 3.75);
        rates.put("PKR", 165.74);
        rates.put("INR", 73.25);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Currency Converter!");

        while (true) {
            System.out.print("Enter base currency (exit --> quit): ");
            String base = scanner.nextLine().toUpperCase();
            if (base.equals("EXIT")) {
                break;
            }

            System.out.print("Enter target currency: ");
            String target = scanner.nextLine().toUpperCase();

            if (!rates.containsKey(base) || !rates.containsKey(target)) {
                System.out.println("Entered currencies are not supported. Please choose from the available currencies.");
                continue;
            }

            System.out.print("Enter amount to convert: ");
            double amount = scanner.nextDouble();

            double convertedAmount = convertCurrency(base, target, amount);

            System.out.println("Converted amount: " + convertedAmount + " " + target);
            scanner.nextLine();
        }
        System.out.println("Thank you for using Currency Converter!");
    }

    private static double convertCurrency(String base, String target, double amount) {
        double baseRate = rates.getOrDefault(base, 1.0);
        double targetRate = rates.getOrDefault(target, 1.0);
        return amount / baseRate * targetRate;
    }
}

