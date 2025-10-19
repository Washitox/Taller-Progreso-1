import java.util.LinkedList;
import java.util.Queue;

// Implementa la cola de esferas del dragón
public class Cola {
    private Queue<EsferaDragon> cola;

    public Cola() {
        cola = new LinkedList<>();
    }

    // Agregar esfera validando que no se repita y sea del 1 al 7
    public void agregar(EsferaDragon e) {
        int numero = e.getNumero();

        // Validar rango
        if (numero < 1 || numero > 7) {
            System.out.println("⚠️ Solo existen 7 esferas del dragón (1 a 7).");
            return;
        }

        // Validar duplicados
        for (EsferaDragon existente : cola) {
            if (existente.getNumero() == numero) {
                System.out.println("⚠️ La Esfera " + numero + " ya ha sido encontrada en " + existente.getUbicacion() + ".");
                return;
            }
        }

        // Si pasa las validaciones, se agrega
        cola.add(e);
        System.out.println("✅ Esfera " + e.getNumero() + " encontrada en " + e.getUbicacion() + ".");
    }

    public void mostrar() {
        if (cola.isEmpty()) {
            System.out.println("Aún no se ha encontrado ninguna esfera.");
            return;
        }

        System.out.println("Estado actual de las esferas encontradas:");
        for (EsferaDragon e : cola) {
            System.out.println("- " + e.toString());
        }
    }

    // Verifica si se reunieron las 7 esferas y las procesa
    public void invocar() {
        if (cola.size() == 7 && contieneTodasLasEsferas()) {
            System.out.println("\n🐉 Invocando a Shenlong...");
            System.out.println("✨ Las 7 Esferas han sido reunidas.");
            System.out.println("🌠 ¡Pide tu deseo!");
        } else {
            System.out.println("⏳ No se han reunido las 7 esferas todavía.");
        }
    }

    // Verifica que existan las esferas 1 al 7
    private boolean contieneTodasLasEsferas() {
        boolean[] encontradas = new boolean[8]; // índice 1-7
        for (EsferaDragon e : cola) {
            encontradas[e.getNumero()] = true;
        }

        for (int i = 1; i <= 7; i++) {
            if (!encontradas[i]) return false;
        }
        return true;
    }
}
