package behavioral;

// ==========================================
// 1. INTERFAZ COMMAND (El Comando)
// ==========================================
// Declara el método de ejecución y opcionalmente el de deshacer
interface Command {
    void execute();

    void undo();
}

// ==========================================
// 2. RECEPTOR (Receiver)
// ==========================================
// Contiene la lógica real de negocio sobre qué hacer
class Light {
    private String location;

    public Light(String location) {
        this.location = location;
    }

    public void turnOn() {
        System.out.println("💡 La luz de [" + location + "] se ha ENCENDIDO.");
    }

    public void turnOff() {
        System.out.println("🌑 La luz de [" + location + "] se ha APAGADO.");
    }
}

// ==========================================
// 3. COMANDOS CONCRETOS (Concrete Commands)
// ==========================================
// Encapsulan la petición vinculando un receptor con una acción específica

class TurnOnLightCommand implements Command {
    private Light light;

    public TurnOnLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff(); // La acción inversa de encender es apagar
    }
}

class TurnOffLightCommand implements Command {
    private Light light;

    public TurnOffLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn(); // La acción inversa de apagar es encender
    }
}

// ==========================================
// 4. INVOCADOR (Invoker)
// ==========================================
// Solicita que se ejecute el comando sin conocer los detalles del receptor
class RemoteControl {
    private Command slot;
    private Command lastCommand;

    // Permite asignar dinámicamente cualquier comando al botón
    public void setCommand(Command command) {
        this.slot = command;
    }

    public void pressButton() {
        if (slot != null) {
            slot.execute();
            lastCommand = slot; // Guardamos el comando ejecutado para poder deshacerlo
        }
    }

    public void pressUndoButton() {
        if (lastCommand != null) {
            System.out.print("[Deshaciendo última acción] -> ");
            lastCommand.undo();
        }
    }
}

// ==========================================
// 5. CLASE PRINCIPAL (Cliente / Ejecución)
// ==========================================
public class CommandPatternDemo {
    public static void main(String[] args) {
        // 1. Creamos el Receptor (el dispositivo real)
        Light livingRoomLight = new Light("Sala de Estar");

        // 2. Creamos los Comandos concretos asociándolos al receptor
        Command lightOn = new TurnOnLightCommand(livingRoomLight);
        Command lightOff = new TurnOffLightCommand(livingRoomLight);

        // 3. Creamos el Invocador (el control remoto)
        RemoteControl remote = new RemoteControl();

        // 4. Configuramos y presionamos el botón para encender la luz
        remote.setCommand(lightOn);
        remote.pressButton();

        // 5. Configuramos y presionamos el botón para apagar la luz
        remote.setCommand(lightOff);
        remote.pressButton();

        // 6. Probamos la funcionalidad de deshacer (volverá a encender la luz)
        remote.pressUndoButton();
    }
}