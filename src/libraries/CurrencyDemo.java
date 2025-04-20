package libraries;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Currency is a handy class in java for representing ISO 4217 currency codes
 * like USD, EUR, JPY etc
 *
 * The Currency class represents a currency — like the US Dollar or the Euro — using:
 * - Currency Code (e.g., "USD", "EUR")
 * - Symbol (e.g., "$", "€")
 * - Default fraction digits (e.g., 2 for cents in dollars)
 *
 * Why use Currency?
 * It helps:
 * - Format currency values properly (e.g., $1,000.00)
 * - Handle internationalization (along with Locale)
 * - Ensure consistency in monetary operations
 */
public class CurrencyDemo {
    public static void main(String[] args) {
        // get currency by code
        Currency usd = Currency.getInstance("USD");

        System.out.println("Currency Code: " + usd.getCurrencyCode()); // USD
        System.out.println("Symbol: " + usd.getSymbol());              // $
        System.out.println("Default Fraction Digits: " + usd.getDefaultFractionDigits()); // 2

        // get currency by Locale
        Locale japan = Locale.JAPAN;
        Currency yen = Currency.getInstance(japan);

        System.out.println("Currency for Japan: " + yen.getCurrencyCode()); // JPY
        System.out.println("Symbol: " + yen.getSymbol(japan));              // ¥

        // format amounts in locale-sensitive way
        double price = 1234.56;
        Locale uk = Locale.UK;
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(uk);

        System.out.println(currencyFormatter.format(price)); // £1,234.56

    }
}
