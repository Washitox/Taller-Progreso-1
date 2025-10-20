import java.util.Stack;

class Pila {
    private Stack<Nave> stack;

    public Pila() {
        stack = new Stack<>();
    }

    public void añadir(Nave nave) {
        stack.push(nave);
    }

    public Nave eliminar() {
        if (!stack.isEmpty()) {
            return stack.pop();
        } else {
            return null;
        }
    }

    public boolean estaVacia() {
        return stack.isEmpty();
    }

    public String mostrarTodo() {
        StringBuilder sb = new StringBuilder();
        Stack<Nave> temp = new Stack<>();
        while (!stack.isEmpty()) {
            Nave n = stack.pop();
            sb.append(n).append(" ");
            temp.push(n);
        }
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
        return sb.toString().trim();
    }

    // Buscar nave por ID
    public boolean buscar(int id) {
        for (Nave n : stack) {
            if (n.getId() == id) {
                return true;
            }
        }
        return false;
    }
}