// ==========================================
// 1. CLASE PRODUCTO (COMPLEJA)
// ==========================================
class Computer {
    // Parámetros obligatorios
    private String cpu;
    private int ram; // en GB
    private int storage; // en GB

    // Parámetros opcionales
    private boolean isGraphicsCardEnabled;
    private boolean isBluetoothEnabled;

    // Constructor privado para que solo el Builder pueda instanciar la clase
    private Computer(ComputerBuilder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled;
        this.isBluetoothEnabled = builder.isBluetoothEnabled;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + cpu +
                ", RAM=" + ram + "GB" +
                ", Almacenamiento=" + storage + "GB" +
                ", Tarjeta Gráfica=" + isGraphicsCardEnabled +
                ", Bluetooth=" + isBluetoothEnabled + "]";
    }

    // ==========================================
    // 2. CLASE BUILDER (ESTÁTICA ANIDADA)
    // ==========================================
    public static class ComputerBuilder {
        // Atributos obligatorios
        private String cpu;
        private int ram;
        private int storage;

        // Atributos opcionales con valores por defecto
        private boolean isGraphicsCardEnabled = false;
        private boolean isBluetoothEnabled = true;

        // Constructor del Builder con los parámetros obligatorios
        public ComputerBuilder(String cpu, int ram, int storage) {
            this.cpu = cpu;
            this.ram = ram;
            this.storage = storage;
        }

        // Métodos setters encadenados (Fluent Interface) para parámetros opcionales
        public ComputerBuilder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
            this.isGraphicsCardEnabled = isGraphicsCardEnabled;
            return this;
        }

        public ComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }

        // Método de construcción final que devuelve el objeto Computer
        public Computer build() {
            // Aquí se pueden agregar validaciones lógicas antes de instanciar
            return new Computer(this);
        }
    }
}

// ==========================================
// 3. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class BuilderPatternDemo {
    public static void main(String[] args) {
        // 1. Construcción de una computadora básica (solo con parámetros obligatorios)
        Computer basicPc = new Computer.ComputerBuilder("Intel Core i3", 8, 256)
                .build();

        // 2. Construcción de una computadora avanzada/gamer usando métodos encadenados
        Computer gamingPc = new Computer.ComputerBuilder("AMD Ryzen 7", 32, 1024)
                .setGraphicsCardEnabled(true)
                .setBluetoothEnabled(true)
                .build();

        System.out.println("Configuración 1: " + basicPc);
        System.out.println("Configuración 2: " + gamingPc);
    }
}