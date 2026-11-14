// ==========================================
// 1. INTERFAZ PROTOTYPE
// ==========================================
interface ShapePrototype {
    ShapePrototype clone();
}

// ==========================================
// 2. PROTOTIPO CONCRETO: Circle (Círculo)
// ==========================================
class Circle implements ShapePrototype {
    private int x;
    private int y;
    private String color;

    // Constructor principal
    public Circle(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    // Constructor de copia (Recomendado para implementar Prototype en Java)
    public Circle(Circle target) {
        if (target != null) {
            this.x = target.x;
            this.y = target.y;
            this.color = target.color;
        }
    }

    @Override
    public ShapePrototype clone() {
        // Devuelve una nueva instancia copiando los valores del objeto actual
        return new Circle(this);
    }

    @Override
    public String toString() {
        return "Circle [x=" + x + ", y=" + y + ", color=" + color + "]";
    }
}

// ==========================================
// 2. PROTOTIPO CONCRETO: Rectangle (Rectángulo)
// ==========================================
class Rectangle implements ShapePrototype {
    private int width;
    private int height;
    private String color;

    // Constructor principal
    public Rectangle(int width, int height, String color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    // Constructor de copia
    public Rectangle(Rectangle target) {
        if (target != null) {
            this.width = target.width;
            this.height = target.height;
            this.color = target.color;
        }
    }

    @Override
    public ShapePrototype clone() {
        return new Rectangle(this);
    }

    @Override
    public String toString() {
        return "Rectangle [width=" + width + ", height=" + height + ", color=" + color + "]";
    }
}

// ==========================================
// 3. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class PrototypePatternDemo {
    public static void main(String[] args) {
        // 1. Creamos un círculo original
        Circle circleOriginal = new Circle(10, 15, "Rojo");
        
        // 2. Clonamos el círculo utilizando el patrón Prototype
        Circle circleClone = (Circle) circleOriginal.clone();

        // 3. Creamos un rectángulo original
        Rectangle rectOriginal = new Rectangle(50, 30, "Verde");
        
        // 4. Clonamos el rectángulo
        Rectangle rectClone = (Rectangle) rectOriginal.clone();

        // Imprimimos los resultados
        System.out.println("--- CÍRCULOS ---");
        System.out.println("Original: " + circleOriginal);
        System.out.println("Clon:     " + circleClone);

        System.out.println("\n--- RECTÁNGULOS ---");
        System.out.println("Original: " + rectOriginal);
        System.out.println("Clon:     " + rectClone);

        // Verificamos que son objetos independientes en memoria
        System.out.println("\n¿Son el mismo objeto en memoria (Círculos)? " + (circleOriginal == circleClone));
    }
}