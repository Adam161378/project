// Bazowa klasa reprezentująca produkt w sklepie
public class Product {
    protected String name;   // Nazwa produktu
    protected double price;  // Cena produktu
    protected int id;        // ID produktu

    // Konstruktor do tworzenia produktu
    public Product(String name, double price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    // Wyświetlanie informacji o produkcie
    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Price: " + price + " PLN");
    }

    // Zwraca aktualną cenę produktu
    public double getPrice() {
        return price;
    }
}
