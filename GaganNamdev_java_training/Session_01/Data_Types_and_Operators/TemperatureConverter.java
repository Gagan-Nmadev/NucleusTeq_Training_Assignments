public class TemperatureConverter {
    
    //  Celsius to Fahrenheit conversion
    public void celsiusToFahrenheit(double celsius) {

        double fahrenheit = (celsius * 9 / 5) + 32;

        System.out.println("Celsius to Fahrenheit:");
        System.out.println(celsius + " °C = " + fahrenheit + " °F");
    }
}
