// ==========================================
// 1. PRODUCTO ABSTRACTO
// ==========================================
interface Notifier {
    void send(String message);
}

// ==========================================
// 2. PRODUCTOS CONCRETOS
// ==========================================
class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Enviando Correo Electrónico: " + message);
    }
}

class SMSNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Enviando SMS: " + message);
    }
}

// ==========================================
// 3. CREADOR (FÁBRICA) ABSTRACTO
// ==========================================
abstract class NotificationService {
    // Este es el Factory Method que las subclases deben implementar
    public abstract Notifier createNotifier();

    // Lógica de negocio que utiliza el producto creado por el Factory Method
    public void processNotification(String message) {
        // La clase creadora puede invocar el factory method sin conocer la clase exacta
        Notifier notifier = createNotifier();
        notifier.send(message);
    }
}

// ==========================================
// 4. CREADORES CONCRETOS
// ==========================================
class EmailNotificationService extends NotificationService {
    @Override
    public Notifier createNotifier() {
        return new EmailNotifier(); // Fábrica concreta que crea un EmailNotifier
    }
}

class SMSNotificationService extends NotificationService {
    @Override
    public Notifier createNotifier() {
        return new SMSNotifier(); // Fábrica concreta que crea un SMSNotifier
    }
}

// ==========================================
// 5. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class FactoryMethodDemo {
    public static void main(String[] args) {
        NotificationService service;

        System.out.println("--- Usando servicio de Email ---");
        service = new EmailNotificationService();
        service.processNotification("¡Hola, este es un aviso importante por correo!");

        System.out.println("\n--- Usando servicio de SMS ---");
        service = new SMSNotificationService();
        service.processNotification("¡Hola, este es un aviso por mensaje de texto!");
    }
}