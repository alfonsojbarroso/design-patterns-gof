package behavioral;

import java.util.ArrayList;
import java.util.List;

// ==========================================
// 1. INTERFAZ SUBJECT (Sujeto / Observable)
// ==========================================
interface Subject {
    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObservers();
}

// ==========================================
// 2. INTERFAZ OBSERVER (Observador)
// ==========================================
interface Observer {
    void update(String news);
}

// ==========================================
// 3. SUJETO CONCRETO (Concrete Subject)
// ==========================================
class NewsAgency implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String news;

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(news);
        }
    }

    public void setNews(String news) {
        this.news = news;
        notifyObservers(); // Notifica automáticamente a todos los observadores ante un cambio
    }
}

// ==========================================
// 4. OBSERVADORES CONCRETOS (Concrete Observers)
// ==========================================
class EmailSubscriber implements Observer {
    private String email;

    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void update(String news) {
        System.out.println("📧 Enviando Email a " + email + " con la noticia: " + news);
    }
}

class MobileAppSubscriber implements Observer {
    private String username;

    public MobileAppSubscriber(String username) {
        this.username = username;
    }

    @Override
    public void update(String news) {
        System.out.println("📱 Notificación Push en la app para " + username + ": " + news);
    }
}

// ==========================================
// 5. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class ObserverPatternDemo {
    public static void main(String[] args) {
        // Creamos el sujeto (la agencia de noticias)
        NewsAgency agency = new NewsAgency();

        // Creamos los observadores (suscriptores)
        Observer subscriber1 = new EmailSubscriber("carlos@example.com");
        Observer subscriber2 = new MobileAppSubscriber("Ana_Dev");

        // Registramos los observadores en el sujeto
        agency.attach(subscriber1);
        agency.attach(subscriber2);

        // Cambiamos el estado (publicamos una noticia) -> esto notificará
        // automáticamente a todos
        System.out.println("--- Publicando noticia 1 ---");
        agency.setNews("¡Java 25 lanzado oficialmente con nuevas características!");

        System.out.println("\n--- Desuscribiendo a Ana y publicando noticia 2 ---");
        agency.detach(subscriber2); // Ana ya no recibirá notificaciones
        agency.setNews("¡Se ha anunciado un nuevo framework de microservicios!");
    }
}