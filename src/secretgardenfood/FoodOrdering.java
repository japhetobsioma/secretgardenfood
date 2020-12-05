package secretgardenfood;

import java.security.SecureRandom;

/**
 * Food Ordering Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class FoodOrdering {

    private String foodOrderingID;
    private double foodOrderTotal;
    private String Date;

    public FoodOrdering() {
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";

        String alphaNumeric = upperAlphabet + numbers;

        StringBuilder sb = new StringBuilder();

        SecureRandom random = new SecureRandom();

        int length = 6;

        for (int i = 0; i < length; i++) {

            int index = random.nextInt(alphaNumeric.length());

            char randomChar = alphaNumeric.charAt(index);

            sb.append(randomChar);
        }

        String randomString = "FORD-";
        randomString += sb.toString();

        this.foodOrderingID = randomString;
        this.foodOrderTotal = 0;
        this.Date = java.time.LocalDate.now().toString();
    }

    public FoodOrdering(String foodOrderingID, double foodOrderTotal, String Date) {
        this.foodOrderingID = foodOrderingID;
        this.foodOrderTotal = foodOrderTotal;
        this.Date = Date;
    }

    public String getFoodOrderingID() {
        return foodOrderingID;
    }

    public void setFoodOrderingID(String foodOrderingID) {
        this.foodOrderingID = foodOrderingID;
    }

    public double getFoodOrderTotal() {
        return foodOrderTotal;
    }

    public void setFoodOrderTotal(double foodOrderTotal) {
        this.foodOrderTotal += foodOrderTotal;
    }

    public void setMinFoodOrderTotal(double foodOrderTotal) {
        this.foodOrderTotal -= foodOrderTotal;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

}
