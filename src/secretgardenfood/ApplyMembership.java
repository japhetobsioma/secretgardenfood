package secretgardenfood;

import java.security.SecureRandom;

/**
 * ApplyMembership Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class ApplyMembership {

    private String ApplyMembershipID;
    private String Type;
    private String Date;
    private double Cost;

    public ApplyMembership() {
        this.ApplyMembershipID = null;
        this.Type = null;
        this.Date = null;
        this.Cost = 0;
    }

    public ApplyMembership(String ApplyMembershipID, String Type, String Date, double Cost) {
        this.ApplyMembershipID = ApplyMembershipID;
        this.Type = Type;
        this.Date = Date;
        this.Cost = Cost;
    }

    public String getApplyMembershipID() {
        return ApplyMembershipID;
    }

    public void setApplyMembershipID() {
        this.ApplyMembershipID = getRandomID();
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public double getCost() {
        return Cost;
    }

    public void setCost(double Cost) {
        this.Cost = Cost;
    }

    public String getRandomID() {
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

        String randomString = "APME-";
        randomString += sb.toString();

        return randomString;
    }
}
