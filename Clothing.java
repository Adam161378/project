//Klasa book dziedziczy z klasy Product
public class Clothing extends Product implements Discountable { 
public book(String name, double price, int id){
    super(name, price, id);
}

@Override
public void applyDiscount(){
    price -= price * (percentage/100);  // obliczenie znizki
}
}