package secretgardenfood;

/**
 * Food Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class Food {

    private String FoodID;
    private String Name;
    private double UnitPrice;
    private String Category;

    public Food() {
        this.FoodID = null;
        this.Name = null;
        this.UnitPrice = 0;
        this.Category = null;
    }

    public Food(String FoodID, String Name, double UnitPrice, String Category) {
        this.FoodID = FoodID;
        this.Name = Name;
        this.UnitPrice = UnitPrice;
        this.Category = Category;
    }

    public String getFoodID() {
        return FoodID;
    }

    public void setFoodID(String FoodID) {
        this.FoodID = FoodID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

}
