import java.util.Scanner;
import java.util.Stack;

public class MensajeYoda {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la secuencia RPN de Yoda (ej: \"entrenar\" \"debes\" + \"camino\" \"el\" + +):");
        String linea = sc.nextLine();

        // Dividimos los tokens por espacio
        String[] tokens = linea.split(" ");

        Stack<String> pila = new Stack<>();

        for (String token : tokens) {
            if (token.equals("+")) {
                if (pila.size() >= 2) {
                    String second = pila.pop(); // último ingresado
                    String first = pila.pop();  // penúltimo ingresado
                    // Concatenar el último delante del penúltimo para respetar el orden RPN
                    pila.push(second + " " + first);
                } else {
                    System.out.println("Error: operador '+' sin suficientes operandos.");
                }
            } else {
                // Eliminamos las comillas si existen
                token = token.replaceAll("^\"|\"$", "");
                pila.push(token);
            }
        }

        if (pila.size() == 1) {
            System.out.println("Mensaje de Yoda: " + pila.pop());
        } else {
            System.out.println("Error: La expresión RPN no está bien formada.");
        }
    }
}
