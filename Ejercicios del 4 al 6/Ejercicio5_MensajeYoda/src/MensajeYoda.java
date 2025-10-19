import java.util.Scanner;

public class MensajeYoda {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese la secuencia RPN de Yoda (ej: \"entrenar\" \"debes\" + \"camino\" \"el\" + +):");
        String linea = sc.nextLine();

        String[] tokens = linea.split(" ");
        String mensaje = RPN.reconstruirMensaje(tokens);

        System.out.println("Mensaje de Yoda: " + mensaje);
    }
}
