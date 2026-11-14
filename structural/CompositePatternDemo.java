package structural;

import java.util.ArrayList;
import java.util.List;

// ==========================================
// 1. COMPONENTE (Interfaz común)
// ==========================================
// Declara la interfaz común tanto para los objetos simples como para los compuestos.
interface FileSystemComponent {
    void showDetails(String indent);

    int getSize(); // Devuelve el tamaño en KB
}

// ==========================================
// 2. HOJA (Leaf) - Objeto individual
// ==========================================
// Representa los objetos finales que no tienen hijos (ej. un archivo).
class FileItem implements FileSystemComponent {
    private String name;
    private int size; // en KB

    public FileItem(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "📄 Archivo: " + name + " (" + size + " KB)");
    }

    @Override
    public int getSize() {
        return size;
    }
}

// ==========================================
// 3. COMPUESTO (Composite) - Contenedor
// ==========================================
// Representa elementos complejos que pueden contener hijos (otros archivos u
// otras carpetas).
class Directory implements FileSystemComponent {
    private String name;
    // Lista para almacenar hijos que pueden ser tanto archivos como subcarpetas
    private List<FileSystemComponent> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    // Métodos para gestionar los hijos
    public void addComponent(FileSystemComponent component) {
        children.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        children.remove(component);
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "📁 Carpeta: " + name + " (Tamaño total: " + getSize() + " KB)");
        // Llama recursivamente a showDetails para cada hijo de manera uniforme
        for (FileSystemComponent component : children) {
            component.showDetails(indent + "   ");
        }
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        // Suma el tamaño de todos sus hijos de forma transparente
        for (FileSystemComponent component : children) {
            totalSize += component.getSize();
        }
        return totalSize;
    }
}

// ==========================================
// 4. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class CompositePatternDemo {
    public static void main(String[] args) {
        // Creamos archivos individuales (Hojas)
        FileSystemComponent file1 = new FileItem("curriculum.pdf", 500);
        FileSystemComponent file2 = new FileItem("foto_perfil.jpg", 1200);
        FileSystemComponent file3 = new FileItem("main.java", 45);
        FileSystemComponent file4 = new FileItem("readme.md", 10);

        // Creamos carpetas (Compuestos)
        Directory rootFolder = new Directory("Raíz");
        Directory docsFolder = new Directory("Documentos");
        Directory codeFolder = new Directory("Código Fuente");

        // Construimos la jerarquía de árbol
        docsFolder.addComponent(file1);
        docsFolder.addComponent(file2);

        codeFolder.addComponent(file3);
        codeFolder.addComponent(file4);

        rootFolder.addComponent(docsFolder);
        rootFolder.addComponent(codeFolder);

        // Mostramos toda la estructura y tamaños de forma uniforme
        System.out.println("--- Estructura del Sistema de Archivos ---");
        rootFolder.showDetails("");
    }
}