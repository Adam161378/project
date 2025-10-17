import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> cart = new ArrayList<>();

        cart.add(new Book("Książka Java", 59.99, 1));
        cart.add(new Electronics("Laptop", 2499.99, 2));
        cart.add(new Clothing("Koszulka", 39.99, 3));

        for (Product p : cart) {
            p.applyDiscount(30);
        }

        double total = 0;
        System.out.println("Koszyk:");
        for (Product p : cart) {
            p.display();
            total += p.getPrice();
        }

        System.out.printf("Suma do zapłaty: %.2f PLN\n", total);
    }
}
