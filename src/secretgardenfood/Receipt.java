package secretgardenfood;

import java.security.SecureRandom;

/**
 * Receipt  Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */

public class Receipt {
    
    private String receiptNo;
    private String receiptDate;
    private String receiptType;
    private double receiptTotalCost;

    public Receipt() {
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

        String randomString = "RCPT-";
        randomString += sb.toString();

        this.receiptNo = randomString;
        this.receiptDate = null;
        this.receiptType = null;
        this.receiptTotalCost = 0;
    }

    public Receipt(String receiptNo, String receiptDate, String receiptType, double receiptTotalCost) {
        this.receiptNo = receiptNo;
        this.receiptDate = receiptDate;
        this.receiptType = receiptType;
        this.receiptTotalCost = receiptTotalCost;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }
    
    public void setNewReceiptType(String receiptType) {
        this.receiptType += receiptType;
    }

    public double getReceiptTotalCost() {
        return receiptTotalCost;
    }

    public void setReceiptTotalCost(double receiptTotalCost) {
        this.receiptTotalCost = receiptTotalCost;
    }
    
    public void setAddReceiptTotalCost(double receiptTotalCost) {
        this.receiptTotalCost += receiptTotalCost;
    }
    
}
