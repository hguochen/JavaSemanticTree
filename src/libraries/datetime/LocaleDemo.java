package libraries.datetime;

import java.text.Collator;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Locale is an object that represents a specific geographical, political or cultural region. used for things like:
 * - Language translations
 * - Number formats (e.g., 1,000.50 vs 1.000,50)
 * - Date formats
 * - Currency formats
 *
 * Java uses Locale together with classes like:
 * - DateFormat
 * - NumberFormat
 * - ResourceBundle
 * - DateTimeFormatter
 *
 * When to use Locale?
 *
 * Formatting dates with	DateFormat / DateTimeFormatter
 * Formatting currencies with	NumberFormat
 * Sorting strings properly	with Collator
 * Translating text	with ResourceBundle
 */
public class LocaleDemo {
    public static void main(String[] args) {
        // creating a locale
        // Built-in constant
        Locale us = Locale.US; // English, United States

        // Language + Country (ISO codes)
        Locale france = new Locale("fr", "FR"); // French, France
        Locale china = new Locale("zh", "CN"); // Chinese, China

        // Just language
        Locale spanish = new Locale("es");

        // date formatting based on locale
        Date today = new Date();
        Locale fr = Locale.FRANCE;

        DateFormat dfUS = DateFormat.getDateInstance(DateFormat.LONG, us);
        DateFormat dfFR = DateFormat.getDateInstance(DateFormat.LONG, fr);

        System.out.println("US format: " + dfUS.format(today));
        System.out.println("French format: " + dfFR.format(today));

        // currency formatting based on locale
        double amount = 123456.78;

        NumberFormat nfUS = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat nfDE = NumberFormat.getCurrencyInstance(Locale.GERMANY);

        System.out.println("US: " + nfUS.format(amount));
        System.out.println("Germany: " + nfDE.format(amount));

        // string comparison based on Locale
        String s1 = "resume";
        String s2 = "résumé";

        Collator collator = Collator.getInstance(Locale.FRENCH);
        System.out.println("Are equal in French? " + (collator.compare(s1, s2) == 0));
    }
}
