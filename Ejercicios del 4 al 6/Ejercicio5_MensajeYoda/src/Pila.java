// Implementación simple de una pila genérica
import java.util.ArrayList;

public class Pila<T> {
    private ArrayList<T> elementos;

    public Pila() {
        elementos = new ArrayList<>();
    }

    public void push(T elemento) {
        elementos.add(elemento);
    }

    public T pop() {
        if (!isEmpty()) {
            return elementos.remove(elementos.size() - 1);
        }
        return null;
    }

    public T peek() {
        if (!isEmpty()) {
            return elementos.get(elementos.size() - 1);
        }
        return null;
    }

    public boolean isEmpty() {
        return elementos.isEmpty();
    }
}
