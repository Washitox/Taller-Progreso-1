import java.util.Scanner;

public class BusquedaEsferas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cola cola = new Cola();
        String comando;

        System.out.println("✨ Sistema de búsqueda de las 7 Esferas del Dragón ✨");

        while (true) {
            System.out.println("\nIngrese un comando (agregar <número> \"<ubicación>\", mostrar, invocar, salir):");
            comando = sc.nextLine().trim();

            if (comando.equalsIgnoreCase("salir")) break;

            if (comando.toLowerCase().startsWith("agregar")) {
                String[] partes = comando.split(" ", 3);

                if (partes.length < 3) {
                    System.out.println("⚠️ Formato incorrecto. Usa: agregar <número> \"<ubicación>\"");
                    continue;
                }

                try {
                    int numero = Integer.parseInt(partes[1]);
                    String ubicacion = partes[2].replace("\"", "");
                    cola.agregar(new EsferaDragon(numero, ubicacion));
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Error: el número de esfera debe ser un entero válido.");
                }
            } else if (comando.equalsIgnoreCase("mostrar")) {
                cola.mostrar();
            } else if (comando.equalsIgnoreCase("invocar")) {
                cola.invocar();
            } else {
                System.out.println("Comando no reconocido.");
            }
        }

        System.out.println("\nFin del sistema. 🐉");
    }
}
