// ==========================================
// 1. PRODUCTOS ABSTRACTOS
// ==========================================
interface Button {
    void paint();
}

interface Checkbox {
    void paint();
}

// ==========================================
// 2. PRODUCTOS CONCRETOS PARA WINDOWS
// ==========================================
class WinButton implements Button {
    @Override
    public void paint() {
        System.out.println("Renderizando un botón estilo Windows.");
    }
}

class WinCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Renderizando un checkbox estilo Windows.");
    }
}

// ==========================================
// 2. PRODUCTOS CONCRETOS PARA MACOS
// ==========================================
class MacButton implements Button {
    @Override
    public void paint() {
        System.out.println("Renderizando un botón estilo MacOS.");
    }
}

class MacCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Renderizando un checkbox estilo MacOS.");
    }
}

// ==========================================
// 3. FÁBRICA ABSTRACTA
// ==========================================
interface GUIFactory {
    Button createButton();

    Checkbox createCheckbox();
}

// ==========================================
// 4. FÁBRICAS CONCRETAS
// ==========================================
class WinFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WinButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WinCheckbox();
    }
}

class MacFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}

// ==========================================
// 5. CLASE CLIENTE
// ==========================================
class Application {
    private Button button;
    private Checkbox checkbox;

    // El cliente trabaja con la fábrica abstracta, ignorando las clases concretas
    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}

// ==========================================
// 6. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class AbstractFactoryDemo {

    private static Application configureApplication() {
        Application app;
        // Simulamos la detección del sistema operativo
        String osName = "windows"; // Puede cambiarse a "mac" para probar la otra familia

        if (osName.contains("mac")) {
            GUIFactory factory = new MacFactory();
            app = new Application(factory);
        } else {
            GUIFactory factory = new WinFactory();
            app = new Application(factory);
        }
        return app;
    }

    public static void main(String[] args) {
        Application app = configureApplication();
        app.paint();
    }
}