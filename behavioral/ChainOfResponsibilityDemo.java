package behavioral;

// ==========================================
// 1. CLASE REQUEST (La solicitud que viaja por la cadena)
// ==========================================
class SupportRequest {
    private String username;
    private String message;
    private boolean isAuthenticated;
    private boolean isVip;

    public SupportRequest(String username, String message, boolean isAuthenticated, boolean isVip) {
        this.username = username;
        this.message = message;
        this.isAuthenticated = isAuthenticated;
        this.isVip = isVip;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public boolean isVip() {
        return isVip;
    }
}

// ==========================================
// 2. MANEJADOR ABSTRACTO (Handler)
// ==========================================
abstract class SupportHandler {
    protected SupportHandler nextHandler;

    // Método para construir la cadena de responsabilidad
    public SupportHandler setNext(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler; // Permite encadenar llamadas fluidas
    }

    // Método que cada eslabón debe implementar
    public abstract void handleRequest(SupportRequest request);
}

// ==========================================
// 3. MANEJADORES CONCRETOS
// ==========================================

// Eslabón 1: Verifica que la solicitud no esté vacía
class ValidationHandler extends SupportHandler {
    @Override
    public void handleRequest(SupportRequest request) {
        System.out.println("[ValidationHandler] Verificando datos de la solicitud...");
        if (request.getMessage() == null || request.getMessage().trim().isEmpty()) {
            System.out.println("❌ Error: La solicitud no puede estar vacía.");
            return; // Rompe la cadena si falla
        }

        // Si pasa, pasa al siguiente eslabón
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

// Eslabón 2: Verifica la autenticación del usuario
class AuthenticationHandler extends SupportHandler {
    @Override
    public void handleRequest(SupportRequest request) {
        System.out.println("[AuthenticationHandler] Verificando credenciales de usuario...");
        if (!request.isAuthenticated()) {
            System.out.println("❌ Error: Usuario no autenticado. Inicie sesión para continuar.");
            return;
        }

        System.out.println("✓ Usuario autenticado correctamente: " + request.getUsername());

        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

// Eslabón 3: Manejador para clientes VIP (Prioridad alta)
class VipSupportHandler extends SupportHandler {
    @Override
    public void handleRequest(SupportRequest request) {
        if (request.isVip()) {
            System.out
                    .println("⭐ [VipSupportHandler] Solicitud atendida por el equipo de soporte VIP prioritario para: "
                            + request.getUsername());
        } else {
            System.out.println("[VipSupportHandler] El usuario no es VIP, pasando al siguiente nivel de soporte...");
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            }
        }
    }
}

// Eslabón 4: Manejador para soporte estándar (General)
class StandardSupportHandler extends SupportHandler {
    @Override
    public void handleRequest(SupportRequest request) {
        System.out.println("📞 [StandardSupportHandler] Solicitud atendida por el equipo de soporte general. Mensaje: '"
                + request.getMessage() + "'");

        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

// ==========================================
// 4. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        // 1. Construimos la cadena: Validation -> Authentication -> VIP -> Standard
        SupportHandler chain = new ValidationHandler();
        chain.setNext(new AuthenticationHandler())
                .setNext(new VipSupportHandler())
                .setNext(new StandardSupportHandler());

        System.out.println("--- PRUEBA 1: Solicitud válida de usuario VIP ---");
        SupportRequest req1 = new SupportRequest("CarlosPerez", "Ayuda con mi factura", true, true);
        chain.handleRequest(req1);

        System.out.println("\n--- PRUEBA 2: Solicitud de usuario estándar no VIP ---");
        SupportRequest req2 = new SupportRequest("AnaGomez", "Problema con la app", true, false);
        chain.handleRequest(req2);

        System.out.println("\n--- PRUEBA 3: Solicitud con fallo de autenticación ---");
        SupportRequest req3 = new SupportRequest("Hacker", "Robar datos", false, false);
        chain.handleRequest(req3);
    }
}