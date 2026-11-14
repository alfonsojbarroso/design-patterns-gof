package behavioral;

import java.util.ArrayList;
import java.util.List;

// ==========================================
// 1. CLASE MEMENTO (El Guardián del Estado)
// ==========================================
// Almacena el estado interno del Originator. Es inmutable.
class EditorMemento {
    private final String content;

    public EditorMemento(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

// ==========================================
// 2. CLASE ORIGINATOR (El Objeto cuyo estado se guarda)
// ==========================================
// Crea mementos que contienen una instantánea de su estado y usa mementos para
// restaurarlo
class TextEditor {
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    // Crea un memento con el estado actual
    public EditorMemento save() {
        return new EditorMemento(content);
    }

    // Restaura el estado a partir de un memento
    public void restore(EditorMemento memento) {
        if (memento != null) {
            this.content = memento.getContent();
        }
    }
}

// ==========================================
// 3. CLASE CARETAKER (El Historial / Gestor)
// ==========================================
// Responsable de almacenar el historial de mementos (no examina ni modifica su
// contenido)
class History {
    private List<EditorMemento> mementos = new ArrayList<>();

    public void push(EditorMemento memento) {
        mementos.add(memento);
    }

    public EditorMemento pop() {
        if (mementos.isEmpty()) {
            return null;
        }
        // Extrae el último estado guardado para hacer el "Undo"
        int lastIndex = mementos.size() - 1;
        EditorMemento memento = mementos.get(lastIndex);
        mementos.remove(lastIndex);
        return memento;
    }
}

// ==========================================
// 4. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class MementoPatternDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        History history = new History();

        // 1. Escribimos la primera versión
        editor.setContent("Versión 1: Hola mundo.");
        System.out.println("Texto actual: " + editor.getContent());

        // Guardamos el estado en el historial antes de modificar
        history.push(editor.save());

        // 2. Escribimos la segunda versión
        editor.setContent("Versión 2: Hola mundo, este es el patrón Memento.");
        System.out.println("Texto actual: " + editor.getContent());

        // Guardamos el estado nuevamente
        history.push(editor.save());

        // 3. Escribimos una tercera versión (cambio erróneo o indeseado)
        editor.setContent("Versión 3: Texto dañado o no deseado...");
        System.out.println("Texto actual: " + editor.getContent());

        // 4. Deshacemos (Undo) recuperando el último memento guardado
        System.out.println("\n--- Ejecutando Deshacer (Undo) ---");
        editor.restore(history.pop());
        System.out.println("Texto restaurado: " + editor.getContent());

        // 5. Deshacemos otra vez para volver al estado inicial
        System.out.println("\n--- Ejecutando Deshacer (Undo) nuevamente ---");
        editor.restore(history.pop());
        System.out.println("Texto restaurado: " + editor.getContent());
    }
}