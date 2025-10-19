// Reconstruye el mensaje de Yoda usando RPN (Forma Polaca Inversa)
public class RPN {

    public static String reconstruirMensaje(String[] tokens) {
        Pila<String> pila = new Pila<>();

        for (String token : tokens) {
            if (!token.equals("+")) {
                // Elimina las comillas si existen
                pila.push(token.replace("\"", ""));
            } else {
                String segundo = pila.pop();
                String primero = pila.pop();
                pila.push(primero + " " + segundo);
            }
        }

        return pila.pop();
    }
}
