package secretgardenfood;

/**
 * Order Details Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class OrderDetails {

    private int QTY;
    private double SubTotal;

    public OrderDetails() {
        this.QTY = 0;
        this.SubTotal = 0;
    }

    public OrderDetails(int QTY, double SubTotal) {
        this.QTY = QTY;
        this.SubTotal = SubTotal;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    public double getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(double SubTotal) {
        this.SubTotal = SubTotal;
    }

}
