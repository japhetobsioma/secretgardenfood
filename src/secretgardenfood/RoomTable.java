package secretgardenfood;

import java.security.SecureRandom;

/**
 * Room Table Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class RoomTable {

    private String RoomTableID;
    private String Date;
    private String Time;
    private String RoomName;
    private String TableName;

    public RoomTable() {
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

        this.RoomTableID = randomString;
        this.Date = null;
        this.Time = null;
        this.RoomName = null;
        this.TableName = null;
    }

    public RoomTable(String RoomTableID, String Date, String Time, String RoomName, String TableName) {
        this.RoomTableID = RoomTableID;
        this.Date = Date;
        this.Time = Time;
        this.RoomName = RoomName;
        this.TableName = TableName;
    }

    public String getRoomTableID() {
        return RoomTableID;
    }

    public void setRoomTableID(String RoomTableID) {
        this.RoomTableID = RoomTableID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String RoomName) {
        this.RoomName = RoomName;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String TableName) {
        this.TableName = TableName;
    }

}
