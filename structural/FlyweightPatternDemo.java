package structural;

import java.util.HashMap;
import java.util.Map;

// ==========================================
// 1. INTERFAZ FLYWEIGHT
// ==========================================
interface Shape {
    void draw(int x, int y); // x e y = Estado extrínseco
}

// ==========================================
// 2. FLYWEIGHT CONCRETO
// ==========================================
class Circle implements Shape {
    private String color; // Estado intrínseco (compartido)

    public Circle(String color) {
        this.color = color;
    }

    @Override
    public void draw(int x, int y) {
        System.out.println("Dibujando Círculo [Color: " + color + ", X: " + x + ", Y: " + y + "]");
    }
}

// ==========================================
// 3. FÁBRICA FLYWEIGHT (Controla el caché)
// ==========================================
class ShapeFactory {
    private static final Map<String, Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle) circleMap.get(color);

        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("[Caché] Creando una nueva instancia única para el color: " + color);
        }
        return circle;
    }
}

// ==========================================
// 4. EJECUCIÓN
// ==========================================
public class FlyweightPatternDemo {
    public static void main(String[] args) {
        // Aunque solicitamos círculos repetidas veces, la fábrica los reutiliza gracias
        // al Flyweight
        Shape shape1 = ShapeFactory.getCircle("Rojo");
        shape1.draw(10, 20);

        Shape shape2 = ShapeFactory.getCircle("Azul");
        shape2.draw(30, 40);

        Shape shape3 = ShapeFactory.getCircle("Rojo"); // Reutiliza la instancia de "Rojo"
        shape3.draw(50, 60);

        System.out.println("¿shape1 y shape3 son el mismo objeto en memoria? " + (shape1 == shape3));
    }
}