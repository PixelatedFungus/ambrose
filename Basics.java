package lusaris;

public class Basics {
    public static void calculate_rent(float totalRent) {
        float jadenRent = (totalRent - 65) / 2;
        jadenRent = (float) Math.round(jadenRent * 100) / 100;
        float wilsonRent = totalRent - jadenRent;
        String stringJadenRent = Float.toString(jadenRent);
        String stringWilsonRent = Float.toString(wilsonRent);
        if (jadenRent + wilsonRent != totalRent) {
            System.out.println("There was an error calculating the rent.");
        } else {
            System.out.println(String.format("Wilson [$%s] \n", stringWilsonRent)
                    + String.format("Jaden [$%s]", stringJadenRent));
        }
    }
}
