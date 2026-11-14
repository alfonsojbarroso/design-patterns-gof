# Patrones de Diseño GoF

Los **patrones de diseño GoF (Gang of Four)** son un conjunto de 23 patrones clásicos de diseño de software orientados a objetos, introducidos en 1994 por **Erich Gamma, Richard Helm, Ralph Johnson y John Vlissides**. Se dividen en tres categorías principales según su propósito:

---

## 1. Patrones Creacionales

Se centran en los mecanismos de creación de objetos, buscando optimizarlos y desacoplar el sistema respecto a cómo se deben instanciar y representar los objetos.

* **Abstract Factory:** Permite crear familias de objetos relacionados sin especificar sus clases concretas.
* **Builder:** Separa la construcción de un objeto complejo de su representación, permitiendo crear diferentes tipos y representaciones usando el mismo código de construcción.
* **Factory Method:** Define una interfaz para crear un objeto, pero deja que sean las subclases quienes decidan qué clase instanciar.
* **Prototype:** Permite crear nuevos objetos clonando instancias ya existentes (prototipos) en lugar de crearlos desde cero.
* **Singleton:** Garantiza que una clase tenga una única instancia global y proporciona un punto de acceso global a ella.

---

## 2. Patrones Estructurales

Tratan sobre cómo se combinan clases y objetos para formar estructuras más grandes y complejas, garantizando que si una parte cambia, toda la estructura no sufra alteraciones.

* **Adapter:** Permite que clases con interfaces incompatibles trabajen juntas al envolver una interfaz existente en otra esperada por el cliente.
* **Bridge:** Desacopla una abstracción de su implementación para que ambas puedan variar de forma independiente.
* **Composite:** Permite componer objetos en estructuras de árbol para representar jerarquías parte-todo, tratando a los objetos individuales y a los compuestos de manera uniforme.
* **Decorator:** Añade responsabilidades dinámicamente a un objeto para extender su funcionalidad sin modificar su código original.
* **Facade:** Proporciona una interfaz unificada y simplificada para un conjunto de interfaces complejas de un subsistema.
* **Flyweight:** Reduce el consumo de memoria compartiendo eficientemente datos comunes entre múltiples objetos similares.
* **Proxy:** Proporciona un sustituto o marcador de posición para otro objeto con el fin de controlar el acceso a este.

---

## 3. Patrones de Comportamiento

Se ocupan de la comunicación, la asignación de responsabilidades y los algoritmos entre objetos.

* **Chain of Responsibility:** Permite pasar solicitudes a lo largo de una cadena de manejadores hasta que uno de ellos procese la solicitud.
* **Command:** Encapsula una solicitud como un objeto, permitiendo parametrizar clientes con diferentes solicitudes, encolarlas o registrar operaciones.
* **Interpreter:** Define una representación para la gramática de un lenguaje junto con un intérprete que usa dicha representación para evaluar oraciones.
* **Iterator:** Permite recorrer los elementos de una colección secuencialmente sin exponer su representación interna.
* **Mediator:** Define un objeto que simplifica la comunicación caótica entre múltiples objetos, centralizando sus interacciones.
* **Memento:** Permite capturar y externalizar el estado interno de un objeto sin violar el encapsulamiento, para poder restaurarlo más tarde.
* **Observer:** Define una relación de dependencia de uno a muchos entre objetos, de manera que cuando un objeto cambia de estado, todos sus dependientes son notificados automáticamente.
* **State:** Permite a un objeto alterar su comportamiento cuando su estado interno cambia, aparentando que cambia de clase.
* **Strategy:** Define una familia de algoritmos, encapsula cada uno y los hace intercambiables, permitiendo que el algoritmo varíe independientemente de los clientes que lo utilizan.
* **Template Method:** Define el esqueleto de un algoritmo en una operación, delegando algunos pasos a las subclases sin cambiar su estructura general.
* **Visitor:** Permite separar un algoritmo de la estructura de objetos sobre la que opera, añadiendo nuevas operaciones sin modificar las clases de los elementos.


---

||
|:---:|
|Dr. Alfonso José Barroso Barajas|
|*Technology Architect*|