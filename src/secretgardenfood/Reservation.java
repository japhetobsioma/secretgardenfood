package secretgardenfood;

import java.security.SecureRandom;

/**
 * Reservation Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class Reservation {

    private String ReservationID;
    private String Info;
    private int NoPersons;
    private double Cost;
    private String Date;
    private String Time;

    public Reservation() {
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

        String randomString = "RESV-";
        randomString += sb.toString();

        this.ReservationID = randomString;
        this.Info = null;
        this.NoPersons = 0;
        this.Cost = 0;
        this.Date = null;
        this.Time = null;
    }

    public Reservation(String ReservationID, String Info, int NoPersons, double Cost,
            String Date, String Time) {
        this.ReservationID = ReservationID;
        this.Info = Info;
        this.NoPersons = NoPersons;
        this.Cost = Cost;
        this.Date = Date;
        this.Time = Time;
    }

    public String getReservationID() {
        return ReservationID;
    }

    public void setReservationID(String ReservationID) {
        this.ReservationID = ReservationID;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String Info) {
        this.Info = Info;
    }

    public int getNoPersons() {
        return NoPersons;
    }

    public void setNoPersons(int NoPersons) {
        this.NoPersons = NoPersons;
    }

    public double getCost() {
        return Cost;
    }

    public void setCost(double Cost) {
        this.Cost = Cost;
    }

    public void setAddCost(double Cost) {
        this.Cost += Cost;

    }

    public String getDate() {
        return Date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

}
