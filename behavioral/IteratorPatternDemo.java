package behavioral;

// ==========================================
// 1. INTERFAZ ITERATOR
// ==========================================
interface Iterator<T> {
    boolean hasNext();

    T next();
}

// ==========================================
// 2. INTERFAZ CONTAINER (Colección)
// ==========================================
interface Container<T> {
    Iterator<T> getIterator();
}

// ==========================================
// 3. COLECCIÓN CONCRETA (Concrete Aggregate)
// ==========================================
class NameRepository implements Container<String> {
    private String[] names = { "Ana", "Carlos", "Beatriz", "David" };

    @Override
    public Iterator<String> getIterator() {
        return new NameIterator();
    }

    // Iterador Concreto interno
    private class NameIterator implements Iterator<String> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            if (index < names.length) {
                return true;
            }
            return false;
        }

        @Override
        public String next() {
            if (this.hasNext()) {
                return names[index++];
            }
            return null;
        }
    }
}

// ==========================================
// 4. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class IteratorPatternDemo {
    public static void main(String[] args) {
        NameRepository namesRepository = new NameRepository();

        for (Iterator<String> iter = namesRepository.getIterator(); iter.hasNext();) {
            String name = iter.next();
            System.out.println("Nombre: " + name);
        }
    }
}