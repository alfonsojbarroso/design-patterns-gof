package behavioral;

// ==========================================
// 1. CLASE ABSTRACTA (Define el Template Method)
// ==========================================
abstract class DataMiner {

    // Este es el Template Method (marcado como final para evitar que sea alterado)
    public final void mine(String path) {
        openFile(path);
        extractData();
        parseData();
        analyzeData();
        sendReport();
        closeFile();
    }

    // Pasos comunes con implementación genérica
    public void openFile(String path) {
        System.out.println("Abriendo archivo en la ruta: " + path);
    }

    public void closeFile() {
        System.out.println("Cerrando archivo y liberando recursos.");
    }

    // Pasos abstractos que las subclases DEBEN implementar obligatoriamente
    abstract void extractData();

    abstract void parseData();

    // Hook (Gancho): Método opcional con implementación por defecto que las
    // subclases pueden sobrescribir
    boolean analyzeData() {
        System.out.println("Realizando análisis de datos estándar...");
        return true;
    }

    abstract void sendReport();
}

// ==========================================
// 2. CLASE CONCRETA 1 (Minería de PDF)
// ==========================================
class PdfDataMiner extends DataMiner {
    @Override
    void extractData() {
        System.out.println("Extrayendo texto y tablas específicas de formato PDF.");
    }

    @Override
    void parseData() {
        System.out.println("Analizando y estructurando datos de PDF a objetos JSON.");
    }

    @Override
    void sendReport() {
        System.out.println("Enviando reporte de PDF por correo electrónico.");
    }
}

// ==========================================
// 3. CLASE CONCRETA 2 (Minería de CSV)
// ==========================================
class CsvDataMiner extends DataMiner {
    @Override
    void extractData() {
        System.out.println("Leyendo filas y columnas separadas por comas (CSV).");
    }

    @Override
    void parseData() {
        System.out.println("Mapeando columnas CSV a entidades del dominio.");
    }

    // Sobrescribimos opcionalmente el hook de análisis
    @Override
    boolean analyzeData() {
        System.out.println("Ejecutando análisis estadístico avanzado específico para CSV...");
        return true;
    }

    @Override
    void sendReport() {
        System.out.println("Exportando reporte de CSV a un servidor Dashboard.");
    }
}

// ==========================================
// 4. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class TemplateMethodPatternDemo {
    public static void main(String[] args) {
        System.out.println("--- PROCESANDO ARCHIVO PDF ---");
        DataMiner pdfMiner = new PdfDataMiner();
        pdfMiner.mine("documento_financiero.pdf");

        System.out.println("\n--- PROCESANDO ARCHIVO CSV ---");
        DataMiner csvMiner = new CsvDataMiner();
        csvMiner.mine("ventas_anuales.csv");
    }
}