package behavioral;

// ==========================================
// 1. INTERFAZ STATE (El Estado)
// ==========================================
interface OrderState {
    void payOrder(OrderContext context);

    void shipOrder(OrderContext context);

    void deliverOrder(OrderContext context);

    void cancelOrder(OrderContext context);
}

// ==========================================
// 2. CLASE CONTEXTO (Context)
// ==========================================
// Mantiene una referencia al estado actual y delega las peticiones en él
class OrderContext {
    private OrderState currentState;

    public OrderContext() {
        // Todo pedido nuevo inicia en el estado NewState
        this.currentState = new NewState();
        System.out.println("[Pedido creado] Estado actual: NUEVO");
    }

    public void setState(OrderState state) {
        this.currentState = state;
    }

    public OrderState getState() {
        return currentState;
    }

    // Métodos delegados al estado actual
    public void pay() {
        currentState.payOrder(this);
    }

    public void ship() {
        currentState.shipOrder(this);
    }

    public void deliver() {
        currentState.deliverOrder(this);
    }

    public void cancel() {
        currentState.cancelOrder(this);
    }
}

// ==========================================
// 3. ESTADOS CONCRETOS (Concrete States)
// ==========================================

// Estado 1: Nuevo
class NewState implements OrderState {
    @Override
    public void payOrder(OrderContext context) {
        System.out.println("-> Pago procesado exitosamente.");
        context.setState(new PaidState()); // Transición de estado a Pagado
    }

    @Override
    public void shipOrder(OrderContext context) {
        System.out.println("❌ Error: No se puede enviar un pedido que aún no ha sido pagado.");
    }

    @Override
    public void deliverOrder(OrderContext context) {
        System.out.println("❌ Error: El pedido no ha sido ni pagado ni enviado.");
    }

    @Override
    public void cancelOrder(OrderContext context) {
        System.out.println("-> Pedido cancelado exitosamente desde el estado Nuevo.");
        context.setState(new CancelledState());
    }
}

// Estado 2: Pagado
class PaidState implements OrderState {
    @Override
    public void payOrder(OrderContext context) {
        System.out.println("⚠️ El pedido ya se encuentra pagado.");
    }

    @Override
    public void shipOrder(OrderContext context) {
        System.out.println("-> Pedido empaquetado y enviado al cliente.");
        context.setState(new ShippedState()); // Transición a Enviado
    }

    @Override
    public void deliverOrder(OrderContext context) {
        System.out.println("❌ Error: El pedido está pagado pero aún no ha sido enviado.");
    }

    @Override
    public void cancelOrder(OrderContext context) {
        System.out.println("-> Pedido cancelado y reembolso emitido.");
        context.setState(new CancelledState());
    }
}

// Estado 3: Enviado
class ShippedState implements OrderState {
    @Override
    public void payOrder(OrderContext context) {
        System.out.println("⚠️ El pedido ya fue pagado con anterioridad.");
    }

    @Override
    public void shipOrder(OrderContext context) {
        System.out.println("⚠️ El pedido ya está en camino.");
    }

    @Override
    public void deliverOrder(OrderContext context) {
        System.out.println("-> Pedido entregado satisfactoriamente al cliente.");
        context.setState(new DeliveredState()); // Transición a Entregado
    }

    @Override
    public void cancelOrder(OrderContext context) {
        System.out.println("❌ Error: No se puede cancelar un pedido que ya está en ruta de entrega.");
    }
}

// Estado 4: Entregado (Estado Final)
class DeliveredState implements OrderState {
    @Override
    public void payOrder(OrderContext context) {
        System.out.println("⚠️ Operación inválida: Pedido ya entregado.");
    }

    @Override
    public void shipOrder(OrderContext context) {
        System.out.println("⚠️ Operación inválida: Pedido ya entregado.");
    }

    @Override
    public void deliverOrder(OrderContext context) {
        System.out.println("⚠️ El pedido ya fue entregado previamente.");
    }

    @Override
    public void cancelOrder(OrderContext context) {
        System.out.println("❌ Error: No se puede cancelar un pedido ya entregado. (Inicie devolución).");
    }
}

// Estado 5: Cancelado (Estado Final)
class CancelledState implements OrderState {
    @Override
    public void payOrder(OrderContext context) {
        System.out.println("❌ Operación denegada: El pedido está cancelado.");
    }

    @Override
    public void shipOrder(OrderContext context) {
        System.out.println("❌ Operación denegada: El pedido está cancelado.");
    }

    @Override
    public void deliverOrder(OrderContext context) {
        System.out.println("❌ Operación denegada: El pedido está cancelado.");
    }

    @Override
    public void cancelOrder(OrderContext context) {
        System.out.println("⚠️ El pedido ya se encontraba cancelado.");
    }
}

// ==========================================
// 4. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class StatePatternDemo {
    public static void main(String[] args) {
        // Creamos un nuevo pedido
        OrderContext order = new OrderContext();

        // Intentamos enviar antes de pagar (Fallará por reglas de negocio del estado
        // actual)
        System.out.println("\n--- Intentando enviar sin pagar ---");
        order.ship();

        // Pagamos el pedido (Cambiará el estado a PaidState)
        System.out.println("\n--- Procesando Pago ---");
        order.pay();

        // Enviamos el pedido (Cambiará el estado a ShippedState)
        System.out.println("\n--- Enviando Pedido ---");
        order.ship();

        // Intentamos cancelar un pedido que ya va en camino (Fallará)
        System.out.println("\n--- Intentando cancelar en ruta ---");
        order.cancel();

        // Entregamos el pedido con éxito (Cambiará a DeliveredState)
        System.out.println("\n--- Finalizando Entrega ---");
        order.deliver();
    }
}