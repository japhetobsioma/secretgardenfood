package secretgardenfood;

import java.security.SecureRandom;

/**
 * Premium Member Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class PremiumMember extends Customer {

    private String premiumCardNumber;
    private String premiumRegDate;
    private String premiumExpireDate;
    private String premiumID;

    public PremiumMember() {
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

        String randomString = "PREM-";
        randomString += sb.toString();

        this.premiumCardNumber = null;
        this.premiumRegDate = null;
        this.premiumExpireDate = null;
        this.premiumID = randomString;
    }

    public PremiumMember(String premiumCardNumber, String premiumRegDate,
            String premiumExpireDate, String premiumID) {
        this.premiumCardNumber = premiumCardNumber;
        this.premiumRegDate = premiumRegDate;
        this.premiumExpireDate = premiumExpireDate;
        this.premiumID = premiumID;

    }

    public String getPremiumCardNumber() {
        return premiumCardNumber;
    }

    public void setPremiumCardNumber(String premiumCardNumber) {
        this.premiumCardNumber = premiumCardNumber;
    }

    public String getPremiumRegDate() {
        return premiumRegDate;
    }

    public void setPremiumRegDate(String premiumRegDate) {
        this.premiumRegDate = premiumRegDate;
    }

    public String getPremiumExpireDate() {
        return premiumExpireDate;
    }

    public void setPremiumExpireDate(String premiumExpireDate) {
        this.premiumExpireDate = premiumExpireDate;
    }

    public String getPremiumID() {
        return premiumID;
    }

    public void setPremiumID(String premiumID) {
        this.premiumID = premiumID;
    }

}
