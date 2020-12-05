package secretgardenfood;

/**
 * Food Menu Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class FdMenu {

    private String name;
    private double price;

    public FdMenu() {
        this.name = "";
        this.price = 0;
    }

    public FdMenu(String name, double price) {
        this.name = name;
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

}
