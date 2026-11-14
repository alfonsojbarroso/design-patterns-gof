package structural;


// ==========================================
// 1. COMPONENTE (Interfaz común)
// ==========================================
interface Coffee {
    String getDescription();
    double getCost();
}

// ==========================================
// 2. COMPONENTE CONCRETO (Objeto Base)
// ==========================================
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Café simple";
    }

    @Override
    public double getCost() {
        return 2.00;
    }
}

// ==========================================
// 3. DECORADOR ABSTRACTO
// ==========================================
// Implementa la misma interfaz y mantiene una referencia al objeto Coffee original
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

// ==========================================
// 4. DECORADORES CONCRETOS
// ==========================================
// Decorador para agregar Leche
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", con Leche";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.50; // Incrementa el costo
    }
}

// Decorador para agregar Azúcar
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", con Azúcar";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.20; // Incrementa el costo
    }
}

// ==========================================
// 5. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class DecoratorPatternDemo {
    public static void main(String[] args) {
        // 1. Pedimos un café simple base
        Coffee myCoffee = new SimpleCoffee();
        System.out.println(myCoffee.getDescription() + " -> Costo: $" + myCoffee.getCost());

        // 2. Pedimos un café con leche (envolvemos el café simple con MilkDecorator)
        Coffee coffeeWithMilk = new MilkDecorator(new SimpleCoffee());
        System.out.println(coffeeWithMilk.getDescription() + " -> Costo: $" + coffeeWithMilk.getCost());

        // 3. Pedimos un café completo: Leche y Azúcar (envolvemos de forma anidada)
        Coffee deluxeCoffee = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.println(deluxeCoffee.getDescription() + " -> Costo: $" + deluxeCoffee.getCost());
    }
}