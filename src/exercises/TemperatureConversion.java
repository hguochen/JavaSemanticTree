package exercises;

public class TemperatureConversion {
    public static double fahrenheitToDegree(double fahrenheit) {
        return ((fahrenheit - 32) * 5) / 9;
    }

    public static double degreeToFahrenheit(double degree) {
        return (degree * 9 / 5) + 32;
    }

    public static void main(String[] args) {
        double fahrenheit = 98.6;
        double degree = 37;
        System.out.println("Fahrenheit " + fahrenheit + " to degree is: " + fahrenheitToDegree(fahrenheit));
        System.out.println("Degree " + fahrenheit + " to fahrenheit is: " + degreeToFahrenheit(degree));
    }
}
