package structural;

// ==========================================
// 1. SUBSISTEMAS COMPLEJOS (Clases independientes)
// ==========================================
class Amplifier {
    public void on() {
        System.out.println("Amplificador encendido.");
    }

    public void setVolume(int level) {
        System.out.println("Volumen del amplificador ajustado a: " + level);
    }

    public void off() {
        System.out.println("Amplificador apagado.");
    }
}

class StreamingPlayer {
    public void on() {
        System.out.println("Reproductor de Streaming encendido.");
    }

    public void play(String movie) {
        System.out.println("Reproduciendo la película: '" + movie + "'");
    }

    public void stop() {
        System.out.println("Reproducción detenida.");
    }

    public void off() {
        System.out.println("Reproductor de Streaming apagado.");
    }
}

class Projector {
    public void on() {
        System.out.println("Proyector encendido.");
    }

    public void wideScreenMode() {
        System.out.println("Proyector configurado en modo pantalla panorámica (16:9).");
    }

    public void off() {
        System.out.println("Proyector apagado.");
    }
}

class TheaterLights {
    public void dim(int level) {
        System.out.println("Atenuando las luces de la sala al " + level + "%.");
    }

    public void on() {
        System.out.println("Luces de la sala encendidas al 100%.");
    }
}

// ==========================================
// 2. CLASE FACADE (La Fachada)
// ==========================================
// Orquesta los subsistemas y ofrece métodos simples de alto nivel al cliente
class HomeTheaterFacade {
    private Amplifier amp;
    private StreamingPlayer player;
    private Projector projector;
    private TheaterLights lights;

    public HomeTheaterFacade(Amplifier amp, StreamingPlayer player, Projector projector, TheaterLights lights) {
        this.amp = amp;
        this.player = player;
        this.projector = projector;
        this.lights = lights;
    }

    // Método simplificado para iniciar toda la experiencia de cine
    public void watchMovie(String movie) {
        System.out.println("\n--- Preparando todo para ver una película ---");
        lights.dim(10);
        projector.on();
        projector.wideScreenMode();
        amp.on();
        amp.setVolume(25);
        player.on();
        player.play(movie);
    }

    // Método simplificado para apagar todo al terminar
    public void endMovie() {
        System.out.println("\n--- Apagando el sistema de cine en casa ---");
        player.stop();
        player.off();
        amp.off();
        projector.off();
        lights.on();
    }
}

// ==========================================
// 3. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class FacadePatternDemo {
    public static void main(String[] args) {
        // Instanciamos los componentes individuales del subsistema
        Amplifier amp = new Amplifier();
        StreamingPlayer player = new StreamingPlayer();
        Projector projector = new Projector();
        TheaterLights lights = new TheaterLights();

        // Creamos la fachada pasándole los subsistemas
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(amp, player, projector, lights);

        // El cliente interactúa únicamente con la fachada usando métodos sencillos
        homeTheater.watchMovie("Inception");

        // Al terminar la película...
        homeTheater.endMovie();
    }
}