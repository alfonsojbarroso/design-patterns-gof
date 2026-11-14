package behavioral;

// ==========================================
// 1. INTERFAZ STRATEGY (La Estrategia)
// ==========================================
// Define el contrato común para todos los algoritmos de cálculo de envío
interface ShippingStrategy {
    double calculateShippingCost(double weightInKg, double distanceInKm);
}

// ==========================================
// 2. ESTRATEGIAS CONCRETAS (Concrete Strategies)
// ==========================================

// Estrategia 1: Envío Estándar (Económico, basado en peso y distancia base)
class StandardShippingStrategy implements ShippingStrategy {
    @Override
    public double calculateShippingCost(double weightInKg, double distanceInKm) {
        System.out.println("Calculando costo con la estrategia: Envío Estándar 📦");
        double baseRate = 5.0;
        return baseRate + (weightInKg * 1.5) + (distanceInKm * 0.1);
    }
}

// Estrategia 2: Envío Exprés (Más rápido, tarifa fija más alta pero menor costo
// por distancia)
class ExpressShippingStrategy implements ShippingStrategy {
    @Override
    public double calculateShippingCost(double weightInKg, double distanceInKm) {
        System.out.println("Calculando costo con la estrategia: Envío Exprés ⚡");
        double baseRate = 15.0;
        return baseRate + (weightInKg * 3.0) + (distanceInKm * 0.2);
    }
}

// Estrategia 3: Envío Internacional (Aplica cargos aduaneros y tarifas
// globales)
class InternationalShippingStrategy implements ShippingStrategy {
    @Override
    public double calculateShippingCost(double weightInKg, double distanceInKm) {
        System.out.println("Calculando costo con la estrategia: Envío Internacional ✈️");
        double baseRate = 50.0;
        return baseRate + (weightInKg * 7.5) + (distanceInKm * 0.5);
    }
}

// ==========================================
// 3. CLASE CONTEXTO (Context)
// ==========================================
// Mantiene una referencia a un objeto Strategy y delega en él la ejecución
class ShoppingCart {
    private ShippingStrategy shippingStrategy;

    // Permite inyectar o cambiar la estrategia en tiempo de ejecución
    public void setShippingStrategy(ShippingStrategy shippingStrategy) {
        this.shippingStrategy = shippingStrategy;
    }

    public double checkout(double weight, double distance) {
        if (shippingStrategy == null) {
            throw new IllegalStateException("Debe seleccionar un método de envío antes de pagar.");
        }
        // El contexto delega el cálculo a la estrategia actual
        return shippingStrategy.calculateShippingCost(weight, distance);
    }
}

// ==========================================
// 4. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class StrategyPatternDemo {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        double weight = 4.5; // kg
        double distance = 120.0; // km

        System.out.println("--- OPCIÓN 1: Envío Estándar ---");
        cart.setShippingStrategy(new StandardShippingStrategy());
        double cost1 = cart.checkout(weight, distance);
        System.out.printf("Costo total: $%.2f USD\n\n", cost1);

        System.out.println("--- OPCIÓN 2: Envío Exprés ---");
        cart.setShippingStrategy(new ExpressShippingStrategy());
        double cost2 = cart.checkout(weight, distance);
        System.out.printf("Costo total: $%.2f USD\n\n", cost2);

        System.out.println("--- OPCIÓN 3: Envío Internacional ---");
        cart.setShippingStrategy(new InternationalShippingStrategy());
        double cost3 = cart.checkout(weight, distance);
        System.out.printf("Costo total: $%.2f USD\n", cost3);
    }
}