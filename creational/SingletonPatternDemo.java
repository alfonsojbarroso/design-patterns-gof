// ==========================================
// 1. CLASE SINGLETON
// ==========================================
class DatabaseConnection {
    
    // Variable estática privada que contendrá la única instancia de la clase
    private static DatabaseConnection instance;

    // Cadena simulada para la conexión
    private String connectionUrl;

    // Constructor privado para evitar que se use el operador 'new' desde fuera de la clase
    private DatabaseConnection() {
        this.connectionUrl = "jdbc:mysql://localhost:3306/mi_base_de_datos";
        System.out.println("¡Conexión a la base de datos inicializada!");
    }

    // Método estático público para obtener la única instancia (Thread-safe básico)
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Método de negocio de ejemplo
    public void query(String sql) {
        System.out.println("Ejecutando consulta en [" + connectionUrl + "]: " + sql);
    }
}

// ==========================================
// 2. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class SingletonPatternDemo {
    public static void main(String[] args) {
        // Intentar usar 'new DatabaseConnection()' daría un error de compilación 
        // porque el constructor es privado.

        // Obtenemos la primera instancia
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        db1.query("SELECT * FROM usuarios");

        // Obtenemos una segunda instancia
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        db2.query("SELECT * FROM productos");

        // Verificamos si ambas variables apuntan exactly al mismo objeto en memoria
        System.out.println("\n¿db1 y db2 son exactamente la misma instancia? " + (db1 == db2));
    }
}