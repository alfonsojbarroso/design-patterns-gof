package behavioral;

// ==========================================
// 1. INTERFAZ EXPRESSION (Expresión abstracta)
// ==========================================
interface Expression {
    int interpret();
}

// ==========================================
// 2. EXPRESIÓN TERMINAL (TerminalExpression)
// ==========================================
class NumberExpression implements Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        return number;
    }
}

// ==========================================
// 3. EXPRESIONES NO TERMINALES (NonTerminalExpression)
// ==========================================
class AddExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public AddExpression(Expression left, Expression right) {
        this.leftExpression = left;
        this.rightExpression = right;
    }

    @Override
    public int interpret() {
        return leftExpression.interpret() + rightExpression.interpret();
    }
}

class SubtractExpression implements Expression {
    private Expression leftExpression;
    private Expression rightExpression;

    public SubtractExpression(Expression left, Expression right) {
        this.leftExpression = left;
        this.rightExpression = right;
    }

    @Override
    public int interpret() {
        return leftExpression.interpret() - rightExpression.interpret();
    }
}

// ==========================================
// 4. CLASE PRINCIPAL (EJECUCIÓN)
// ==========================================
public class InterpreterPatternDemo {
    public static void main(String[] args) {
        // Representa la expresión: (5 + 10) - 3
        // Construimos el árbol sintáctico: Substract(Add(5, 10), 3)
        Expression expression = new SubtractExpression(
                new AddExpression(new NumberExpression(5), new NumberExpression(10)),
                new NumberExpression(3));

        int result = expression.interpret();
        System.out.println("Resultado de la expresión (5 + 10) - 3 = " + result);
    }
}