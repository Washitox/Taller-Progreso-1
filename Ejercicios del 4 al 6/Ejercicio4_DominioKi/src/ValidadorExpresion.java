// Valida si una expresión de Ki ([], {}, ()) está balanceada usando Pila
public class ValidadorExpresion {

    public static boolean esBalanceada(String expresion) {
        Pila<Character> pila = new Pila<>();

        for (char c : expresion.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                pila.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (pila.isEmpty()) return false;

                char abierto = pila.pop();
                if ((c == ')' && abierto != '(') ||
                        (c == '}' && abierto != '{') ||
                        (c == ']' && abierto != '[')) {
                    return false;
                }
            }
        }

        return pila.isEmpty();
    }
}
