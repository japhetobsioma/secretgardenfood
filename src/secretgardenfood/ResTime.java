package secretgardenfood;

/**
 * Reservation Time Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class ResTime {

    private String time;
    private int tblLeft;

    public ResTime() {
        this.time = "";
        this.tblLeft = 0;
    }

    public ResTime(String time, int tblLeft) {
        this.time = time;
        this.tblLeft = tblLeft;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTblLeft() {
        return tblLeft;
    }

    public void setTblLeft(int tblLeft) {
        this.tblLeft = tblLeft;
    }

}
