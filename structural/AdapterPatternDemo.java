package structural;

// ==========================================
// 1. INTERFAZ TARGET (Lo que el cliente espera usar)
// ==========================================
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// ==========================================
// 2. INTERFAZ ADAPTEE (Interfaz existente incompatible)
// ==========================================
interface AdvancedMediaPlayer {
    void playVlc(String fileName);

    void playMp4(String fileName);
}

// Implementaciones concretas del Adaptee
class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Reproduciendo archivo VLC. Nombre: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // No hace nada
    }
}

class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // No hace nada
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Reproduciendo archivo MP4. Nombre: " + fileName);
    }
}

// ==========================================
// 3. CLASE ADAPTER (El Adaptador)
// ==========================================
// Implementa la interfaz Target (MediaPlayer) y traduce las llamadas al Adaptee
class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}

// ==========================================
// 4. CLASE CLIENTE (AudioPlayer)
// ==========================================
class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        // Reproductor nativo integrado para MP3
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Reproduciendo archivo MP3. Nombre: " + fileName);
        }
        // Utiliza el Adapter para manejar formatos que nativamente no soporta
        else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Formato inválido: " + audioType + " no soportado.");
        }
    }
}

// ==========================================
// 5. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        // Reproduce MP3 (Soporte nativo)
        audioPlayer.play("mp3", "cancion_favorita.mp3");

        // Reproduce MP4 usando el Adapter
        audioPlayer.play("mp4", "video_musical.mp4");

        // Reproduce VLC usando el Adapter
        audioPlayer.play("vlc", "pelicula_indie.vlc");

        // Intenta reproducir un formato no soportado
        audioPlayer.play("avi", "video_antiguo.avi");
    }
}