package structural;

// ==========================================
// 1. IMPLEMENTADOR (Interfaz de la Implementación)
// ==========================================
// Define la interfaz común para todas las implementaciones de dispositivos
interface Device {
    boolean isEnabled();

    void enable();

    void disable();

    int getVolume();

    void setVolume(int percent);

    int getChannel();

    void setChannel(int channel);

    void printStatus();
}

// ==========================================
// 2. IMPLEMENTACIONES CONCRETAS
// ==========================================
class Television implements Device {
    private boolean on = false;
    private int volume = 30;
    private int channel = 1;

    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        on = true;
    }

    @Override
    public void disable() {
        on = false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int volume) {
        if (volume > 100) {
            this.volume = 100;
        } else if (volume < 0) {
            this.volume = 0;
        } else {
            this.volume = volume;
        }
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
    }

    @Override
    public void printStatus() {
        System.out.println("\n------------------------------------");
        System.out.println("| I'm a TV. Status: " + (on ? "Enabled" : "Disabled"));
        System.out.println("| Volume: " + volume + " | Channel: " + channel);
        System.out.println("------------------------------------\n");
    }
}

class Radio implements Device {
    private boolean on = false;
    private int volume = 20;
    private int channel = 95;

    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        on = true;
    }

    @Override
    public void disable() {
        on = false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int volume) {
        if (volume > 50) {
            this.volume = 50;
        } // Máximo 50 para radio
        else if (volume < 0) {
            this.volume = 0;
        } else {
            this.volume = volume;
        }
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
    }

    @Override
    public void printStatus() {
        System.out.println("\n------------------------------------");
        System.out.println("| I'm a Radio. Status: " + (on ? "Enabled" : "Disabled"));
        System.out.println("| Volume: " + volume + " | Frequency: " + channel + " FM");
        System.out.println("------------------------------------\n");
    }
}

// ==========================================
// 3. ABSTRACCIÓN
// ==========================================
// Define la interfaz de control de alto nivel y mantiene una referencia al
// objeto Device (el "puente")
class RemoteControl {
    protected Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    public void togglePower() {
        if (device.isEnabled()) {
            device.disable();
        } else {
            device.enable();
        }
    }

    public void volumeDown() {
        device.setVolume(device.getVolume() - 10);
    }

    public void volumeUp() {
        device.setVolume(device.getVolume() + 10);
    }

    public void channelDown() {
        device.setChannel(device.getChannel() - 1);
    }

    public void channelUp() {
        device.setChannel(device.getChannel() + 1);
    }
}

// ==========================================
// 4. ABSTRACCIÓN REFINADA
// ==========================================
// Extiende la funcionalidad de la abstracción sin tocar las clases de
// dispositivos
class AdvancedRemoteControl extends RemoteControl {

    public AdvancedRemoteControl(Device device) {
        super(device);
    }

    public void mute() {
        System.out.println("Remote: Silenciando dispositivo...");
        device.setVolume(0);
    }
}

// ==========================================
// 5. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class BridgePatternDemo {
    public static void main(String[] args) {
        // 1. Probamos una Televisión con un Control Remoto Básico
        Device tv = new Television();
        RemoteControl basicRemote = new RemoteControl(tv);

        basicRemote.togglePower();
        tv.printStatus();

        basicRemote.volumeUp();
        basicRemote.channelUp();
        tv.printStatus();

        // 2. Probamos una Radio con un Control Remoto Avanzado (con función Mute)
        Device radio = new Radio();
        AdvancedRemoteControl advancedRemote = new AdvancedRemoteControl(radio);

        advancedRemote.togglePower();
        radio.printStatus();

        advancedRemote.mute(); // Característica avanzada del control
        radio.printStatus();
    }
}