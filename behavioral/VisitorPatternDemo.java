package behavioral;

// ==========================================
// 1. INTERFAZ VISITOR (Visitante)
// ==========================================
interface Visitor {
    int visit(Book book);

    int visit(Fruit fruit);
}

// ==========================================
// 2. INTERFAZ ELEMENT (Elemento)
// ==========================================
interface ItemElement {
    int accept(Visitor visitor);
}

// ==========================================
// 3. ELEMENTOS CONCRETOS
// ==========================================
class Book implements ItemElement {
    private int price;
    private String isbnNumber;

    public Book(int cost, String isbn) {
        this.price = cost;
        this.isbnNumber = isbn;
    }

    public int getPrice() {
        return price;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
}

class Fruit implements ItemElement {
    private int pricePerKg;
    private double weight;
    private String name;

    public Fruit(int priceKg, double wt, String nm) {
        this.pricePerKg = priceKg;
        this.weight = wt;
        this.name = nm;
    }

    public int getPricePerKg() {
        return pricePerKg;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
}

// ==========================================
// 4. VISITANTE CONCRETO (Concrete Visitor)
// ==========================================
class ShoppingCartVisitorImpl implements Visitor {
    @Override
    public int visit(Book book) {
        int cost = 0;
        // Aplicar descuento si el libro cuesta más de 50
        if (book.getPrice() > 50) {
            cost = book.getPrice() - 5;
            System.out.println("Libro ISBN: " + book.getIsbnNumber() + " con descuento aplicado.");
        } else {
            cost = book.getPrice();
            System.out.println("Libro ISBN: " + book.getIsbnNumber() + " a precio normal.");
        }
        return cost;
    }

    @Override
    public int visit(Fruit fruit) {
        int cost = (int) (fruit.getPricePerKg() * fruit.getWeight());
        System.out.println("Fruta: " + fruit.getName() + " costo total: " + cost + " USD");
        return cost;
    }
}

// ==========================================
// 5. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class VisitorPatternDemo {
    public static void main(String[] args) {
        ItemElement[] items = new ItemElement[] {
                new Book(20, "1234"),
                new Book(60, "5678"),
                new Fruit(10, 2.5, "Manzana"),
                new Fruit(15, 1.5, "Plátano")
        };

        Visitor visitor = new ShoppingCartVisitorImpl();
        int totalSum = 0;

        for (ItemElement item : items) {
            totalSum += item.accept(visitor);
        }

        System.out.println("\nCosto Total Calculado por el Visitor: $" + totalSum + " USD");
    }
}