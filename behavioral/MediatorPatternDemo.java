package behavioral;

import java.util.ArrayList;
import java.util.List;

// ==========================================
// 1. INTERFAZ MEDIATOR (Mediador)
// ==========================================
interface AirTrafficControlMediator {
    void sendMessage(String message, Airplane sender);

    void registerAirplane(Airplane airplane);
}

// ==========================================
// 2. MEDIADOR CONCRETO (Concrete Mediator)
// ==========================================
class AirportControlTower implements AirTrafficControlMediator {
    private List<Airplane> airplanes;

    public AirportControlTower() {
        this.airplanes = new ArrayList<>();
    }

    @Override
    public void registerAirplane(Airplane airplane) {
        airplanes.add(airplane);
    }

    @Override
    public void sendMessage(String message, Airplane sender) {
        // La torre recibe el mensaje y lo distribuye a todos los demás aviones
        for (Airplane airplane : airplanes) {
            // Evitamos reenviar el mensaje al avión que lo originó
            if (airplane != sender) {
                airplane.receive(message);
            }
        }
    }
}

// ==========================================
// 3. CLASE COMPONENT / COLLEAGUE (Colega)
// ==========================================
abstract class Airplane {
    protected AirTrafficControlMediator mediator;
    protected String name;

    public Airplane(AirTrafficControlMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
        mediator.registerAirplane(this); // Se registra automáticamente en la torre
    }

    public abstract void send(String message);

    public abstract void receive(String message);
}

// ==========================================
// 4. COLEGAS CONCRETOS (Concrete Colleagues)
// ==========================================
class CommercialAirplane extends Airplane {

    public CommercialAirplane(AirTrafficControlMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println("[" + name + "] Envia mensaje a la torre: \"" + message + "\"");
        // Se comunica exclusivamente a través del mediador
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println("[" + name + "] Recibe notificación de la torre: \"" + message + "\"");
    }
}

// ==========================================
// 5. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class MediatorPatternDemo {
    public static void main(String[] args) {
        // 1. Creamos el Mediador (Torre de Control)
        AirTrafficControlMediator controlTower = new AirportControlTower();

        // 2. Creamos los colegas (Aviones) vinculados al mediador
        Airplane plane1 = new CommercialAirplane(controlTower, "Vuelo Iberia 6400");
        Airplane plane2 = new CommercialAirplane(controlTower, "Vuelo Aeroméxico 120");
        System.out.println("--- COMUNICACIÓN VÍA TORRE DE CONTROL (MEDIATOR) ---\n");

        // El avión 1 envía un mensaje; la torre lo retransmite al resto sin que se
        // conozcan entre ellos
        plane1.send("Solicito permiso para iniciar maniobra de aterrizaje en pista 2.");

        System.out.println("\n--------------------------------------------------\n");

        // El avión 2 envía otro mensaje
        plane2.send("Entendido. Mantendré altitud de espera a 10,000 pies.");
    }
}