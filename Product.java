public class Product implements Discountable {
    protected String name;
    protected double price;
    protected int id;

    public Product(String name, double price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public void display() {
        System.out.printf("ID: %d, Name: %s, Price: %.2f PLN\n", id, name, price);
    }

    @Override
    public void applyDiscount(double percentage) {
        price -= price * (percentage / 100);
    }

    public double getPrice() {
        return price;
    }
}
