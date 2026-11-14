package structural;

import java.util.ArrayList;
import java.util.List;

// ==========================================
// 1. SUJETO (Interfaz común)
// ==========================================
// Tanto el objeto real como el proxy implementan la misma interfaz
interface Internet {
    void connectTo(String serverHost) throws Exception;
}

// ==========================================
// 2. SUJETO REAL (RealSubject)
// ==========================================
// Contiene la lógica de negocio principal (la conexión real a Internet)
class RealInternet implements Internet {
    @Override
    public void connectTo(String serverHost) {
        System.out.println("Conectando exitosamente a: " + serverHost);
    }
}

// ==========================================
// 3. PROXY (Proxy de Protección / Control de Acceso)
// ==========================================
// Controla el acceso al objeto real aplicando validaciones previas
class ProxyInternet implements Internet {
    private Internet realInternet = new RealInternet();
    private static List<String> bannedSites;

    static {
        bannedSites = new ArrayList<>();
        bannedSites.add("facebook.com");
        bannedSites.add("tiktok.com");
        bannedSites.add("juegos-online.com");
    }

    @Override
    public void connectTo(String serverHost) throws Exception {
        // Control de acceso antes de delegar al objeto real
        if (bannedSites.contains(serverHost.toLowerCase())) {
            throw new Exception(
                    "Acceso Denegado: El sitio '" + serverHost + "' está bloqueado por políticas de la empresa.");
        }

        // Si pasa la validación, delega la petición al objeto real
        realInternet.connectTo(serverHost);
    }
}

// ==========================================
// 4. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class ProxyPatternDemo {
    public static void main(String[] args) {
        // El cliente interactúa con el Proxy pensando que es el servicio de Internet
        Internet internet = new ProxyInternet();

        try {
            // 1. Intento de conexión a un sitio permitido
            internet.connectTo("github.com");

            // 2. Intento de conexión a un sitio bloqueado (el Proxy lo detiene)
            internet.connectTo("tiktok.com");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
