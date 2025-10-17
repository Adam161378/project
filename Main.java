public class Main {
    public static void main(String[] args) {
        // Lista produktów (koszyk)
        ArrayList<Product> cart = new ArrayList<>();

        // Dodajemy produkty do koszyka
        cart.add(new Product("Książka Java", 59.99, 1));
        cart.add(new Product("Laptop", 2499.99, 2));
        cart.add(new Product("Koszulka", 39.99, 3));

        // Zniżka 10% na każdy produkt
        for (Product p : cart) {
            p.applyDiscount(10); // zniżka 10%
        }

        // Wyświetlenie koszyka
        double total = 0;
        System.out.println("Koszyk:");
        for (Product p : cart) {
            p.display();            // wyświetl produkt
            total += p.price;       // sumuj ceny
        }

        // Łączna cena
        System.out.printf("Suma do zapłaty: %.2f PLN\n", total);
    }
}