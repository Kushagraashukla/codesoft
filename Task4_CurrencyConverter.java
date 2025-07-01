import java.util.HashMap;
import java.util.Scanner;

public class Task4_CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Predefined exchange rates (against INR)
        HashMap<String, Double> exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 0.012);  // Indian Rupee to US Dollar
        exchangeRates.put("EUR", 0.011);  // Indian Rupee to Euro
        exchangeRates.put("JPY", 1.75);   // Indian Rupee to Japanese Yen
        exchangeRates.put("INR", 1.0);    // Base
        exchangeRates.put("GBP", 0.0095); // Indian Rupee to British Pound

        System.out.println("Available Currencies: INR, USD, EUR, JPY, GBP");

        System.out.print("Enter base currency (e.g., INR): ");
        String baseCurrency = scanner.next().toUpperCase();

        System.out.print("Enter target currency (e.g., USD): ");
        String targetCurrency = scanner.next().toUpperCase();

        if (!exchangeRates.containsKey(baseCurrency) || !exchangeRates.containsKey(targetCurrency)) {
            System.out.println("Invalid currency code entered.");
            return;
        }

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        // Convert amount to INR first, then to target currency
        double inINR = amount / exchangeRates.get(baseCurrency);
        double convertedAmount = inINR * exchangeRates.get(targetCurrency);

        System.out.printf("Converted Amount: %.2f %s\n", convertedAmount, targetCurrency);
        scanner.close();
    }
}
