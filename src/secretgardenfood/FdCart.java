package secretgardenfood;

/**
 * Food Cart Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class FdCart {

    private String name;
    private double price;
    private int QTY;

    public FdCart() {
        this.name = "";
        this.QTY = 0;
        this.price = 0;

    }

    public FdCart(String name, int QTY, double price) {
        this.name = name;
        this.QTY = QTY;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

}
